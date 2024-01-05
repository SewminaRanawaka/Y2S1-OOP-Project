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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Nurse_Profile {

	JFrame frm_nurseprof;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	private JTextField txt_name;
	private JTextField txt_phoneno;
	private JTextField txt_dob;
	private JTextField txt_email;
	private JTextArea txt_address;
	private JTextArea txt_qualification;
	JLabel lbl_nurseid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nurse_Profile window = new Nurse_Profile();
					window.frm_nurseprof.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nurse_Profile() {
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
	
	public void data_load(String nid)
	{
		try
		{
			pst=con.prepareStatement("select*from nurse where NurseID=?");
			pst.setString(1, nid);
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
		frm_nurseprof = new JFrame();
		frm_nurseprof.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_nurseprof.setBounds(100, 100, 720, 504);
		frm_nurseprof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_nurseprof.getContentPane().setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Login Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(365, 89, 310, 84);
		frm_nurseprof.getContentPane().add(panel_2);
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 89, 310, 365);
		frm_nurseprof.getContentPane().add(panel_1);
		
		JLabel lblNurseId = new JLabel("Nurse ID");
		lblNurseId.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(125, 57, 155, 20);
		panel_1.add(txt_name);
		
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
		
		lbl_nurseid = new JLabel("");
		lbl_nurseid.setBounds(125, 29, 155, 18);
		panel_1.add(lbl_nurseid);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("update nurse set Username=?,Password=?,Name=?,Address=?,Phone_No=?,Email=?,Date_of_birth=?,Qualification=? where NurseID=?");
					
					pst.setString(1, txt_uname.getText());
					pst.setString(2, txt_pwd.getText());
					pst.setString(3, txt_name.getText());
					pst.setString(4, txt_address.getText());
					pst.setInt(5, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(6, txt_email.getText());
					pst.setString(7, txt_dob.getText());
					pst.setString(8, txt_qualification.getText());
					pst.setString(9, lbl_nurseid.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Update personal information");
					data_load(lbl_nurseid.getText());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(452, 431, 89, 23);
		frm_nurseprof.getContentPane().add(btn_update);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst=con.prepareStatement("select * from nurse where NurseID = ?");
					pst.setString(1, lbl_nurseid.getText());
					rs = pst.executeQuery();
					if(rs.next())
					{
						frm_nurseprof.dispose();	
						Nurse n = new Nurse(rs.getString("NurseID"),rs.getString("Name"));
						n.frm_nurse.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_back.setBounds(593, 431, 89, 23);
		frm_nurseprof.getContentPane().add(btn_back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				lbl_nurseid.setText(d1.getValueAt(selectedIndex, 0).toString());
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
		scrollPane.setBounds(10, 11, 684, 59);
		frm_nurseprof.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
