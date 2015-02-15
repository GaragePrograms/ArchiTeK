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
	public OptionsFrame(ArchiTeKNode node){
		super("Node Options");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.node=node;
		
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		
		mainPanel.setLayout(null);

		JLabel nameText = new JLabel("Name:");
		nameText.setBounds(10, 10, 100, 25);
		mainPanel.add(nameText);
		nameBox.setText(node.name);
		nameBox.setBounds(110, 10, 470, 25);
		mainPanel.add(nameBox);
		
		JLabel commentText = new JLabel("Comment:");
		commentText.setBounds(10, 40, 100, 25);
		mainPanel.add(commentText);
		commentBox.setText(node.comment);
		commentBox.setBounds(110, 40, 470, 250);
		mainPanel.add(commentBox);
		
		final OptionsFrame of = this;
		exitButton.setBounds(260, 325, 80, 25);
		exitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		of.node.setNameAndRefresh(of.nameBox.getText());
	        		ArchiTeK.getInstance().panel.updateFrame();;
	        		of.node.comment=of.commentBox.getText();
	        		of.dispose();
	        	}
		});
		mainPanel.add(exitButton);

		setVisible(true);
	}
}
