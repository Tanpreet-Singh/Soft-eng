package gui;

public class Users {

	String username;
	String password;
	int level;

	public Users(String username, String password, int level) {
		super();
		this.username = username;
		this.password = password;
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
