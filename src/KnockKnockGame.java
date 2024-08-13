import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.GameModel;
import Model.Model;
import Model.Card;
import Model.Player;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

  }
}