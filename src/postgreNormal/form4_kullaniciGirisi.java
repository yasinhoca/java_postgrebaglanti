package postgreNormal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class form4_kullaniciGirisi extends JFrame {

	private JPanel contentPane;
	private JTextField kt;
	private JTextField st;
	String kad, sifre, sorgu;
	ResultSet rs;
	boolean giris = false;
			

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form4_kullaniciGirisi frame = new form4_kullaniciGirisi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public form4_kullaniciGirisi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 ad\u0131 :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 40, 138, 43);
		contentPane.add(lblNewLabel);
		
		kt = new JTextField();
		kt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		kt.setBounds(157, 40, 126, 30);
		contentPane.add(kt);
		kt.setColumns(10);
		
		JLabel lblifre = new JLabel("\u015Eifre :");
		lblifre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblifre.setBounds(10, 93, 138, 43);
		contentPane.add(lblifre);
		
		st = new JTextField();
		st.setFont(new Font("Tahoma", Font.PLAIN, 18));
		st.setColumns(10);
		st.setBounds(157, 93, 126, 30);
		contentPane.add(st);
		
		JButton btnNewButton = new JButton("G\u0130R\u0130\u015E");		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(80, 160, 138, 54);
		contentPane.add(btnNewButton);
		
		Veritabani.baglan();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgu = "select * from kullanici";
				rs = Veritabani.listele(sorgu);
				kad = kt.getText();
				sifre = st.getText();
				
				try {
					while(rs.next()) {
						if(kad.equals(rs.getString("kad")) && sifre.equals(rs.getString("sifre"))) {
							giris = true;
							formKullanici fk = new formKullanici();							
							if(rs.getString("tur").equals("admin")) {
								System.out.println("Admin giriþi baþarýlý!");
								fk.setTitle(kad + " Hoþgeldiniz!");
								fk.tur="admin";
							} else if(rs.getString("tur").equals("user")){
								System.out.println("User giriþi baþarýlý!");
								fk.setTitle(kad + " Hoþgeldiniz!");
								fk.tur="user";
							}
							fk.kad = kad;
							fk.show();
						}
					}
					if(giris==false) System.out.println("Kullanýcý adý veya þifre hatalý");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
