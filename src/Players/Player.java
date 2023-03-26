package Players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import engine.Main;

public class Player{
	public float X, Y;
	public String name;
	
	private Point pos = new Point();
	
	public Player(String name, float x, float y) {
		this.name = name;
		this.X = x;
		this.Y = y;
	}

	public void tick() {
		pos.x = (int)(this.X * Main.SCS.width + Main.SCS.cX);
		pos.y = (int)(this.Y * Main.SCS.height + Main.SCS.cY);
	}
	
	public void render(Graphics2D g2, Point relativTo) {
		g2.setColor(Color.RED);
		g2.fillRect(
				relativTo.x - pos.x + Main.SCS.cX + (Main.SCS.width >> 1),
				relativTo.y - pos.y + Main.SCS.cY + (Main.SCS.height >> 1), 
				40, 40);
		
		System.out.println((relativTo.x - pos.x + Main.SCS.cX + (Main.SCS.width >> 1))+" ; "+
				(relativTo.y - pos.y + Main.SCS.cY + (Main.SCS.height >> 1)));
	}
}
