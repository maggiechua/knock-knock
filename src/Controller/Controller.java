package Controller;

import Model.GameModel;

public class Controller implements GameController {
  private GameModel model;

  public Controller(GameModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model or View is null");
    }
    else {
      this.model = model;
    }
  }

  @Override
  public void execute() {

  }
}
