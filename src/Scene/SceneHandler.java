package Scene;
import java.awt.*;
import java.util.*;

public class SceneHandler {
	private HashMap<String, SceneClass> scenes = new HashMap<String, SceneClass>();
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
	
	public void removeScene(String key) {
		scenes.remove(key);
	}
	
	public void tick() {
		if(selected == null) {
			return;
		}
		selected.tick();
	}
	
	public void render(Graphics2D g2) {
		if(selected == null) {
			return;
		}
		selected.render(g2);
	}
}
