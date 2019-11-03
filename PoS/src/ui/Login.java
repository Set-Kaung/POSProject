package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import services.LoginValidation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(47, 58, 74, 16);
		panel.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(118, 53, 130, 26);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblSignin = new JLabel("Sign-In");
		lblSignin.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblSignin.setBounds(180, 6, 74, 30);
		panel.add(lblSignin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(47, 96, 74, 16);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 86, 130, 26);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = usernameField.getText();
				char[] password = passwordField.getPassword();
				boolean valid = false;
				
				if(!name.isEmpty() && !(password.length == -1)) {
				LoginValidation validation = new LoginValidation();
			    valid = validation.isValid(name, password);
			    if(valid) 
				{
					JOptionPane.showMessageDialog(null,"Login Successful");
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Username or password incorrect.");
				}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Please enter username and password.");
				}
				
				
			}
		});
		btnLogin.setBounds(118, 174, 117, 29);
		panel.add(btnLogin);
	}
}
