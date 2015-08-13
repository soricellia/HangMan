package hangMan;
//Author:Anthony Soricelli
//date Created:11/3/13
//edited:11/12/13
//Letter class used to keep track of letters used
public class Letter {
	private char letter;
	private boolean isUsed;
	public Letter(){
	letter = ' ';
	isUsed=false;
	}
	public Letter(char ch){
		letter=toUpperCase(ch);
	}
	public char getLetter(){
	return letter;
	}
	public void setLetter(char ch){
	letter=toUpperCase(ch);
	
	}
	public char toUpperCase(char ch){
		return Character.toUpperCase(ch);
	
	}
	public void setLetterUsed(){
		isUsed=true;
	}
	public void setLetterUnused(){
		isUsed=false;
	}
	public boolean isLetterUsed(){
		return isUsed;
	}

}
