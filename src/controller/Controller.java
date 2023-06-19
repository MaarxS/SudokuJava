package controller;

import java.awt.event.ActionEvent;

import model.FieldGenerator;
import model.SudokuField;
import view.GUI;
import view.Str8tsFieldGUI;
import view.SudokuFieldGUI;



public class Controller {

	private GUI mainGUI;
	private FieldGenerator fieldGenerator = new FieldGenerator();
	private String gameMode = "Sudoku";

	public Controller(GUI gui) {
		mainGUI = gui;
	}


	public void emptyFieldButtonOnClick(ActionEvent e) {
		createNewField(true);
	}

	public void onClickCreate(ActionEvent e) {
		createNewField(false);
	}
	
	private void createNewField(boolean isNewFieldEmpty) {
		switch(gameMode)
		{
		case "Sudoku":
			SudokuField sudoku = new SudokuField();
			if (!isNewFieldEmpty) {
				fieldGenerator.generateSolvable(sudoku);
			}
			SudokuController sudokuController = new SudokuController(sudoku);
			SudokuFieldGUI sudokuGui = new SudokuFieldGUI(sudokuController, isNewFieldEmpty);
			sudokuController.setGUI(sudokuGui);
			break;
			
		case "Str8":
			SudokuField str8tsField = new SudokuField();
			if (!isNewFieldEmpty) {
				fieldGenerator.generateSolvable(str8tsField);
			}
			Str8tsController str8tsController = new Str8tsController(str8tsField);
			Str8tsFieldGUI gui = new Str8tsFieldGUI(str8tsController, isNewFieldEmpty);
			str8tsController.setGUI(gui);
			break;
		case "Killer":

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
