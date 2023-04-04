package Scene;

import java.awt.image.*;

public class SceneSizer {
	public final int[] RATIO = {16, 9};
	
	public int cX = 0, cY = 0, width = 0, height = 0;
	private float ratio = 1;
	
	public int convertValueWithRatio(int value) {
		return (int)(ratio * value);
	}
	
	public int convertValueWithRatio(float value) {
		return (int)(ratio * value);
	}
	
	public int convertValueWithRatio(double value) {
		return (int)(ratio * value);
	}
	
	public BufferedImage bf = null;
	
	public void tick(int w, int h) {
		int windowOptionsHeight = 0;
		double
			ratioWidth = w / RATIO[0],
			ratioHeight = h / RATIO[1];
		
		double smallestRatio = Math.min(ratioWidth, ratioHeight);
		width = (int)(smallestRatio * RATIO[0]);
		height = (int)(smallestRatio * RATIO[1]);
		
		cX = (w - width) >> 1;
		cY = (h - height + windowOptionsHeight) >> 1;
		
		ratio = (width * height) / 250000;
	}
}
