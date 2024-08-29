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
   * @param player a String representation of the player's name
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
   * @param player a String representation of the player's name
   */
  void printNoValidPlays(String player);

  /**
   * The printDrawCards() method is used to display a message letting the given player know that since
   * a draw 2 special card was played by the previous player, 2 cards have been added to their
   * hand and their turn is skipped.
   * @param player a String representation of the player's name
   * @param sc  a String representation of the special card played
   * @param numCards an Integer representation of the number of cards drawn
   */
  void printDrawCards(String player, String sc, int numCards);

  /**
   *
   * @param player a String representation of the player's name
   */
  void printReverseDirection(String player);

  /**
   *
   * @param player a String representation of the player's name
   */
  void printPlayerChooseNewSuit(String player);

  /**
   *
   * @param player a String representation of the player's name
   * @param suit a String representation of the new suit
   */
  void printSuitChange(String player, String suit);
}
