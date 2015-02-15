/**
 * Copyright (C) 2015 GaragePrograms
 * License: GNU GPLv3
 */
package io.github.garageprograms.architek;

import java.awt.*;
import javax.swing.*;

import io.github.garageprograms.architek.datamodel.*;

/**
 * The panel on which the flow chart will be drawn.
 * 
 * @author Nicolás A. Ortega
 * @version 15.02.14
 */
public class DrawingPanel extends JPanel/* implements Runnable*/ {
	private UserProject currentProject;
	private Graphics2D g2d;

	private JFrame frame;

	public DrawingPanel(JFrame frame) {
		this.frame = frame;
		setFocusable(true);
		repaint();
	}

	public void createProject(String name) {
		currentProject = new UserProject(name, "Untitled");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, frame.getContentPane().getSize().width, frame.getContentPane().getSize().height);
	}

	// Getter methods:
	public UserProject getCurrentProject() { return currentProject; }
}
