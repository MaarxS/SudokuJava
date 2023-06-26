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
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);

	public Str8tsFieldGUI(Str8tsController controller, boolean addOn) {
		super(controller, addOn);

		lblTitleField.setText("Str8ts");

		if(addOn) {
			btnSolve.setText("Str8ts lösen");
			cbPaintBlack = new JCheckBox("Schwarze Felder");
			controlPanel.add(cbPaintBlack);
			cbPaintBlack.addItemListener((e) -> {
				for(int i = 0; i < 81; i++) {
					Position pos = new Position(i % 9, i / 9);


					sudoku.getTextFieldForListener(i).addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							if(cbPaintBlack.isSelected()) {
								sudoku.setColor(pos, COLOR_BLACK);
								controller.setBlack(pos, true);
							} else {
								sudoku.setColor(pos, COLOR_BACKGROUND);
								controller.setBlack(pos, false);
							}

						}
					});
				}	
			});

		}


	}
	public JCheckBox getCheckBox() {
		return cbPaintBlack;
	}


}
