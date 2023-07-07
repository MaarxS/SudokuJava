package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import controller.KillerController;
import model.Position;

public class KillerFieldGUI extends SudokuFieldGUI {

	public static final Color COLOR_SELECTED = Color.BLUE;
	
	public KillerFieldGUI(KillerController controller, boolean addOn) {
		super(controller, addOn);
		
		lblTitleField.setText("Killer");
		if(addOn) {
			btnSolve.setText("Killer lösen");
			JButton btnAddGroup = new JButton("Gruppe hinzufügen");
			btnAddGroup.addActionListener(controller::onAddGroup);
			controlPanel.add(btnAddGroup);
			JCheckBox cbSelectGroup = new JCheckBox("Gruppe markiern");
			controlPanel.add(cbSelectGroup);
			for(Position pos : Position.iterateAll()) {
				fieldPanel.addTextFieldMouseListener(pos, new MouseAdapter() {
					public void mousePressed(MouseEvent e){
						if(cbSelectGroup.isSelected()) {
							if (fieldPanel.getColor(pos).equals(COLOR_BACKGROUND)) {
								fieldPanel.setColor(pos, COLOR_SELECTED);
							} else {
								fieldPanel.setColor(pos, COLOR_BACKGROUND);
							}
						}
					}
				});
			}
		}
	}

	public String showNumberDialog() {
		return JOptionPane.showInputDialog("Geben Sie die Summe der Gruppe ein:");
	}
}
