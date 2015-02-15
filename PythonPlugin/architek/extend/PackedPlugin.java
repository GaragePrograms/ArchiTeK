package architek.extend;

import java.util.ArrayList;

import architek.extend.PythonExporter;
import io.github.garageprograms.architek.datamodel.Property;
import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.plugins.Exporter;
import io.github.garageprograms.architek.plugins.Plugin;
import io.github.garageprograms.architek.plugins.PluginManager;
import io.github.garageprograms.architek.plugins.ProgrammingLanguage;

public class PackedPlugin implements Plugin {
	public class PythonLanguage implements ProgrammingLanguage {
		private final PythonExporter exporter = new PythonExporter();
		public String getName() {return "Python 2.7";}
		public String getUniqueID() {return "python27_01";}
		public ArrayList<Property> getProperties() {return new ArrayList<Property>();}
		public Exporter getExporter() {return this.exporter;}
		public void install(UserProject p) {
			System.out.println("(PythonPlugin) Installing stdlib: python27_v00.ark");
			p.installLibrary("python27_v00");
			p.programmingLanguageUID=this.getUniqueID();
		}
	}
	public String getName() {return "Python 2.7 Plugin version 01";}
	public String getUID() {return "pyplugin_27_01";}
	public void install() {
		System.out.println("--->PythonPlugin Installing");
		PluginManager.getInstance().languages.add(new PythonLanguage());
	}

}
