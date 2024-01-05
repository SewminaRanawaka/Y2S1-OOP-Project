import java.sql.Statement;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class Doctor {

	JFrame frm_doctor;
	JLabel lbl_duid;
	JLabel lbl_duname;
	private JButton btn_viewchannel;
	private JButton btn_viewPatient;
	private JLabel lbl_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor window = new Doctor();
					window.frm_doctor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Doctor()
	{
		initialize();
		scaleImage();
	}
	
	String docid;
	String id;
	String docname;
	String name;
	public Doctor(String duid, String duname) {
		initialize();
		connector();
		scaleImage();
		
		this.docid = duid;
		lbl_duid.setText(duid);
		
		this.docname = duname;
		lbl_duname.setText(duname);
		
		id = docid;
		name = docname;
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
	
	public void scaleImage()
	{
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Doctor.jpg"));
		
		Image img = icon.getImage();
		Image imgscale = img.getScaledInstance(lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgscale);
		lbl_img.setIcon(scaledIcon);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_doctor = new JFrame();
		frm_doctor.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_doctor.setTitle("Doctor");
		frm_doctor.setBounds(100, 100, 728, 408);
		frm_doctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_doctor.getContentPane().setLayout(null);
		frm_doctor.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(459, 11, 240, 67);
		frm_doctor.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("User Id");
		lblNewLabel.setBounds(10, 11, 66, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(10, 36, 66, 18);
		panel.add(lblNewLabel_1);
		
		lbl_duid = new JLabel("");
		lbl_duid.setBounds(115, 11, 115, 18);
		panel.add(lbl_duid);
		
		lbl_duname = new JLabel("");
		lbl_duname.setBounds(115, 36, 115, 18);
		panel.add(lbl_duname);
		
		JButton btn_profile = new JButton("Profile");
		btn_profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_doctor.dispose();
				
				Doctor_Profile df = new Doctor_Profile();
				df.lbl_docid.setText(id);
				df.data_load(id);
				df.frm_docprof.setVisible(true);
			}
		});
		btn_profile.setBounds(610, 89, 89, 23);
		frm_doctor.getContentPane().add(btn_profile);
		
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_doctor.dispose();
				Login lg = new Login();
				lg.frm_login.setVisible(true);
			}
		});
		btn_logout.setBounds(610, 335, 89, 23);
		frm_doctor.getContentPane().add(btn_logout);
		
		JButton btn_viewChannel = new JButton("View Channel");
		btn_viewChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				View_Channel vc = new View_Channel();
				vc.frm_viewchannel.setVisible(true);
				vc.table_load(id);
			}
		});
		btn_viewChannel.setBounds(10, 227, 111, 23);
		frm_doctor.getContentPane().add(btn_viewChannel);
		
		lbl_img = new JLabel("");
		lbl_img.setBounds(0, 0, 712, 369);
		frm_doctor.getContentPane().add(lbl_img);
	}
}
