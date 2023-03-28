package Subscene;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.Main;

public class Window extends Subscene {
	
	private long started;
	private Rectangle shape;
	
	public Window(String title, String message, float lifetime) {
		
		this.started = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		shape = new Rectangle(
				(int)(Main.SCS.cX + Main.SCS.width * 0.3),
				(int)(Main.SCS.cY + Main.SCS.height * 0.3)
		)
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}
}
