package junit_test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import model.FoodItem;
import model.User;

public class TestFoodItem {
	
	
	@Test
	public void test_item_1() {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		LocalDate expiredDate = LocalDate.parse("2026-10-19"); 
		FoodItem item1 = new FoodItem("Rice", "Grains",5, expiredDate, carmella);
		
		assertEquals("Rice", item1.getName());
		assertEquals("Grains", item1.getType());
		assertEquals(5, item1.getQuantity());
		assertEquals(expiredDate, item1.getExpirationDate());
		assertEquals("cmell05", item1.getDonor().getUsername());
		
		
		
	}
	
	@Test
	public void test_item_2() {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		User joy = new User("joy123", "abcde*86", "Recepient");
		
		LocalDate expiredDate = LocalDate.parse("2026-10-19"); 
		FoodItem item1 = new FoodItem("Rice", "Grains",5, expiredDate, carmella);
		
		
		// when quantity requested is smaller or equal than quantity available
		item1.requestFood(joy, 3);
		
		assertEquals("joy123 has successfully requested for Rice (quantity: " + 3 + ")", item1.toString());
		
		assertEquals(2, item1.getQuantity());
		
		
		// when quantity requested is greater than quantity available
		item1.requestFood(joy, 3);
		assertEquals("Sorry, quantity requested is bigger than quantity available.", item1.toString());
		
		// when requester is sucessfull in claiming food item
		//claimer has requested it and the item is in "Requested" state
		assertTrue(item1.claimFood(joy));
		


		
		
		
	}
	
	@Test
	public void test_item_3() {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		User joy = new User("joy123", "abcde*86", "Recepient");
		
		LocalDate expiredDate = LocalDate.parse("2026-10-19"); 
		FoodItem item1 = new FoodItem("Rice", "Grains",5, expiredDate, carmella);
		
		
		// when quantity requested is smaller or equal than quantity available
		item1.requestFood(joy, 3);
		
		assertEquals("joy123 has successfully requested for Rice (quantity: " + 3 + ")", item1.toString());
		
		assertEquals(2, item1.getQuantity());
		
		
		item1.cancelRequest(joy, 3);
		
		assertEquals("joy123 has cancelled request for Rice", item1.toString());
		
		assertEquals(5, item1.getQuantity());
	
		


		
		
		
	}
	
	@Test
	public void test_item_4() {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		User joy = new User("joy123", "abcde*86", "Recepient");
		
		LocalDate expiredDate = LocalDate.parse("2026-10-19"); 
		FoodItem item1 = new FoodItem("Rice", "Grains",5, expiredDate, carmella);
		
		
		// when quantity requested is smaller or equal than quantity available
		item1.requestFood(joy, 3);
		
		assertEquals("joy123 has successfully requested for Rice (quantity: " + 3 + ")", item1.toString());
		
		assertEquals(2, item1.getQuantity());
		
		
		// Update quantity to 1
		item1.updateQuantity(1);
		assertEquals("Requested",item1.getStatus());
		assertEquals("Quantity has been updated from: 2 to: 1",item1.toString());
		assertEquals(1, item1.getQuantity());
		
		// Update quantity to 0
		item1.updateQuantity(0);
		
		assertEquals("Out Of Stock",item1.getStatus());
		assertEquals("This item is out of stock",item1.toString());
		assertEquals(0, item1.getQuantity());
		
		//check if item is expired
		assertFalse(item1.isExpired());

	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_item_5() {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		
		User joy = new User("joy123", "abcde*86", "Recepient");
		User mimi = new User("mimi88", "MypAssword77", "Recepient");
		User jackie = new User("jackie300", "Updated22", "Recepient");
		
		LocalDate expiredDate = LocalDate.parse("2026-10-19"); 
		
		FoodItem item1 = new FoodItem("Rice", "Grains", 10 , expiredDate, carmella);
		
		
		// Requester1
		item1.requestFood(joy, 3);
		assertEquals("joy123 has successfully requested for Rice (quantity: 3)", item1.toString());
		assertEquals(7 ,item1.getQuantity());
		
		//Requester 2
		item1.requestFood(mimi, 5);
		assertEquals("mimi88 has successfully requested for Rice (quantity: 5)", item1.toString());
		assertEquals(2 ,item1.getQuantity());
		
		//Requester 3 (failed because quantity requested is bigger than quantity available)
		item1.requestFood(jackie, 4);
		assertEquals("Sorry, quantity requested is bigger than quantity available.", item1.toString());
		assertEquals(2, item1.getQuantity());
		
		//get list of requesters based on the order of request
		
		User[] requesterList = {joy, mimi};
		
		assertEquals(requesterList, item1.getRequestersList());
		
		assertEquals("List of users that requested this item: [joy123, mimi88]", item1.getStatus());
		
		assertEquals("Name: Rice\n"
				+ "Type: Grains\n"
				+ "Current Quantity: 2\n"
				+ "Expiration Date is: 2026-10-19\n"
				+ "Was donated by: cmell05\n"
				+ "Status: List of users that requested this item: [joy123, mimi88]", item1.getItemInfo());

	}
	

}
