package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

public class UserClass extends UserFile{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public ArrayList<UserClass> parentClasses = new ArrayList<UserClass>();
	public UserFile parent = null;
	
	public boolean canApplyProperty(Property propertyList){
		for (Property p : this.properties){
			if (!p.canBeAppliedToClass(this)){
				return false;
			}
		}
		return true;
	}
	
	public void render() {}
}
