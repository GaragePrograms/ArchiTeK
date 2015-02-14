package io.github.garageprograms.architek.datamodel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class SerializableArchiTeKNode extends ArchiTeKNode{
	public Element defaultSaveToXML(Document doc){
		Element node = doc.createElement("ArchiTeKNode");
		Element nameNode = doc.createElement("name");
		Element commentNode = doc.createElement("comment");
		nameNode.appendChild(doc.createTextNode(this.name));
		commentNode.appendChild(doc.createTextNode(this.comment));
		node.appendChild(nameNode);
		node.appendChild(commentNode);
		return node;
	}
	
	public void defaultLoadFromXML(Element elem){
		this.name = elem.getElementsByTagName("name").item(0).getTextContent();
		this.comment = elem.getElementsByTagName("comment").item(0).getTextContent();
	}
	
	public abstract Element saveToXML(Document doc);
	
	//public abstract void loadFromXML(Element elem, ArchiTeKNode directParent);
}
