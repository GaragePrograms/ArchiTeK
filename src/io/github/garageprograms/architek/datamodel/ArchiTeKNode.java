package io.github.garageprograms.architek.datamodel;

public abstract class ArchiTeKNode {
	public String name = "";
	public String comment = "";
	public int x, y;
	public ArchiTeKNode(String name, String comment){
		this.name=name;
		this.comment=comment;
		x = 0;
		y = 0;
	}
	
	public void render(){};
	
	public boolean canApplyProperty(Property p){
		return false;
	}
}
