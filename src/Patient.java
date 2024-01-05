

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Patient {

	//private static final AbstractButton JTable1 = null;
	JFrame frm_patient;
	private JTextField txt_pname;
	private JTextField txt_pphone;
	private JTextField txt_paddress;
	private JTextField txt_page;
	private JTextField txt_pblood;
	private JTextField txt_pemail;
	private JTable table;
	private JTextField txt_pid;
	private JComboBox cmb_pgender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patient window = new Patient();
					window.frm_patient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Patient() {
		initialize();
		Connect();
		AutoID();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incare_hospital", "root", "Ss@200105502040");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*#########################################Auto ID################################*/
	
	public void AutoID() {
		try {
			
				Statement s = con.createStatement();
				rs=s.executeQuery("select MAX(Patient_ID) from patient");
				rs.next();
				rs.getString("MAX(Patient_ID)");
				
				if(rs.getString("MAX(Patient_ID)") == null)
				{
					
					txt_pid.setText("PN0001");	
				}
			else
			{
				
				long id = Long.parseLong(rs.getString("MAX(Patient_ID)").substring(2,rs.getString("MAX(Patient_ID)").length()));
				id++;
				txt_pid.setText("PN" + String.format("%04d", id));
				
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();	
		}
	}          
	
	/*#########################################patient Table################################*/
	
	public void table_load() {
		
		try {
		pst = con.prepareStatement("select * from patient");
		rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_patient = new JFrame();
		frm_patient.getContentPane().setBackground(new Color(128, 255, 255));
		frm_patient.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_patient.setBounds(100, 100, 1124, 588);
		frm_patient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_patient.setLocationRelativeTo(null);
		
		
		/*#########################################Add################################*/
		frm_patient.getContentPane().setLayout(null);
		
		JButton btn_add = new JButton("Add");
		btn_add.setBounds(88, 502, 88, 28);
		frm_patient.getContentPane().add(btn_add);
		btn_add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String pId = txt_pid.getText();
				String pname = txt_pname.getText();
				String paddress = txt_paddress.getText();
				String pphone = txt_pphone.getText();
				int page = Integer.parseInt(txt_page.getText());
				String pgender = String.valueOf(cmb_pgender.getSelectedItem().toString());
				String pblood = txt_pblood.getText();
				String pemail = txt_pemail.getText();
				
				
				try {
					pst = con.prepareStatement("insert into patient(Patient_ID, Name, Address, Phone_No, Age, Gender, Blood_Type, Email)values(?,?,?,?,?,?,?,?)");
					pst.setString(1, pId);
					pst.setString(2, pname);
					pst.setString(3, paddress);
					pst.setString(4, pphone);
					pst.setInt(5, page);
					pst.setString(6, pgender);
					pst.setString(7, pblood);
					pst.setString(8, pemail);
					pst.executeUpdate();
					
					JOptionPane.showConfirmDialog(null, "Insert data");
					
					AutoID();
					
					txt_pname.setText("");
					txt_paddress.setText("");
					txt_pphone.setText("");
					txt_page.setText("");
					cmb_pgender.setSelectedIndex(-1);
					txt_pblood.setText("");
					txt_pemail.setText("");
					
					table_load();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		
		
		/*#########################################Update################################*/
		
		JButton btn_update = new JButton("Update");
		btn_update.setBounds(279, 502, 88, 28);
		frm_patient.getContentPane().add(btn_update);
		
		btn_update.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String pname = txt_pname.getText();
				String address = txt_paddress.getText();
				String phone = txt_pphone.getText();
				int age = Integer.parseInt(txt_page.getText());
				String gender = String.valueOf(cmb_pgender.getSelectedItem().toString());
				String blood = txt_pblood.getText();
				String email = txt_pemail.getText();
				String pno = txt_pid.getText();
				
				
				try {
					pst = con.prepareStatement("update patient set Name = ? , Address = ?, Phone_No = ?, Age = ?, Gender = ?, Blood_Type = ?, Email = ? where Patient_ID = ?");
					pst.setString(1, pname);
					pst.setString(2, address);
					pst.setString(3, phone);
					pst.setInt(4, age);
					pst.setString(5, gender);
					pst.setString(6, blood);
					pst.setString(7, email);
					pst.setString(8, pno);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Patient Updated");
					
					AutoID();
					txt_pname.setText("");
					txt_paddress.setText("");
					txt_pphone.setText("");
					txt_page.setText("");
					cmb_pgender.setSelectedIndex(-1);
					txt_pblood.setText("");
					txt_pemail.setText("");
					
					txt_pname.requestFocus();
					
					table_load();
					
					btn_add.setEnabled(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		
		
		/*#########################################Delete################################*/
		JButton btn_delete = new JButton("Delete");
		btn_delete.setBounds(464, 502, 88, 28);
		frm_patient.getContentPane().add(btn_delete);
		
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pno = txt_pid.getText();
					
				try {
					pst = con.prepareStatement("delete from patient where Patient_ID = ?");
			
					pst.setString(1, pno);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Patient Deleted!!!");
					
					AutoID();
					txt_pname.setText("");
					txt_paddress.setText("");
					txt_pphone.setText("");
					txt_page.setText("");
					cmb_pgender.setSelectedIndex(-1);
					txt_pblood.setText("");
					txt_pemail.setText("");
					
					txt_pname.requestFocus();
					
					table_load();
					
					btn_add.setEnabled(true);
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		/*#########################################Exit################################*/
		
		JButton btn_exit = new JButton("Exit");
		btn_exit.setBounds(846, 502, 88, 28);
		frm_patient.getContentPane().add(btn_exit);
		
		btn_exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				frm_patient.dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 264, 1052, 215);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				txt_pid.setText(d1.getValueAt(selectedIndex, 0).toString());
				txt_pname.setText(d1.getValueAt(selectedIndex, 1).toString());
				txt_paddress.setText(d1.getValueAt(selectedIndex, 2).toString());
				txt_pphone.setText(d1.getValueAt(selectedIndex, 3).toString());
				txt_page.setText(d1.getValueAt(selectedIndex, 4).toString());
				
				String gender = d1.getValueAt(selectedIndex, 5).toString();
				//JComboBox cmb_pgender = new JComboBox();
				switch(gender) {
				case "Male":
					cmb_pgender.setSelectedIndex(0);
					break;
				
				case "Female":
					cmb_pgender.setSelectedIndex(1);
					break;
				}
				
				txt_pblood.setText(d1.getValueAt(selectedIndex, 6).toString());
				txt_pemail.setText(d1.getValueAt(selectedIndex, 7).toString());
				
				
				btn_add.setEnabled(false);
			}
			
		});
		frm_patient.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 28, 1052, 226);
		frm_patient.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbl_pid = new JLabel("Patient ID");
		lbl_pid.setBounds(10, 26, 87, 14);
		panel.add(lbl_pid);
		lbl_pid.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txt_pid = new JTextField();
		txt_pid.setBounds(191, 24, 267, 20);
		panel.add(txt_pid);
		txt_pid.setColumns(10);
		
		txt_pname = new JTextField();
		txt_pname.setBounds(724, 26, 274, 20);
		panel.add(txt_pname);
		txt_pname.setColumns(10);
		
		JLabel lbl_pname = new JLabel("Patient Name");
		lbl_pname.setBounds(543, 28, 87, 14);
		panel.add(lbl_pname);
		lbl_pname.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txt_paddress = new JTextField();
		txt_paddress.setBounds(191, 76, 267, 20);
		panel.add(txt_paddress);
		txt_paddress.setColumns(10);
		
		JLabel lbl_paddress = new JLabel("Address");
		lbl_paddress.setBounds(10, 78, 87, 14);
		panel.add(lbl_paddress);
		lbl_paddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*#########################################Validation################################*/
		
		txt_pphone = new JTextField();
		txt_pphone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(Character.isLetter(c))
				{
					txt_pphone.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter number only");
					txt_pphone.setEditable(true);
				}
				else
				{
					txt_pphone.setEditable(true);
				}
				
			}
		});
		txt_pphone.setBounds(724, 76, 274, 20);
		panel.add(txt_pphone);
		txt_pphone.setColumns(10);
		
		JLabel lbl_pphone = new JLabel("Phone Number");
		lbl_pphone.setBounds(543, 78, 133, 14);
		panel.add(lbl_pphone);
		lbl_pphone.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lbl_page = new JLabel("Age");
		lbl_page.setBounds(10, 129, 87, 14);
		panel.add(lbl_page);
		lbl_page.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		/*#########################################Validation################################*/
		
		txt_page = new JTextField();
		txt_page.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(Character.isLetter(c))
				{
					txt_page.setEditable(false);
					JOptionPane.showMessageDialog(null, "Enter number only");
					txt_page.setEditable(true);
				}
				else
				{
					txt_page.setEditable(true);
				}
				
			}
		});
		txt_page.setBounds(191, 127, 267, 20);
		panel.add(txt_page);
		txt_page.setColumns(10);
		
		JLabel lbl_pblood = new JLabel("Blood Group");
		lbl_pblood.setBounds(10, 186, 87, 14);
		panel.add(lbl_pblood);
		lbl_pblood.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txt_pblood = new JTextField();
		txt_pblood.setBounds(191, 184, 267, 20);
		panel.add(txt_pblood);
		txt_pblood.setColumns(10);
		
		cmb_pgender = new JComboBox();
		cmb_pgender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cmb_pgender.setBounds(724, 129, 110, 22);
		panel.add(cmb_pgender);
		cmb_pgender.setToolTipText("Gender");
		
		JLabel lbl_pgender = new JLabel("Gender");
		lbl_pgender.setBounds(543, 132, 87, 14);
		panel.add(lbl_pgender);
		lbl_pgender.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lbl_pemail = new JLabel("Email");
		lbl_pemail.setBounds(543, 182, 87, 14);
		panel.add(lbl_pemail);
		lbl_pemail.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txt_pemail = new JTextField();
		txt_pemail.setBounds(724, 180, 274, 20);
		panel.add(txt_pemail);
		txt_pemail.setColumns(10);
		
		JButton btn_clear = new JButton("Clear");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt_pname.setText("");
				txt_paddress.setText("");
				txt_pphone.setText("");
				txt_page.setText("");
				cmb_pgender.setSelectedIndex(-1);
				txt_pblood.setText("");
				txt_pemail.setText("");
				
				AutoID();
				table_load();
				btn_add.setEnabled(true);
			}
		});
		btn_clear.setBounds(649, 502, 88, 28);
		frm_patient.getContentPane().add(btn_clear);
	}
}




