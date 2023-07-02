package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Position;


public class TextFieldGridPanel extends JPanel {
	
	protected JTextField[] tf = new JTextField[81];
	protected JPanel[] panels = new JPanel[9];
	public static final Color COLOR_RED = new Color(148, 46, 46);
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);
	public static final Color COLOR_GREEN = new Color(11, 120, 11);
	
	public TextFieldGridPanel() {
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
			Position pos = new Position(i % 9, i / 9);
			tf[i] = new JTextField();
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			tf[i].addMouseListener(new MouseAdapter(){		
				public void mousePressed(MouseEvent e){
					if (getColor(pos).equals(COLOR_RED) || getColor(pos).equals(COLOR_GREEN)) {
						setColor(pos, COLOR_BACKGROUND);
					}
				}
			});
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
	
	public void setText(Position position, String text) {
		tf[position.to1D()].setText(text);
	}
	
	public String getText(Position position) {
		return tf[position.to1D()].getText();
	}
	
	public void setColor(Position position, Color color){
		tf[position.to1D()].setBackground(color);
	}
	
	public Color getColor(Position position){
		return tf[position.to1D()].getBackground();
	}
	public boolean isEditable(Position position) {
		return tf[position.to1D()].isEditable();
	}
	
	public void setEditable(Position position, Boolean value) {
		tf[position.to1D()].setEditable(value);
	}

	public void addTextFieldMouseListener(Position pos, MouseListener listener) {
		tf[pos.to1D()].addMouseListener(listener);
	}
	
}
