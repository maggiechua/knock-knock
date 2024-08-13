package Model;

public interface GameModel {

  void createPrivateRoom();

  void changeDisplayMode();

  void modifyRule(Card card, String rule);

  boolean canModifyRules();

  void generateHands();

  void updateHand(String card);

  boolean canPlayCard(String card);

}
