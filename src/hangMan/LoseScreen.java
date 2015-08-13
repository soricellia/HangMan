package hangMan;

import java.awt.event.*;
import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
/**
 * this Panel should only be displayed if the criteria for a lose
 * is met.
 * 
 * @author Tony
 *
 */
@SuppressWarnings("serial")//not sure what this does.
public class LoseScreen extends JPanel implements ActionListener{
	private JPanel background;
	private JPanel mainPanel;
	/**
	 * first half of screen
	 */
	private JPanel imagePanel;
	private JLabel imageLabel;
	private ImageIcon imageIcon;
	/**
	 * second half of screen
	 */
	private JPanel textPanel;
	private JTextArea textArea;
	private JPanel buttonPanel;
	private JPanel playPanel;
	private JButton playButton;
	private JPanel quitPanel;
	private JButton quitButton;
	
	private JPanel blankPanel;
	/**
	 * other classes called
	 */
	private HangManFrame hmf;
	private Words words;
	
	public LoseScreen(HangManFrame hmf, Words words){
		this.hmf=hmf;
		this.words=words.copy();
		/**
		 * background
		 */
		background=new PaintImage("images/chalkboardDefault.png");
		background.setLayout(new FlowLayout());
		add(background);
		background.setPreferredSize(new Dimension(1024,672));
		
		buildImagePanel();
		background.add(imagePanel);
		
		buildTextPanel();
		textPanel.setPreferredSize(new Dimension(700,100));
		background.add(textPanel);
		
		/**
		 * mainPanel
		 */
		mainPanel=new JPanel(new GridLayout(2,1));
		mainPanel.setOpaque(false);
		background.add(mainPanel);
		/**
		 * adding components
		 */
		//buildTextPanel();
		//mainPanel.add(textPanel);
		buildButtonPanel();
		blankPanel=new JPanel();
		blankPanel.setOpaque(false);
		mainPanel.add(blankPanel);
		mainPanel.add(buttonPanel);
		playSound("sounds/breakNeckSound.wav");
	}

	public void buildImagePanel(){
		imagePanel=new JPanel();
		imagePanel.setOpaque(false);
		imageIcon=new ImageIcon("images/deadHangMan.png");
		imageLabel=new JLabel(imageIcon);
		imageLabel.setOpaque(false);
		imagePanel.add(imageLabel);
	}
	public void buildTextPanel(){
		textPanel=new JPanel();
		textPanel.setOpaque(false);
		/**
		 * building text
		 */
		textArea=new JTextArea();
		textArea.setText("You lost!\n"+"The word was "+words.getGameWord()+"\nTry again.");
		//textArea.setText("Jiffy has died.\nThe bastard PeterPanPenuPutter strikes again!");
		textArea.setBackground(Color.WHITE);
		textArea.setBorder(null);
		textArea.setEditable(false);
		textArea.setFont(hmf.getFont(25));
		textArea.setOpaque(false);
		textArea.setWrapStyleWord(true);
		textArea.setForeground(Color.WHITE);
		
		textPanel.add(textArea);
		
	}
	
	public void buildButtonPanel(){
		/**
		 * building buttonPanel
		 */
		buttonPanel=new JPanel(new FlowLayout());
		buttonPanel.setOpaque(false);
		/**
		 * building playButton
		 */
		playPanel=new JPanel();

		playPanel.setPreferredSize(new Dimension(250,100));
		playPanel.setOpaque(false);
		
		playButton=new LetterButton("Play Again?");
		playButton.addActionListener(this);
		playPanel.add(playButton);
		
		/**
		 * building quitButton
		 */
		quitPanel=new JPanel();

		quitPanel.setPreferredSize(new Dimension(200,100));
		quitPanel.setOpaque(false);
		
		quitButton=new LetterButton("Quit");
		quitButton.addActionListener(this);
		quitPanel.add(quitButton);
		/**
		 * adding components
		 */
		buttonPanel.add(playPanel);
		buttonPanel.add(quitPanel);
	}

	public void playSound(String fileName){
		try{
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
		clip.start();
		}catch(Exception e){
			System.out.println(e);
			}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(playButton)){
			//System.out.println("works");
			hmf.remove(this);
			hmf.addHangManGui();
		}
		if(e.getSource().equals(quitButton)){
			System.exit(0);
		}
	}
	
	
}
