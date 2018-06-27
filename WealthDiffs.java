
public class WealthDiffs {
	public String cityName;
	public String cityInitAlleg;
	public String cityCurrAlleg;
	public int wealthDiff;
	public String trader;
	
	public WealthDiffs[] calcWealthDiffs(City[] cities, int inpVall){
		int wd_idx = 0;
		
		WealthDiffs[] wealthDiffs = new WealthDiffs[4*cities.length];
		
		//check all cities, skip first file line as it contains the column headings
		for(int city_idx = 1; city_idx < (cities.length - 1); city_idx++){

			//cities[city_idx]  	= new City();
			/*
			wealthDiffs[wd_idx].setCityName(		cities[city_idx].cityName);
			wealthDiffs[wd_idx].setCityInitAlleg(	cities[city_idx].cityInitAlleg);
			wealthDiffs[wd_idx].setCityCurrAlleg(	cities[city_idx].cityCurrAlleg);
			*/
			
			for(int trader_idx = 0; trader_idx < 4; trader_idx++){			
				wealthDiffs[4*wd_idx + trader_idx] = new WealthDiffs();
				wealthDiffs[4*wd_idx + trader_idx].cityName 		= cities[city_idx].cityName;
				wealthDiffs[4*wd_idx + trader_idx].cityInitAlleg 	= cities[city_idx].cityInitAlleg;
				wealthDiffs[4*wd_idx + trader_idx].cityCurrAlleg 	= cities[city_idx].cityCurrAlleg;
				
				switch(trader_idx){
				case 0:
					/*
					wealthDiffs[wd_idx].setWealthDiff(cities[city_idx].CityWeaponTW - inpVall);
					wealthDiffs[wd_idx].setTrader("Weapons Trader");
					*/
					wealthDiffs[4*wd_idx + trader_idx].wealthDiff 	= Math.abs(cities[city_idx].CityWeaponTW - inpVall);
					wealthDiffs[4*wd_idx + trader_idx].trader 		= "Weapons Trader";
					break;
					
				case 1:
					/*
					wealthDiffs[wd_idx].setWealthDiff(cities[city_idx].CityArmorTW - inpVall);
					wealthDiffs[wd_idx].setTrader("Armor Trader");
					*/
					wealthDiffs[4*wd_idx + trader_idx].wealthDiff 	= Math.abs(cities[city_idx].CityArmorTW - inpVall);
					wealthDiffs[4*wd_idx + trader_idx].trader 		= "Armor Trader";
					break;
				case 2:
					/*
					wealthDiffs[wd_idx].setWealthDiff(cities[city_idx].CityHorseTW - inpVall);
					wealthDiffs[wd_idx].setTrader("Horse Trader");
					*/
					wealthDiffs[4*wd_idx + trader_idx].wealthDiff 	= Math.abs(cities[city_idx].CityHorseTW - inpVall);
					wealthDiffs[4*wd_idx + trader_idx].trader 		= "Horse Trader";
					break;
				case 3:
					/*
					wealthDiffs[wd_idx].setWealthDiff(cities[city_idx].cityGeneralTW - inpVall);
					wealthDiffs[wd_idx].setTrader("General goods Trader");
					*/
					wealthDiffs[4*wd_idx + trader_idx].wealthDiff 	= Math.abs(cities[city_idx].cityGeneralTW - inpVall);
					wealthDiffs[4*wd_idx + trader_idx].trader 		= "General goods Trader";
					break;
				}
				//System.out.println(4*wd_idx + trader_idx);		//DEBUG
			}
			wd_idx++;
		}
		
		return wealthDiffs;
	}
	
	public WealthDiffs[] calcMinWealthDiffs(WealthDiffs[] wealthDiffs, int inpVall, int traderAnalysis){
		//WealthDiffs[] minWealthDiffs = new WealthDiffs[traderAnalysis];
		WealthDiffs[] minWealthDiffs = new WealthDiffs[20*wealthDiffs.length];

		/*
		minWealthDiffs[0].setCityName(		wealthDiffs[0].cityName);
		minWealthDiffs[0].setCityInitAlleg(	wealthDiffs[0].cityInitAlleg);
		minWealthDiffs[0].setCityCurrAlleg(	wealthDiffs[0].cityCurrAlleg);
		minWealthDiffs[0].setWealthDiff(	wealthDiffs[0].wealthDiff);
		minWealthDiffs[0].setTrader(		wealthDiffs[0].trader);
		*/
		
		minWealthDiffs[0] = new WealthDiffs();

		int minWdIdx = 0;
		
		minWealthDiffs[0].cityName 		= wealthDiffs[0].cityName;
		minWealthDiffs[0].cityInitAlleg = wealthDiffs[0].cityInitAlleg;
		minWealthDiffs[0].cityCurrAlleg = wealthDiffs[0].cityCurrAlleg;
		minWealthDiffs[0].wealthDiff 	= wealthDiffs[0].wealthDiff;
		minWealthDiffs[0].trader 		= wealthDiffs[0].trader;
		
		int minWealthDiff = minWealthDiffs[0].wealthDiff;
		int maxWealthDiff = minWealthDiffs[0].wealthDiff;
		/*
		//each city has 4 traders, skip counting first file line (4) and last sum file line (another 4)
		for(int wd_idx = 1; wd_idx < wealthDiffs.length-8; wd_idx++){
			//System.out.println(wd_idx + " " + minWealthDiff);		//DEBUG
			if(	(wealthDiffs[wd_idx].wealthDiff < minWealthDiff)&&
				(minWdIdx < traderAnalysis-1 ) ){
				
				minWdIdx++;
				
				minWealthDiffs[minWdIdx] = new WealthDiffs();		
				minWealthDiffs[minWdIdx].cityName 		= wealthDiffs[wd_idx].cityName;
				minWealthDiffs[minWdIdx].cityInitAlleg 	= wealthDiffs[wd_idx].cityInitAlleg;
				minWealthDiffs[minWdIdx].cityCurrAlleg 	= wealthDiffs[wd_idx].cityCurrAlleg;
				minWealthDiffs[minWdIdx].wealthDiff 		= wealthDiffs[wd_idx].wealthDiff;
				minWealthDiffs[minWdIdx].trader 			= wealthDiffs[wd_idx].trader;
				
				minWealthDiff = minWealthDiffs[minWdIdx].wealthDiff;
				System.out.println( "Min wealth diff " + minWealthDiffs[minWdIdx].wealthDiff + " for " + 
									minWealthDiffs[minWdIdx].cityName + "/" + minWealthDiffs[minWdIdx].cityCurrAlleg + 
									minWealthDiffs[minWdIdx].trader);
			}
		}
		*/
		
		int maxIdx = 0;

		System.out.println("1: initial min index 0 = " + minWealthDiffs[0].wealthDiff);
		
		for(int wd_idx = 1; wd_idx < wealthDiffs.length-8; wd_idx++){	
			if (inpVall != wealthDiffs[wd_idx].wealthDiff) {
			
				System.out.println("\n 2: wd_idx = " + wd_idx + " Unsorted value : " + wealthDiffs[wd_idx].wealthDiff);		//DEBUG
				for(minWdIdx = 0; minWdIdx < wd_idx; minWdIdx++){
					System.out.println("3: if " + wealthDiffs[wd_idx].wealthDiff + " < " + minWealthDiffs[minWdIdx].wealthDiff);
					System.out.println("4: minWdIdx = " + minWdIdx);
					if(wealthDiffs[wd_idx].wealthDiff <= minWealthDiffs[minWdIdx].wealthDiff){
						//System.out.println("index : " + minWdIdx + " Sorted value : " +  minWealthDiffs[minWdIdx].wealthDiff);		//DEBUG
						
						//if (minWdIdx > 0) {
							
							//move all following indices 1 index lower
							//for(int idx = minWdIdx; idx < maxIdx+1; idx++){
							for(int idx = maxIdx ; idx > minWdIdx-1 ; idx--){
								
								System.out.println("5: idx = " + idx);
								
								//if (idx + 1 <= traderAnalysis) {
	
									//System.out.println("if index " + (idx + 1) + " <= " + traderAnalysis);
									if(idx == maxIdx){
										minWealthDiffs[idx+1] = new WealthDiffs();	
									}	
									
									minWealthDiffs[idx+1].cityName 		= minWealthDiffs[idx].cityName;
									minWealthDiffs[idx+1].cityInitAlleg = minWealthDiffs[idx].cityInitAlleg;
									minWealthDiffs[idx+1].cityCurrAlleg = minWealthDiffs[idx].cityCurrAlleg;
									minWealthDiffs[idx+1].wealthDiff 	= minWealthDiffs[idx].wealthDiff;
									minWealthDiffs[idx+1].trader 		= minWealthDiffs[idx].trader;
	
									System.out.println(	"6: move index " + idx + " (" + minWealthDiffs[idx].wealthDiff + ") " + 
														" -> " + (idx+1) + " (" + minWealthDiffs[idx+1].wealthDiff + ")");
									if (idx+1 > maxIdx) {
										maxIdx = idx+1;
										System.out.println("7: maxIdx = " + maxIdx);
									}
								//}else{
									//break;
								//}
							}
						//}
						
						minWealthDiffs[minWdIdx].cityName 		= wealthDiffs[wd_idx].cityName;
						minWealthDiffs[minWdIdx].cityInitAlleg 	= wealthDiffs[wd_idx].cityInitAlleg;
						minWealthDiffs[minWdIdx].cityCurrAlleg 	= wealthDiffs[wd_idx].cityCurrAlleg;
						minWealthDiffs[minWdIdx].wealthDiff 	= wealthDiffs[wd_idx].wealthDiff;
						minWealthDiffs[minWdIdx].trader 		= wealthDiffs[wd_idx].trader;
	
						System.out.println("8: minWdIdx = " + minWdIdx + " (" + minWealthDiffs[minWdIdx].wealthDiff + ") ");
						break;
						/*
					}else{
						
						minWealthDiffs[minWdIdx].cityName 		= wealthDiffs[wd_idx].cityName;
						minWealthDiffs[minWdIdx].cityInitAlleg 	= wealthDiffs[wd_idx].cityInitAlleg;
						minWealthDiffs[minWdIdx].cityCurrAlleg 	= wealthDiffs[wd_idx].cityCurrAlleg;
						minWealthDiffs[minWdIdx].wealthDiff 	= wealthDiffs[wd_idx].wealthDiff;
						minWealthDiffs[minWdIdx].trader 		= wealthDiffs[wd_idx].trader;
						*/
					}else{
						if (wealthDiffs[wd_idx].wealthDiff > maxWealthDiff) {
							System.out.println("9: maxIdx = " + maxIdx);
							maxIdx++;
							minWealthDiffs[maxIdx] = new WealthDiffs();
							
							minWealthDiffs[maxIdx].cityName 		= wealthDiffs[wd_idx].cityName;
							minWealthDiffs[maxIdx].cityInitAlleg = wealthDiffs[wd_idx].cityInitAlleg;
							minWealthDiffs[maxIdx].cityCurrAlleg = wealthDiffs[wd_idx].cityCurrAlleg;
							minWealthDiffs[maxIdx].wealthDiff 	= wealthDiffs[wd_idx].wealthDiff;
							minWealthDiffs[maxIdx].trader 		= wealthDiffs[wd_idx].trader;
							
							maxWealthDiff = wealthDiffs[wd_idx].wealthDiff;
							
							System.out.println("10: minWdIdx = " + maxIdx + " (" + minWealthDiffs[maxIdx].wealthDiff + ") ");
	
							System.out.println("11: maxIdx = " + maxIdx);
						}
						
					}
					
					
				}
				
				/*
				if(wealthDiffs[wd_idx].wealthDiff < wealthDiffs[wd_idx-1].wealthDiff){
	
	
				}
				*/
				
				if(wealthDiffs[wd_idx].wealthDiff < minWealthDiff){
					//minWealthDiffs[minWdIdx] = new WealthDiffs();		
					minWealthDiffs[0].cityName 		= wealthDiffs[wd_idx].cityName;
					minWealthDiffs[0].cityInitAlleg = wealthDiffs[wd_idx].cityInitAlleg;
					minWealthDiffs[0].cityCurrAlleg = wealthDiffs[wd_idx].cityCurrAlleg;
					minWealthDiffs[0].wealthDiff 	= wealthDiffs[wd_idx].wealthDiff;
					minWealthDiffs[0].trader 		= wealthDiffs[wd_idx].trader;
	
					System.out.println("12: minWdIdx = " + 0 + " ( new min value = " + minWealthDiffs[0].wealthDiff + ") \n");
					minWealthDiff = wealthDiffs[wd_idx].wealthDiff;
				}	
			}else{

				System.out.println("9: maxIdx = " + maxIdx);
				maxIdx++;
				minWealthDiffs[maxIdx] = new WealthDiffs();
				
				minWealthDiffs[maxIdx].cityName 		= wealthDiffs[wd_idx].cityName;
				minWealthDiffs[maxIdx].cityInitAlleg = wealthDiffs[wd_idx].cityInitAlleg;
				minWealthDiffs[maxIdx].cityCurrAlleg = wealthDiffs[wd_idx].cityCurrAlleg;
				minWealthDiffs[maxIdx].wealthDiff 	= wealthDiffs[wd_idx].wealthDiff;
				minWealthDiffs[maxIdx].trader 		= wealthDiffs[wd_idx].trader;
				
				maxWealthDiff = wealthDiffs[wd_idx].wealthDiff;
				
				System.out.println("10: minWdIdx = " + maxIdx + " (" + minWealthDiffs[maxIdx].wealthDiff + ") ");

				System.out.println("11: maxIdx = " + maxIdx);
			}
		}
		
		
		
		//++traderAnalysis
		return minWealthDiffs;
	}
	
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String newCityName) {
		this.cityName = newCityName;
	}
	
	public String getCityInitAlleg() {
		return this.cityInitAlleg;
	}

	public void setCityInitAlleg(String newCityInitAlleg) {
		this.cityInitAlleg = newCityInitAlleg;
	}

	public String getCityCurrAlleg() {
		return this.cityInitAlleg;
	}

	public void setCityCurrAlleg(String newCityCurrAlleg) {
		this.cityCurrAlleg = newCityCurrAlleg;
	}

	public int getWealthDiff() {
		return this.wealthDiff;
	}

	public void setWealthDiff(int newWealthDiff) {
		this.wealthDiff = newWealthDiff;
	}

	public String getTrader() {
		return this.trader;
	}

	public void setTrader(String newTrader) {
		this.trader = newTrader;
	}

	
	
}
