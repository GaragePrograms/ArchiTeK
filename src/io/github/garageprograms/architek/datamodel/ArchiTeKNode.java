package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.ArchiTeK;
import io.github.garageprograms.architek.DrawingPanel;
import io.github.garageprograms.architek.OptionsFrame;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public abstract class ArchiTeKNode extends JLabel {
	public String name = "";
	public String comment = "";
	public JButton editButton = new JButton("Edit");
	public int x = 0;
	public int y = 0;
	

	public ArchiTeKNode(String name, String comment){
		super(name);
		this.name=name;
		this.comment=comment;
		setLocation(0, 10);
		final ArchiTeKNode abc=this;
		
		 editButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new OptionsFrame(abc);
	         }          
	      });
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.RED);
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
	}
	
	public void renderButton(Graphics2D g2d){
		editButton.paint(g2d);
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
		this.restoreLocation();
		this.addChildren(drawingPanel, parent);
	}
}
