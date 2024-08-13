package Model;

import java.util.List;
import java.util.Map;


public class Model implements GameModel {
  List<Player> players;
  Map<Card, String> rules;

  public Model(List<Player> players, Map<Card, String> rules) {
    this.players = players;
    this.rules = rules;
    this.generateHands();
  }

  @Override
  public void createPrivateRoom() {

  }

  @Override
  public void changeDisplayMode() {

  }

  @Override
  public void modifyRule(Card card, String rule) {
    if (rules.containsKey(card)) {
      rules.put(card, rule);
    }
  }

  @Override
  public boolean canModifyRules() {
    return false;
  }

  @Override
  public void generateHands() {
    for (Player p : players) {
      p.generateHand();
    }
  }

  @Override
  public void updateHand(String card) {

  }

  @Override
  public boolean canPlayCard(String card) {
    return false;
  }
}
