package controller;

import java.awt.event.ActionEvent;

import model.Position;
import model.Str8tsField;
import view.Str8tsFieldGUI;
import view.SudokuFieldGUI;


public class Str8tsController extends SudokuController{

	private Str8tsField playerField;

	public Str8tsController(Str8tsField str8tsField, Str8tsField solvedField) {
		super(str8tsField, solvedField);
		playerField = str8tsField;
	}
	
	
	public void setGUI(Str8tsFieldGUI str8tsFieldGUI) {
		super.setGUI(str8tsFieldGUI);
		gui = str8tsFieldGUI;
		for (int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
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
		for(int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			if (gui.isEditable(pos)) {
				gui.setTextfield(pos, "");
				gui.setColor(pos, SudokuFieldGUI.COLOR_BACKGROUND);
				playerField.setBlack(pos, false);
				playerField.set(pos, 0);
			}

		}
	}
	
}
