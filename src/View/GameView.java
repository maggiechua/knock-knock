package View;

import java.util.List;

import Model.Card;

public interface GameView {
  /**
   * The welcomeMessage() method is used to display a welcome message to the user when
   * they run the program to play Knock-Knock.
   */
  void welcomeMessage();

  /**
   * The printBaseRules() method is used to display the base game rules for Knock-Knock.
   */
  void printBaseRules();

  /**
   * The printTopCardInPile() method is used to display the top card in the pile following each
   * player's turn.
   * @param c the given card to display
   */
  void printTopCardInPile(Card c);

  /**
   * The printPlayerHand() method is used to display the user's cards.
   * @param hand the cards in the user's hand represented as a List
   */
  void printPlayerHand(List<Card> hand);
}
