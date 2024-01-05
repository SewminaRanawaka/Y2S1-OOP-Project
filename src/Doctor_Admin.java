import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector; //for Vector

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel; //for DefaultTableModel
import javax.swing.table.TableModel; // for TableModel

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class Doctor_Admin extends Administrator{

	protected JFrame frm_docAdmin;
	private JTextField txt_uname;
	private JTextField txt_pwd;
	private JTextField txt_docid;
	private JTextField txt_name;
	private JTextField txt_phoneno;
	private JTextField txt_email;
	private JTextField txt_specialization;
	private JTextField txt_channelf;
	private JTextField txt_roomno;
	private JTable table;
	private JButton btn_save;
	private JTextField txt_dob;
	private JTextField txt_search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor_Admin window = new Doctor_Admin();
					window.frm_docAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Doctor_Admin() {
		initialize();
		connector();
		AutoID();
		table_load();
	}
	
	String adminid;
	String adminname;
	public Doctor_Admin(String aid, String aname) {
		initialize();
		
		this.adminid=aid;
		this.adminname=aname;
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
	
	public void AutoID()
	{
		try
		{
			Statement s = con.createStatement();
			rs=s.executeQuery("select Max(DoctorID) from doctor");
			rs.next();
			rs.getString("MAX(DoctorID)");
			
			if(rs.getString("MAX(DoctorID)")==null)
			{
				txt_docid.setText("DN0001");
			}
			else
			{
				long id = Long.parseLong(rs.getString("MAX(DoctorID)").substring(2,rs.getString("MAX(DoctorID)").length()));
				id++;
				
				txt_docid.setText("DN"+String.format("%04d", id));
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void table_load()//To view data entered in a table
	{
		try
		{
			pst = con.prepareStatement("select*from doctor");
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
		frm_docAdmin = new JFrame();
		frm_docAdmin.getContentPane().setBackground(new Color(255, 204, 102));
		frm_docAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_docAdmin.setBounds(100, 100, 1300, 593);
		frm_docAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_docAdmin.getContentPane().setLayout(null);
		frm_docAdmin.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "Login Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 71, 310, 84);
		frm_docAdmin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 25, 65, 20);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(10, 53, 65, 20);
		panel.add(lblPassword);
		
		txt_uname = new JTextField();
		txt_uname.setBounds(126, 26, 155, 20);
		panel.add(txt_uname);
		txt_uname.setColumns(10);
		
		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(126, 54, 155, 20);
		panel.add(txt_pwd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 166, 310, 365);
		frm_docAdmin.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDoctorId.setBounds(10, 11, 77, 20);
		panel_1.add(lblDoctorId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(10, 42, 77, 20);
		panel_1.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(10, 73, 77, 20);
		panel_1.add(lblAddress);
		
		JLabel lblPhoneno = new JLabel("Phone No");
		lblPhoneno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneno.setBounds(10, 120, 77, 20);
		panel_1.add(lblPhoneno);
		
		txt_docid = new JTextField();
		txt_docid.setColumns(10);
		txt_docid.setBounds(125, 12, 155, 20);
		panel_1.add(txt_docid);
		txt_docid.setEditable(false);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(125, 43, 155, 20);
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
		txt_phoneno.setBounds(125, 120, 155, 20);
		panel_1.add(txt_phoneno);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(125, 151, 155, 20);
		panel_1.add(txt_email);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 151, 77, 20);
		panel_1.add(lblEmail);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateOfBirth.setBounds(10, 184, 77, 20);
		panel_1.add(lblDateOfBirth);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQualification.setBounds(10, 215, 77, 20);
		panel_1.add(lblQualification);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSpecialization.setBounds(10, 268, 83, 20);
		panel_1.add(lblSpecialization);
		
		JLabel lblChanneFee = new JLabel("Channe fee");
		lblChanneFee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChanneFee.setBounds(10, 299, 77, 20);
		panel_1.add(lblChanneFee);
		
		JLabel lblroomNo = new JLabel("Room No");
		lblroomNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblroomNo.setBounds(10, 333, 77, 20);
		panel_1.add(lblroomNo);
		
		JTextArea txt_address = new JTextArea();
		txt_address.setBounds(125, 72, 155, 37);
		panel_1.add(txt_address);
		
		JTextArea txt_qualification = new JTextArea();
		txt_qualification.setBounds(125, 215, 155, 37);
		panel_1.add(txt_qualification);
		
		txt_specialization = new JTextField();
		txt_specialization.setColumns(10);
		txt_specialization.setBounds(125, 269, 155, 20);
		panel_1.add(txt_specialization);
		
		txt_channelf = new JTextField();
		txt_channelf.setColumns(10);
		txt_channelf.setBounds(125, 300, 155, 20);
		panel_1.add(txt_channelf);
		
		txt_roomno = new JTextField();
		txt_roomno.setColumns(10);
		txt_roomno.setBounds(125, 334, 155, 20);
		panel_1.add(txt_roomno);
		
		txt_dob = new JTextField();
		txt_dob.setColumns(10);
		txt_dob.setBounds(125, 182, 155, 20);
		panel_1.add(txt_dob);
		
		///////////////////Insert function//////////////////////
		JButton btn_save = new JButton("Save");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("insert into doctor(DoctorID,Username,Password,Name,Address,Phone_No,Email,Date_of_birth,Qualification,Specialization,Channel_fee,Room_No) values(?,?,?,?,?,?,?,?,?,?,?,?)");
					
					pst.setString(1, txt_docid.getText());
					pst.setString(2, txt_uname.getText());
					pst.setString(3, txt_pwd.getText());
					pst.setString(4, txt_name.getText());
					pst.setString(5, txt_address.getText());
					pst.setInt(6, Integer.parseInt(txt_phoneno.getText()));
					pst.setString(7, txt_email.getText());
					pst.setString(8, txt_dob.getText());
					pst.setString(9, txt_qualification.getText());
					pst.setString(10, txt_specialization.getText());
					pst.setInt(11, Integer.parseInt(txt_channelf.getText()));
					pst.setInt(12, Integer.parseInt(txt_roomno.getText()));
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Add Record");
					
					txt_uname.setText("");
					txt_pwd.setText("");
					txt_name.setText("");
					txt_address.setText("");
					txt_phoneno.setText("");
					txt_email.setText("");
					txt_dob.setText("");
					txt_qualification.setText("");
					txt_specialization.setText("");
					txt_channelf.setText("");
					txt_roomno.setText("");
					txt_uname.requestFocus();
					
					AutoID();
					table_load();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_save.setBounds(722, 491, 89, 23);
		frm_docAdmin.getContentPane().add(btn_save);
		
		/////////////////////Insert function//////////////////////
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
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
					pst.setString(12, txt_docid.getText());
					
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
					txt_specialization.setText("");
					txt_channelf.setText("");
					txt_roomno.setText("");
					txt_uname.requestFocus();
					
					btn_save.setEnabled(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(821, 491, 89, 23);
		frm_docAdmin.getContentPane().add(btn_update);
		
		///////////////////////////Delete function///////////////////////
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("delete from doctor where DoctorID=?");
					
					pst.setString(1, txt_docid.getText());
					
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
					txt_specialization.setText("");
					txt_channelf.setText("");
					txt_roomno.setText("");
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
		btn_delete.setBounds(920, 491, 89, 23);
		frm_docAdmin.getContentPane().add(btn_delete);
		
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
				txt_specialization.setText("");
				txt_channelf.setText("");
				txt_roomno.setText("");
				txt_search.setText("");
				txt_uname.requestFocus();
				AutoID();
				table_load();
				btn_save.setEnabled(true);
			}
		});
		btn_clear.setBounds(1019, 491, 89, 23);
		frm_docAdmin.getContentPane().add(btn_clear);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_docAdmin.dispose();
			}
		});
		btn_back.setBounds(1118, 491, 89, 23);
		frm_docAdmin.getContentPane().add(btn_back);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(353, 473, 310, 58);
		frm_docAdmin.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 24, 58, 16);
		panel_2.add(lblNewLabel_1);
		
		/////////////////Search function////////////////////////////
		txt_search = new JTextField();
		txt_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					pst = con.prepareStatement("select * from doctor where name = ?");
					pst.setString(1, txt_search.getText());
					rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					//table_load();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				txt_docid.setText(d1.getValueAt(selectedIndex, 0).toString());
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
				
				btn_save.setEnabled(false);
				
			}
		});
		scrollPane.setBounds(353, 70, 921, 377);
		frm_docAdmin.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txt_search.setBounds(102, 23, 183, 20);
		panel_2.add(txt_search);
		txt_search.setColumns(10);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDoctor.setBounds(602, 11, 82, 30);
		frm_docAdmin.getContentPane().add(lblDoctor);
	}
}
