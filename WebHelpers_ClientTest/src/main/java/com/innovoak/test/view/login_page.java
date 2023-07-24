package com.innovoak.test.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import com.innovoak.util.webhelpers.RelationalMapper;
import com.innovoak.util.webhelpers.criteria.SelectCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.comparing.EqualsCriteria;
import com.innovoak.util.webhelpers.criteria.predicate.logical.AndCriteria;

public class login_page implements ActionListener {
	private JFrame login_page;
	private JTextField name_text;
	private JTextField address_text;
	private JTextField email_text;
	private JTextField age_text;
	JButton login = new JButton("Login");

	public login_page() {
		login_page = new JFrame();
		JPanel login_page_panel = new JPanel();
		login_page.setSize(300, 300);
		login_page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login_page.add(login_page_panel);
		login_page_panel.setLayout(null);

		// Labels
		JLabel create_account_label = new JLabel("Login");
		create_account_label.setBounds(100, 15, 200, 25);
		login_page_panel.add(create_account_label);

		JLabel name = new JLabel("name");
		name.setBounds(20, 50, 100, 25);
		login_page_panel.add(name);

		JLabel address = new JLabel("address");
		address.setBounds(20, 85, 100, 25);
		login_page_panel.add(address);

		JLabel email = new JLabel("email");
		email.setBounds(20, 120, 100, 25);
		login_page_panel.add(email);

		JLabel age = new JLabel("age");
		age.setBounds(20, 155, 100, 25);
		login_page_panel.add(age);

		// Textfields
		name_text = new JTextField(50);
		name_text.setBounds(80, 50, 165, 25);
		login_page_panel.add(name_text);

		address_text = new JTextField(50);
		address_text.setBounds(80, 85, 165, 25);
		login_page_panel.add(address_text);

		email_text = new JTextField(50);
		email_text.setBounds(80, 120, 165, 25);
		login_page_panel.add(email_text);

		age_text = new JTextField(50);
		age_text.setBounds(80, 155, 165, 25);
		login_page_panel.add(age_text);

		// create_account button
		
		login.setBounds(20, 190, 150, 25);
		login_page_panel.add(login);
		login.addActionListener(this);

		login_page.setVisible(true);
		login_page_panel.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == login) {
		login_page.dispose();
		login_page.setVisible(false);
		profile_page profile_page = new profile_page();
	
		
		}
		
//		String name = name_text.getText();
//		String address = name_text.getText();
//		String email = name_text.getText();
//		String age = name_text.getText();
//
//		UserClientRepository userClient = new UserClientRepository();
//		ProfileClientRepository profileClient = new ProfileClientRepository();
//		AddressClientRepository addressClient = new AddressClientRepository();
//
//		try {
//			List<User> users = userClient.getAllBy(new SelectCriteria(
//					new AndCriteria(
//							new AndCriteria(new EqualsCriteria("name", name), new EqualsCriteria("age", age)),
//							new AndCriteria(new EqualsCriteria("email", email), new EqualsCriteria("address", address)))));
//			
//			if (users.size() == 0)
//				JOptionPane.showMessageDialog(login_page, "ERROR", "Invalid Credentials", JOptionPane.ERROR_MESSAGE);
//			
//			User user = users.get(0);
//			// Get 1 to 1 profile
//			Profile profile = RelationalMapper.get___ToOne(profileClient, user, "profileID");
//			// Get 1 to many addresses
//			List<Address> addresses = RelationalMapper.get___ToMany(addressClient, Address.class, user, "userID");
//			// Get all addresses
//			List<Address> total = addressClient.getAll();
//			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

	}

}
