package net.sites.seryux.utils;

import java.util.ArrayList;

public class AdsAutoTouch {

	private static AdsAutoTouch touchList; 
	private static ArrayList<Toucher> actions;
	
	
	public static AdsAutoTouch getAdsAutoTouch(){
		if(touchList==null){
			touchList = new AdsAutoTouch();
			actions = new ArrayList<Toucher>();
		}
		return touchList;
	}
	
	public void add(Toucher actionTouch){
		actions.add(actionTouch);
	}
	
	public void executeAll(){
		for(Toucher tou : actions){
			tou.execute();
		}
	}
	
}
