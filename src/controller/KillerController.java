package controller;

import java.awt.event.ActionEvent;
import java.util.TreeSet;

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
		paintGroups();
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
			int groupNumber = playerField.getGroupCount() + 1;
			playerField.setSum(groupNumber, sum);
			boolean selectedSomething = false;
			for (Position pos : Position.iterateAll()) {
				if (gui.getColor(pos) == KillerFieldGUI.COLOR_SELECTED) {
					selectedSomething = true;
					playerField.setGroup(pos, groupNumber);
					gui.setColor(pos, KillerFieldGUI.COLOR_BACKGROUND);
				}
			}
			if (!selectedSomething) {
				playerField.setSum(groupNumber, 0);
				JOptionPane.showMessageDialog(null, "Es muss mindestens eine Feld ausgewählt sein.");
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Geben Sie eine Zahl ein.");
		}
		paintGroups();
	}
	
	public void paintGroups() {
		for (int i = 1; i <= playerField.getGroupCount(); i++) {
			TreeSet<Position> groupMembers = new TreeSet<>(playerField.getMembers(i));
			gui.paintBorderAround(groupMembers, String.valueOf(playerField.getSum(i)));
		}
	}
	
	@Override
	public void clearFieldOnClick(ActionEvent e) {
		super.clearFieldOnClick(e);
		playerField = new KillerField();
		gui.clearBorders();
	}

}
