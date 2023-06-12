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
import java.awt.BorderLayout;

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
		setBounds(100, 100, 470, 220);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		
		JPanel panel_24 = new JPanel();
		contentPane.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Sudoku Rechner");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_24.add(lblTitle, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSolveType = new JLabel("Sudoku lösen lassen");
		lblSolveType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSolveType.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblSolveType);
		
		JPanel panel_22 = new JPanel();
		panel_1.add(panel_22, BorderLayout.NORTH);
		
		JPanel panel_23 = new JPanel();
		panel_1.add(panel_23, BorderLayout.SOUTH);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnOpenEmpty = new JButton("Feld öffnen");
		btnOpenEmpty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(btnOpenEmpty);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9, BorderLayout.WEST);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10, BorderLayout.EAST);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCreateType = new JLabel("Sudoku erstellen");
		lblCreateType.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateType.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lblCreateType);
		
		JPanel panel_20 = new JPanel();
		panel_2.add(panel_20, BorderLayout.NORTH);
		
		JPanel panel_21 = new JPanel();
		panel_2.add(panel_21, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Leicht", "Mittel", "Schwer"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(comboBox);
		
		JPanel panel_16 = new JPanel();
		panel_3.add(panel_16, BorderLayout.NORTH);
		
		JPanel panel_17 = new JPanel();
		panel_3.add(panel_17, BorderLayout.WEST);
		
		JPanel panel_18 = new JPanel();
		panel_3.add(panel_18, BorderLayout.SOUTH);
		
		JPanel panel_19 = new JPanel();
		panel_3.add(panel_19, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnCreate = new JButton("Erstellen");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(btnCreate);
		
		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12, BorderLayout.NORTH);
		
		JPanel panel_13 = new JPanel();
		panel_4.add(panel_13, BorderLayout.WEST);
		
		JPanel panel_14 = new JPanel();
		panel_4.add(panel_14, BorderLayout.EAST);
		
		JPanel panel_15 = new JPanel();
		panel_4.add(panel_15, BorderLayout.SOUTH);

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
