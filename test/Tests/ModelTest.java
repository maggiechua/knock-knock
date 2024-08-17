package Tests;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

public class ModelTest {
  private GameModel model;
  private GameView view;
  private GameController controller;

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
    CardDeck cardDeck = new CardDeck(2);
    List<Player> players = new ArrayList<>(Arrays.asList(
            new Player("Player 1", new ArrayList<Card>(), cardDeck),
            new Player("Player 2", new ArrayList<Card>(), cardDeck),
            new Player("Player 3", new ArrayList<Card>(), cardDeck),
            new Player("Player 4", new ArrayList<Card>(), cardDeck)
    ));
    model = new Model(players, baseRules);
    view = new ViewMock();
    controller = new ControllerMock();
  }

  /**
   * The following tests shows that each player in the model is given a random hand
   * consisting of 7 cards. There may be repeats of the same card if there are multiple
   * decks being drawn upon for gameplay.
   */
  @Test
  public void testGenerateHandsWithOneDeck() {
    model.generateHands();

  }
}
