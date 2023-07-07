package controller;

import java.awt.event.ActionEvent;

import model.KillerField;
import model.Position;
import view.SudokuFieldGUI;

public class KillerController extends SudokuController<KillerField>{

	public KillerController(KillerField killer, KillerField solvedKiller) {
		super(killer, solvedKiller);
	}
	@Override
	public void setGUI(SudokuFieldGUI killerFieldGUI) {
		super.setGUI(killerFieldGUI);

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
