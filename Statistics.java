import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class Statistics {

	private JFrame frame;
	Connection connection = null;
	String year[] = new String [3000];
	String date[] = new String [32];
	String month[] = new String [13];
	String hours[] = new String [25];
	String minutes[] = new String [61];
	String seconds[] = new String [61];
	String y1,y2,m1,m2,d1,d2,h1,h2,mi1,mi2,s1,s2;
	Object selectedStateobj;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox,comboBox_1,comboBox_2,comboBox_3,comboBox_4,comboBox_5,comboBox_6,comboBox_7,comboBox_8,comboBox_9,comboBox_10,comboBox_11;
	int ans= 1;
	private JLabel lblShopWise,lblCategoryWise,lblItemWise;
	private JTable table;
	private JTable table_1;
	private JTable table_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics window = new Statistics();
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
	public Statistics() {
		connection = sqliteConnection.dbConnector();
		int i;
		year[0]=" ";
		date[0]=" ";
		month[0]=" ";
		hours[0]=" ";
		minutes[0]=" ";
		seconds[0]=" ";
		for(i=1901;i<=2100;i++)
			year[i-1900]=Integer.toString(i);
		for(i=1;i<=31;i++)
			date[i]=Integer.toString(i);
		for(i=1;i<=12;i++)
			month[i]=Integer.toString(i);
		for(i=0;i<24;i++)
			hours[i+1]=Integer.toString(i);
		for(i=0;i<60;i++)
			minutes[i+1]=Integer.toString(i);
		for(i=0;i<60;i++)
			seconds[i+1]=Integer.toString(i);
			
		
		initialize();
	}
	public void input()
	{
		selectedStateobj = comboBox.getSelectedItem();
		y1 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_1.getSelectedItem();
		m1 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_2.getSelectedItem();
		d1 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_3.getSelectedItem();
		h1 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_4.getSelectedItem();
		mi1 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_5.getSelectedItem();
		s1 = String.valueOf(selectedStateobj);

		selectedStateobj = comboBox_6.getSelectedItem();
		y2 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_7.getSelectedItem();
		m2 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_8.getSelectedItem();
		d2 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_9.getSelectedItem();
		h2 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_10.getSelectedItem();
		mi2 = String.valueOf(selectedStateobj);
		selectedStateobj = comboBox_11.getSelectedItem();
		s2 = String.valueOf(selectedStateobj);
		if(y1==year[0] || y2==year[0] || m1==month[0]||m2==month[0]||h1==hours[0]||h2==hours[0]||mi1==minutes[0]||mi2==minutes[0]||s1==seconds[0]||s2==seconds[0])
		{
			ans = 0;
			JOptionPane.showMessageDialog(null,"Please provide the respective dates");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Statistics");
		frame.setBounds(100, 100, 833, 1800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(144, 175, 197));
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setFont(new Font("Dialog", Font.BOLD, 20));
		lblStatistics.setBounds(332, 42, 138, 15);
		frame.getContentPane().add(lblStatistics);
		
		JLabel lblEnterStartingDate = new JLabel("Enter Starting Date");
		lblEnterStartingDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEnterStartingDate.setBounds(29, 107, 217, 19);
		frame.getContentPane().add(lblEnterStartingDate);
		
		 comboBox = new JComboBox(year);
		comboBox.setBounds(264, 107, 91, 24);
		frame.getContentPane().add(comboBox);
		
		 comboBox_1 = new JComboBox(month);
		comboBox_1.setBounds(367, 107, 80, 24);
		frame.getContentPane().add(comboBox_1);
		
		 comboBox_2 = new JComboBox(date);
		comboBox_2.setBounds(459, 107, 74, 24);
		frame.getContentPane().add(comboBox_2);
		
		 comboBox_3 = new JComboBox(hours);
		comboBox_3.setBounds(545, 107, 74, 24);
		frame.getContentPane().add(comboBox_3);
		
		 comboBox_4 = new JComboBox(minutes);
		comboBox_4.setBounds(631, 107, 74, 24);
		frame.getContentPane().add(comboBox_4);
		
		 comboBox_5 = new JComboBox(seconds);
		comboBox_5.setBounds(717, 107, 79, 24);
		frame.getContentPane().add(comboBox_5);
		
		JLabel lblEnterEndingDate = new JLabel("Enter Ending Date");
		lblEnterEndingDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEnterEndingDate.setBounds(29, 309, 200, 24);
		frame.getContentPane().add(lblEnterEndingDate);
		 comboBox_6 = new JComboBox(year);
		comboBox_6.setBounds(264, 309, 91, 24);
		frame.getContentPane().add(comboBox_6);
		
		 comboBox_7 = new JComboBox(month);
		comboBox_7.setBounds(367, 309, 80, 24);
		frame.getContentPane().add(comboBox_7);
		
		 comboBox_8 = new JComboBox(date);
		comboBox_8.setBounds(459, 309, 74, 24);
		frame.getContentPane().add(comboBox_8);
		
		 comboBox_9 = new JComboBox(hours);
		comboBox_9.setBounds(545, 309, 74, 24);
		frame.getContentPane().add(comboBox_9);
		
		 comboBox_10 = new JComboBox(minutes);
		comboBox_10.setBounds(631, 309, 74, 24);
		frame.getContentPane().add(comboBox_10);
		
		 comboBox_11 = new JComboBox(seconds);
		comboBox_11.setBounds(717, 309, 79, 24);
		frame.getContentPane().add(comboBox_11);
		
		JLabel lblYear = new JLabel("year");
		lblYear.setBounds(274, 143, 70, 15);
		frame.getContentPane().add(lblYear);
		
		JLabel lblMonth = new JLabel("month");
		lblMonth.setBounds(367, 143, 70, 15);
		frame.getContentPane().add(lblMonth);
		
		JLabel lblDate = new JLabel("date");
		lblDate.setBounds(463, 143, 70, 15);
		frame.getContentPane().add(lblDate);
		
		JLabel lblHour = new JLabel("hour");
		lblHour.setBounds(545, 143, 70, 15);
		frame.getContentPane().add(lblHour);
		
		JLabel lblMinute = new JLabel("minute");
		lblMinute.setBounds(641, 143, 70, 15);
		frame.getContentPane().add(lblMinute);
		
		JLabel lblSeconds = new JLabel("seconds");
		lblSeconds.setBounds(727, 143, 70, 15);
		frame.getContentPane().add(lblSeconds);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.setBackground(Color.ORANGE);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				input();
				if(ans==1) {
				String start = y1+"/"+m1+"/"+d1+" "+h1+":"+mi1+":"+s1;
				String end = y2+"/"+m2+"/"+d2+" "+h2+":"+mi2+":"+s2;
				String query = "select shop,sum(price) from purchasedhistory  where date between ? and ? group by shop";
				try {
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, start);
				pst.setString(2, end);
				ResultSet rs = pst.executeQuery();
				int count=0;
				while(rs.next())
					count++;
				pst.close();
				rs.close();
				if(count==0)
					JOptionPane.showMessageDialog(null, "No purchase made!!");
				
					 pst = connection.prepareStatement(query);
					pst.setString(1, start);
					pst.setString(2, end);
					 rs = pst.executeQuery();
				lblShopWise.setVisible(true);
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(36, 638, 367, 148);
				frame.getContentPane().add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			
				pst.close();
				rs.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Wrong Entry!Try again!!");
				}
				
				 query = "select category,sum(price) from purchasedhistory  where date between ? and ? group by category";
				try {
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, start);
				pst.setString(2, end);
				ResultSet rs = pst.executeQuery();
				lblCategoryWise.setVisible(true);
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(459, 638, 346, 148);
				frame.getContentPane().add(scrollPane_1);
				
				table_1 = new JTable();
				scrollPane_1.setViewportView(table_1);
				
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Wrong Entry!Try again!!");
				}
				
				 query = "select name,sum(price) from purchasedhistory  where date between ? and ? group by name";
					try {
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, start);
					pst.setString(2, end);
					ResultSet rs = pst.executeQuery();
					lblItemWise.setVisible(true);
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(272, 859, 329, 157);
					frame.getContentPane().add(scrollPane);
					
					table_3 = new JTable();
					scrollPane.setViewportView(table_3);
			
					table_3.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Wrong Entry!Try again!!");
					}
					
				
			}}
		});
		
		btnShow.setBounds(650, 489, 117, 25);
		frame.getContentPane().add(btnShow);
		
		lblShopWise = new JLabel("SHOP WISE");
		lblShopWise.setFont(new Font("Dialog", Font.BOLD, 18));
		lblShopWise.setBounds(36, 584, 117, 15);
		frame.getContentPane().add(lblShopWise);
		 lblItemWise = new JLabel("ITEM WISE");
		lblItemWise.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItemWise.setBounds(383, 816, 138, 15);
		frame.getContentPane().add(lblItemWise);
		 lblCategoryWise = new JLabel("CATEGORY WISE");
		lblCategoryWise.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCategoryWise.setBounds(545, 584, 174, 15);
		frame.getContentPane().add(lblCategoryWise);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(0, 128, 128));
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		 btnBack.setIcon(new ImageIcon(img));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
		    	AdminHomePage haha = new AdminHomePage();
		    	haha.lol().setVisible(true);
			}
		});
		btnBack.setBounds(679, 967, 117, 32);
		frame.getContentPane().add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(316, 35, 145, 24);
		frame.getContentPane().add(panel);
		
		
		lblShopWise.setVisible(false);
		lblItemWise.setVisible(false);
		lblCategoryWise.setVisible(false);
			
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}
