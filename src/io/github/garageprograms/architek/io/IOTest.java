package io.github.garageprograms.architek.io;

import io.github.garageprograms.architek.datamodel.UserClass;
import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserFunction;
import io.github.garageprograms.architek.datamodel.UserProject;

public class IOTest {

	public static void main(String[] args) {
		UserProject proj = new UserProject("Test Project", "Test Description");
		
		UserFile file = new UserFile("test.py", "A Hello World program.");
		proj.addFile(file);
		
		UserClass array = new UserClass("Array", "An array");
		proj.addFile(array);
		
		UserFunction hw = new UserFunction("__main__", "It says hello!");
		hw.parameters.put("args", array);
		file.addFunction(hw);
		
		SaveManager.saveProject(proj, "test.ark");
		
		UserProject recproj = SaveManager.loadProject("test.ark");
		System.out.println(recproj.name);
		System.out.println(recproj.comment);
	}

}
