/**
 * Copyright (C) 2015 GaragePrograms
 */
package io.github.garageprograms.architek;

import java.awt.Toolkit;
import javax.swing.*;

/**
 * The main class of the program.
 * 
 * @author Nicolás A. Ortega
 * @version 15.02.14
 */
public class ArchiTeK {
	private JFrame frame;
	private JMenuBar menuBar;

	public ArchiTeK() {
		// Create frame with caption
		frame = new JFrame("ArchiTeK");
		// Close when 'X' button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set size to fullscreen.
		Toolkit tk = Toolkit.getDefaultToolkit();
		frame.setSize((int)tk.getScreenSize().getWidth(), (int)tk.getScreenSize().getHeight());

		// Setup menu
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		frame.setVisible(true);
	}

	public static void main(String[] args) { new ArchiTeK(); }
}
