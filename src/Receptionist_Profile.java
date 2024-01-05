import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Receptionist_Profile {

	JFrame frm_recepprof;
	private JTextField txt_name;
	private JTextField txt_phoneno;
	private JTextField txt_dob;
	private JTextField txt_email;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	JLabel lbl_recepid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist_Profile window = new Receptionist_Profile();
					window.frm_recepprof.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Receptionist_Profile() {
		initialize();
		connector();
		data_load(null);
	}
	
	public Receptionist_Profile(String rid) {
		initialize();
		lbl_recepid.setText(rid);
		
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
	
	public void data_load(String rid)
	{
		try
		{
			pst=con.prepareStatement("select*from receptionist where ReceptionistID=?");
			pst.setString(1, rid);
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
		frm_recepprof = new JFrame();
		frm_recepprof.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_recepprof.setBounds(100, 100, 684, 468);
		frm_recepprof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_recepprof.getContentPane().setLayout(null);
		frm_recepprof.setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 94, 310, 324);
		frm_recepprof.getContentPane().add(panel_1);
		
		JLabel lblNurseId = new JLabel("Receptionist ID");
		lblNurseId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNurseId.setBounds(10, 25, 90, 20);
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
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(125, 57, 155, 20);
		panel_1.add(txt_name);
		
		txt_phoneno = new JTextField();
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
		
		lbl_recepid = new JLabel("");
		lbl_recepid.setBounds(125, 29, 155, 18);
		panel_1.add(lbl_recepid);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Login Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(348, 94, 310, 84);
		frm_recepprof.getContentPane().add(panel_2);
		
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
		txt_uname.setBounds(126, 26, 155, 20);
		panel_2.add(txt_uname);
		
		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(126, 54, 155, 20);
		panel_2.add(txt_pwd);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
					pst = con.prepareStatement("update receptionist set Username=?,Password=?,Name=?,Address=?,Phone_No=?,Email=?,Date_of_birth=?,Qualification=? where ReceptionistID=?");
					
					pst.setString(1, txt_uname.getText());
					pst.setString(2, txt_pwd.getText());
					pst.setString(3, txt_name.getText());
					pst.setString(4, txt_address.getText());
					pst.setInt(5, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(6, txt_email.getText());
					pst.setString(7, txt_dob.getText());
					pst.setString(8, txt_qualification.getText());
					pst.setString(9, lbl_recepid.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Update personal information");
					data_load(lbl_recepid.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(428, 367, 89, 23);
		frm_recepprof.getContentPane().add(btn_update);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst=con.prepareStatement("select * from receptionist where ReceptionistID = ?");
					pst.setString(1, lbl_recepid.getText());
					rs = pst.executeQuery();
					if(rs.next())
					{
						frm_recepprof.dispose();	
						Receptionist r = new Receptionist(rs.getString("ReceptionistID"),rs.getString("Name"));
						r.frm_receptionist.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_back.setBounds(569, 367, 89, 23);
		frm_recepprof.getContentPane().add(btn_back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				lbl_recepid.setText(d1.getValueAt(selectedIndex, 0).toString());
				txt_uname.setText(d1.getValueAt(selectedIndex, 1).toString());
				txt_pwd.setText(d1.getValueAt(selectedIndex, 2).toString());
				txt_name.setText(d1.getValueAt(selectedIndex, 3).toString());
				txt_address.setText(d1.getValueAt(selectedIndex, 4).toString());
				txt_phoneno.setText(d1.getValueAt(selectedIndex, 5).toString());
				txt_email.setText(d1.getValueAt(selectedIndex, 6).toString());
				txt_dob.setText(d1.getValueAt(selectedIndex, 7).toString());
				txt_qualification.setText(d1.getValueAt(selectedIndex,8).toString());
			}
		});
		scrollPane.setBounds(10, 11, 648, 63);
		frm_recepprof.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
