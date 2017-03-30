/**
 * 
 */
package test;

import java.math.BigDecimal;

import beans.OpenWeatherData;
import weatherGenie.GetWeatherData;

/**
 * @author read
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		//checkTempConversion();
		callWeatherService();
	}
	
	/**
	 * set up a test bean for the other test methods
	 */
	private static  OpenWeatherData getATestWeatherBean(){
		 OpenWeatherData testBean = new OpenWeatherData(
				"17601", true, "800", "Nice", "Nice Day", 
				"00", "East Petersburg", new BigDecimal(268.48), new BigDecimal(1037.0), new BigDecimal(41.0), 
				new BigDecimal(266.15), new BigDecimal(270.15), new BigDecimal(16093), "US");
		 return testBean;
	}
	@SuppressWarnings("unused")
	private static void checkTempConversion(){
		OpenWeatherData testBean = getATestWeatherBean();
		BigDecimal tempInKelvin = testBean.getTemp();
		System.out.println("Current Temp in Cent:" + testBean.getTempInDegreesCent(tempInKelvin));
		System.out.println("Current Temp in Farenheit:" + testBean.getTempInDegreesFarenheit(tempInKelvin));
		System.out.println("Max temp in F:" + testBean.getTempInDegreesFarenheit(testBean.getTempMax()));
		System.out.println("Min temp in F:" + testBean.getTempInDegreesFarenheit(testBean.getTempMin()));
	}
	
	/**
	 * call the GetWeatherService class and check the results in the bean
	 * 
	 */
	private static void callWeatherService(){
		// create an object to store the weather data
		OpenWeatherData weatherData = new OpenWeatherData();
		// create the object to call the web service and load the weatherData bean
		GetWeatherData getWeatherData = new GetWeatherData();
		weatherData = getWeatherData.getWeatherData("17601");
		
		// list the contents of the bean
		System.out.println("Your query string: " + weatherData.getSearchString());
		System.out.println("was call successful? " + weatherData.getSearchSuccess());
		System.out.println("message returned: " + weatherData.getWebServiceCallMessage());
		
		// location
		System.out.println("Location: " + weatherData.getLocationName());
		System.out.println("Country: " + weatherData.getCountry() );
		// conditions
		System.out.println("conditions: " + weatherData.getSummary() + " (" + weatherData.getDescription() + ")");
		
		// temps
		System.out.println("current temp     : " + weatherData.getTemp());
		System.out.println("24 hour high temp: " + weatherData.getTempMax());
		System.out.println("24 hour low temp : " + weatherData.getTempMin());
		System.out.println("Humidity: " + weatherData.getHumidity());
		System.out.println("Pressure: " + weatherData.getPressure());
		// wind speed
		System.out.println("wind speed: " + weatherData.getWindSpeed());
		System.out.println("wind dir  : " + weatherData.getWinddirection());
	}

}
