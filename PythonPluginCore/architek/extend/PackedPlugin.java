package architek.extend;

import io.github.garageprograms.architek.plugins.Exporter;
import io.github.garageprograms.architek.plugins.Plugin;
import io.github.garageprograms.architek.plugins.PluginManager;
import io.github.garageprograms.architek.plugins.ProgrammingLanguage;
import io.github.garageprograms.architek.datamodel.Property;

import java.util.ArrayList;

public class PackedPlugin extends Plugin {

	public class PythonLanguage extends ProgrammingLanguage {
		public String name = "Python";
		public String uniqueID = "python27_v00";
		public ArrayList<Property> properties = new ArrayList<Property>();
		public Exporter exporter;
	}

	public String name = "PythonPlugin v0.1";
	public String description = "Python code managment core.";
	public void install(){
		System.out.println("--> PythonPlugin installing...");
		PluginManager.getInstance().languages.add(new PythonLanguage());
		PluginManager.getInstance().plugins.add(this);
		PluginManager.getInstance().requredLibraries.clear();
		PluginManager.getInstance().requredLibraries.add("python27_v00");
	}
}
