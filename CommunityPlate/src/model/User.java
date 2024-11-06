package model;

public class User {



	private String username;
	private String password;
	// either a donor or recipient
	private String role;

	private int[] ratingMap;
	private int[] rating;
	private int ratingCount;
	private final int MAX_RATING = 1000;

	private String status;

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.ratingMap = new int[5]; // For ratings 1 to 5
		this.rating = new int[MAX_RATING];
		this.ratingCount = 0;
	}

	// Getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	// Method to authenticate a user
	public boolean authenticate(String passwordEntered) {
		if (!password.equals(passwordEntered)) {
			status = "Password entered is wrong. Please try again!";
			return false;
			
		} else {
			status = username + " has successfully been authenticated";
			return true;
		}
	}

	public void setUsername(String username) throws InvalidNameException {
//	    Username must be at least five characters long and start with a letter
//	    Only letters and numbers can be used; no special characters, spaces nor symbols
		
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9]{4,}$";

		if (!username.matches(regex)) {
			throw new InvalidNameException("Invalid username. Please try again! ");
		} else {
			this.username = username;
			status = "Username: " + username + " has successfully been created";
		}
	}

	public void setPassword(String password) throws InvalidPasswordException {
		
		//	   Password requirements:
		//     At least 12 characters long 
		//	   A combination of uppercase letters, lowercase letters, numbers, and symbols.
		
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{12,}$";
		
		if (!password.matches(regex)) {
			throw new InvalidPasswordException("Invalid password. Please enter a new one! ");
		} else {
			this.password = password;
			status = "Password: " + password + " has successfully been set";
		}
	}
	
	public void changePassword(String oldPassword, String newPassword) throws InvalidPasswordException {
		if(!authenticate(oldPassword)) {
			status = "Old password is incorrect. Please try again!";
			
		}else {
			setPassword(newPassword);
			
			
		}
		
		
	}
	
	
	public void setRole(String newRole) throws InvalidRoleException{
		if((!(newRole.equals("Donor") ||newRole.equals("Recipient"))) ) {
			throw new InvalidRoleException("Role must be either donor or recipient ");
			
		}
		else {
			this.role = newRole;
		}
	}
	
	
	
	
	
	

	public void addRating(int newRating) {
		if (newRating >= 1 && newRating <= 5 && ratingCount < MAX_RATING) {
			rating[ratingCount] = newRating;
			ratingCount++;
		}
	}

	public void submitRating(int newRating) {
		if (newRating >= 1 && newRating <= 5) {
			ratingMap[newRating - 1]++;
			status = username + " has this rating distribution: ";
			
			status += "[";
			for(int i = 0; i < 5; i++) {
				status += "Score " + (i+1) + ": ";
				status += ratingMap[i];
				if(i != 4) {
					status += ", ";
				}
			}
		}
	}

	public String getAverageRating() {
		String ratingStatus = "";
		
		if (ratingCount == 0) {
			ratingStatus = "No ratings available yet! ";
		}

		double sum = 0.0;
		for (int i = 0; i < ratingCount; i++) {
			sum += rating[i];
		}
		double average = sum / ratingCount;
		ratingStatus =  String.format("%.1f", average);
		status = "New average rating for " + username + "is" + ratingStatus;
		
		return ratingStatus;
		
		
	}
	
	public String displayInfo() {
		return "Username: " + username + ", Role: " + role + ", Average rating: " + getAverageRating();
	}
	
	
	
	

	@Override
	public String toString() {
		return status;
	}
}

