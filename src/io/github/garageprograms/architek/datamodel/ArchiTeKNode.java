package io.github.garageprograms.architek.datamodel;

import io.github.garageprograms.architek.OptionsFrame;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public abstract class ArchiTeKNode extends JLabel {
	public String name = "";
	public String comment = "";
	public JButton editButton = new JButton("Edit");

	public ArchiTeKNode(String name, String comment){
		super(name);
		this.name=name;
		this.comment=comment;
		setLocation(0, 10);
		final ArchiTeKNode abc=this;
		
		 editButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new OptionsFrame(abc);
	         }          
	      });
	}
	
	public void renderButton(Graphics2D g2d){
		editButton.paint(g2d);
	}
	
	public boolean canApplyProperty(Property p){
		return false;
	}
}
