package Model;

import java.util.List;

/**
 * The Player class represents a player in the Knock-Knock Game, consisting of information on their
 * username and current hand.
 */
public class Player {
  private String name;
  private List<Card> hand;
  private CardDeck deck;

  /**
   * The Player constructor creates a Player object consisting of the user's username and current
   * hand in the game.
   * @param name a String representation of their username
   * @param hand a List of Cards representing their hand in the game
   * @param deck a CardDeck object representing all available cards in the game
   */
  public Player(String name, List<Card> hand, CardDeck deck) {
    this.name = name;
    this.hand = hand;
    this.deck = deck;
  }

  public List<Card> getPlayerHand() {
    return this.hand;
  }

  /**
   * The generateHand() method is called at the beginning of the game and generates 7 cards
   * from the deck.
   */
  public void generateHand() {
    int numCards = 0;
    while (numCards < 7) {
      hand.add(deck.drawCard());
      numCards++;
    }
  }

  /**
   * The addCard() method adds the given Card object to the player's hand.
   * @param c the given Card
   */
  public void addCard(Card c) {
    hand.add(c);
  }

  /**
   * The removeCard() method removes the given Card object from the player's hand.
   * @param c the given Card
   */
  public void removeCard(Card c) {
    hand.remove(c);
  }
}
