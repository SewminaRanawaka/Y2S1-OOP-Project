import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Admin {

	JFrame frm_addadmin;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	private JLabel lbl_adminid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Admin window = new Add_Admin();
					window.frm_addadmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Add_Admin() {
		initialize();
		connector();
		AutoID();
	}
	
	String adminid;
	String adminname;
	public Add_Admin(String aid, String aname) {
		initialize();
		this.adminid=aid;
		this.adminname=aname;
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btn_viewchannel_1;
	
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

	
	public void AutoID()
	{
		try
		{
			Statement s = con.createStatement();
			rs=s.executeQuery("select Max(AdminID) from administrator");
			rs.next();
			rs.getString("MAX(AdminID)");
			
			if(rs.getString("MAX(AdminID)")==null)
			{
				lbl_adminid.setText("AD001");
			}
			else
			{
				long id = Long.parseLong(rs.getString("MAX(AdminID)").substring(2,rs.getString("MAX(AdminID)").length()));
				id++;
				
				lbl_adminid.setText("AD"+String.format("%03d", id));
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
		frm_addadmin = new JFrame();
		frm_addadmin.setBounds(100, 100, 367, 309);
		frm_addadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_addadmin.getContentPane().setLayout(null);
		frm_addadmin.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(38, 88, 65, 20);
		frm_addadmin.getContentPane().add(lblNewLabel);
		
		txt_uname = new JTextField();
		txt_uname.setColumns(10);
		txt_uname.setBounds(154, 89, 155, 20);
		frm_addadmin.getContentPane().add(txt_uname);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(38, 133, 65, 20);
		frm_addadmin.getContentPane().add(lblPassword);
		
		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(154, 134, 155, 20);
		frm_addadmin.getContentPane().add(txt_pwd);
		
		JLabel lblAdministratorId = new JLabel("Administrator ID");
		lblAdministratorId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdministratorId.setBounds(38, 40, 105, 20);
		frm_addadmin.getContentPane().add(lblAdministratorId);
		
		lbl_adminid = new JLabel("");
		lbl_adminid.setBounds(153, 40, 155, 18);
		frm_addadmin.getContentPane().add(lbl_adminid);
		
		JButton btn_add = new JButton("Add");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst =con.prepareStatement("insert into administrator (AdminID,Username,Password) values (?,?,?)");
					pst.setString(1, lbl_adminid.getText());
					pst.setString(2, txt_uname.getText());
					pst.setString(3, txt_pwd.getText());
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Add User?");
					AutoID();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_add.setBounds(220, 196, 89, 23);
		frm_addadmin.getContentPane().add(btn_add);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_addadmin.dispose();
			}
		});
		btn_back.setBounds(220, 230, 89, 23);
		frm_addadmin.getContentPane().add(btn_back);
	}
}
