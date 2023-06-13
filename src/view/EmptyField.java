package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EmptyField extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitleField;
	private JPanel panel;
	private JTextField textField;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnSolve;
	private JTextField[] tf = new JTextField[81];
	private JPanel[] panels = new JPanel[9];
	private JButton btnClearFields;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmptyField frame = new EmptyField();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmptyField() {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());       
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
