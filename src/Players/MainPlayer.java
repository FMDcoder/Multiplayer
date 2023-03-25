package Players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
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
				vy = -1;
				break;
			case KeyEvent.VK_S:
				vy = 1;
				break;
			case KeyEvent.VK_A:
				vx = -1;
				break;
			case KeyEvent.VK_D:
				vx = 1;
				break;
			default:
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				vy = 0;
				break;
			case KeyEvent.VK_S:
				vy = 0;
				break;
			case KeyEvent.VK_A:
				vx = 0;
				break;
			case KeyEvent.VK_D:
				vx = 0;
				break;
			default:
				break;
		}
	}
}
