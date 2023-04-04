package Subscene;

import java.awt.Graphics2D;

public abstract class Subscene {
	public abstract void tick();
	public abstract void render(Graphics2D g2);
	public abstract boolean needSelfDestruct();
}
