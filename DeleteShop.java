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

@SuppressWarnings("unused")
public class DeleteShop {

	private JFrame frame;
	private JTextField textField;
	String name="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteShop window = new DeleteShop();
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
	public DeleteShop() {
		initialize();
		
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = sqliteConnection.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setTitle("Remove Shop");
		frame.setBounds(100, 100, 659, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterShopName = new JLabel("ENTER SHOP NAME");
		lblEnterShopName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblEnterShopName.setBounds(30, 72, 268, 51);
		frame.getContentPane().add(lblEnterShopName);
		
		textField = new JTextField();
		textField.setBounds(299, 72, 205, 51);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		JLabel lblDoYouWanna = new JLabel("Do you wanna remove the shop?");
		lblDoYouWanna.setForeground(new Color(0, 0, 128));
		lblDoYouWanna.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoYouWanna.setBounds(45, 204, 314, 32);
		frame.getContentPane().add(lblDoYouWanna);
		
		JButton btnYes = new JButton("YES");
		btnYes.setForeground(new Color(255, 255, 0));
		btnYes.setBackground(new Color(255, 165, 0));
		Image img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					name = textField.getText();
					if(name.equals(""))
						JOptionPane.showMessageDialog(null,"Enter Shop Name");
					else
					{
					
					String query = "select * from ShopkeeperInfo where shop_name=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, name);
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()) 
						count++;
					rs.close();pst.close();
					if(count==1)
					{
					 query = "delete from ShopkeeperInfo where shop_name=?";
					 pst = connection.prepareStatement(query);
					pst.setString(1, name);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Removal Successful");
					pst.close();
					connection.close();
			    	frame.dispose();
			    	AdminHomePage haha = new AdminHomePage();
			    	haha.lol().setVisible(true);}
					if(count==0)
					{
						JOptionPane.showMessageDialog(null, "Shop do not exist");	
					}
				}}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Shop do not exist");	
				}
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(165, 266, 112, 32);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.setBackground(new Color(255, 255, 0));
		btnNo.setForeground(new Color(255, 165, 0));
		 img = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		btnNo.setIcon(new ImageIcon(img));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				connection.close();
		    	frame.dispose();
		    	AdminHomePage haha = new AdminHomePage();
		    	haha.lol().setVisible(true);
			}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);	
				}}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNo.setBounds(299, 266, 104, 32);
		frame.getContentPane().add(btnNo);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 203, 329, 33);
		frame.getContentPane().add(panel);
		panel.setBorder(new LineBorder(Color.black, 5));
	}
	public JFrame lol()
    {
    	return frame;
    }

}