package Server;

import Players.Player;
import Scene.Playground;
import engine.Main;

public class Package {
	
	public static String pack(String... args) {
		return String.join(":", args);
	}
	
	public void interperate(String line) {
		String[] data = line.split(":");
		
		Playground play = (Playground)Main.sh.getSelectedScene();
		
		switch(data[0]) {
		case "USERINFO":
			try {
				float 
					x = Float.valueOf(data[2]),
					y = Float.valueOf(data[3]);
				
				Player plr = new Player(data[1], x, y);
				play.plrhandler.handlePlayer(plr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "DISCONNECT":
			
			play.plrhandler.removePlayer(data[2]);
						if(data[1].equals("HEADCLIENT")) {
				Main.headServer.closeConnection();
				Main.sh.selectScene("FrontPage");
			}
			break;
		}
	}
}
