package Scene;

import java.awt.*;

public abstract class SceneClass implements Listener {
	public abstract void setup();
	public abstract void disassemble();
	public abstract void tick();
	public abstract void render(Graphics2D g2);
}
