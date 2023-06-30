package controller;

import java.awt.event.ActionEvent;

import model.Position;
import model.Str8tsField;
import view.Str8tsFieldGUI;


public class Str8tsController extends SudokuController{

	private Str8tsField playerField;
	


	public Str8tsController(Str8tsField str8tsField) {
		super(str8tsField, null);
		playerField = str8tsField;
	}
	
	public void setGUI(Str8tsFieldGUI str8tsFieldGUI) {
		super.setGUI(str8tsFieldGUI);
	}

	public void setBlack(Position pos, boolean value) {
		playerField.setBlack(pos, value);
	}
	
	@Override
	public void clearFieldOnClick(ActionEvent e) {
		for(int i = 0; i < 81; i++) {
			Position pos = new Position(i % 9, i / 9);
			if (emptyField.isEditable(pos)) {
				emptyField.setTextfield(pos, "");
				emptyField.setColor(pos, COLOR_BACKGROUND);
				playerField.setBlack(pos, false);
			}

		}
	}
}
