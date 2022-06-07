package postgreNormal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class form5_createDropInsert extends JFrame {

	private JPanel contentPane;
	String sorgu;
	ArrayList<String> n = new ArrayList<String>(Arrays.asList("1001","1002","1003","1004"));
	ArrayList<String> ad = new ArrayList<String>(Arrays.asList("mustafa","ryan","ibrahim","emin"));
	ArrayList<String> soyad = new ArrayList<String>(Arrays.asList("ercan","hakim","almaz","gonca"));
	ArrayList<String> adrs = new ArrayList<String>(Arrays.asList("istanbul","konya","ankara","izmir"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form5_createDropInsert frame = new form5_createDropInsert();
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
	public form5_createDropInsert() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Veritabani.baglan();
		
		
		JButton btnNewButton = new JButton("OLU\u015ETUR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgu="create table if not exists adres(ogrno varchar(10) NOT NULL,"
						+ "isim varchar(20), soyisim varchar(20), "
						+ "adres varchar(100),"
						+ "CONSTRAINT adres_pkey PRIMARY KEY (ogrno))";
				
				System.out.println(sorgu);
				Veritabani.olustur(sorgu);				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(80, 10, 241, 92);
		contentPane.add(btnNewButton);
		
		JButton btnSil = new JButton("S\u0130L");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgu = "drop table if exists adres";
				Veritabani.sil(sorgu);
			}
		});
		btnSil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSil.setBounds(80, 143, 241, 92);
		contentPane.add(btnSil);
		
		JButton btnEkle = new JButton("EKLE");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<n.size();i++) {
					sorgu = "insert into adres(ogrno,isim,soyisim,adres)"
							+ "values('"+n.get(i)+"','"+ad.get(i)+"','"+soyad.get(i)+"','"
							+ adrs.get(i)+"')";
					Veritabani.ekle(sorgu);
				}
				
			}
		});
		btnEkle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEkle.setBounds(80, 273, 241, 92);
		contentPane.add(btnEkle);
	}

}
