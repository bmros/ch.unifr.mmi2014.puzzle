//package ch.unifr.mmi.baeriswr;

import javax.swing.JPanel;
import javax.swing.JLabel;

/*
 this class keeps track of some interesting game
 information and display them in labels
 */
public class StatePanel extends JPanel {
    private JLabel textScore = new JLabel("Score");
    private JLabel score  = new JLabel("0",JLabel.CENTER);
    private JLabel textLevel = new JLabel("| Level");
    private JLabel level  = new JLabel("1",JLabel.CENTER);
    private JLabel textCombo = new JLabel("| Max Combo");
    private JLabel combo  = new JLabel("0",JLabel.CENTER);

    
    public StatePanel(){
        add(textScore);
        add(score);
        add(textLevel);
        add(level);
        add(textCombo);
        add(combo);
    }
    public void setScore(int score){
        this.score.setText(Integer.toString(score));
    }
    public void setLevel(int level){
        this.level.setText(Integer.toString(level));
    }
    public void setCombo(int combo){
        this.combo.setText(Integer.toString(combo));
    }

}
