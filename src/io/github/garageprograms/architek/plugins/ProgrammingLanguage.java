package io.github.garageprograms.architek.plugins;

import io.github.garageprograms.architek.datamodel.Property;
import io.github.garageprograms.architek.datamodel.UserProject;

import java.util.ArrayList;

public interface ProgrammingLanguage {
	public String getName();
	public String getUniqueID();
	public ArrayList<Property> getProperties();
	public Exporter getExporter();
	public void install(UserProject p);
}
