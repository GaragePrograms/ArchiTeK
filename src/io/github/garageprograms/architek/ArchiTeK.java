/**
 * Copyright (C) 2015 GaragePrograms
 * License: GNU GPLv3
 */
package io.github.garageprograms.architek;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.io.SaveManager;
import io.github.garageprograms.architek.plugins.PluginManager;

/**
 * The main class of the program.
 * 
 * @author Nicolás A. Ortega
 * @version 15.02.14
 */
public class ArchiTeK {
	public static boolean ready = false;
	private static ArchiTeK instance = null;
	public static ArchiTeK getInstance() {
		if(instance == null) {
			instance = new ArchiTeK();
			ready = true;
		}
		return instance;
	}
	   
	public JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu, aboutMenu;
	public String version = "ArchiTeK 0.1";

	public DrawingPanel panel;

	private ArchiTeK() {
		
		// Create frame with caption
		frame = new JFrame(version);
		// Close when 'X' button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set size to fullscreen.
		Toolkit tk = Toolkit.getDefaultToolkit();
		frame.setSize(800, 600);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// Create the drawing panel
		panel = new DrawingPanel(frame, new UserProject("Untitled New", "New project."));
		
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
		fileMenu.add(saveProject);
		fileMenu.add(loadProject);
		fileMenu.add(loadProjectTest);
		fileMenu.addSeparator();
		fileMenu.add(exportProject);
		fileMenu.addSeparator();
		fileMenu.add(quit);

		// Create edit menu
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		editMenu.add(addFile);
		editMenu.addSeparator();
		editMenu.add(addImportFile);
		editMenu.add(addImportLib);

		// Create about menu
		aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		aboutMenu.add(copyrightPopup);

		frame.setVisible(true);
	}

	private Action newProject = new AbstractAction("New Project") {
		public void actionPerformed(ActionEvent ae) {
			if (!PluginManager.getInstance().hasStarted) PluginManager.getInstance().loadAllPlugins();
//			PluginManager.getInstance().installLanguage("pyplugin_27_01");
//			panel.currentProject = new UserProject("Untitled New", "New project.");
//			
//			panel.updateFrame();
			new SelectLanguageFrame();
		}
	};
	
	private Action exportProject = new AbstractAction("Export Project") {
		public void actionPerformed(ActionEvent ae) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fc.showSaveDialog(ArchiTeK.getInstance().frame);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            PluginManager.getInstance().language.getExporter().exportToDir(file.getAbsolutePath(), panel.currentProject);
	        } else {
	            System.out.println("No file selected");
	        }
			
		}
	};
	
	private Action addImportFile = new AbstractAction("Link Project.ark") {
		public void actionPerformed(ActionEvent ae) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(ArchiTeK.getInstance().frame);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            try {
					ArchiTeK.getInstance().panel.currentProject.addImport(file.getAbsolutePath());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					 System.out.println("Bad file");
					e.printStackTrace();
				}
	        } else {
	            System.out.println("No file selected");
	        }
		}
	};
	
	private Action addImportLib = new AbstractAction("Link Project Lib") {
		public void actionPerformed(ActionEvent ae) {
			String s = (String)JOptionPane.showInputDialog(null, "NOTE: Must be on ArchiTeK Path to be imported.");
			ArchiTeK.getInstance().panel.currentProject.installLibrary(s);
		}
	};
	
	private Action saveProject = new AbstractAction("Save Project") {
		public void actionPerformed(ActionEvent ae) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(ArchiTeK.getInstance().frame);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            SaveManager.saveProject(ArchiTeK.getInstance().panel.currentProject, file.getAbsolutePath());;
	        } else {
	            System.out.println("No file selected");
	        }
		}
	};
	
	private Action loadProject = new AbstractAction("Load Project") {
		public void actionPerformed(ActionEvent ae) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(ArchiTeK.getInstance().frame);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            try {
					ArchiTeK.getInstance().panel.currentProject=SaveManager.loadProject(file.getAbsolutePath());
					ArchiTeK.getInstance().panel.updateFrame();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					 System.out.println("Bad file");
					e.printStackTrace();
				}
	        } else {
	            System.out.println("No file selected");
	        }
		}
	};
	
	private Action loadProjectTest = new AbstractAction("Load Test Project") {
		public void actionPerformed(ActionEvent ae) {
			try {
				ArchiTeK.getInstance().panel.currentProject=SaveManager.loadProject("python27_v00.ark");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArchiTeK.getInstance().panel.updateFrame();
		}
	};

	private Action quit = new AbstractAction("Quit") {
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	};

	private Action addFile = new AbstractAction("Add File") {
		public void actionPerformed(ActionEvent ae) {
			if(panel != null && panel.getCurrentProject() != null) {
				panel.addFile("Untitled", "Untitled");
			}
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
				"along with this program.  If not, see <http://www.gnu.org/licenses/>.\n\n"+
				"By Louis Goessling and Nicolás Ortega\n\n");
		}
	};

	private class Window {
		public Window(String title, String msg) {
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) { ArchiTeK.getInstance(); }
}
