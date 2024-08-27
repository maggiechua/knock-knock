package Model;

import java.util.LinkedList;
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
  private LinkedList<Player> order;

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
    this.order = new LinkedList<Player>();
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
  public void setUpOrder() {
    int numPlayers = players.size();
    for (int i = 0; i < numPlayers; i++) {
      Player current = players.get(i);
      Player next = null;
      Player prev = null;
      try {
        next = players.get(i + 1);
      }
      catch (Exception e) {
        next = players.get(0);
      }
      try {
        prev = players.get(i - 1);
      }
      catch (Exception e) {
        prev = players.get(players.size() - 1);
      }
      order.add(current);
      current.setNextPlayer(next);
      current.setPrevPlayer(prev);
    }
  }

  @Override
  public Card getTopCardInPile(boolean startingCard) {
    if (startingCard) {
      return cardDeck.generateStartingCard();
    }
    else {
      return cardDeck.getTopCardInPile();
    }
  }

  @Override
  public void generateHands() {
    for (Player p : players) {
      p.generateHand();
    }
  }

  @Override
  public Player getNextPlayer(Player current, boolean reverse) {
    if (current == null) {
      return order.getFirst();
    }
    else if (reverse) {
      return current.getPrevPlayer();
    }
    else {
      return current.getNextPlayer();
    }
  }

  @Override
  public boolean hasValidPlays(Player p) {
    return p.containsValidPlays();
  }

  @Override
  public void resetValidPlays(Player p) {
    p.resetPlays();
  }

  @Override
  public String checkSpecialCard(Card c) {
    System.out.println(c.getSuit() + " " + c.getValue());
    return cardDeck.specialCard(c);
    // need to convert the card in order for it work
  }

  @Override
  public List<Card> getHand(Player p) {
    return p.getPlayerHand();
  }

  @Override
  public void updateHand(String card, Player p) {
    p.removeCard(card);
  }

  @Override
  public void drawCards(int numCards, Player p) {
    p.addCard(numCards);
  }

  @Override
  public boolean canPlayCard(String card) {
    return cardDeck.checkPlay(card);
  }
}
