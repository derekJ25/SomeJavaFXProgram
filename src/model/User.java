package model;

public class User {
	
	private String name;
	private String username;
	private String password;
	private byte[] profilePicture;
	
	public User(String name, String username, String password, byte[] profilePicture) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.profilePicture = profilePicture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
	
}
