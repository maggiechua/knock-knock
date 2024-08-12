package Model;

public interface GameModel {

  void createPrivateRoom();

  void changeDisplayMode();

  void modifyRule(String card, String rule);

  boolean canModifyRules();

  void generateHand();

  void updateHand(String card);

  boolean canPlayCard(String card);

}
