package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserProject extends SerializableArchiTeKNode {
	public ArrayList<UserFile> files = new ArrayList<UserFile>();
	public ArrayList<UserProject> imports = new ArrayList<UserProject>();

	public UserProject(String name, String comment) {
		this.name = name;
		this.comment = comment;
	}
	
	public String referencePath = ""; //TODO: Setup
	
	public void save(String filename){};
	public void load(String filename){};
	
	public void addFile(UserFile f){
		this.files.add(f);
		f.parent=this;
	}
	
	public Element saveReferenceToXML(Document doc){
		Element node = doc.createElement("UserProjectReference");
		node.setAttribute("referencePath", this.referencePath);
		return node;
	}

	public Element saveToXML(Document doc) {
		Element node = doc.createElement("UserProject");
		node.appendChild(this.defaultSaveToXML(doc));
		Element filesNode = doc.createElement("files");
		for (UserFile f : this.files){
			filesNode.appendChild(f.saveToXML(doc));
		}
		node.appendChild(filesNode);
		Element importsNode = doc.createElement("imports");
		for (UserProject p : this.imports){
			importsNode.appendChild(p.saveReferenceToXML(doc));
		}
		node.appendChild(importsNode);
		
		return node;
	}
}
