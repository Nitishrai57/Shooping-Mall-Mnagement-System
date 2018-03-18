import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Font;

import javax.swing.table.DefaultTableModel;

import org.eclipse.swt.graphics.Image;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.awt.Color;    

public class Feedback {

	private JFrame frame;
	private JLabel lblGiveFeedback;
	private JButton btnNewButton;
	Connection connection = null;
	static int index;
	static String getname,vishal;
	java.sql.Statement st1;
	private JButton btnNewButton_1;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feedback window = new Feedback(index,vishal,getname);
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
	public Feedback(int i,String a,String b) {
		index = i;
		vishal = a;
		getname = b;
		connection = sqliteConnection.dbConnector();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Feedback");
		frame.setBounds(100, 100, 828, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(144, 175, 197));
		
		
		JLabel lblFeedbacks = new JLabel("FEEDBACKS");
		lblFeedbacks.setFont(new Font("Dialog", Font.BOLD, 20));
		lblFeedbacks.setBounds(346, 31, 146, 31);
		frame.getContentPane().add(lblFeedbacks);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 163, 765, 346);
		frame.getContentPane().add(scrollPane_2);
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(new Color(192, 192, 192));
		textArea_1.setForeground(new Color(0, 0, 0));
		textArea_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		textArea_1.setBorder(new LineBorder(Color.black, 5));
		scrollPane_2.setViewportView(textArea_1);
		
		JButton btnDisplayFeedbacks = new JButton("DISPLAY FEEDBACKS");
		btnDisplayFeedbacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String query = "select * from Feedback";
					PreparedStatement pst;
					pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						String a = rs.getString("user");
						String b = rs.getString("feedback");
						String c = rs.getString("time");
						String result = a+" :\n "+b+" \n"+c;
						textArea_1.append("\n");
						textArea_1.append("\n");
						textArea_1.append("\n");
						textArea_1.append(result);
					}
					
					pst.close();
					rs.close();
				
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "No Feedback");	
				}}
		});
		btnDisplayFeedbacks.setBounds(63, 96, 174, 25);
		frame.getContentPane().add(btnDisplayFeedbacks);
		
		lblGiveFeedback = new JLabel("GIVE FEEDBACK");
		lblGiveFeedback.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblGiveFeedback.setBounds(116, 657, 179, 25);
		frame.getContentPane().add(lblGiveFeedback);
		
		btnNewButton = new JButton("SUBMIT ");
		btnNewButton.setBackground(new Color(192, 192, 192));
		 java.awt.Image img = new ImageIcon(this.getClass().getResource("/enter.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lele = textArea.getText();
				
				
				textArea.setText(" ");
				if(lele.length()==0)
					JOptionPane.showMessageDialog(null, "Please Enter Feedback");	
				else
				{
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					   LocalDateTime now = LocalDateTime.now();  
					   String date = dtf.format(now);  
				
					try {
						st1 = connection.createStatement();
						st1.execute("INSERT INTO Feedback (user,feedback,time) VALUES('"+getname+"','"+lele+"','"+date+"')");
						st1.close();

						
							String query = "select * from Feedback";
							PreparedStatement pst;
							pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							while(rs.next())
							{
								String a = rs.getString("user");
								String b = rs.getString("feedback");
								String c = rs.getString("time");
								String result = a+" :\n "+b+" \n"+c;
								textArea_1.append("\n");
								textArea_1.append("\n");
								textArea_1.append("\n");
								textArea_1.append(result);
							}
							
							pst.close();
							rs.close();
						
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null, "No Feedback");	
						}
					
					
			    	
				}
			}
		});
		btnNewButton.setBounds(506, 792, 134, 42);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setBackground(new Color(255, 165, 0));
		 img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
			btnNewButton_1.setIcon(new ImageIcon(img));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(index==0) {
					frame.dispose();
		    	AdminHomePage haha = new AdminHomePage();
		    	haha.lol().setVisible(true);
			}
				else if(index==1)
				{
					frame.dispose();
			    	ShopkeeperHomePage haha = new ShopkeeperHomePage(vishal);
			    	haha.lol().setVisible(true);
				}
				else
				{
					frame.dispose();
			    	CustomerHomePage haha = new CustomerHomePage(vishal);
			    	haha.lol().setVisible(true);
				}
				}
		});
		btnNewButton_1.setBounds(74, 817, 117, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(367, 541, 394, 231);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setBorder(new LineBorder(Color.black, 5));
		
		
		
	
		
		
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}
