package Model;

import java.util.List;
import java.util.Map;

/**
 * The following class represents the Model of the Knock-Knock Game, consisting of the current
 * list of players in the game and the rules for the round.
 */
public class Model implements GameModel {
  private List<Player> players;
  private Map<Card, String> rules;
  private CardDeck cardDeck;

  /**
   * The Model constructor takes in a list of players, a map representing a card and its
   * corresponding rules, and a CardDeck object which represents the cards in play.
   * @param players a List representation of players in the game
   * @param rules a Map representation of the rules depending on the card
   * @param cardDeck a CardDeck object representing cards in play
   */
  public Model(List<Player> players, Map<Card, String> rules, CardDeck cardDeck) {
    this.players = players;
    this.rules = rules;
    this.cardDeck = cardDeck;
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
    return cardDeck.generateStartingCard();
  }

  @Override
  public void generateHands() {
    for (Player p : players) {
      p.generateHand();
    }
  }

  @Override
  public List<Card> getHand(int player) {
    return players.get(player - 1).getPlayerHand();
  }

  @Override
  public void updateHand(String card, int player) {

//    cardDeck.addPlayedCard(card);
    players.get(player - 1).removeCard(card);
  }

  @Override
  public boolean canPlayCard(String card) {
    return cardDeck.checkPlay(card);
  }
}
