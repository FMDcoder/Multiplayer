package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Sqaure extends ObjectShape {
	
	public Sqaure(int x, int y, int size) {
		this.shape = new Polygon();
		
		for(int xp = -1; xp < 2; xp += 2) {
			for(int yp = -1; yp < 2; yp += 2) {
				int pointX = (xp * size / 2) + x,
					pointY = (yp * size / 2) + y;
				
				shape.addPoint(pointX, pointY);
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fill(shape);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
