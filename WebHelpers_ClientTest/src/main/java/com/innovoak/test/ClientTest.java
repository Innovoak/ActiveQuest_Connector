package com.innovoak.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.test.webhelpers.client.repository.AddressClientRepository;
import com.innovoak.test.webhelpers.client.repository.ProfileClientRepository;
import com.innovoak.test.webhelpers.client.repository.UserClientRepository;
import com.innovoak.util.webhelpers.RelationalMapper;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;

public class ClientTest {
	// Resources
	static User user;
	static UserClientRepository userRepo = new UserClientRepository();
	static ProfileClientRepository profileRepo = new ProfileClientRepository();
	static AddressClientRepository addressRepo = new AddressClientRepository();
	static Scanner input = new Scanner(System.in);
	// Formats the date
	static SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/DD");

	public static void main(String[] args) throws Exception {

		System.out.println("Application Starting");

		auth();

		System.out.println("User successfully logged in: " + user.getUsername());

		handleProfile();

		input.close();
	}

	public static void auth() throws Exception {
		// Give client options
		System.out.println("Choose one of the following");
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("3) Exit");

		// Get choice
		int choice = input.nextInt();

		// Check choices
		switch (choice) {
		case 1:
			doLogin();
			break;
		case 2:
			doRegister();
			break;
		case 3:
			doExit();
			break;
		default:
			System.err.println("Invalid option: " + choice);
			// Use recursion to loop back
			auth();
		}

	}

	// Exited
	private static void doExit() {
		input.close();
		System.exit(0);
	}

	public static void doLogin() throws Exception {
		// Get username
		System.out.println("Enter your username: ");
		String username = input.nextLine();

		// Get users by username
		List<User> users = userRepo.getAllBy(new SelectCriteria(new EqualsCriteria("username", username)));

		// Check if tehre are any users with the following username
		// If not, print an error message and redo login
		if (users.isEmpty()) {
			System.err.println("Invalid credientials: No user found with the following username");

			doLogin();
			return;
		}

		// Get the password
		System.out.println("Enter your password: ");
		String password = new String(System.console().readPassword());

		// If the password does not match, print error message and redo login
		if (!users.get(0).getPassword().equals(password)) {
			System.err.println("Invalid credientials: password is not valid");

			doLogin();
			return;
		}

		// Set current user as the head of the list
		user = users.get(0);
	}

	private static void doRegister() throws Exception {

		// Create the user
		user = createUser();
		Profile profile = createProfile();
		List<Address> addresses = createAddresses();

		// Set one to one and one to many values
		RelationalMapper.setOneToOne(user, "profileID", profile);
		RelationalMapper.setOneToMany(user, addresses, Address.class, "userID");

		// Persist the objects
		userRepo.insert(user);
		profileRepo.insert(profile);
		addressRepo.insertAll(addresses);
	}

	private static Profile createProfile() {
		Profile profile = new Profile();

		// Set the name
		profile.setName(ProfileCreator.newName());
		// Set the email
		profile.setEmail(ProfileCreator.newEmail());
		// Set the date of birth
		profile.setDob(ProfileCreator.newDob());
		// Set the bio
		profile.setBio(ProfileCreator.newBio());

		return profile;
	}

	private static List<Address> createAddresses() {
		List<Address> addresses = new LinkedList<>();

		boolean wantsMoreAddresses;
		do {

			Address address = new Address();
			// Set the country
			address.setCountry(AddressCreator.newCountry());

			// Set the city
			address.setCity(AddressCreator.newCity());

			// Set the country
			address.setStreetAddress(AddressCreator.newStreetAddress());

			// Set the zip
			address.setZip(AddressCreator.newZip());

			// Check if the user wants to add their address
			System.out.println("This is your address: " + address);
			System.out.println("Are you going to commit or rollback? (Y/N)");
			if (input.nextLine().equalsIgnoreCase("Y"))
				addresses.add(address);

			// Check if they want another address
			System.out.println("Would you like to add another address? (Y/N)");
			wantsMoreAddresses = input.nextLine().equalsIgnoreCase("Y");

		} while (wantsMoreAddresses);

		return addresses;
	}

	private static User createUser() throws Exception {
		User user = new User();

		// Create user stuff
		// Set username
		user.setUsername(UserCreator.newUsername());

		// Set password
		user.setPassword(UserCreator.newPassword());

		return user;
	}

	public static void handleProfile() throws Exception {

		System.out.println(
				"Would you like to: \n 1) View your profile\n 2) View your addresses\n 3) Delete your account\n 4) Exit");
		int choice = input.nextInt();

		switch (choice) {
		case 1:
			// View profile
			viewProfile();

			break;
		case 2:
			// View address
			break;
		case 3:
			// Remove all user data
			userRepo.delete(user.getId());
			profileRepo.delete(user.getProfileID());
			addressRepo.deleteAllBy(new EqualsCriteria("userID", user.getId()));

			// Deleted
			System.out.println("Successfully logged out");
			// Logout
			user = null;
			// Go back to auth
			auth();
			break;
		case 4:
			// Exit
			doExit();
			break;
		default:
			System.err.println("Invalid input");
			handleProfile();
		}
	}

	public static void viewProfile() throws Exception {
		// View profile
		Profile profile = RelationalMapper.getOneToOne(profileRepo, user::getProfileID);

		// Print out profile
		System.out.printf("Name: %s%n", profile.getName());
		System.out.printf("Email: %s%n", profile.getEmail());
		System.out.printf("Date of Birth: %s%n", format.format(profile.getDob()));
		System.out.printf("Bio: %s%n", profile.getBio());

		System.out.println("Would you like to: \n 1) Edit your profile \n 2) Go back \n 3) Exit");
		int choice = input.nextInt();

		switch (choice) {
		case 1:
			
			
			break;
		case 2:
			handleProfile();
			break;
		case 3:
			doExit();
			break;
		default:
			System.err.println("Invalid input");
			viewProfile();
		}
	}
	
	public static void viewAddresses() throws Exception {
		
	}
	
	// Gets a valid value from user
	public static <V> V getValid(Callable<V> callable) {

		// Value
		V value = null;

		// Perform this until valid
		do {
			try {
				// Perform this successfully
				value = callable.call();

				// and break out of loop
				break;
				// Otherwise catch error
			} catch (Exception e) {

				// Get message and print
				System.err.println(e.getMessage());

			}

			// Continue this until we break
		} while (true);

		// Return value
		return value;
	}

	static class UserCreator {
		public static String newUsername() throws Exception {
			return getValid(() -> {
				// Enter username
				System.out.println("Enter username (Must be at least 8 characters long AND NO SPACES)");
				String username = input.nextLine();

				// first check if username is the minimum characters
				if (username.trim().length() < 8 || username.contains(" "))
					throw new Exception("Username must be 8 characters long and must contain no spaces");

				// Get users by username
				List<User> users = userRepo.getAllBy(new SelectCriteria(new EqualsCriteria("username", username)));

				// Check if username is taken
				if (!users.isEmpty())
					throw new Exception("Username has been taken");

				return username;
			});
		}

		public static String newPassword() throws Exception {
			return getValid(() -> {

				// Enter username
				System.out.println("Enter password (Must be at least 8 characters long)");
				String password = input.nextLine();

				// first check if username is the minimum characters
				if (password.trim().length() < 8)
					throw new Exception("Password must be 8 characters long");

				return password;
			});
		}
	}

	static class ProfileCreator {
		public static String newName() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your name");
				String name = input.nextLine();

				// first check if username is the minimum characters
				if (name.trim().isEmpty())
					throw new Exception("Name must be provided");

				// Return name
				return name;
			});
		}

		public static String newEmail() {
			return getValid(() -> {

				// Get the email
				System.out.println("Enter your email");
				String email = input.nextLine();

				// first check if username is the minimum characters
				if (email.trim().isEmpty())
					throw new Exception("Email must be provided");

				// Check format of email
				if (!email.matches("[\\w]+@[\\w]+\\.[a-zA-Z]+"))
					throw new Exception("Incorrect email format");

				// Return name
				return email;
			});
		}

		public static Date newDob() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your date of birth");
				String dob = input.nextLine();

				// first check if username is the minimum characters
				if (dob.trim().isEmpty())
					throw new Exception("Name must be provided");

				// Return date
				return new Date(format.parse(dob).getTime());
			});
		}

		public static String newBio() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your bio");
				String bio = input.nextLine();

				// Return name
				return bio;
			});
		}

	}

	static class AddressCreator {

		public static String newCountry() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your country");
				String country = input.nextLine();

				// first check if country is the minimum characters
				if (country.trim().isEmpty())
					throw new Exception("Country must be provided");

				// Return name
				return country;
			});
		}

		public static String newStreetAddress() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your street address");
				String street = input.nextLine();

				// first check if country is the minimum characters
				if (street.trim().isEmpty())
					throw new Exception("Street address must be provided");

				// Return name
				return street;
			});
		}

		public static String newCity() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your city");
				String city = input.nextLine();

				// first check if country is the minimum characters
				if (city.trim().isEmpty())
					throw new Exception("City must be provided");

				// Return name
				return city;
			});
		}

		public static String newZip() {
			return getValid(() -> {

				// Get the name
				System.out.println("Enter your zip");
				String zip = input.nextLine();

				// first check if country is the minimum characters
				if (zip.trim().isEmpty())
					throw new Exception("Zip must be provided");

				// Return name
				return zip;
			});
		}

	}

}
