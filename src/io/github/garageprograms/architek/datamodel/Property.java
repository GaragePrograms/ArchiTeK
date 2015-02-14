package io.github.garageprograms.architek.datamodel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Property extends SerializableArchiTeKNode{
	String fullyQualifiedID = "DefaultProperty";
	
	public boolean canBeAppliedToClass(UserClass c){
		return false;
	}
	
	public boolean canBeAppliedToFunction(UserFunction c){
		return false;
	}
	
	public boolean canBeAppliedToVariable(UserVariable c){
		return false;
	}

	public Element saveToXML(Document doc) {
		Element node = doc.createElement("property");
		node.setAttribute("fullyQualifiedID", this.fullyQualifiedID);
		return node;
	}
}
