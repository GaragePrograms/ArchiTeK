package io.github.garageprograms.architek.datamodel;

public class ArchiTeKNode {
	public String name = "";
	public String comment = "";
	public void render(){};
	public boolean canApplyProperty(Property p){
		return false;
	}
}
