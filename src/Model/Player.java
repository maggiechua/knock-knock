package Model;

import java.util.List;
import java.util.Random;

/**
 * The Player class represents a player in the Knock Knock Game, consisting of information on their
 * username and current hand.
 */
public class Player {
  private String name;
  private List<Card> hand;
  private CardDeck deck;
  private Random rand;

  /**
   * The Player constructor creates a Player object consisting of the user's username and current
   * hand in the game.
   * @param name a String representation of their username
   * @param hand a List of Cards representing their hand in the game
   */
  public Player(String name, List<Card> hand) {
    this.name = name;
    this.hand = hand;
    this.deck = new CardDeck(2);
    this.rand = new Random();
  }

  /**
   * The generateHand() method is called at the beginning of the game and generates 7 cards
   * from the deck.
   */
  public void generateHand() {
    int numCards = 0;
    while (numCards < 8) {
      hand.add(deck.generateCard(rand.nextInt(53)));
      numCards++;
    }
  }
}
