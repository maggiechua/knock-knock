package Tests.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Mocks.ControllerMock;
import Mocks.ViewMock;
import Model.GameModel;
import Model.Model;
import View.GameView;
import Controller.GameController;
import Model.Player;
import Model.Card;
import Model.CardDeck;

/**
 * A JUnit Test Class for the Knock-Knock Model Implementation. It contains test cases to
 * demonstrate that the game works according to the base game rules:
 * GAME SETUP
 * - the starting card is NOT a special card
 * - player order is set up correctly
 * - the deck contains valid cards as well as the correct quantities (i.e. if multiple decks are
 * in play)
 * - each player is given 7 cards to begin with from the deck
 * GAMEPLAY
 * - determines which cards the player can play based on the top card in the pile
 * - resets so that none of the cards are marked valid after a play is made
 * - automatically draws a card for the player if they have no valid cards to play
 * - updates which player's turn it is
 * - when a card is played, it automatically updates the player's hand (removes the card)
 * - when special cards are played, the correct updates are made to the player's hand/turn/pile
 * - determines if the player says 'Knock-Knock' when they have one card remaining; otherwise
 * forces them to draw another card
 * WIN CASE
 * - if a player runs out of cards, display a win message
 * - allow the remaining players to continue playing until there is only one person with cards
 * remaining
 * {All methods present in the GameModel are also tested as some of them serve as intermediaries
 * to perform the functionality listed above. Any Classes representing other forms are data are
 * separately tested in their own respective test class file.}
 */
public class ModelTest {
  private GameModel model;
  private GameView view;
  private GameController controller;
  private Appendable log;

  @Before
  public void setUp() {
    Map<Card, String> baseRules = new HashMap<>();
    baseRules.put(new Card("clubs", "J"), "draw 7 cards");
    baseRules.put(new Card("any", "2"), "draw 2 cards");
    baseRules.put(new Card("any", "8"), "wild card - can switch suit as long as " +
            "played on top of a card of the same suit (i.e. if last card was Hearts, can only put " +
            "8 of hearts)");
    baseRules.put(new Card("any", "A"), "skip the next player's turn");
    baseRules.put(new Card("any", "7"), "reverse direction");
    CardDeck cardDeck = new CardDeck(2, new Random(20));
    List<Player> players = new ArrayList<>(Arrays.asList(
            new Player("Player 1", new ArrayList<Card>(), cardDeck),
            new Player("Player 2", new ArrayList<Card>(), cardDeck),
            new Player("Player 3", new ArrayList<Card>(), cardDeck),
            new Player("Player 4", new ArrayList<Card>(), cardDeck)
    ));
    model = new Model(players, baseRules, cardDeck, new LinkedList<Player>(), null);
    view = new ViewMock(log);
    controller = new ControllerMock();
  }

  // invalid card given -> input contains typos: hQ, Hq, H* -> higher level function call
  // assertFalse(deck1.checkPlay("hQ"));
  // assertFalse(deck1.checkPlay("Hq"));
  // assertFalse(deck1.checkPlay("H*"));

  // {GAME SETUP TESTS}
  @Test
  public void testStartingCardNotSpecialCard() {

  }

  // {GAMEPLAY TESTS}
  @Test
  public void testCanPlayCard() {

  }

  @Test
  public void testRemovePlayedCardFromPlayerHand() {

  }


  // {WIN CASE TESTS}
}
