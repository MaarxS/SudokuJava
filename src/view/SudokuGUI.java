package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Position;


public class SudokuGUI extends JPanel{
	
	protected JTextField[] tf = new JTextField[81];
	protected JPanel[] panels = new JPanel[9];
	
	public SudokuGUI() {
//		this.setOpaque(false);
		this.setLayout(new GridLayout(3, 3, 0, 0));
		
		for (int i = 0; i < 9; i++) {
			panels[i] = new JPanel();
			panels[i].setBorder(BorderFactory.createEtchedBorder());
			panels[i].setLayout(new GridLayout(3, 3, 0, 0));
			this.add(panels[i]);

		}
		int i = 0;
		int j = 0;
		while (i < 81) {
			tf[i] = new JTextField();
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			panels[j].add(tf[i]);
			tf[i].setColumns(3);
			i++;
			if (i % 3 == 0) {
				j++;
				if (j == 3 && i < 27) {
					j = 0;
				} else if (j == 6 && i < 54) {
					j = 3;
				} else if (j == 9 && i < 81) {
					j = 6;
				}
			}
		}
		
	}
	
	public void setTextfield(Position position, String text) {
		tf[index].setText(text);
	}
	
	public String getTextfield() {
		return;
	}
	public void setColor(Position position, Color color){
		
	}

	

	
}
