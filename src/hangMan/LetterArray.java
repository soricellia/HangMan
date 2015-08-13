package hangMan;
//Author:Anthony Soricelli
//Date Created:11/4/13
//Edited: 11/12/13
//Summary: Class LetterArray uses the object Letter
//		   to store whether a letter is used or not
public class LetterArray {
private Letter[] letterList;

	public LetterArray(){
		/**
		 * Builds an array letterList containing the characters A-Z
		**/
		letterList = new Letter[26];
		for(int i=0;i<letterList.length;i++){
			letterList[i]=new Letter((char)('A'+i));
		}
	}
	public String letterUsedString(){
		/**
		 * Passes each instance of letterList thats
		 *  used into string alphabet 
		**/
		String alphabet="";//making sure no bad data gets into here
		for(int i=0;i<letterList.length;i++){
			if(letterList[i].isLetterUsed())//calls Letter class isLetterUsed method to test for unused letters
				alphabet+=letterList[i].getLetter();
		}
		return alphabet;
	}
	public String letterUnusedString(){
		/**
		 * Passes each instance of letterList thats
		 * unused into string alphabet.
		**/
		String alphabet="";//making sure no bad data gets into here
		for(int i=0;i<letterList.length;i++)
			if(!letterList[i].isLetterUsed()){//calls Letter class isLetterUsed method to test for used letters
				alphabet+=letterList[i].getLetter();
			}
		return alphabet;
	}
	public void setLetterUsed(char ch){
		/**
		 * Passes each instance of letterList thats
		 * used into string alphabet.
		**/
		int index=(ch-65);//letter A starts at ascii #65
		letterList[index].setLetterUsed();
	}
	public void setAllLettersUnused(){
		/**
		 * Sets all instances of letterList to unused. 
		**/
		for(int i=0;i<letterList.length;i++)
			letterList[i].setLetterUnused();
	}
}

