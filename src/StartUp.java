import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class StartUp {

	public JFrame frm_startup;
	private JLabel lbl_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUp window = new StartUp();
					window.frm_startup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartUp() {
		initialize();
		scaleImage();
	}
	
	public void scaleImage()
	{
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/medical.jpg"));
		
		Image img = icon.getImage();
		Image imgscale = img.getScaledInstance(lbl_img.getWidth(),lbl_img.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgscale);
		lbl_img.setIcon(scaledIcon);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_startup = new JFrame();
		frm_startup.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sewmina Ranawaka\\Downloads\\RedCross.jpg"));
		frm_startup.setTitle("StartUp");
		frm_startup.setBounds(100, 100, 565, 395);
		frm_startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_startup.getContentPane().setLayout(null);
		frm_startup.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("InCare Hospital");
		lblNewLabel.setFont(new Font("Perpetua Titling MT", Font.BOLD, 26));
		lblNewLabel.setBounds(133, 36, 265, 38);
		frm_startup.getContentPane().add(lblNewLabel);
		
		JButton btn_login = new JButton("Login");
		btn_login.setOpaque(false);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frm_startup.dispose();
				Login lg = new Login();
				lg.frm_login.setVisible(true);
						
			}
		});
		btn_login.setBounds(450, 322, 89, 23);
		frm_startup.getContentPane().add(btn_login);
		
		lbl_img = new JLabel("");
		lbl_img.setBounds(0, 0, 549, 356);
		frm_startup.getContentPane().add(lbl_img);
	}
}
