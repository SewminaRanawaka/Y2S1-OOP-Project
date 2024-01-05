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
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Receptionist {

	JFrame frm_receptionist;
	JLabel lbl_runame;
	JLabel lbl_ruid;
	private JLabel lbl_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist window = new Receptionist();
					window.frm_receptionist.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Receptionist() {
		initialize();
		scaleImage();
	}
	
	String recepid;
	String recepname;
	String id;
	String name;
	public Receptionist(String ruid, String runame) {
		initialize();
		scaleImage();
		
		this.recepid=ruid;
		lbl_ruid.setText(recepid);
		
		this.recepname = runame;
		lbl_runame.setText(recepname);
		
		id = recepid;
		name = recepname;
	}
	
	public void scaleImage()
	{
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Receptionist.jpg"));
		
		Image img = icon.getImage();
		Image imgscale = img.getScaledInstance(lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgscale);
		lbl_img.setIcon(scaledIcon);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_receptionist = new JFrame();
		frm_receptionist.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_receptionist.setBounds(100, 100, 675, 429);
		frm_receptionist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_receptionist.getContentPane().setLayout(null);
		frm_receptionist.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(409, 11, 240, 67);
		frm_receptionist.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("User Id");
		lblNewLabel.setBounds(10, 11, 66, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(10, 36, 66, 18);
		panel.add(lblNewLabel_1);
		
		lbl_ruid = new JLabel("");
		lbl_ruid.setBounds(93, 11, 137, 18);
		panel.add(lbl_ruid);
		
		lbl_runame = new JLabel("");
		lbl_runame.setBounds(93, 36, 137, 18);
		panel.add(lbl_runame);
		
		JButton btn_profile = new JButton("Profile");
		btn_profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_receptionist.dispose();
				Receptionist_Profile rp = new Receptionist_Profile();
				rp.lbl_recepid.setText(recepid);
				rp.data_load(recepid);
				rp.frm_recepprof.setVisible(true);
			}
		});
		btn_profile.setBounds(560, 89, 89, 23);
		frm_receptionist.getContentPane().add(btn_profile);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_receptionist.dispose();
				Login lg = new Login();
				lg.frm_login.setVisible(true);
			}
		});
		btn_logout.setBounds(560, 356, 89, 23);
		frm_receptionist.getContentPane().add(btn_logout);
		
		JButton btn_addpatient = new JButton("Patient");
		btn_addpatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Patient p = new Patient();
				p.frm_patient.setVisible(true);
			}
		});
		btn_addpatient.setBounds(147, 305, 105, 23);
		frm_receptionist.getContentPane().add(btn_addpatient);
		
		JButton btn_addchannel = new JButton("Add Channel");
		btn_addchannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Channel c = new Channel();
				c.frm_channel.setVisible(true);
			}
		});
		btn_addchannel.setBounds(10, 305, 105, 23);
		frm_receptionist.getContentPane().add(btn_addchannel);
		
		lbl_img = new JLabel("");
		lbl_img.setBounds(0, 0, 659, 390);
		frm_receptionist.getContentPane().add(lbl_img);
	}
}
