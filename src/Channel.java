import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDayChooser;

import net.proteanit.sql.DbUtils;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.color.*;
import java.awt.Toolkit;

public class Channel {

	JFrame frm_channel;
	private JTextField txt_rno;
	private JTable table;
	private JLabel lbl_cno;
	private JComboBox cmb_dname;
	private JComboBox cmb_pname;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Channel window = new Channel();
					window.frm_channel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Channel() {
		initialize();
		connector();
		AutoID();
		table_load();
		LoadDoctor();
		LoadPatient();
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
	
	public void table_load() {
		
		try {
		pst = con.prepareStatement("select * from channel");
		rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AutoID()
	{
		try
		{
			Statement s = con.createStatement();
			rs=s.executeQuery("select Max(Channel_ID) from channel");
			rs.next();
			rs.getString("MAX(Channel_ID)");
			
			if(rs.getString("MAX(Channel_ID)")==null)
			{
				lbl_cno.setText("CH0001");
			}
			else
			{
				long id = Long.parseLong(rs.getString("MAX(Channel_ID)").substring(2,rs.getString("MAX(Channel_ID)").length()));
				id++;
				
				lbl_cno.setText("CH"+String.format("%04d", id));
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*#########################################Doctor################################*/
	public class Doctor {
		String dId;
		String dName;
		
		public Doctor(String did, String dname) {
			this.dId = did;
			this.dName = dname;
		}
		
		public String toString() {
			return dName;
		}
	}
	
	public void LoadDoctor() {
		try {
			pst = con.prepareStatement("select * from Doctor");
			rs = pst.executeQuery();
			
			while(rs.next()) {
			cmb_dname.addItem(new Doctor(rs.getString(1),rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*#########################################Patient################################*/
	
	public class Patient {
		String pId;
		String pName;
		
		public Patient(String pid, String pname) {
			this.pId = pid;
			this.pName = pname;
		}
		
		public String toString() {
			return pName;
		}
	}
	
	/*#########################################Load Patient################################*/
	public void LoadPatient() {
		try {
			pst = con.prepareStatement("select * from Patient");
			rs = pst.executeQuery();
			
			while(rs.next()) {
			cmb_pname.addItem(new Patient(rs.getString(1),rs.getString(2)));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_channel = new JFrame();
		frm_channel.setBounds(100, 100, 1122, 461);
		frm_channel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_channel.getContentPane().setLayout(null);
		frm_channel.setLocationRelativeTo(null);
		
		ImageIcon image = new ImageIcon("/Redcross.jpg");
		frm_channel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Channel Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 16, 366, 304);
		frm_channel.getContentPane().add(panel);
		
		JLabel jlabel1 = new JLabel("Channel No");
		jlabel1.setFont(new Font("Tahoma", Font.BOLD, 11));
		jlabel1.setBounds(35, 50, 81, 14);
		panel.add(jlabel1);
		
		JLabel lbl_dname = new JLabel("Doctor Name");
		lbl_dname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_dname.setBounds(35, 100, 81, 14);
		panel.add(lbl_dname);
		
		JLabel lbl_pname = new JLabel("Patient Name");
		lbl_pname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_pname.setBounds(35, 150, 81, 14);
		panel.add(lbl_pname);
		
		JLabel lbl_rno = new JLabel("Room No");
		lbl_rno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_rno.setBounds(35, 200, 81, 14);
		panel.add(lbl_rno);
		
		JLabel lbl_cdate = new JLabel("Channel Date");
		lbl_cdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_cdate.setBounds(35, 248, 81, 14);
		panel.add(lbl_cdate);
		
		lbl_cno = new JLabel("");
		lbl_cno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_cno.setBounds(156, 50, 164, 14);
		panel.add(lbl_cno);
		
		txt_rno = new JTextField();
		txt_rno.setColumns(10);
		txt_rno.setBounds(156, 197, 164, 22);
		panel.add(txt_rno);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(156, 245, 162, 20);
		panel.add(dateChooser);
		
		cmb_pname = new JComboBox();
		cmb_pname.setBounds(156, 146, 164, 22);
		panel.add(cmb_pname);
		
		cmb_dname = new JComboBox();
		cmb_dname.setBounds(156, 96, 164, 22);
		panel.add(cmb_dname);
		
		
		
		JButton btn_create = new JButton("Create");
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Doctor d = (Doctor) cmb_dname.getSelectedItem();
				Patient p = (Patient) cmb_pname.getSelectedItem();
				
				SimpleDateFormat dateformat = new  SimpleDateFormat("yyyy-MM-dd");
				String date = dateformat.format(dateChooser.getDate());
				
				try {
					pst = con.prepareStatement("insert into channel(Channel_ID, Doctor_Name, Patient_Name, Room_No, Channel_Date, Doctor_ID, Patient_ID)values(?,?,?,?,?,?,?)");
					pst.setString(1, lbl_cno.getText());
					pst.setString(2, d.dName);
					pst.setString(3, p.pName);
					pst.setString(4, txt_rno.getText());
					pst.setString(5, date);
					pst.setString(6, d.dId);
					pst.setString(7, p.pId);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Channel added");
					
					lbl_cno.setText("");
					cmb_dname.setSelectedIndex(-1);
					cmb_pname.setSelectedIndex(-1);
					txt_rno.setText("");
					
					AutoID();
					table_load();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_create.setBounds(197, 360, 123, 33);
		frm_channel.getContentPane().add(btn_create);
		
		JButton btn_clear = new JButton("Clear");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cmb_dname.setSelectedIndex(-1);
				cmb_pname.setSelectedIndex(-1);
				txt_rno.setText("");
				AutoID();
			}
		});
		btn_clear.setBounds(606, 360, 123, 33);
		frm_channel.getContentPane().add(btn_clear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				TableModel d1 = table.getModel();
				
				lbl_cno.setText(d1.getValueAt(selectedIndex, 0).toString());
				
				DefaultComboBoxModel list = new DefaultComboBoxModel();
				JComboBox cbo_cats = new JComboBox(list);
				
				
				
				//cmb_dname.setSelectedItem(d1.getValueAt(selectedIndex, 3).toString());
				//cmb_pname.setSelectedItem(d1.getValueAt(selectedIndex, 5).toString());
				txt_rno.setText(d1.getValueAt(selectedIndex, 5).toString());
			}
		});
		scrollPane.setBounds(409, 27, 687, 293);
		frm_channel.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_channel.dispose();
			}
		});
		btn_back.setBounds(749, 360, 123, 33);
		frm_channel.getContentPane().add(btn_back);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Doctor d = (Doctor) cmb_dname.getSelectedItem();
				Patient p = (Patient) cmb_pname.getSelectedItem();
				
				SimpleDateFormat dateformat = new  SimpleDateFormat("yyyy-MM-dd");
				String date = dateformat.format(dateChooser.getDate());
				
				try {
					pst = con.prepareStatement("update channel set Doctor_Name=?, Patient_Name=?, Room_No=?, Channel_Date=?, Doctor_ID=?, Patient_ID=? where  Channel_ID=?");
					pst.setString(1, d.dName);
					pst.setString(2, p.pName);
					pst.setString(3, txt_rno.getText());
					pst.setString(4, date);
					pst.setString(5, d.dId);
					pst.setString(6, p.pId);
					pst.setString(7, lbl_cno.getText());
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Updated.");
					lbl_cno.setText("");
					cmb_dname.setSelectedIndex(-1);
					cmb_pname.setSelectedIndex(-1);
					txt_rno.setText("");
					
					AutoID();
					table_load();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_update.setBounds(330, 360, 123, 33);
		frm_channel.getContentPane().add(btn_update);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pst = con.prepareStatement("delete from channel where Channel_ID=?");
					
					pst.setString(1, lbl_cno.getText());
					
					pst.executeUpdate();
					JOptionPane.showConfirmDialog(null, "Delete Rocord");
					
					JOptionPane.showMessageDialog(null, "Record Deleted.");
					lbl_cno.setText("");
					cmb_dname.setSelectedIndex(-1);
					cmb_pname.setSelectedIndex(-1);
					txt_rno.setText("");
					
					table_load();
					AutoID();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_delete.setBounds(473, 360, 123, 33);
		frm_channel.getContentPane().add(btn_delete);
	}
}
