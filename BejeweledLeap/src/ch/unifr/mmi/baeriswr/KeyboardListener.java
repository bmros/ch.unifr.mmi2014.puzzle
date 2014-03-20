package ch.unifr.mmi.baeriswr;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyboardListener {
	
	private Game gamePanel;

	private class MyDispatcher implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // ENTER: set focus
					setFocus();
				}
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT): gamePanel.directionPerformed(1);break;
				case (KeyEvent.VK_RIGHT): gamePanel.directionPerformed(3);break;
				case (KeyEvent.VK_DOWN): gamePanel.directionPerformed(2);break;
				case (KeyEvent.VK_UP): gamePanel.directionPerformed(4);break;
				default: break;
				}
				
				
				
				//System.out.println("tester");
			} else if (e.getID() == KeyEvent.KEY_RELEASED) {
				//System.out.println("2test2");
			} else if (e.getID() == KeyEvent.KEY_TYPED) {
				//System.out.println("3test3");
			}
			return false;
		}
	}
	
	
	
	public KeyboardListener(Game gamePanel) {
        this.gamePanel = gamePanel;
        System.out.println("test");
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }
	
	private void setFocus() {
		gamePanel.setFingerFocus();
	}
	
	private void keyPressed(int direction) {
		gamePanel.directionPerformed(direction);
		
	}
	
	

}

/*
public class KeyboardListener implements KeyListener {
	
	private Game gamePanel;
    
    public KeyboardListener(Game gamePanel){
        this.gamePanel = gamePanel;
    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("space pressed");
		} else {
			System.out.println("keyocode = "+arg0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("space pressed");
		} else {
			System.out.println("keyocode = "+arg0);
		}
	}
	
	private void setFocus() {
		gamePanel.setFingerFocus();
	}
	
	private void keyPressed(int direction) {
		gamePanel.directionPerformed(direction);
		
	}
}
*/
/*
public class MyFrame extends JFrame {    
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                System.out.println("tester");
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                System.out.println("2test2");
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                System.out.println("3test3");
            }
            return false;
        }
    }
    public MyFrame() {
        add(new JTextField());
        System.out.println("test");
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.pack();
        f.setVisible(true);
    }
}

*/





