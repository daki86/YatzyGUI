import java.util.Observable;
import java.util.Random;

public class Dice extends Observable{
	private int totDiceSides;
	private int rolledNr;
	private boolean rollDice; 
	
	public Dice(){
		this.totDiceSides = 6;
		this.rollDice = true;
	}
	
	public Dice(int totSides){
		this.totDiceSides = totSides;
	}
	
	public int rollDice(){
		Random diceNr = new Random();
		this.rolledNr = (diceNr.nextInt(this.totDiceSides)+1);
		
		return this.rolledNr;
	}
	
	public int getRolledNr(){
		return this.rolledNr;
	}
	
	public boolean getRollDice(){
		return this.rollDice;
	}
	
	public void setRollDice(boolean rollDice){
		this.rollDice = rollDice;
	}
	
	public String toString(){
		String showNr = ""+this.rolledNr;
		
		return showNr;
	}
}
