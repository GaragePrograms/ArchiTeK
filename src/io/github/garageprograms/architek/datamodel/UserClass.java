package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.plugins.PluginManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserClass extends UserFile{
	public UserClass(String name, String comment) {
		super(name, comment);
	}

	public UserClass(Element classNode, ArchiTeKNode archiTeKNode, UserProject project) {
		super(classNode, archiTeKNode, project);
		this.parent=(UserFile)archiTeKNode;
		
		NodeList propertiesNode = classNode.getElementsByTagName("properties").item(0).getChildNodes();
		for (int temp = 0; temp < propertiesNode.getLength(); temp++) {
			Element propertyNode = (Element)propertiesNode.item(temp);
			this.properties.add(PluginManager.getInstance().getProperty(propertyNode.getAttribute("name")));
		}
		
		NodeList parentsNode = classNode.getElementsByTagName("parentClasses").item(0).getChildNodes();
		for (int temp = 0; temp < parentsNode.getLength(); temp++) {
			Element parentNode = (Element)parentsNode.item(temp);
			this.parentClasses.add(project.getClassByLookup(parentNode.getAttribute("lookupID")));
		}
	}

	public ArrayList<Property> properties = new ArrayList<Property>();
	public ArrayList<UserClass> parentClasses = new ArrayList<UserClass>();
	public UserFile parent;
	
	public boolean canApplyProperty(Property p){
		if (!p.canBeAppliedToClass(this)){
			return false;
		}
		return true;
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		for (UserClass c : this.encapsulatedClasses){
			c.draw(g2d);
			g2d.setColor(Color.BLUE);
			g2d.drawLine(this.x+this.getBounds().width, this.y+this.getBounds().height, c.x, c.y);
		}
		for (UserFunction c : this.encapsulatedFunctions){
			c.draw(g2d);
			g2d.setColor(Color.RED);
			g2d.drawLine(this.x+this.getBounds().width, this.y+this.getBounds().height, c.x, c.y);
		}
		for (UserVariable c : this.encapsulatedVariables){
			c.draw(g2d);
			g2d.setColor(Color.GREEN);
			g2d.drawLine(this.x+this.getBounds().width, this.y+this.getBounds().height, c.x, c.y);
		}
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
			Element e = doc.createElement("parentClass");
			e.setAttribute("lookupID", c.getLookupID());
			parentsNode.appendChild(e);
		}
		
		node.appendChild(propertiesNode);
		node.appendChild(parentsNode);
		node.setAttribute("protoClassSourceID", this.parent.name);
		
		return node;
	}
	
	public String getLookupID(){
		return this.parent.name+":"+this.name;
	}
}
