package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import controller.Controller;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel[] labelList = new JLabel[9];

	

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
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());       
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 470, 220);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		
		JPanel panel_24 = new JPanel();
		contentPane.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		labelList[0] = new JLabel("Sudoku Rechner");
		labelList[0].setHorizontalAlignment(SwingConstants.CENTER);
		labelList[0].setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_24.add(labelList[0], BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		labelList[1] = new JLabel("Sudoku lösen lassen");
		labelList[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		labelList[1].setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(labelList[1]);
		
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
		
		labelList[2] = new JLabel("Sudoku erstellen");
		labelList[2].setHorizontalAlignment(SwingConstants.CENTER);
		labelList[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(labelList[2]);
		
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
		
		Controller controller = new Controller(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnModeSudoku = new JButton("Sudoku");
		btnModeSudoku.addActionListener(controller:: modeSudokuOnClick);		
		menuBar.add(btnModeSudoku);

		JButton btnModeStr8 = new JButton("Str8");
		btnModeStr8.addActionListener(controller::modeStr8OnClick);
		menuBar.add(btnModeStr8);

		JButton btnModeKiller = new JButton("Killer");
		btnModeKiller.addActionListener(controller::modeKillerOnClick);
		menuBar.add(btnModeKiller);
		
		btnOpenEmpty.addActionListener(controller::emptyFieldButtonOnClick);
	}
	public void setLabelList(int index, String text) {
		labelList[index].setText(text);
	}

}
