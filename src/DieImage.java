import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DieImage extends JButton implements Observer, ActionListener{
	
	static ImageIcon rollingDieImage = new ImageIcon(DieImage.class.getResource("rollingDie.gif"));
	static ImageIcon imageOne = new ImageIcon(DieImage.class.getResource("dieOne.png"));
	static ImageIcon imageTwo = new ImageIcon(DieImage.class.getResource("dieTwo.png"));
	static ImageIcon imageThree = new ImageIcon(DieImage.class.getResource("dieThree.png"));
	static ImageIcon imageFour = new ImageIcon(DieImage.class.getResource("dieFour.png"));
	static ImageIcon imageFive = new ImageIcon(DieImage.class.getResource("dieFive.png"));
	static ImageIcon imageSix = new ImageIcon(DieImage.class.getResource("dieSix.png"));
	
	static ImageIcon dieImages [] = {imageOne, imageTwo, imageThree, imageFour, imageFive, imageSix};
	
	public DieImage(){
		setFocusable(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setMaximumSize(new Dimension(70, 70));
		setIcon(dieImages[0]);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		b.setIcon(rollingDieImage);
	}
}
