package io.github.garageprograms.architek.datamodel;

import java.awt.Graphics2D;

public abstract class ArchiTeKNode {
	public String name = "";
	public String comment = "";
	public int x, y;

	public ArchiTeKNode(String name, String comment){
		this.name=name;
		this.comment=comment;
		x = y = 0;
	}
	
	public void draw(Graphics2D g2d) { }

	public boolean canApplyProperty(Property p){
		return false;
	}
}
