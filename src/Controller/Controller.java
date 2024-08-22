package Controller;

import java.io.InputStreamReader;
import java.util.Scanner;

import Model.GameModel;
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
    basicGameplay(false, 1);
  }

  /**
   * The basicGameplay() method is used to start the game and update it as players make their
   * moves until only one person is left with cards in their hand.
   * @param endGame a Boolean representation of whether the game has ended
   * @param currentPlayer an Integer representation of which player's turn it is
   */
  private void basicGameplay(boolean endGame, int currentPlayer) {
    // Cases:
    // - game just started -> determine starting card [X]
    // - player has no valid cards they can play -> draw card + move to next player [X]
    // - player has valid cards they can play -> ask them to select and add their card to pile
    //   - normal card -> add to pile and move to next player [X]
    //   - only have two remaining cards -> check if they 'say'
    //   'Knock-Knock' (if yes, remind other players; no, force player to draw another card)
    //   - place a special card (A, 2) -> must skip next player accordingly and force them to draw
    //   the required number of cards
    //   - place a special card (7) -> change direction of game, so player's turns go other way
    //   - place a special card (8) -> ask player what suit they would like to change game to
    // - player has played their last card -> depending on game, once first person runs out, they
    //   automatically win; otherwise, keep playing until only one person left with cards
    model.getTopCardInPile(true);
    while (!endGame) {
      view.printTopCardInPile(model.getTopCardInPile(false));
//      view.printPlayerHand(model.getHand(currentPlayer));
      if (!model.hasValidPlays(currentPlayer)) {
        model.drawCards(1, currentPlayer);
        view.printNoValidPlays(currentPlayer);
        view.printPlayerHand(model.getHand(currentPlayer));
      }
      else {
        view.printPlayerHand(model.getHand(currentPlayer));
        view.printPlayerTurn(Integer.toString(currentPlayer));
        playCard(Integer.toString(currentPlayer));
        model.resetValidPlays(currentPlayer);
      }
      // check special cards
      String value = model.checkSpecialCard(model.getTopCardInPile(false)); // A J 2 | 7 8
      // probably will have to use deques for this to work??
      if (currentPlayer == 2) {
        currentPlayer = 1;
      }
      else {
        currentPlayer++;
      }
      specialCardPlayed(value, currentPlayer);
    }
  }

  /**
   * The playCard() method is used during a player's turn where a player selects a card in their
   * hand to play and the request is granted it valid, otherwise, they are prompted to choose a
   * different valid card, or forced to draw a card.
   * @param player representation of the current player's turn as a String
   */
  private void playCard(String player) {
    Scanner sc = new Scanner(rd);
    String nextPlay = sc.next();
    if (model.canPlayCard(nextPlay)) {
      model.updateHand(nextPlay, Integer.parseInt(player));
    }
    else {
      view.printInvalidPlay();
      playCard(player);
    }
  }

  /**
   *
   * @param value
   * @param player
   */
  private void specialCardPlayed(String value, int player) {
    if (value.equals("J")) {
      model.drawCards(7, player);
    }
    else if (value.equals("2")) {
      model.drawCards(2, player);
    }
    else if (value.equals("8")) {
      // ask for suit
    }
    else if (value.equals("7")) {
      // reverse direction
    }
  }

  // use deques for updating players instead? so things are linked??
  // implement command-design pattern??
//  static class SkipCardCommand implements PlayCommand {
//    @Override
//    public void execute() {
//
//    }
//  }
}
