package Model;

import java.util.List;
import java.util.Random;

public class Player {
  private String name;
  private List<Card> hand;
  private CardDeck deck;
  private Random rand;

  public Player(String name, List<Card> hand) {
    this.name = name;
    this.hand = hand;
    this.deck = new CardDeck(2);
    this.rand = new Random();
  }

  public void generateHand() {
    int numCards = 0;
    while (numCards < 8) {
      hand.add(deck.generateCard(rand.nextInt(53)));
      numCards++;
    }
  }
}
