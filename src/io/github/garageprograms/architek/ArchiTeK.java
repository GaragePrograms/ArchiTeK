package io.github.garageprograms.architek;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class ArchiTeK {
	private JFrame frame;

	public ArchiTeK() {
		frame = new JFrame("ArchiTeK");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		frame.setSize((int)tk.getScreenSize().getWidth(), (int)tk.getScreenSize().getHeight());
		frame.setVisible(true);
	}

	public static void main(String[] args) { new ArchiTeK(); }
}
