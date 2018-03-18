import java.awt.Color;
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
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class DeleteEmployee {

	private JFrame frame;
	private JTextField textField;
	String name="";
	static String vishal,getshop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployee window = new DeleteEmployee(vishal,getshop);
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
	public DeleteEmployee(String ha,String gg) {
		vishal = ha;
		getshop = gg;
		initialize();
		
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = sqliteConnection.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setTitle("Remove Employee");
		frame.setBounds(100, 100, 659, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterShopName = new JLabel("Enter Employee Name");
		lblEnterShopName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblEnterShopName.setBounds(12, 72, 295, 51);
		frame.getContentPane().add(lblEnterShopName);
		
		textField = new JTextField();
		textField.setBounds(319, 76, 205, 51);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		
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
						JOptionPane.showMessageDialog(null,"Enter Employee Name");
					else
					{
						String query = "select * from EmployeeInfo where shop=? and name=? ";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, getshop);
						pst.setString(2, name);
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()) 
							count++;
						rs.close();pst.close();
						if(count==1) {
					query = "delete from EmployeeInfo where shop=? and name=? ";
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
							JOptionPane.showMessageDialog(null, "Employee do not exist");	
							
					}
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Employee do not exist");	
				}
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnYes.setBounds(165, 276, 112, 32);
		frame.getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.setForeground(new Color(255, 165, 0));
		btnNo.setBackground(new Color(255, 255, 0));
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
		btnNo.setBounds(289, 276, 114, 32);
		frame.getContentPane().add(btnNo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(25, 225, 362, 39);
		frame.getContentPane().add(panel);
		panel.setBorder(new LineBorder(Color.black, 5));
		JLabel lblDoYouWanna = new JLabel("Do you wanna remove the employee?");
		lblDoYouWanna.setForeground(new Color(0, 0, 128));
		panel.add(lblDoYouWanna);
		lblDoYouWanna.setFont(new Font("Tahoma", Font.PLAIN, 18));
	}
	public JFrame lol()
    {
    	return frame;
    }

}