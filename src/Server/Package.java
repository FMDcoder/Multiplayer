package Server;

public class Package {
	public void interperate(String line) {
		
	}
	
	public String packageData(String... data) {
		String result = "";
		
		for(int i = 0; i < data.length - 1; i++) {
			result += data[i]+";";
		}
		result += data[data.length - 1];
		
		return result;
	}
}
