package Interaction;

import engine.Main;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Main.sh.getSelectedScene().mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Main.sh.getSelectedScene().mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Main.sh.getSelectedScene().mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Main.sh.getSelectedScene().mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Main.sh.getSelectedScene().mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Main.sh.getSelectedScene().mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Main.sh.getSelectedScene().mouseMoved(e);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Main.sh.getSelectedScene().mouseWheelMoved(e);
	}

}
