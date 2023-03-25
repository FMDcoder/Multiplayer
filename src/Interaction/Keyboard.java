package Interaction;

import engine.Main;
import java.awt.event.*;

public class Keyboard implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		Main.sh.getSelectedScene().keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Main.sh.getSelectedScene().keyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Main.sh.getSelectedScene().keyReleased(e);
	}

}
