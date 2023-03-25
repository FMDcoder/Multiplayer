package Players;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

public class PlayerHandler {
	public LinkedList<Player> players = new LinkedList<>();
	
	public void addPlayer(Player plr) {
		players.add(plr);
	}
	
	public void removePlayer(String name) {
		try {
			players.remove(getPlayer(name));
		} catch (Exception e) {
		}
	}
	
	public void tickPlayer() {
		for(Player plr: players) {
			plr.tick();
		}
	}
	
	public void renderPlayer(Graphics2D g2, MainPlayer mplr) {
		Point relativ = new Point(
				(int)(mplr.x),
				(int)(mplr.y)
		);
		
		for(Player plr: players) {
			plr.render(g2, relativ);
		}
	}
	
	public Player getPlayer(String name) {
		for(Player plr: players) {
			if(plr.name.equals(name)){
				return plr;
			}
		}
		return null;
	}
	
	public void setValues(String name, int x, int y) {
		Player plr = getPlayer(name);
		if(plr != null) {
			plr.X = x;
			plr.Y = x;
		}
	}
	
	public boolean isUniqueName(String name) {
		return getPlayer(name) == null;
	}
}
