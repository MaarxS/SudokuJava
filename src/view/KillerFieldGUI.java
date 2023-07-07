package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.KillerController;
import controller.Str8tsController;
import model.Position;

public class KillerFieldGUI extends SudokuFieldGUI {

	
	public KillerFieldGUI(KillerController controller, boolean addOn) {
		super(controller, addOn);
		
		lblTitleField.setText("Killer");
		if(addOn) {
			btnSolve.setText("Killer lösen");
	
		}
	}

}
