import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class View_Channel {

	JFrame frm_viewchannel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Channel window = new View_Channel();
					window.frm_viewchannel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View_Channel() {
		initialize();
		 connector();
		 table_load(null);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btn_back;
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
	
	public void table_load(String did)//To view data entered in a table
	{
		try
		{
			pst = con.prepareStatement("select*from channel where Doctor_ID=?");
			pst.setString(1, did);
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
		frm_viewchannel = new JFrame();
		frm_viewchannel.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_viewchannel.setBounds(100, 100, 861, 454);
		frm_viewchannel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_viewchannel.getContentPane().setLayout(null);
		frm_viewchannel.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 813, 293);
		frm_viewchannel.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_viewchannel.dispose();
			}
		});
		btn_back.setBounds(734, 370, 89, 23);
		frm_viewchannel.getContentPane().add(btn_back);
	}
}
