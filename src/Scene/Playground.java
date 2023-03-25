package Scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Components.*;
import Players.MainPlayer;
import Players.Player;
import Players.PlayerHandler;
import engine.Main;

public class Playground extends SceneClass{
	
	public MainPlayer plr = new MainPlayer();
	public PlayerHandler plrhandler = new PlayerHandler();
	
	public boolean intro = true, isError = false;
	private long errorStart = System.currentTimeMillis();
	
	public Title introTitle = new Title("Make a name!", 0.5f, 0.3f, 0.1f),
				errorTitle = new Title("error", 0.5f, 0.4f, 0.03f);
	
	public InputField nameInput = new InputField("Name", "\\w+", 0.5f, 0.6f, 0.7f, 0.15f);
	public Button submitName = new Button("Submit", 0.5f, 0.85f, 0.15f, 0.15f * (3 / 4f)) {
		@Override
		public void trigger() {
			Playground pg = (Playground)Main.sh.getSelectedScene();
			
			if(pg.nameInput.matchesRegex()) {
				if(pg.plrhandler.isUniqueName(nameInput.text)) {
					plr.name = nameInput.text;
					plrhandler.addPlayer(new Player(nameInput.text, 0.5f, 0.5f));
					intro = false;
				}
				return;
			}
			pg.invokeError("Name is only allowed to have letters and numbers!");
		}
	};
	
	@Override
	public void setup() {
		errorTitle.color = new Color(230, 0, 0);
	}

	@Override
	public void disassemble() {
		// TODO Auto-generated method stub
		intro = true;
	}
	
	public void invokeError(String s) {
		isError = true;
		errorStart = System.currentTimeMillis();
		errorTitle.text = s;
	}
	
	@Override
	public void tick() {
		if(isError) {
			errorTitle.tick();
			if(System.currentTimeMillis() - errorStart > 5000) {
				isError = false;
			}
		}
		if(intro) {
			introTitle.tick();
			nameInput.tick();
			submitName.tick();
			return;
		}
		plr.tick();
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(new Color(15,0,21));
		g2.fillRect(Main.SCS.cX, Main.SCS.cY, Main.SCS.width, Main.SCS.height);
		
		if(isError) {
			errorTitle.render(g2);
		}
		
		if(intro) {
			introTitle.render(g2);
			nameInput.render(g2);
			submitName.render(g2);
			return;
		}
		
		plrhandler.renderPlayer(g2, plr);
	}
	
	public void mouseMoved(MouseEvent e) {
		if(intro) {
			nameInput.mouseMoved(e);
			submitName.mouseMoved(e);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if(intro) {
			nameInput.mousePressed(e);
			submitName.mousePressed(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(intro) {
			submitName.mouseReleased(e);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(intro) {
			nameInput.keyPressed(e);
			return;
		}
		plr.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		if(intro) {
			return;
		}
		plr.keyReleased(e);
	}
}
