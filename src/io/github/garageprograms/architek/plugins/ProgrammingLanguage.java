package io.github.garageprograms.architek.plugins;

import io.github.garageprograms.architek.datamodel.Property;

import java.util.ArrayList;

public class ProgrammingLanguage {
	public String name = "";
	public String uniqueID = "";
	public ArrayList<Property> properties = new ArrayList<Property>();
	public Exporter exporter;
}
