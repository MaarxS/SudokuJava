package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class BorderedPanel extends JPanel {
	
	private Color color = Color.WHITE;
	private boolean top = false;
	private boolean left = false;
	private boolean right = false;
	private boolean bottom = false;
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setTopBorder(boolean b) {
		top = b;
	}
	
	public void setLeftBorder(boolean b) {
		left = b;
	}
	
	public void setRightBorder(boolean b) {
		right = b;
	}
	
	public void setBottomBorder(boolean b) {
		bottom = b;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		if (top) g2.draw(new Line2D.Float(0, 0, getWidth(), 0));
		if (left) g2.draw(new Line2D.Float(0, 0, 0, getHeight()));
		if (right) g2.draw(new Line2D.Float(getWidth() + -1, 0, getWidth() + -1, getHeight()));
		if (bottom) g2.draw(new Line2D.Float(0, getHeight() - 1, getWidth(), getHeight() - 1));
	}
}
