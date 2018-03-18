import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;


public class Inventory {

	private JFrame frame;
	Connection connection = null;
	private JTable table;
	String options[] = {" ","Categories Wise","Shop Wise","ID Wise"};
	private JTextField textField;
	private JTable table_1;
	private JTextField textField_1;
	private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
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
	
	public Inventory() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Inventory");
		frame.setBounds(100, 100, 874, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(144, 175, 197));
		JLabel lblInventory = new JLabel("INVENTORY");
		lblInventory.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		lblInventory.setBounds(310, 22, 157, 27);
		frame.getContentPane().add(lblInventory);
	
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(292, 100, 175, 36);
		frame.getContentPane().add(comboBox);
		comboBox.setBorder(new LineBorder(Color.black, 5));
		
		JLabel lblSelectPreferredOrder = new JLabel("Select Preferred Order");
		lblSelectPreferredOrder.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSelectPreferredOrder.setBounds(29, 98, 245, 27);
		frame.getContentPane().add(lblSelectPreferredOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 214, 743, 237);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.setBackground(new Color(0, 128, 128));
		Image img = new ImageIcon(this.getClass().getResource("/display.png")).getImage();
		btnDisplay.setIcon(new ImageIcon(img));
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				String query;
				String select = comboBox.getSelectedItem().toString();
				if(select==options[0])
					JOptionPane.showMessageDialog(null, "Please select the preferred order!!");
				else if(select==options[1])
				{
					
					try {
						query = "select * from Inventory order by category";
						PreparedStatement pst;
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						rs.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else if(select==options[2])
				{
					query = "select * from Inventory order BY shop ";
					PreparedStatement pst;
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						rs.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Inventory is empty");
					}
				}
				else if(select==options[3])
				{
					query = "select * from Inventory order BY ID DESC ";
					PreparedStatement pst;
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						rs.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Inventory is empty");
					}
				}
				else
				{}
				
			}
		});
		btnDisplay.setBounds(580, 164, 134, 27);
		frame.getContentPane().add(btnDisplay);
		
		JLabel lblEnterShopName = new JLabel("ENTER SHOP NAME");
		lblEnterShopName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblEnterShopName.setBounds(29, 504, 234, 19);
		frame.getContentPane().add(lblEnterShopName);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.ITALIC, 18));
		textField.setText("");
		textField.setBounds(268, 489, 199, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 575, 743, 100);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblListOfItems = new JLabel("LIST OF ITEMS OF A SHOP");
		lblListOfItems.setBounds(357, 674, 208, 39);
		frame.getContentPane().add(lblListOfItems);
		lblListOfItems.setVisible(false);
		
		
		JLabel lblEnterItemName = new JLabel("ENTER ITEM NAME");
		lblEnterItemName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblEnterItemName.setBounds(40, 744, 234, 19);
		frame.getContentPane().add(lblEnterItemName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.ITALIC, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(268, 744, 199, 36);
		frame.getContentPane().add(textField_1);
		textField_1.setBorder(new LineBorder(Color.black, 5));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(42, 817, 730, 94);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
	
		
		JLabel lblListOfItems_1 = new JLabel("LIST OF ITEMS");
		lblListOfItems_1.setBounds(387, 923, 208, 39);
		frame.getContentPane().add(lblListOfItems_1);
		lblListOfItems_1.setVisible(false);
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table_1.setFocusable(false);
		table_1.setRowSelectionAllowed(false);
		table_1.setCellSelectionEnabled(false);
		table_2.setFocusable(false);
		table_2.setRowSelectionAllowed(false);
		table_2.setCellSelectionEnabled(false);
		table_1.setColumnSelectionAllowed(false);
		table_2.setColumnSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		
		JButton button = new JButton("DISPLAY");
		button.setBackground(new Color(0, 128, 128));
		 img = new ImageIcon(this.getClass().getResource("/display.png")).getImage();
		button.setIcon(new ImageIcon(img));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					model.setRowCount(0);
					lblListOfItems.setVisible(false);
					String query = "select * from Inventory where shop=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					String name = textField.getText();
					textField.setText(null);
					pst.setString(1, name);
					ResultSet rs = pst.executeQuery();
				
					
					int count = 0;
					
					while(rs.next())
						count++;
					
					if(count==0)
						JOptionPane.showMessageDialog(null, "Shop do not exist");
					else {		
						rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					lblListOfItems.setVisible(true);}
					rs.close();
					pst.close();
				
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Shop do not exist");
				}
				
			}
		});
		button.setBounds(597, 503, 139, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("DISPLAY");
		button_1.setBackground(new Color(0, 139, 139));
		
		button_1.setBounds(619, 780, 134, 25);
		frame.getContentPane().add(button_1);
		 img = new ImageIcon(this.getClass().getResource("/display.png")).getImage();
			button_1.setIcon(new ImageIcon(img));
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(255, 140, 0));
		  img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		 btnBack.setIcon(new ImageIcon(img));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
		    	AdminHomePage haha = new AdminHomePage();
		    	haha.lol().setVisible(true);
			}
		});
		btnBack.setBounds(40, 939, 117, 36);
		frame.getContentPane().add(btnBack);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					lblListOfItems.setVisible(false);
					DefaultTableModel model = (DefaultTableModel) table_2.getModel();
					model.setRowCount(0);
					String query = "select * from Inventory where name=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					String name = textField_1.getText();
					textField_1.setText(null);
					pst.setString(1, name);
					ResultSet rs = pst.executeQuery();int count = 0;
					
					while(rs.next())
						count++;
					if(count==0)
						JOptionPane.showMessageDialog(null, "Item do not exist");
					else {			
						rs = pst.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					lblListOfItems_1.setVisible(true);}
					rs.close();
					pst.close();
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Item do not exist");
				}
				
			}
		});
	}
	 public JFrame lol()
	    {
	    	return frame;
	    }
}
