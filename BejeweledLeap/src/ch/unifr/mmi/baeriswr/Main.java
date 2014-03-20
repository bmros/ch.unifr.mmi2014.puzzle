package ch.unifr.mmi.baeriswr;

import javax.swing.JFrame;

//import ch.unifr.baeriswr.SampleListener;

import com.leapmotion.leap.Controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.IOException;
public class Main extends JFrame{
     public static void main(String[] args){
         new Main();
     }
     public Main(){
         windowSetup();
         // Content Pane Setup
         Container content = getContentPane();
         StatePanel sPanel = new StatePanel();
         Game newGame = new Game(sPanel);
         ControlPanel cPanel = new ControlPanel(newGame);
         content.add(newGame,BorderLayout.CENTER);
         content.add(sPanel,BorderLayout.WEST);
         content.add(cPanel,BorderLayout.SOUTH);
         content.setFocusable(true);
         //set visible
         pack();
         setVisible(true);
     }
     private void windowSetup(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth  = screenSize.width;
        setSize(screenWidth,screenHeight);
        setLocationByPlatform(true);
        setTitle("Bejeweled");
        setLayout(new BorderLayout());
        setDefaultCloseOperation (EXIT_ON_CLOSE);   
    }
}
