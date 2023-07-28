package com.innovoak.test.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.innovoak.test.webhelpers.client.model.Address;
import com.innovoak.test.webhelpers.client.model.User;
import com.innovoak.test.webhelpers.client.repository.AddressClientRepository;
import com.innovoak.test.webhelpers.client.repository.ProfileClientRepository;
import com.innovoak.test.webhelpers.client.repository.UserClientRepository;
import com.innovoak.util.webhelpers.RelationalMapper;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.logical.AndCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.logical.NotCriteria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class profile_page implements ActionListener{
	
	private JFrame showProfile = new JFrame();
	private JPanel contentPane = new JPanel();
	private JTextField name_textfield;
	private JTextField email_textfield;
	private JTextField textField;
	private JTextField country_text;
	private JTextField city_text;
	private JTextField address_text;
	private JTextField zip_text;
	private JButton edit_profile_button = new JButton("Edit Profile");
	private JButton save_changes = new JButton("Save Changes");
	private JComboBox dob;
	private JComboBox yob;
	private JComboBox mob;
	private JTextArea bio_textfield;
	private JCheckBox isStudent_checkbox;
	private String name;
	private String username;
	private Date dateOfBirth; 
	private String email; 
	private String country; 
	private String city;
	private String streetAddress;
	private String zip; 
	private Boolean isStudent; 
	private String bio; 
	private JButton deleteAccount;
	com.innovoak.test.webhelpers.client.model.Profile profile; 
	Address address1;
	User user;
	private String id; 
	
	UserClientRepository userClient = new UserClientRepository();
	ProfileClientRepository profileClient = new ProfileClientRepository();
	AddressClientRepository addressClient = new AddressClientRepository();
	
	public profile_page(String profileID) {

		id = profileID; 
		try {
			List<User> users = userClient.getAllBy(new SelectCriteria(new EqualsCriteria("profileID", profileID)));
			user = users.get(0);
			

			profile = RelationalMapper.get___ToOne(profileClient, user, "profileID");
			List<Address> addresses = RelationalMapper.get___ToMany(addressClient, Address.class, user, "userID");
			List<Address> total = addressClient.getAll();
			address1 = addresses.get(0);
			
			
			username = user.getUsername();
			name = profile.getName();
			bio = profile.getBio();
			isStudent = profile.isStudent();
			dateOfBirth = profile.getDob();
			country = address1.getCountry();
			city = address1.getCity();
			streetAddress = address1.getStreetAddress(); 
			zip = address1.getZip();
			
			
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showProfile.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		showProfile.setBounds(100, 100, 337, 449);
	
		
		JLabel profile_label = new JLabel("Profile");
		profile_label.setFont(new Font("Tahoma", Font.BOLD, 18));
		profile_label.setBounds(130, 10, 88, 22);
		contentPane.add(profile_label);
		
		JLabel name_label = new JLabel("Name");
		name_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name_label.setBounds(20, 56, 61, 13);
		contentPane.add(name_label);
		
		JLabel emaill_label = new JLabel("Email");
		emaill_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emaill_label.setBounds(20, 79, 45, 13);
		contentPane.add(emaill_label);
		
		JLabel bio_label = new JLabel("Bio");
		bio_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bio_label.setBounds(20, 102, 45, 13);
		contentPane.add(bio_label);
		
		JLabel dob_label = new JLabel("Date of Birth");
		dob_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dob_label.setBounds(20, 180, 95, 13);
		contentPane.add(dob_label);
		
		JLabel isStudent_label = new JLabel("isStudent");
		isStudent_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		isStudent_label.setBounds(20, 206, 78, 13);
		contentPane.add(isStudent_label);
		
		isStudent_checkbox = new JCheckBox("");
		isStudent_checkbox.setEnabled(false);
		isStudent_checkbox.setBounds(85, 203, 93, 21);
		contentPane.add(isStudent_checkbox);
		isStudent_checkbox.setEnabled(isStudent);
		
		
		bio_textfield = new JTextArea();
		bio_textfield.setEditable(false);
		bio_textfield.setText(bio);
		bio_textfield.setBounds(20, 121, 262, 49);
		contentPane.add(bio_textfield);
		
		name_textfield = new JTextField();
		name_textfield.setEditable(false);
		name_textfield.setText(name);
		name_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name_textfield.setBounds(75, 55, 207, 19);
		contentPane.add(name_textfield);
		name_textfield.setColumns(10);
		
		email_textfield = new JTextField();
		email_textfield.setEditable(false);
		email_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email_textfield.setText(email);
		email_textfield.setBounds(75, 79, 207, 19);
		contentPane.add(email_textfield);
		email_textfield.setColumns(10);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfBirth);
		
		dob = new JComboBox();
		dob.setEnabled(false);
		dob.setEditable(true);
		dob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dob.setToolTipText("Day");
		dob.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dob.setMaximumRowCount(31);
		dob.setBounds(243, 176, 45, 22);
		contentPane.add(dob);
		dob.setSelectedItem(cal.get(Calendar.DATE));
		
		
		mob = new JComboBox();
		mob.setEnabled(false);
		mob.setEditable(true);
		mob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mob.setModel(new DefaultComboBoxModel(new String[] {"1\t", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		mob.setToolTipText("Month");
		mob.setMaximumRowCount(12);
		mob.setBounds(191, 176, 45, 22);
		contentPane.add(mob);
		mob.setSelectedItem(cal.get(Calendar.MONTH));
		
		 yob = new JComboBox();
		yob.setEnabled(false);
		yob.setEditable(true);
		yob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yob.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005"}));
		yob.setToolTipText("Year");
		yob.setMaximumRowCount(100);
		yob.setBounds(108, 176, 75, 22);
		contentPane.add(yob);
		yob.setSelectedItem(cal.get(Calendar.YEAR));
		
		JLabel country_label = new JLabel("Country");
		country_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		country_label.setBounds(20, 233, 95, 13);
		contentPane.add(country_label);
		
		JLabel city_label = new JLabel("City");
		city_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		city_label.setBounds(20, 256, 78, 22);
		contentPane.add(city_label);
		
		JLabel street_address_label = new JLabel("Street Address");
		street_address_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		street_address_label.setBounds(20, 289, 95, 13);
		contentPane.add(street_address_label);
		
		JLabel zip_code_label = new JLabel("Zip Code");
		zip_code_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		zip_code_label.setBounds(20, 314, 95, 13);
		contentPane.add(zip_code_label);
		
		country_text = new JTextField();
		country_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		country_text.setText(country);
		country_text.setEditable(false);
		country_text.setBounds(118, 230, 164, 19);
		contentPane.add(country_text);
		country_text.setColumns(10);
		
		city_text = new JTextField();
		city_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		city_text.setText(city);
		city_text.setEditable(false);
		city_text.setBounds(118, 260, 164, 19);
		contentPane.add(city_text);
		city_text.setColumns(10);
		
		address_text = new JTextField();
		address_text.setText(streetAddress);
		address_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address_text.setEditable(false);
		address_text.setBounds(118, 287, 164, 19);
		contentPane.add(address_text);
		address_text.setColumns(10);
		
		zip_text = new JTextField();
		zip_text.setText(zip);
		zip_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		zip_text.setEditable(false);
		zip_text.setBounds(118, 314, 164, 19);
		contentPane.add(zip_text);
		zip_text.setColumns(10);
		
		
		edit_profile_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		edit_profile_button.setBounds(20, 346, 125, 21);
		contentPane.add(edit_profile_button);
		
		
		
		save_changes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		save_changes.setEnabled(false);
		save_changes.setBounds(148, 346, 134, 21);
		contentPane.add(save_changes);
		
		deleteAccount = new JButton("Delete Account");
		deleteAccount.addActionListener(this);
		deleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteAccount.setBounds(20, 378, 262, 21);
		contentPane.add(deleteAccount);
		
	
		
		edit_profile_button.addActionListener(this);
		save_changes.addActionListener(this);
		
		
		
		showProfile.setVisible(true);
		contentPane.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == edit_profile_button) {
			bio_textfield.setEditable(true);
			name_textfield.setEditable(true);
			email_textfield.setEditable(true);
			country_text.setEditable(true);
			city_text.setEditable(true);
			zip_text.setEditable(true);
			address_text.setEditable(true);
			dob.setEnabled(true);
			mob.setEnabled(true);
			yob.setEnabled(true);
			isStudent_checkbox.setEnabled(true);
			save_changes.setEnabled(true);
		}

		if (e.getSource() == save_changes) {
			bio_textfield.setEditable(false);
			name_textfield.setEditable(false);
			email_textfield.setEditable(false);
			country_text.setEditable(false);
			city_text.setEditable(false);
			zip_text.setEditable(false);
			address_text.setEditable(false);
			dob.setEnabled(false);
			mob.setEnabled(false);
			yob.setEnabled(false);
			isStudent_checkbox.setEnabled(false);
			save_changes.setEnabled(false);
			
			try {
				List<User> updatedUsers = userClient.getAllBy(new SelectCriteria
						(new AndCriteria 
								(new EqualsCriteria("email", email), 
										new NotCriteria
										(new EqualsCriteria("username", username)))));
				if(updatedUsers.size() == 0) {
					profile.setName(name_textfield.getSelectedText());
					profile.setBio(bio_textfield.getSelectedText());
					profile.setEmail(email_textfield.getSelectedText());
					address1.setCountry(country_text.getSelectedText());
					address1.setCity(city_text.getSelectedText());
					address1.setZip(zip_text.getSelectedText());				
					address1.setStreetAddress(address_text.getSelectedText());
					String date_string = yob.getSelectedItem() + "-" + mob.getSelectedItem() + "-" + dob.getSelectedItem();
					profile.setDob(Date.valueOf(date_string));
					profile.setStudent(isStudent_checkbox.isSelected());
					
					userClient.update(user, id);
					addressClient.update(address1, id);
					profileClient.update(profile, id);
					
				}else {
					JOptionPane.showMessageDialog(showProfile, "An account has already been created with this email.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == deleteAccount) {
			try {
				userClient.delete(id);
				addressClient.deleteAllBy(new EqualsCriteria ("id", id));
				profileClient.delete(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
