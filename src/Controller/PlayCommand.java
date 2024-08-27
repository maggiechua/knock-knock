package Controller;

import Model.Player;

/**
 *
 */
abstract class PlayCommand {
  protected Player current;

  public PlayCommand(Player current) {
    this.current = current;
  }

  abstract void execute();
}
