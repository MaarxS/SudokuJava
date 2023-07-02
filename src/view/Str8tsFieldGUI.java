package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import controller.Str8tsController;
import model.Position;

public class Str8tsFieldGUI extends SudokuFieldGUI {

	protected JCheckBox cbPaintBlack;
	public static final Color COLOR_BLACK = new Color(0, 0, 0);

	public Str8tsFieldGUI(Str8tsController controller, boolean addOn) {
		super(controller, addOn);

		lblTitleField.setText("Str8ts");

		if(addOn) {
			btnSolve.setText("Str8ts lösen");
			cbPaintBlack = new JCheckBox("Felder färben");
			controlPanel.add(cbPaintBlack);
			for(Position pos : Position.iterateAll()) {
				fieldPanel.addTextFieldMouseListener(pos, new MouseAdapter() {
					public void mousePressed(MouseEvent e){
						if(cbPaintBlack.isSelected()) {
							if (fieldPanel.getColor(pos).equals(COLOR_BACKGROUND)) {
								fieldPanel.setColor(pos, COLOR_BLACK);
								controller.setBlack(pos, true);
							} else {
								fieldPanel.setColor(pos, COLOR_BACKGROUND);
								controller.setBlack(pos, false);
							}
						}
					}
				});
			}	
		}
	}
}
