package javaee.modular.application.api;

public class GreetingsMessage {
	
	private User user;
	
	private String message;

	public GreetingsMessage(User user, String message) {
		super();
		this.user = user;
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}
	
}
