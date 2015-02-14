package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class UserFunction extends ArchiTeKNode{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public HashMap<String, UserClass> parameters = new HashMap<String, UserClass>();
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
