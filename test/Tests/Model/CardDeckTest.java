package Tests.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Model.Card;
import Model.CardDeck;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit Test Class for the Knock-Knock Model Implementation. It contains test cases to
 * demonstrate that the CardDeck class works accurately according to base game rules. It tests ALL
 * public methods present, while private methods (since they are helpers to the public ones) are
 * assumed to work correctly as long as all tests pass.
 */
public class CardDeckTest {
  // Each of the variables listed below represent the number of card decks for a given gameplay
  // (i.e. deck1 = 1 card deck in play; deck2 = 2 card decks in play; etc.)
  CardDeck deck1;
  CardDeck deck2;
  CardDeck deck5;
  List<Card> specialCards;

  @Before
  public void setUp() {
    deck1 = new CardDeck(1, new Random(20));
    deck2 = new CardDeck(2, new Random(20));
    deck5 = new CardDeck(5, new Random(20));
    specialCards = new ArrayList<Card>(Arrays.asList(
            new Card("♣", "J"),
            new Card("◆", "2"),
            new Card("◆", "8"),
            new Card("◆", "A"),
            new Card("◆", "7"),
            new Card("♣", "2"),
            new Card("♣", "8"),
            new Card("♣", "A"),
            new Card("♣", "7"),
            new Card("♥", "2"),
            new Card("♥", "8"),
            new Card("♥", "A"),
            new Card("♥", "7"),
            new Card("♠", "2"),
            new Card("♠", "8"),
            new Card("♠", "A"),
            new Card("♠", "7")
    ));
  }

  @Test
  public void testGenerateStartingCard() {
    Card firstCard = deck1.drawCard();
    System.out.println(firstCard.getSuit() + firstCard.getValue());
    Card startingCard = deck1.generateStartingCard();
    assertEquals("♠Q", startingCard.getSuit() + startingCard.getValue());
  }

  @Test
  public void testGenerateDeck() {

  }

  @Test
  public void testSpecialCard() {

  }

  @Test
  public void testConvertSuit() {
    // converting valid inputs for String to suit symbol conversion
    String diamonds = deck1.convertSuit("D");
    assertEquals("◆", diamonds);
    String clubs = deck1.convertSuit("C");
    assertEquals("♣", clubs);
    String hearts = deck1.convertSuit("H");
    assertEquals("♥", hearts);
    String spades = deck1.convertSuit("S");
    assertEquals("♠", spades);

    // providing invalid inputs for conversion -> although this scenario does not happen
    // based on the restrictions we place when calling the method
    String invalid = deck1.convertSuit("9");
    assertEquals("", invalid);
  }
}
