package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Field;
import model.SudokuField;
import view.SudokuFieldGUI;
import model.Solver;

public class SudokuController {
	
	
	protected SudokuFieldGUI emptyField;
	private Field playerField;
	protected JTextField[] field;
	
	public SudokuController(Field sudoku) {
		playerField = sudoku;
	}
	
	public void setGUI(SudokuFieldGUI frame) {
		emptyField = frame;
		try {
			frame.setVisible(true);
		} catch (Exception event) {
			event.printStackTrace();
		}
		setEmptyField(playerField);
	}
	
	protected SudokuController(SudokuFieldGUI emptyField, SudokuField sudoku) {
		this.emptyField = emptyField;
		playerField = sudoku;
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
		Solver solver = new Solver(playerField);
		solver.solve();
		setEmptyField(playerField);
	}
	
	public void showTippOnClick(ActionEvent e) {
		
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
		field = emptyField.getTextfield();
		
		for(int i = 0; i < 81; i++) {
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
				playerField.set(x, y, 0);
				field[i].setBackground(new Color(70, 73, 75));
			}else {

				try {
					playerField.set(x, y, Integer.parseInt(field[i].getText()));
					field[i].setBackground(new Color(70, 73, 75));
					if(playerField.get(x, y) > 9) {
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
	
	public void showMistakesOnClick(ActionEvent e) {
		getEmptyField();
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			boolean t = playerField.isCorrect(x, y);
			if (playerField.isEditable(x, y) && !playerField.isCorrect(x, y)) {
				field[i].setBackground(new Color(148, 46, 46));
			}
		}
	}
}
