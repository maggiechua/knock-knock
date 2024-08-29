package Tests.Model;

import org.junit.Before;
import org.junit.Test;

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

  @Before
  public void setUp() {
    deck1 = new CardDeck(1, new Random(20));
    deck2 = new CardDeck(2, new Random(20));
    deck5 = new CardDeck(5, new Random(20));
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
}
