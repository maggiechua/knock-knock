package Model;

/**
 * The following class Card represents a single playing card in a deck of cards, where each object
 * consists of a suit and value.
 */
public class Card {
  private final String suit;
  private final String value;
  private boolean validPlay;

  /**
   * The Card constructor consists of a suit (Diamond, Club, Heart, Spade) and its value (1 to 10
   * and face cards)
   * @param suit the card suit represented as a String (D, C, H, S)
   * @param value the value represented as a String (2-10, J, Q, K, A)
   */
  public Card(String suit, String value) {
    this.suit = suit;
    this.value = value;
    this.validPlay = false;
  }

  public String getSuit() {
    return this.suit;
  }

  public String getValue() {
    return this.value;
  }

  public boolean getValidPlay() { return this.validPlay; }

  public void makeValidPlay() { this.validPlay = true; }

  public void resetValidPlay() { this.validPlay = false; }
}
