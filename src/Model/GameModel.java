package Model;

/**
 * The following interface represents the Model for the Knock Knock Game, allowing for users
 * to customize their gameplay while also monitoring the existing game.
 */
public interface GameModel {

  void createPrivateRoom();

  void changeDisplayMode();

  void modifyRule(Card card, String rule);

  boolean canModifyRules();

  /**
   * The generateHands() method is called to provide each players a random set of 7 cards in the
   * card deck.
   */
  void generateHands();

  void updateHand(String card);

  boolean canPlayCard(String card);

}
