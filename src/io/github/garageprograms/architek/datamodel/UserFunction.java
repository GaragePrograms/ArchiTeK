package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

public class UserFunction extends ArchiTeKNode{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public UserClass returnType = null;
	public UserFile parent = null;
	
	public boolean canApplyProperty(Property propertyList){
		for (Property p : this.properties){
			if (!p.canBeAppliedToFunction(this)){
				return false;
			}
		}
		return true;
	}
	
	public void render() {}
}
