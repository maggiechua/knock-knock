package Controller;

import java.io.InputStreamReader;
import java.util.Scanner;

import Model.GameModel;
import Model.Player;
import View.GameView;

/**
 * The following class represents the Controller for the Knock-Knock Game,
 * serving as a mediator between model and view functionality. The following
 * allows for a text-based version of the game to be run for initial building.
 */
public class Controller implements GameController {
  private GameModel model;
  private GameView view;
  private Readable rd;

  /**
   * The Controller constructor uses a given model and view to allow for gameplay
   * of Knock-Knock
   * @param model represents the model of the game
   * @param view represents the view of the game
   */
  public Controller(GameModel model, GameView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or View is null");
    }
    else {
      this.model = model;
      this.view = view;
      this.rd = new InputStreamReader(System.in);
    }
  }

  @Override
  public void execute() {
    // Setup of the Game
    view.welcomeMessage();
    model.generateHands();
    basicGameplay(false);
  }

  /**
   * The basicGameplay() method is used to start the game and update it as players make their
   * moves until only one person is left with cards in their hand.
   * @param endGame a Boolean representation of whether the game has ended
   */
  private void basicGameplay(boolean endGame) {
    // Cases:
    // - game just started -> determine starting card [X]
    // - player has no valid cards they can play -> draw card + move to next player [X]
    // - player has valid cards they can play -> ask them to select and add their card to pile [X]
    //   - normal card -> add to pile and move to next player [X]
    //   - only have two remaining cards -> check if they 'say'
    //   'Knock-Knock' (if yes, remind other players; no, force player to draw another card)
    //   - place a special card (A, 2, J) -> must skip next player accordingly and force them to draw
    //   the required number of cards []
    //   - place a special card (2, J) -> requires next player to draw the required number of cards [X]
    //   - place a special card (7) -> change direction of game, so player's turns go other way []
    //   - place a special card (8) -> ask player what suit they would like to change game to []
    // - player has played their last card -> depending on game, once first person runs out, they
    //   automatically win; otherwise, keep playing until only one person left with cards
    model.getTopCardInPile(true);
    model.setUpOrder();
    Player currentPlayer = model.getNextPlayer(null, false);
    while (!endGame) {
      view.printTopCardInPile(model.getTopCardInPile(false));
      if (!model.hasValidPlays(currentPlayer)) {
        model.drawCards(1, currentPlayer);
        view.printNoValidPlays(currentPlayer.getPlayerName());
        view.printPlayerHand(model.getHand(currentPlayer));
      }
      else {
        view.printPlayerHand(model.getHand(currentPlayer));
        view.printPlayerTurn(currentPlayer.getPlayerName());
        playCard(currentPlayer);
        model.resetValidPlays(currentPlayer);
      }
      // check special cards
      String value = model.checkSpecialCard(model.getTopCardInPile(false)); // A J 2 | 7 8
      specialCardPlayed(value, currentPlayer);
      currentPlayer = model.getNextPlayer(currentPlayer, false);
    }
  }

  /**
   * The playCard() method is used during a player's turn where a player selects a card in their
   * hand to play and the request is granted it valid, otherwise, they are prompted to choose a
   * different valid card, or forced to draw a card.
   * @param p the given Player
   */
  private void playCard(Player p) {
    Scanner sc = new Scanner(rd);
    String nextPlay = sc.next();
    if (model.canPlayCard(nextPlay)) {
      model.updateHand(nextPlay, p);
    }
    else {
      view.printInvalidPlay();
      playCard(p);
    }
  }

  /**
   * The specialCardPlayed() method checks to see if the given value of the card matches any of
   * the special cards. If so, it saves the command and executes it.
   * @param value a String representation of the special card's value; is an empty string if the
   *              card is not a special card
   * @param p the given Player that played the special card
   */
  private void specialCardPlayed(String value, Player p) {
    PlayCommand command = null;
    switch (value) {
      case "J":
        command = new Draw7Command(p);
        break;
      case "2":
        command = new Draw2Command(p);
        break;
      case "8":
        command = new ChangeSuit8Command(p);
        break;
      case "7":
        command = new Reverse7Command(p);
        break;
      case "A":
        command = new SkipCommand(p);
        break;
    }
    try {
      command.execute();
    }
    catch (Exception e) {
      // do nothing; all program to continue running
      // not sure if best way to handle, but this is what we'll do for now
    }
  }

  /**
   * The SkipCommand class executes the skip special card (A of any suit) by skipping over the next
   * player's turn.
   */
  public class SkipCommand extends PlayCommand {
    public SkipCommand(Player current) {
      super(current);
    }

    @Override
    void execute() {
      model.getNextPlayer(current.getNextPlayer(), false);
    }
  }

  /**
   * The Draw2Command class executes the draw 2 special card (2 of any suit) by forcing the
   * next player to add 2 cards to their hand and skips their turn.
   */
  public class Draw2Command extends PlayCommand {
    public Draw2Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      view.printDrawCards(current.getNextPlayer().getPlayerName(), "Draw 2");
      model.drawCards(2, current.getNextPlayer());
      PlayCommand skip = new SkipCommand(current.getNextPlayer());
      skip.execute();
    }
  }

  /**
   * The Draw7Command executes the ♣J special card by forcing the next player to add 7 cards
   * to their hand and skips their turn.
   */
  public class Draw7Command extends PlayCommand {
    public Draw7Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      view.printDrawCards(current.getNextPlayer().getPlayerName(), "♣J");
      model.drawCards(7, current.getNextPlayer());
      PlayCommand skip = new SkipCommand(current.getNextPlayer());
      skip.execute();
    }
  }

  /**
   * The ChangeSuit8Command class executes the change suit special card (8 of any suit)
   * to provide what suit they would like to change to. Then it asks the next player to play a
   * card that is in that suit.
   */
  public class ChangeSuit8Command extends PlayCommand {
    public ChangeSuit8Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      Scanner sc = new Scanner(rd);
      view.printPlayerChooseNewSuit(current.getPlayerName());
      String suit = sc.next();

      view.printTopCardInPile(model.getTopCardInPile(false));
      view.printPlayerHand(model.getHand(current.getNextPlayer()));
      view.printSuitChange(current.getNextPlayer().getPlayerName(), suit);
      model.hasValidPlays(current.getNextPlayer()); //checks it based on the top card in pile
      playCard(current.getNextPlayer());
    }
  }

  /**
   * The Reverse7Command class executes the reverse special card (7 of any suit) by changing
   * the order of the players' turns.
   */
  public class Reverse7Command extends PlayCommand {
    public Reverse7Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      view.printTopCardInPile(model.getTopCardInPile(false));
      view.printPlayerHand(model.getHand(current.getPrevPlayer()));
      view.printReverseDirection(current.getPrevPlayer().getPlayerName());
      model.hasValidPlays(current.getPrevPlayer());
      playCard(current.getPrevPlayer());

      model.getNextPlayer(current, true);
    }
  }
}
