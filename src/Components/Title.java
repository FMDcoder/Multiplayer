package Components;

import java.awt.*;

import engine.Main;

public class Title extends Component {

	private Font font;
	private FontMetrics fm;
	
	private float x, y, h;
	public String text;
	private Point finalshape = new Point();
	
	public Color color = Color.WHITE;
	
	public Title(String text, float procX, float procY, float procSize) {
		this.text = text;
		this.x = procX;
		this.y = procY;
		this.h = procSize;
	}
	
	@Override
	public void tick() {
		int heightFont = (int)(Main.SCS.width * h);
		font = new Font(Font.SANS_SERIF, Font.BOLD, heightFont);
		
		if (fm == null) {
			return;
		}
		
		finalshape.x = (int)(Main.SCS.width * x - (fm.stringWidth(text) >> 1)) + Main.SCS.cX;
		finalshape.y = (int)(Main.SCS.height * y - (fm.getHeight() >> 1)) + Main.SCS.cY;
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setFont(font);
		fm = g2.getFontMetrics();
		
		g2.setColor(color);
		g2.drawString(text, finalshape.x, finalshape.y);
	}

}
