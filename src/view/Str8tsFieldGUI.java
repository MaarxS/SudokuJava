package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import controller.Str8tsController;
import view.SudokuFieldGUI;

public class Str8tsFieldGUI extends SudokuFieldGUI {

	protected JCheckBox cbPaintBlack;
	
	public Str8tsFieldGUI(Str8tsController controller, boolean addOn) {
		super(controller, addOn);
		
		lblTitleField.setText("Str8ts");
		
//		if(addOn) {
			btnSolve.setText("Str8ts lösen");
			cbPaintBlack = new JCheckBox("Schwarze Felder");
			panel_1.add(cbPaintBlack);
			cbPaintBlack.addItemListener(controller::setBlack);
//		}

		
	}
	public JCheckBox getCheckBox() {
		return cbPaintBlack;
	}

}
