package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.DrawingPanel;
import io.github.garageprograms.architek.NewNodeFrame;
import io.github.garageprograms.architek.OptionsFrame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserFile extends SerializableArchiTeKNode {
	JButton createChildButton = new JButton("   Add");
	public UserFile(String name, String comment) {
		super(name, comment);
		this.setupButton();
	}
	
	private void setupButton(){
		createChildButton.setBorder(null);
		 final UserFile uf = this;
		createChildButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new NewNodeFrame(uf);
	         }          
	      });
	}
	
	public void restoreLocation(){
		super.restoreLocation();
		this.createChildButton.setLocation(x+this.getBounds().width+this.editButton.getBounds().width, y);
	}

	public void installToPanel(DrawingPanel drawingPanel, ArchiTeKNode parent){
		super.installToPanel(drawingPanel, parent);
		drawingPanel.add(this.createChildButton);
	}
	
	public void renderButton(Graphics2D g2d){
		super.renderButton(g2d);
		createChildButton.paint(g2d);
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
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
	
	public UserFile(Element elem, ArchiTeKNode directParent, UserProject project){
		super("","");
		this.setupButton();
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

	public void removeChildren() {
		for(int i = 0; i < encapsulatedClasses.size(); i++) {
			if(encapsulatedClasses.get(i).remove) {
				encapsulatedClasses.remove(i);
			} else {
				encapsulatedClasses.get(i).removeChildren();
			}
		}
		for(int i = 0; i < encapsulatedFunctions.size(); i++) {
			if(encapsulatedFunctions.get(i).remove) {
				encapsulatedFunctions.remove(i);
			}
		}
		for(int i = 0; i < encapsulatedVariables.size(); i++) {
			if(encapsulatedVariables.get(i).remove) {
				encapsulatedVariables.remove(i);
			}
		}
	}

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
		for (UserFunction c : this.encapsulatedFunctions){
			c.installToPanel(drawingPanel, this);
		}
		for (UserVariable c : this.encapsulatedVariables){
			c.installToPanel(drawingPanel, this);
		}
	}
	
	public void changeChildrensLocationBy(int x, int y) {
		for (UserClass c : this.encapsulatedClasses){
			c.changeLocationBy(x, y);
		}
		for (UserFunction c : this.encapsulatedFunctions){
			c.changeLocationBy(x, y);
		}
		for (UserVariable c : this.encapsulatedVariables){
			c.changeLocationBy(x, y);
		}
	}
	
	public boolean mousePressed(MouseEvent me){
		for (UserClass c : this.encapsulatedClasses){
			if (c.mousePressed(me))return true;
		}
		for (UserFunction c : this.encapsulatedFunctions){
			if (c.mousePressed(me)) return true;
		}
		for (UserVariable c : this.encapsulatedVariables){
			if (c.mousePressed(me)) return true;
		}
		return super.mousePressed(me);
	}
	
	public void mouseReleased(MouseEvent me){
		super.mouseReleased(me);
		for (UserClass c : this.encapsulatedClasses){
			c.mouseReleased(me);
		}
		for (UserFunction c : this.encapsulatedFunctions){
			c.mouseReleased(me);
		}
		for (UserVariable c : this.encapsulatedVariables){
			c.mouseReleased(me);
		}
	}
	
	public void mouseDragged(MouseEvent me){
		super.mouseDragged(me);
		for (UserClass c : this.encapsulatedClasses){
			c.mouseDragged(me);
		}
		for (UserFunction c : this.encapsulatedFunctions){
			c.mouseDragged(me);
		}
		for (UserVariable c : this.encapsulatedVariables){
			c.mouseDragged(me);
		}
	}
}
