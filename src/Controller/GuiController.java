package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.GameModel;
import View.GameView;

public class GuiController implements ActionListener, GameController {
  private final GameView view;
  private final GameModel model;

  public GuiController(GameModel model, GameView view) {
    this.model = model;
    this.view = view;
  }



  @Override
  public void execute() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
