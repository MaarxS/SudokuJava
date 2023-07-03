package controller;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import model.Field;
import model.Position;
import model.Solver;
import model.SolverThread;
import view.SudokuFieldGUI;

public class SudokuController {
	
	protected SudokuFieldGUI gui;
	private Solver solver;
	private Field playerField;
	private Field solvedField;
	private SolverThread thread;
	
	public SudokuController(Field sudoku, Field solvedSudoku) {
		playerField = sudoku;
		solver = new Solver();
		solvedField = solvedSudoku;
		thread = new SolverThread(solver, playerField);
	}
	
	public void setGUI(SudokuFieldGUI frame) {
		gui = frame;
		try {
			frame.setVisible(true);
		} catch (Exception event) {
			event.printStackTrace();
		}
		setTextFields(playerField);
		setInitalValuesUneditable();
	}
	
	
	public void clearFieldOnClick(ActionEvent e) {
		endSolving();
		for(Position pos : Position.iterateAll()) {
			if (gui.isEditable(pos)) {
				gui.setTextfield(pos, "");
				gui.setColor(pos, SudokuFieldGUI.COLOR_BACKGROUND);
				playerField.set(pos, 0);
			}
		}
	}
	
	public void solveOnClick(ActionEvent e) {
		if(playerField.isSolved()) {
			JOptionPane.showMessageDialog(null, "Es sind bereits alle Felder ausgefüllt!");
			return;
		}
		boolean inputCorrect = showMistakesOnClick(e);
		if(!inputCorrect) {
			return;
		}
		
		Executors.newSingleThreadExecutor().execute(() -> {
			playerField = solver.solve(playerField);

			setTextFields(playerField);
			System.out.println(playerField);
		});
//		if(thread.getBlockOtherThreads())
//		{
//			return;
//		}
//		thread.run();
//		playerField = thread.getSolvedField();
	}
	
	public void showTippOnClick(ActionEvent e) {
		showMistakesOnClick(null);
		if(playerField.isSolved()) {
			return;
		}
		Random random = new Random();

		int index;
		Position pos;
		do {
			index = random.nextInt(81);
			pos = new Position(index % 9, index / 9);
		} while (!gui.getTextfield(pos).equals("") || !gui.isEditable(pos));
		gui.setColor(pos, SudokuFieldGUI.COLOR_GREEN);
		
		playerField.set(pos, solvedField.get(pos));
		setTextFields(playerField);
	}
	
	
	public void setTextFields(Field field) {

		int fieldValue = 0;
		for(Position pos : Position.iterateAll()) {
			fieldValue = field.get(pos);
			if(fieldValue == 0) {
				gui.setTextfield(pos, "");
			}else {
				gui.setTextfield(pos, String.valueOf(fieldValue));
			}
		}
	}
	
	private void setInitalValuesUneditable() {
		for (Position pos : Position.iterateAll()) {
			if (!gui.getTextfield(pos).equals("")) {
				gui.setEditable(pos, false);
			}
		}
	}
	
	public boolean readTextFields() {
		boolean isCorrect = true;
		
		for (Position pos : Position.iterateAll()) {
			if(gui.getTextfield(pos).equals("0")) {
				gui.setColor(pos, SudokuFieldGUI.COLOR_RED);
				isCorrect = false;
			} else if(gui.getTextfield(pos).equals("")) {
				playerField.set(pos, 0);
			} else {

				try {
					playerField.set(pos, Integer.parseInt(gui.getTextfield(pos)));
					if(playerField.get(pos) > 9) {
						gui.setColor(pos, SudokuFieldGUI.COLOR_RED);
						isCorrect = false;
					}
				} catch(NumberFormatException e) {
					gui.setColor(pos, SudokuFieldGUI.COLOR_RED);
					isCorrect = false;
				}
			}
		}
		return isCorrect;
	}
	
	public boolean showMistakesOnClick(ActionEvent e) {
		boolean isValid = true;
		isValid = readTextFields();
		
		for (Position pos : Position.iterateAll()) {
			if (gui.isEditable(pos) && !playerField.isCorrect(pos)) {
				gui.setColor(pos, SudokuFieldGUI.COLOR_RED);
				isValid = false;
			}
		}
		if(!isValid) {
			JOptionPane.showMessageDialog(null,"Bitte überprüfen Sie Ihre Eingabe.");
		}else if(playerField.isSolved()) {
			JOptionPane.showMessageDialog(null,"Das gesamte Feld wurde richtig gelöst.");
		}
		return isValid;
	}
	public void endSolving() {
		thread.setStopSolving(true);
	}
}
