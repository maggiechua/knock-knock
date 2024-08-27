package View;

import java.awt.*;

import javax.swing.*;

public class GuiView extends JFrame {

  private final JPanel mainPanel;
  private final JPanel cardPanel;



  public GuiView() {
    setSize(800, 600);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
//    mainPanel.setBackground(new Color(53, 101, 77));


    int cardWidth = 110;
    int cardHeight = 154;


    cardPanel = new JPanel() {
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        Image hiddenCard = new ImageIcon(getClass().getResource("/cards/BACK.png")).getImage();
        g.drawImage(hiddenCard, 20, 20, cardWidth, cardHeight, null);
      }
    };

    cardPanel.setBackground(new Color(53, 101, 77));

    add(mainPanel, BorderLayout.CENTER);
    mainPanel.add(cardPanel, BorderLayout.CENTER);







  }

}
