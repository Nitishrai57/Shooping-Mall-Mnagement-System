import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;

public class DeleteItemShop {

	private JFrame frame;
	private JTextField textField;
	String name="";
	static String getshop,vishal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteItemShop window = new DeleteItemShop(vishal,getshop);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public DeleteItemShop(String a,String b) {
		vishal = a;
		getshop = b;
		initialize();
		
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = sqliteConnection.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setTitle("Delete Item");
		frame.setBounds(100, 100, 659, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterShopName = new JLabel("ENTER ITEM ID");
		lblEnterShopName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblEnterShopName.setBounds(30, 72, 268, 51);
		frame.getContentPane().add(lblEnterShopName);
		
		textField = new JTextField();
		textField.setBounds(299, 72, 205, 51);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDoYouWanna = new JLabel("Do you wanna remove the item?");
		lblDoYouWanna.setForeground(new Color(0, 0, 128));
		lblDoYouWanna.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoYouWanna.setBounds(45, 204, 309, 32);
		frame.getContentPane().add(lblDoYouWanna);
		
		JButton btnYes = new JButton("YES");
		Image img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					name = textField.getText();
					if(name.equals(""))
						JOptionPane.showMessageDialog(null,"Enter Item Name");
					else
					{
						String query = "select * from inventory where shop=? and id=? ";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, getshop);
						pst.setString(2, name);
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()) 
							count++;
						rs.close();pst.close();
						if(count==1) {
					query = "delete from inventory where shop=? and id=? ";
					 pst = connection.prepareStatement(query);
					pst.setString(1, getshop);
					pst.setString(2, name);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Removal Successful");
					pst.close();
				
					frame.dispose();
			    	ShopkeeperHomePage haha = new ShopkeeperHomePage(vishal);
			    	haha.lol().setVisible(true);}
						else
							JOptionPane.showMessageDialog(null, "Item do not exist");	
						}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Item do not exist");	
				}
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(165, 266, 112, 32);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		 img = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		btnNo.setIcon(new ImageIcon(img));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				connection.close();
		    	frame.dispose();
		    	ShopkeeperHomePage haha = new ShopkeeperHomePage(vishal);
		    	haha.lol().setVisible(true);
			}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);	
				}}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNo.setBounds(306, 266, 104, 32);
		frame.getContentPane().add(btnNo);
		textField.setBorder(new LineBorder(Color.black, 5));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(30, 199, 339, 39);
		frame.getContentPane().add(panel);
		panel.setBorder(new LineBorder(Color.black, 5));
	}
	public JFrame lol()
    {
    	return frame;
    }
}