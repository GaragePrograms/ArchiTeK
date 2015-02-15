package io.github.garageprograms.architek.io;

import io.github.garageprograms.architek.datamodel.UserClass;
import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserFunction;
import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.datamodel.UserVariable;
import io.github.garageprograms.architek.plugins.PluginManager;

public class IOTest {

	public static void main(String[] args) {
		PluginManager.getInstance().loadAllPlugins("plugins");
		PluginManager.getInstance().installLanguage("python27_v00");
		UserProject proj = new UserProject("Test Project", "Test Description");
		
		UserFile sys = new UserFile("__builtin__", "System Builtins");
		UserClass any = new UserClass("Any", "Any datatype is accepted here.");
		sys.addClass(any);
		
		UserClass array = new UserClass("Array", "An array");
		UserFunction add = new UserFunction("append", "Append to the array");
		add.parameters.put("object", any);
		array.addFunction(add);
		sys.addClass(array);
		
		UserClass string = new UserClass("String", "A string, duh!");
		UserFunction len = new UserFunction("len", "Get length of string");
		string.addFunction(len);
		sys.addClass(string);
		
		proj.addFile(sys);
		
		UserFile file = new UserFile("test.py", "A Hello World program.");
		UserVariable var = new UserVariable("__name__", "Defines the name of the file");
		var.type=string;
		file.addVariable(var);
		proj.addFile(file);
		
		UserFunction hw = new UserFunction("__main__", "It says hello!");
		hw.parameters.put("args", array);
		file.addFunction(hw);
		
		SaveManager.saveProject(proj, "test.ark");
		
		UserProject recproj = SaveManager.loadProject("test.ark");
		System.out.println(recproj.files.get(0).encapsulatedClasses.get(1).encapsulatedFunctions.get(0).parameters.get("object").name);
	}

}
