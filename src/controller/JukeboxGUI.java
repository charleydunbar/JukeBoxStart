// Charles Dunbar, Mohammad Adlan Fauzi
package controller;

import view.JukeboxView;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Jukebox;

// This class is the GUI, however it uses a view to actually
// set up and maintain the display
@SuppressWarnings("serial")
public class JukeboxGUI extends JFrame {

	private JukeboxView view;
	private JPanel currentView;
	public static final int width = 360;
	public static final int height = 330;

	private Jukebox jukebox;

	// displays GUI
	public static void main(String[] args) {
		JukeboxGUI g = new JukeboxGUI();
		g.setVisible(true);
	}

	// constructor, sets sizes, properties, and view
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

	// sets view to requested view
	private void setViewTo(JPanel newView) {
		if (currentView != null)
			remove(currentView);

		currentView = newView;
		add(currentView);

		currentView.repaint();
		validate();
	}

}
