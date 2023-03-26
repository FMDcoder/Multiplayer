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
		
		System.out.println(data[0]);
		if(data[0].equals("USERINFO")) {
			try {
				float 
					x = Float.valueOf(data[2]),
					y = Float.valueOf(data[3]);
				
				Player plr = new Player(data[1], x, y);
				
				Playground play = (Playground)Main.sh.getSelectedScene();
				play.plrhandler.handlePlayer(plr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
