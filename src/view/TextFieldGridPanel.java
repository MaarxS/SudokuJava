package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.SortedSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Position;


public class TextFieldGridPanel extends JPanel {
	
	private final int FIELDS_IN_3_GROUPS = 27;
	private final int GROUPS_IN_ROW = 3;
	private final int FIELDS_IN_ROW = 9;
	private final int TOTAL_FIELDS = 81;
	
	protected JTextField[] tf = new JTextField[TOTAL_FIELDS];
	protected JPanel[] panels = new JPanel[FIELDS_IN_ROW];
	private BorderedPanel[] fieldPanel = new BorderedPanel[TOTAL_FIELDS];
	public static final Color COLOR_RED = new Color(148, 46, 46);
	public static final Color COLOR_BACKGROUND = new Color(70, 73, 75);
	public static final Color COLOR_GREEN = new Color(11, 120, 11);

	
	public TextFieldGridPanel() {
		this.setLayout(new GridLayout(3, 3, 0, 0));
		
		for (int i = 0; i < GROUPS_IN_ROW * 3; i++) {
			panels[i] = new JPanel();
			panels[i].setBorder(BorderFactory.createEtchedBorder());
			panels[i].setLayout(new GridLayout(3, 3, 0, 0));
			this.add(panels[i]);

		}
		int i = 0;
		int j = 0;
		while (i < TOTAL_FIELDS) {
			Position pos = new Position(i % FIELDS_IN_ROW, i / FIELDS_IN_ROW);
			fieldPanel[i] = new BorderedPanel();
			fieldPanel[i].setLayout(new BorderLayout());
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
			fieldPanel[i].add(tf[i]);
			panels[j].add(fieldPanel[i]);
			tf[i].setColumns(GROUPS_IN_ROW);
	
			i++;
			/*Sorgt dafür, das die Felder in der Richtigen Reihenfolge in die 9 Gruppenpannels eingefügt werden (von links oben nach rechts unten) */
			if (i % GROUPS_IN_ROW == 0) {
				j++;
				if (j == GROUPS_IN_ROW && i < FIELDS_IN_3_GROUPS) {
					j = 0;
				} else if (j == GROUPS_IN_ROW * 2 && i < FIELDS_IN_3_GROUPS * 2) {
					j = GROUPS_IN_ROW;
				} else if (j == GROUPS_IN_ROW * 3 && i < FIELDS_IN_3_GROUPS * 3) {
					j = GROUPS_IN_ROW * 2;
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
	
	/**
	 * Paints a Border around the given Positions.
	 * @param positions the Positions to Paint a border around
	 * @param title to title of the border
	 */
	public void paintBorderAround(SortedSet<Position> positions, String title) {
		for (Position pos : positions) {
			Position top = new Position(pos.x, pos.y - 1);
			fieldPanel[pos.to1D()].setTopBorder(!positions.contains(top));
			Position bottom = new Position(pos.x, pos.y + 1);
			fieldPanel[pos.to1D()].setBottomBorder(!positions.contains(bottom));
			Position left = new Position(pos.x - 1, pos.y);
			fieldPanel[pos.to1D()].setLeftBorder(!positions.contains(left));
			Position right = new Position(pos.x + 1, pos.y);
			fieldPanel[pos.to1D()].setRightBorder(!positions.contains(right));
		}
		Position pos = positions.first();
		JLabel label = new JLabel(title);
		label.setFont(getFont().deriveFont(10f));
		fieldPanel[pos.to1D()].add(label, BorderLayout.NORTH);
		fieldPanel[pos.to1D()].revalidate();
	}
	
	/** Remove all group Borders which where added with {@link #paintBorderAround(SortedSet, String)}.*/
	public void clearBorders() {
		for (Position pos : Position.iterateAll()) {
			fieldPanel[pos.to1D()].setTopBorder(false);
			fieldPanel[pos.to1D()].setLeftBorder(false);
			fieldPanel[pos.to1D()].setRightBorder(false);
			fieldPanel[pos.to1D()].setBottomBorder(false);
			if (fieldPanel[pos.to1D()].getComponentCount() > 1) {
				fieldPanel[pos.to1D()].remove(1);
			}
			fieldPanel[pos.to1D()].revalidate();
			fieldPanel[pos.to1D()].repaint();
		}
	}
	
}
