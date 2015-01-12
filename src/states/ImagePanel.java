package states;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

@SuppressWarnings({"serial", "unused"})
public class ImagePanel extends JPanel {

    private Image image;

    public ImagePanel(Image image, GridBagConstraints c, GridBagLayout layout) {
	this.image = image;
	Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
	setPreferredSize(size);
	setMinimumSize(size);
	setMaximumSize(size);
	setSize(size);
	layout.setConstraints(this, c);
    }

    //Very important to get the image on screen
    public void paintComponent(Graphics g) {
	g.drawImage(image, 0, 0, null);
    }
}
