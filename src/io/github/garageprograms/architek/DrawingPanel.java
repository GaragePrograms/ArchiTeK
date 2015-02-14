/**
 * Copyright (C) 2015 GaragePrograms
 * License: GNU GPLv3
 */
package io.github.garageprograms.architek;

import javax.swing.*;

import io.github.garageprograms.architek.datamodel.*;

/**
 * The panel on which the flow chart will be drawn.
 * 
 * @author Nicolás A. Ortega
 * @version 15.02.14
 */
public class DrawingPanel extends JPanel {
	private UserProject currentProject;

	public DrawingPanel() { setFocusable(true); }

	public void createProject(String name) {
		currentProject = new UserProject(name, "Untitled");
	}
}
