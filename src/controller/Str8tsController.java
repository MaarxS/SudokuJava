package controller;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import model.Str8tsField;
import view.Str8tsFieldGUI;


public class Str8tsController extends SudokuController{

	private Str8tsFieldGUI fieldGUI;
	private Str8tsField playerField;
	
	public static final Color COLOR_BLACK = new Color(0, 0, 0);

	public Str8tsController(Str8tsField str8tsField) {
		super(str8tsField);
		playerField = str8tsField;
	}
	
	public void setGUI(Str8tsFieldGUI str8tsFieldGUI) {
		super.setGUI(str8tsFieldGUI);
		fieldGUI = str8tsFieldGUI;
	}

	public void setBlack(ItemEvent e) {
		textFields = fieldGUI.getTextfield();
		for(int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			JTextField currentField = textFields[i];
			textFields[i].addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					if(fieldGUI.getCheckBox().isSelected()) {
						currentField.setBackground(COLOR_BLACK);
						playerField.setBlack(x, y, true);
					} else {
						currentField.setBackground(COLOR_BACKGROUND);
						playerField.setBlack(x, y, false);
					}
				}
			});
		}
	}
}
