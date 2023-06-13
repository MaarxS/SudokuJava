package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;

public class EmptyField extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitleField;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnSolve;
	private JTextField[] tf = new JTextField[81];
	private JPanel[] panels = new JPanel[9];
	private JButton btnClearFields;

	public EmptyField() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblTitleField = new JLabel("Sudoku");
		lblTitleField.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleField.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitleField, BorderLayout.NORTH);

		panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 3, 0, 0));

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnClearFields = new JButton("Felder löschen");
		panel_1.add(btnClearFields);

		btnSolve = new JButton("Sudoku lösen");
		panel_1.add(btnSolve);

		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);

		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);

		for(int i = 0; i < 9; i++) {
			panels[i]  = new JPanel();
			panels[i].setBorder(BorderFactory.createEtchedBorder());
			panels[i].setLayout(new GridLayout(3, 3, 0, 0));
			panel.add(panels[i]);


		}
		int i = 0;
		int j = 0;
		while (i < 81) {
			tf[i]  = new JTextField();
			tf[i].setHorizontalAlignment(JTextField.CENTER);
			tf[i].setText(String.valueOf(i));
			panels[j].add(tf[i]);
			tf[i].setColumns(3);
			i++;
			if(i%3 == 0) {
				j++;
				if(j==3 && i < 27) {
					j= 0;
				}else if (j==6 && i < 54) {
					j = 3;
				}else if (j == 9 && i < 81){
					j=6;
				}
			}
		}
	}
	public JTextField[] getTextfield() {
		return tf;
	}
	public void setTextfield(int value, int index) {
		tf[index].setText(String.valueOf(value));;
	}
}



