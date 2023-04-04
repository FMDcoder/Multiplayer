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
		pos.x = Main.SCS.convertValueWithRatio(this.X);
		pos.y = Main.SCS.convertValueWithRatio(this.Y);
	}
	
	public void render(Graphics2D g2, Point relativTo) {
		g2.setColor(Color.RED);
		g2.fillRect(
				pos.x - relativTo.x + Main.SCS.cX + (Main.SCS.width >> 1),
				pos.y - relativTo.y + Main.SCS.cY + (Main.SCS.height >> 1), 
				Main.SCS.convertValueWithRatio(40), 
				Main.SCS.convertValueWithRatio(40)
		);
	}
}
