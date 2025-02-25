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
  private Player next;
  private Player prev;

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

  public String getPlayerName() {
    return this.name;
  }

  /**
   * The getPlayerHand() method determines which cards on the player's turn can be
   * played, marks them, and returns them.
   * @return the player's hand as a List representation
   */
  public List<Card> getPlayerHand() {
    markValidPlays();
    return this.hand;
  }

  public Player getNextPlayer() {
    return this.next;
  }

  public Player getPrevPlayer() {
    return this.prev;
  }

  /**
   *
   * @param p
   */
  public void setNextPlayer(Player p) {
    this.next = p;
  }

  /**
   *
   * @param p
   */
  public void setPrevPlayer(Player p) {
    this.prev = p;
  }

  /**
   * The containsValidPlays() method determines if the player's hand contains valid plays.
   * @return True if there is at least one valid play; False otherwise
   */
  public boolean containsValidPlays() {
    boolean valid = false;
    markValidPlays();
    for (Card c : hand) {
      if (c.getValidPlay()) {
        valid = true;
        break;
      }
    }
    return valid;
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
   * The markValidPlays() method modifies the cards in a hand to determine if a card can be played
   * for the next turn.
   */
  public void markValidPlays() {
    for (Card c : hand) {
      if (deck.checkPlay(c.getSuit() + c.getValue())) {
        c.makeValidPlay();
      }
    }
  }

  /**
   * The resetPlays() method mutates the validPlay variable and resets it back to False
   * once a player has chosen a valid card to play.
   */
  public void resetPlays() {
    for (Card c : hand) {
      c.resetValidPlay();
    }
  }

  /**
   * The addCard() method adds a given number of cards to the player's hand from the card deck.
   * @param numCards an Integer representation of the number of cards to add to the deck
   */
  public void addCard(int numCards) {
    for (int i = 1; i <= numCards; i++) {
      hand.add(deck.drawCard());
    }
  }

  /**
   * The removeCard() method removes the given Card object from the player's hand.
   * @param c the given card represented as a String
   */
  public void removeCard(String c) {
    Card convertCard = convertToCard(c);
    deck.addPlayedCard(convertCard);
    hand.remove(convertCard);
  }

  /**
   * The convertToCard() method finds the String representation of the given card in the player's
   * hand and returns it.
   * @param card the given String representation of the card
   * @return a Card object
   */
  private Card convertToCard(String card) {
    Card matchingCard = null;
    for (Card c : hand) {
      if (c.getSuit().equals(deck.convertSuit(card.charAt(0) + ""))
        && c.getValue().equals(card.substring(1))) {
          matchingCard = c;
      }
    }
    return matchingCard;
  }
}
