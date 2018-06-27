import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
/*
import acok_trade.City;
import acok_trade.Item;
*/

public class City {
	public String cityName;
	public String cityInitAlleg;
	public String cityCurrAlleg;
	public int CityWeaponTW;
	public int CityArmorTW;
	public int CityHorseTW;
	public int cityGeneralTW;
	public int cityTotalWealth;
	public int cityMinWealth;
	public int cityMaxWealth;

	public final String cityWealthFile = "C:\\Users\\freelancer\\workspace\\ACOK_CityWealth\\ACOK_CityWealth.csv";

	public City(){};
	
	public int getMaxCities(String pathName) throws FileNotFoundException{
		
		int maxCityLines = 0;
		
		try { // determine number of lines (cities) in file
			LineNumberReader lnr = new LineNumberReader(new FileReader(pathName));
			while (lnr.skip(Long.MAX_VALUE) > 0) {
			}

			maxCityLines = lnr.getLineNumber();
			lnr.close();

			//System.out.println("File has " + maxCityLines + " lines."); // test
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxCityLines;
	}
	
	public City[] showTradeCities() throws FileNotFoundException{
		
		int maxCityLines = 0;	//getMaxFileLines(pathName);
		
		maxCityLines = getMaxCities(cityWealthFile);
		
		try { // determine number of lines (cities) in file
			LineNumberReader lnr = new LineNumberReader(new FileReader(cityWealthFile));
			while (lnr.skip(Long.MAX_VALUE) > 0) {
			}

			maxCityLines = lnr.getLineNumber();
			lnr.close();

			System.out.println("File has " + maxCityLines + " lines."); // test
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		City city = new City();
		
		City[] cities = new City[maxCityLines];

		String[] cityLines = new String[maxCityLines];
		
		int line = 0;
		
		String[] lineElements;
		
		File file = new File(cityWealthFile);
		Scanner scanner = new Scanner(file);
		
		try{
			try{
				while (scanner.hasNextLine()) {
					// parse line		
					cityLines[line] = scanner.nextLine();
					System.out.println("Processing line " + (line + 1));
					System.out.println(cityLines[line]);
					if(line == 0){				
					}else{
						if(line == maxCityLines - 1){
							break;
						}else{
							city = parseCityLine(cityLines[line]);
							
							city.cityName 		= this.getCityName(); 
							city.cityInitAlleg 	= this.getCityInitAlleg();
							city.cityCurrAlleg 	= this.getCityCurrAlleg();
							city.CityWeaponTW 	= this.getCityWeaponTW();
							city.CityArmorTW 	= this.getCityArmorTW();
							city.CityHorseTW 	= this.getCityHorseTW();
							city.cityGeneralTW 	= this.getCityGeneralTW();
							
							this.setCityTotalWealth(city.CityWeaponTW + city.CityArmorTW + city.CityHorseTW + city.cityGeneralTW);
							this.setCityMaxWealth(Math.max(city.CityWeaponTW, Math.max(city.CityArmorTW, Math.max(city.CityHorseTW, city.cityGeneralTW))));
							this.setCityMinWealth(calcCityMinWealth(city.CityWeaponTW, city.CityArmorTW, city.CityHorseTW, city.cityGeneralTW));
							
							city.cityTotalWealth 	= this.getCityTotalWealth();
							city.cityMinWealth 		= this.getCityMinWealth();
							city.cityMaxWealth 		= this.getCityMaxWealth();
							
							cities[line] = new City();
							
							cities[line].setCityName(		city.cityName);
							cities[line].setCityInitAlleg(	city.cityInitAlleg);
							cities[line].setCityCurrAlleg(	city.cityCurrAlleg);
							cities[line].setCityWeaponTW(	city.CityWeaponTW);
							cities[line].setCityArmorTW(	city.CityArmorTW);
							cities[line].setCityHorseTW(	city.CityHorseTW);
							cities[line].setCityGeneralTW(	city.cityGeneralTW);
							cities[line].setCityTotalWealth(city.cityTotalWealth);
							cities[line].setCityMinWealth(	city.cityMinWealth);
							cities[line].setCityMaxWealth(	city.cityMaxWealth);
						}
					}
					line++;
				}
			} finally {
				scanner.close();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
				
		return cities;
	}
	
	public int calcCityMinWealth(int CityWeaponTW, int CityArmorTW, int CityHorseTW, int cityGeneralTW){ 
		int init = 10000000;
		int min = init;
		
		if((CityWeaponTW != 0)&&(CityWeaponTW < min)){
			min = CityWeaponTW;
		}

		if((CityArmorTW != 0)&&(CityArmorTW < min)){
			min = CityArmorTW;
		}

		if((CityHorseTW != 0)&&(CityHorseTW < min)){
			min = CityHorseTW;
		}

		if((cityGeneralTW != 0)&&(cityGeneralTW < min)){
			min = cityGeneralTW;
		}
		
		if(min == init){
			min = 0;
		}
		
		return min;
	}
	
	public City parseCityLine(String fileLine){
		City city = new City();
		String[] lineElements;
		
		lineElements = splitLine(fileLine);
		
		// not all lines have the "new allegiance" element
		for (int lineElementIdx = 0; lineElementIdx < lineElements.length; lineElementIdx++) {
			switch (lineElementIdx) {
			case 0:	//assign city name
				this.setCityName(lineElements[lineElementIdx]);
				break;
			case 1: //assign city initial allegiance
				this.setCityInitAlleg(lineElements[lineElementIdx]);
				break;
			case 2: //assign city current allegiance
				this.setCityCurrAlleg(lineElements[lineElementIdx]);
				break;
			case 3: 
				this.setCityWeaponTW(Integer.parseInt(lineElements[lineElementIdx]));
				break;
			case 4: 
				this.setCityArmorTW(Integer.parseInt(lineElements[lineElementIdx]));
				break;
			case 5: 
				this.setCityHorseTW(Integer.parseInt(lineElements[lineElementIdx]));
				break;
			case 6: 
				this.setCityGeneralTW(Integer.parseInt(lineElements[lineElementIdx]));
				break;
			default: //parse city trading items
				
				break;
			}
		}
		
		return city;
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

	public int getCityWeaponTW() {
		return this.CityWeaponTW;
	}

	public void setCityWeaponTW(int newCityWeaponTW) {
		this.CityWeaponTW = newCityWeaponTW;
	}

	public int getCityArmorTW() {
		return this.CityArmorTW;
	}

	public void setCityArmorTW(int newCityArmorTW) {
		this.CityArmorTW = newCityArmorTW;
	}

	public int getCityHorseTW() {
		return this.CityHorseTW;
	}

	public void setCityHorseTW(int newCityHorseTW) {
		this.CityHorseTW = newCityHorseTW;
	}
	
	public int getCityGeneralTW() {
		return this.cityGeneralTW;
	}

	public void setCityGeneralTW(int newCityGeneralTW) {
		this.cityGeneralTW = newCityGeneralTW;
	}	

	public int getCityTotalWealth() {
		return this.cityTotalWealth;
	}

	public void setCityTotalWealth(int newCityTotalWealth) {
		this.cityTotalWealth = newCityTotalWealth;
	}
	
	public int getCityMinWealth() {
		return this.cityMinWealth;
	}

	public void setCityMinWealth(int newCityMinWealth) {
		this.cityMinWealth = newCityMinWealth;
	}	
	
	public int getCityMaxWealth() {
		return this.cityMaxWealth;
	}

	public void setCityMaxWealth(int newCityMaxWealth) {
		this.cityMaxWealth = newCityMaxWealth;
	}
	
	public String[] splitLine(String line) {
		return line.split(";");
	}
	
}
