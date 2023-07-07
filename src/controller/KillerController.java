package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import model.KillerField;
import model.Position;
import view.KillerFieldGUI;
import view.SudokuFieldGUI;

public class KillerController extends SudokuController<KillerField>{

	private KillerFieldGUI gui;
	
	public KillerController(KillerField killer, KillerField solvedKiller) {
		super(killer, solvedKiller);
	}
	@Override
	public void setGUI(SudokuFieldGUI killerFieldGUI) {
		super.setGUI(killerFieldGUI);
		gui = (KillerFieldGUI) killerFieldGUI;
	}
	
	public void onAddGroup(ActionEvent event) {
		String input = gui.showNumberDialog();
		if (input == null) {
			return;
		}
		try {
			int sum = Integer.parseInt(input);
			if (sum <= 0) {
				JOptionPane.showMessageDialog(null, "Die Summe muss mindestens 1 sein");
				return;
			}
			if (sum >= 100) {
				JOptionPane.showMessageDialog(null, "Die Summe muss kleiner als 100 sein.");
				return;
			}
			// TODO get group count
			playerField.setSum(1, sum);
			for (Position pos : Position.iterateAll()) {
				if (gui.getColor(pos) == KillerFieldGUI.COLOR_SELECTED) {
					playerField.setGroup(pos, 1);
					gui.setColor(pos, KillerFieldGUI.COLOR_BACKGROUND);
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Geben Sie eine Zahl ein.");
		}
	}
	
	@Override
	public void clearFieldOnClick(ActionEvent e) {
		super.clearFieldOnClick(e);
		// TODO entferne Ränder
	}

}
