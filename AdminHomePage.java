import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.util.Random;
import javax.swing.JPanel; 
public class AdminHomePage {

	private JFrame frame;
	JLabel lblWelcome;
	Random rand = new Random();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomePage window = new AdminHomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public AdminHomePage() {
		initialize();
	}


		public void progress()
		{
			
			
			new Thread(new Runnable()
			{
				public void run()
				{
					int x = 275,inc = 10;
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
		frame.setTitle("Welcome Admin");
		frame.setBounds(100, 100, 690, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 666, 93);
		panel.setBackground(new Color(25, 25, 25));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		lblWelcome = new JLabel("WELCOME ADMIN !!");
		lblWelcome.setBounds(221, 26, 258, 27);
		panel.add(lblWelcome);
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		progress();
	
		
	
		
		
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(255, 69, 0));
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
		btnLogout.setBounds(471, 70, 178, 35);
		frame.getContentPane().add(btnLogout);
		
		JButton btnInventory = new JButton("INVENTORY");
		btnInventory.setBackground(new Color(128, 128, 128));
		img = new ImageIcon(this.getClass().getResource("/inventory.png")).getImage();
		btnInventory.setIcon(new ImageIcon(img));
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Inventory logad = new Inventory();
				logad.lol().setVisible(true);
				
			}
		});
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInventory.setBounds(22, 449, 202, 35);
		frame.getContentPane().add(btnInventory);
		
		JButton btnStatistics = new JButton("STATISTICS");
		btnStatistics.setBackground(new Color(233, 150, 122));
		img = new ImageIcon(this.getClass().getResource("/bar-chart.png")).getImage();
		btnStatistics.setIcon(new ImageIcon(img));
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
		    	Statistics haha = new Statistics();
		    	haha.lol().setVisible(true);
			}
		});
		btnStatistics.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStatistics.setBounds(471, 449, 186, 35);
		frame.getContentPane().add(btnStatistics);
		
		JButton btnFeedback = new JButton("FEEDBACK");
		btnFeedback.setBackground(new Color(0, 139, 139));
		img = new ImageIcon(this.getClass().getResource("/feedbck.png")).getImage();
		btnFeedback.setIcon(new ImageIcon(img));
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Feedback logad = new Feedback(0,"Admin","Admin");
				logad.lol().setVisible(true);
				
			}
		});
		btnFeedback.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFeedback.setBounds(262, 443, 186, 46);
		frame.getContentPane().add(btnFeedback);
		
		JLabel lblNewLabel_1 = new JLabel("MANAGE SHOP");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_1.setBounds(400, 140, 186, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnAddShop = new JButton("ADD SHOP");
		btnAddShop.setBackground(Color.CYAN);
		btnAddShop.setForeground(Color.BLACK);
		 img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		 btnAddShop.setIcon(new ImageIcon(img));
		btnAddShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				RegistrationShopkeeperAdmin logad = new RegistrationShopkeeperAdmin();
				logad.lol().setVisible(true);
			}
		});
		btnAddShop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddShop.setBounds(22, 116, 202, 35);
		frame.getContentPane().add(btnAddShop);
		
		JButton btnDeleteShop = new JButton("DELETE SHOP");
		btnDeleteShop.setBackground(Color.LIGHT_GRAY);
		img = new ImageIcon(this.getClass().getResource("/minus.png")).getImage();
		btnDeleteShop.setIcon(new ImageIcon(img));
		btnDeleteShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				DeleteShop logad = new DeleteShop();
				logad.lol().setVisible(true);
			}
		});
		btnDeleteShop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteShop.setBounds(22, 163, 202, 35);
		frame.getContentPane().add(btnDeleteShop);
		
		JButton btnAddItem = new JButton("ADD ITEM");
		btnAddItem.setBackground(new Color(0, 128, 128));
		btnAddItem.setForeground(Color.BLACK);
		 img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		btnAddItem.setIcon(new ImageIcon(img));
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddItem haha = new AddItem();
				haha.lol().setVisible(true);
				
			}
		});
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddItem.setBounds(65, 241, 202, 35);
		frame.getContentPane().add(btnAddItem);
		
		JButton btnDeleteItem = new JButton("DELETE ITEM");
		btnDeleteItem.setBackground(new Color(0, 250, 154));
		img = new ImageIcon(this.getClass().getResource("/minus.png")).getImage();
		 btnDeleteItem.setIcon(new ImageIcon(img));
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				DeleteItem haha = new DeleteItem();
				haha.lol().setVisible(true);
			}
		});
		btnDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteItem.setBounds(65, 310, 202, 35);
		frame.getContentPane().add(btnDeleteItem);
		
		JButton btnUpdateItem_1 = new JButton("UPDATE ITEM");
		btnUpdateItem_1.setBackground(new Color(255, 69, 0));
		 img = new ImageIcon(this.getClass().getResource("/rotation.png")).getImage();
		 btnUpdateItem_1.setIcon(new ImageIcon(img));
		btnUpdateItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UpdateItem haha = new UpdateItem();
				haha.lol().setVisible(true);
			}
		});
		btnUpdateItem_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdateItem_1.setBounds(61, 377, 202, 35);
		frame.getContentPane().add(btnUpdateItem_1);
		
		JLabel lblNewLabel_2 = new JLabel("MANAGE INVENTORY");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_2.setBounds(370, 329, 268, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 104, 666, 128);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(223, 226, 219));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 230, 655, 195);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(255, 240, 86));
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 424, 666, 115);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(255,255,255));
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}