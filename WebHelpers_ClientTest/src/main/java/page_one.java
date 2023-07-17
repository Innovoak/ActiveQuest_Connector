import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class page_one implements ActionListener {

	JFrame createAccount = new JFrame();
	JPanel createAccountPanel = new JPanel();
	JButton create_account = new JButton("Create Account");
	JButton go_to_login_page = new JButton("Login");

	JLabel create_account_label = new JLabel("Create Account");
	
	JLabel name = new JLabel("name");
	JLabel address = new JLabel("address");
	JLabel email = new JLabel("email");
	JLabel age = new JLabel("age");

	JTextField name_text = new JTextField(50);
	JTextField address_text = new JTextField(50);
	JTextField email_text = new JTextField(50);
	JTextField age_text = new JTextField(50);


	public page_one() {
		// TODO Auto-generated method stub
		
		//creating frame and panel for 'create account' page
		createAccount.setSize(300,300);
		createAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		createAccount.add(createAccountPanel);
		createAccountPanel.setLayout(null);
		
		//Labels
		create_account_label.setBounds(100,15,200,25);
		createAccountPanel.add(create_account_label);
		
		name.setBounds(20,50,100,25);
		createAccountPanel.add(name);
		
		address.setBounds(20,85,100,25);
		createAccountPanel.add(address);
		
		email.setBounds(20,120,100,25);
		createAccountPanel.add(email);
		
		age.setBounds(20,155,100,25);
		createAccountPanel.add(age);
		
		//Textfields
		name_text.setBounds(80,50,165,25);
		createAccountPanel.add(name_text);
		
		address_text.setBounds(80,85,165,25);
		createAccountPanel.add(address_text);
		
		email_text.setBounds(80,120,165,25);
		createAccountPanel.add(email_text);
		
		age_text.setBounds(80,155,165,25);
		createAccountPanel.add(age_text);
		
		
		//create_account button
		create_account.setBounds(20,190,150,25);
		createAccountPanel.add(create_account);
		create_account.addActionListener(null);
				
		//already signed up button
		JLabel already_signed_up = new JLabel("Already Have an Account?");
		already_signed_up.setBounds(20,220,165,25);
		createAccountPanel.add(already_signed_up);
		
		go_to_login_page.setBounds(190,220,75,25);
		createAccountPanel.add(go_to_login_page);
		go_to_login_page.addActionListener(this);
		
		
		createAccount.setVisible(true);
		createAccountPanel.setVisible(true);
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == go_to_login_page)
		createAccount.dispose();
		createAccount.setVisible(false);
		login_page login_page = new login_page();
		
		
	}

}