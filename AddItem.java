import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class AddItem {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	int quantity;
	double price,weight;
	Connection connection;
	String name,category,shop,id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItem window = new AddItem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddItem() {
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setTitle("Add Item");
		frame.setBounds(100, 100, 686, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterItemInfo = new JLabel("ENTER ITEM INFO");
		lblEnterItemInfo.setForeground(new Color(0, 0, 0));
		lblEnterItemInfo.setFont(new Font("Dialog", Font.BOLD, 22));
		lblEnterItemInfo.setBounds(235, 13, 258, 25);
		frame.getContentPane().add(lblEnterItemInfo);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(0, 0, 128));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblId.setBounds(34, 63, 56, 16);
		frame.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setForeground(new Color(0, 0, 128));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(23, 115, 67, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setForeground(new Color(0, 0, 128));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(23, 160, 80, 16);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblWeight = new JLabel("WEIGHT");
		lblWeight.setForeground(new Color(0, 0, 128));
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWeight.setBounds(23, 207, 106, 16);
		frame.getContentPane().add(lblWeight);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setForeground(new Color(0, 0, 128));
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity.setBounds(23, 258, 106, 16);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblCategory = new JLabel("CATEGORY");
		lblCategory.setForeground(new Color(0, 0, 128));
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategory.setBounds(23, 309, 155, 16);
		frame.getContentPane().add(lblCategory);
		
		JLabel lblShop = new JLabel("SHOP");
		lblShop.setForeground(new Color(0, 0, 139));
		lblShop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblShop.setBounds(23, 359, 80, 16);
		frame.getContentPane().add(lblShop);
		
		JButton btnYes = new JButton("YES");
		btnYes.setBackground(new Color(255, 140, 0));
		btnYes.setForeground(new Color(255, 255, 0));
		Image img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					id = textField.getText();
					name =textField_1.getText();
					price = Double.parseDouble(textField_2.getText());
					weight = Double.parseDouble(textField_3.getText());
					quantity = Integer.parseInt(textField_4.getText());
					category = textField_5.getText();
					shop = textField_6.getText();
			    	Statement st1 = connection.createStatement();
			    	st1.execute("INSERT INTO Inventory (id,name,price,weight,quantity,category,shop) VALUES('"+id+"','"+name+"','"+price+"','"+weight+"','"+quantity+"','"+category+"','"+shop+"')");
			    	JOptionPane.showMessageDialog(null,"Item is added successfully");
			    	st1.close();
			    	connection.close();
			    	frame.dispose();
			    	AdminHomePage haha = new AdminHomePage();
			    	haha.lol().setVisible(true);
			    	}
			    	catch (Exception e1) {
			    		JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid information");
			    	}
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(235, 441, 132, 42);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.setBackground(new Color(255, 165, 0));
		btnNo.setForeground(new Color(255, 255, 0));
		 img = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		btnNo.setIcon(new ImageIcon(img));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
					frame.dispose();
			    	AdminHomePage haha = new AdminHomePage();
			    	haha.lol().setVisible(true);
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1);	
				}
		    
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNo.setBounds(396, 441, 122, 42);
		frame.getContentPane().add(btnNo);
		
		textField = new JTextField();
		textField.setBounds(137, 62, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 114, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		textField_2 = new JTextField();
		textField_2.setBounds(137, 159, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(145, 206, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(145, 258, 116, 22);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(145, 308, 132, 22);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(145, 358, 116, 22);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
	
		JLabel lblDoYouWant = new JLabel("Do you want to add an item?");
		lblDoYouWant.setBackground(new Color(138, 43, 226));
		lblDoYouWant.setForeground(new Color(0, 0, 128));
		lblDoYouWant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDoYouWant.setBounds(182, 395, 336, 16);
		frame.getContentPane().add(lblDoYouWant);
		
		JPanel panel = new JPanel();
		panel.setBounds(168, 392, 307, 25);
		frame.getContentPane().add(panel);
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}