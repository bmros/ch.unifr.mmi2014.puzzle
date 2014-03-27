package ch.unifr.mmi.baeriswr;

import java.awt.Color;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JComponent;
/*
 This class records the state of the game
 It will be extended to support animation 
 and the various states of the bejeweled game.
 */
public final class Game extends JComponent{
    private StatePanel sPanel;
    private Board gameBoard;
    private Algorithms solver;
    private Animation animation;
    private Tile focus;
    private Tile fingerFocus;
    private boolean started;
    private int score;
    private int level;
    private int combo;
    public static ImageLibrary imageLibrary = new ImageLibrary();
    public static SoundLibrary soundLibrary = new SoundLibrary();
    
    
    public BufferedImage boardImg;
    public ImageIcon boardIcon;
    
    public Game(StatePanel sPanel){
    	//small test
    	System.out.println(System.getProperty("user.dir"));
        try {
           // boardImg = ImageIO.read(new File("\\res\\board.png"));
           boardImg = ImageIO.read(getClass().getResource("/res/board.png"));
        } catch (IOException e) { System.out.println(e.getMessage());}
        
        // getClass().getResource("/images/yourImageName.extension");
        
        this.boardIcon = new ImageIcon();
        this.boardIcon.setImage(this.boardImg);
        started = false;
        this.sPanel = sPanel;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800,600)); 
        // Leap Motion Listener
        this.addMouseListener(new LeapMotionListener(this));
        KeyboardListener k = new KeyboardListener(this);        
        //this.addKeyListener(new KeyboardListener(this));
        this.addMouseListener(new MouseListener(this));
        //System.out.println("is focusable: "+this.isFocusable());
        this.setFocusable(true);
    }
    public void initGame(){
        gameBoard = new Board(this);
        solver = new Algorithms(gameBoard,this);
        animation = new Animation(this,gameBoard,null);
        // initialize game
        gameBoard.initAll();
        while(!solver.isStable()) {
            solver.rmChains();
        }
        //set up state information
        started = true;
        focus = null;
        score = 0;
        combo = 0;
        level = 1;
        sPanel.setScore(score);
        sPanel.setCombo(combo);
        sPanel.setLevel(level);
        sPanel.setRow(-1);
        sPanel.setColumn(-1);
        repaint();
        Game.soundLibrary.playAudio("fall");
    }
    public void updateGame() {
        if (!solver.isStable()) {
            solver.markDeleted();
            solver.calculateDrop();
            animation.setType(Animation.animType.CASCADE);
            animation.animateCascade();
            Game.soundLibrary.playAudio("fall");
        }
    }
    public void cleanBoard() {
        solver.applyDrop();
        solver.fillEmpty();
        solver.endCascade();
    }
    public void addScore(int points){
         if ((this.score+points) > 1000){
            this.level++;
            sPanel.setLevel(this.level);
            this.score = 0;
            sPanel.setScore(this.score);
        }
         else {
           this.score += points;
           sPanel.setScore(score);
         }
        
    }
    public void setCombo(int combo){
        if (combo > this.combo){
          this.combo = combo;
          sPanel.setCombo(combo);
        }
    }
    public void clickPerformed(int click_x,int click_y) {
        sPanel.setColumn(click_x);
        sPanel.setRow(click_y);
        Tile clicked = gameBoard.getTileAt(click_y, click_x);
        if (focus == null) {
            focus = clicked;
            clicked.inFocus = true;
            Game.soundLibrary.playAudio("select");
        }
        else {
            if (focus.equals(clicked)) {
                clicked.inFocus = false;
                focus = null;
            }
            else {
                if(focus.isNeighbor(clicked)){
                    focus.inFocus = false;
                    swapTiles(focus,clicked);
                    focus = null;
                }
                else {
                    focus.inFocus = false;
                    focus = clicked;  
                    clicked.inFocus = true;
                }
            }
        }
    }
    
    /**
     * method to highlight the current tile pointed at
     * @param click_x
     * @param click_y
     */
    public void showFingerFocus(int click_x,int click_y) {
        //sPanel.setColumn(click_x);
        //sPanel.setRow(click_y);
        Tile focusedTile = gameBoard.getTileAt(click_y, click_x);
        if (fingerFocus == null) {
        	fingerFocus = focusedTile;
            focusedTile.inFingerFocus = true;
            // Game.soundLibrary.playAudio("select");
        } 
        else {
            fingerFocus.inFingerFocus = false;
            fingerFocus = focusedTile;  
            focusedTile.inFingerFocus = true;        
        }
        
    }
    
    /**
     * simple method to set the focus to the current pointed at tile
     */
    public void setFingerFocus() {
    	
    	if (fingerFocus == null) {
    		// do nothing
    	} else {
    		if (focus == null) { // no other focused tile
    			fingerFocus.inFocus = true;
    			focus = fingerFocus;
    			//System.out.println("focus set to: "+);
    		} else { // there is another focused tile
    			if(fingerFocus.isNeighbor(focus)){ // neighbours: swap them
    				swapTiles(focus,fingerFocus);
    				focus.inFocus = false;
    				focus = null;
    			} else {
    				focus.inFocus = false;
        			fingerFocus.inFocus = true;
        			focus = fingerFocus;
    			}	
    		}
    	}
    }
    
    /**
     * method to swap tiles with no specific focused tile, but pointed
     * depending on the direction
     * @param direction indicates the direction: 1 for left, 2 for down, 3 for right, 4 for up
     * 
     * ## 4 ##
     * # 1 3 #
     * ## 2 ##
     */
    public void directionPerformed(int direction) {
    	if (fingerFocus == null) {
    		// do nothing
    	} else {
    		if(focus != null) {
    			focus.inFocus = false;
    			focus = null;
    		}
    		focus = fingerFocus;
    		focus.inFocus = true;
    		
    		switch(direction) {
    		case 1: { //left
    			if (focus.col == 0) { // outermost left: cant swap
    				break;
    			} else {
    				//System.out.println("LEFT: swapping tile("+focus.col+","+focus.row+") with tile("+(focus.col-1)+","+focus.row+")");
    				Tile swapTile = gameBoard.getTileAt(focus.row, focus.col-1);
    				swapTiles(focus,swapTile);
    				focus.inFocus = false;
    				focus = null;
    				break;
    			}
    		}
    		case 2: { // down
    			if (focus.row == 7) { // bottom row: cant swap
    				break;
    			} else {
    				//System.out.println("DOWN: swapping tile("+focus.col+","+focus.row+") with tile("+(focus.col)+","+(focus.row+1)+")");
    				Tile swapTile = gameBoard.getTileAt(focus.row+1, focus.col);
    				swapTiles(focus,swapTile);
    				focus.inFocus = false;
    				focus = null;
    				break;
    			}
    			
    		}
    		case 3: { // right
    			if (focus.col == 7) { // outermost right: cant swap
    				break;
    			} else {
    				//System.out.println("RIGHT: swapping tile("+focus.col+","+focus.row+") with tile("+(focus.col+1)+","+focus.row+")");
    				Tile swapTile = gameBoard.getTileAt(focus.row, focus.col+1);
    				swapTiles(focus,swapTile);
    				focus.inFocus = false;
    				focus = null;
    				break;
    			}
    		}
    		case 4: { // up
    			if (focus.row == 0) { // top row: cant swap
    				break;
    			} else {
    				//System.out.println("UP: swapping tile("+focus.col+","+focus.row+") with tile("+(focus.col)+","+(focus.row+1)+")");
    				Tile swapTile = gameBoard.getTileAt(focus.row-1, focus.col);
    				swapTiles(focus,swapTile);
    				focus.inFocus = false;
    				focus = null;
    				break;
    			}
    		}
    		default: { // not valid
    			break;
    		}
    		}
    		if (focus != null) {
    			focus.inFocus = false;
        		focus = null;
    		}
    		if (fingerFocus != null) {
    			fingerFocus.inFingerFocus = false;
        		fingerFocus = null;
    		}
    		
    	}	
    }
    
    
    
    
    
    
    
    private void swapTiles(Tile t1,Tile t2){
        animation.setType(Animation.animType.SWAP);
        animation.animateSwap(t1, t2);
    }
    public void paintComponent(Graphics g){
        this.boardIcon.paintIcon(null, g, 0, 0);
        if(started)
        drawGems(g);
    }
    private void drawGems(Graphics g){
        int row,col;
        for (row=0;row<8;row++){
            for (col=0;col<8;col++){
                Tile tile = gameBoard.getTileAt(row, col);
                tile.draw(g);
            }
        }
    }
   
}
