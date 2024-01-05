import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;


import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class Nurse_Admin extends Administrator{

	protected JFrame frm_nurseAdmin;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	private JTextField txt_nurseid;
	private JTextField txt_name;
	private JTextField txt_phoneno;
	private JTextField txt_dob;
	private JTextField txt_search;
	private JTextField txt_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nurse_Admin window = new Nurse_Admin();
					window.frm_nurseAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nurse_Admin() {
		initialize();
		connector();
		AutoID();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
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
	
	public void AutoID()
	{
		try
		{
			Statement s = con.createStatement();
			rs=s.executeQuery("select Max(NurseID) from nurse");
			rs.next();
			rs.getString("MAX(NurseID)");
			
			if(rs.getString("MAX(NurseID)")==null)
			{
				txt_nurseid.setText("NN0001");
			}
			else
			{
				long id = Long.parseLong(rs.getString("MAX(NurseID)").substring(2,rs.getString("MAX(NurseID)").length()));
				id++;
				
				txt_nurseid.setText("NN"+String.format("%04d", id));
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("select*from nurse");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
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
		frm_nurseAdmin = new JFrame();
		frm_nurseAdmin.getContentPane().setBackground(new Color(0, 204, 153));
		frm_nurseAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_nurseAdmin.setBounds(100, 100, 1218, 587);
		frm_nurseAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_nurseAdmin.getContentPane().setLayout(null);
		frm_nurseAdmin.setLocationRelativeTo(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(10, 64, 310, 84);
		frm_nurseAdmin.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Login Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 25, 65, 20);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword_1.setBounds(10, 53, 65, 20);
		panel_2.add(lblPassword_1);
		
		txt_uname = new JTextField();
		txt_uname.setColumns(10);
		txt_uname.setBounds(126, 26, 155, 20);
		panel_2.add(txt_uname);
		
		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(126, 54, 155, 20);
		panel_2.add(txt_pwd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 159, 310, 365);
		frm_nurseAdmin.getContentPane().add(panel_1);
		
		JLabel lblNurseId = new JLabel("Nurse ID");
		lblNurseId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNurseId.setBounds(10, 25, 77, 20);
		panel_1.add(lblNurseId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(10, 56, 77, 20);
		panel_1.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(10, 99, 77, 20);
		panel_1.add(lblAddress);
		
		JLabel lblPhoneno = new JLabel("Phone No");
		lblPhoneno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneno.setBounds(10, 148, 77, 20);
		panel_1.add(lblPhoneno);
		
		txt_nurseid = new JTextField();
		txt_nurseid.setColumns(10);
		txt_nurseid.setBounds(125, 26, 155, 20);
		panel_1.add(txt_nurseid);
		txt_nurseid.setEditable(false);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(125, 57, 155, 20);
		panel_1.add(txt_name);
		
		/*#########################################Validation################################*/
		
		txt_phoneno = new JTextField();
		txt_phoneno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			
				char c = e.getKeyChar();
				if(Character.isLetter(c))
				{
					txt_phoneno.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter number only");
					txt_phoneno.setEditable(true);
				}
				else
				{
					txt_phoneno.setEditable(true);
				}
				
			}
		});
		txt_phoneno.setColumns(10);
		txt_phoneno.setBounds(125, 149, 155, 20);
		panel_1.add(txt_phoneno);
		
		txt_dob = new JTextField();
		txt_dob.setColumns(10);
		txt_dob.setBounds(125, 222, 155, 20);
		panel_1.add(txt_dob);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 179, 77, 20);
		panel_1.add(lblEmail);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(10, 221, 77, 20);
		panel_1.add(lblDateOfBirth);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQualification.setBounds(10, 256, 77, 20);
		panel_1.add(lblQualification);
		
		JTextArea txt_address = new JTextArea();
		txt_address.setBounds(125, 98, 155, 37);
		panel_1.add(txt_address);
		
		JTextArea txt_qualification = new JTextArea();
		txt_qualification.setBounds(125, 256, 155, 48);
		panel_1.add(txt_qualification);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(125, 182, 155, 20);
		panel_1.add(txt_email);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setOpaque(false);
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(330, 466, 310, 58);
		frm_nurseAdmin.getContentPane().add(panel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Search");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 24, 58, 16);
		panel_2_1.add(lblNewLabel_1_1);
		
		/////////////////Search function////////////////
		txt_search = new JTextField();
		txt_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					pst = con.prepareStatement("select * from nurse where name = ?");
					pst.setString(1, txt_search.getText());
					rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txt_search.setColumns(10);
		txt_search.setBounds(102, 23, 183, 20);
		panel_2_1.add(txt_search);
		
		///////////////////////Insert function////////////////////////
		JButton btn_save = new JButton("Save");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("insert into nurse(NurseID,Username,Password,Name,Address,Phone_No,Email,Date_of_birth,Qualification) values(?,?,?,?,?,?,?,?,?)");
					
					pst.setString(1, txt_nurseid.getText());
					pst.setString(2, txt_uname.getText());
					pst.setString(3, txt_pwd.getText());
					pst.setString(4, txt_name.getText());
					pst.setString(5, txt_address.getText());
					pst.setInt(6, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(7, txt_email.getText());
					pst.setString(8, txt_dob.getText());
					pst.setString(9, txt_qualification.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Add Record");
					AutoID();
					
					txt_uname.setText("");
					txt_pwd.setText("");
					txt_name.setText("");
					txt_address.setText("");
					txt_phoneno.setText("");
					txt_email.setText("");
					txt_dob.setText("");
					txt_qualification.setText("");
					txt_uname.requestFocus();
					
					table_load();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_save.setBounds(668, 480, 89, 23);
		frm_nurseAdmin.getContentPane().add(btn_save);
		
		///////////////////////////Update function/////////////////////////////
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					pst = con.prepareStatement("update nurse set Username=?,Password=?,Name=?,Address=?,Phone_No=?,Email=?,Date_of_birth=?,Qualification=? where NurseID=?");
					
					pst.setString(1, txt_uname.getText());
					pst.setString(2, txt_pwd.getText());
					pst.setString(3, txt_name.getText());
					pst.setString(4, txt_address.getText());
					pst.setInt(5, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(6, txt_email.getText());
					pst.setString(7, txt_dob.getText());
					pst.setString(8, txt_qualification.getText());
					pst.setString(9, txt_nurseid.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Update record");
					AutoID();
					table_load();
					
					txt_uname.setText("");
					txt_pwd.setText("");
					txt_name.setText("");
					txt_address.setText("");
					txt_phoneno.setText("");
					txt_email.setText("");
					txt_dob.setText("");
					txt_qualification.setText("");
					txt_uname.requestFocus();
					
					btn_save.setEnabled(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(775, 480, 89, 23);
		frm_nurseAdmin.getContentPane().add(btn_update);
		
		/////////////////////Delete function//////////////////////////
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("delete from nurse where NurseID=?");
					
					pst.setString(1, txt_nurseid.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Delete Rocord");
					
					
					txt_uname.setText("");
					txt_pwd.setText("");
					txt_name.setText("");
					txt_address.setText("");
					txt_phoneno.setText("");
					txt_email.setText("");
					txt_dob.setText("");
					txt_qualification.setText("");
					txt_uname.requestFocus();
					
					table_load();
					AutoID();
					btn_save.setEnabled(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_delete.setBounds(890, 480, 89, 23);
		frm_nurseAdmin.getContentPane().add(btn_delete);
		
		JButton btn_clear = new JButton("Clear");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt_uname.setText("");
				txt_pwd.setText("");
				txt_name.setText("");
				txt_address.setText("");
				txt_phoneno.setText("");
				txt_email.setText("");
				txt_dob.setText("");
				txt_qualification.setText("");
				txt_uname.requestFocus();
				txt_search.setText("");
				AutoID();
				table_load();
				btn_save.setEnabled(true);
			}
		});
		btn_clear.setBounds(996, 480, 89, 23);
		frm_nurseAdmin.getContentPane().add(btn_clear);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_nurseAdmin.dispose();
			}
		});
		btn_back.setBounds(1095, 480, 89, 23);
		frm_nurseAdmin.getContentPane().add(btn_back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				txt_nurseid.setText(d1.getValueAt(selectedIndex, 0).toString());
				txt_uname.setText(d1.getValueAt(selectedIndex, 1).toString());
				txt_pwd.setText(d1.getValueAt(selectedIndex, 2).toString());
				txt_name.setText(d1.getValueAt(selectedIndex, 3).toString());
				txt_address.setText(d1.getValueAt(selectedIndex, 4).toString());
				txt_phoneno.setText(d1.getValueAt(selectedIndex, 5).toString());
				txt_email.setText(d1.getValueAt(selectedIndex, 6).toString());
				txt_dob.setText(d1.getValueAt(selectedIndex, 7).toString());
				txt_qualification.setText(d1.getValueAt(selectedIndex,8).toString());
				
				btn_save.setEnabled(false);
			}
		});
		scrollPane.setBounds(349, 63, 832, 379);
		frm_nurseAdmin.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Nurse");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(614, 11, 82, 30);
		frm_nurseAdmin.getContentPane().add(lblNewLabel);
	}
}
