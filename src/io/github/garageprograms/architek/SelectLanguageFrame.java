package io.github.garageprograms.architek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import io.github.garageprograms.architek.datamodel.UserProject;
import io.github.garageprograms.architek.plugins.PluginManager;
import io.github.garageprograms.architek.plugins.ProgrammingLanguage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
//
////Create the combo box, select item at index 4.
////Indices start at 0, so 4 specifies the pig.
//JComboBox petList = new JComboBox(petStrings);
//petList.setSelectedIndex(4);
class SelectLanguageFrame extends JFrame {
	JComboBox<String> combo;
	String[] mapping;
	public SelectLanguageFrame(){
		super("New Project -> Select Language");
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(new JLabel("Add .jar plugins to ~/.ArchiTeK/plugins or $ARCHITEK_INSTALL/plugins to allow new languages"));

		this.mapping=new String[PluginManager.getInstance().languages.size()];
		String[] choices = new String[PluginManager.getInstance().languages.size()];
		for (ProgrammingLanguage l : PluginManager.getInstance().languages){
			this.mapping[PluginManager.getInstance().languages.indexOf(l)]=l.getUniqueID();
			choices[PluginManager.getInstance().languages.indexOf(l)]=l.getName()+" ("+l.getUniqueID()+")";
		}
		this.combo = new JComboBox<String>(choices);
		
		JButton OKButton=new JButton("Select");
		final SelectLanguageFrame slf = this;
		OKButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		System.out.println("Mapping -> "+slf.mapping[1]);
	        		System.out.println("Value   -> "+slf.combo.getSelectedIndex());
	        		System.out.println("Result  -> "+slf.mapping[slf.combo.getSelectedIndex()]);
	        		PluginManager.getInstance().installLanguage(slf.mapping[slf.combo.getSelectedIndex()]);
	        		ArchiTeK.getInstance().panel.currentProject = new UserProject("Untitled "+(String)slf.combo.getSelectedItem()+" Project", "New project.");
	        		ArchiTeK.getInstance().panel.updateFrame();
	        		slf.dispose();
	        	}
		});
		p.add(combo);
		p.add(OKButton);
		add(p);
		pack();
		setVisible(true);
	}
}
