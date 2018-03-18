import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
public class HomePage {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	Connection connection = null;
	String arr[]= {" ","Admin","Shopkeeper","Customer"};
	JLabel lblShoppingMallManagement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void progress()
	{
		
		
		new Thread(new Runnable()
		{
			public void run()
			{
				int x = 123,inc = 10;
				int movement = 0;
				while(true)
				{
					try
					{
						
						Thread.sleep(500);
						if(x>200)
						{
							x-=inc;
							movement = 1;
						}
						else if(x<50)
						{
							x+=inc;
							movement = 0;
						}
						else if(movement==0)
							x+=inc;
						else
							x-=inc;
						lblShoppingMallManagement.setBounds(x, 0, 600, 47);
						
						
						
					}
					catch(Exception e)
					{}
				}
			}
		}).start();
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("HOMEPAGE");
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 16));
		frame.setBounds(100, 100, 836, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblNewLabel = new JLabel("  USERNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(299, 231, 166, 27);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPassword.setBounds(299, 309, 149, 20);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.ITALIC, 18));
		textField.setBounds(461, 222, 299, 47);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		progress();
		
		JComboBox comboBox = new JComboBox(arr);
		comboBox.setBounds(219, 93, 192, 34);
		frame.getContentPane().add(comboBox);
		comboBox.setBorder(new LineBorder(Color.black, 5));
		
		JLabel lblLoginAs = new JLabel("LOGIN AS:");
		lblLoginAs.setFont(new Font("Dialog", Font.BOLD, 20));
		lblLoginAs.setBounds(72, 93, 128, 21);
		frame.getContentPane().add(lblLoginAs);
		JButton btnLogin = new JButton("LOGIN");
		 Image img = new ImageIcon(this.getClass().getResource("/Ok-icon.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img));
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query;
					String haha = (String) comboBox.getSelectedItem();
					if(haha==arr[0])
						JOptionPane.showMessageDialog(null, "Please select the login administrator!!");
					else if(haha==arr[1]) {
						query = "select username,password from AdminInfo where username=? and password=? ";
						PreparedStatement pst = connection.prepareStatement(query);
						String name = textField.getText();
						pst.setString(1, name);
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()) 
							count++;
						if(count==1) {
//							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							frame.dispose();
							AdminHomePage haha1 = new AdminHomePage();
							haha1.lol().setVisible(true);}
						else
							JOptionPane.showMessageDialog(null, "Wrong Username or Password!Try again!!");
						rs.close();
						pst.close();
					}
					else if(haha==arr[2]) {
						query = "select username,password from ShopkeeperInfo where username=? and password=? ";
						PreparedStatement pst = connection.prepareStatement(query);
						String name = textField.getText();
						pst.setString(1, name);
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()) 
							count++;
						if(count==1) {
//							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							frame.dispose();
							ShopkeeperHomePage haha1 = new ShopkeeperHomePage(name);
							haha1.lol().setVisible(true);}
						else
							JOptionPane.showMessageDialog(null, "Wrong Username or Password!Try again!!");
						rs.close();
						pst.close();
					}
					else {
						query = "select username,password from CustomerInfo where username=? and password=? ";
						PreparedStatement pst = connection.prepareStatement(query);
						String name = textField.getText();
						pst.setString(1, name);
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()) 
							count++;
						if(count==1) {
//							JOptionPane.showMessageDialog(null, "Username and Password is correct");
							frame.dispose();
							CustomerHomePage haha1 = new CustomerHomePage(name);
							haha1.lol().setVisible(true);}
						else
							JOptionPane.showMessageDialog(null, "Wrong Username or Password!Try again!!");
						rs.close();
						pst.close();
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Wrong Username or Password");	
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		btnLogin.setBounds(572, 366, 213, 56);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(461, 305, 299, 34);
		frame.getContentPane().add(passwordField);
		passwordField.setBorder(new LineBorder(Color.black, 5));
		
		lblNewLabel_1 = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Login.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img1));
		lblNewLabel_1.setBounds(21, 206, 254, 256);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JButton btnRegisterShopkeeper = new JButton("REGISTER SHOPKEEPER");
		img1 = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		btnRegisterShopkeeper.setIcon(new ImageIcon(img1));
		btnRegisterShopkeeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				RegistrationShopkeeper logad = new RegistrationShopkeeper();
				logad.lol().setVisible(true);
				
			}
		});
		btnRegisterShopkeeper.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRegisterShopkeeper.setBounds(21, 517, 275, 34);
		frame.getContentPane().add(btnRegisterShopkeeper);
		
		JButton btnRegisterCustomer = new JButton("REGISTER CUSTOMER");
		img1 = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		btnRegisterCustomer.setIcon(new ImageIcon(img1));
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				RegistrationCustomer logad = new RegistrationCustomer();
				logad.lol().setVisible(true);
			}
		});
		btnRegisterCustomer.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRegisterCustomer.setBounds(308, 517, 266, 34);
		frame.getContentPane().add(btnRegisterCustomer);
		
		JButton btnRegisterAdmin = new JButton("REGISTER ADMIN");
		img1 = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		btnRegisterAdmin.setIcon(new ImageIcon(img1));
		btnRegisterAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from AdminInfo";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()) 
						count++;
					if(count==0)
					{
						frame.dispose();
						RegistrationAdmin logad = new RegistrationAdmin();
						logad.lol().setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Admin already registered!!");
						
						
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
			
				}
				
				
				
			}
		});
		btnRegisterAdmin.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRegisterAdmin.setBounds(586, 517, 227, 34);
		frame.getContentPane().add(btnRegisterAdmin);
		JPanel panel_2 = new JPanel();
		JPanel panel = new JPanel();
		panel_2.setBackground(new Color(144, 175, 197));
		panel.setBounds(12, 13, 812, 47);
		frame.getContentPane().add(panel);
		
		lblShoppingMallManagement = new JLabel("Shopping Mall Management System");
		panel.add(lblShoppingMallManagement);
		lblShoppingMallManagement.setForeground(new Color(255, 255, 255));
		lblShoppingMallManagement.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 26));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(42, 49, 50));
		panel_1.setBounds(21, 494, 803, 106);
		frame.getContentPane().add(panel_1);
		
		
		panel.setBackground(new Color(51, 107, 135));
		panel_2.setBounds(21, 53, 792, 442);
		frame.getContentPane().add(panel_2);
		
		
		
		
		
		
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}