import java.util.Scanner;

public class YatzyProtocol {
	private int [] protocol;
	private int totDiceToss;
	private boolean [] combinationTicktOff;
	private Dice [] dice;
	private int totDice;
	private final int totprotocolRows = 18;
	private int totSumOfprotocol;
	
	public YatzyProtocol(){
		this.totDice = 5;
		this.totDiceToss = 3;
		this.totSumOfprotocol = 0;
		this.combinationTicktOff = new boolean[totprotocolRows];
		this.protocol = new int [totprotocolRows];
		this.dice = new Dice [totDice];
		
		for(int i = 0; i<totprotocolRows; i++){
			this.protocol[i] = 0;
			this.combinationTicktOff[i] = false;
		}
		
		for(int i = 0; i < totDice; i++){
			this.dice[i] = new Dice();
		}
	}
	
	public void rollDice(){
		for(int i = 0; i < totDice; i++){		
			if(this.dice[i].getRollDice())
				this.dice[i].rollDice();
		}
	}
	
	public int getDieNumber(int index){
		return this.dice[index].getRolledNr();
	}
	
	public boolean checkIfAllDiceAreLocked(){
		boolean allDiceAreLocked = false;
		
		for(Dice d: dice){
			if(d.getRollDice()){
				allDiceAreLocked = false;
				break;
			}else{
				allDiceAreLocked = true;
			}
		}
		
		return allDiceAreLocked;
	}
	
	public void keepDice(int index){	
		this.dice[index].setRollDice(false);
	}
	
	public void releaseDice(int index){	
		this.dice[index].setRollDice(true);
	}
	
	public boolean getKeepDice(int index){
		return this.dice[index].getRollDice();
	}
	
	public void resetKeepDice(){
		for(Dice d: dice){
			d.setRollDice(true);
		}
	}
	
	public void decreaseDieTosses(){
		this.totDiceToss--;
	}
	
	public int getTotDiceTossLeft(){
		return this.totDiceToss;
	}
	
	public int getTotDice(){
		return this.totDice;
	}
	
	public void setTotDice(int totDice){
		this.totDice = totDice;
	}
	
	public void resetTotDiceToss(){
		this.totDiceToss = 3;
	}
	
	public void resetProtocol(){
		for(int i=0; i<this.totprotocolRows; i++){
			this.protocol[i] = 0;
			this.combinationTicktOff[i] = false;
		}
	}
	
	public int getPointsFromProtocol(int index){
		return this.protocol[index-1];
	}
	
	public boolean getCombinationTicktOff(int index){
		return this.combinationTicktOff[index];
	}
	
	public boolean getIfAllCombosTicktOff(){
		boolean done = true;
		
		for(int i=0; i<this.totprotocolRows; i++){
			if(!this.combinationTicktOff[i] && i != 6 && i != 7 && i != 17){
				done = false;
				break;
			}
		}
		
		return done;
	}
	
	public int getBonus(){
		totSumOfprotocol = 0;
		
		for(int i = 0; i<6; i++){
			totSumOfprotocol += this.protocol[i];
		}
		
		if(totSumOfprotocol >= 63){
			totSumOfprotocol = 50;
		}else{
			totSumOfprotocol = 0;
		}
		
		return totSumOfprotocol;
	}
	
	public int getUperSum(){
		totSumOfprotocol = 0;
		
		for(int i = 0; i<6; i++){
			totSumOfprotocol += this.protocol[i];
		}
		
		return totSumOfprotocol;
	}
	
	public int getTotSumOfProtocol(){
		totSumOfprotocol = 0;
		for(int i = 0; i<this.totprotocolRows; i++){
			if( i !=7 && i != 17)
				totSumOfprotocol += this.protocol[i];
		}
		
		return totSumOfprotocol;
	}
	
	public void addPointsToProtocol(int choice){
		int totSum = 0;
		int counter = 0;
		int countIfFound = 0;
		int countTotPairs = 0;
		int sameNr = 0;
		int nr = 0;
		int savePairNr = 0;
		boolean found = false;
				
		if(!this.combinationTicktOff[choice-1]){	
			switch (choice){
				
				//Look for one's
				case 1:
					
					for(int i=0; i<this.totDice; i++){
						if(this.dice[i].getRolledNr() == 1){
							totSum++;
						}
					}
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
					
				//Look for two's
				case 2:
					
					for(int i=0; i<this.totDice; i++){
						if(this.dice[i].getRolledNr() == 2){
							totSum += 2;
						}
					}
				
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
					
				//Look for three's
				case 3:
					
					for(int i=0; i<this.totDice; i++){
						if(this.dice[i].getRolledNr() == 3){
							totSum += 3;
						}
					}
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
				
				//Look for four's		
				case 4:
					
					for(int i=0; i<this.totDice; i++){
						if(this.dice[i].getRolledNr() == 4){
							totSum += 4;
						}
					}
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
				
				//Look for five's	
				case 5:	
					
					for(int i=0; i<this.totDice; i++){
						if(this.dice[i].getRolledNr() == 5){
							totSum += 5;
						}
					}
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
				
				//Look for sixth's
				case 6:
					
					for(int i=0; i<this.totDice; i++){
						if(this.dice[i].getRolledNr() == 6){
							totSum += 6;
						}
					}
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
					
				//Look for one pair's
				case 9:					
				
					do{
						nr = this.dice[counter].getRolledNr();
						sameNr = 0;
						
						for(int i = 0; i < this.totDice; i++){
							if(nr == this.dice[i].getRolledNr()){
								sameNr++;
							}
						}
						
						if(sameNr >= 2 && savePairNr < nr){
							totSum = nr + nr;
							savePairNr = nr;
						}
						
						counter++;
					}while(counter != 3);
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
				
				//Look for two pair's	
				case 10:
					
					do{
						nr = this.dice[counter].getRolledNr();
						if(nr != savePairNr){
							for(int i = 0; i < this.totDice; i++){
								if(nr == this.dice[i].getRolledNr()){
									sameNr++;
									if(sameNr == 2){
										savePairNr = nr;
										countTotPairs++;
										totSum += 2 * nr;
										sameNr = 0;
										break;
									}
								}
							}
						}
							
						sameNr = 0;
						counter++;
					}while(counter != 4 && countTotPairs !=2);
					
					this.combinationTicktOff[choice-1] = true;
					
					if(countTotPairs == 2){
						this.protocol[choice-1] = totSum;
					}else
						this.protocol[choice-1] = 0;
					break;
					
				//Look for three of a kind 
				case 11:
					
					do{
						nr = this.dice[counter].getRolledNr();
						sameNr = 0;
						
						for(int i = 0; i < this.totDice; i++){
							if(nr == this.dice[i].getRolledNr()){
								sameNr++;
								if(sameNr == 3){
									totSum = 3 * nr;
									break;
								}
							}
						}						
						
						counter++;
					}while(counter != 4);
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
					
				//Look for four of a kind
				case 12:
					
					do{
						nr = this.dice[counter].getRolledNr();
						sameNr = 0;
						
						for(int i = 0; i < this.totDice; i++){
							if(nr == this.dice[i].getRolledNr()){
								sameNr++;
								if(sameNr == 4){
									totSum = 4 * nr;
									break;
								}
							}
						}						
						
						counter++;
					}while(counter != 2);
					
					this.combinationTicktOff[choice-1] = true;
					this.protocol[choice-1] = totSum;
					
					break;
				
				//Look for full house
				case 13:
					
					int threeOfAKindNr = 0;
					
					do{
						nr = this.dice[counter].getRolledNr();
						sameNr = 0;
						
						for(int i = 0; i < this.totDice; i++){
							if(nr == this.dice[i].getRolledNr()){
								sameNr++;
							}
						}
						
						if(sameNr == 2 && savePairNr != nr){
							savePairNr = nr;
							System.out.println("twoPair: "+nr);
						}else if(sameNr == 3 && threeOfAKindNr != nr){
							threeOfAKindNr = nr;
							System.out.println("threePair: "+nr);
						}
						
						counter++;
					}while(counter != 5);
					
					if(savePairNr != 0 && threeOfAKindNr != 0){
						//totSum = 2 * savePairNr + 3 * threeOfAKindNr;
						this.protocol[choice-1] = 28;
					}else
						this.protocol[choice-1] = totSum;
					
					this.combinationTicktOff[choice-1] = true;
					
					break;
				
				//Look for small straight
				case 14:
					
					nr = 1;
					
					do{		
						
						for(int i = 0; i < this.totDice; i++){
							if(nr == this.dice[i].getRolledNr()){
								countIfFound++;
								totSum += nr;
								break;
							}
						}
						
						nr++;
					}while(nr != 6);
					
					if(countIfFound == 5){
						this.protocol[choice-1] = 30;
					}
					
					this.combinationTicktOff[choice-1] = true;
					
					break;
				
				//Look for big straight
				case 15:
					
					nr = 2;
					
					do{						
						for(int i = 0; i < this.totDice; i++){
							if(nr == this.dice[i].getRolledNr()){
								countIfFound++;
								totSum += nr;
								break;
							}
						}
						
						nr++;
					}while(nr != 7);
					
					if(countIfFound == 5){
						this.protocol[choice-1] = 40;
					}
					
					this.combinationTicktOff[choice-1] = true;
					
					break;
				
				//Chance add all dice's
				case 16:
					
					for(int i = 0; i < this.totDice; i++){
						 totSum += this.dice[i].getRolledNr();	
					}
					
					this.protocol[choice-1] = totSum;
					this.combinationTicktOff[choice-1] = true;
					
					break;
				
				//Look for Yatzy	
				case 17:
					nr = this.dice[0].getRolledNr();
					
					for(int i = 0; i < this.totDice; i++){
						 if( nr != this.dice[i].getRolledNr()){
							 found = false;
							 break;
						 }else{
							 found = true;
						 }
					}
					
					if(found){
						this.protocol[choice-1] = 50;
					}else{
						this.protocol[choice-1] = 0;
					}
					
					this.combinationTicktOff[choice-1] = true;
					
					break;
			}
		}			
	}
	
	public void showDice(){
		for(int i=0; i<totDice; i++){
			System.out.print(this.dice[i].toString()+" ");
		}
		System.out.println();
	}
	
	public void showYatzyprotocol(){
		for(int i = 0; i < totprotocolRows; i++){
			if(this.protocol[i] != 0)
			System.out.println(this.protocol[i]); 
		}
	}
}
