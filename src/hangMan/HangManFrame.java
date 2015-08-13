package hangMan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
/**
 * this frame is not working the way i intended. i'm not really sure what's wrong.
 * there is an error message when the game is ran, but it still plays. weird.  
 * also, there is an annoying part on the top of the screen where the background is not drawn. 
 * 
 * i wasn't sure about using aggregation on the hangManFrame..
 * i thought about adding it but decided because of time constraints not to look into it.
 * the problem i was having was making a copy of the frame. i didn't want to make a new frame every time
 * i passed a copy into a different screen. the overall layout is a big problem i think.
 * 
 * i thought about using a card layout, but then i would have to keep the whole gui in the same class..
 * which was becoming a problem because my class already got so large. thinking back, i might be able
 * to come up with an alternative solution, but because of time contraints im leaving this as is. 
 * 
 * I was also looking to call all sounds from an external class, instead of having a playSound method in 
 * almost every class. i needed more time to work on this. 
 * 
 * some classes are not incredibly necessary(like paintImage and letterButton)
 * but i experimented a lot and i did a lot of this before we learned inheritance. 
 * if i was to write this program again, i would probably use a different approach. 
 * 
 * i've come up with a lot of personal projects i can do after i hand this in, to further my understand of 
 * the language. this was a great project that give me the creativity to come up with new projects.
 * before i started this project, i couldn't think of a project that i was capable of doing without
 * someone assigning me one.
 * 
 * date:12/10/13 
 * @author Tony
 *
 */
public class HangManFrame extends JFrame{
	private MenuScreen ms;
	private HangManGui hmg;
	private WinScreen ws;
	private LoseScreen ls;
	private JPanel mainScreen;
	public HangManFrame(){
		
		setTitle("HangMan");
		setSize(1024,672);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainScreen=new JPanel(new BorderLayout());
		add(mainScreen, BorderLayout.CENTER);
		
		addMenuScreen();
		setVisible(true);
	}
	public void addMenuScreen(){
		ms = new MenuScreen(this);
		mainScreen.add(ms);
		repaint();
		setVisible(true);
	}
	public void addWinScreen(Words words){
		ws=new WinScreen(this, words);
		mainScreen.add(ws);
		repaint();
		setVisible(true);
	}
	public void addLoseScreen(Words words){
		ls=new LoseScreen(this, words);
		mainScreen.add(ls);
		repaint();
		setVisible(true);		
		}
	
	public void addHangManGui(){
		hmg=new HangManGui(this);
		mainScreen.add(hmg);
		repaint();
		setVisible(true);
	}
	public void remove(JPanel screenName){
		mainScreen.remove(screenName);
	}
	/**
	 * i should have added more getters and setters.. also, aggregation is 
	 * really terrible in the project. i didn't realize how much i was tacking 
	 * on by adding these extras. i added all of this before i learned of 
	 * aggregation and i never thought to rewrite this code.
	 * @return returning new HangManGui
	 */
	public JPanel getHmg(){
		return hmg;
	}
	public Font getFont(int fontSize){
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
