package com.innovoak.test.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Profile implements ActionListener{
	
	private JFrame Profile = new JFrame();
	private JPanel contentPane;
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
	private JTextField bio_textfield;
	private JCheckBox isStudent_checkbox;



	public Profile() {
		Profile = new JFrame();
		contentPane = new JPanel();
		Profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Profile.add(contentPane);
		contentPane.setLayout(null);
		
		Profile.setBounds(100, 100, 337, 424);
		

		
	
		
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
		
		JCheckBox isStudent_checkbox = new JCheckBox("");
		isStudent_checkbox.setEnabled(false);
		isStudent_checkbox.setBounds(85, 203, 93, 21);
		contentPane.add(isStudent_checkbox);
		
		JTextArea bio_textfield = new JTextArea();
		bio_textfield.setEditable(false);
		bio_textfield.setText("UW COMP ENG 2028  \r\n*Insert Aspirational quote*");
		bio_textfield.setBounds(20, 121, 262, 49);
		contentPane.add(bio_textfield);
		
		name_textfield = new JTextField();
		name_textfield.setEditable(false);
		name_textfield.setText("Anshul Huang");
		name_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name_textfield.setBounds(75, 55, 207, 19);
		contentPane.add(name_textfield);
		name_textfield.setColumns(10);
		
		email_textfield = new JTextField();
		email_textfield.setEditable(false);
		email_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email_textfield.setText("jeffrey.shah@hotmail.com");
		email_textfield.setBounds(75, 79, 207, 19);
		contentPane.add(email_textfield);
		email_textfield.setColumns(10);
		
		JComboBox dob = new JComboBox();
		dob.setEnabled(false);
		dob.setEditable(true);
		dob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dob.setToolTipText("Day");
		dob.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dob.setMaximumRowCount(31);
		dob.setBounds(243, 176, 45, 22);
		contentPane.add(dob);
	
		
		JComboBox mob = new JComboBox();
		mob.setEnabled(false);
		mob.setEditable(true);
		mob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mob.setModel(new DefaultComboBoxModel(new String[] {"1\t", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		mob.setToolTipText("Month");
		mob.setMaximumRowCount(12);
		mob.setBounds(191, 176, 45, 22);
		contentPane.add(mob);
		
		JComboBox yob = new JComboBox();
		yob.setEnabled(false);
		yob.setEditable(true);
		yob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yob.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005"}));
		yob.setToolTipText("Year");
		yob.setMaximumRowCount(100);
		yob.setBounds(108, 176, 75, 22);
		contentPane.add(yob);
		
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
		country_text.setText("Canada");
		country_text.setEditable(false);
		country_text.setBounds(118, 230, 164, 19);
		contentPane.add(country_text);
		country_text.setColumns(10);
		
		city_text = new JTextField();
		city_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		city_text.setText("Markham");
		city_text.setEditable(false);
		city_text.setBounds(118, 260, 164, 19);
		contentPane.add(city_text);
		city_text.setColumns(10);
		
		address_text = new JTextField();
		address_text.setText("69420 Bur Oak");
		address_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address_text.setEditable(false);
		address_text.setBounds(118, 287, 164, 19);
		contentPane.add(address_text);
		address_text.setColumns(10);
		
		zip_text = new JTextField();
		zip_text.setText("123 456");
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
		
		
	
		
		edit_profile_button.addActionListener(this);
		save_changes.addActionListener(this);
		contentPane.add(save_changes);
		
		
		Profile.setVisible(true);
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
		}

		if (e.getSource() == save_changes) {
			isStudent_checkbox.setEnabled(true);
			dob.setEnabled(true);
			mob.setEnabled(true);
			yob.setEnabled(true);
			save_changes.setEnabled(true);
		}
	}
}
