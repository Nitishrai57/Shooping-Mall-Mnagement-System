import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;

public class RegistrationShopkeeper {
	

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	int index,count=0,oops;
	Connection con1=sqliteConnection.dbConnector() ;
	Statement st1;
    String name,gender="",dob1="",dob2="",dob="",dobb="",password="",mobileNumber="",email="",area="",state="",username="",selectedState="";
	String[] states = new String[50];
	String shopname="";
	private JPasswordField passwordField;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationShopkeeper window = new RegistrationShopkeeper();
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
	public RegistrationShopkeeper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setTitle("Register Shopkeeper");
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistrationForm = new JLabel("REGISTRATION FORM");
		lblRegistrationForm.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 26));
		lblRegistrationForm.setBounds(187, 9, 368, 29);
		frame.getContentPane().add(lblRegistrationForm);
		
		JLabel lblUsername = new JLabel("NAME");
		lblUsername.setForeground(new Color(0, 0, 128));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(24, 54, 136, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(0, 0, 128));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(24, 444, 136, 20);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setForeground(new Color(0, 0, 128));
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGender.setBounds(24, 103, 108, 16);
		frame.getContentPane().add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("MALE");
		rdbtnMale.setBackground(new Color(255, 160, 122));
		rdbtnMale.setForeground(new Color(0, 0, 128));
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnMale.setBounds(135, 101, 68, 25);
		frame.getContentPane().add(rdbtnMale);
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			gender = "Male";
			}catch(Exception e1)
				{
				JOptionPane.showMessageDialog(null, e1);	
			}}
				
			});
		
		JRadioButton rdbtnFemale = new JRadioButton("FEMALE");
		rdbtnFemale.setBackground(new Color(255, 160, 122));
		rdbtnFemale.setForeground(new Color(0, 0, 128));
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFemale.setBounds(226, 101, 101, 25);
		frame.getContentPane().add(rdbtnFemale);
		ButtonGroup jb = new ButtonGroup();
		jb.add(rdbtnMale);
		jb.add(rdbtnFemale);
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			gender = "Female";
			}
			});
		
		JLabel lblDateOfBirth = new JLabel("DATE OF BIRTH");
		lblDateOfBirth.setForeground(new Color(0, 0, 128));
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateOfBirth.setBounds(24, 146, 179, 16);
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblMobileNumber = new JLabel("MOBILE NUMBER");
		lblMobileNumber.setForeground(new Color(0, 0, 128));
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMobileNumber.setBounds(24, 194, 179, 16);
		frame.getContentPane().add(lblMobileNumber);
		
		JLabel lblEmailid = new JLabel("EMAIL-ID");
		lblEmailid.setForeground(new Color(0, 0, 128));
		lblEmailid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailid.setBounds(24, 239, 121, 16);
		frame.getContentPane().add(lblEmailid);
		
		JLabel lblAddress = new JLabel("ADDRRESS");
		lblAddress.setForeground(new Color(0, 0, 128));
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(24, 296, 121, 16);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblState = new JLabel("STATE");
		lblState.setForeground(new Color(0, 0, 128));
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblState.setBounds(24, 347, 121, 16);
		frame.getContentPane().add(lblState);
		
		JLabel lblUsername_1 = new JLabel("USERNAME");
		lblUsername_1.setForeground(new Color(0, 0, 128));
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername_1.setBounds(24, 405, 136, 16);
		frame.getContentPane().add(lblUsername_1);
		
		textField = new JTextField();
		textField.setBounds(135, 50, 223, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(187, 143, 47, 29);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(247, 146, 47, 29);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(311, 146, 47, 29);
		frame.getContentPane().add(textField_3);
		
		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy.setForeground(new Color(0, 0, 128));
		lblddmmyyyy.setFont(new Font("Dialog", Font.BOLD, 18));
		lblddmmyyyy.setBounds(386, 149, 147, 16);
		frame.getContentPane().add(lblddmmyyyy);
		
		textField_4 = new JTextField();
		textField_4.setBounds(210, 191, 180, 29);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(135, 236, 180, 29);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(145, 289, 245, 34);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		textField.setBorder(new LineBorder(Color.black, 5));
		textField_1.setBorder(new LineBorder(Color.black, 5));
		textField_2.setBorder(new LineBorder(Color.black, 5));
		textField_3.setBorder(new LineBorder(Color.black, 5));
		textField_4.setBorder(new LineBorder(Color.black, 5));
		textField_5.setBorder(new LineBorder(Color.black, 5));
		textField_6.setBorder(new LineBorder(Color.black, 5));
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(115, 341, 239, 29);
		frame.getContentPane().add(comboBox);
		comboBox.setBorder(new LineBorder(Color.black, 5));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
			Object selectedStateobj = comboBox.getSelectedItem();
			selectedState = String.valueOf(selectedStateobj);
			index = comboBox.getSelectedIndex();

			}
			});
		
		textField_7 = new JTextField();
		textField_7.setBounds(145, 402, 232, 29);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		textField_7.setBorder(new LineBorder(Color.black, 5));
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBackground(new Color(0, 128, 128));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBounds(316, 527, 200, 43);
		frame.getContentPane().add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 444, 233, 24);
		frame.getContentPane().add(passwordField);
		passwordField.setBorder(new LineBorder(Color.black, 5));
		
		JLabel lblShopName = new JLabel("SHOP NAME");
		lblShopName.setForeground(new Color(0, 0, 128));
		lblShopName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblShopName.setBounds(24, 485, 121, 24);
		frame.getContentPane().add(lblShopName);
		
		textField_8 = new JTextField();
		textField_8.setBounds(145, 477, 223, 34);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		textField_8.setBorder(new LineBorder(Color.black, 5));
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(255, 140, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con1.close();
					frame.dispose();
			    	HomePage haha = new HomePage();
			    	haha.lol().setVisible(true);
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
		    	
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(135, 534, 136, 29);
		frame.getContentPane().add(btnBack);
		Image img = new ImageIcon(this.getClass().getResource("/employee.png")).getImage();
		btnRegister.setIcon(new ImageIcon(img));
		 img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
			btnBack.setIcon(new ImageIcon(img));
			
		btnRegister.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent event)
		{
		oops=0;
		getUserInformation();
		wrongInput();
		if(oops==0) {
		validation();
		registerUserData();}

		}
		});
		states[0] = "";
		states[1] = "Andra Pradesh";
		states[2] = "Arunachal Pradesh";
	
		states[3] = "Bihar";
		states[4] = "Chhattisgarh";
		states[5] = "Goa";
		states[6] = "Gujarat";
		states[7] = "Haryana";
		states[8] = "Himachal Pradesh";
		states[9] = "Jammu and Kashmir";
		states[10] = "Jharkhand";
		states[11] = "Karnataka";
		states[12] = "Kerala";
		states[13] = "Madya Pradesh";
		states[14] = "Maharashtra";
		states[15] = "Manipur";
		states[16] = "Meghalaya";
		states[17] = "Mizoram";
		states[18] = "Nagaland";
		states[19] = "Orissa";
		states[20] = "Punjab";
		states[21] = "Rajasthan";
		states[22] = "Sikkim";
		states[23] = "Tamil Nadu";
		states[24] = "Tripura";
		states[25] = "Uttaranchal";
		states[26] = "Uttar Pradesh";
		states[27] = "West Bengal";
		states[28] = "Andaman and Nicobar Islands";
		states[29] = "Chandigarh";
		states[30] = "Dadar and Nagar Haveli";
		states[31] = "Daman and Diu";
		states[32] = "Delhi";
		states[33] = "Lakshadeep";
		states[34] = "Pondicherry";
		for (int j= 0 ; j< 35; j++) { comboBox.addItem(states[j]); }
		
	
		
			}
		
		
		
		
	
    public void getUserInformation() {
    	name = textField.getText();
    	dob =  textField_1.getText();
    	dob1 = textField_2.getText();
    	dob2 = textField_3.getText();
    	dobb = dob + "/" + dob1 + "/" + dob2;
    	mobileNumber = textField_4.getText();
    	email = textField_5.getText();
    	area = textField_6.getText();
    	state = selectedState;
    	username = textField_7.getText();
    	password = passwordField.getText();	
    	shopname = textField_8.getText();
    }
    public void wrongInput()
    {
 
    	int l = name.length();
    	int i;
    	for(i=0;i<l;i++)
    	{
    		char c = name.charAt(i);
    		if(!(((c>='a' || c>='A') && (c<='Z' || c<='z')) || (c==' ')))
    		{
    			JOptionPane.showMessageDialog(null,"Enter Correct Name");
    			oops=1;
    			break;
    		
    		}			
    	}
    	if(oops==1)
    		return;
    	l = dob.length();
    	for(i=0;i<l;i++)
    	{
    		char c = dob.charAt(i);
    		if(!((c>='0') && (c<='9')))
    		{oops=1;
    			JOptionPane.showMessageDialog(null,"Enter Correct date");
    			break;
    		
    		}			
    	}
    	if(oops==1)
    		return;
    	l = dob1.length();
    	for(i=0;i<l;i++)
    	{
    		char c = dob1.charAt(i);
    		if(!((c>='0') && (c<='9')))
    		{oops=1;
    			JOptionPane.showMessageDialog(null,"Enter Correct month");
    			break;
    		
    		}			
    	}
    	if(oops==1)
    		return;
    	l = dob2.length();
    	for(i=0;i<l;i++)
    	{
    		char c = dob2.charAt(i);
    		if(!((c>='0') && (c<='9')))
    		{oops=1;
    			JOptionPane.showMessageDialog(null,"Enter Correct year");
    			break;
    		
    		}			
    	}
    	if(oops==1)
    		return;
    	l = mobileNumber.length();
    	for(i=0;i<l;i++)
    	{
    		char c = mobileNumber.charAt(i);
    		if(!((c>='0') && (c<='9')))
    		{oops=1;
    			JOptionPane.showMessageDialog(null,"Enter Correct mobile number");
    			break;
    		
    		}			
    	}
    	

    }
    public void validation() {
    	count = 0;
    	 if(name.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Name");
    	}
    	else if(password.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Password");
    	}
    	else if(dob.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Date");
    	}
    	else if(dob1.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Month");
    	}
    	else if(state.equals(""))
    			{
    		JOptionPane.showMessageDialog(null,"Enter State");
    			}
    	else if(dob2.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Year");
    	}

    	else if(mobileNumber.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Mobile Number");
    	}

    	else if(email.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter E-Mail address");
    	}

    	else if(area.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter address");
    	}

    	else if(selectedState.equals(states[0]))
    	{
    	JOptionPane.showMessageDialog(null,"Select State");
    	}
    	else if(username.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Enter Username");
    	}
    	else if(gender.equals(""))
    	{
    	JOptionPane.showMessageDialog(null,"Select gender");
    	}
    	else if(shopname.equals(""))
    	{
    		JOptionPane.showMessageDialog(null,"Give Name");
    	}
    	else {
    	if((Integer.parseInt(dob2)<=3000) && (Integer.parseInt(dob2)>=1950))
    	{
    	if((Integer.parseInt(dob1)<=12) && (Integer.parseInt(dob1)>0))
    	{
    	if((Integer.parseInt(dob1)==1) || (Integer.parseInt(dob1)==3) || (Integer.parseInt(dob1)==5) || (Integer.parseInt(dob1)==7) || (Integer.parseInt(dob1)==8) || (Integer.parseInt(dob1)==10) || (Integer.parseInt(dob1)==12))
    	{
    	if((Integer.parseInt(dob)<=31) && (Integer.parseInt(dob)>=1))
    	{
    	dobb=dob+"/"+dob1+"/"+dob2;

    	if(email.contains("@") && email.contains(".com")) {
    	if(mobileNumber.length()==10) {
    	count++;
    	}
    	else {
    	JOptionPane.showMessageDialog(null,"Enter 10-digit moblie number");
    	}
    	}
    	else {
    	JOptionPane.showMessageDialog(null,"Enter valid mail ID");
    	}

    	}
    	else
    	{
    	JOptionPane.showMessageDialog(null,"Invalid Date");
    	}
    	}
    	else if((Integer.parseInt(dob1)==4) || (Integer.parseInt(dob1)==6) || (Integer.parseInt(dob1)==9) || (Integer.parseInt(dob1)==11))
    	{
    	if((Integer.parseInt(dob)<=30) && (Integer.parseInt(dob)>=1))
    	{
    	dobb=dob+"/"+dob1+"/"+dob2;

    	if(email.contains("@") && email.contains(".com")) {
    	if(mobileNumber.length()==10) {
    	count++;
    	}
    	else {
    	JOptionPane.showMessageDialog(null,"Enter 10-digit moblie number");
    	}
    	}
    	else {
    	JOptionPane.showMessageDialog(null,"Enter valid mail ID");
    	}

    	}
    	else
    	{
    	JOptionPane.showMessageDialog(null,"Invalid Date");
    	}
    	}
    	else
    	{
    	if(((Integer.parseInt(dob2)%100)==0) || ((Integer.parseInt(dob2)%4)==0))
    	{
    	if((Integer.parseInt(dob)<=29) && (Integer.parseInt(dob)>=1))
    	{
    	dobb=dob+"/"+dob1+"/"+dob2;
    	}
    	else
    	{
    	JOptionPane.showMessageDialog(null,"Invalid Date");
    	}
    	}
    	else
    	{
    	if((Integer.parseInt(dob)<=28) && (Integer.parseInt(dob)>=1))
    	{
    	dobb=dob+"/"+dob1+"/"+dob2;
    	}
    	else
    	{
    	JOptionPane.showMessageDialog(null,"Invalid Date");
    	}
    	}
    	}
    	}
    	else
    	{
    	JOptionPane.showMessageDialog(null,"Enter month between 1 and 12");
    	}
    	}
    	else
    	{
    	JOptionPane.showMessageDialog(null,"Enter year between 1950 and 3000");
    	}
    	}
    	}
    public void registerUserData() {
    	try {

    	if(count==1) {
    		

    	
    	st1 = con1.createStatement();
    	st1.execute("INSERT INTO ShopkeeperInfo (Name,Gender,Password,DOB,Mobile_Number,Email,Address,State,Username,shop_name) VALUES('"+name+"','"+gender+"','"+password+"','"+dobb+"','"+mobileNumber+"','"+email+"','"+area+"','"+state+"','"+username+"','"+shopname+"')");
    	JOptionPane.showMessageDialog(null,"Data are Registered Successfully");
    	st1.close();
    	con1.close();
    	frame.dispose();
    	HomePage haha = new HomePage();
    	haha.lol().setVisible(true);
    	
    	
    	}

    	}
    	catch (Exception e) {
    		JOptionPane.showMessageDialog(null,e);
    	}

    	}
    public JFrame lol()
    {
    	return frame;
    }
}