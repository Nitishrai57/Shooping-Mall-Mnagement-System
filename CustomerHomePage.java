import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class CustomerHomePage {

	private JFrame frame;
	static String usershop;
	static String vishal;
	private ResultSet rs;
	private PreparedStatement pst;
	private JTable table;
	double total=0.0;
	double balance;
	Connection connection = null; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	int quantity;
	double price,weight;
	String name,category,shop,id;
	static String getname,date;
	JLabel lblWelcome;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerHomePage window = new CustomerHomePage(vishal);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	

	public CustomerHomePage(String name) {
		
		connection = sqliteConnection.dbConnector();
		vishal = name;
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	


	public void progress()
	{
		
		
		new Thread(new Runnable()
		{
			public void run()
			{
				int x = 229,inc = 10;
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
						lblWelcome.setBounds(x, 37, 600, 47);
						
						
						
					}
					catch(Exception e)
					{}
				}
			}
		}).start();
	}
	




	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 262, 958, 189);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		textField = new JTextField();
		textField.setBounds(518, 463, 129, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		
		
		try
		{
		
			String query = "select * from CustomerInfo where username=? ";
			pst = connection.prepareStatement(query);
			pst.setString(1, vishal);
			rs = pst.executeQuery();
			usershop = rs.getString("Name");
			balance = rs.getDouble("balance");
			rs.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Wrong Username or Password");	
			
		}
		frame.setTitle("Welcome "+usershop);
		try
		{
			String query = "select id,name,quantity,price,shop,date,category from PurchasedHistory where USERNAME=? order by date DESC ";
			pst = connection.prepareStatement(query);
			pst.setString(1, usershop);
			
			rs = pst.executeQuery();
			while(rs.next())
				total+=rs.getDouble("PRICE");
			rs.close();
			pst.close();
			 query = "select id,name,quantity,price,shop,date,category from PurchasedHistory where USERNAME=? order by date DESC";
			pst = connection.prepareStatement(query);
			pst.setString(1, usershop);
			
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			textField.setText(Double.toString(total));
			total = 0.0;
			rs.close();
			pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);	
		}
		Image img = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		
		JButton btnInventory = new JButton("INVENTORY");
		img = new ImageIcon(this.getClass().getResource("/inventory.png")).getImage();
		btnInventory.setIcon(new ImageIcon(img));
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				InventoryCustomer logad = new InventoryCustomer(vishal);
				logad.lol().setVisible(true);
				
			}
		});
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInventory.setBounds(54, 777, 187, 36);
		frame.getContentPane().add(btnInventory);
		
		JButton btnFeedback = new JButton("FEEDBACK");
		btnFeedback.setBackground(new Color(0, 139, 139));
		img = new ImageIcon(this.getClass().getResource("/feedbck.png")).getImage();
		btnFeedback.setIcon(new ImageIcon(img));
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				String lolol = "Cus "+usershop;
				Feedback logad = new Feedback(2,vishal,lolol);
				logad.lol().setVisible(true);
			}
		});
		btnFeedback.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFeedback.setBounds(334, 777, 181, 36);
		frame.getContentPane().add(btnFeedback);
		img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		
		JLabel lblPurchaseHistory = new JLabel("Purchase History");
		lblPurchaseHistory.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblPurchaseHistory.setBounds(12, 223, 217, 27);
		frame.getContentPane().add(lblPurchaseHistory);
		
		JLabel lblTotalPurchased = new JLabel("Total Purchased");
		lblTotalPurchased.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTotalPurchased.setBounds(334, 464, 165, 15);
		frame.getContentPane().add(lblTotalPurchased);
		
		JLabel label = new JLabel("Enter Items Purchased");
		label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		label.setBounds(43, 523, 309, 25);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("ID");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		label_1.setBounds(52, 577, 56, 16);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(126, 577, 116, 27);
		frame.getContentPane().add(textField_1);
		textField_1.setBorder(new LineBorder(Color.black, 5));
		
		
		JLabel label_2 = new JLabel("QUANTITY");
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		label_2.setBounds(310, 580, 106, 16);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(451, 577, 116, 27);
		frame.getContentPane().add(textField_2);
		textField_2.setBorder(new LineBorder(Color.black, 5));
		
		JLabel label_3 = new JLabel("Do you want to buy the item?");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 20));
		label_3.setBounds(198, 608, 336, 75);
		frame.getContentPane().add(label_3);
		
		JButton btnYes = new JButton("YES");
		btnYes.setForeground(new Color(255, 127, 80));
		btnYes.setBackground(new Color(255, 255, 0));
		img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = textField_1.getText();
				String qua = (textField_2.getText());
				int count = 0;
				int ans = 1;
				if(qua.length()==0)
					ans=0;
				
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
					
					if(price>balance) {
						JOptionPane.showMessageDialog(null,"Insufficient balance!!");
						
						puchu=1;}
						
					shop = rs.getString("shop");
					category = rs.getString("category");
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					   LocalDateTime now = LocalDateTime.now();  
					    date = dtf.format(now);  
					
					pst.close();
					rs.close();
				}
				catch (Exception e1) {
		    		JOptionPane.showMessageDialog(null,"Invalid details !! Please Enter valid information");
		    	}
				if(puchu==0) {
				try {
			
					Statement st1 = connection.createStatement();
					balance-=price;
			    	st1.execute("INSERT INTO PurchasedHistory (id,name,quantity,price,shop,date,username,category) VALUES('"+id+"','"+name+"','"+quantity+"','"+price+"','"+shop+"','"+date+"','"+usershop+"','"+category+"')");
			    	JOptionPane.showMessageDialog(null,"Item is purchased successfully");
			    	st1.close();
			    	textField_1.setText(null);
					textField_2.setText(null);
					String query = "select id,name,quantity,price,shop,date,category from PurchasedHistory where USERNAME=? order by date DESC ";
					pst = connection.prepareStatement(query);
					pst.setString(1, usershop);
					
					rs = pst.executeQuery();
					while(rs.next())
						total+=rs.getDouble("PRICE");
					rs.close();
					pst.close();
					 query = "select id,name,quantity,price,shop,date,category from PurchasedHistory where USERNAME=? order by date DESC";
					pst = connection.prepareStatement(query);
					pst.setString(1, usershop);
					
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					textField.setText(Double.toString(total));
					total = 0.0;
					rs.close();
					pst.close();
			    	}
			    	catch (Exception e1) {
			    		System.out.println(e);
			    		JOptionPane.showMessageDialog(null,e);
			    	}}
			}}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(258, 681, 121, 33);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.setBackground(new Color(255, 165, 0));
		btnNo.setForeground(new Color(255, 255, 0));
		img = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		btnNo.setIcon(new ImageIcon(img));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(null);
				textField_1.setText(null);
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNo.setBounds(418, 681, 116, 33);
		frame.getContentPane().add(btnNo);
		
		JButton btnCheckBalance = new JButton("Check Balance");
		img = new ImageIcon(this.getClass().getResource("/money.png")).getImage();
		btnCheckBalance.setIcon(new ImageIcon(img));
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Your account balance is "+balance);
				
			}
		});
		btnCheckBalance.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCheckBalance.setBounds(12, 135, 217, 36);
		frame.getContentPane().add(btnCheckBalance);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 26, 1026, 98);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(51, 107, 135));
		
		
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBounds(833, 62, 181, 36);
		img = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		panel.add(btnLogout);
		btnLogout.setIcon(new ImageIcon(img));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomePage window = new HomePage();
				window.lol().setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		
		lblWelcome = new JLabel("WELCOME "+usershop.toUpperCase()+" !!");
		lblWelcome.setBounds(330, 37, 373, 27);
		panel.add(lblWelcome);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 122, 1026, 406);
		frame.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(144, 175, 197));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 523, 1026, 247);
		frame.getContentPane().add(panel_2);
		panel_2.setBackground(new Color(144, 175, 197));
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(187, 116, 305, 20);
		panel_2.add(panel_4);
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 763, 1026, 104);
		frame.getContentPane().add(panel_3);
		panel_3.setBackground(new Color(42, 49, 50));
		panel_3.setLayout(null);
	}
		
	
		
		
		
		
	
	 public JFrame lol()
	    {
	    	return frame;
	    }
}