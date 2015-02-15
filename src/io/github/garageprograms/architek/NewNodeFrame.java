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

public class NewNodeFrame extends JFrame {
	UserFile node;
	public JButton exitButton = new JButton("Cancel");
	private JButton classButton = new JButton("Class");
	private JButton functionButton = new JButton("Function");
	private JButton variableButton = new JButton("Variable");
	public NewNodeFrame(UserFile node){
		super("New Node");
		this.node=node;
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		final NewNodeFrame of = this;
		exitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		of.dispose();
        	}
	});
	
	
			classButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		of.node.addClass(new UserClass("New", ""));
		    		ArchiTeK.getInstance().panel.updateFrame();
		    		of.dispose();
		    	}
		});
		p.add(classButton);
		
		functionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				of.node.addFunction(new UserFunction("New", ""));
				ArchiTeK.getInstance().panel.updateFrame();
				of.dispose();
			}
		});
		p.add(functionButton);
		
		variableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				of.node.addVariable(new UserVariable("New", ""));
				ArchiTeK.getInstance().panel.updateFrame();
				of.dispose();
			}
		});
		p.add(variableButton);
		p.add(exitButton);
		add(p);
		pack();

		setVisible(true);
	}
}
