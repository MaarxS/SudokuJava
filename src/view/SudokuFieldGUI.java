package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import controller.SudokuController;
import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SudokuFieldGUI extends JFrame {

	protected JPanel contentPane;
	protected JTextField[] tf = new JTextField[81];
	protected JPanel[] panels = new JPanel[9];
	protected JButton btnSolve;
	protected JPanel panel_1;
	protected JPanel panel_4;
	protected JLabel lblTitleField;

	public SudokuFieldGUI(SudokuController fieldController, boolean addOn) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 3, 0, 0));

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnClearFields = new JButton("Felder löschen");
		panel_1.add(btnClearFields);
		btnClearFields.addActionListener(fieldController::clearFieldOnClick);

		btnSolve = new JButton("Sudoku lösen");
		panel_1.add(btnSolve);
		btnSolve.addActionListener(fieldController::solveOnClick);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblTitleField = new JLabel("Sudoku");
		lblTitleField.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitleField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTitleField);

		for (int i = 0; i < 9; i++) {
			panels[i] = new JPanel();
			panels[i].setBorder(BorderFactory.createEtchedBorder());
			panels[i].setLayout(new GridLayout(3, 3, 0, 0));
			panel.add(panels[i]);

		}
		int i = 0;
		int j = 0;
		while (i < 81) {
			tf[i] = new JTextField();
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			panels[j].add(tf[i]);
			tf[i].setColumns(3);
			i++;
			if (i % 3 == 0) {
				j++;
				if (j == 3 && i < 27) {
					j = 0;
				} else if (j == 6 && i < 54) {
					j = 3;
				} else if (j == 9 && i < 81) {
					j = 6;
				}
			}
		}
		if(!addOn) {
			btnSolve.setText("Lösung überprüfen");
			
			JButton btnTipp = new JButton("Lösungstipp");
			panel_1.add(btnTipp);
			btnTipp.addActionListener(fieldController::showTippOnClick);
		}
	}

	public JTextField[] getTextfield() {
		return tf;
	}

	public void setTextfield(String value, int index) {
		tf[index].setText(value);
	}
	
	public void setTitle(String title) {
		lblTitleField.setText(title);
	}
}
