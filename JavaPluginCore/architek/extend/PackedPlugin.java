package architek.extend;

import io.github.garageprograms.architek.plugins.Exporter;
import io.github.garageprograms.architek.plugins.Plugin;
import io.github.garageprograms.architek.plugins.PluginManager;
import io.github.garageprograms.architek.plugins.ProgrammingLanguage;
import io.github.garageprograms.architek.datamodel.Property;

import java.util.ArrayList;

public class PackedPlugin extends Plugin {

	public class JavaLanguage extends ProgrammingLanguage {
		public String name = "Java";
		public String uniqueID = "java_1.8_25_v00";
		public ArrayList<Property> properties = new ArrayList<Property>();
		public Exporter exporter;
		
		public JavaLanguage(){
			this.properties.add(new Property("public", "Accessible from everywhere"));
		}
	}

	public String name = "JavaPlugin v0.1";
	public String description = "Java code managment core.";
	public void install(){
		System.out.println("--> JavaPlugin installing...");
		PluginManager.getInstance().languages.add(new JavaLanguage());
		PluginManager.getInstance().plugins.add(this);
	}
}
