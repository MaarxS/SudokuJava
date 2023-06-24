package controller;

import java.awt.event.ActionEvent;

import model.FieldGenerator;
import model.Str8tsField;
import model.SudokuField;
import view.GUI;
import view.Str8tsFieldGUI;
import view.SudokuFieldGUI;



public class Controller {

	private GUI mainGUI;
	private FieldGenerator fieldGenerator = new FieldGenerator();
	private enum gameMode {
		SUDOKU,
		STR8TS,
		KILLER;
	}
	private gameMode mode = gameMode.SUDOKU;

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
		switch(mode)
		{
		case SUDOKU:
			SudokuField sudoku = new SudokuField();
			if (!isNewFieldEmpty) {
				fieldGenerator.generateSolvable(sudoku);
			}
			SudokuController sudokuController = new SudokuController(sudoku);
			SudokuFieldGUI sudokuGui = new SudokuFieldGUI(sudokuController, isNewFieldEmpty);
			sudokuController.setGUI(sudokuGui);
			break;
			
		case STR8TS:
			Str8tsField str8tsField = new Str8tsField();
			if (!isNewFieldEmpty) {
				fieldGenerator.generateSolvable(str8tsField);
			}
			Str8tsController str8tsController = new Str8tsController(str8tsField);
			Str8tsFieldGUI gui = new Str8tsFieldGUI(str8tsController, isNewFieldEmpty);
			str8tsController.setGUI(gui);
			break;
		case KILLER:

			break;
		}
	}

	public void modeSudokuOnClick(ActionEvent e) {
		mode = gameMode.SUDOKU;
		mainGUI.setLabelList(0, "Sudoku Rechner");
		mainGUI.setLabelList(1, "Sudoku lösen lassen");
		mainGUI.setLabelList(2, "Sudoku erstellen");		
	}


	public void modeStr8OnClick(ActionEvent e) {
		mode = gameMode.STR8TS;
		mainGUI.setLabelList(0, "Str8 Rechner");
		mainGUI.setLabelList(1, "Str8 lösen lassen");
		mainGUI.setLabelList(2, "Str8 erstellen");
	}


	public void modeKillerOnClick(ActionEvent e) {
		mode = gameMode.KILLER;
		mainGUI.setLabelList(0, "Killer Rechner");
		mainGUI.setLabelList(1, "Killer lösen lassen");
		mainGUI.setLabelList(2, "Killer erstellen");
	}



}
