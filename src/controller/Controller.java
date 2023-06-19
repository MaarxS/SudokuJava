package controller;

import java.awt.event.ActionEvent;

import model.FieldGenerator;
import model.SudokuField;
import view.GUI;



public class Controller {

	private GUI mainGUI;
	private FieldGenerator fieldGenerator = new FieldGenerator();
	private String gameMode = "Sudoku";
	private Boolean isNewFieldEmpty = true;

	public Controller(GUI gui) {
		mainGUI = gui;
	}


	public void emptyFieldButtonOnClick(ActionEvent e) {
		isNewFieldEmpty = true;
		switch(gameMode)
		{
		case "Sudoku":
			SudokuField sudoku = new SudokuField();
			new SudokuController(sudoku, isNewFieldEmpty);
			
			break;
			
			
		case "Str8":
			SudokuField str8 = new SudokuField();
			new Str8Controller(str8, isNewFieldEmpty);
			
			break;
		case "Killer":

			break;
		}

	}

	public void onClickCreate(ActionEvent e) {
		isNewFieldEmpty = false;
		switch(gameMode) {
		
		case "Sudoku":
			
			SudokuField sudoku = new SudokuField();
			fieldGenerator.generateSolvable(sudoku);
			new SudokuController(sudoku, isNewFieldEmpty);

			break;
			
			
		case "Str8":
			
			SudokuField str8 = new SudokuField();
			new Str8Controller(str8, isNewFieldEmpty);
			
			break;
			
			
		case "Killer":
			SudokuField killer = new SudokuField();
			new KillerController(killer, isNewFieldEmpty);
			break;
		}
	}

	public void modeSudokuOnClick(ActionEvent e) {

		gameMode = "Sudoku";
		mainGUI.setLabelList(0, "Sudoku Rechner");
		mainGUI.setLabelList(1, "Sudoku lösen lassen");
		mainGUI.setLabelList(2, "Sudoku erstellen");		
	}


	public void modeStr8OnClick(ActionEvent e) {

		gameMode = "Str8";
		mainGUI.setLabelList(0, "Str8 Rechner");
		mainGUI.setLabelList(1, "Str8 lösen lassen");
		mainGUI.setLabelList(2, "Str8 erstellen");
	}


	public void modeKillerOnClick(ActionEvent e) {

		gameMode = "Killer";
		mainGUI.setLabelList(0, "Killer Rechner");
		mainGUI.setLabelList(1, "Killer lösen lassen");
		mainGUI.setLabelList(2, "Killer erstellen");
	}



}
