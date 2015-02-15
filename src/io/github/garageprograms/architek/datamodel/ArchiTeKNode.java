package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.ArchiTeK;
import io.github.garageprograms.architek.DrawingPanel;
import io.github.garageprograms.architek.OptionsFrame;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public abstract class ArchiTeKNode extends JLabel {
	public String name = "";
	public String comment = "";
	public JButton editButton = new JButton("Edit");
	public JButton removeButton = new JButton("Remove");
	public int x = 0;
	public int y = 0;

	public ArchiTeKNode(String name, String comment){
		super(name);
		this.name=name;
		this.comment=comment;
		setLocation(0, 10);
		final ArchiTeKNode abc=this;
		editButton.setBorder(null);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OptionsFrame(abc, true);
			}
		});
		removeButton.setBorder(null);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// REMOVE ME
			}
		});
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.draw(getBounds());
	}
	
	public void changeLocationBy(int x, int y){
		this.changeChildrensLocationBy(x, y);
		this.x+=x;
		this.y+=y;
		this.restoreLocation();
	}
	
	public void changeChildrensLocationBy(int x2, int y2) {}

	public void restoreLocation(){
		this.setLocation(x, y);
		this.editButton.setLocation(x+this.getBounds().width, y);
		this.removeButton.setLocation(x+this.getBounds().width, y+this.editButton.getBounds().height);
	}
	
	public void renderButton(Graphics2D g2d){
		editButton.paint(g2d);
		removeButton.paint(g2d);
	}
	
	public boolean canApplyProperty(Property p){
		return false;
	}
	
	public void setNameAndRefresh(String name){
		this.name=name;
		this.setText(name);
	}
	
	public void addChildren(DrawingPanel drawingPanel, ArchiTeKNode parent) {}
	public void installToPanel(DrawingPanel drawingPanel, ArchiTeKNode parent){
		drawingPanel.add(this);
		drawingPanel.add(this.editButton);
		drawingPanel.add(this.removeButton);
		this.addChildren(drawingPanel, parent);
	}
	
	public boolean isBeingDragged = false;
	int lastMouseX=0;
	int lastMouseY=0;
	
	public boolean mousePressed(MouseEvent me) {
		if (this.getBounds().contains(me.getPoint())){
			this.isBeingDragged = true;
			return true;
		}
		return false;
	}

	public void mouseDragged(MouseEvent me) {
		
		if (this.isBeingDragged){
			this.changeLocationBy(me.getX()-this.lastMouseX, me.getY()-lastMouseY);
			
		}
		this.lastMouseX=me.getX();
		this.lastMouseY=me.getY();
	}
	
	public void mouseReleased(MouseEvent me) {
		this.isBeingDragged=false;
	}
}
