package View;

import java.util.List;

import Model.Card;

/**
 *
 */
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

  /**
   * The printPlayerTurn() method is used to print the prompt message for the next player's
   * turn.
   */
  void printPlayerTurn(String player);

  /**
   * The printInvalidPlay() method is used to print a message informing the player that they
   * have chosen a card that cannot be played, so they must select again.
   */
  void printInvalidPlay();

  /**
   * The printNoValidPlays() method is used to display a message letting the player know that
   * none of the cards in their hand can be played, so a card was drawn and added to their hand.
   * @param player
   */
  void printNoValidPlays(String player);

  /**
   *
   * @param player
   */
  void printPlayerChooseNewSuit(String player);
}
