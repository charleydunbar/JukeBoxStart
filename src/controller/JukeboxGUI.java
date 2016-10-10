package controller;

import view.JukeboxView;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Jukebox;

@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame {

	private JukeboxView view;
	private JPanel currentView;
	public static final int width = 360;
	public static final int height = 330;

	private Jukebox jukebox;

	public static void main(String[] args) {
		JukeboxGUI g = new JukeboxGUI();
		g.setVisible(true);
	}

	public JukeboxGUI() {
		setSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 40);
		this.setTitle("Jukebox");
		this.setResizable(false);

		jukebox = new Jukebox();
		view = new JukeboxView(jukebox);

		setViewTo(view);
	}

	private void setViewTo(JPanel newView) {
		if (currentView != null)
			remove(currentView);

		currentView = newView;
		add(currentView);

		currentView.repaint();
		validate();
	}

}
