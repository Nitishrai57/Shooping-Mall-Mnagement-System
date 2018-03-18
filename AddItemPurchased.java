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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class AddItemPurchased {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_4;
	int quantity;
	double price,weight;
	Connection connection;
	String name,category,shop,id;
	static String vishal,getname,date;
	double totalprice=0.0;
	static double balance;
	PreparedStatement pst;
	ResultSet rs;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemPurchased window = new AddItemPurchased(vishal,getname,balance);
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
	public AddItemPurchased(String a,String b,double c) {
		vishal = a;
		getname = b;
		balance = c;
		connection = sqliteConnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 686, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterItemInfo = new JLabel("Enter Items Purchased");
		lblEnterItemInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblEnterItemInfo.setBounds(209, 24, 309, 25);
		frame.getContentPane().add(lblEnterItemInfo);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblId.setBounds(34, 80, 56, 16);
		frame.getContentPane().add(lblId);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity.setBounds(295, 80, 106, 16);
		frame.getContentPane().add(lblQuantity);
		
		JButton btnYes = new JButton("YES");
		Image img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				String qua = (textField_4.getText());
				int count = 0;
				int ans = 1;
				
				try
				{
					String query = "Select * from inventory where id=?";
					pst = connection.prepareStatement(query);
					pst.setString(1, id);
					rs = pst.executeQuery();
					while(rs.next())
						count++;
					rs.close();
					pst.close();
				}
				catch (Exception e1) {
		    		JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid information");
		    	}
				int i;
				for(i=0;i<qua.length();i++)
				{
					char c;
					c=qua.charAt(i);
					if(!(c>='0' && c<='9'))
					{
						ans = 0;
						break;
					}
						
				}
				if(count == 0) {
			
				
					JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid item ID");}
				else if(ans==0)
				{
					JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid Qunatity");
				}
				else
				{
					
				quantity = Integer.parseInt(qua);
				int puchu = 0;
				try
				{
					puchu = 0;
					String query = "Select * from inventory where id=?";
					pst = connection.prepareStatement(query);
					pst.setString(1, id);
					rs = pst.executeQuery();
					name = rs.getString("name");
					price = Double.parseDouble(rs.getString("price"))*quantity;
					totalprice+=price;
					if(totalprice>balance) {
						JOptionPane.showMessageDialog(null,"Insufficient balance!!");
						totalprice-=price;
						puchu=1;}
						
					shop = rs.getString("shop");
					category = rs.getString("category");
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					   LocalDateTime now = LocalDateTime.now();  
					    date = dtf.format(now);  
					rs.close();
					pst.close();
				}
				catch (Exception e1) {
		    		JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid information");
		    	}
				if(puchu==0) {
				try {
			
					Statement st1 = connection.createStatement();
			    	st1.execute("INSERT INTO PurchasedHistory (id,name,quantity,price,shop,date,username,category) VALUES('"+id+"','"+name+"','"+quantity+"','"+price+"','"+shop+"','"+date+"','"+getname+"','"+category+"')");
			    	JOptionPane.showMessageDialog(null,"Item is purchased successfully");
			    	st1.close();
			    	textField.setText(null);
					textField_4.setText(null);
			    	}
			    	catch (Exception e1) {
			    		System.out.println(e);
			    		JOptionPane.showMessageDialog(null,e);
			    	}}
			}}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(209, 212, 121, 33);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		img = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		btnNo.setIcon(new ImageIcon(img));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_4.setText(null);
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNo.setBounds(360, 212, 116, 33);
		frame.getContentPane().add(btnNo);
		
		textField = new JTextField();
		textField.setBounds(127, 74, 116, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		textField_4 = new JTextField();
		textField_4.setBounds(444, 80, 116, 22);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setBorder(new LineBorder(Color.black, 5));
		JLabel lblDoYouWant = new JLabel("Do you want to buy the item?");
		lblDoYouWant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDoYouWant.setBounds(127, 157, 336, 25);
		frame.getContentPane().add(lblDoYouWant);
		
		JButton btnNewButton = new JButton("Back");
		 img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String query = "UPDATE CUSTOMERINFO SET BALANCE=? WHERE USERNAME =?";
				pst = connection.prepareStatement(query);
				pst.setDouble(1, balance-totalprice);
				pst.setString(2, vishal);
				pst.execute();
				pst.close();
				
				frame.dispose();
		    	CustomerHomePage haha = new CustomerHomePage(vishal);
		    	haha.lol().setVisible(true);
			}
				catch(Exception e)
				{}}
		});
		btnNewButton.setBounds(12, 393, 117, 33);
		frame.getContentPane().add(btnNewButton);
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }

}