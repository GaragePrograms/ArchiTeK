package io.github.garageprograms.architek.datamodel;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UserFile extends SerializableArchiTeKNode{
	public ArrayList<UserClass> encapsulatedClasses = new ArrayList<UserClass>();
	public ArrayList<UserFunction> encapsulatedFunctions = new ArrayList<UserFunction>();
	public ArrayList<UserVariable> encapsulatedVariables = new ArrayList<UserVariable>();
	public UserProject parent = null;
	
	public void addClass(UserClass c){
		this.encapsulatedClasses.add(c);
		c.parent = this.parent;
		c.parentFile = this;
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
}
