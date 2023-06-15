package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Field;
import model.SudokuField;
import view.EmptyField;
import model.Solver;

public class FieldController {
	
	
	private EmptyField emptyField;
	private Field playerField;
	private SudokuField playerSudoku;
	
	public FieldController(SudokuField sudoku) {
		playerSudoku = sudoku;
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
		boolean inputCorrect = getEmptyField();
		if(!inputCorrect) {
			return;
		}
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
	public boolean getEmptyField() {

		int x = 0;
		int y = 0;
		boolean isCorrect = true;
		JTextField[] field = emptyField.getTextfield();
		
		playerField = playerSudoku;
		for(int i = 0; i < 81; i++) {

			if(field[i].getText().equals("0")) {
				field[i].setBackground(new Color(148, 46, 46));
				isCorrect = false;
			}
			else if(field[i].getText().equals("")) {
				playerSudoku.set(x, y, 0);
				field[i].setBackground(new Color(70, 73, 75));
			}else {

				try {
					playerSudoku.set(x, y, Integer.parseInt(field[i].getText()));
					field[i].setBackground(new Color(70, 73, 75));
					if(playerSudoku.get(x, y) > 9) {
						field[i].setBackground(new Color(148, 46, 46));
						isCorrect = false;
					}
				}catch(NumberFormatException e) {
					field[i].setBackground(new Color(148, 46, 46));
					isCorrect = false;
				}
			}
			x++;
			if(x == 9) {
				x = 0;
				y++;
			}

		}
		if(!isCorrect) {
			JOptionPane.showMessageDialog(null,"Bitte überprüfen Sie Ihre Eingabe.");
		}
		return isCorrect;
	}

}
