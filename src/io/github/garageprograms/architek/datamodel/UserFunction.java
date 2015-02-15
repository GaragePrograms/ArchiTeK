package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.plugins.PluginManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserFunction extends SerializableArchiTeKNode{
	public UserFunction(String name, String comment) {
		super(name, comment);
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.RED);
		g2d.draw(getBounds());
	}
	
	public UserFunction(Element elem, ArchiTeKNode directParent, UserProject project){
		super("","");
		this.defaultLoadFromXML(elem);
		this.returnType=project.getClassByLookup(elem.getAttribute("returnType"));
		
		NodeList propertiesNode = elem.getElementsByTagName("properties").item(0).getChildNodes();
		for (int temp = 0; temp < propertiesNode.getLength(); temp++) {
			Element propertyNode = (Element)propertiesNode.item(temp);
			this.properties.add(PluginManager.getInstance().getProperty(propertyNode.getAttribute("name")));
		}
		
		NodeList parametersNode = elem.getElementsByTagName("parameters").item(0).getChildNodes();
		for (int temp = 0; temp < parametersNode.getLength(); temp++) {
			Element parameterNode = (Element)parametersNode.item(temp);
			//System.out.println("Name => "+parameterNode.getAttribute("name"));
			//System.out.println("======> "+parameterNode.getAttribute("type"));
			//System.out.println("======> "+project.getClassByLookup(parameterNode.getAttribute("type")));
			this.parameters.put(parameterNode.getAttribute("name"), project.getClassByLookup(parameterNode.getAttribute("type")));
		}
	}

	public ArrayList<Property> properties = new ArrayList<Property>();
	public HashMap<String, UserClass> parameters = new HashMap<String, UserClass>();
	public UserClass returnType = null;
	public UserFile parent = null;
	
	public boolean canApplyProperty(Property p){
		if (!p.canBeAppliedToFunction(this)){
			return false;
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
