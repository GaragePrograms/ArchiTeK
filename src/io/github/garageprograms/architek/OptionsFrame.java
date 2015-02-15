package io.github.garageprograms.architek;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.github.garageprograms.architek.datamodel.ArchiTeKNode;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OptionsFrame extends JFrame {
	ArchiTeKNode node;
	public JButton exitButton = new JButton("Save");
	public JTextField nameBox = new JTextField(20);
	public JTextArea commentBox = new JTextArea(10, 75);
	boolean editable;
	public OptionsFrame(ArchiTeKNode node, boolean editable){
		super("Node Options");this.editable=editable;
		setSize(600, 400);
		this.node=node;
		
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		
		mainPanel.setLayout(null);

		JLabel nameText = new JLabel("Name:");
		nameText.setBounds(10, 10, 100, 25);
		mainPanel.add(nameText);
		nameBox.setText(node.name);
		nameBox.setEditable(editable);
		nameBox.setBounds(110, 10, 470, 25);
		mainPanel.add(nameBox);
		
		JLabel commentText = new JLabel("Comment:");
		commentText.setBounds(10, 40, 100, 25);
		mainPanel.add(commentText);
		commentBox.setText(node.comment);
		commentBox.setEditable(editable);
		commentBox.setBounds(110, 40, 470, 250);
		mainPanel.add(commentBox);
		
		final OptionsFrame of = this;
		exitButton.setBounds(260, 325, 80, 25);
		exitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if (of.editable){
		        		of.node.comment=of.commentBox.getText();
		        		of.node.setNameAndRefresh(of.nameBox.getText());
	        		}
				of.dispose();
	        	}
		});
		mainPanel.add(exitButton);

		setVisible(true);
	}
}
