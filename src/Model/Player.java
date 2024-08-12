package Model;

import java.util.List;

public class Player {
  private String name;
  private List<Card> hand;

  public Player(String name, List<Card> hand) {
    this.name = name;
    this.hand = hand;
  }
}
