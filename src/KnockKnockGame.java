import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.Controller;
import Controller.GameController;
import Model.GameModel;
import Model.Model;
import Model.Card;
import Model.Player;
import View.GameView;
import View.View;

/**
 * This is the main method of the Knock-Knock Game so that it can be run for
 * entertainment. The following represents the base game representation which consists of
 * four players, base rules, and a text-based version of the game.
 */
public class KnockKnockGame {
  public static void main(String[] args) {
    Map<Card, String> baseRules = new HashMap<>();
    baseRules.put(new Card("clubs", "J"), "draw 7 cards");
    baseRules.put(new Card("any", "2"), "draw 2 cards");
    baseRules.put(new Card("any", "8"), "wild card - can switch suit as long as " +
            "played on top of a card of the same suit (i.e. if last card was Hearts, can only put " +
            "8 of hearts)");
    baseRules.put(new Card("any", "A"), "skip the next player's turn");
    baseRules.put(new Card("any", "7"), "reverse direction");
    List<Player> players = new ArrayList<>(Arrays.asList(
            new Player("Player 1", new ArrayList<Card>()),
            new Player("Player 2", new ArrayList<Card>()),
            new Player("Player 3", new ArrayList<Card>()),
            new Player("Player 4", new ArrayList<Card>())
    ));

    GameModel model = new Model(players, baseRules);
    GameView view = new View(System.out);
    GameController controller = new Controller(model, view);
    controller.execute();
  }
}