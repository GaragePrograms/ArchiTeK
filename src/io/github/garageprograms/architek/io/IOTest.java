package io.github.garageprograms.architek.io;

import io.github.garageprograms.architek.datamodel.UserClass;
import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserFunction;
import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.plugins.PluginManager;

public class IOTest {

	public static void main(String[] args) {
		PluginManager.getInstance().loadAllPlugins();
		PluginManager.getInstance().installLanguage("python27_v00");
//		UserProject proj = new UserProject("Python Builtins", "The python modules built into the interpereter.");
//		
//		UserFile sys = new UserFile("__builtin__", "System Builtins");
//		UserClass any = new UserClass("Any", "Any datatype is accepted here.");
//		sys.addClass(any);
//		
//		UserClass array = new UserClass("list", "An array");
//		UserFunction add = new UserFunction("append", "Append to the array");
//		add.parameters.put("object", any);
//		array.addFunction(add);
//		sys.addClass(array);
//		
//		UserClass bool = new UserClass("Boolean", "Boolean. True/False");
//		sys.addClass(bool);
//		
//		UserClass string = new UserClass("String", "A string, duh!");
//		UserFunction endswith = new UserFunction("endswith", "Check to see if it ends with a certian string");
//		endswith.parameters.put("suffix", string);
//		endswith.returnType=bool;
//		string.addFunction(endswith);
//		sys.addClass(string);
//		
//		proj.addFile(sys);
		
		UserProject proj = new UserProject("Test", "test");
		proj.installLibrary("python27_00");
		System.out.println(proj.getClassByLookup("__builtin__:String").comment);
	}

}
