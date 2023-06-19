package controller;

import java.awt.event.ActionEvent;

import model.FieldGenerator;
import model.SudokuField;
import view.GUI;
import view.SudokuFieldGUI;


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
			SudokuController controller = new SudokuController(sudoku);
			try {
				SudokuFieldGUI frame = new SudokuFieldGUI(controller, isNewFieldEmpty);
				frame.setVisible(true);
				controller.setGUI(frame);
			} catch (Exception event) {
				event.printStackTrace();
			}

		case "Str8":
			

		case "Killer":


		}

	}

	public void onClickCreate(ActionEvent e) {
		isNewFieldEmpty = false;
		switch(gameMode) {
		
		case "Sudoku":
			SudokuField sudoku = new SudokuField();
			fieldGenerator.generateSolvable(sudoku);
			SudokuController controller = new SudokuController(sudoku);
			try {
				SudokuFieldGUI frame = new SudokuFieldGUI(controller, isNewFieldEmpty);
				frame.setVisible(true);
				controller.setGUI(frame);
			} catch (Exception event) {
				event.printStackTrace();
			}
		case "Str8":
		case "Killer":
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
