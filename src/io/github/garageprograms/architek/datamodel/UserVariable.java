package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

public class UserVariable extends ArchiTeKNode{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public UserClass type = null;
	public UserFile parent = null;
	
	public boolean canApplyProperty(Property propertyList){
		for (Property p : this.properties){
			if (!p.canBeAppliedToVariable(this)){
				return false;
			}
		}
		return true;
	}
	
	public void render() {}
}
