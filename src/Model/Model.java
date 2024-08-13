package Model;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The following class represents the Model of the Knock Knock Game, consisting of the current
 * list of players in the game and the rules for the round.
 */
public class Model implements GameModel {
  private List<Player> players;
  private Map<Card, String> rules;

  public Model(List<Player> players, Map<Card, String> rules) {
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
  public Card generateStartingCard() {
    return null;
  }

  @Override
  public void generateHands(Random rand) {
    for (Player p : players) {
      p.generateHand(rand);
    }
  }

  @Override
  public List<Card> getHand() {
    return players.get(0).getPlayerHand();
  }

  @Override
  public void updateHand(String card) {

  }

  @Override
  public boolean canPlayCard(String card) {
    return false;
  }
}
