package Controller;

import Model.Player;

/**
 * The PlayCommand abstract class is used to implement the Command Design Pattern for all special
 * cards in the game.
 */
abstract class PlayCommand {
  protected Player current;

  public PlayCommand(Player current) {
    this.current = current;
  }

  abstract void execute();
}
