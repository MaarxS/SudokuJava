package controller;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import model.SudokuField;
import view.Str8FieldGUI;


public class Str8Controller extends SudokuController{

	private Str8FieldGUI emptyField;

	public Str8Controller(SudokuField str8, boolean isNewFieldEmpty) {
		super(str8, isNewFieldEmpty);
		try {
			Str8FieldGUI frame = new Str8FieldGUI(this, isNewFieldEmpty);
			frame.setVisible(true);
			emptyField = frame;
		} catch (Exception event) {
			event.printStackTrace();
		}
		setEmptyField(str8);
	}
	public void setBlack(ItemEvent e) {
		field = emptyField.getTextfield();
		for(int i = 0; i < 81; i++) {
			JTextField currentField = field[i];
			field[i].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if(emptyField.getCheckBox().isSelected()) {
						currentField.setBackground(new Color(0, 0, 0));
					}
				}
			});
		}
	}
}
