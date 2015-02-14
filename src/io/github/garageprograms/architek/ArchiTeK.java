/**
 * Copyright (C) 2015 GaragePrograms
 * License: GNU GPLv3
 */
package io.github.garageprograms.architek;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
	private JMenu fileMenu, aboutMenu;

	public ArchiTeK() {
		// Create frame with caption
		frame = new JFrame("ArchiTeK");
		// Close when 'X' button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set size to fullscreen.
		Toolkit tk = Toolkit.getDefaultToolkit();
		frame.setSize((int)tk.getScreenSize().getWidth(), (int)tk.getScreenSize().getHeight());

		/* CREATE MENUS */
		// Create menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// Create menus
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu createNew = new JMenu("Create new...");
		fileMenu.add(createNew);

		aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		aboutMenu.add(copyrightPopup);

		frame.setVisible(true);
	}

	private Action copyrightPopup = new AbstractAction("Copyright") {
		public void actionPerformed(ActionEvent ae) {
			new Window("Copyright",
				"Program structure flow chart software.\n" +
				"Copyright (C) 2015 GaragePrograms\n\n" +
			        "This program is free software: you can redistribute it and/or modify\n" +
				"it under the terms of the GNU General Public License as published by\n" +
				"the Free Software Foundation, either version 3 of the License, or\n" +
				"(at your option) any later version.\n\n" +
				"This program is distributed in the hope that it will be useful,\n" +
				"but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
				"MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the\n" +
				"GNU General Public License for more details.\n\n" +
				"You should have received a copy of the GNU General Public License\n" +
				"along with this program.  If not, see <http://www.gnu.org/licenses/>.\n\n");
		}
	};

	private class Window {
		public Window(String title, String msg) {
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) { new ArchiTeK(); }
}
