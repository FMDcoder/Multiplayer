package Subscene;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.Main;

public class Window extends Subscene {
	
	private String title, msg;
	private float lifetime;
	private long started;
	private Rectangle shape;
	
	public Window(String title, String message, float lifetime) {
		
		this.lifetime = lifetime;
		this.title = title;
		this.msg = message;
		
		this.started = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needSelfDestruct() {
		float timePassed = (System.currentTimeMillis() - this.started);
		return timePassed >= lifetime;
	}
}
