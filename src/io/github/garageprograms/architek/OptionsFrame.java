package io.github.garageprograms.architek;

import io.github.garageprograms.architek.datamodel.ArchiTeKNode;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class OptionsFrame extends JFrame {
	ArchiTeKNode node;
	public JButton exitButton = new JButton("Exit");
	public JTextField nameBox = new JTextField();
	public JTextField commentBox = new JTextField();
	public OptionsFrame(ArchiTeKNode node){
		super("Node Options");
		this.node=node;
		nameBox.setText(node.name);
		nameBox.setLocation(0, 0);
		this.add(nameBox);
		commentBox.setText(node.comment);
		commentBox.setLocation(0, 50);
		this.add(commentBox);
		exitButton.setLocation(0,100);
		this.add(exitButton);
		this.pack();
		this.setVisible(true);
	}
}
