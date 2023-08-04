package com.innovoak.test;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.test.webhelpers.client.repository.AddressClientRepository;
import com.innovoak.test.webhelpers.client.repository.ProfileClientRepository;
import com.innovoak.test.webhelpers.client.repository.UserClientRepository;
import com.innovoak.util.webhelpers.RelationalMapper;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;

public class ClientTest2 {

	private static String[] usernames = { "epicbird", "adityarao", "cooldood", "happyissad", "felixlovescoding" };
	private static String[] cities = { "Markham", "Stouffville" };
	private static String[] zips = { "L6E1Z9", "L6E1X1", "L6A209" };
	private static String[] addresses = { "216 Wesmina Road", "82 Miramar Dr", "281 Williamson Road",
			"1993 Bur Oak Ave" };
	private static String[] names = { "Aditya Rao", "Vyas", "Manav", "Happy", "Felix" };
	@SuppressWarnings("deprecation")
	private static Date[] dobs = { new Date(2005, 6, 15), new Date(2005, 10, 23), new Date(2005, 6, 8) };
	private static String[] bios = { "Cool gamer", "Math nerd", "Comp sci nerd", "Everything is life", "Gym rat" };
	private static String[] emails = { "random@gmail.com", "random2@gmail.com", "random3@gmail.com",
			"cooldood@hotmail.com" };

	public static Stream<Character> getRandomSpecialChars(int count, char startChar, char endChar) {
		Random random = new SecureRandom();
		IntStream specialChars = random.ints(count, startChar, endChar);
		return specialChars.mapToObj(data -> (char) data);
	}

	public static String generatePassword(int length) {
		List<Character> pwdChars = Stream.concat(getRandomSpecialChars(2, '0', ';'), Stream.concat(
				getRandomSpecialChars((length - 2) / 2, 'a', 'z'), getRandomSpecialChars((length - 2) / 2, 'A', 'Z')))
				.collect(Collectors.toList());

		Collections.shuffle(pwdChars);

		return pwdChars.stream().map(String::valueOf).collect(Collectors.joining());
	}

	static UserClientRepository userRepo = new UserClientRepository();
	static ProfileClientRepository profileRepo = new ProfileClientRepository();
	static AddressClientRepository addressRepo = new AddressClientRepository();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		// SUCCESS
//		create();

//		_break();
//
//		Profile profile = read();

//			_break();
//			update(profile);
//
//		_break();
//
		delete();

	}

	public static void create() throws Exception {
		// Create a bunch
		for (String username : usernames) {

			User user = createUser(username);
			Profile profile = createProfile();
			List<Address> addresses = new LinkedList<>();
			createAddresses(addresses);

			RelationalMapper.setOneToOne(user, "profileID", profile);
			RelationalMapper.setOneToMany(user, addresses, Address.class, "userID");

			// Create
			userRepo.insert(user);
			profileRepo.insert(profile);
			addressRepo.insertAll(addresses);
		}
	}

	public static Profile read() throws Exception {
		// Read
		System.out.println("Login:");

		User user = null;

		// Login username
		do {
			System.out.println("Enter your Username:");
			List<User> result = userRepo
					.getAllBy(new SelectCriteria(new EqualsCriteria("username", scanner.nextLine())));

			if (result.isEmpty())
				System.err.println("No user found");
			else
				user = result.get(0);
		} while (user == null);

		// Login password
		do {
			System.out.println("Enter your password:");

			if (user.getPassword().equals(scanner.nextLine()))
				break;
			else
				System.err.println("Incorrect password");
		} while (true);

		// Print user, profile and addresses
		Profile profile = RelationalMapper.getOneToOne(profileRepo, user::getProfileID);

		System.out.println("User:");
		System.out.println(user);
		System.out.println("Profile:");
		System.out.println(profile);
		System.out.println("Addresses:");
		RelationalMapper.getOneToMany(addressRepo, Address.class, user, "userID").forEach(System.out::println);

		return profile;
	}

	public static void update(Profile profile) throws Exception {
		// Update
		System.out.println("Lets change your bio");
		profile.setBio(scanner.nextLine());
		System.out.println(profile);
		profileRepo.update(profile, profile.getId());

	}

	public static void delete() throws Exception {

		// Delete
		userRepo.deleteAll();
		profileRepo.deleteAll();
		addressRepo.deleteAll();
	}

	private static void createAddresses(List<Address> addresses) {
		int nums = getRandom(new Integer[] { 1, 2, 3 });

		for (int i = 0; i < nums; i++) {
			addresses.add(
					new Address(getRandom(cities), "Canada", getRandom(zips), getRandom(ClientTest2.addresses), null));
		}
	}

	private static <T> T getRandom(T[] values) {
		Random random = new Random();

		return values[random.nextInt(values.length)];
	}

	private static Profile createProfile() {
		return new Profile(getRandom(names), getRandom(emails), getRandom(bios), getRandom(dobs), true);
	}

	private static User createUser(String user) {
		return new User(user, generatePassword(8), null);
	}

	public static void _break() {

		System.out.println("Press next to continue");
		scanner.nextLine();

	}
}
