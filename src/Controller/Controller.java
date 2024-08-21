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
    basicGameplay(false, 0);
  }

  /**
   * The basicGameplay() method is used to start the game and update it as players make their
   * moves until only one person is left with cards in their hand.
   * @param endGame a Boolean representation of whether the game has ended
   * @param currentPlayer an Integer representation of which player's turn it is
   */
  private void basicGameplay(boolean endGame, int currentPlayer) {
    while (!endGame) {
      if (currentPlayer == 0) {
        model.getTopCardInPile(true);
        currentPlayer++;
      }
//      else if (!model.hasValidPlays(currentPlayer)) {
//        model.drawCards(1, currentPlayer);
//        view.printPlayerHand(model.getHand(currentPlayer));
//        view.printNoValidPlays(currentPlayer);
//      }
      else {
        // must reset valid plays after a card is chosen
        view.printTopCardInPile(model.getTopCardInPile(false));
        view.printPlayerHand(model.getHand(currentPlayer));
        view.printPlayerTurn(Integer.toString(currentPlayer));
        playCard(Integer.toString(currentPlayer));
      }
      if (currentPlayer == 4) {
        currentPlayer = 1;
      }
      else {
        currentPlayer++;
      }
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
}
