package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserFunction extends SerializableArchiTeKNode{
	public UserFunction(String name, String comment) {
		super(name, comment);
	}

	public ArrayList<Property> properties = new ArrayList<Property>();
	public HashMap<String, UserClass> parameters = new HashMap<String, UserClass>();
	public UserClass returnType = null;
	public UserFile parent = null;
	
	public boolean canApplyProperty(Property propertyList){
		for (Property p : this.properties){
			if (!p.canBeAppliedToFunction(this)){
				return false;
			}
		}
		return true;
	}
	
	public Element saveToXML(Document doc){
		Element node = doc.createElement("UserFunction");
		node.appendChild(this.defaultSaveToXML(doc));
		if (this.returnType!=null){
			node.setAttribute("returnType", this.returnType.getLookupID());
		}else{
			node.setAttribute("returnType", "__void__");
		}
		Element propertiesNode = doc.createElement("properties");
		for (Property p : this.properties){
			propertiesNode.appendChild(p.saveToXML(doc));
		}
		node.appendChild(propertiesNode);
		Element parametersNode = doc.createElement("parameters");
		for (String pn : this.parameters.keySet()){
			Element te = doc.createElement("parameter");
			te.setAttribute("name", pn);
			te.setAttribute("type", this.parameters.get(pn).getLookupID());
			parametersNode.appendChild(te);
		}
		node.appendChild(parametersNode);
		return node;
	}
	
	public void render() {}
}
