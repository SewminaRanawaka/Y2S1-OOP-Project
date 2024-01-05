import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Administrator_Profile {

	JFrame frm_adminprof;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	private JTextField txt_name;
	private JTextField txt_phoneno;
	private JTextField txt_email;
	JLabel lbl_adminid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator_Profile window = new Administrator_Profile();
					window.frm_adminprof.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Administrator_Profile() {
		initialize();
		connector();
		data_load(null);
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
		}
		catch(Exception e)
		{
			System.out.print("Database connection failed");
			e.printStackTrace();
		}
	}
	
	public void data_load(String aid)
	{
		try
		{
			pst=con.prepareStatement("select*from administrator where AdminID=?");
			pst.setString(1, aid);
			rs = pst.executeQuery();
			if(rs.next())
			{
				lbl_adminid.setText(rs.getString("AdminID"));
				txt_name.setText(rs.getString("Name"));
				txt_phoneno.setText(rs.getString("Phone_No"));
				txt_email.setText(rs.getString("Email"));
				txt_uname.setText(rs.getString("Username"));
				txt_pwd.setText(rs.getString("Password"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_adminprof = new JFrame();
		frm_adminprof.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_adminprof.setBounds(100, 100, 672, 270);
		frm_adminprof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_adminprof.getContentPane().setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Login Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(365, 27, 281, 84);
		frm_adminprof.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 25, 65, 20);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword_1.setBounds(10, 53, 65, 20);
		panel_2.add(lblPassword_1);
		
		txt_uname = new JTextField();
		txt_uname.setColumns(10);
		txt_uname.setBounds(109, 25, 155, 20);
		panel_2.add(txt_uname);
		
		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(109, 53, 155, 20);
		panel_2.add(txt_pwd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 27, 319, 168);
		frm_adminprof.getContentPane().add(panel_1);
		
		JLabel lblAdministratorId = new JLabel("Administrator ID");
		lblAdministratorId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdministratorId.setBounds(10, 25, 104, 20);
		panel_1.add(lblAdministratorId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(10, 56, 77, 20);
		panel_1.add(lblName);
		
		JLabel lblPhoneno = new JLabel("Phone No");
		lblPhoneno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneno.setBounds(10, 87, 77, 20);
		panel_1.add(lblPhoneno);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(145, 56, 155, 20);
		panel_1.add(txt_name);
		
		txt_phoneno = new JTextField();
		txt_phoneno.setColumns(10);
		txt_phoneno.setBounds(145, 87, 155, 20);
		panel_1.add(txt_phoneno);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 118, 77, 20);
		panel_1.add(lblEmail);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(145, 120, 155, 20);
		panel_1.add(txt_email);
		
		lbl_adminid = new JLabel("");
		lbl_adminid.setBounds(145, 29, 155, 20);
		panel_1.add(lbl_adminid);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("update administrator set Username=?,Password=?,Name=?,Phone_No=?,Email=? where AdminID=?");
					
					pst.setString(1, txt_uname.getText());
					pst.setString(2, txt_pwd.getText());
					pst.setString(3, txt_name.getText());
					pst.setInt(4, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(5, txt_email.getText());
					pst.setString(6, lbl_adminid.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Update personal information");
					data_load(lbl_adminid.getText());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(416, 172, 89, 23);
		frm_adminprof.getContentPane().add(btn_update);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst=con.prepareStatement("select * from administrator where AdminID = ?");
					pst.setString(1, lbl_adminid.getText());
					rs = pst.executeQuery();
					if(rs.next())
					{
						frm_adminprof.dispose();	
						Administrator ad = new Administrator(rs.getString("AdminID"),rs.getString("Name"));
						ad.frm_admin.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_back.setBounds(557, 172, 89, 23);
		frm_adminprof.getContentPane().add(btn_back);
	}
}
