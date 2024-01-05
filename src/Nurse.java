import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class Nurse {

	JFrame frm_nurse;
	JLabel lbl_nuid;
	JLabel lbl_nuname;
	private JLabel lbl_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nurse window = new Nurse();
					window.frm_nurse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Nurse() {
		initialize();
		scaleImage();
	}
	
	String nurseid;
	String nursename;
	String id;
	String name;
	public Nurse(String nuid, String nuname) {
		initialize();
		connector();
		scaleImage();
		
		this.nurseid = nuid;
		lbl_nuid.setText(nurseid);
		
		this.nursename = nuname;
		lbl_nuname.setText(nursename);
		
		id = nurseid;
		name = nursename;
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void connector()
	{
		try
		{
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incare_hospital","root","Ss@200105502040");
		}
		catch(Exception e)
		{
			System.out.print("Database connection failed");
			e.printStackTrace();
		}
	}
	
	public void scaleImage()
	{
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Nurse.jpg"));
		
		Image img = icon.getImage();
		Image imgscale = img.getScaledInstance(lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgscale);
		lbl_img.setIcon(scaledIcon);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_nurse = new JFrame();
		frm_nurse.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_nurse.setTitle("Nurse");
		frm_nurse.setBounds(100, 100, 828, 491);
		frm_nurse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_nurse.getContentPane().setLayout(null);
		frm_nurse.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(562, 11, 240, 69);
		frm_nurse.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("User Id");
		lblNewLabel.setBounds(10, 11, 66, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(10, 36, 66, 18);
		panel.add(lblNewLabel_1);
		
		lbl_nuid = new JLabel("");
		lbl_nuid.setBounds(115, 11, 115, 18);
		panel.add(lbl_nuid);
		
		lbl_nuname = new JLabel("");
		lbl_nuname.setBounds(115, 36, 115, 18);
		panel.add(lbl_nuname);
		
		JButton btn_profile = new JButton("Profile");
		btn_profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_nurse.dispose();
				Nurse_Profile np = new Nurse_Profile();
				np.lbl_nurseid.setText(lbl_nuid.getText());
				np.data_load(lbl_nuid.getText());
				np.frm_nurseprof.setVisible(true);
			}
		});
		btn_profile.setBounds(713, 95, 89, 23);
		frm_nurse.getContentPane().add(btn_profile);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_nurse.dispose();
				Login lg = new Login();
				lg.frm_login.setVisible(true);
			}
		});
		btn_logout.setBounds(713, 418, 89, 23);
		frm_nurse.getContentPane().add(btn_logout);
		
		JButton btn_viewdata = new JButton("View Data");
		btn_viewdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Nurse_DataView nd = new Nurse_DataView();
				nd.frm_dataview.setVisible(true);
			}
		});
		btn_viewdata.setBounds(53, 294, 105, 23);
		frm_nurse.getContentPane().add(btn_viewdata);
		
		lbl_img = new JLabel("");
		lbl_img.setBounds(0, 0, 812, 452);
		frm_nurse.getContentPane().add(lbl_img);
	}
}
