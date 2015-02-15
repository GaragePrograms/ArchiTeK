package io.github.garageprograms.architek.plugins;

import io.github.garageprograms.architek.datamodel.Property;
import io.github.garageprograms.architek.datamodel.UserProject;

import java.util.ArrayList;

public class DummyProgrammingLanguageImpl implements ProgrammingLanguage {
	public String getName() {return "Unset";}
	public String getUniqueID() {return "_default";}
	public ArrayList<Property> getProperties() {return new ArrayList<Property>();}
	public Exporter getExporter() {return new DummyExporterImpl();}
	public void install(UserProject p) {System.out.println("Dummy installing...");}

}
