package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.DrawingPanel;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserFile extends SerializableArchiTeKNode {
	public UserFile(String name, String comment) {
		super(name, comment);
	}
	
	public UserFile(Element elem, ArchiTeKNode directParent, UserProject project){
		super("","");
		this.defaultLoadFromXML(elem);
		
		
		NodeList classesNode = elem.getElementsByTagName("encapsulatedClasses").item(0).getChildNodes();
		for (int temp = 0; temp < classesNode.getLength(); temp++) {
			Element classNode = (Element)classesNode.item(temp);
			this.addClass(new UserClass(classNode, (ArchiTeKNode)this, project));
		}
		
		NodeList functionsNode = elem.getElementsByTagName("encapsulatedFunctions").item(0).getChildNodes();
		for (int temp = 0; temp < functionsNode.getLength(); temp++) {
			Element functionNode = (Element)functionsNode.item(temp);
			this.addFunction(new UserFunction(functionNode, (ArchiTeKNode)this, project));
		}
		
		NodeList variablesNode = elem.getElementsByTagName("encapsulatedVariables").item(0).getChildNodes();
		for (int temp = 0; temp < variablesNode.getLength(); temp++) {
			Element variableNode = (Element)variablesNode.item(temp);
			this.addVariable(new UserVariable(variableNode, (ArchiTeKNode)this, project));
		}
		
		this.parent=project;
	}

	public ArrayList<UserClass> encapsulatedClasses = new ArrayList<UserClass>();
	public ArrayList<UserFunction> encapsulatedFunctions = new ArrayList<UserFunction>();
	public ArrayList<UserVariable> encapsulatedVariables = new ArrayList<UserVariable>();
	public UserProject parent = null;

	public void addClass(UserClass c){
		this.encapsulatedClasses.add(c);
		c.parent = this;
	}
	
	public void addFunction(UserFunction f){
		this.encapsulatedFunctions.add(f);
		f.parent = this;
	}
	
	public void addVariable(UserVariable v){
		this.encapsulatedVariables.add(v);
		v.parent = this;
	}
	
	public Element saveToXML(Document doc){
		Element node = doc.createElement("UserFile");
		node.appendChild(this.defaultSaveToXML(doc));
		Element classesNode = doc.createElement("encapsulatedClasses");
		for (UserClass c : this.encapsulatedClasses){
			classesNode.appendChild(c.saveToXML(doc));
		}
		Element functionsNode = doc.createElement("encapsulatedFunctions");
		for (UserFunction f : this.encapsulatedFunctions){
			functionsNode.appendChild(f.saveToXML(doc));
		}
		Element variablesNode = doc.createElement("encapsulatedVariables");
		for (UserVariable v : this.encapsulatedVariables){
			variablesNode.appendChild(v.saveToXML(doc));
		}
		node.appendChild(classesNode);
		node.appendChild(functionsNode);
		node.appendChild(variablesNode);
		return node;
	}

	public void addChildren(DrawingPanel drawingPanel, ArchiTeKNode parent) {
		for (UserClass c : this.encapsulatedClasses){
			c.installToPanel(drawingPanel, this);
		}
	}
}
