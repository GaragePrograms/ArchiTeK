package io.github.garageprograms.architek.plugins;

import io.github.garageprograms.architek.datamodel.Property;
import io.github.garageprograms.architek.datamodel.UserClass;
import io.github.garageprograms.architek.datamodel.UserFile;

import java.util.ArrayList;

public class ProgrammingLanguage {
	public String name = "";
	public String uniqueID = "";
	public ArrayList<Property> properties = new ArrayList<Property>();
	public Exporter exporter;
	public UserFile  builtins = new UserFile("__builtins__", "Built-in stuff");
	public UserClass voidType = new UserClass("Void", "Use this to represent a void function");
	public ProgrammingLanguage(){
		voidType.parent=builtins;
	}
}
