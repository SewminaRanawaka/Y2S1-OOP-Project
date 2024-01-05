import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.Toolkit;

public class Administrator {

	public JFrame frm_admin;
	JLabel lbl_auname;
	JLabel lbl_auid;
	private JLabel lbl_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator window = new Administrator();
					window.frm_admin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Administrator() {
		initialize();
		connector();
		scaleImage();
	}
	
	String adminid;
	String adminname;
	String id;
	String name;
	public Administrator(String auid, String auname) {
		initialize();
		scaleImage();
		
		this.adminid=auid;
		lbl_auid.setText(adminid);
		
		this.adminname=auname;
		lbl_auname.setText(adminname);
		
		id = adminid;
		name = adminname;
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
	
	public void scaleImage()
	{
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Admin.jpg"));
		
		Image img = icon.getImage();
		Image imgscale = img.getScaledInstance(lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgscale);
		lbl_img.setIcon(scaledIcon);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_admin = new JFrame();
		frm_admin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_admin.setTitle("Administrator");
		frm_admin.setBounds(100, 100, 863, 504);
		frm_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_admin.getContentPane().setLayout(null);
		frm_admin.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(588, 11, 240, 75);
		frm_admin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Id");
		lblNewLabel.setBounds(10, 11, 66, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(10, 36, 66, 18);
		panel.add(lblNewLabel_1);
		
		lbl_auid = new JLabel("");
		lbl_auid.setBounds(115, 11, 115, 18);
		panel.add(lbl_auid);
		
		lbl_auname = new JLabel("");
		lbl_auname.setBounds(115, 36, 115, 18);
		panel.add(lbl_auname);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_admin.dispose();
				Login lg = new Login();
				lg.frm_login.setVisible(true);
				
			}
		});
		btn_logout.setBounds(724, 417, 104, 23);
		frm_admin.getContentPane().add(btn_logout);
		
		JButton btn_addAdmin = new JButton("Add Admin");
		btn_addAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_addAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Add_Admin ad = new Add_Admin();
				ad.frm_addadmin.setVisible(true);
			}
		});
		btn_addAdmin.setBounds(45, 260, 104, 23);
		frm_admin.getContentPane().add(btn_addAdmin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Modify User", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(45, 323, 593, 75);
		frm_admin.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btn_doctor = new JButton("Doctor");
		btn_doctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Doctor_Admin da = new Doctor_Admin();
				da.frm_docAdmin.setVisible(true);
			}
		});
		btn_doctor.setBounds(10, 27, 104, 23);
		panel_1.add(btn_doctor);
		
		JButton btn_patient = new JButton("Patient");
		btn_patient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Patient_Admin da = new Patient_Admin();
				da.frm_patientAdmin.setVisible(true);
			}
		});
		btn_patient.setBounds(165, 27, 104, 23);
		panel_1.add(btn_patient);
		
		JButton btn_receptionist = new JButton("Receptionist");
		btn_receptionist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Receptionist_Admin da = new Receptionist_Admin();
				da.frm_receptionistAdmin.setVisible(true);
			}
		});
		btn_receptionist.setBounds(316, 27, 117, 23);
		panel_1.add(btn_receptionist);
		
		JButton btn_nurse = new JButton("Nurse");
		btn_nurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Nurse_Admin da = new Nurse_Admin();
				da.frm_nurseAdmin.setVisible(true);
			}
		});
		btn_nurse.setBounds(473, 27, 89, 23);
		panel_1.add(btn_nurse);
		
		JButton btn_Profile = new JButton("Profile");
		btn_Profile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_admin.dispose();
				
				Administrator_Profile ad = new Administrator_Profile();
				//df.lbl_adminid.setText(id);
				ad.data_load(id);
				ad.frm_adminprof.setVisible(true);
			}
		});
		btn_Profile.setBounds(739, 97, 89, 23);
		frm_admin.getContentPane().add(btn_Profile);
		
		lbl_img = new JLabel("");
		lbl_img.setBounds(0, 0, 874, 465);
		frm_admin.getContentPane().add(lbl_img);
	}
}
