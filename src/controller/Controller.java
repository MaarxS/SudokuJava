package controller;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import model.Field;
import view.EmptyField;
import view.GUI;

public class Controller {
	private EmptyField emptyField;
	private int[][] playerfield = new int[9][9];

	public Controller(GUI gui) {
	}

	public void emptyFieldButtonOnClick(ActionEvent e) {
		try {
			EmptyField frame = new EmptyField();
			frame.setVisible(true);
			emptyField = frame;
		} catch (Exception event) {
			event.printStackTrace();
		}
	}
	public void modeSudokuOnClick(ActionEvent e) {

	}
	public void modeStr8OnClick(ActionEvent e) {

	}
	public void modeKillerOnClick(ActionEvent e) {

	}
	public void setEmptyField(Field field) {
		
		int fieldValue = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < 81; i++) {
			fieldValue = field.get(x, y);
			emptyField.setTextfield(fieldValue, i);
			if(x == 9) {
				x = 0;
				y++;
			}
			x++;
			i++;
		}
	}
	public void getEmptyField() {
		
		int x = 0;
		int y = 0;
		JTextField[] field = emptyField.getTextfield();
		for(int i = 0; i < 81; i++) {
			playerfield[x][y] = Integer.parseInt(field[i].getText());
			if(x == 9) {
				x = 0;
				y++;
			}
			i++;
			x++;
			
		}
	}

}
