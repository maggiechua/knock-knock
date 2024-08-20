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
    int currentPlayer = 0;
    boolean endGame = false;
    Scanner sc = new Scanner(rd);
    view.welcomeMessage();
    model.generateHands();

    // Basic Gameplay - Display Hand, Play Card, Valid -> Continue | Invalid -> Ask Again
    // Special Feature - makes bold the cards that the player can play
    while (!endGame) {
      if (currentPlayer == 0) {
        view.printTopCardInPile(model.generateStartingCard());
        currentPlayer++;
      }
      else {
        view.printPlayerHand(model.getHand(currentPlayer));
        view.printPlayerTurn();
        String nextPlay = sc.next();
        model.canPlayCard(nextPlay);

        if (currentPlayer == 4) {
          currentPlayer = 1;
        }
        else {
          currentPlayer++;
        }
      }
    }


  }
}
