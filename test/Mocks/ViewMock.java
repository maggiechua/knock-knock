package Mocks;

import java.util.List;

import Model.Card;
import View.GameView;

public class ViewMock implements GameView {
  @Override
  public void welcomeMessage() {

  }

  @Override
  public void printBaseRules() {

  }

  @Override
  public void printTopCardInPile(Card c) {

  }

  @Override
  public void printPlayerHand(List<Card> hand) {

  }

  @Override
  public void printPlayerTurn(String player) {

  }

  @Override
  public void printInvalidPlay() {

  }

  @Override
  public void printNoValidPlays(String player) {

  }

  @Override
  public void printDrawCards(String player, String sc) {

  }

  @Override
  public void printReverseDirection(String player) {

  }

  @Override
  public void printPlayerChooseNewSuit(String player) {

  }

  @Override
  public void printSuitChange(String player, String suit) {

  }
}
