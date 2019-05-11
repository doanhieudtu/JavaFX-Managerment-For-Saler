package Entity;

public class Authentication {
	private static 	String name;
	private static  String pass;
	static void Constructor() {
		name= "abc";
		pass= "456";
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Authentication.name = name;
	}
	public static String getPass() {
		return pass;
	}
	public static void setPass(String pass) {
		Authentication.pass = pass;
	}
	
}
