package model;

import java.time.LocalDate;


public class FoodItem {
	
	private String name;
	private String type; //ex: divided into 5 categories: 1.Grains, 2. Proteins 3.Fruit and Vegetables 4.Pantry staples 5. Diet restrictions
	private int quantity;
	private LocalDate expirationDate;
	private User donor;
	
	private String status; // e.g., "Available", "Requested", "Claimed"
	private User requesters []; // Keep track of users who have requested the food item
	private int requesterCount;
	
	
	private String update;
	
	
	
	
	public FoodItem(String name, String type, int quantity, LocalDate expirationDate, User donor) {
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.expirationDate = expirationDate;
		this.donor = donor;
		this.status = "Available"; // set status to available
		this.requesters = new User[quantity];
		update = "";
			
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getDonorUsername() {
		return donor.getUsername();
	}
	
	
	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	
	public User getDonor() {
		return donor;
	}
	
	
	
	//Implement method to request food
	//Remember: return true if food is available
	
	public void requestFood(User requester, int quantity) {
		
		if(requesterCount < this.quantity && quantity < this.quantity) {
			requesters[requesterCount] = requester;
			requesterCount++;
			status = "Requested";
			update = requester.getUsername() + " has successfully requested for " + name + " (quantity: " + quantity + ")";
			
			
			
			this.quantity = this.quantity - quantity;
			
			
		}
		
		else {
			
			update = "Sorry, quantity requested is bigger than quantity available.";
			
			
		}
		
		
		

	}
	// Only allow claiming if the claimer has requested it and the item is in "Requested" state
	
	public boolean claimFood(User requester) {
		for(int i = 0; i < requesterCount; i++) {
			if(status.equals("Requested") && requesters[i].equals(requester) ) {
				update = requester.getUsername() + " has successfully claimed " + name + " (quantity: " + quantity + ")";
				return true;
			}
			
		}
		return false;
		
	}
	
		
		
		//user can cancel request 
	public void cancelRequest(User requester, int quantity) {
		for(int i = 0; i < requesterCount; i++) {
			if(status.equals("Requested") && requesters[i].equals(requester)) {
				for(int j = i; j < requesterCount - 1; j++) {
					requesters[j] = requesters[j+1];
					
				}
				
				requesters[requesterCount - 1] = null;
				requesterCount--;
				update = requester.getUsername() + " has cancelled request for " + name;
				this.quantity += quantity;
				
				
			}
		}
	}
	
	
	
	
	
	//Update Quantity
//	Allow the donor to update the quantity of the food item if they have more or fewer items available.
	
	public void updateQuantity(int newQuantity) {
		if(newQuantity > 0) {
			
			int temp = this.quantity;
			this.quantity = newQuantity;
			update = "Quantity has been updated from: " + temp + " to: " + newQuantity;
		}
		else {
			this.quantity = 0;
			status = "Out Of Stock";
			update = "This item is out of stock";
			
		}
		
		
		
		
	}
	
	
	public boolean isExpired() {
		return expirationDate.isBefore(LocalDate.now());
	}
	
	//Return a list of users who have requested the food item.
	
	public User [] getRequestersList() {
		
		User [] temp = new User[requesterCount];
		status = "List of users that requested this item: [";
		for(int i = 0; i < requesterCount; i++) {
			temp[i] = requesters[i];
			
			status += temp[i].getUsername();
			if(i != requesterCount - 1) {
				status+= ", ";
			}
			
		}
		status += "]";
		
		
		
		
		
		
		
		return temp;
		
		
		
		
	}
	
	
	
	
	
	
	public String getItemInfo() {
		String result = "";
		result += "Name: " + name;
		result += "\nType: " + type;
		result += "\nCurrent Quantity: " + quantity;
		result += "\nExpiration Date is: " + expirationDate;
		result += "\nWas donated by: " + donor.getUsername();
		result += "\nStatus: " + status;
		
		return result;
	
	}
	
	
	@Override
	public String toString() {
		
		return update;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}