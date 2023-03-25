package Scene;

import java.awt.image.*;

public class SceneSizer {
	public final int[] RATIO = {16, 9};
	
	public int cX = 0, cY = 0, width = 0, height = 0;
	
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
	}
}
