/**
 * 
 */
package weatherGenie;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import beans.OpenWeatherData;

/**
 * @author read
 * 
 * given a search string, attempt to get the weather data for that query
 *
 */
public class GetWeatherData {
	
	public OpenWeatherData getWeatherData(String query){
		
		// first, assume the new query will be successful
		OpenWeatherData weatherData = new OpenWeatherData();
		weatherData.setSearchString(query);
		weatherData.setWebServiceCallMessage("Query successful");
		weatherData.setSearchSuccess(true);
		
		//check that we were passed a search string
		if (query == null || query.trim().isEmpty()){
			// set the error message
			weatherData.setWebServiceCallMessage("You did not enter a search string");
			weatherData.setSearchSuccess(false);
			return weatherData;
		}
		
		//URL url;
		try {
			// connect to the external server via the url and grab the result
			URL url = new URL(this.setupUrl(query));
			InputStream is = url.openStream();
			// parse in the json returned into an object
			JsonReader rdr = Json.createReader(is);			
			JsonObject obj = rdr.readObject();
			
			// first figure out if query was successful by checking the "cod" field. If not, set error message and return
			int returnCode = obj.getJsonNumber("cod").intValue();
			if (! (returnCode == 200)){
				weatherData.setSearchSuccess(false);
				weatherData.setWebServiceCallMessage("No location found to match your query");
				return weatherData;
			}
			
			JsonArray results = obj.getJsonArray("weather");
			// this can be an array, one for each day
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				// load the weather 'main' and description. I'll give you the first one
				weatherData.setSummary(result.getString("main", ""));
				weatherData.setDescription(result.getString("description"));				
			}
			
			// I've got city, get the country
			weatherData.setLocationName(obj.getString("name",""));
			
			// get the detailed weather object
			JsonObject innerJsonObject = obj.getJsonObject("main");
			
			// load the temperature, humidity, pressure
			// I'll give you the first one:
			weatherData.setTemp(innerJsonObject.getJsonNumber("temp").bigDecimalValue());
			weatherData.setTempMin(innerJsonObject.getJsonNumber("temp_min").bigDecimalValue());
			weatherData.setTempMax(innerJsonObject.getJsonNumber("temp_max").bigDecimalValue());
			weatherData.setPressure(innerJsonObject.getJsonNumber("pressure").bigDecimalValue());
			weatherData.setHumidity(innerJsonObject.getJsonNumber("humidity").bigDecimalValue());
			
			weatherData.setCountry(obj.getJsonObject("sys").getString("country"));

			// get the wind speed and direction
			JsonObject wind = obj.getJsonObject("wind");
			
			// I'll give you the windSpeed, you get the direction ("deg")
			weatherData.setWindSpeed(wind.getJsonNumber("speed").intValue());
			weatherData.setWinddirection(wind.getJsonNumber("deg").intValue());

			
		} catch (MalformedURLException e) {
			weatherData.setWebServiceCallMessage("Bad Url setup");
			weatherData.setSearchSuccess(false);
		} catch (IOException e) {
			weatherData.setWebServiceCallMessage("Unsuccessful query. Please try again.");
			weatherData.setSearchSuccess(false);
		} catch(Exception e){
			weatherData.setWebServiceCallMessage("Unknown error");
			weatherData.setSearchSuccess(false);
		}
		return weatherData;

	}
	
	/**
	 * 
	 * @param searchQuery
	 * @return the url string with the search query embedded
	 */
	private String setupUrl(String searchQuery){
		String url = "http://api.openweathermap.org/data/2.5/weather?q=@@@&APPID=" + Reference.openWeatherAppId;
		return url.replace("@@@", searchQuery);

	}
	
}
