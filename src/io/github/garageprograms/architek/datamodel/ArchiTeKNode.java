package io.github.garageprograms.architek.datamodel;

public abstract class ArchiTeKNode {
	public String name = "";
	public String comment = "";
	public ArchiTeKNode(String name, String comment){
		this.name=name;
		this.comment=comment;
	}
	
	public void render(){};
	
	public boolean canApplyProperty(Property p){
		return false;
	}
}
