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

public class DeleteItem {

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
					DeleteItem window = new DeleteItem();
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
	public DeleteItem() {
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
		textField.setBorder(new LineBorder(Color.black, 5));
		JLabel lblDoYouWanna = new JLabel("Do you wanna remove the item?");
		lblDoYouWanna.setForeground(new Color(0, 0, 128));
		lblDoYouWanna.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoYouWanna.setBounds(45, 204, 309, 32);
		frame.getContentPane().add(lblDoYouWanna);
		
		JButton btnYes = new JButton("YES");
		btnYes.setBackground(new Color(255, 140, 0));
		btnYes.setForeground(new Color(255, 255, 0));
		Image img = new ImageIcon(this.getClass().getResource("/yes.png")).getImage();
		btnYes.setIcon(new ImageIcon(img));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					name = textField.getText();
					if(name.equals(""))
						JOptionPane.showMessageDialog(null,"Enter Item Id");
					else
					{
					
					String query = "SELECT* from inventory where id=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, name);
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next())
						count++;
					rs.close();pst.close();
					if(count==1) {
						 query = "delete from inventory where id=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, name);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Removal Successful");
						pst.close();
						connection.close();
				    	frame.dispose();
				    	AdminHomePage haha = new AdminHomePage();
				    	haha.lol().setVisible(true);
					}
					
					else
						JOptionPane.showMessageDialog(null, "Item do not exist");
			
					
				}}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);	
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
		btnNo.setBounds(306, 266, 104, 32);
		frame.getContentPane().add(btnNo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(30, 204, 324, 32);
		frame.getContentPane().add(panel);
		panel.setBorder(new LineBorder(Color.black, 5));
	}
	public JFrame lol()
    {
    	return frame;
    }
}