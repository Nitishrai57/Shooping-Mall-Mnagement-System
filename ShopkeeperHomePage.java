import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JPanel;

public class ShopkeeperHomePage {

	private JFrame frame;
	static String usershop;
	String vishal;
	String getshop;
	private ResultSet rs;
	private PreparedStatement pst;
	JLabel lblWelcome;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopkeeperHomePage window = new ShopkeeperHomePage(usershop);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	

	public ShopkeeperHomePage(String name) {
	
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
				int x = 109,inc = 10;
				int movement = 0;
				while(true)
				{
					try
					{
						
						Thread.sleep(500);
						if(x>350)
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
						lblWelcome.setBounds(x, 26, 300, 27);
					
						
						
						
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
		frame.setBounds(100, 100, 690, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		try
		{
			Connection connection = null; 
			connection = sqliteConnection.dbConnector();
			String query = "select * from ShopkeeperInfo where username=? ";
			pst = connection.prepareStatement(query);
			pst.setString(1, vishal);
			rs = pst.executeQuery();
			usershop = rs.getString("Name");
			getshop = rs.getString("shop_name");
			rs.close();
			pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Wrong Username or Password");	
			
		}
		
		frame.setTitle("Welcome "+usershop.toUpperCase());
		 lblWelcome = new JLabel("WELCOME "+usershop.toUpperCase()+" !!");
		 lblWelcome.setForeground(new Color(0, 0, 0));
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblWelcome.setBounds(109, 26, 468, 27);
		frame.getContentPane().add(lblWelcome);
		progress();
		
		
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		btnLogout.setIcon(new ImageIcon(img));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomePage window = new HomePage();
				window.lol().setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLogout.setBounds(513, 63, 165, 27);
		frame.getContentPane().add(btnLogout);
		
		JButton btnInventory = new JButton("INVENTORY");
		btnInventory.setBackground(Color.LIGHT_GRAY);
		img = new ImageIcon(this.getClass().getResource("/inventory.png")).getImage();
		btnInventory.setIcon(new ImageIcon(img));
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				InventoryShop logad = new InventoryShop(vishal,getshop);
				logad.lol().setVisible(true);
				
			}
		});
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInventory.setBounds(12, 445, 196, 35);
		frame.getContentPane().add(btnInventory);
		
		JButton btnStatistics = new JButton("STATISTICS");
		btnStatistics.setBackground(Color.ORANGE);
		img = new ImageIcon(this.getClass().getResource("/bar-chart.png")).getImage();
		btnStatistics.setIcon(new ImageIcon(img));
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
		    	StatisticsShop haha = new StatisticsShop(vishal,getshop);
		    	haha.lol().setVisible(true);
			}
		});
		btnStatistics.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStatistics.setBounds(252, 445, 186, 35);
		frame.getContentPane().add(btnStatistics);
		
		JButton btnFeedback = new JButton("FEEDBACK");
		btnFeedback.setBackground(new Color(0, 139, 139));
		img = new ImageIcon(this.getClass().getResource("/feedbck.png")).getImage();
		btnFeedback.setIcon(new ImageIcon(img));
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				String lolol = "Shk "+getshop;
				Feedback logad = new Feedback(1,vishal,lolol);
				logad.lol().setVisible(true);
			}
		});
		btnFeedback.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFeedback.setBounds(457, 445, 186, 35);
		frame.getContentPane().add(btnFeedback);
		
		JButton btnAddShop = new JButton("Add Employee");
		btnAddShop.setBackground(Color.CYAN);
		img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		 btnAddShop.setIcon(new ImageIcon(img));
		btnAddShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				AddEmployee logad = new AddEmployee(vishal,usershop,getshop);
				logad.lol().setVisible(true);
			}
		});
		btnAddShop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddShop.setBounds(12, 95, 253, 35);
		frame.getContentPane().add(btnAddShop);
		
		JButton btnDeleteShop = new JButton("Remove Employee");
		btnDeleteShop.setBackground(Color.LIGHT_GRAY);
		img = new ImageIcon(this.getClass().getResource("/minus.png")).getImage();
		btnDeleteShop.setIcon(new ImageIcon(img));
		btnDeleteShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				DeleteEmployee logad = new DeleteEmployee(vishal,getshop);
				logad.lol().setVisible(true);
			}
		});
		btnDeleteShop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteShop.setBounds(12, 142, 253, 35);
		frame.getContentPane().add(btnDeleteShop);
		img = new ImageIcon(this.getClass().getResource("/minus.png")).getImage();
		btnDeleteShop.setIcon(new ImageIcon(img));
		 
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 189, 666, 223);
		frame.getContentPane().add(panel_2);
		panel_2.setBackground(new Color(63, 176, 172));
		panel_2.setLayout(null);
		
		JButton btnAddItem = new JButton("ADD ITEM");
		btnAddItem.setBounds(12, 30, 196, 35);
		panel_2.add(btnAddItem);
		btnAddItem.setBackground(Color.ORANGE);
		btnAddItem.setForeground(Color.BLACK);
		img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		btnAddItem.setIcon(new ImageIcon(img));
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddItemShop haha = new AddItemShop(vishal,getshop);
				haha.lol().setVisible(true);
				
			}
		});
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnDeleteItem = new JButton("DELETE ITEM");
		btnDeleteItem.setBounds(12, 88, 196, 35);
		panel_2.add(btnDeleteItem);
		btnDeleteItem.setBackground(new Color(152, 251, 152));
		img = new ImageIcon(this.getClass().getResource("/minus.png")).getImage();
		btnDeleteItem.setIcon(new ImageIcon(img));
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				DeleteItemShop haha = new DeleteItemShop(vishal,getshop);
				haha.lol().setVisible(true);
			}
		});
		btnDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnUpdateItem_1 = new JButton("UPDATE ITEM");
		btnUpdateItem_1.setBounds(12, 146, 196, 35);
		panel_2.add(btnUpdateItem_1);
		btnUpdateItem_1.setBackground(new Color(255, 69, 0));
		img = new ImageIcon(this.getClass().getResource("/rotation.png")).getImage();
		btnUpdateItem_1.setIcon(new ImageIcon(img));
		btnUpdateItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UpdateItemShop haha = new UpdateItemShop(vishal,getshop);
				haha.lol().setVisible(true);
			}
		});
		btnUpdateItem_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblManageInventory = new JLabel("MANAGE INVENTORY");
		lblManageInventory.setForeground(new Color(0, 0, 128));
		lblManageInventory.setFont(new Font("Dialog", Font.BOLD, 20));
		lblManageInventory.setBounds(396, 79, 234, 24);
		panel_2.add(lblManageInventory);
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 410, 666, 117);
		frame.getContentPane().add(panel_4);
		panel_4.setBackground(new Color(23, 62, 67));
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 81, 666, 139);
		frame.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(250, 229, 150));
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(258, 7, 10, 10);
		panel_1.add(panel);
		panel_1.setBackground(new Color(237, 217, 192));
		
		JLabel lblManageEmployee = new JLabel("MANAGE EMPLOYEE");
		lblManageEmployee.setForeground(UIManager.getColor("CheckBox.foreground"));
		lblManageEmployee.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblManageEmployee.setBounds(382,45,225,24);
		panel_1.add(lblManageEmployee);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, -17, 666, 107);
		frame.getContentPane().add(panel_3);
		panel_3.setBackground(new Color(51, 107, 135));
		panel_1.setBackground(new Color(217, 218, 244));
		panel_2.setBackground(new Color(144, 175, 197));
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}