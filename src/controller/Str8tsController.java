package controller;

import model.Position;
import model.Str8tsField;
import view.Str8tsFieldGUI;


public class Str8tsController extends SudokuController{

	private Str8tsField playerField;
	


	public Str8tsController(Str8tsField str8tsField) {
		super(str8tsField);
		playerField = str8tsField;
	}
	
	public void setGUI(Str8tsFieldGUI str8tsFieldGUI) {
		super.setGUI(str8tsFieldGUI);
	}

	public void setBlack(Position pos, boolean value) {
		playerField.setBlack(pos, value);
	}
}
