package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.KillerController;
import controller.Str8tsController;

public class KillerFieldGUI extends SudokuFieldGUI {

	
	public KillerFieldGUI(KillerController controller, boolean addOn) {
		super(controller, addOn);
		
		lblTitleField.setText("Killer");
	}

}
