package io.github.garageprograms.architek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.github.garageprograms.architek.datamodel.UserClass;
import io.github.garageprograms.architek.datamodel.UserFile;
import io.github.garageprograms.architek.datamodel.UserFunction;
import io.github.garageprograms.architek.datamodel.UserVariable;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TypeSelectFrame extends JFrame {
	UserVariable node;
	public JButton exitButton = new JButton("Set");
	public JButton getButton = new JButton("View");
	public JTextField nameBox = new JTextField(30);
	public TypeSelectFrame(UserVariable node){
		super("Set Type");
		if (node.type!=null){
			this.nameBox.setText(node.type.getLookupID());
		}else{
			this.nameBox.setText("");
		}
		this.node=node;
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		final TypeSelectFrame of = this;
		exitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		of.node.type=ArchiTeK.getInstance().panel.currentProject.getClassByLookup(of.nameBox.getText());
	        		of.dispose();
	        	}
		});
		getButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if (of.node.parent.parent.getClassByLookup(of.nameBox.getText())!=null){
	        			new OptionsFrame(of.node.parent.parent.getClassByLookup(of.nameBox.getText()), false);
	        		}
	        	}
		});
		p.add(nameBox);
		p.add(getButton);
		p.add(exitButton);
		add(p);
		pack();

		setVisible(true);
	}
}
