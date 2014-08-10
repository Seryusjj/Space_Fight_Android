package net.sites.seryux.utils;



public class GameState {
	private static GameState state = new GameState();

	public int score;
	public boolean gameOver ;
	public boolean ready ;


	private GameState() {
		resetGameState();
		
	}

	public static GameState getGameState() {
		return state == null ? new GameState() : state;

	}

	public void resetGameState(){
		score =0;
		gameOver =false;
		ready = false;
	}

}
