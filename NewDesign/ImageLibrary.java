//package ch.unifr.mmi.baeriswr;


import javax.imageio.*;
import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.awt.image.BufferedImage;
public class ImageLibrary {
    private HashMap<Tile.tileID,BufferedImage> imageCollection;
    public ImageLibrary() {
    	
        imageCollection = new HashMap(9);
        BufferedImage img;
        try {
        img = ImageIO.read(new File("gemRed.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemRed.png"));	
        imageCollection.put(Tile.tileID.RED, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("gemGreen.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemGreen.png"));	
        imageCollection.put(Tile.tileID.GREEN, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("gemBlue.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemBlue.png"));	
        imageCollection.put(Tile.tileID.BLUE, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("gemOrange.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemOrange.png"));	
        imageCollection.put(Tile.tileID.ORANGE, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("gemPurple.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemPurple.png"));	
        imageCollection.put(Tile.tileID.PURPLE, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("gemWhite.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemWhite.png"));	
        imageCollection.put(Tile.tileID.WHITE, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("gemYellow.png"));
        //img = ImageIO.read(getClass().getResource("/res/gemYellow.png"));	
        imageCollection.put(Tile.tileID.YELLOW, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("focus.png"));
        //img = ImageIO.read(getClass().getResource("/res/focus.png"));	
        imageCollection.put(Tile.tileID.FOCUS, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        try {
        img = ImageIO.read(new File("fingerfocus.png"));
        //img = ImageIO.read(getClass().getResource("/res/fingerfocus.png"));	
        imageCollection.put(Tile.tileID.FINGERFOCUS, img);
        } catch (IOException e) { System.out.println(e.getMessage()); }
        
    }
    BufferedImage getImage(Tile.tileID id) {
        return imageCollection.get(id);
    }
}
