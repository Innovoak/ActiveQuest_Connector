# The Web Helper's connectivity API

The API is used to assist in creating distributed applications
THe API is broken down into 2 portions.
1. The Connectivity portion between a client and a server (aka Messaging API)
2. Database Access and Repository API

## 1. Connectivity API

In distributed applications, there will always be a requirement for standardizing the method of communication.
There are typically 2 endpoints in a distributed application.
1. Client Side - presentation layer
2. Server Side - controller and query layer

In our case since we are using Java for both client and server, we can rely on Java's built in Serialization to help turn our objects to binary form.
Our client side application will be built in Android (Java) and our server side will be comprimized of Java Servlet's to act as the server component
and MySQL to act as the database component. Server to Database is already handled by **JDBC** but Client to Server still needs to be implemented.
Natively, there will be an **HttpURLConnection** on the client connecting to the server and an **HttpServlet** handling this request and returning the
appropriate response. We can think of this as messaging. Client sends a message to server, server processes this message, then server sends a message back to client.

			HttpURLConnection                  Servlet
	Client <----------------> Message Object <-----------> Server

This approach is error prone if it is not wrapped in an Object. Hence, the creation of the following classes:
 - `com.innovoak.util.webhelpers.client.Message` - acts as data transfer object: has a name, action, description, and content
 - `com.innovoak.util.webhelpers.client.HttpMessageClient` - acts as sender: sends Message to Servlet via HttpURLConnection as request and receives a Message as response
 - `com.innovoak.util.webhelpers.client.MessageServlet` - acts as receiver: receives the Message and content and performs action based on type of Message sent. Returns a Message as a Response
 
These 3 classes form the foundation of the connectivity between client and server in this Library.


## 2. Database Access and Repository API

### Repository API
In distributed applications, there will always be a requirement for persisting or serializing data and reusing it.
Typically for applications which use SQL databases, they would use **JDBC** to connect to the database and have a Connection for every request sent by the Servlet (assuming this is for Server Side applications
although this need not be used for just Server side applications). However, when using this approach in long term and grand scale, it is not very efficient to keep creating 80 lines of code to perform CRUD operations
for every Model which will be persisted. This gave birth to the DAO design pattern in which for every model being persisted, there will be an associated data access object which performs these CRUD operations.
In our case, the foundation of the DAO pattern is in `com.innovoak.util.webhelpers.Repository` interface. It is important to note that Data Access does not mean specifically for Database Access but can also
be for accessing resources on the Net or a file, hence this is not in the data package and this is an interface.

Accessing resources from Server have already been implemented with both the `com.innovoak.util.webhelpers.client.ClientRepository<T>` and the `com.innovoak.util.webhelpers.server.RepositoryServlet<T>` classes.
Both classes are abstract classes to accomodate for the model being sent and both classes use the Connectivity features mentioned above (ClientRepository -> HttpMessageClient && RepositoryServlet -> MessageServlet).
For every ClientRepository there is on the client side, there must be a RepositoryServlet on the server side. Another thing to note is that RepositoryServlet does not provide any features which Repository has to offer.
This is because how the server wishes to persist the object is up to the developers and client's needs, providing a layer of abstraction.

The Repository API also uses a set of classes known as `Criteria`s. These classes specify conditions or clauses in which certain actions must be performed. These groups of classes were built with a heavy 
bias towards SQL more specifically MySQL as our database will be MySQL. Within the Criteria API, there are 2 kinds of Criteria: a **SelectCriteria** and a **PredicateCriteria**. A **SelectCriteria** has a **PredicateCriteria** but
also provides pagination features such as Limiting and Offset (this'll be used for select/getAllBy queries). A **PredicateCriteria** represents the conditions to perform an action. There are comparing operators and logical operators
for criteria. Logical Operators are things like "AND", "OR", "NOT" whereas Comparing Operators are things like =, <, >, <=, =>, <>, IN, LIKE, BETWEEN, etc. In other words, when you pass a criteria, it is represented as a tree where
the branches are Logical Operators and the leafs are the comparing operators.

### Database Connectivity

The most important part of an application isn't the application itself, but how the data is stored. In our case, this'll be done via MySQL database. The Data portion of the WebHelpers API contains a small ORM framework to assist
developers with persisting their Models into the database. Unlike other ORM frameworks, this one is very limited in terms of storing relationships, being able to accomodate multiple databases and managing the states of the tables.
The tables must be created before the execution of the application. The tables must exactly represent the java models present. All models must inherit the `com.innovoak.util.webhelpers.data.Model` class and must have their
serializable id stated.

To use the framework, you must get the Singleton instance of `com.innovoak.util.webhelpers.data.DatabaseService` via getInstance method, configure it with database configuration, and create the data source via `DatabaseService.open()`.
With the Database Service, you are able to create `DatabaseSession`s which act as a method of connecting to the database. The Database Session actually encapsulates the `java.sql.Connection` and is used to create database repositories.
The actual implementation of the Repository API occurs here. `DatabaseRepository` is an abstract class which implements all the database logic such as Select, Insert, Update, and Delete and makes use of the Criteria API. It internally uses the
Query API which serves as the barebones of the interaction with the database via JDBC. Queries from the Query API are executed by the DatabaseSession instance and once all the tasks required are complete, you must close the session. The DatabaseService automatically registers the Database Repositories by scanning the classpath on the initialization of the application so there is no registration needed.

As stated earlier, this framework does not handle relationships like other frameworks such as Hibernate does. As compensation, we suggest you use the `com.innovoak.util.webhelpers.RelationalMapper` class to assist in managing relationships.
This will work for ANY repository (ClientRepository, RepositoryServlet, DatabaseRepository) and will access the required values via reflection. This framework also gives you the opportunity for handling

Lastly the API also provides 2 features for configuring the use of the Database Connectivity portion of this application with the server portion. The first class is `com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet` (abstract) which receives the instance of `DatabaseService` and gets the repository associated with the class it represents (which means there must be a DatabaseRepository for every DatabaseRepositoryServlet). This class inherits the RepositoryServlet and encapsulates all the features provided by DatabaseRepository as a side effect of Java not being able to provide multiple inheritance.
The last portion of the usage is the ability to configure the database service on startup and shutdown of the server. We have provided a ServletContextListener called `DatabaseContextListener` which basically manages the lifecycle of the database service.


# Some sample code to get you started


Models

```
User.java

import java.util.Objects;

import com.innovoak.util.webhelpers.data.Model;
import com.innovoak.util.webhelpers.data.annotations.Column;

public class User extends Model {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String profileID;

	public User() {
	}

	public User(String username, String password, String profileID) {
		super();
		this.username = username;
		this.password = password;
		this.profileID = profileID;
	}

	public User(String id, String username, String password, String profileID) {
		super(id);
		this.username = username;
		this.password = password;
		this.profileID = profileID;
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

	@Column(columnName = "profile_id")
	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, profileID, username);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(profileID, other.profileID)
				&& Objects.equals(username, other.username);
	}

}

--------------------------------------------------

Profile.java

import java.sql.Date;
import java.util.Objects;

import com.innovoak.util.webhelpers.data.Model;

public class Profile extends Model {
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String bio;
	private Date dob;
	private boolean isStudent;

	public Profile() {
	}

	public Profile(String name, String email, String bio, Date dob, boolean isStudent) {
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.dob = dob;
		this.isStudent = isStudent;
	}

	public Profile(String id, String name, String email, String bio, Date dob, boolean isStudent) {
		super(id);
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.dob = dob;
		this.isStudent = isStudent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bio, dob, email, isStudent, name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(bio, other.bio) && Objects.equals(dob, other.dob) && Objects.equals(email, other.email)
				&& isStudent == other.isStudent && Objects.equals(name, other.name);
	}

}

------------------------------------------------------

Address.java

import java.util.Objects;

import com.innovoak.util.webhelpers.data.Model;

public class Address extends Model {
	private static final long serialVersionUID = 1L;

	private String city;
	private String country;
	private String zip;
	private String streetAddress;
	private String userID;

	public Address() {
	}

	public Address(String city, String country, String zip, String streetAddress, String userID) {
		this.city = city;
		this.country = country;
		this.zip = zip;
		this.streetAddress = streetAddress;
		this.userID = userID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(city, country, streetAddress, userID, zip);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(streetAddress, other.streetAddress) && Objects.equals(userID, other.userID)
				&& Objects.equals(zip, other.zip);
	}

}


```

Client

```
UserClientRepository.java

import java.net.MalformedURLException;
import java.net.URL;

import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.client.ClientRepository;

public class UserClientRepository extends ClientRepository<User> {

	// Keep repository URL
	@Override
	protected URL getRepositoryURL() throws MalformedURLException {
		return new URL("http://localhost:8080/WebHelpers/test/user");
	}

}

-----------------------------------------------------
ProfileClientRepository.java


import java.net.MalformedURLException;
import java.net.URL;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.client.ClientRepository;

public class ProfileClientRepository extends ClientRepository<Profile> {

	// Keep repository URL
	@Override
	protected URL getRepositoryURL() throws MalformedURLException {
		return new URL("http://localhost:8080/WebHelpers/test/profile");
	}

}

----------------------------------------------------------

AddressClientRepository

import java.net.MalformedURLException;
import java.net.URL;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.client.ClientRepository;

public class AddressClientRepository extends ClientRepository<Address> {

	// Keep repository URL
	@Override
	protected URL getRepositoryURL() throws MalformedURLException {
		return new URL("http://localhost:8080/WebHelpers/test/address");
	}

}

-------------------------------------------------------------

Example login method

void login(String username, String password) {
	UserClientRepository userRepo = new UserClientRepository();
	
	// Get users from repo by user name and password
	List<User> users = userRepo.getAllBy(
		new SelectCriteria(
			new AndCriteria(
				new EqualsCriteria("username", username),
				new EqualsCriteria("password", password))));
				
	if (users.isEmpty()) {
		Dialog.sendErrorMessage("Invalid Credentials");
		return;
	}
	
	// Ideally we should only get 1
	CurrentUser.user = users.get(0);

	NavigationManager.navigateTo("home-page");
}

-------------------------------------------------

Example view profile

void getProfile(User user) {
	
	ProfileClientRepository repository = new ProfileClientRepository();

	Profile profile = RelationalMapper.get___ToOne(repository, User::getProfileID);
	
	display(profile);

}

------------------------------------------------

Example view address: One to Many relationship

void getAddresses(User user) {
	AddressClientRepository repository = new AddressClientRepository();

	List<Address> address = RelationalMapper.get___ToMany(repository, Address.class, user, "user_id");
	
	showAddress(address);
}



```

Server - Context path: /WebHelpers/test/

```

UserRepository.java

import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.data.DatabaseRepository;
import com.innovoak.util.webhelpers.data.DatabaseSession;

// User Database repository
public class UserRepository extends DatabaseRepository<User> {

	// Constructor
	public UserRepository(DatabaseSession session) {
		super(session);
	}

	@Override
	public String getTableName() {
		return "user";
	}

	@Override
	public User newInstance() {
		return new User();
	}

}

-----------------------------------------------------

ProfileRepository.java

import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.util.webhelpers.data.DatabaseRepository;
import com.innovoak.util.webhelpers.data.DatabaseSession;

public class ProfileRepository extends DatabaseRepository<Profile> {

	public ProfileRepository(DatabaseSession session) {
		super(session);
	}

	@Override
	public String getTableName() {
		return "profile";
	}

	@Override
	public Profile newInstance() {
		return new Profile();
	}

}

-------------------------------------------------------------

AddressRepository.java

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.util.webhelpers.data.DatabaseRepository;
import com.innovoak.util.webhelpers.data.DatabaseSession;

public class AddressRepository extends DatabaseRepository<Address> {

	public AddressRepository(DatabaseSession session) {
		super(session);
	}

	@Override
	public String getTableName() {
		return "address";
	}

	@Override
	public Address newInstance() {
		return new Address();
	}

}

-------------------------------------------------------------

UserRepositoryServlet.java

import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class UserRepositoryServlet
 */
@WebServlet("/user")
public class UserRepositoryServlet extends DatabaseRepositoryServlet<User> {
	private static final long serialVersionUID = 1L;

	// Keep the users class here
	public UserRepositoryServlet() {
		super(User.class);
	}

}

--------------------------------------------------------------
ProfileRepositoryServlet.java

import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class UserRepositoryServlet
 */
@WebServlet("/profile")
public class ProfileRepositoryServlet extends DatabaseRepositoryServlet<Profile> {
	private static final long serialVersionUID = 1L;

	// Keep the users class here
	public ProfileRepositoryServlet() {
		super(Profile.class);
	}

}

----------------------------------------------------------------
AddressRepositoryServlet.java

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.util.webhelpers.server.DatabaseRepositoryServlet;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AddressRepositoryServlet
 */
@WebServlet("/address")
public class AddressRepositoryServlet extends DatabaseRepositoryServlet<Address> {
	private static final long serialVersionUID = 1L;

	public AddressRepositoryServlet() {
		super(Address.class);
	}

}

-----------------------------------------------------------------
ServletContextListener.java

import javax.servlet.annotation.WebListener;

import com.innovoak.util.webhelpers.data.Configuration;
import com.innovoak.util.webhelpers.data.Configuration.ConfigurationBuilder;
import com.innovoak.util.webhelpers.server.DatabaseContextListener;

@WebListener
public class ServletContextListener extends DatabaseContextListener {

	@Override
	public Configuration getConfiguration() {
		return ConfigurationBuilder.create().setDriverClassName("com.mysql.cj.jdbc.Driver")
				.setUrl("jdbc:mysql://localhost:3306/webhelpers").setUsername("root").setPassword("root")
				.setDefaultAutoCommit(false).setMaxIdle(15).setMinIdle(10).setMaxOpenPreparedStatements(150)
				.setPoolPreparedStatements(true).build();
	}

}

```