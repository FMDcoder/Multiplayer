package Scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;

import Components.*;
import engine.Main;

public class FrontPage extends SceneClass {

	private boolean connetToServer = false, isError = false;
	
	private long errorStart = System.currentTimeMillis();
	
	private Title 
		title = new Title("Multiplayer", 0.5f, 0.25f, 0.08f),
		errorTitle = new Title("error", 0.5f, 0.3f, 0.03f);
		
	private Button 
					join = new Button("JOIN", 0.5f, 0.5f, 0.3f, 0.1f) {
						@Override
						public void trigger() {
							FrontPage fp = (FrontPage)Main.sh.getSelectedScene();
							fp.isError = false;
							fp.connetToServer = true;
						}
					},
					createServer = new Button("CREATE SERVER", 0.5f, 0.65f, 0.3f, 0.1f) {
						@Override 
						public void trigger() {
							FrontPage fp = (FrontPage)Main.sh.getSelectedScene();
							
							int port = Main.serverThread.createServer();
							//Main.client.connect(port);
							
							Main.sh.selectScene("Playground");
						}
					},
					joinServer = new Button("JOIN SERVER", 0.5f, 0.7f, 0.3f, 0.1f){
						@Override 
						public void trigger() {
							FrontPage fp = (FrontPage)Main.sh.getSelectedScene();
							
							if(fp.port.matchesRegex()&& fp.address.matchesRegex()) {
								
								int port = Integer.parseInt(fp.port.text);
								
								Main.serverThread.createServer(port);
								Main.client.connect(fp.address.text, port);
								
								Main.sh.selectScene("Playground");
								return;
							}
							fp.invokeError("Port or Address does not have the right format");
						}
					},
					backToMeny = new Button("Back", 0.8f, 0.85f, 0.2f, 0.1f) {
						@Override
						public void trigger() {
							FrontPage fp = (FrontPage)Main.sh.getSelectedScene();
							fp.connetToServer = false;
							fp.isError = false;
						}
					};
					
	private InputField
					port = new InputField("Port", "\\d+", 0.28f, 0.5f, 0.2f, 0.1f),
					address = new InputField("IP address", 
							"^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$", 0.62f, 0.5f, 0.4f, 0.1f);
	@Override
	public void setup() {
		errorTitle.color = new Color(230, 0, 0);
	}
	
	@Override
	public void disassemble() {
		connetToServer = false;
		isError = false;
	}
	
	public void tick() {
		title.tick();
		
		if(isError) {
			errorTitle.tick();
			if(System.currentTimeMillis() - errorStart > 5000) {
				isError = false;
			}
		}
		
		if(connetToServer) {
			port.tick();
			address.tick();
			joinServer.tick();
			backToMeny.tick();
			return;
		}
		join.tick();
		createServer.tick();
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(new Color(15,0,21));
		g2.fillRect(Main.SCS.cX,Main.SCS.cY, Main.SCS.width, Main.SCS.height);
		
		title.render(g2);
		
		if(isError) {
			errorTitle.render(g2);
		}
		
		if(connetToServer) {
			port.render(g2);
			address.render(g2);
			joinServer.render(g2);
			backToMeny.render(g2);
			return;
		}
		join.render(g2);
		createServer.render(g2);
		
	}
	
	public void invokeError(String str) {
		errorStart = System.currentTimeMillis();
		errorTitle.text = str;
		isError = true;
	}
	
	public void mouseMoved(MouseEvent e) {
		if(connetToServer) {
			port.mouseMoved(e);
			address.mouseMoved(e);
			joinServer.mouseMoved(e);
			backToMeny.mouseMoved(e);
			return;
		}
		join.mouseMoved(e);
		createServer.mouseMoved(e);
	}
	
	public void mousePressed(MouseEvent e) {
		if(connetToServer) {
			port.mousePressed(e);
			address.mousePressed(e);
			joinServer.mousePressed(e);
			backToMeny.mousePressed(e);
			return;
		}
		join.mousePressed(e);
		createServer.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(connetToServer) {
			joinServer.mouseReleased(e);
			backToMeny.mouseReleased(e);
			return;
		}
		join.mouseReleased(e);
		createServer.mouseReleased(e);
	}
	
	public void keyPressed(KeyEvent e) {
		if(connetToServer) {
			address.keyPressed(e);
			port.keyPressed(e);
			return;
		}
	}
}
