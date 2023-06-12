package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);

		JLabel lblTitle = new JLabel("Sudoku Rechner");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitle);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSolveType = new JLabel("Sudoku lösen lassen");
		lblSolveType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolveType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSolveType.setBounds(0, 11, 149, 38);
		panel_1.add(lblSolveType);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnOpenEmpty = new JButton("Feld öffnen");
		btnOpenEmpty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOpenEmpty.setBounds(10, 11, 100, 30);
		panel.add(btnOpenEmpty);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCreateType = new JLabel("Sudoku erstellen");
		lblCreateType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreateType.setBounds(10, 0, 110, 38);
		panel_2.add(lblCreateType);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Leicht", "Mittel", "Schwer"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBounds(20, 5, 100, 30);
		panel_3.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnCreate = new JButton("Erstellen");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreate.setBounds(10, 5, 100, 30);
		panel_4.add(btnCreate);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnModeSudoku = new JButton("Sudoku");
		btnModeSudoku.addActionListener(event -> {
			lblTitle.setText("Sudoku Rechner");
			lblCreateType.setText("Sudoku erstellen");
			lblSolveType.setText("Sudoku lösen lassen");
		});
		menuBar.add(btnModeSudoku);

		JButton btnModeStr8 = new JButton("Str8");
		btnModeStr8.addActionListener(event -> {
			lblTitle.setText("Str8 Rechner");
			lblCreateType.setText("Str8 erstellen");
			lblSolveType.setText("Str8 lösen lassen");
		});
		menuBar.add(btnModeStr8);

		JButton btnModeKiller = new JButton("Killer");
		btnModeKiller.addActionListener(event -> {
			lblTitle.setText("Killer Rechner");
			lblCreateType.setText("Killer erstellen");
			lblSolveType.setText("Killer lösen lassen");
		});
		menuBar.add(btnModeKiller);
	}
}
