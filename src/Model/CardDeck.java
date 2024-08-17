package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
  private List<Card> invalidStartingCards;

  /**
   * The CardDeck constructor is an object that represents the cards in circulation for the current
   * game of Knock-Knock.
   * @param numDecks the given number of card decks that will be in play
   */
  public CardDeck(Integer numDecks) {
    this.cards = new ArrayList<Card>();
    this.drawPile = new LinkedList<Card>();
    this.numDecks = numDecks;
    this.invalidStartingCards = new ArrayList<Card>(Arrays.asList(
            new Card("C", "J"),
            new Card("D", "2"),
            new Card("D", "8"),
            new Card("D", "A"),
            new Card("D", "7"),
            new Card("C", "2"),
            new Card("C", "8"),
            new Card("C", "A"),
            new Card("C", "7"),
            new Card("H", "2"),
            new Card("H", "8"),
            new Card("H", "A"),
            new Card("H", "7"),
            new Card("S", "2"),
            new Card("S", "8"),
            new Card("S", "A"),
            new Card("S", "7")
    ));
    generateDeck();
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
   * @param c the given Card object
   */
  public void addPlayedCard(Card c) {
    cards.add(c);
  }

  /**
   * The generateStartingCard() method provides a valid starting card for the game.
   * @return a valid starting Card
   */
  public Card generateStartingCard() {
    if (validStartingCard(drawPile.peek())) {
      return drawCard();
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
    if (invalidStartingCards.contains(c)) {
      return false;
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
    Collections.shuffle(cards, new Random());
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
   * The populate() method populates the drawPile with all valid cards in a deck as well as its
   * duplicates based on the number of players for the game.
   */
  private void populate() {
    cards.add(new Card("D", "A"));
    cards.add(new Card("D", "2"));
    cards.add(new Card("D", "3"));
    cards.add(new Card("D", "4"));
    cards.add(new Card("D", "5"));
    cards.add(new Card("D", "6"));
    cards.add(new Card("D", "7"));
    cards.add(new Card("D", "8"));
    cards.add(new Card("D", "9"));
    cards.add(new Card("D", "10"));
    cards.add(new Card("D", "J"));
    cards.add(new Card("D", "Q"));
    cards.add(new Card("D", "K"));

    cards.add(new Card("C", "A"));
    cards.add(new Card("C", "2"));
    cards.add(new Card("C", "3"));
    cards.add(new Card("C", "4"));
    cards.add(new Card("C", "5"));
    cards.add(new Card("C", "6"));
    cards.add(new Card("C", "7"));
    cards.add(new Card("C", "8"));
    cards.add(new Card("C", "9"));
    cards.add(new Card("C", "10"));
    cards.add(new Card("C", "J"));
    cards.add(new Card("C", "Q"));
    cards.add(new Card("C", "K"));

    cards.add(new Card("H", "A"));
    cards.add(new Card("H", "2"));
    cards.add(new Card("H", "3"));
    cards.add(new Card("H", "4"));
    cards.add(new Card("H", "5"));
    cards.add(new Card("H", "6"));
    cards.add(new Card("H", "7"));
    cards.add(new Card("H", "8"));
    cards.add(new Card("H", "9"));
    cards.add(new Card("H", "10"));
    cards.add(new Card("H", "J"));
    cards.add(new Card("H", "Q"));
    cards.add(new Card("H", "K"));

    cards.add(new Card("S", "A"));
    cards.add(new Card("S", "2"));
    cards.add(new Card("S", "3"));
    cards.add(new Card("S", "4"));
    cards.add(new Card("S", "5"));
    cards.add(new Card("S", "6"));
    cards.add(new Card("S", "7"));
    cards.add(new Card("S", "8"));
    cards.add(new Card("S", "9"));
    cards.add(new Card("S", "10"));
    cards.add(new Card("S", "J"));
    cards.add(new Card("S", "Q"));
    cards.add(new Card("S", "K"));
  }
}
