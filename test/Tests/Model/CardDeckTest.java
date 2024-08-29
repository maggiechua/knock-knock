package Tests.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import Model.Card;
import Model.CardDeck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
  List<Card> normalDeck;
  Random rand;

  @Before
  public void setUp() {
    rand = new Random(20);
    deck1 = new CardDeck(1, rand);
    deck2 = new CardDeck(2, rand);
    deck5 = new CardDeck(5, rand);
    specialCards = setUpSpecialCards();
    normalDeck = new ArrayList<>();
    setUpNormalDeck();
  }

  /**
   * The checkCardEquality() method determines if the given card is present in the list of
   * special cards.
   * @param c the given Card
   * @return True if the given card is a special card; False if otherwise
   */
  private boolean checkIfSpecialCard(Card c) {
    boolean specialCard = false;
    for (Card sc : specialCards) {
      if (sc.getSuit().equals(c.getSuit()) &&
      sc.getValue().equals(c.getValue())) {
        specialCard = true;
        break;
      }
    }
    return specialCard;
  }

  private boolean checkCardEquality(Card c1, Card c2) {
    return c1.getSuit().equals(c2.getSuit()) && c1.getValue().equals(c2.getValue());
  }

  /**
   * The retrieveCardsInDrawPile() method is used to extract all the cards in the generated
   * drawPile for local use in testing
   * @param deck the given CardDeck
   * @param num the number of decks used in gameplay
   * @return the game's draw pile as a Queue representation
   */
  private Queue<Card> retrieveCardsInDrawPile(CardDeck deck, int num) {
    Queue<Card> drawPile = new LinkedList<>();
    for (int i = 0; i < num * 52; i++) {
      drawPile.add(deck.drawCard());
    }
    return drawPile;
  }

  /**
   * For the testGenerateDeck(), the decks are initialized in the setUp() area,
   * so the generateDeck() method does not get called in the test.
   */
  @Test
  public void testGenerateDeck() {
    // retrieving the draw pile and confirming that the number of cards in the drawPile (since
    // this is before it is distributed to the players is consistent with the number of
    // decks utilized for gameplay)
    Queue<Card> drawPile1 = retrieveCardsInDrawPile(deck1, 1);
    assertEquals(52, drawPile1.size());
    // checks that the draw pile has the correct quantity of each card if multiple decks are
    // utilized
    for (Card c : drawPile1) {
      //
    }
    // checks that the draw pile has been shuffled (its order should not be the same as a new
    // pack of playing cards) -> how do we prove this???
    for (Card c : drawPile1) {
      System.out.print(c.getSuit() + c.getValue() + " ");
    }
    System.out.println("\n");

    Queue<Card> drawPile2 = retrieveCardsInDrawPile(deck2, 2);
    assertEquals(104, drawPile2.size());
    for (Card c : drawPile2) {
      System.out.print(c.getSuit() + c.getValue() + " ");
    }
    System.out.println("\n");

    Queue<Card> drawPile3 = retrieveCardsInDrawPile(deck5, 5);
    assertEquals(260, drawPile3.size());
    for (Card c : drawPile3) {
      System.out.print(c.getSuit() + c.getValue() + " ");
    }
  }

  @Test
  public void testDrawCard() {
    // normal

    // when there are no cards remaining in the draw pile
  }

  /**
   * The testGetTopCardInPile() can also account for the addPlayedCard() method as the latter
   * simply adds the given card to the play pile (which can then be retrieved using
   * getTopCardInPile())
   */
  @Test
  public void testGetTopCardInPile() {
    // when the game has just been set up, draw the first card from the draw pile and place it
    // in the play pile; then compare the cards are equivalent
    Card drawnCard1 = deck1.drawCard();
    deck1.addPlayedCard(drawnCard1);
    assertEquals(drawnCard1, deck1.getTopCardInPile());
    assertEquals("♥7",drawnCard1.getSuit() + drawnCard1.getValue());

    // 40 cards drawn and added to the pile -> maybe add fuzzy testing for this one?
    for (int i = 0; i < 40; i++) {
      deck1.addPlayedCard(deck1.drawCard());
    }

    // check that the cards are equivalent even after many cards have been played into the
    // play pile
    Card drawnCardRand = deck1.drawCard();
    deck1.addPlayedCard(drawnCardRand);
    assertEquals(drawnCardRand, deck1.getTopCardInPile());
    assertEquals("♣8",drawnCardRand.getSuit() + drawnCardRand.getValue());
  }

  @Test
  public void testGenerateStartingCard() {
    // starting card generated
    Card startingCard1 = deck1.generateStartingCard();
    // retrieve the top card in the pile
    Card topCard1 = deck1.getTopCardInPile();
    // the starting card and the first card in pile should be equivalent
    assertEquals(startingCard1, topCard1);
    // checks that the starting card is not present in specialCards list
    assertFalse(checkIfSpecialCard(topCard1));

    Card startingCard2 = deck2.generateStartingCard();
    Card topCard2 = deck2.getTopCardInPile();
    assertEquals(startingCard2, topCard2);
    assertFalse(checkIfSpecialCard(topCard2));
  }

  @Test
  public void testSpecialCard() {
    // tests that the value of the special card is returned as a String
    for (Card sc : specialCards) {
      assertEquals(sc.getValue(), deck1.specialCard(sc));
    }

    // tests that if the given card is NOT a special card, an empty String is returned
    assertEquals("", deck1.specialCard(new Card("◆", "5")));
    assertEquals("", deck1.specialCard(new Card("♣", "9")));
    assertEquals("", deck1.specialCard(new Card("♥", "K")));
    assertEquals("", deck1.specialCard(new Card("♠", "Q")));
  }

  @Test
  public void testCheckPlay() {
    Card startingCard = deck1.generateStartingCard();
    assertEquals("♠Q", startingCard.getSuit() + startingCard.getValue());

    // Invalid Play -> Not the same suit OR value
    assertFalse(deck1.checkPlay("D4"));
    assertFalse(deck1.checkPlay("HK"));
    // Invalid Play Special Cards
    assertFalse(deck1.checkPlay("CJ"));
    assertFalse(deck1.checkPlay("H7"));
    assertFalse(deck1.checkPlay("D2"));
    assertFalse(deck1.checkPlay("CA"));

    // Valid Play -> Same suit OR Different suit, same value
    assertTrue(deck1.checkPlay("SK"));
    assertTrue(deck1.checkPlay("HQ"));
    // Valid Play Special Cards
    assertTrue(deck1.checkPlay("S8"));
    assertTrue(deck1.checkPlay("SA"));
    assertTrue(deck1.checkPlay("S2"));
    assertTrue(deck1.checkPlay("S7"));
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

    // providing invalid inputs for conversion -> need to figure out how to do this?
    String invalid = deck1.convertSuit("9");
    assertEquals("9", invalid);
  }

  /**
   * The setUpSpecialCards() method adds all special cards in base game to a List for testing
   * purposes.
   * @return a List representation containing special Cards
   */
  private List<Card> setUpSpecialCards() {
    return new ArrayList<Card>(Arrays.asList(
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

  /**
   * The setUpNormalDeck() method adds all possible cards in a single set of playing cards
   * into a List.
   */
  private void setUpNormalDeck() {
    normalDeck.add(new Card("◆", "A"));
    normalDeck.add(new Card("◆", "2"));
    normalDeck.add(new Card("◆", "3"));
    normalDeck.add(new Card("◆", "4"));
    normalDeck.add(new Card("◆", "5"));
    normalDeck.add(new Card("◆", "6"));
    normalDeck.add(new Card("◆", "7"));
    normalDeck.add(new Card("◆", "8"));
    normalDeck.add(new Card("◆", "9"));
    normalDeck.add(new Card("◆", "10"));
    normalDeck.add(new Card("◆", "J"));
    normalDeck.add(new Card("◆", "Q"));
    normalDeck.add(new Card("◆", "K"));

    normalDeck.add(new Card("♣", "A"));
    normalDeck.add(new Card("♣", "2"));
    normalDeck.add(new Card("♣", "3"));
    normalDeck.add(new Card("♣", "4"));
    normalDeck.add(new Card("♣", "5"));
    normalDeck.add(new Card("♣", "6"));
    normalDeck.add(new Card("♣", "7"));
    normalDeck.add(new Card("♣", "8"));
    normalDeck.add(new Card("♣", "9"));
    normalDeck.add(new Card("♣", "10"));
    normalDeck.add(new Card("♣", "J"));
    normalDeck.add(new Card("♣", "Q"));
    normalDeck.add(new Card("♣", "K"));

    normalDeck.add(new Card("♥", "A"));
    normalDeck.add(new Card("♥", "2"));
    normalDeck.add(new Card("♥", "3"));
    normalDeck.add(new Card("♥", "4"));
    normalDeck.add(new Card("♥", "5"));
    normalDeck.add(new Card("♥", "6"));
    normalDeck.add(new Card("♥", "7"));
    normalDeck.add(new Card("♥", "8"));
    normalDeck.add(new Card("♥", "9"));
    normalDeck.add(new Card("♥", "10"));
    normalDeck.add(new Card("♥", "J"));
    normalDeck.add(new Card("♥", "Q"));
    normalDeck.add(new Card("♥", "K"));

    normalDeck.add(new Card("♠", "A"));
    normalDeck.add(new Card("♠", "2"));
    normalDeck.add(new Card("♠", "3"));
    normalDeck.add(new Card("♠", "4"));
    normalDeck.add(new Card("♠", "5"));
    normalDeck.add(new Card("♠", "6"));
    normalDeck.add(new Card("♠", "7"));
    normalDeck.add(new Card("♠", "8"));
    normalDeck.add(new Card("♠", "9"));
    normalDeck.add(new Card("♠", "10"));
    normalDeck.add(new Card("♠", "J"));
    normalDeck.add(new Card("♠", "Q"));
    normalDeck.add(new Card("♠", "K"));
  }
}
