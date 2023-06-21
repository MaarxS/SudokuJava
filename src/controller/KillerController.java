package controller;

import model.SudokuField;
import view.KillerFieldGUI;

public class KillerController extends SudokuController{

	public KillerController(SudokuField killer, boolean isNewFieldEmpty) {
		super(killer);
		try {
			KillerFieldGUI frame = new KillerFieldGUI(this, isNewFieldEmpty);
			frame.setVisible(true);
		} catch (Exception event) {
			event.printStackTrace();
		}
		setTextFields(killer);
	}

}
