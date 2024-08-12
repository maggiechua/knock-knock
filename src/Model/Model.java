package Model;

import java.util.List;
import java.util.Map;


public class Model implements GameModel {
  Map<Player, List<Card>> players;
  Map<Card, String> rules;

  public Model(Map<Player, List<Card>> players, Map<Card, String> rules) {
    this.players = players;
    this.rules = rules;
  }

  @Override
  public void createPrivateRoom() {

  }

  @Override
  public void changeDisplayMode() {

  }

  @Override
  public void modifyRule(String card, String rule) {

  }

  @Override
  public boolean canModifyRules() {
    return false;
  }

  @Override
  public void generateHand() {

  }

  @Override
  public void updateHand(String card) {

  }

  @Override
  public boolean canPlayCard(String card) {
    return false;
  }
}
