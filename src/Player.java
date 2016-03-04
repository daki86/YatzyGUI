
public class Player {
	private String name;
	private int totWins;
	private YatzyProtocol chart;
	
	public Player(String name){
		this.name = name;
		this.chart = new YatzyProtocol();
	}
	
	public String toString(){
		return this.name;
	}
	
	public void addAWin(){
		this.totWins++;
	}
	
	public int getTotWins(){
		return this.totWins;
	}
	
	public YatzyProtocol getPlayerYatzyProtocol(){
		return this.chart;
	}
	
	public String getName(){
		return this.name;
	}	
}
