package ch.unifr.mmi.baeriswr;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class VoiceListener {
	
	private Game gamePanel;
	private SpeechCommand speechcommand;

	private class MyDispatcher implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			return false;
		}
	}

	public VoiceListener(Game gamePanel) {
        this.gamePanel = gamePanel;
        //System.out.println("test");
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        new Thread(this.speechcommand = new SpeechCommand(this)).start();
    }
	
	private void setFocus() {
		gamePanel.setFingerFocus();
	}
	
	protected void keyPressed(int direction) {
		gamePanel.directionPerformed(direction);
		
	}
	
	

}

class SpeechCommand implements Runnable {
	
	static ConfigurationManager cm;
	static Recognizer recognizer;
	static Microphone microphone;
	VoiceListener voicelistener;
	
	public SpeechCommand(VoiceListener listenerInstance) {
		this.voicelistener = listenerInstance;
		cm = new ConfigurationManager(SpeechCommand.class.getResource("command.config.xml"));
		recognizer = (Recognizer) cm.lookup("recognizer");
	    recognizer.allocate();
	    // start the microphone or exit the program if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
        }
	}
	
	@Override
	public void run() {
		
		
		while (true) {
            //System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("Your command is: " + resultText + '\n');
                dispatchVoiceCommand(result);
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
	}
	
	private void dispatchVoiceCommand(Result result) {

		switch (result.getBestFinalResultNoFiller()) {
		case ("left"): voicelistener.keyPressed(1);break;
		case ("down"): voicelistener.keyPressed(2);break;
		case ("right"): voicelistener.keyPressed(3);break;
		case ("up"): voicelistener.keyPressed(4);break;
		// others?
		// case ("left"): voicelistener.keyPressed(1);break;
		default: break;
		}
		return;
	}
	
	
	
	
	/*
    public static void main(String[] args) {
        ConfigurationManager cm;

        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(SpeechCommand.class.getResource("command.config.xml"));
        }

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit the program if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

        System.out.println("Say: (top | down | left | right)");

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("Your command is: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }
	*/



	
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





