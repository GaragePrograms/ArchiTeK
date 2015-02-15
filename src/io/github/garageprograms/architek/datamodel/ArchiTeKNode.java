package io.github.garageprograms.architek.datamodel;

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
	
	public int getAbsX(){
		return x;
	}
	
	public int getAbsY(){
		return y;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.draw(getBounds());
	}
	
	public void saveLocation(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void restoreLocation(){
		this.setLocation(this.getAbsX(), this.getAbsY());
		this.editButton.setLocation(x+this.getBounds().width, y);
	}
	
	public void renderButton(Graphics2D g2d){
		editButton.paint(g2d);
	}
	
	public boolean canApplyProperty(Property p){
		return false;
	}
	
	public void setNameAndRefresh(String name){
		Point p =  this.getLocation();
		this.name=name;
		this.setText(name);
		this.setLocation(p);
	}
	
	public void addChildren(DrawingPanel drawingPanel) {}
	public void installToPanel(DrawingPanel drawingPanel, ArchiTeKNode parent){
		drawingPanel.add(this);
		drawingPanel.add(this.editButton);
		this.restoreLocation();
		this.addChildren(drawingPanel);
	}
}
