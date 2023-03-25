package engine;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import Interaction.Keyboard;
import Interaction.Mouse;
import Scene.*;
import Server.HeadServer;

public class Main extends JFrame implements Runnable {
	
	public int FPS = 0;
	
	public static HeadServer hs = new HeadServer();
	
	public static SceneSizer SCS = new SceneSizer();
	public static SceneHandler sh = new SceneHandler();
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void window() {
		this.setTitle("Multiplayer");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				hs.closeConnection();
		        System.exit(0);
		    }
		});
		
		this.addKeyListener(new Keyboard());
		
		Mouse mouseListener = new Mouse();
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.addMouseWheelListener(mouseListener);
		
		this.setVisible(true);
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
	}
	
	public void prepareObjects() { 
		sh.addScene("FrontPage", new FrontPage());
		sh.addScene("Playground", new Playground());
		sh.selectScene("FrontPage");
	}
	
	public Main() {
		prepareObjects();
		window();
		
		Thread t1 = new Thread(this);
		Thread t2 = new Thread(hs);
		
		t2.start();
		t1.start();
	}

	public void run() {
		float 
			deltaTick = 0f,
			deltaFrame = 0f;
		
		float SIXTY_PER_SECOND_CONST = 50 / 3f;
		
		int frames = 0;
		
		long 
			now = System.currentTimeMillis(),
			last = System.currentTimeMillis(),
			timer = System.currentTimeMillis();
		
		while(true) {
			now = System.currentTimeMillis();
			
			deltaTick += (now - last) / SIXTY_PER_SECOND_CONST;
			deltaFrame += (now - last) / SIXTY_PER_SECOND_CONST;
			
			last = System.currentTimeMillis();
			
			if(deltaTick > 1f) {
				tick();
				deltaTick--;
			}
			
			if(deltaFrame > 1f) {
				render();
				frames++;
				deltaFrame--;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				FPS = frames;
				frames = 0;
			}
		}		
	}
	
	public void tick() {
		SCS.tick(this.getWidth(), this.getHeight());
		sh.tick();
	}
	
	public void renderSettings(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			bs = this.getBufferStrategy();
		}
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		renderSettings(g2);
		
		sh.render(g2);
		
		g2.dispose();
		bs.show();
	}
}
