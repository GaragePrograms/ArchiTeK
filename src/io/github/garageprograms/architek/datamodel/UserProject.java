package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

public class UserProject extends ArchiTeKNode {
	public ArrayList<UserFile> files = new ArrayList<UserFile>();
	
	public void save(String filename){};
	public void load(String filename){};
	
	public void addFile(UserFile f){
		this.files.add(f);
		f.parent=this;
	}
}
