import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login_page {


	
	public login_page() {
		// TODO Auto-generated method stub
		//creating frame and panel for 'create account' page
			JFrame login_page = new JFrame();
			JPanel login_page_panel = new JPanel();
			login_page.setSize(300,300);
			login_page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
			login_page.add(login_page_panel);
			login_page_panel.setLayout(null);
			
			//Labels
			JLabel create_account_label = new JLabel("Login");
			create_account_label.setBounds(100,15,200,25);
			login_page_panel.add(create_account_label);
			
			JLabel name = new JLabel("name");
			name.setBounds(20,50,100,25);
			login_page_panel.add(name);
			
			JLabel address = new JLabel("address");
			address.setBounds(20,85,100,25);
			login_page_panel.add(address);
			
			JLabel email = new JLabel("email");
			email.setBounds(20,120,100,25);
			login_page_panel.add(email);
			
			JLabel age = new JLabel("age");
			age.setBounds(20,155,100,25);
			login_page_panel.add(age);
			
			//Textfields
			JTextField name_text = new JTextField(50);
			name_text.setBounds(80,50,165,25);
			login_page_panel.add(name_text);
			
			JTextField address_text = new JTextField(50);
			address_text.setBounds(80,85,165,25);
			login_page_panel.add(address_text);
			
			JTextField email_text = new JTextField(50);
			email_text.setBounds(80,120,165,25);
			login_page_panel.add(email_text);
			
			JTextField age_text = new JTextField(50);
			age_text.setBounds(80,155,165,25);
			login_page_panel.add(age_text);
			
			
			//create_account button
			JButton login = new JButton("Login");
			login.setBounds(20,190,150,25);
			login_page_panel.add(login);
			login.addActionListener(null);
			
			login_page.setVisible(true);
			login_page_panel.setVisible(true);
						
	}

}
