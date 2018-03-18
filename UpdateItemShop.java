import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;

public class UpdateItemShop {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	int quantity;
	double price,weight;
	Connection connection;
	String name,category,shop,id;
	static String vishal,getshop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateItemShop window = new UpdateItemShop(vishal,getshop);
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
	public UpdateItemShop(String a,String b) {
		vishal = a;
		getshop = b;
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setTitle("Update Shop");
		frame.setBounds(100, 100, 686, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterItemInfo = new JLabel("ENTER ITEM INFO");
		lblEnterItemInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
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
		lblCategory.setBounds(23, 309, 119, 16);
		frame.getContentPane().add(lblCategory);
		
		JButton btnYes = new JButton("YES");
		btnYes.setBackground(new Color(255, 165, 0));
		btnYes.setForeground(new Color(255, 255, 0));
		Image img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					id = textField.getText();
					if(id.equals(""))
					{JOptionPane.showMessageDialog(null,"Enter Item Id");}
					else {
					
						String query ="Select * from inventory where id=? and shop=?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, id);
						pst.setString(2, getshop);
						ResultSet rs = pst.executeQuery();
						int count=0;
						while(rs.next())
							count++;
						pst.close();
						rs.close();
						if(count==0)
							JOptionPane.showMessageDialog(null,"ID do not Exist!! Please Enter Correct Id");
						else {
							
							 query ="Select * from inventory where id=? and shop=?";
							 pst = connection.prepareStatement(query);
							 pst.setString(1, id);
								pst.setString(2, getshop);
							rs = pst.executeQuery();
							
						name =rs.getString(2);
						price = (rs.getDouble(3));
						weight =(rs.getDouble(4));
						quantity = (rs.getInt(5));
						category =rs.getString(6);
			
					String name1 =textField_1.getText();
					String price1 = (textField_2.getText());
					String weight1 = (textField_3.getText());
					String quantity1 = (textField_4.getText());
					String category1 = textField_5.getText();
					
					
			        
					
					if(!(name1.equals("")))
					name = name1.substring(0);
					if(!(price1.equals("")))
						price = Double.parseDouble(price1);
					if(!(weight1.equals("")))
						weight = Double.parseDouble(weight1);
					if(!(quantity1.equals("")))
						quantity = Integer.parseInt(quantity1);
					if(!(category1.equals("")))
						category = category1;
				
				pst.close();
				rs.close();
						 
			    	Statement st1 = connection.createStatement();
			    	query = "UPDATE Inventory set name='"+name+"',price='"+price+"',weight='"+weight+"',quantity='"+quantity+"',category='"+category+"',shop='"+getshop+"' where id=? ";
					pst = connection.prepareStatement(query);
					pst.setString(1, id);
					pst.execute();
			
			    	JOptionPane.showMessageDialog(null,"Item is updated successfully");
			    	st1.close();
			    	connection.close();
			    	frame.dispose();
			    	ShopkeeperHomePage haha = new ShopkeeperHomePage(vishal);
			    	haha.lol().setVisible(true);
			    	}}}
			    	catch (Exception e1) {
			    		JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid information");
			    	}
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(307, 423, 119, 31);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.setBackground(new Color(0, 128, 128));
		btnNo.setForeground(new Color(255, 165, 0));
		 img = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
			btnNo.setIcon(new ImageIcon(img));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
					frame.dispose();
					ShopkeeperHomePage haha = new ShopkeeperHomePage(vishal);
			    	haha.lol().setVisible(true);
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1);	
				}
		    
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNo.setBounds(438, 423, 108, 31);
		frame.getContentPane().add(btnNo);
		
		textField = new JTextField();
		textField.setBounds(127, 63, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 115, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(127, 160, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(127, 201, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(147, 258, 116, 22);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(147, 309, 116, 22);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblDoYouWant = new JLabel("Do you want to update an item?");
		lblDoYouWant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDoYouWant.setBounds(182, 395, 340, 16);
		frame.getContentPane().add(lblDoYouWant);
		
		JPanel panel = new JPanel();
		panel.setBounds(169, 383, 353, 31);
		frame.getContentPane().add(panel);
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }

}