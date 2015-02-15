package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.plugins.PluginManager;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserVariable extends SerializableArchiTeKNode{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public UserClass type = null;
	public UserFile parent = null;
	
	public UserVariable(String name, String comment) {
		super(name, comment);
	}
	
	public UserVariable(Element elem, ArchiTeKNode directParent, UserProject project){
		super("","");
		this.defaultLoadFromXML(elem);
		NodeList propertiesNode = elem.getElementsByTagName("properties").item(0).getChildNodes();
		for (int temp = 0; temp < propertiesNode.getLength(); temp++) {
			Element propertyNode = (Element)propertiesNode.item(temp);
			this.properties.add(PluginManager.getInstance().getProperty(propertyNode.getAttribute("name")));
		}
		this.type=project.getClassByLookup(elem.getAttribute("type"));
		this.parent=(UserFile)directParent;
	}
	
	public boolean canApplyProperty(Property p){
		if (!p.canBeAppliedToVariable(this)){
			return false;
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
