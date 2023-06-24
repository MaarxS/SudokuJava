package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Field;
import model.Position;
import model.Solver;
import model.SudokuField;
import view.SudokuFieldGUI;

public abstract class FieldController {
	protected SudokuFieldGUI emptyField;
	protected Field playerField;
	protected JTextField[] field;
	
	public FieldController(SudokuField sudoku) {
		playerField = sudoku;
		setEmptyField(sudoku);
		
	}
	
	public void clearFieldOnClick(ActionEvent e) {
		getEmptyField();
		for(int i = 0; i < 81; i++) {
			emptyField.setTextfield("", i);
			field[i].setBackground(new Color(70, 73, 75));
		}
	}
	public void solveOnClick(ActionEvent e) {
		boolean inputCorrect = getEmptyField();
		if(!inputCorrect) {
			return;
		}
		Solver solver = new Solver();
		playerField = solver.solve(playerField);
		setEmptyField(playerField);
	}
	public void showTippOnClick(ActionEvent e) {
		
	}
	public void setEmptyField(Field field) {
		int fieldValue = 0;
		for(int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			fieldValue = field.get(pos);
			if(fieldValue == 0) {
				emptyField.setTextfield("", i);
			}else {
				emptyField.setTextfield(String.valueOf(fieldValue), i);
			}
		}
	}

	public boolean getEmptyField() {
		boolean isCorrect = true;
		field = emptyField.getTextfield();
		
		for(int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			JTextField currentField = field[i];
			currentField.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
						currentField.setBackground(new Color(70, 73, 75));
						System.out.println("is done");
				}
			});

			if(field[i].getText().equals("0")) {
				field[i].setBackground(new Color(148, 46, 46));
				isCorrect = false;
			}
			else if(field[i].getText().equals("")) {
				playerField.set(pos, 0);
				field[i].setBackground(new Color(70, 73, 75));
			}else {

				try {
					playerField.set(pos, Integer.parseInt(field[i].getText()));
					field[i].setBackground(new Color(70, 73, 75));
					if(playerField.get(pos) > 9) {
						field[i].setBackground(new Color(148, 46, 46));
						isCorrect = false;
					}
				}catch(NumberFormatException e) {
					field[i].setBackground(new Color(148, 46, 46));
					isCorrect = false;
				}
			}
		}
		if(!isCorrect) {
			JOptionPane.showMessageDialog(null,"Bitte überprüfen Sie Ihre Eingabe.");
		}
		return isCorrect;
	}
}
