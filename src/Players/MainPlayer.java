package Players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import Server.Package;
import engine.Main;

public class MainPlayer {
	public String name;
	public double x, y, vx, vy;

	public void tick() {
		x += vx;
		y += vy;
		
		String output = Package.pack(
				"USERINFO", this.name, String.valueOf(x), String.valueOf(y));
		
		Main.headServer.sendMessage(output);
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2.fillRect(
				240,
				240,
				40, 40);
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
