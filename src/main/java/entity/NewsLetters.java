package entity;

public class NewsLetters {
	String email;
	boolean enabled;

	public NewsLetters() {
	}

	public NewsLetters(String email, boolean enabled) {
		this.email = email;
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
