package Scene;
import java.awt.event.*;

public interface Listener {
	public default void keyTyped(KeyEvent e) {}
	public default void keyPressed(KeyEvent e) {}
	public default void keyReleased(KeyEvent e) {}
	public default void mouseClicked(MouseEvent e) {}
	public default void mousePressed(MouseEvent e) {}
	public default void mouseReleased(MouseEvent e) {}
	public default void mouseEntered(MouseEvent e) {}
	public default void mouseExited(MouseEvent e) {}
	public default void mouseDragged(MouseEvent e) {}
	public default void mouseMoved(MouseEvent e) {}
	public default void mouseWheelMoved(MouseWheelEvent e) {}
}
