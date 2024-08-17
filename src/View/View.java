package View;

import java.io.IOException;
import java.util.List;

import Model.Card;

public class View implements GameView {
  private Appendable ap;

  public View(Appendable ap) {
    this.ap = ap;
  }

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
      printCardOutline(1, "| " + c.getSuit() + c.getValue() + "  | ");
    }
    writeMessage("\n");
    printCardOutline(hand.size(), "|     | ");
    printCardOutline(hand.size(), "------- ");
  }

  @Override
  public void printPlayerTurn() {
    writeMessage("What card will you like to play? ");
  }
}
