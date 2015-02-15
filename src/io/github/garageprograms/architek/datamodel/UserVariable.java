package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.DrawingPanel;
import io.github.garageprograms.architek.NewNodeFrame;
import io.github.garageprograms.architek.TypeSelectFrame;
import io.github.garageprograms.architek.plugins.PluginManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserVariable extends SerializableArchiTeKNode{
	public ArrayList<Property> properties = new ArrayList<Property>();
	public UserClass type = null;
	public UserFile parent = null;
	JButton setTypeButton = new JButton("   Type");
	
	public UserVariable(String name, String comment) {
		super(name, comment);
		this.setupButton();
	}
	
	public UserVariable(Element elem, ArchiTeKNode directParent, UserProject project){
		super("","");
		this.setupButton();
		this.defaultLoadFromXML(elem);
		NodeList propertiesNode = elem.getElementsByTagName("properties").item(0).getChildNodes();
		for (int temp = 0; temp < propertiesNode.getLength(); temp++) {
			Element propertyNode = (Element)propertiesNode.item(temp);
			this.properties.add(PluginManager.getInstance().getProperty(propertyNode.getAttribute("name")));
		}
		this.type=project.getClassByLookup(elem.getAttribute("type"));
		this.parent=(UserFile)directParent;
	}
	
	private void setupButton(){
		setTypeButton.setBorder(null);
		 final UserVariable uv = this;
		 setTypeButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new TypeSelectFrame(uv);
	         }          
	      });
	}
	
	public void restoreLocation(){
		super.restoreLocation();
		this.setTypeButton.setLocation(x+this.getBounds().width+this.editButton.getBounds().width, y);
	}
	
	public void installToPanel(DrawingPanel drawingPanel, ArchiTeKNode parent){
		super.installToPanel(drawingPanel, parent);
		drawingPanel.add(this.setTypeButton);
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
