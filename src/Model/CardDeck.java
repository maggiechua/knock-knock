package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * The following class CardDeck represents the card deck for the game. This card deck is based on
 * a standard deck of cards (52 cards), but depending on the number of players, will consist of
 * multiple decks for the sake of interesting gameplay.
 */
public class CardDeck {
  private Integer capacity;
  private Integer front;
  private Integer rear;
  private Integer size;
  private Card drawingPile[];
  private Map<Integer, Card> standardDeck;

  public CardDeck(Integer numDecks) {
    this.capacity = numDecks * 52;
    this.front = 0;
    this.rear = capacity - 1;
    this.size = 0;
    this.drawingPile = new Card[this.capacity];
    this.standardDeck = new HashMap<>();
    this.populate();
  }

  /**
   * The generateCard() method returns the corresponding card that the given integer is associated
   * with.
   * @param val the card to retrieve represented as an Integer
   * @return a Card object
   */
  public Card generateCard(Integer val) {
    // checking to see if we exceeded the valid number of cards available in the deck if the
    // same number is provided??
    return standardDeck.get(val);
  }

  public void updateCirculation() {

  }

  public void enqueue(Card c) {
    this.rear = this.rear + 1;
    this.drawingPile[this.rear] = c;
    this.size += 1;
  }

  public void dequeue() {

  }

  /**
   * The populate() method populates the standardDeck Map where each card is associated with an
   * Integer value for the purposes of card generation.
   */
  private void populate() {
    standardDeck.put(1, new Card("D", "A"));
    standardDeck.put(2, new Card("D", "2"));
    standardDeck.put(3, new Card("D", "3"));
    standardDeck.put(4, new Card("D", "4"));
    standardDeck.put(5, new Card("D", "5"));
    standardDeck.put(6, new Card("D", "6"));
    standardDeck.put(7, new Card("D", "7"));
    standardDeck.put(8, new Card("D", "8"));
    standardDeck.put(9, new Card("D", "9"));
    standardDeck.put(10, new Card("D", "10"));
    standardDeck.put(11, new Card("D", "J"));
    standardDeck.put(12, new Card("D", "Q"));
    standardDeck.put(13, new Card("D", "K"));

    standardDeck.put(14, new Card("C", "A"));
    standardDeck.put(15, new Card("C", "2"));
    standardDeck.put(16, new Card("C", "3"));
    standardDeck.put(17, new Card("C", "4"));
    standardDeck.put(18, new Card("C", "5"));
    standardDeck.put(19, new Card("C", "6"));
    standardDeck.put(20, new Card("C", "7"));
    standardDeck.put(21, new Card("C", "8"));
    standardDeck.put(22, new Card("C", "9"));
    standardDeck.put(23, new Card("C", "10"));
    standardDeck.put(24, new Card("C", "J"));
    standardDeck.put(25, new Card("C", "Q"));
    standardDeck.put(26, new Card("C", "K"));

    standardDeck.put(27, new Card("H", "A"));
    standardDeck.put(28, new Card("H", "2"));
    standardDeck.put(29, new Card("H", "3"));
    standardDeck.put(30, new Card("H", "4"));
    standardDeck.put(31, new Card("H", "5"));
    standardDeck.put(32, new Card("H", "6"));
    standardDeck.put(33, new Card("H", "7"));
    standardDeck.put(34, new Card("H", "8"));
    standardDeck.put(35, new Card("H", "9"));
    standardDeck.put(36, new Card("H", "10"));
    standardDeck.put(37, new Card("H", "J"));
    standardDeck.put(38, new Card("H", "Q"));
    standardDeck.put(39, new Card("H", "K"));

    standardDeck.put(40, new Card("S", "A"));
    standardDeck.put(41, new Card("S", "2"));
    standardDeck.put(42, new Card("S", "3"));
    standardDeck.put(43, new Card("S", "4"));
    standardDeck.put(44, new Card("S", "5"));
    standardDeck.put(45, new Card("S", "6"));
    standardDeck.put(46, new Card("S", "7"));
    standardDeck.put(47, new Card("S", "8"));
    standardDeck.put(48, new Card("S", "9"));
    standardDeck.put(49, new Card("S", "10"));
    standardDeck.put(50, new Card("S", "J"));
    standardDeck.put(51, new Card("S", "Q"));
    standardDeck.put(52, new Card("S", "K"));
  }
}
