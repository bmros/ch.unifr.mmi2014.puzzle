package ch.unifr.mmi.baeriswr;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 


/*
 this class monitors user input on the game board
 */

public class MouseListener extends MouseInputAdapter {
    private Game gamePanel;
    
    public MouseListener(Game gamePanel){
        this.gamePanel = gamePanel;
    }
    public void mouseClicked (MouseEvent e){
    	
    /*
      System.out.println("mouse Clicked");
      int col = (e.getX()-240)/65;
      int row = (e.getY()-40)/65;
      if (col < 0) col = 0;
      if (col > 7) col = 7;
      if (row < 0) row = 0;
      if (row > 7) row = 7;
      gamePanel.clickPerformed(col, row);
      gamePanel.repaint();
     */ 
    }
    public void mouseReleased (MouseEvent e){
    	
        
        System.out.println("mouse Clicked");
        int col = (e.getX()-240)/65;
        int row = (e.getY()-40)/65;
        if (col < 0) col = 0;
        if (col > 7) col = 7;
        if (row < 0) row = 0;
        if (row > 7) row = 7;
        gamePanel.clickPerformed(col, row);
        gamePanel.repaint();
        
      }
    
    
}


