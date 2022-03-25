package postgreNormal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class form extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String  sorgu = "select * from ogrenci";
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = {"Numara","Ad","Soyad","Telefon"};
	Object[] satirlar = new Object[4];
	private JTextField txtSelectFrom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form frame = new form();
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
	public form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 72, 487, 264);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Sorgula");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(397, 24, 128, 37);
		contentPane.add(btnNewButton);
		
		txtSelectFrom = new JTextField();
		txtSelectFrom.setText("select * from ogrenci");
		txtSelectFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSelectFrom.setBounds(38, 24, 333, 30);
		contentPane.add(txtSelectFrom);
		txtSelectFrom.setColumns(10);
		
		Veritabani.baglan();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorgu = txtSelectFrom.getText();
				modelim.setColumnCount(0);
				modelim.setRowCount(0);
				modelim.setColumnIdentifiers(kolonlar);
				
				ResultSet rs = Veritabani.listele(sorgu);
				
				try {
					while(rs.next()) {
						satirlar[0]=rs.getString("ogrencino");
						satirlar[1]=rs.getString("ad");
						satirlar[2]=rs.getString("soyad");
						satirlar[3]=rs.getString("tel");
						modelim.addRow(satirlar);
					}
					table.setModel(modelim);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
	}
}
