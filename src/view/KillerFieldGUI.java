package view;

import controller.KillerController;


public class KillerFieldGUI extends SudokuFieldGUI {

	
	public KillerFieldGUI(KillerController controller, boolean addOn) {
		super(controller, addOn);
		
		lblTitleField.setText("Killer");
		if(addOn) {
			btnSolve.setText("Killer lösen");
	
		}
	}

}
