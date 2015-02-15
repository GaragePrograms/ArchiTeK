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
		this.node=node;
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel namepanel = new JPanel();
		namepanel.add(new JLabel("Name:"));
		nameBox.setText(node.name);
		//nameBox.setLocation(0, 0);
		namepanel.add(nameBox);
		
		mainPanel.add(namepanel);
		
		JPanel commentpanel = new JPanel();
		commentpanel.add(new JLabel("Comment:"));
		commentBox.setText(node.comment);
		//commentBox.setLocation(0, 50);
		commentpanel.add(commentBox);
		//exitButton.setLocation(0,100);
		mainPanel.add(commentpanel);
		
		mainPanel.add(exitButton);
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
		
		final OptionsFrame of = this;
		
		exitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		of.node.setNameAndRefresh(of.nameBox.getText());
	        		ArchiTeK.getInstance().panel.updateFrame();;
	        		of.node.comment=of.commentBox.getText();
	        		//ArchiTeK.getInstance().panel.updateFrame();
	        		of.dispose();
	        	}
		});
	}
}
