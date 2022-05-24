package postgreNormal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;

public class form_r extends JFrame {

	private JPanel contentPane;
	JButton[] b = new JButton[20]; 
	String sorgu;
	private JTextField tnu;
	private JTextField tad;
	private JTextField tsoyad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form_r frame = new form_r();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public form_r() throws SQLException {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tnu = new JTextField();
		tnu.setEnabled(false);
		tnu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tnu.setBounds(240, 44, 128, 28);
		contentPane.add(tnu);
		tnu.setColumns(10);
		
		JButton btnNewButton = new JButton("KAYDET");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(240, 249, 128, 28);
		contentPane.add(btnNewButton);
		
		tad = new JTextField();
		tad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tad.setColumns(10);
		tad.setBounds(240, 122, 128, 28);
		contentPane.add(tad);
		
		tsoyad = new JTextField();
		tsoyad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tsoyad.setColumns(10);
		tsoyad.setBounds(240, 196, 128, 28);
		contentPane.add(tsoyad);
		
		JLabel lblNewLabel = new JLabel("Numara");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(240, 10, 96, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblAd = new JLabel("Ad");
		lblAd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAd.setBounds(240, 88, 96, 24);
		contentPane.add(lblAd);
		
		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoyad.setBounds(240, 162, 96, 24);
		contentPane.add(lblSoyad);
		
		Veritabani.baglan();
		boya();
		
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<20;i++) {
					if(e.getSource()==b[i]) tnu.setText(b[i].getText());
				}				
			}
		};
		
		for(int i=0;i<20;i++) b[i].addActionListener(al);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgu = "update rezerve set "
						+ "ad='"+tad.getText()+"',"
						+ "soyad='"+tsoyad.getText()+"',"
						+ "dolu=true "
						+ "where numara="+tnu.getText();
				System.out.println(sorgu);
				//sorgu = "update rezerve set ad='ali',soyad='veli',dolu=true where numara=1";
				Veritabani.guncelle(sorgu);
				try {
					boya();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			}
		});
		
		
	}
	
	void boya() throws SQLException {
		for(int i=0;i<10;i++) {
			b[i] = new JButton();
			b[i].setBounds(20,i*50+10,50,50);
			contentPane.add(b[i]);
			b[i].setText(Integer.toString(i+1));
			sorgu = "select dolu from rezerve where numara="+Integer.toString(i+1);
			ResultSet rs = Veritabani.listele(sorgu);
			while(rs.next()) {
				if(rs.getBoolean("dolu")==false) b[i].setBackground(Color.GREEN);
				else {
					b[i].setBackground(Color.RED);
					b[i].setEnabled(false);
				}
			}
		}
		
		for(int i=10;i<20;i++) {
			b[i] = new JButton();
			b[i].setBounds(150,(i-10)*50+10,50,50);
			contentPane.add(b[i]);
			b[i].setText(Integer.toString(i+1));
			sorgu = "select dolu from rezerve where numara="+Integer.toString(i+1);
			ResultSet rs = Veritabani.listele(sorgu);
			while(rs.next()) {
				if(rs.getBoolean("dolu")==false) b[i].setBackground(Color.GREEN);
				else {
					b[i].setBackground(Color.RED);
					b[i].setEnabled(false);
				}
			}
		}
	}
}
