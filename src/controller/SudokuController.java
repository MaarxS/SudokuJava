package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.JOptionPane;

import model.Field;
import model.Position;
import model.SolverTask;
import view.SudokuFieldGUI;

public class SudokuController<T extends Field> {
	
	protected SudokuFieldGUI gui;
	protected T playerField;
	protected T solvedField;    
	private SolverTask<T> solverTask;
	private final int FIELDS_IN_ROW = 9;
	private final int TOTAL_FIELDS = 81;
	
	public SudokuController(T sudoku, T solvedSudoku) {
		playerField = sudoku;
		solvedField = solvedSudoku;
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
	
	public void cancelSolving(ActionEvent e) {
		endSolving();
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
		if (solverTask == null || solverTask.isDone()) {
			solverTask = new SolverTask<>(playerField);
			solverTask.addPropertyChangeListener((evt) -> {
				onLoadingStr8tsProgress(evt, solverTask);
			});
			solverTask.execute();
		}
	}

	private void onLoadingStr8tsProgress(PropertyChangeEvent evt, Future<T> field) {
		if ("progress".equals(evt.getPropertyName())) {
			int progress = (int) evt.getNewValue();
			gui.setProgress(progress);
			boolean loadingComplete = progress == 100;
			if (loadingComplete && !field.isCancelled()) {
				try {
					playerField = field.get();
					setTextFields(playerField);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*Diese Methode deckt zufälliig ein Feld von der Lösung auf */
	public void showTippOnClick(ActionEvent e) {
		showMistakesOnClick(null);
		if(playerField.isSolved()) {
			return;
		}
		Random random = new Random();

		int index;
		Position pos;
		do {
			index = random.nextInt(TOTAL_FIELDS);
			pos = new Position(index % FIELDS_IN_ROW, index / FIELDS_IN_ROW);
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
	
	/* Methode sorgt dafür, dass der Nutzer keine vorgegeben Werte löschen kann*/
	private void setInitalValuesUneditable() {
		for (Position pos : Position.iterateAll()) {
			if (!gui.getTextfield(pos).equals("")) {
				gui.setEditable(pos, false);
			}
		}
	}
	/* Benutzereingabe einlesen und auf Eingabefehler überprüfen */
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
					int value = Integer.parseInt(gui.getTextfield(pos));
					if(value > 9 || value < 0) {
						gui.setColor(pos, SudokuFieldGUI.COLOR_RED);
						isCorrect = false;
					} else {
						playerField.set(pos, value);
					}
				} catch(NumberFormatException e) {
					gui.setColor(pos, SudokuFieldGUI.COLOR_RED);
					isCorrect = false;
				}
				
			}
			
		}
		return isCorrect;
	}
	
	/* Auf Logikfehler prüfen, anschließend alle Fehler anzeigen */
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
		gui.setProgress(100);
		if (solverTask != null) {
			solverTask.cancel(false);
		}
	}
}
