package View;

import java.io.IOException;
import java.util.List;

import Model.Card;

/**
 * The following class represents the View for a text-based Knock-Knock game, consisting of
 * print methods to display updates during gameplay. 
 */
public class View implements GameView {
  private Appendable ap;

  /**
   *
   * @param ap
   */
  public View(Appendable ap) {
    this.ap = ap;
  }

  /**
   * The writeMessage() method is used to print text to the console, displaying updates to the
   * game for the player.
   * @param message the given message to display represented as a String
   * @throws IllegalStateException if an invalid message is given
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      ap.append(message);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void welcomeMessage() {
    writeMessage("Welcome to the Knock-Knock App! \n");
    printBaseRules();
  }

  @Override
  public void printBaseRules() {
    writeMessage("The objective of Knock-Knock is to get rid of all your cards. Below " +
            "are the base game rules for the game: \n");
    writeMessage("- Jack of CLUBS means the player after has to draw 7 cards \n");
    writeMessage("- 2 of ANY SUIT means the player after has to draw 2 cards \n");
    writeMessage("- 8 of ANY SUIT is a wild card, meaning that the player who placed it can" +
            "change it to any suit they desire. must be placed on the same suit when played. \n");
    writeMessage("- Ace of ANY SUIT means the next player's turn is skipped \n");
    writeMessage("- 7 of ANY SUIT means the direction is reversed \n \n");
  }

  @Override
  public void printTopCardInPile(Card c) {
    writeMessage("                    ------- \n" +
                 "                    |     | \n" +
            "                    | " + c.getSuit() + c.getValue() + "  | \n" +
                 "                    |     | \n" +
                 "                    ------- \n");
  }

  /**
   * The printCardOutline() method is used to display a player's hand in a nice format.
   * @param numCards the number of cards to print represented as Integer
   * @param pattern the given pattern to print represented as a String
   */
  private void printCardOutline(int numCards, String pattern) {
    for (int i = 0; i < numCards; i++) {
      writeMessage(pattern);
    }
    if (numCards > 1) {
      writeMessage("\n");
    }
  }

  @Override
  public void printPlayerHand(List<Card> hand) {
    printCardOutline(hand.size(), "------- ");
    printCardOutline(hand.size(), "|     | ");
    for (Card c : hand) {
      if (c.getValidPlay()) {
        printCardOutline(1, "| " + c.getSuit() + c.getValue() + "* | ");
      }
      else {
        printCardOutline(1, "| " + c.getSuit() + c.getValue() + "  | ");
      }
    }
    writeMessage("\n");
    printCardOutline(hand.size(), "|     | ");
    printCardOutline(hand.size(), "------- ");
  }

  @Override
  public void printPlayerTurn(String player) {
    writeMessage("What card will you like to play, " + player +
            "? Enter the suit (D, C, H, S) followed by " +
            "its value (i.e. D9): ");
  }

  @Override
  public void printInvalidPlay() {
    writeMessage("The chosen card cannot be played. Please select again: ");
  }

  @Override
  public void printNoValidPlays(String player) {
    writeMessage("There were no valid cards that could be played. A card was drawn and added " +
            "to your hand, " + player + ". \n");
  }

  @Override
  public void printPlayerChooseNewSuit(String player) {
    writeMessage(player + ", please type what suit you would like to change to (D, C, H, S): ");
  }
}
