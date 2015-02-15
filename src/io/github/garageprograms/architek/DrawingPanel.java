/**
 * Copyright (C) 2015 GaragePrograms
 * License: GNU GPLv3
 */
package io.github.garageprograms.architek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import io.github.garageprograms.architek.datamodel.*;

/**
 * The panel on which the flow chart will be drawn.
 * 
 * @author Nicolás A. Ortega
 * @version 15.02.14
 */
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
	private UserProject currentProject;
	private Graphics2D g2d;

	private JFrame frame;

	private ArchiTeKNode node;

	public DrawingPanel(JFrame frame, String name) {
		this.frame = frame;
		setFocusable(true);
		currentProject = new UserProject(name, "Comments.");
		repaint();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, frame.getContentPane().getSize().width, frame.getContentPane().getSize().height);

		for(int i = 0; i < currentProject.files.size(); i++) {
			currentProject.files.get(i).draw(g2d);
		}
	}

	public void addFile(String name, String comment) {
		currentProject.files.add(new UserFile(name, comment));
		repaint();
	}

	public void mouseClicked(MouseEvent me) { }
	public void mouseEntered(MouseEvent me) { }
	public void mouseExited(MouseEvent me) { }
	public void mousePressed(MouseEvent me) {
		objectLoop:
		for(int i = 0; i < currentProject.files.size(); i++) {
			if(currentProject.files.get(i).rect.contains(me.getPoint())) {
				node = currentProject.files.get(i);
				break objectLoop;
			}
		}
	}
	public void mouseReleased(MouseEvent me) { node = null; }

	public void mouseDragged(MouseEvent me) {
		if(node == null) return;
		node.rect.setLocation(me.getPoint());
		repaint();
	}
	public void mouseMoved(MouseEvent me) { }

	// Getter methods:
	public UserProject getCurrentProject() { return currentProject; }
}
