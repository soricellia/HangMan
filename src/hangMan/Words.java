package hangMan;
import java.util.*;
import java.io.*;
/**
 * DATE MODIFIED:12/10/2013 -- giveHint()
 * Summary: Class Words builds and array from file words.txt.
 * 			methods can be used to generate a game word, as 
 * 			well as a current word for said game word.
 * 			This class also keeps track of number of guesses.
 * 
 * EDIT on 12/11/13
 * I realized i could have controlled my LetterArray class from my Words class. 
 * That would make resetting the game much easier. 
 * I really didn't have enough time to change all of my code to work for this fix.
 * I'm leaving as-is for now. 
 * @author Tony
 *
 */
public class Words {
private ArrayList<String> words;
private String gameWord;
private String currentWord;
private Random randNum;
private int numberOfGuesses;		
private LetterArray la;	

public Words(){
	populateArray();
	setGameWord();
}
public Words(Words w){
	//this.words.addAll(w.words);//i couldnt figure this out
	this.gameWord=w.gameWord;
	this.currentWord=w.currentWord;
	this.randNum=w.randNum;
	this.numberOfGuesses=w.numberOfGuesses;
}
	public void populateArray(){
		/**
		 * Adding the file that contains the words list
		 * to an array called ArrayList
		**/
		String line="";
		words=new ArrayList<String>();
		//la=new LetterArray();
		try{
		File wordsFile=new File("words.txt");
		Scanner fileScan=new Scanner(wordsFile);
		while(fileScan.hasNext()){
			line=fileScan.nextLine();
			words.add(line);
		}	
		fileScan.close();
		}catch(IOException e){}
		
	}
	
	public void setGameWord(){
		/**
		 * Initializing gameWord and currentWord
		**/
		randNum=new Random();
		gameWord=words.get(randNum.nextInt(words.size()));
		numberOfGuesses=0;
		setBlanksInCurrentWord();
		//la.setAllLettersUnused(); //this would make resetting the game easier from the gui.
	}
	public void setBlanksInCurrentWord(){
		/**
		 * setting string currentWord to "_" for each character
		 * in string gameWord
		**/
		currentWord="";//making sure no bad data
		for(int i=0;i<gameWord.length();i++){
			currentWord+="_ ";//puts an "_" in place for each letter in the word		
		}
		numberOfGuesses=0;
	}
	
	public boolean isCharInGameWord(char ch){
		
		/**
		Looking to see if the guessed char is in gameWord
		**/
		
		int letterSpotter=gameWord.indexOf(ch);
		if(letterSpotter==-1){
			numberOfGuesses+=1;
			return false;
		}else{
			/**
			Inserting letter into currentWord string. 
			**/
			for(int i=0;i<gameWord.length();i++){
				if(gameWord.charAt(i)==ch){
					insertLetter(i, ch);
				}	
		}
	return true;
	}
}
	public void insertLetter(int index,char ch){
		final int spaces = 2;//used to handle the extra space in between each letter.
		/**
		Adding guessed letter ch to string currentWord.
		**/
		String temp=currentWord.substring(0,index*spaces);
		temp+=ch+currentWord.substring((index*spaces+1),currentWord.length());
		currentWord=temp;
		
	}
	public String getGameWord(){
		return gameWord;
	}
	public String getCurrentWord(){
		return currentWord;
	}
	public int getNumberOfBadGuesses(){
		return numberOfGuesses;
	}
	public void resetGameWords(){
		setGameWord();
	}
	public boolean winGame(){
		final int SPACES=2;
		/**
		* Taking the blank spaces out of currentWord.
		**/
		String winningWord="";
		for(int i=0;i<gameWord.length();i++){
			winningWord+=""+currentWord.charAt(i*SPACES);
		}
		/**
		 * testing for a win
		 **/
		if(winningWord.equals(gameWord)){
			return true;
		}
		return false;
		
	}
	public void loseGame(){
		System.out.println("You Lost!");
		setGameWord();
	}
	/**
	 * this method gives a hint in the first occurrence of '_' in currentWord.
	 */
	public char giveHint(){
	/**
	 * variable ch was made into a string because i had problems 
	 * initializing a char to null. i compensated by casting 
	 * String ch into a char.
	 */
		String ch="";
		double indexCh=0;
		for(int i=0;i<currentWord.length();i++){
			if(currentWord.charAt(i)=='_'){
				indexCh=i/2;
				ch=""+gameWord.charAt((int)indexCh);
				isCharInGameWord(ch.charAt(0));
				//couldn't figure out how to make this a while loop so..
				i=currentWord.length();//...i cheated :) 
			}//end if
		}//end for
		numberOfGuesses++;
		return ch.charAt(0);
	}//end method
	public Words copy(){
		return new Words(this);
	}
}



