package Mocks;

import java.io.IOException;
import java.util.List;

import Model.Card;
import Model.GameModel;
import Model.Player;

/**
 *
 */
public class ModelMock implements GameModel {
  private Appendable log;

  /**
   *
   * @param ap
   */
  ModelMock(Appendable ap) {
    this.log = ap;
  }

  /**
   * The following method appends the command called by the controller to the log.
   * @param result given string to add to the log of called commands.
   */
  public void appendResult(String result) {
    try {
      log.append(result);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public Player getCurrentPlayer() {
    return null;
  }

  @Override
  public void createPrivateRoom() {

  }

  @Override
  public void changeDisplayMode() {

  }

  @Override
  public void modifyRule(Card card, String rule) {

  }

  @Override
  public boolean canModifyRules() {
    return false;
  }

  @Override
  public void setUpOrder() {

  }

  @Override
  public Card getTopCardInPile(boolean startingCard) {
    return null;
  }

  @Override
  public void generateHands() {

  }

  @Override
  public GameModel updateNextPlayer(Player current, boolean reverse) {
    return null;
  }

  @Override
  public List<Card> getHand(Player p) {
    return List.of();
  }

  @Override
  public boolean hasValidPlays(Player p) {
    return false;
  }

  @Override
  public void resetValidPlays(Player p) {

  }

  @Override
  public String checkSpecialCard(Card c) {
    return "";
  }

  @Override
  public void updateHand(String card, Player p) {

  }

  @Override
  public void drawCards(int numCards, Player p) {

  }

  @Override
  public boolean canPlayCard(String card) {
    return false;
  }
}
