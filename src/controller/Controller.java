package controller;

import java.awt.event.ActionEvent;

import view.EmptyField;
import view.GUI;

public class Controller {
	public Controller(GUI gui) {
		
	}
	public void emptyFieldButtonOnClick(ActionEvent e) {
		try {
			EmptyField frame = new EmptyField();
			frame.setVisible(true);
		} catch (Exception event) {
			event.printStackTrace();
		}
	}
}
