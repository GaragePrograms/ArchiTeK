package io.github.garageprograms.architek.datamodel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class ArchiTeKNode {
	public String name = "";
	public String comment = "";
	public Rectangle rect;
	public boolean dragging;

	public ArchiTeKNode(String name, String comment){
		this.name=name;
		this.comment=comment;
		rect = new Rectangle(0, 0, 0, 0);
		dragging = false;
	}
	
	public void draw(Graphics2D g2d) { }

	public boolean canApplyProperty(Property p){
		return false;
	}
}
