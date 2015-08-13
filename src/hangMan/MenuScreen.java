package hangMan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class MenuScreen extends JPanel implements ActionListener{
	/**
	 * background
	 */
	private JPanel background;
	private JPanel mainPanel;
	/**
	 * first half of screen
	 */
	private JPanel imagePanel;
	private ImageIcon ic;
	private JLabel imageLabel;
	/**
	 * second half of screen
	 */
	private JPanel textPanel;
	private JLabel textLabel;
	
	private JPanel playPanel;
	private JButton playButton;
	private JPanel quitPanel;
	private JButton quitButton;
	/**
	 * 
	 */
	private HangManFrame hmf;
	private Clip clip;
	public MenuScreen(HangManFrame mainFrame){
		this.hmf=mainFrame;
		background=new PaintImage("images/chalkboardDefault.png");
		add(background);
		background.setPreferredSize(new Dimension(1024,672));
		buildMenuScreen();
		background.add(mainPanel);
		setVisible(true);
		
	}
	public void buildMenuScreen(){
		
		mainPanel=new JPanel();

		mainPanel.setLayout(new GridLayout(2,1));
		mainPanel.setOpaque(false);
		
		buildImage();
		mainPanel.add(imagePanel);
		buildTextPanel();
		mainPanel.add(textPanel);
		background.add(mainPanel);
		playSound("sounds/MenuScreenSound.wav");
		this.clip.loop(clip.LOOP_CONTINUOUSLY);
		}
	public void buildImage(){
		imagePanel=new JPanel();
		imagePanel.setOpaque(false);
		ic=new ImageIcon("images/hangManNoose.png");
		imageLabel=new JLabel(ic);
		imagePanel.add(imageLabel);
	}
	public void buildTextPanel(){
		textPanel=new JPanel();
		textPanel.setLayout(new GridLayout(3,1));
		
		textLabel=new JLabel("Welcome to Hang Man");
		textLabel.setFont(myFont(45));
		textLabel.setOpaque(false);
		textLabel.setForeground(Color.WHITE);
		textPanel.add(textLabel);
		
		buildPlayPanel();
		textPanel.add(playPanel);
		buildQuitPanel();
		textPanel.add(quitPanel);
		
		textPanel.setOpaque(false);
	}
	public void buildPlayPanel(){
		playPanel=new JPanel();
		playPanel.setOpaque(false);
		
		setPlayButton(new LetterButton("Play"));
		getPlayButton().addActionListener(this);
		getPlayButton().setOpaque(false);
		playPanel.add(getPlayButton());
	}
	public void buildQuitPanel(){
		quitPanel=new JPanel();
		quitPanel.setOpaque(false);
		
		quitButton=new LetterButton("Quit");
		quitButton.addActionListener(this);
		quitButton.setOpaque(false);
		quitPanel.add(quitButton);
		}
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
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(getPlayButton())){
			hmf.remove(this);
			hmf.addHangManGui();
			this.clip.stop();
			playSound("sounds/chalkSound.wav");
			
		}
		if(e.getSource().equals(quitButton)){
			System.exit(0);
		}
	}
	public JButton getPlayButton() {
		return playButton;
	}
	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}
	public void removeBackground(){
		remove(background);
		repaint();
	}
	public JButton getQuitButton(){
		return quitButton;
	}

public void playSound(String fileName){
	try{
	this.clip=AudioSystem.getClip();
	this.clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
	FloatControl gainControl = 
		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
	this.clip.start();
	}catch(Exception e){
		System.out.println("didnt work");
		}
}
}
