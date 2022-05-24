package postgreNormal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class form3 extends JFrame {

	private JPanel contentPane;
	static JButton[] b =new JButton[20];
	static String sorgu;
	static ResultSet rs;
	private JTextField tn;
	private JTextField ta;
	private JTextField ts;
	static ActionListener al;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form3 frame = new form3();
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
	public form3() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Numara");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(303, 23, 103, 29);
		contentPane.add(lblNewLabel);

		tn = new JTextField();
		tn.setEnabled(false);
		tn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tn.setBounds(303, 62, 156, 41);
		contentPane.add(tn);
		tn.setColumns(10);

		JLabel lblAd = new JLabel("Ad");
		lblAd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAd.setBounds(303, 123, 103, 29);
		contentPane.add(lblAd);

		ta = new JTextField();
		ta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ta.setColumns(10);
		ta.setBounds(303, 162, 156, 41);
		contentPane.add(ta);

		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoyad.setBounds(303, 227, 103, 29);
		contentPane.add(lblSoyad);

		ts = new JTextField();
		ts.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ts.setColumns(10);
		ts.setBounds(303, 266, 156, 41);
		contentPane.add(ts);

		JButton btnNewButton = new JButton("KAYDET");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(303, 344, 156, 41);
		contentPane.add(btnNewButton);

		Veritabani.baglan();
		boya();
		actionAta();
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgu = "update rezerve set "+
						"ad='"+ ta.getText() +"',"+
						"soyad='"+ ts.getText() +"',"+
						"dolu=true "+
						" where numara="+tn.getText();
				System.out.println(sorgu);
				Veritabani.guncelle(sorgu);
				try {
					temizle();
					boya();
					actionAta();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}
	
	void actionAta() {
		al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<20;i++) {
					if(e.getSource()==b[i]) tn.setText(b[i].getText()); 
				}
			}
		};

		for(int i=0;i<20;i++) b[i].addActionListener(al);
	}

	void temizle() {
		for(int i=0;i<10;i++) {
			contentPane.remove(b[i]);
			contentPane.repaint();
			contentPane.revalidate();
		}
	}

	void boya() throws SQLException {
		for(int i=0;i<10;i++) {
			sorgu = "select dolu from rezerve where numara=" + Integer.toString(i+1);
			rs = Veritabani.listele(sorgu);		
			b[i] = new JButton();
			contentPane.add(b[i]);
			b[i].setBounds(20, i*50+10, 50, 50);
			b[i].setText(Integer.toString(i+1));
			while(rs.next()) {
				if(rs.getBoolean("dolu")==false) b[i].setBackground(Color.GREEN);
				else {
					b[i].setBackground(Color.RED);
					b[i].setEnabled(false);
				}
			}

		}

		for(int i=10;i<20;i++) {
			sorgu = "select dolu from rezerve where numara=" + Integer.toString(i+1);
			rs = Veritabani.listele(sorgu);
			b[i] = new JButton();
			contentPane.add(b[i]);
			b[i].setBounds(160, (i-10)*50+10, 50, 50);
			b[i].setText(Integer.toString(i+1));
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
