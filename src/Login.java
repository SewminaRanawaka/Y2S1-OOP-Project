import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Login {

	public JFrame frm_login;
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JLabel lbl_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frm_login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connector();
		scaleImage();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void connector()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incare_hospital","root","Ss@200105502040");
			System.out.print("Connection Successfull.");
			
		}
		catch(Exception e)
		{
			System.out.print("Database connection failed");
			e.printStackTrace();
		}
	}
	
	public void scaleImage()
	{
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/login.jpg"));
		
		Image img = icon.getImage();
		Image imgscale = img.getScaledInstance(lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgscale);
		lbl_img.setIcon(scaledIcon);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_login = new JFrame();
		frm_login.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_login.setTitle("Login");
		frm_login.setBounds(100, 100, 661, 414);
		frm_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_login.getContentPane().setLayout(null);
		frm_login.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "User Login", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(350, 79, 285, 225);
		frm_login.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 40, 90, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txt_username = new JTextField();
		txt_username.setBounds(135, 42, 133, 20);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 86, 90, 20);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txt_password = new JPasswordField();
		txt_password.setBounds(135, 88, 133, 20);
		panel.add(txt_password);
		
		JLabel lblNewLabel_2 = new JLabel("Usertype");
		lblNewLabel_2.setBounds(10, 133, 90, 20);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JComboBox cmb_usertype = new JComboBox();
		cmb_usertype.setBounds(135, 134, 133, 22);
		panel.add(cmb_usertype);
		cmb_usertype.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Doctor", "Receptionist", "Nurse"}));
		
		JButton btn_login = new JButton("Login");
		btn_login.setForeground(SystemColor.text);
		btn_login.setBackground(SystemColor.textHighlight);
		btn_login.setBounds(10, 190, 89, 23);
		panel.add(btn_login);
		
		JButton btn_back = new JButton("Back");
		btn_back.setForeground(SystemColor.text);
		btn_back.setBackground(SystemColor.textHighlight);
		btn_back.setBounds(179, 190, 89, 23);
		panel.add(btn_back);
		
		lbl_img = new JLabel("");
		lbl_img.setBounds(0, 0, 645, 375);
		frm_login.getContentPane().add(lbl_img);
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_login.dispose();
				StartUp su = new StartUp();
				su.frm_startup.setVisible(true);
			}
		});
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txt_username.getText();
				String password = txt_password.getText();
				//String usertype = String.valueOf(cmb_usertype.getSelectedItem().toString());
				
				if(cmb_usertype.getSelectedIndex()==0) 
				{
					try
					{
						pst = con.prepareStatement("select * from administrator where Username = ? and Password = ?");
						
						pst.setString(1, username);
						pst.setString(2, password);
						
						rs = pst.executeQuery();
						
						if(rs.next())
						{
							String uid = rs.getString("AdminID");
							String uname = rs.getString("Name");
							frm_login.dispose();
							
							Administrator admin = new Administrator(uid,uname);
							admin.frm_admin.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect password or username");
							//txt_username.setText("");
							txt_password.setText("");
							cmb_usertype.setSelectedIndex(-1);
							txt_username.requestFocus();
						}
						
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(cmb_usertype.getSelectedIndex()==1)
				{
					try
					{
						pst = con.prepareStatement("select * from doctor where Username = ? and Password = ?");
						
						pst.setString(1, username);
						pst.setString(2, password);
						
						rs = pst.executeQuery();
						
						if(rs.next())
						{
							String uid = rs.getString("DoctorID");
							String uname = rs.getString("Name");
							frm_login.dispose();
							
							Doctor d = new Doctor(uid,uname);
							//d.lbl_duid.setText(uid);
							//d.lbl_duname.setText(uname);
							d.frm_doctor.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect username or password");
							//txt_username.setText("");
							txt_password.setText("");
							cmb_usertype.setSelectedIndex(-1);
							txt_username.requestFocus();
						}
						
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
				
				if(cmb_usertype.getSelectedIndex()==2)
				{
					try
					{
						pst = con.prepareStatement("select * from receptionist where Username = ? and Password = ?");
						
						pst.setString(1, username);
						pst.setString(2, password);
						
						rs = pst.executeQuery();
						
						if(rs.next())
						{
							String uid = rs.getString("ReceptionistID");
							String uname = rs.getString("Name");
							frm_login.dispose();
							
							Receptionist d = new Receptionist(uid,uname);
							d.frm_receptionist.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect username or password");
							//txt_username.setText("");
							txt_password.setText("");
							cmb_usertype.setSelectedIndex(-1);
							txt_username.requestFocus();
						}
						
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
				
				if(cmb_usertype.getSelectedIndex()==3)
				{
					try
					{
						pst = con.prepareStatement("select * from nurse where Username = ? and Password = ?");
						
						pst.setString(1, username);
						pst.setString(2, password);
						
						rs = pst.executeQuery();
						
						if(rs.next())
						{
							String uid = rs.getString("NurseID");
							String uname = rs.getString("Name");
							frm_login.dispose();
							
							Nurse n = new Nurse();
							n.lbl_nuid.setText(uid);
							n.lbl_nuname.setText(uname);
							n.frm_nurse.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect username or password");
							//txt_username.setText("");
							txt_password.setText("");
							cmb_usertype.setSelectedIndex(-1);
							txt_username.requestFocus();
						}
						
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
