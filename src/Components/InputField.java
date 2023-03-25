package Components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.Main;

public class InputField extends Component{

	public String 
				text = "",
				regex = "",
				placeholder = "";
	
	public Color 
		background = new Color(0,0,0,0),
		border = new Color(230, 230, 230),
		textColor = new Color(230, 230, 230),
		placeholderTextColor = new Color(200, 200, 200),
		
		hoverBackground = new Color(0,0,0,0),
		hoverBorder = new Color(170, 170, 170),
		hoverTextColor = new Color(170, 170, 170),
		hoverPlaceholderTextColor = new Color(140, 140, 140),
		
		focusedBackground = new Color(0,0,0,0),
		focusedBorder = new Color(110, 110, 110),
		focusedTextColor = new Color(110, 110, 110),
		focusedplaceholderTextColor = new Color(80, 80, 80);
	
	private boolean focused = false, hovered = false;
	
	private float x, y, w, h;
	private Rectangle 
		bounds = new Rectangle(),
		rectangle = new Rectangle();
	
	private Point 
		ball2 = new Point(),
		fontposition = new Point();
	
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	private FontMetrics fm;
	
	public InputField (String placeholder, String regex, 
			float procX, float procY, float procWidth, float procHeight) {
		
		this.placeholder = placeholder;
		this.regex = regex;
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
		
		fontposition.x = bounds.x + (bounds.height >> 1);
		fontposition.y = (int)(bounds.y + fm.getHeight());
	}
	
	public void renderShape(Graphics2D g2, Color c0, Color c1, Color c2, Color c3) {
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
		
		Shape shape = g2.getClip();
		g2.setClip(new Rectangle(
				bounds.x + (bounds.height >> 1), 
				bounds.y, 
				bounds.width - bounds.height, 
				bounds.height)
		);
		if(text.length() > 0) {
			g2.setColor(c2);
			int s = Math.max(fm.stringWidth(text) - bounds.width + bounds.height, 0);
			g2.drawString(text, fontposition.x - s, fontposition.y);
		}
		else {
			g2.setColor(c3);
			int s = Math.max(fm.stringWidth(placeholder) - bounds.width + bounds.height, 0);
			g2.drawString(placeholder, fontposition.x - s, fontposition.y);
		}
		
		g2.setClip(shape);
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setFont(font);
		fm = g2.getFontMetrics();
		
		Stroke oldStroke = g2.getStroke();
		
		g2.setStroke(new BasicStroke(Main.SCS.width * 0.008f));
		
		if(focused) {
			renderShape(g2, focusedBackground, focusedBorder, focusedTextColor, focusedplaceholderTextColor);
		} else if (hovered) {
			renderShape(g2, hoverBackground, hoverBorder, hoverTextColor, hoverPlaceholderTextColor);
		} else {
			renderShape(g2, background, border, textColor, placeholderTextColor);
		}
		
		g2.setStroke(oldStroke);
	}
	
	public boolean matchesRegex() {
		return (text.matches(regex));
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
		focused = hovered;
	}
	
	public void keyPressed(KeyEvent e) {
		if(focused) {
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_BACK_SPACE:
					text = text.substring(0,Math.max(text.length() - 1, 0));
					break;
				default:
					if(e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
						break;
					}
					text += e.getKeyChar();
					break;
			}
		}
	}
}
