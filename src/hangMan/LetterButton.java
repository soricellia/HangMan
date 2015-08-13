package hangMan;

import java.awt.*;
import java.io.File;

import javax.swing.JButton;
/**
 * Im using this as an easy way to call an edited version
 * of JButton... this is not really needed, and this class
 * does not bring in any extra fields. I made this BEFORE
 * we learned inheritance. I have not edited this since 
 * first made. I'm keeping this as-is too show my learning
 * process. 
 * @author Tony
 * Do not remember date created. 
 */
@SuppressWarnings("serial")// not sure what this does.
public class LetterButton extends JButton{
	
	public LetterButton(){
		
	setForeground(Color.WHITE);
	setBackground(Color.BLACK);
	setOpaque(false);
	setBorder(null);
	setFont(myFont(35));
	setPreferredSize(new Dimension(38,25));//this is useful for letters like 'i'
	setSelectedIcon(null);
	}
	public LetterButton(String text){
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setOpaque(false);
		setBorder(null);
		setFont(myFont(35));
		setSelectedIcon(null);
		this.setText(text);
	}
	/**
	 * this is copied and pasted from HMG class
	 * @param fontSize
	 * @return
	 */
	public Font myFont(int fontSize){
		
		Font font=null;
		try{
			font= Font.createFont(Font.TRUETYPE_FONT, new File("fonts/SquareChalk.ttf"));		
			font = font.deriveFont(Font.TRUETYPE_FONT, fontSize);
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		}catch(Exception e){}
		
		return font;
	}
	
}
