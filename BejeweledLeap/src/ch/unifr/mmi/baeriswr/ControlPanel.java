package ch.unifr.mmi.baeriswr;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {
    private JButton newGameButton = new JButton("New Game");
    private Game game;
    private JButton exit = new JButton("Exit");
    private JButton cheat = new JButton("Cheat");
    public ControlPanel(Game game){
        this.game = game;
        add(newGameButton);
        newGameButton.addActionListener(this);
        add(exit);
        exit.addActionListener(this);
        add(cheat);
        cheat.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e){
      if (e.getSource().equals(newGameButton)){
            game.initGame();
      }

      if (e.getSource().equals(exit))
          System.exit(0);
   }
}
