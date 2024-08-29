package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * The following class CardDeck represents the card deck for the game. This card deck is based on
 * a standard deck of cards (52 cards), but depending on the number of players, will consist of
 * multiple decks for the sake of interesting gameplay.
 */
public class CardDeck {
  private List<Card> cards;
  private Queue<Card> drawPile;
  private int numDecks;
  private List<Card> specialCards;
  private Random rand;

  /**
   * The CardDeck constructor is an object that represents the cards in circulation for the current
   * game of Knock-Knock.
   * @param numDecks the given number of card decks that will be in play
   * @param rand the given Random seeded value for shuffling the cards in the deck
   */
  public CardDeck(Integer numDecks, Random rand) {
    this.cards = new ArrayList<Card>();
    this.drawPile = new LinkedList<Card>();
    this.numDecks = numDecks;
    this.specialCards = new ArrayList<Card>(Arrays.asList(
            new Card("♣", "J"),
            new Card("◆", "2"),
            new Card("◆", "8"),
            new Card("◆", "A"),
            new Card("◆", "7"),
            new Card("♣", "2"),
            new Card("♣", "8"),
            new Card("♣", "A"),
            new Card("♣", "7"),
            new Card("♥", "2"),
            new Card("♥", "8"),
            new Card("♥", "A"),
            new Card("♥", "7"),
            new Card("♠", "2"),
            new Card("♠", "8"),
            new Card("♠", "A"),
            new Card("♠", "7")
    ));
    this.rand = rand;
    generateDeck();
  }

  /**
   * The getTopCardInPile() retrieves the top card in the played pile.
   * @return a Card object
   */
  public Card getTopCardInPile() {
    return cards.get(cards.size() - 1);
  }

  /**
   * The drawCard() method returns the first card in the drawingPile queue
   * @return a Card object
   */
  public Card drawCard() {
    if (drawPile.size() == 1) {
      resetDeck();
    }
    return drawPile.poll();
  }

  /**
   * The addPlayedCard() method adds the given valid card played by the player and adds it to the
   * pile.
   * @param c the given card represented as a String
   */
  public void addPlayedCard(Card c) {
    cards.add(c);
  }

  /**
   * The checkPlay() method determines if the given card can be played based on the top card in
   * the pile.
   * @param card the given Card object
   * @return True if the given card is a valid play; False if otherwise
   */
  public boolean checkPlay (String card) {
    Card topCard = getTopCardInPile();
    String suit = convertSuit(card.charAt(0) + "");
    Card given = new Card(suit, card.charAt(1) + "");
    return topCard.getSuit().equals(given.getSuit()) || topCard.getValue().equals(given.getValue());
  }

  /**
   * The specialCard() method checks if the given Card is one of the following: 2, 8, 7, A, ♣J.
   * It returns the card's value if it is a special card, otherwise it returns an empty String.
   * @param c the given Card object
   * @return a String representation of the card's value
   */
  public String specialCard(Card c) {
    String value = "";
    for (Card card : specialCards) {
      if (c.getSuit().equals(card.getSuit()) && c.getValue().equals(card.getValue())) {
        value = c.getValue();
        break;
      }
    }
    return value;
  }

  /**
   * The generateStartingCard() method provides a valid starting card for the game.
   * @return a valid starting Card
   */
  public Card generateStartingCard() {
    if (validStartingCard(drawPile.peek())) {
      Card topCard = drawCard();
      addPlayedCard(topCard);
      return topCard;
    }
    else {
      addPlayedCard(drawCard());
      return generateStartingCard();
    }
  }

  /**
   * The validStartingCard() method checks if the given card is a valid starting card
   * for the game, meaning that it is not one of the special cards as dictated by the rules
   * @param c the given Card
   * @return True if it is a valid starting card; False if otherwise
   */
  private boolean validStartingCard(Card c) {
    for (Card comp : specialCards) {
      if (c.getSuit().equals(comp.getSuit()) && c.getValue().equals(comp.getValue())) {
        return false;
      }
    }
    return true;
  }

  /**
   * The generateDeck() method is used to create a deck of cards containing all valid card
   * possibilities (by suit and value) as well as the correct number of duplicates if multiple
   * decks are in play. After, the cards are shuffled and added to a queue.
   */
  public void generateDeck() {
    int num = 0;
    while (num < numDecks) {
      populate();
      num++;
    }
    resetDeck();
  }

  /**
   * The resetDeck() method is used to shuffle the collected pile (list) of cards and add them
   * to the draw pile.
   */
  private void resetDeck() {
    Collections.shuffle(cards, rand);
    setUpDrawPile();
    cards.clear();
  }

  /**
   * The setUpDrawPile() method adds the shuffled list of cards into the drawPile queue.
   */
  private void setUpDrawPile() {
    for (Card c : cards) {
      drawPile.offer(c);
    }
  }

  /**
   * The convertSuit() converts the given String representation of a suit (D, C, H, S) to
   * (◆ ♣ ♥ ♠)
   * @param s the given suit represented as a String
   * @return the converted suit to the String
   */
  public String convertSuit(String s) {
    if (s.equals("D")) {
      return "◆";
    }
    else if (s.equals("C")) {
      return "♣";
    }
    else if (s.equals("H")) {
      return "♥";
    }
    else if (s.equals("S")) {
      return "♠";
    }
    else {
      return s;
    }
  }

  /**
   * The populate() method populates the drawPile with all valid cards in a deck as well as its
   * duplicates based on the number of players for the game.
   */
  private void populate() {
    cards.add(new Card("◆", "A"));
    cards.add(new Card("◆", "2"));
    cards.add(new Card("◆", "3"));
    cards.add(new Card("◆", "4"));
    cards.add(new Card("◆", "5"));
    cards.add(new Card("◆", "6"));
    cards.add(new Card("◆", "7"));
    cards.add(new Card("◆", "8"));
    cards.add(new Card("◆", "9"));
    cards.add(new Card("◆", "10"));
    cards.add(new Card("◆", "J"));
    cards.add(new Card("◆", "Q"));
    cards.add(new Card("◆", "K"));

    cards.add(new Card("♣", "A"));
    cards.add(new Card("♣", "2"));
    cards.add(new Card("♣", "3"));
    cards.add(new Card("♣", "4"));
    cards.add(new Card("♣", "5"));
    cards.add(new Card("♣", "6"));
    cards.add(new Card("♣", "7"));
    cards.add(new Card("♣", "8"));
    cards.add(new Card("♣", "9"));
    cards.add(new Card("♣", "10"));
    cards.add(new Card("♣", "J"));
    cards.add(new Card("♣", "Q"));
    cards.add(new Card("♣", "K"));

    cards.add(new Card("♥", "A"));
    cards.add(new Card("♥", "2"));
    cards.add(new Card("♥", "3"));
    cards.add(new Card("♥", "4"));
    cards.add(new Card("♥", "5"));
    cards.add(new Card("♥", "6"));
    cards.add(new Card("♥", "7"));
    cards.add(new Card("♥", "8"));
    cards.add(new Card("♥", "9"));
    cards.add(new Card("♥", "10"));
    cards.add(new Card("♥", "J"));
    cards.add(new Card("♥", "Q"));
    cards.add(new Card("♥", "K"));

    cards.add(new Card("♠", "A"));
    cards.add(new Card("♠", "2"));
    cards.add(new Card("♠", "3"));
    cards.add(new Card("♠", "4"));
    cards.add(new Card("♠", "5"));
    cards.add(new Card("♠", "6"));
    cards.add(new Card("♠", "7"));
    cards.add(new Card("♠", "8"));
    cards.add(new Card("♠", "9"));
    cards.add(new Card("♠", "10"));
    cards.add(new Card("♠", "J"));
    cards.add(new Card("♠", "Q"));
    cards.add(new Card("♠", "K"));
  }
}
