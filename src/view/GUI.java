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
		setBounds(100, 100, 450, 200);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Sudoku Rechner");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(124, 11, 180, 40);
		contentPane.add(lblTitle);

		JLabel lblSolveType = new JLabel("Sudoku lösen lassen");
		lblSolveType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSolveType.setBounds(63, 62, 127, 14);
		contentPane.add(lblSolveType);

		JLabel lblCreateType = new JLabel("Sudoku erstellen");
		lblCreateType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreateType.setBounds(63, 92, 127, 14);
		contentPane.add(lblCreateType);

		JButton btnLeeresOeffnen = new JButton("Feld öffnen");
		btnLeeresOeffnen.setBounds(315, 59, 102, 23);
		contentPane.add(btnLeeresOeffnen);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Leicht", "Mittel", "Schwer"}));
		comboBox.setBounds(200, 89, 89, 22);
		contentPane.add(comboBox);

		JButton btnErstellen = new JButton("Erstellen");
		btnErstellen.setBounds(315, 89, 102, 23);
		contentPane.add(btnErstellen);

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
