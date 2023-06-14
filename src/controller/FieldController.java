package controller;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import model.Field;
import model.SudokuField;
import view.EmptyField;
import model.Solver;

public class FieldController {
	private EmptyField emptyField;
	private Field playerField;
	public FieldController() {
		try {
			EmptyField frame = new EmptyField(this);
			frame.setVisible(true);
			emptyField = frame;
		} catch (Exception event) {
			event.printStackTrace();
		}

	}
	public void clearFieldOnClick(ActionEvent e) {
		for(int i = 0; i < 81; i++) {
			emptyField.setTextfield("", i);
		}
	}
	public void solveOnClick(ActionEvent e) {
		getEmptyField();
		Solver solver = new Solver(playerField);
		solver.solve();
		setEmptyField(playerField);
	}
	public void setEmptyField(Field field) {

		int fieldValue = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < 81; i++) {
			fieldValue = field.get(x, y);
			if(fieldValue == 0) {
				emptyField.setTextfield("", i);
			}else {
				emptyField.setTextfield(String.valueOf(fieldValue), i);
			}
			x++;
			if(x == 9) {
				x = 0;
				y++;
			}
			
		}
	}
	public void getEmptyField() {

		int x = 0;
		int y = 0;
		JTextField[] field = emptyField.getTextfield();
		SudokuField sudoku = new SudokuField();
		playerField = sudoku;
		for(int i = 0; i < 81; i++) {
			if(field[i].getText().equals("")) {
				sudoku.set(x, y, 0);
			}else {
				sudoku.set(x, y, Integer.parseInt(field[i].getText()));
			}
			x++;
			if(x == 9) {
				x = 0;
				y++;
			}
			
		}
	}

}
