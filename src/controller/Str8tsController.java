package controller;

import java.awt.event.ActionEvent;

import model.Position;
import model.Str8tsField;
import view.Str8tsFieldGUI;
import view.SudokuFieldGUI;


public class Str8tsController extends SudokuController<Str8tsField> {

	public Str8tsController(Str8tsField str8tsField, Str8tsField solvedField) {
		super(str8tsField, solvedField);
		playerField = str8tsField;
	}
	
	/** 
	 * Takes a {@link Str8tsFieldGUI} which extends {@link SudokuFieldGUI}.<br />
	 * The Argument is SudokuField, because it has to override the method in {@link SudokuController}. 
	 */
	@Override
	public void setGUI(SudokuFieldGUI str8tsFieldGUI) {
		super.setGUI(str8tsFieldGUI);
		for (Position pos : Position.iterateAll()) {
			if (playerField.isBlack(pos)) {
				gui.setColor(pos, Str8tsFieldGUI.COLOR_BLACK);
				gui.setEditable(pos, false);
			}
		}
	}

	public void setBlack(Position pos, boolean value) {
		playerField.setBlack(pos, value);
	}
	
	@Override
	public void clearFieldOnClick(ActionEvent e) {
		for (Position pos : Position.iterateAll()) {
			if (gui.isEditable(pos)) {
				gui.setTextfield(pos, "");
				gui.setColor(pos, SudokuFieldGUI.COLOR_BACKGROUND);
				playerField.setBlack(pos, false);
				playerField.set(pos, 0);
			}
		}
	}
}
