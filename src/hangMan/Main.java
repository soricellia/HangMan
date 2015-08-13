package hangMan;
import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;
//AUTHOR: ATHONY SORICELLI
//Last edited: 11/12/13
//summary: Program builds objects letterArray and Words. Program then 
//		   uses them to display data onto the users screen. 
public class Main {

	/**
	 * This is to be used to test the classes
	 * you have written for the Hangman project.
	 */
	public static void main(String[] args) throws IOException {
		/**
		 * Building objects
		 **/

		Words wordList = new Words();
		LetterArray letters = new LetterArray();
		Scanner keybd = new Scanner(System.in);
		String guess="";
		boolean gameOver=false;
		char chGuessed = ' ';
		int difficulty=setDifficulty();
		int numOfGames=1;
		int numOfGuesses=0;
	/**
	 * This is the loop that displays the gameWord and CurrentGuess.
	 * Also, sets all letters unused at the end of loop.	
	**/
		for (int i =0; i<numOfGames; i++){
			System.out.println("Game word: "+wordList.getGameWord());
			System.out.println("Current guess:  "+ wordList.getCurrentWord());
			/**
			 * This loop plays the game
			**/
			while(!gameOver){
				System.out.println("Enter a guess:  ");
				guess = keybd.nextLine();
				guess=guess.toUpperCase();
				chGuessed = guess.charAt(0);
				letters.setLetterUsed(chGuessed);
				
				wordList.isCharInGameWord(chGuessed);
				
				/**
				 * setting number of guesses
				**/
				numOfGuesses=wordList.getNumberOfBadGuesses();
				
				/**
				 * Displaying currentWord, numberOfBadGuesses, and letterUsedString.
				**/
				System.out.println("Current Word:  "+wordList.getCurrentWord()+
						"\nNumber of guesses:  "+wordList.getNumberOfBadGuesses()+
						"\nLetters Used:  "+letters.letterUsedString());
				
				/**
				 * Checking for a win or loss
				**/
				if(wordList.winGame()){
					gameOver=true;
				}
				else if(numOfGuesses>=difficulty){
					wordList.loseGame();
					gameOver=true;
				}	
			}//end while
			/**
			 * resetting letters
			**/
			letters.setAllLettersUnused();
		}//end for
		keybd.close();
		System.exit(0);
	}
	
	public static int setDifficulty(){
		int difficulty=0;
		boolean valid=false;
		do{
		String temp=JOptionPane.showInputDialog("Which difficulty do you choose? (Number of guesses per word)"
				+ "\nenter a number between 1 and 10:");
		difficulty=Integer.parseInt(temp);
		if(difficulty>1||difficulty<10)
			valid=true;
		}while(!valid);
		return difficulty;
	}

}
