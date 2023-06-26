package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import model.Field;
import model.Position;
import model.Solver;
import view.SudokuFieldGUI;

public class SudokuController {
	
	
	protected SudokuFieldGUI emptyField;
	private Solver solver;
	private Field playerField;
	private Field solvedField;
//	protected JTextField[] textFields;
	
	public static final Color COLOR_RED = new Color(148, 46, 46);
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);
	public static final Color COLOR_GREEN = new Color(11, 120, 11);
	
	
	public SudokuController(Field sudoku, Field solvedSudoku) {
		playerField = sudoku;
		solver = new Solver();
		solvedField = solvedSudoku;
		// TODO pass solved Field as parameter
	}
	
	public void setGUI(SudokuFieldGUI frame) {
		emptyField = frame;
		try {
			frame.setVisible(true);
		} catch (Exception event) {
			event.printStackTrace();
		}
		setTextFields(playerField);
//		initializeTextFields();
		setInitalValuesUneditable();
	}
	
	
	public void clearFieldOnClick(ActionEvent e) {
		for(int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			if (emptyField.isEditable(pos)) {
				emptyField.setTextfield(pos, "");
				emptyField.setColor(pos, COLOR_BACKGROUND);
			}

		}
	}
	
	public void solveOnClick(ActionEvent e) {
		if(playerField.isSolved()) {
			return;
		}
		boolean inputCorrect = readTextFields();
		if(!inputCorrect) {// TODO check if fields correct
			return;
		}
		System.out.println(playerField);
		playerField = solver.solve(playerField);
		setTextFields(playerField);
		System.out.println(playerField);
	}
	
	public void showTippOnClick(ActionEvent e) {
		if(playerField.isSolved()) {
			return;
		}
		readTextFields();
		Random random = new Random();

		int index;
		Position pos;
		do {
			index = random.nextInt(81);
			pos = new Position(index % 9, index / 9);
		} while (!emptyField.getTextfield(pos).equals(""));
		emptyField.setColor(pos, COLOR_GREEN);
		
		playerField.set(pos, solvedField.get(pos));
		setTextFields(playerField);
	}
	
	
	public void setTextFields(Field field) {

		int fieldValue = 0;
		for(int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			fieldValue = field.get(pos);
			if(fieldValue == 0) {
				emptyField.setTextfield(pos, "");
			}else {
				emptyField.setTextfield(pos, String.valueOf(fieldValue));
			}
		}
	}
	
	private void setInitalValuesUneditable() {
		for (int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);

			if (!emptyField.getTextfield(pos).equals("")) {
				emptyField.setEditable(pos, false);
			}
		}
	}


	
	public boolean readTextFields() {
		boolean isCorrect = true;
		
		for (int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			if(emptyField.getTextfield(pos).equals("0")) {
				emptyField.setColor(pos, COLOR_BACKGROUND);
				isCorrect = false;
			}
			else if(emptyField.getTextfield(pos).equals("")) {
				playerField.set(pos, 0);
			}else {

				try {
					playerField.set(pos, Integer.parseInt(emptyField.getTextfield(pos)));
					if(playerField.get(pos) > 9) {
						emptyField.setColor(pos, COLOR_BACKGROUND);
						isCorrect = false;
					}
				}catch(NumberFormatException e) {
					emptyField.setColor(pos, COLOR_RED);
					isCorrect = false;
				}
			}
		}
		if(!isCorrect) {
			JOptionPane.showMessageDialog(null,"Bitte überprüfen Sie Ihre Eingabe.");
		}
		return isCorrect;
	}
	
	public void showMistakesOnClick(ActionEvent e) {
		readTextFields();
		for (int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			if (emptyField.isEditable(pos) && !playerField.isCorrect(pos)) {
				emptyField.setColor(pos, COLOR_RED);
			}
		}
	}
}
