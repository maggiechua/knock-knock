package Model;

import java.util.List;
import java.util.Random;

/**
 * The following interface represents the Model for the Knock-Knock Game, allowing for users
 * to customize their gameplay while also monitoring the existing game.
 */
public interface GameModel {

  void createPrivateRoom();

  void changeDisplayMode();

  void modifyRule(Card card, String rule);

  boolean canModifyRules();

  /**
   * The getTopCardInPile() method is used to retrieve the top card in the middle pile where
   * all cards are played.
   * @param startingCard a boolean representation that represents whether a starting card needs
   *                     to be generated
   * @return a Card object
   */
  Card getTopCardInPile(boolean startingCard);

  /**
   * The generateHands() method is called to provide each player's a random set of 7 cards in the
   * card deck.
   */
  void generateHands();

  /**
   * The getHand() method is used to retrieve a given player's hand, displaying all the cards
   * they are currently holding and which ones are valid moves.
   * @param player an Integer representation of the given player
   * @return a List representation of all Card(s) in a player's hand
   */
  List<Card> getHand(int player);

  /**
   *
   * @param player
   * @return
   */
  boolean hasValidPlays(int player);

  /**
   * The updateHand() method is used to update a given player's hand when they play a card on
   * their turn or have to draw a card.
   * @param card
   * @param player an Integer representation of the given player
   */
  void updateHand(String card, int player);

  /**
   *
   * @param numCards
   * @param player an Integer representation of the given player
   */
  void drawCards(int numCards, int player);

  /**
   * The canPlayCard() method is used to determine if a given card can be played based on the
   * top card in the pile.
   * @param card the given card represented as a String
   * @return True if the card can be played; otherwise False
   */
  boolean canPlayCard(String card);
}
