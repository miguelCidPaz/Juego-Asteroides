package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	
	private boolean[] keys = new boolean[256];
	
	public static boolean UP, LEFT, RIGHT, DISPARO;
	
	public KeyBoard() {
		UP = false;
		LEFT = false;
		RIGHT = false;
	}
	
	public void update() {
		UP=keys[KeyEvent.VK_UP];
		LEFT=keys[KeyEvent.VK_LEFT];
		RIGHT=keys[KeyEvent.VK_RIGHT];
		DISPARO =keys[KeyEvent.VK_P];
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		keys[arg0.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		keys[arg0.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
