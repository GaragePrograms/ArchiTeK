package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserProject extends SerializableArchiTeKNode {
	public ArrayList<UserFile> files = new ArrayList<UserFile>();
	public ArrayList<UserProject> imports = new ArrayList<UserProject>();

	public UserProject(String name, String comment) {
		super(name, comment);
	}
	
	public UserProject(Element elem){
		super("","");
		this.defaultLoadFromXML(elem);
		NodeList filesNode = elem.getElementsByTagName("files").item(0).getChildNodes();
		for (int temp = 0; temp < filesNode.getLength(); temp++) {
			Element fileNode = (Element)filesNode.item(temp);
			this.addFile(new UserFile(fileNode, (ArchiTeKNode)this, this));
		}
		
		NodeList importsNode = elem.getElementsByTagName("imports").item(0).getChildNodes();
		for (int temp = 0; temp < importsNode.getLength(); temp++) {
			Element importNode = (Element)importsNode.item(temp);
			this.imports.add(new UserProject(importNode));
		}
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
	
	public UserClass getClassByLookup(String lookupID){
		for (UserFile f : this.files){
			if (f instanceof UserClass){
				UserClass c = (UserClass)f;
				if (c.getLookupID().equals(lookupID)){
					return c;
				}
			}else{
				for (UserClass c : f.encapsulatedClasses){
					c.getLookupID();
					if (c.getLookupID().equals(lookupID)){
						return c;
					}
				}
			}
		}
		return null;
	}
}
