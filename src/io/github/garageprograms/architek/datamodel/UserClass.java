package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserClass extends UserFile{
	public UserClass(String name, String comment) {
		super(name, comment);
	}

	public UserClass(Element classNode, ArchiTeKNode archiTeKNode, UserProject project) {
		super(classNode, archiTeKNode, project);
		this.parent=project;
		if (archiTeKNode!=project){
			this.parentFile=(UserFile)archiTeKNode;
		}
	}

	public ArrayList<Property> properties = new ArrayList<Property>();
	public ArrayList<UserClass> parentClasses = new ArrayList<UserClass>();
	public UserFile    parentFile = null;
	
	public boolean canApplyProperty(Property propertyList){
		for (Property p : this.properties){
			if (!p.canBeAppliedToClass(this)){
				return false;
			}
		}
		return true;
	}
	
	public void render() {}
	
	public Element saveToXML(Document doc){
		Element node = doc.createElement("UserClass");
		node.appendChild(super.saveToXML(doc));
		Element propertiesNode = doc.createElement("properties");
		for (Property p : this.properties){
			propertiesNode.appendChild(p.saveToXML(doc));
		}
		
		Element parentsNode = doc.createElement("parentClasses");
		for (UserClass c : this.parentClasses){
			parentsNode.appendChild(c.saveToXML(doc));
		}
		
		node.appendChild(propertiesNode);
		node.appendChild(parentsNode);
		
		return node;
	}
	
	public String getLookupID(){
		String filename = this.name;
		if (this.parentFile != null){
			filename = this.parentFile.name;
		}
		return this.parent.name+":"+filename+":"+this.name;
	}
}
