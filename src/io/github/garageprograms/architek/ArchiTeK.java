/**
 * Copyright (C) 2015 GaragePrograms
 * License: GNU GPLv3
 */
package io.github.garageprograms.architek;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.*;

import io.github.garageprograms.architek.datamodel.UserFile;

/**
 * The main class of the program.
 * 
 * @author Nicolás A. Ortega
 * @version 15.02.14
 */
public class ArchiTeK {
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu, aboutMenu;

	private DrawingPanel panel;

	public ArchiTeK() {
		// Create frame with caption
		frame = new JFrame("ArchiTeK");
		// Close when 'X' button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set size to fullscreen.
		Toolkit tk = Toolkit.getDefaultToolkit();
		frame.setSize(800, 600);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// Create the drawing panel
		panel = new DrawingPanel(frame);
		frame.add(panel);

		// Create menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		/* CREATE MENUS */
		// Create file menu
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.add(newProject);
		fileMenu.addSeparator();
		fileMenu.add(quit);

		// Create edit menu
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		editMenu.add(addFile);

		// Create about menu
		aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		aboutMenu.add(copyrightPopup);

		frame.setVisible(true);
	}

	private Action newProject = new AbstractAction("New Project") {
		public void actionPerformed(ActionEvent ae) {
			panel.createProject("Untitled");
		}
	};

	private Action quit = new AbstractAction("Quit") {
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	};

	private Action addFile = new AbstractAction("Add File") {
		public void actionPerformed(ActionEvent ae) {
			panel.getCurrentProject().files.add(new UserFile("Untitled", "Untitled"));
		}
	};

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
