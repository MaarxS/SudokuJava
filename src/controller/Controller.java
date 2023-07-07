package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import model.FieldGenerator;
import model.Pair;
import model.Str8tsField;
import model.Str8tsGenerator;
import model.SudokuField;
import view.GUI;
import view.KillerFieldGUI;
import view.Str8tsFieldGUI;
import view.SudokuFieldGUI;



public class Controller {

	private GUI mainGUI;
	private FieldGenerator fieldGenerator = new FieldGenerator();
	
	private enum GameMode {
		SUDOKU,
		STR8TS, 
		KILLER;
	}
	private GameMode mode = GameMode.SUDOKU;

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
		int difficulty = mainGUI.getComboBoxIndex();
		switch(mode)
		{
		case SUDOKU:
			SudokuField sudoku;
			SudokuField solvedSudoku;
			
			if (isNewFieldEmpty) {
				sudoku = new SudokuField();
				solvedSudoku = null;
			} else {
				Pair<SudokuField, SudokuField> solvedAndUnsolved = fieldGenerator.generate(difficulty);
				sudoku = solvedAndUnsolved.unsolved;
				solvedSudoku = solvedAndUnsolved.solved;
			}
			
			SudokuController<SudokuField> sudokuController = new SudokuController<>(sudoku, solvedSudoku);
			SudokuFieldGUI sudokuGui = new SudokuFieldGUI(sudokuController, isNewFieldEmpty);
			sudokuController.setGUI(sudokuGui);
			break;
			
		case STR8TS:
			if (isNewFieldEmpty) {
				Str8tsController str8tsController = new Str8tsController(new Str8tsField(), null);
				Str8tsFieldGUI gui = new Str8tsFieldGUI(str8tsController, isNewFieldEmpty);
				str8tsController.setGUI(gui);
			} else {
				Str8tsGenerator str8tsGenerator = new Str8tsGenerator();
				str8tsGenerator.setDifficulty(difficulty);
				str8tsGenerator.addPropertyChangeListener((evt) -> {
					onLoadingStr8tsProgress(evt, str8tsGenerator);
				});
				str8tsGenerator.execute();
			}
			break;
		case KILLER:
			if (isNewFieldEmpty) {
				KillerController killerController = new KillerController(new SudokuField(), null);
				KillerFieldGUI frame = new KillerFieldGUI(killerController, isNewFieldEmpty);
				killerController.setGUI(frame);
			} else {
				
			}
			
			break;
		}
	}
	
	private void onLoadingStr8tsProgress(PropertyChangeEvent evt, Future<Pair<Str8tsField, Str8tsField>> field) {
		if ("progress".equals(evt.getPropertyName())) {
			int progress = (int) evt.getNewValue();
			mainGUI.setProgress(progress);
			boolean loadingComplete = progress == 100;
			if (loadingComplete) {
				try {
					Pair<Str8tsField, Str8tsField> fields = field.get();
					Str8tsController str8tsController = new Str8tsController(fields.solved, fields.unsolved);
					Str8tsFieldGUI gui = new Str8tsFieldGUI(str8tsController, false);
					str8tsController.setGUI(gui);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void modeSudokuOnClick(ActionEvent e) {
		mode = GameMode.SUDOKU;
		mainGUI.setLabelList(0, "Sudoku Rechner");
		mainGUI.setLabelList(1, "Sudoku lösen lassen");
		mainGUI.setLabelList(2, "Sudoku erstellen");		
	}


	public void modeStr8OnClick(ActionEvent e) {
		mode = GameMode.STR8TS;
		mainGUI.setLabelList(0, "Str8 Rechner");
		mainGUI.setLabelList(1, "Str8 lösen lassen");
		mainGUI.setLabelList(2, "Str8 erstellen");
	}


	public void modeKillerOnClick(ActionEvent e) {
		mode = GameMode.KILLER;
		mainGUI.setLabelList(0, "Killer Rechner");
		mainGUI.setLabelList(1, "Killer lösen lassen");
		mainGUI.setLabelList(2, "Killer erstellen");
	}



}
