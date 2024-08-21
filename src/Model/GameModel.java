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

  Card generateStartingCard();

  /**
   * The generateHands() method is called to provide each player's a random set of 7 cards in the
   * card deck.
   */
  void generateHands();

  List<Card> getHand(int player);

  void updateHand(String card, int player);

  boolean canPlayCard(String card);

}
