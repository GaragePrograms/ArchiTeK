package io.github.garageprograms.architek.datamodel;

import javax.swing.JLabel;

public abstract class ArchiTeKNode extends JLabel {
	public String name = "";
	public String comment = "";

	public ArchiTeKNode(String name, String comment){
		super(name);
		this.name=name;
		this.comment=comment;
		setLocation(0, 10);
	}
	
	public boolean canApplyProperty(Property p){
		return false;
	}
}
