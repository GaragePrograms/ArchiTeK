package io.github.garageprograms.architek.io;

import io.github.garageprograms.architek.datamodel.UserClass;
import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserFunction;
import io.github.garageprograms.architek.datamodel.UserProject;

public class IOTest {

	public static void main(String[] args) {
		UserProject proj = new UserProject("Test Project", "Test Description");
		
		UserFile file = new UserFile();
		file.name="test.py";
		file.comment="A Hello World program.";
		proj.addFile(file);
		
		UserClass array = new UserClass();
		array.name="Array";
		array.comment="An array";
		proj.addFile(array);
		
		UserFunction hw = new UserFunction();
		hw.name="__main__";
		hw.comment="It says hello!";
		hw.parameters.put("args", array);
		file.addFunction(hw);
		
		SaveManager.saveProject(proj, "test.ark");
	}

}
