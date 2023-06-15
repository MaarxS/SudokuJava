package controller;

import java.awt.event.ActionEvent;

import model.SudokuField;
import view.GUI;


public class Controller {
	
	private GUI mainGUI;
	
	public Controller(GUI gui) {
		mainGUI = gui;
	}

	
	public void emptyFieldButtonOnClick(ActionEvent e) {
		
		SudokuField sudoku = new SudokuField();
		new SudokuController(sudoku);
	}
	
	
	public void modeSudokuOnClick(ActionEvent e) {
		
		mainGUI.setLabelList(0, "Sudoku Rechner");
		mainGUI.setLabelList(1, "Sudoku lösen lassen");
		mainGUI.setLabelList(2, "Sudoku erstellen");		
	}
	
	
	public void modeStr8OnClick(ActionEvent e) {
		
		mainGUI.setLabelList(0, "Str8 Rechner");
		mainGUI.setLabelList(1, "Str8 lösen lassen");
		mainGUI.setLabelList(2, "Str8 erstellen");
	}
	
	
	public void modeKillerOnClick(ActionEvent e) {
		
		mainGUI.setLabelList(0, "Killer Rechner");
		mainGUI.setLabelList(1, "Killer lösen lassen");
		mainGUI.setLabelList(2, "Killer erstellen");
	}
	


}
