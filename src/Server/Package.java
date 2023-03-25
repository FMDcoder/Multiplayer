package Server;

public class Package {
	
	public static String pack(String... args) {
		return String.join(":", args);
	}
	
	public void interperate(String line) {
		System.out.println(line);
	}
}
