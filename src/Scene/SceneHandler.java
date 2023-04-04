package Scene;
import java.awt.*;
import java.util.*;

import Subscene.Subscene;

public class SceneHandler {
	private HashMap<String, SceneClass> scenes = new HashMap<String, SceneClass>();
	private ArrayList<Subscene> subscenes = new ArrayList<>();
	
	private SceneClass selected = null;
	
	public SceneClass getScene(String key) {
		return scenes.get(key);
	}
	
	public void selectScene(String key) {
		if(!(selected == null)) {
			selected.disassemble();
		}
		selected = scenes.get(key);
		selected.setup();
	}
	
	public SceneClass getSelectedScene() {
		return selected;
	}
	
	public void addScene(String key, SceneClass scene) {
		scenes.put(key, scene);
	}
	
	public void addSubScene(Subscene sub) {
		subscenes.add(sub);
	}
	
	public void removeScene(String key) {
		scenes.remove(key);
	}
	
	public void tick() {
		if(selected == null) {
			return;
		}
		selected.tick();
		
		ArrayList<Integer> destroy = new ArrayList<>();
		
		for(int i = 0; i < subscenes.size(); i++) {
			Subscene sub = subscenes.get(i);
			sub.tick();
			if(sub.needSelfDestruct()) {
				destroy.add(i);
			}
		}
		
		for(int v: destroy) {
			subscenes.remove(v);
		}
	}
	
	public void render(Graphics2D g2) {
		if(selected == null) {
			return;
		}
		selected.render(g2);
		
		for(Subscene sub: subscenes) {
			sub.render(g2);
		}
	}
}
