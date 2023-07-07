package controller;

import java.awt.event.ActionEvent;

import model.Position;
import model.SudokuField;
import view.KillerFieldGUI;
import view.Str8tsFieldGUI;
import view.SudokuFieldGUI;

public class KillerController extends SudokuController<SudokuField>{

	public KillerController(SudokuField killer, SudokuField solvedKiller) {
		super(killer, null);

		setTextFields(killer);
		playerField = killer;
	}
	@Override
	public void setGUI(SudokuFieldGUI killerFieldGUI) {
		super.setGUI(killerFieldGUI);
		for (Position pos : Position.iterateAll()) {

		}
	}
	@Override
	public void clearFieldOnClick(ActionEvent e) {
		for (Position pos : Position.iterateAll()) {
			if (gui.isEditable(pos)) {
				gui.setTextfield(pos, "");
				gui.setColor(pos, SudokuFieldGUI.COLOR_BACKGROUND);
				playerField.set(pos, 0);
			}
		}
	}

}
