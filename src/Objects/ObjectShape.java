package Objects;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;

public abstract class ObjectShape {
	public Polygon shape;
	
	public abstract void render(Graphics2D g2);
	public abstract void tick();
	
	public boolean collide(Shape s1) {
		Area areaA = new Area(shape);
		areaA.intersect(new Area(s1));
		return !areaA.isEmpty();
	}
}
