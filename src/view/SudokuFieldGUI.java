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
import java.awt.Component;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SudokuFieldGUI extends JFrame {

	protected JPanel contentPane;

	protected JButton btnSolve;
	protected JPanel controlPanel;
	protected JPanel titlePanel;
	protected JLabel lblTitleField;
	SudokuGUI sudoku = new SudokuGUI();


	public SudokuFieldGUI(SudokuController fieldController, boolean addOn) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		

		controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);

		JButton btnClearFields = new JButton("Felder löschen");
		controlPanel.add(btnClearFields);
		btnClearFields.addActionListener(fieldController::clearFieldOnClick);

		btnSolve = new JButton("Sudoku lösen");
		controlPanel.add(btnSolve);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		lblTitleField = new JLabel("Sudoku");
		lblTitleField.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitleField.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblTitleField);
		
		
		contentPane.add(sudoku, BorderLayout.CENTER);
		
		if(!addOn) {
			btnSolve.setText("Lösung überprüfen");
			btnSolve.addActionListener(fieldController::showMistakesOnClick);
			
			JButton btnTipp = new JButton("Lösungstipp");
			controlPanel.add(btnTipp);
			btnTipp.addActionListener(fieldController::showTippOnClick);
		} else {
			btnSolve.addActionListener(fieldController::solveOnClick);
		}
		
	}

	
	public void setTitle(String title) {
		lblTitleField.setText(title);
	}
	public JTextField[] getTextfield() {
		return sudoku.tf;
	}

	public void setTextfield(String value, int index) {
		sudoku.tf[index].setText(value);
	}
}
