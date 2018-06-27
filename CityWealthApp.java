import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CityWealthApp  {

	public static void main(String[] args) throws FileNotFoundException {
		int maxCityLines = 0;	//getMaxFileLines(pathName);
		
		City city = new City();
		
		final String cityWealthFile = "C:\\Users\\freelancer\\workspace\\ACOK_CityWealth\\ACOK_CityWealth.csv";

		maxCityLines = city.getMaxCities(cityWealthFile);

		City[] cities = new City[maxCityLines];
		
		for(int city_idx = 0; city_idx < maxCityLines-1; city_idx++){
			cities[city_idx] = new City();
		}
		
		cities = city.showTradeCities();
		
		// Get city name display tabs
		int[] nameCharNbr = new int[maxCityLines];
		
		for(int city_idx = 1; city_idx < maxCityLines-1; city_idx++){
			nameCharNbr[city_idx] = cities[city_idx].cityName.length();
		}		
				
		int maxNameCharNbr = Arrays.stream(nameCharNbr).max().getAsInt();
		int maxNameTabsNbr = maxNameCharNbr/8 + 1;
		
		// Get initial city allegiance display tabs
		int[] cityInitAllegCharNbr = new int[maxCityLines];

		for(int city_idx = 1; city_idx < maxCityLines-1; city_idx++){
			cityInitAllegCharNbr[city_idx] = cities[city_idx].cityInitAlleg.length();
		}	
		
		int maxCityInitAllegCharNbr = Arrays.stream(cityInitAllegCharNbr).max().getAsInt();
		int maxCityInitAllegTabsNbr = maxCityInitAllegCharNbr/8 + 1;
		
		// Get current city allegiance display tabs
		int[] cityCurrAllegCharNbr = new int[maxCityLines];

		for(int city_idx = 1; city_idx < maxCityLines-1; city_idx++){
			cityCurrAllegCharNbr[city_idx] = cities[city_idx].cityCurrAlleg.length();
		}	
		
		int maxCityCurrAllegCharNbr = Arrays.stream(cityCurrAllegCharNbr).max().getAsInt();
		int maxCityCurrAllegTabsNbr = maxCityCurrAllegCharNbr/8 + 1;
		
		// Get input welth value to find min diff with current trader wealth
		int inpVall = 0;
		
		System.out.println("Please enter the wealth value:");
		try{
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String inputString = bufferRead.readLine();
		inpVall = Integer.parseInt(inputString);
		}catch (Exception e){
			e.printStackTrace();			
		} // Try - catch
		
		// Determine the number of traders to display the min diff with input wealth value
		int traderAnalysis = 0;
		
		System.out.println("Please enter how many traders to display:");
		try{
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String inputString = bufferRead.readLine();
		traderAnalysis = Integer.parseInt(inputString);
		}catch (Exception e){
			e.printStackTrace();			
		} // Try - catch
		
		WealthDiffs wealthDiff = new WealthDiffs();

		WealthDiffs[] wealthDiffs = new WealthDiffs[4*cities.length];
		wealthDiffs = wealthDiff.calcWealthDiffs(cities, inpVall);
		WealthDiffs[] minWealthDiffs = new WealthDiffs[traderAnalysis];
		minWealthDiffs = wealthDiff.calcMinWealthDiffs(wealthDiffs, inpVall, traderAnalysis);
		/*
		int minWdIdx = minWealthDiffs.length;
		while(minWdIdx > 0){
			minWdIdx--;
			System.out.println("Min wealth diff " + 
								minWealthDiffs[minWdIdx].wealthDiff + " for " + 
								minWealthDiffs[minWdIdx].cityName + "/" + 
								minWealthDiffs[minWdIdx].cityCurrAlleg + 
								minWealthDiffs[minWdIdx].trader);
		}
		*/
		for (int minWdIdx = 0; minWdIdx < traderAnalysis; minWdIdx++) {
			System.out.println("Min wealth diff " + 
					minWealthDiffs[minWdIdx].wealthDiff + " for " + 
					minWealthDiffs[minWdIdx].cityName + "/" + 
					minWealthDiffs[minWdIdx].cityCurrAlleg + 
					minWealthDiffs[minWdIdx].trader);
		}
		
		
		for(int city_idx = 1; city_idx < maxCityLines-1; city_idx++){
			// Apply city name display tabs
			String namesTabs = "";
			int namesTabsNbr = maxNameTabsNbr - (cities[city_idx].cityName).length()/8;
			
			for(int addTabs = 0; addTabs < namesTabsNbr ; addTabs++){
				namesTabs += "\t";
			}
			
			// Apply initial city allegiance display tabs
			String cityInitAllegTabs = "";
			int cityInitAllegTabsNbr = maxCityInitAllegTabsNbr - (cities[city_idx].cityInitAlleg).length()/8;
			
			for(int addTabs = 0; addTabs < cityInitAllegTabsNbr ; addTabs++){
				cityInitAllegTabs += "\t";
			}
			
			// Apply current city allegiance display tabs
			String cityCurrAllegTabs = "";
			int cityCurrAllegTabsNbr = maxCityCurrAllegTabsNbr - (cities[city_idx].cityCurrAlleg).length()/8;
			
			for(int addTabs = 0; addTabs < cityCurrAllegTabsNbr ; addTabs++){
				cityCurrAllegTabs += "\t";
			}
			
			
			
			//city.CityWeaponTW = inpVall - cities[city_idx].CityWeaponTW;
			
			
			System.out.println(	cities[city_idx].cityName + namesTabs +
								cities[city_idx].cityInitAlleg + cityInitAllegTabs +
								cities[city_idx].cityCurrAlleg + cityCurrAllegTabs +
								cities[city_idx].CityWeaponTW + "\t" +
								cities[city_idx].CityArmorTW + "\t" +
								cities[city_idx].CityHorseTW + "\t" +
								cities[city_idx].cityGeneralTW + "\t" +
								cities[city_idx].cityTotalWealth + "\t" +
								cities[city_idx].cityMinWealth + "\t" +
								cities[city_idx].cityMaxWealth);
		}

		
		
		
		
		
		
		
		
		
		
		
	}


}
