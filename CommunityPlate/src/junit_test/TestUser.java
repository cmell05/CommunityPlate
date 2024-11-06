package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.InvalidNameException;
import model.InvalidPasswordException;
import model.InvalidRoleException;
import model.User;

public class TestUser {

	@Test
	public void test_user_1() {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		
		assertEquals("cmell05",carmella.getUsername());
		assertEquals("Trust123*",carmella.getPassword());
		assertEquals("Donor",carmella.getRole());
		
		
		assertFalse( carmella.authenticate("abc"));
		assertTrue(carmella.authenticate("Trust123*"));
		
		carmella.authenticate("Trust123*");
		assertEquals("cmell05 has successfully been authenticated", carmella.toString());
		
		carmella.authenticate("InvalidPass");
		assertEquals("Password entered is wrong. Please try again!", carmella.toString());
		
		
		
		
		
	}
	
	@Test
	public void test_user_2()  {
		User carmella = new User("cmell05", "Trust123*", "Donor");

		try {
			carmella.setUsername("sprinkles999");
		} catch (InvalidNameException e) {
			
		}
		
		
		assertEquals("sprinkles999", carmella.getUsername());
		assertEquals("Username: sprinkles999 has successfully been created", carmella.toString());
		

		try {
			carmella.setUsername("888notvalid&");
		} catch (InvalidNameException e) {
			
		}

		
		assertThrows(InvalidNameException.class, () -> carmella.setUsername("888notvalid&"));
		assertNotEquals("888notvalid&", carmella.getUsername());
		assertEquals("sprinkles999", carmella.getUsername());
		
		
	}
	
	@Test
	public void test_user_3()  {
		User carmella = new User("cmell05", "Trust123*", "Donor");
	
		try {
			carmella.setPassword("NewValidPass!123");
		} catch (InvalidPasswordException e) {
			
		}

		assertEquals("NewValidPass!123", carmella.getPassword());
		assertEquals("Password: " + "NewValidPass!123" + " has successfully been set", carmella.toString());
		
		

		try {
			carmella.setPassword("invalidpass11");
		} catch (InvalidPasswordException e) {

		}

		
	}
	
	@Test
	public void test_user_4()   {
		User carmella = new User("cmell05", "Trust123*", "Donor");
	
		try {
			carmella.changePassword("Trust123*", "NewValidPass!123");
		} catch (InvalidPasswordException e) {
			
		}
		
		
		assertEquals("Password: NewValidPass!123 has successfully been set", carmella.toString());
		
		
		
		
	}
	@Test
	public void test_user_5()   {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		
		try {
			carmella.setRole("Recipient");
		} catch (InvalidRoleException e) {
		
		}
		
		assertEquals("Recipient", carmella.getRole());
		
		try {
			carmella.setRole("watcher");
		} catch (InvalidRoleException e) {
			
		}
		
//		assertThrows(InvalidRoleException.class, () -> carmella.getRole());


		
	}
	
	
	@Test
	public void test_user_6()   {
		User carmella = new User("cmell05", "Trust123*", "Donor");
		
		carmella.addRating(5);
		carmella.addRating(3);
		carmella.addRating(4);
		carmella.addRating(1);
		
		assertEquals("3.3", carmella.getAverageRating());		

		carmella.submitRating(3);
		carmella.submitRating(5);
		carmella.submitRating(5);
		
		assertEquals("cmell05 has this rating distribution: [Score 1: 0, Score 2: 0, Score 3: 1, Score 4: 0, Score 5: 2", carmella.toString());
		
		assertEquals("Username: cmell05, Role: Donor, Average rating: 3.3", carmella.displayInfo());

		
	}
	
	
	

	
	
	
	
	
	

}
