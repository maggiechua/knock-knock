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
    // - player has valid cards they can play -> ask them to select and add their card to pile []
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
   * @param p
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
   *
   * @param value
   * @param p
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

  public class SkipCommand extends PlayCommand {
    public SkipCommand(Player current) {
      super(current);
    }

    @Override
    void execute() {
      model.getNextPlayer(current.getNextPlayer(), false);
    }
  }

  public class Draw2Command extends PlayCommand {
    public Draw2Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      model.drawCards(2, current.getNextPlayer());
      PlayCommand skip = new SkipCommand(current.getNextPlayer());
      skip.execute();
    }
  }

  public class Draw7Command extends PlayCommand {
    public Draw7Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      model.drawCards(7, current.getNextPlayer());
      PlayCommand skip = new SkipCommand(current.getNextPlayer());
      skip.execute();
    }
  }

  public class ChangeSuit8Command extends PlayCommand {
    public ChangeSuit8Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      view.printPlayerChooseNewSuit(current.getPlayerName());
      // retrieve suit and change what cards next player can play accordingly
    }
  }

  public class Reverse7Command extends PlayCommand {
    public Reverse7Command(Player current) {
      super(current);
    }

    @Override
    void execute() {
      model.getNextPlayer(current, true);
    }
  }
}
