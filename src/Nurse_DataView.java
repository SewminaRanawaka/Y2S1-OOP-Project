import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Nurse_DataView {

	JFrame frm_dataview;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nurse_DataView window = new Nurse_DataView();
					window.frm_dataview.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nurse_DataView() {
		initialize();
		Connect();
		PatientTable_load();
		ChannelTable_load();
	}
	

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incare_hospital", "root", "Ss@200105502040");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PatientTable_load() {
		
		try {
		pst = con.prepareStatement("select * from patient");
		rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	
public void ChannelTable_load() {
		
		try {
		pst = con.prepareStatement("select * from channel");
		rs = pst.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_dataview = new JFrame();
		frm_dataview.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_dataview.setBounds(100, 100, 883, 498);
		frm_dataview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_dataview.getContentPane().setLayout(null);
		frm_dataview.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(26, 11, 815, 397);
		frm_dataview.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Patient", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 790, 347);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Channel", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 790, 347);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_dataview.dispose();
			}
		});
		btn_back.setBounds(752, 425, 89, 23);
		frm_dataview.getContentPane().add(btn_back);
	}
}
