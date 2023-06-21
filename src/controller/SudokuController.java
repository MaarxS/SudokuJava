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
	protected JTextField[] textFields;
	
	public static final Color COLOR_RED = new Color(148, 46, 46);
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);
	
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
		setTextFields(playerField);
		initializeTextFields();
	}
	
	protected SudokuController(SudokuFieldGUI emptyField, SudokuField sudoku) {
		this.emptyField = emptyField;
		playerField = sudoku;
	}
	
	public void clearFieldOnClick(ActionEvent e) {
		for(int i = 0; i < 81; i++) {
			emptyField.setTextfield("", i);
			textFields[i].setBackground(COLOR_BACKGROUND);
		}
	}
	
	public void solveOnClick(ActionEvent e) {
		boolean inputCorrect = readTextFields();
		if(!inputCorrect) {
			return;
		}
		new Thread(() ->{
			Solver solver = new Solver(playerField);
			solver.setUpdateListener((field) -> {
				setTextFields(field);
			});
			solver.solve();
			setTextFields(playerField);
		}).start();
	}
	
	public void showTippOnClick(ActionEvent e) {
		// TODO
	}
	
	public void setTextFields(Field field) {

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
	
	public void initializeTextFields() {
		textFields = emptyField.getTextfield();
		
		for(int i = 0; i < 81; i++) {
			JTextField currentField = textFields[i];
			currentField.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if (currentField.getBackground().equals(COLOR_RED)) {
						currentField.setBackground(COLOR_BACKGROUND);
					}
				}
			});
		}
	}
	
	public boolean readTextFields() {
		int x = 0;
		int y = 0;
		boolean isCorrect = true;
		
		for (int i = 0; i < 81; i++) {
			if(textFields[i].getText().equals("0")) {
				textFields[i].setBackground(COLOR_RED);
				isCorrect = false;
			}
			else if(textFields[i].getText().equals("")) {
				playerField.set(x, y, 0);
			}else {

				try {
					playerField.set(x, y, Integer.parseInt(textFields[i].getText()));
					if(playerField.get(x, y) > 9) {
						textFields[i].setBackground(COLOR_RED);
						isCorrect = false;
					}
				}catch(NumberFormatException e) {
					textFields[i].setBackground(COLOR_RED);
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
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			if (playerField.isEditable(x, y) && !playerField.isCorrect(x, y)) {
				textFields[i].setBackground(COLOR_RED);
			}
		}
	}
}
