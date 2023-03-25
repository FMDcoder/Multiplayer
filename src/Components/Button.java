package Components;

import engine.Main;

import java.awt.*;
import java.awt.event.*;

public class Button extends Component implements Action {

	public Color 
		background = new Color(0,0,0,0),
		border = new Color(230, 230, 230),
		textColor = new Color(230, 230, 230),
		
		hoverBackground = new Color(0,0,0,0),
		hoverBorder = new Color(170, 170, 170),
		hoverTextColor = new Color(170, 170, 170),
		
		clickBackground = new Color(0,0,0,0),
		clickBorder = new Color(110, 110, 110),
		clickTextColor = new Color(110, 110, 110);
	
	private boolean
		hovered = false,
		clicked = false;
	
	public float x, y, w, h;
	public String text;
	
	private Rectangle 
			bounds = new Rectangle(),
			rectangle = new Rectangle();
	private Point 
				ball2 = new Point(),
				fontposition = new Point();
	
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	private FontMetrics fm;

	public Button(String text, float procX, float procY, float procWidth, float procHeight) {
		this.text = text;
		this.x = procX;
		this.y = procY;
		this.w = procWidth;
		this.h = procHeight;
	}
	
	@Override
	public void tick() {
		bounds.x = (int)(x * Main.SCS.width);
		bounds.y = (int)(y * Main.SCS.height);
		bounds.width = (int)(w * Main.SCS.width);
		bounds.height = (int)(h * Main.SCS.height);
		
		bounds.x = bounds.x - (bounds.width >> 1) + Main.SCS.cX;
		bounds.y = bounds.y - (bounds.height >> 1) + Main.SCS.cY;
		
		rectangle.x = bounds.x + (bounds.height >> 1);
		rectangle.y = bounds.y;
		
		ball2.x = bounds.width - bounds.height + bounds.x;
		ball2.y = bounds.y;
		
		rectangle.width = bounds.width - bounds.height + 1;
		rectangle.height = bounds.height;
		
		font = font.deriveFont(bounds.height * 0.55f);
		
		if(fm == null) {
			return;
		}
		
		fontposition.x = bounds.x + ((bounds.width - fm.stringWidth(text)) >> 1);
		fontposition.y = (int)(bounds.y + fm.getHeight());
	}
	
	public void renderShape(Graphics2D g2, Color c0, Color c1, Color c2) {
		g2.setColor(c0);
		g2.fillArc(
			bounds.x,
			bounds.y, 
			bounds.height, 
			bounds.height, 
			90, 
			180
		);
		g2.fillRect(
			rectangle.x, 
			rectangle.y, 
			rectangle.width, 
			rectangle.height
		);
		g2.fillArc(
			ball2.x,
			ball2.y, 
			bounds.height, 
			bounds.height, 
			270,
			180
		);
		
		g2.setColor(c1);
		g2.drawArc(
			bounds.x, 
			bounds.y, 
			bounds.height, 
			bounds.height, 
			90, 
			180
		);
		g2.drawLine(
			rectangle.x, 
			rectangle.y, 
			rectangle.x + rectangle.width, 
			rectangle.y
		);
		g2.drawLine(
			rectangle.x,
			rectangle.y + rectangle.height, 
			rectangle.x + rectangle.width, 
			rectangle.y + rectangle.height
		);
		g2.drawArc(
			ball2.x,
			ball2.y, 
			bounds.height, 
			bounds.height, 
			270, 
			180
		);
		g2.setColor(c2);
		g2.drawString(text, fontposition.x, fontposition.y);
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setFont(font);
		fm = g2.getFontMetrics();
		
		Stroke oldStroke = g2.getStroke();
		
		g2.setStroke(new BasicStroke(Main.SCS.width * 0.008f));
		
		if(clicked) {
			renderShape(g2, clickBackground, clickBorder, clickTextColor);
		} else if (hovered) {
			renderShape(g2, hoverBackground, hoverBorder, hoverTextColor);
		} else {
			renderShape(g2, background, border, textColor);
		}
		
		g2.setStroke(oldStroke);
	}
	
	public void mouseMoved(MouseEvent e) {
		int
			mx = e.getX(),
			my = e.getY();
		
		boolean 
			ix = mx >= bounds.x && mx <= bounds.x + bounds.width,
			iy = my >= bounds.y && my <= bounds.y + bounds.height;
			
			hovered = ix && iy;
	}
	
	public void mousePressed(MouseEvent e) {
		if(hovered) {
			clicked = true;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(hovered && clicked) {
			this.trigger();
		}
		clicked = false;
	}
}
