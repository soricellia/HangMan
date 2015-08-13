
package hangMan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.*;
/**
 * This is where you play the game.
 * 
 * my private fields are indented for readability.
 * the indented fields are child's of the fields not indented.
 * 
 * Date:12/10/13
 * @author Tony
 *
 */
@SuppressWarnings("serial")//not sure what this does. eclipse yelled at me too do this.
public class HangManGui extends JPanel implements ActionListener{
	/**
	 * everything goes on here
	 */
	private JPanel background;
	/**
	 * everything but menuBar goes on here.
	 */
		private JPanel mainPanel;
		
	private Words words=new Words();
	private LetterArray letters=new LetterArray();
	/**
	 * menu bar
	 */
	private JMenuBar menuBar;
		private JMenu fileMenu;
			private JMenuItem newWord;
			private JMenuItem giveHint;
			private JMenuItem returnToMenuScreen;
			private JMenuItem quit;
	/**
	 * hangManPanel and components
	 */
	private JPanel hangManPanel;
		private JPanel hangManImagePanel;
			private JLabel imageLabel;
			private ImageIcon im;
		private JPanel currentWordPanel;
			private JTextArea currentWordLabel;
		private JPanel lettersUsedPanel;
		private JTextArea lettersUsedLabel;
	/**
	 * textPanel and components
	 */
	private JPanel textPanel;
		private JPanel lettersUnusedPanel;
		private LetterButton[] letterButton;
	
	private JTextArea blankLineLabel;
	private HangManFrame hmf;
	
	public HangManGui(HangManFrame hmf) {
		this.hmf=hmf;
		background=new PaintImage("images/chalkboard.jpg");
		background.setLayout(new BorderLayout());
		add(background);
		
		mainPanel=new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setPreferredSize(new Dimension(1024,672));
		buildMenuBar();
		
		buildHangManPanel();
		buildTextPanel();
		
		background.add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}
	/**
	 * menuBar is set to the top of the screen
	 */
	public void buildMenuBar(){

		/**
		 * menubar
		 */
		menuBar= new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setOpaque(false);
		
		fileMenu=new JMenu("OPTIONS");
		fileMenu.setFont(myFont(25));
		fileMenu.setForeground(Color.WHITE);
		fileMenu.setOpaque(false);
		
		newWord=new JMenuItem("New Word");
		newWord.addActionListener(new DropDownListener());
		fileMenu.add(newWord);
		
		giveHint=new JMenuItem("Hint");
		giveHint.addActionListener(new DropDownListener());
		fileMenu.add(giveHint);
		
		returnToMenuScreen=new JMenuItem("Return to Menu Screen");
		returnToMenuScreen.addActionListener(new DropDownListener());
		fileMenu.add(returnToMenuScreen);
		
		quit=new JMenuItem("Quit");
		quit.addActionListener(new DropDownListener());
		fileMenu.add(quit);
		
		menuBar.add(fileMenu);
		background.add(menuBar, BorderLayout.NORTH);
	}
	/**
	 * this panel contains the imagePanel and the currentWordPanel.
	 */
	public void buildHangManPanel(){
		hangManPanel=new JPanel();
		hangManPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		hangManPanel.setPreferredSize(new Dimension(400,672));
		hangManPanel.setOpaque(false);
		buildHangManImagePanel();
	
		buildCurrentWordPanel();
		mainPanel.add(hangManPanel);
	}
	/**
	 * the hangMan is drawn here
	 */
	public void buildHangManImagePanel(){
		
		hangManImagePanel=new JPanel();
		im=new ImageIcon("images/hangManNoose.png");
		imageLabel=new JLabel(im);
		imageLabel.setOpaque(false);
		hangManImagePanel.add(imageLabel);
		hangManImagePanel.setOpaque(false);
		hangManPanel.add(hangManImagePanel);
	}
	/**
	 * this is not really a label, but rather a JTextArea.
	 * I never changed the name of currentWordLabel...
	 */
	public void buildCurrentWordLabel(){
		currentWordLabel=new JTextArea();
		currentWordLabel.setText("Guess This Word:\n"+words.getCurrentWord());
		currentWordLabel.setFont(myFont(30));
		currentWordLabel.setOpaque(false);
		currentWordLabel.setEditable(false);
		currentWordLabel.setLineWrap(true);
		currentWordLabel.setForeground(Color.WHITE);
	}
	/**
	 * holds currentWordLabel.
	 */
	public void buildCurrentWordPanel(){
		currentWordPanel=new JPanel();
		currentWordPanel.setLayout(new GridLayout(2,1));
		
		buildBlankLine(1);
		currentWordPanel.add(blankLineLabel);
		buildCurrentWordLabel();
		currentWordPanel.add(currentWordLabel);
		currentWordPanel.setOpaque(false);
		
		hangManPanel.add(currentWordPanel);
	}
	/**
	 * holds all buttons, as well as the usedLetters.
	 */
	public void buildTextPanel(){
	textPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	textPanel.setPreferredSize(new Dimension(500,672));
	textPanel.setOpaque(false);
	buildLettersUnusedPanel();
	lettersUnusedPanel.setPreferredSize(new Dimension(300,250));
	buildLettersUsedPanel();
	mainPanel.add(textPanel);
	}
	/**
	 * holds the LetterButtons
	 */
	public void buildLettersUnusedPanel(){
		lettersUnusedPanel=new JPanel();
		lettersUnusedPanel.setOpaque(false);
		buildBlankLine(1);
		lettersUnusedPanel.add(blankLineLabel);
		buildLetterButton();
		
		textPanel.add(lettersUnusedPanel);
	}
	/**
	 * this panel puts all letters used onto it. 
	 * The letters are put onto a JTextArea.
	 * The lettersUsed come from the LetterArray class.
	 */
	public void buildLettersUsedPanel(){
		lettersUsedPanel=new JPanel();
		lettersUsedLabel=new JTextArea();
		/*********************************************
		 * The "\n" is there to position the letterUsedString into
		 * the place i wanted it. 
		 **************************************************/
		lettersUsedLabel.setText("\n"+letters.letterUsedString());
		lettersUsedLabel.setFont(myFont(40));
		lettersUsedLabel.setForeground(Color.WHITE);
		lettersUsedLabel.setOpaque(false);
		lettersUsedLabel.setEditable(false);
		lettersUsedLabel.setLineWrap(true);
		lettersUsedLabel.setWrapStyleWord(true);
		lettersUsedLabel.setBorder(null);
		lettersUsedLabel.setPreferredSize(new Dimension(150,300));
		lettersUsedPanel.add(lettersUsedLabel);
		lettersUsedPanel.setOpaque(false);
		textPanel.add(lettersUsedPanel);
	}

	/**
	 * i made this because i needed too put things in specific
	 * spots, and i'm not quite skilled enough yet too put things 
	 * where i want them using layoutMangers
	 * 
	 * @param numOfLines and int.. More lines, more spaces.
	 */
	public void buildBlankLine(int numOfLines){
		blankLineLabel=new JTextArea(numOfLines,30);
		blankLineLabel.setOpaque(false);
		blankLineLabel.setEditable(false);
	}
	/**
	 * creating an array of letterButtons and
	 * sticking them on letterUnusedPanel.
	 * added an actionListener(this) too each button.
	 */
	public void buildLetterButton(){
		
		letterButton=new LetterButton[letters.letterUnusedString().length()];	
		for(int i=0;i<letters.letterUnusedString().length();i++){
			
			letterButton[i]=new LetterButton();
			letterButton[i].setText(""+letters.letterUnusedString().charAt(i));
			//letterButton[i].setFont(myFont(35));
			//letterButton[i].setBackground(Color.black);
			//letterButton[i].setForeground(Color.WHITE);
			//letterButton[i].setOpaque(false);
			//letterButton[i].setBorder(null);
			letterButton[i].addActionListener(this);
			lettersUnusedPanel.add(letterButton[i]);
		}
	}
	/**
	 * I'm not really familiar with this. i learned how to do this off of google. 
	 * i understand a little of what i'm doing, but not all.
	 * @param fontSize the size of the font (int)
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
	/**
	 * I did my actionPerformed like this because i thought 
	 * it was cool when i first learned it. I still think its cool,
	 * but i wouldn't do it like this, if i had more than
	 * one action listener (which i do). I'm keeping it like this
	 * to show my learning process. 
	 * 
	 */
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() instanceof LetterButton){
				char ch=((LetterButton)e.getSource()).getText().charAt(0);
				
				words.isCharInGameWord(ch);
				letters.setLetterUsed(ch);
				currentWordLabel.setText("Guess This Word:\n"+words.getCurrentWord());
				lettersUsedLabel.setText("\n"+letters.letterUsedString());
				lettersUnusedPanel.remove((LetterButton)e.getSource());
				drawHangMan();
				playSound("sounds/chalkSound.wav");
				//lettersUnusedPanel.revalidate();
				//lettersUnusedPanel.repaint();
					
				if(words.winGame()){
					try {
						//Thread.sleep(3000);// i was trying to use this so i could see
						//the finished current word before the screen switches. 
						//this did not work.
						hmf.remove(this);
						hmf.addWinScreen(this.words.copy());
					} catch (Exception e1) {
						//e1.printStackTrace();
					}
					//System.exit(0);
				}
				if(words.getNumberOfBadGuesses()==7){
					hmf.remove(this);
					hmf.addLoseScreen(this.words.copy());
				}
			}
		}//end actionperformed
		public void drawHangMan(){
			/**
			 * i could have done this differently but this was a last min 'fix'
			 */
			ImageIcon[] ic=new ImageIcon[7];
			for(int i=0;i<ic.length;i++){
				ic[i]=new ImageIcon("images/hangMan"+i+".png");
			}
			
			switch(words.getNumberOfBadGuesses()){
			case 1:
				imageLabel.setIcon(ic[1]);
				break;
			case 2:
				imageLabel.setIcon(ic[2]);
				break;
			case 3:
				imageLabel.setIcon(ic[3]);
				break;
			case 4:
				imageLabel.setIcon(ic[4]);
				break;
			case 5:
				imageLabel.setIcon(ic[5]);
				break;
			case 6:
				imageLabel.setIcon(ic[6]);
				break;
			default:
					imageLabel.setIcon(ic[0]);
		
				
			}//end switch
			
		}
		/**
		 * I'm not really familiar with this. i learned how to do this off of google. 
		 * i understand a little of what i'm doing, but not all.
		 * @param fileName name of audio clip.
		 */
		public void playSound(String fileName){
			try{
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
			clip.start();
			}catch(Exception e){
				System.out.println(e);
				}
			
		}
		/**
		 * This nested class is for my fileMenu MenuBar
		 * @author Tony
		 *
		 */
		public class DropDownListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(e.getSource().equals(quit))	
					System.exit(0);
				if(e.getSource().equals(giveHint)){
					/**
					 * this method adds the letter into the
					 * first occurrence of '_'.
					 * 
					 */
					char ch= words.giveHint();
					int index=(ch-65);//using ascii table values here
					/*************************************************************
					 * Because this mimics a click its calling the actionPerformed
					 * for the LetterButton when clicked.
					 * 
					 * this was awesome ^_^
					 ***********************************************************/
					letterButton[index].doClick();
					
						}//end if
				if(e.getSource().equals(newWord)){
					/**
					 * resets buttonArray,
					 * currentWord and currentWordLabel,
					 * gameWord,
					 * letters,
					 * lettersUsedLabel,
					 * picture
					 */
					words.setGameWord();
					letters.setAllLettersUnused();
					currentWordLabel.setText(words.getCurrentWord());
					currentWordLabel.setText("Guess This Word:\n"+words.getCurrentWord());
					lettersUsedLabel.setText("\n"+letters.letterUsedString());
					lettersUnusedPanel.removeAll();
					lettersUnusedPanel.add(blankLineLabel);
					for(int i=0;i<letterButton.length;i++){
						lettersUnusedPanel.add(letterButton[i]);
						
					}
					drawHangMan();
				}
				/**
				 * brings back into menuScreen.
				 */
				if(e.getSource().equals(returnToMenuScreen)){
					hmf.remove(hmf.getHmg());
					hmf.addMenuScreen();
				}
					}//end action performed
		}//end class dropDownListener
	}//end hmg

