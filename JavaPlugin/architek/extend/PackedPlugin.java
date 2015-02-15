package architek.extend;

import java.util.ArrayList;

import io.github.garageprograms.architek.datamodel.Property;
import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.plugins.DummyExporterImpl;
import io.github.garageprograms.architek.plugins.Exporter;
import io.github.garageprograms.architek.plugins.Plugin;
import io.github.garageprograms.architek.plugins.PluginManager;
import io.github.garageprograms.architek.plugins.ProgrammingLanguage;

public class PackedPlugin implements Plugin {
	public class JavaLanguage implements ProgrammingLanguage {
		private ArrayList<Property> props = new ArrayList<Property>();
		public JavaLanguage(){
			this.props.add(new Property("public", "Globally Accessible"));
		}
		public String getName() {return "Java 1.8";}
		public String getUniqueID() {return "java_1.8_01";}
		public ArrayList<Property> getProperties() {return props;}
		public Exporter getExporter() {return new DummyExporterImpl();}
		public void install(UserProject p) {}
	}
	public String getName() {return "Java 1.8 Plugin v01";}
	public String getUID() {return "jplugin_18_01";}
	public void install() {
		System.out.println("--->JavaPlugin Installing");
		PluginManager.getInstance().languages.add(new JavaLanguage());
	}

}
