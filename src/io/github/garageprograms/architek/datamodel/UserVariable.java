package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserVariable extends SerializableArchiTeKNode{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public UserClass type = null;
	public UserFile parent = null;
	
	public boolean canApplyProperty(Property propertyList){
		for (Property p : this.properties){
			if (!p.canBeAppliedToVariable(this)){
				return false;
			}
		}
		return true;
	}
	
	public Element saveToXML(Document doc){
		Element node = doc.createElement("UserVariable");
		node.appendChild(this.defaultSaveToXML(doc));
		node.setAttribute("type", this.type.getLookupID());
		Element propertiesNode = doc.createElement("properties");
		for (Property p : this.properties){
			propertiesNode.appendChild(p.saveToXML(doc));
		}
		node.appendChild(propertiesNode);
		return node;
	}
	
	public void render() {}
}
