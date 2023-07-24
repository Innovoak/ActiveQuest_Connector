import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.Profile;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.test.webhelpers.client.repository.AddressClientRepository;
import com.innovoak.test.webhelpers.client.repository.ProfileClientRepository;
import com.innovoak.test.webhelpers.client.repository.UserClientRepository;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.logical.AndCriteria;
import com.innovoak.util.webhelpers.data.query.InsertQuery;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class registerPage implements ActionListener {
	
	String dateOfBirth;
	JFrame createAccount = new JFrame();
	JPanel createAccountPanel = new JPanel();
	JButton create_account = new JButton("Create Account");
	JButton go_to_login_page = new JButton("Login");

	JLabel create_account_label = new JLabel("Create Account");
	
	JLabel name = new JLabel("name");
	JLabel username = new JLabel("username");
	JLabel email = new JLabel("email");

	JTextField name_text = new JTextField(50);
	JTextField username_text = new JTextField(50);
	JTextField email_text = new JTextField(50);
	
	private JTextField zip_text;
	private JTextField country_text;
	private JTextField city_text;
	private JTextField address_text;
	private JLabel bithday;
	private final JTextField password1_text = new JTextField(50);
	private final JLabel password1 = new JLabel("password");
	private String date_string;
	JComboBox dob = new JComboBox();
	JComboBox mob = new JComboBox();
	JComboBox yob = new JComboBox();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public registerPage() {
		// TODO Auto-generated method stub
		
		//creating frame and panel for 'create account' page
		createAccount.setSize(521,335);
		createAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		createAccount.getContentPane().add(createAccountPanel);
		createAccountPanel.setLayout(null);
		
		//Labels
		create_account_label.setBounds(216,11,75,25);
		createAccountPanel.add(create_account_label);
		
		name.setBounds(20,50,100,25);
		createAccountPanel.add(name);
		
		username.setBounds(20, 85, 100, 25);
		createAccountPanel.add(username);
		
		email.setBounds(20,160,100,25);
		createAccountPanel.add(email);
		
		//Textfields
		name_text.setBounds(80,50,165,25);
		createAccountPanel.add(name_text);
		
		username_text.setBounds(80, 85, 165, 25);
		createAccountPanel.add(username_text);
		
		email_text.setBounds(80,160,165,25);
		createAccountPanel.add(email_text);
		
		
		//create_account button
		create_account.setBounds(179,232,150,25);
		createAccountPanel.add(create_account);
		create_account.addActionListener(null);
				
		//already signed up button
		JLabel already_signed_up = new JLabel("Already Have an Account?");
		already_signed_up.setBounds(20,260,165,25);
		createAccountPanel.add(already_signed_up);
		
		go_to_login_page.setBounds(190,260,75,25);
		createAccountPanel.add(go_to_login_page);
		
		JLabel country = new JLabel("Country");
		country.setBounds(259, 51, 100, 25);
		createAccountPanel.add(country);
		
		zip_text = new JTextField(50);
		zip_text.setBounds(319, 161, 165, 25);
		createAccountPanel.add(zip_text);
		
		country_text = new JTextField(50);
		country_text.setBounds(319, 51, 165, 25);
		createAccountPanel.add(country_text);
		
		city_text = new JTextField(50);
		city_text.setBounds(319, 86, 165, 25);
		createAccountPanel.add(city_text);
		
		address_text = new JTextField(50);
		address_text.setBounds(319, 126, 165, 25);
		createAccountPanel.add(address_text);
		
		JLabel city = new JLabel("City");
		city.setBounds(259, 86, 100, 25);
		createAccountPanel.add(city);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(259, 126, 100, 25);
		createAccountPanel.add(addressLabel);
		
		JLabel zip = new JLabel("Zip");
		zip.setBounds(259, 161, 100, 25);
		createAccountPanel.add(zip);
		

		bithday = new JLabel("Dob");
		bithday.setBounds(20, 124, 30, 25);
		createAccountPanel.add(bithday);
		
		
		dob.setToolTipText("Day");
		dob.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dob.setMaximumRowCount(31);
		dob.setBounds(80, 127, 45, 22);
		createAccountPanel.add(dob);
		
		
		mob.setModel(new DefaultComboBoxModel(new String[] {"1\t", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		mob.setToolTipText("Month");
		mob.setMaximumRowCount(12);
		mob.setBounds(130, 127, 55, 22);
		createAccountPanel.add(mob);
		
		
		yob.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005"}));
		yob.setToolTipText("Year");
		yob.setMaximumRowCount(100);
		yob.setBounds(190, 127, 55, 22);
		createAccountPanel.add(yob);
		
		password1_text.setBounds(80, 196, 165, 25);
		
		createAccountPanel.add(password1_text);
		password1.setBounds(20, 196, 100, 25);
		
		createAccountPanel.add(password1);
		password2_text.setBounds(319, 196, 165, 25);
		
		createAccountPanel.add(password2_text);
		password2.setBounds(259, 197, 100, 25);
		
		createAccountPanel.add(password2);
		go_to_login_page.addActionListener(this);
		
		create_account.addActionListener(this);
	
		
		
;		
		createAccount.setVisible(true);
		createAccountPanel.setVisible(true);
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == go_to_login_page) {
			createAccount.dispose();
			createAccount.setVisible(false);
			login_page login_page = new login_page();
		}
	
		
		if(e.getSource() == create_account) {
			
			System.out.print("clicked create account");
			date_string = yob.getSelectedItem() + "-" + mob.getSelectedItem() + "-" + dob.getSelectedItem();
			String name = name_text.getText();
			String email = email_text.getText();
			String username = username_text.getText();
			String country = country_text.getText();
			String city = city_text.getText();
			String streetAddress = address_text.getText();
			String zip = zip_text.getText(); 
			String password1 = password1_text.getText();
			String password2 = password2_text.getText();
			String profileID = UUID.randomUUID().toString();
			String bio = null; 
			Boolean isStudent = null;
			Date date = Date.valueOf(date_string);
			
			UserClientRepository userClient = new UserClientRepository();
			ProfileClientRepository profileClient = new ProfileClientRepository();
			AddressClientRepository addressClient = new AddressClientRepository();
			
			if(name.isEmpty() || username.isEmpty() || email.isEmpty() ) {
				JOptionPane.showMessageDialog(createAccount, "Please enter all of the required fields", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
			try {
				List<Profile> profiles = profileClient.getAllBy(new SelectCriteria(new EqualsCriteria("email", email)));
				List<User> users = userClient.getAllBy(new SelectCriteria(new EqualsCriteria("username", username)));
				
				
				if (profiles.size() == 0 && users.size() == 0) {
					User user = new User(username, password1, profileID);
					userClient.insert(user);
					
					Profile profile = new Profile(name, email, bio, date, isStudent);
					profileClient.insert(profile);
					
					Address address = new Address(city, country, zip, streetAddress, profileID);
					addressClient.insert(address);
					
					createAccount.dispose();
					createAccount.setVisible(false);
					login_page loginPage = new login_page();
					
				}else if (profiles.size() == 0){
					JOptionPane.showMessageDialog(createAccount, "An account has already been created with this email.", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (users.size() == 0) {
					JOptionPane.showMessageDialog(createAccount, "An account with this username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
				}
					
				
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	public User user;
	private final JTextField password2_text = new JTextField(50);
	private final JLabel password2 = new JLabel("password");
	


	
}