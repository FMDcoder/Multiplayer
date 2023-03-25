package Players;

import java.awt.event.KeyEvent;
import engine.Main;

public class MainPlayer {
	public String name;
	public double x, y, vx, vy;

	public void tick() {
		x += vx;
		y += vy;
		
		String msg = Main.pack.packageData(
				"USERINFO",this.name, String.valueOf(x), String.valueOf(y));
		
		Main.serverThread.sendMessage(msg);
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				vx = -0.1;
				break;
			case KeyEvent.VK_S:
				vx = 0.1;
				break;
			case KeyEvent.VK_A:
				vy = -0.1;
				break;
			case KeyEvent.VK_D:
				vy = 0.1;
				break;
			default:
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				vx = 0;
				break;
			case KeyEvent.VK_S:
				vx = 0;
				break;
			case KeyEvent.VK_A:
				vy = 0;
				break;
			case KeyEvent.VK_D:
				vy = 0;
				break;
			default:
				break;
		}
	}
}
