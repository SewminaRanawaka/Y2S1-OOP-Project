import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Doctor_Profile {

	JFrame frm_docprof;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	private JTextField txt_name;
	private JTextField txt_phoneno;
	private JTextField txt_email;
	private JTextField txt_specialization;
	private JTextField txt_channelf;
	private JTextField txt_roomno;
	private JTextField txt_dob;
	JLabel lbl_docid;
	private JTextArea txt_qualification;
	private JTextArea txt_address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor_Profile window = new Doctor_Profile();
					window.frm_docprof.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Doctor_Profile() {
		initialize();
		connector();
		data_load(null);
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
	
	public void data_load(String did)
	{
		try
		{
			pst=con.prepareStatement("select*from doctor where DoctorID=?");
			pst.setString(1, did);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*public void doctor_table()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incare_hospital", "root", "#gayanuka22");
			
			pst= con.prepareStatement("select * from doctor");
			//pst.setString(1, docid);
			rs = pst.executeQuery();
			ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel df = (DefaultTableModel) table.getModel();
			
			int cols = rsm.getColumnCount();
			String[] colName = new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsm.getColumnName(i+1);
			df.setColumnIdentifiers(colName);
			
			while(rs.next())
			{
				String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(3),
							rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
								rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)};
				df.addRow(row);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_docprof = new JFrame();
		frm_docprof.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_docprof.setTitle("User Profile");
		frm_docprof.setBounds(100, 100, 738, 536);
		frm_docprof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_docprof.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Login Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 402, 310, 84);
		frm_docprof.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 25, 65, 20);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 53, 65, 20);
		panel.add(lblPassword);
		
		txt_uname = new JTextField();
		txt_uname.setColumns(10);
		txt_uname.setBounds(126, 26, 155, 20);
		panel.add(txt_uname);
		
		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(126, 54, 155, 20);
		panel.add(txt_pwd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 90, 689, 285);
		frm_docprof.getContentPane().add(panel_1);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDoctorId.setBounds(26, 44, 77, 20);
		panel_1.add(lblDoctorId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(26, 75, 77, 20);
		panel_1.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(26, 106, 77, 20);
		panel_1.add(lblAddress);
		
		JLabel lblPhoneno = new JLabel("Phone No");
		lblPhoneno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneno.setBounds(26, 176, 77, 20);
		panel_1.add(lblPhoneno);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(141, 76, 177, 20);
		panel_1.add(txt_name);
		
		txt_phoneno = new JTextField();
		txt_phoneno.setColumns(10);
		txt_phoneno.setBounds(141, 176, 177, 20);
		panel_1.add(txt_phoneno);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(141, 239, 177, 20);
		panel_1.add(txt_email);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(26, 239, 77, 20);
		panel_1.add(lblEmail);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(26, 210, 77, 20);
		panel_1.add(lblDateOfBirth);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQualification.setBounds(372, 45, 77, 20);
		panel_1.add(lblQualification);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSpecialization.setBounds(372, 176, 83, 20);
		panel_1.add(lblSpecialization);
		
		JLabel lblChanneFee = new JLabel("Channel fee");
		lblChanneFee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChanneFee.setBounds(372, 208, 77, 20);
		panel_1.add(lblChanneFee);
		
		JLabel lblroomNo = new JLabel("Room No");
		lblroomNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblroomNo.setBounds(372, 239, 77, 20);
		panel_1.add(lblroomNo);
		
		txt_address = new JTextArea();
		txt_address.setBounds(141, 105, 177, 61);
		panel_1.add(txt_address);
		
		txt_qualification = new JTextArea();
		txt_qualification.setBounds(482, 44, 171, 122);
		panel_1.add(txt_qualification);
		
		txt_specialization = new JTextField();
		txt_specialization.setColumns(10);
		txt_specialization.setBounds(482, 176, 171, 20);
		panel_1.add(txt_specialization);
		
		txt_channelf = new JTextField();
		txt_channelf.setColumns(10);
		txt_channelf.setBounds(482, 208, 171, 20);
		panel_1.add(txt_channelf);
		
		txt_roomno = new JTextField();
		txt_roomno.setColumns(10);
		txt_roomno.setBounds(482, 239, 171, 20);
		panel_1.add(txt_roomno);
		
		txt_dob = new JTextField();
		txt_dob.setColumns(10);
		txt_dob.setBounds(141, 207, 177, 20);
		panel_1.add(txt_dob);
		
		lbl_docid = new JLabel("");
		lbl_docid.setBounds(141, 44, 155, 18);
		panel_1.add(lbl_docid);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst=con.prepareStatement("select * from doctor where DoctorID = ?");
					pst.setString(1, lbl_docid.getText());
					rs = pst.executeQuery();
					if(rs.next())
					{
						frm_docprof.dispose();	
						Doctor d = new Doctor(rs.getString("DoctorID"),rs.getString("Name"));
						d.frm_doctor.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_back.setBounds(623, 463, 89, 23);
		frm_docprof.getContentPane().add(btn_back);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("update doctor set Username=?,Password=?,Name=?,Address=?,Phone_No=?,Email=?,Date_of_birth=?,Qualification=?,Specialization=?,Channel_fee=?,Room_No=? where DoctorID=?");
					
					pst.setString(1, txt_uname.getText());
					pst.setString(2, txt_pwd.getText());
					pst.setString(3, txt_name.getText());
					pst.setString(4, txt_address.getText());
					pst.setInt(5, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(6, txt_email.getText());
					pst.setString(7, txt_dob.getText());
					pst.setString(8, txt_qualification.getText());
					pst.setString(9, txt_specialization.getText());
					pst.setInt(10, Integer.parseInt(txt_channelf.getText()));
					pst.setInt(11, Integer.parseInt(txt_roomno.getText()));
					pst.setString(12, lbl_docid.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Update personal information");
					data_load(lbl_docid.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(499, 463, 89, 23);
		frm_docprof.getContentPane().add(btn_update);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 689, 58);
		frm_docprof.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				lbl_docid.setText(d1.getValueAt(selectedIndex, 0).toString());
				txt_uname.setText(d1.getValueAt(selectedIndex, 1).toString());
				txt_pwd.setText(d1.getValueAt(selectedIndex, 2).toString());
				txt_name.setText(d1.getValueAt(selectedIndex, 3).toString());
				txt_address.setText(d1.getValueAt(selectedIndex, 4).toString());
				txt_phoneno.setText(d1.getValueAt(selectedIndex, 5).toString());
				txt_email.setText(d1.getValueAt(selectedIndex, 6).toString());
				txt_dob.setText(d1.getValueAt(selectedIndex, 7).toString());
				txt_qualification.setText(d1.getValueAt(selectedIndex,8).toString());
				txt_specialization.setText(d1.getValueAt(selectedIndex, 9).toString());
				txt_channelf.setText(d1.getValueAt(selectedIndex, 10).toString());
				txt_roomno.setText(d1.getValueAt(selectedIndex, 11).toString());
			}
		});
	}
}
