package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.SudokuController;
import model.Position;


public class SudokuFieldGUI extends JFrame {

	protected JPanel contentPane;

	protected JButton btnSolve;
	protected JPanel controlPanel;
	protected JPanel titlePanel;
	protected JLabel lblTitleField;
	protected TextFieldGridPanel fieldPanel;
	private JPanel centerPanel;
	private JButton btnCancel;
	private JButton btnClearFields;
	private JProgressBar progressBar;
	
	public static final Color COLOR_RED = new Color(148, 46, 46);
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);
	public static final Color COLOR_GREEN = new Color(11, 120, 11);

	public SudokuFieldGUI(SudokuController fieldController, boolean addOn) {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				dispose(); 
				fieldController.endSolving();
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);

		btnClearFields = new JButton("Felder löschen");
		controlPanel.add(btnClearFields);
		btnClearFields.addActionListener(fieldController::clearFieldOnClick);


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
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		contentPane.add(centerPanel, BorderLayout.CENTER);

		fieldPanel = new TextFieldGridPanel();
		centerPanel.add(fieldPanel, BorderLayout.CENTER);
		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		centerPanel.add(progressBar, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(fieldController::cancelSolving);

		controlPanel.add(btnCancel);
		btnCancel.setVisible(false);
		JButton btnCheck = new JButton("Lösung überprüfen");
		JButton btnTipp = new JButton("Lösungstipp");
		btnSolve = new JButton("Sudoku lösen");
		if(!addOn) {
			btnCheck.addActionListener(fieldController::showMistakesOnClick);
			controlPanel.add(btnCheck);

			controlPanel.add(btnTipp);
			btnTipp.addActionListener(fieldController::showTippOnClick);
		} else {
			controlPanel.add(btnSolve);
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
	
	/**
	 * Shows a progress bar and replaces solve button with cancel button
	 * if called with a number from 0 to 99. 
	 * Hides the ProgressBar when called with 100.
	 * @param progress a number from 0 to 100
	 */
	public void setProgress(int progress) {
		if (progress == 100) {
			btnSolve.setVisible(true);
			btnCancel.setVisible(false);
			progressBar.setVisible(false);
			progressBar.setIndeterminate(false);
		} else if (!progressBar.isVisible()) {
			btnSolve.setVisible(false);
			btnCancel.setVisible(true);
			progressBar.setValue(progress);
			progressBar.setVisible(true);
		} else {
			if (progress == 99) {
				progressBar.setIndeterminate(true);
			} else {
				progressBar.setValue(progress);
			}
		}
	}
}
