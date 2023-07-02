package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.SudokuController;
import model.Position;

import java.awt.Color;


public class SudokuFieldGUI extends JFrame {

	protected JPanel contentPane;

	protected JButton btnSolve;
	protected JPanel controlPanel;
	protected JPanel titlePanel;
	protected JLabel lblTitleField;
	protected TextFieldGridPanel fieldPanel;
	
	public static final Color COLOR_RED = new Color(148, 46, 46);
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);
	public static final Color COLOR_GREEN = new Color(11, 120, 11);

	public SudokuFieldGUI(SudokuController fieldController, boolean addOn) {
		
		fieldPanel = new TextFieldGridPanel();

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
		
		contentPane.add(fieldPanel, BorderLayout.CENTER);
		
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
	

	public void setTextfield(Position position, String text) {
		fieldPanel.setText(position, text);
	}
	
	public String getTextfield(Position position) {
		return fieldPanel.getText(position);
	}
	
	public void setColor(Position position, Color color){
		fieldPanel.setColor(position, color);
	}
	
	public Color getColor(Position position){
		return fieldPanel.getColor(position);
	}
	public boolean isEditable(Position position) {
		return fieldPanel.isEditable(position);
	}
	
	public void setEditable(Position position, Boolean value) {
		fieldPanel.setEditable(position, value);
	}
}
