/**
 * 
 */
package beans;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author read
 *
 */
public class OpenWeatherData {
	
	/**
	 * 
	 * null constructor
	 *
	 */
	public OpenWeatherData(){
		// Set the created time in constructor
				this.timeCreated = Instant.now();
	}

	/**
	 * @param searchString
	 * @param searchSuccess
	 * @param weatherId
	 * @param summary
	 * @param description
	 * @param icon
	 * @param locationName
	 * @param temp
	 * @param pressure
	 * @param humidity
	 * @param tempMin
	 * @param tempMax
	 * @param visibility
	 * @param country
	 */
	public OpenWeatherData(String searchString, Boolean searchSuccess, String weatherId, String summary,
			String description, String icon, String locationName, BigDecimal temp, BigDecimal pressure,
			BigDecimal humidity, BigDecimal tempMin, BigDecimal tempMax, BigDecimal visibility, String country) {
		super();
		this.searchString = searchString;
		this.webServiceCallStatus = searchSuccess;
		this.weatherId = weatherId;
		this.summary = summary;
		this.description = description;
		this.icon = icon;
		this.locationName = locationName;
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.visibility = visibility;
		this.country = country;
		// Set the created time in constructor
		this.timeCreated = Instant.now();
	}
	/**
	 * @author Justin Brubaker
	 * Stores the time this data was created for history purging purposes.
	 */
	private Instant timeCreated;

	/**
	 * search string for the weather lookup. Can be a zip code, a city, state, country
	 */
	private String searchString;
/**
 * result of the search. True means we had a successful web service call, false means a problem
 */
	private Boolean webServiceCallStatus;
	
	/**
	 * message for web service call
	 */
	private String webServiceCallMessage;

	/**
	 * we are not currently using the location data, latitude and longitude
	 */
	/** 
	 * the following attributes are part of the summary subObject, "weather":, This is an array in the
	 * json object, but currently only returns one row 
	 */
	/**
	 * "id":800
	 */
	private String weatherId;
	/**
	 * Summary of weather "main":"
	 */
	private String summary;
	/**
	 * Longer description "description":
	 */
	private String description;
	/**
	 * Weather icon., currently unused "icon":
	 */
	private String icon;
	
	/**
	 * location "name":"
	 */
	private String locationName;

	/**
	 * The following attributes are part of the subObject "main":
	 * all temps are returned in degrees kelvin
	 */
	/**
	 * current temp "temp":
	 */
	private BigDecimal temp;
	/**
	 * pressure "pressure":
	 */
	private BigDecimal pressure;
	/**
	 * humidity "humidity":
	 */
	private BigDecimal humidity;
	/**
	 * min temp "temp_min":
	 */
	private BigDecimal tempMin;
	/**
	 * max temp "temp_max"
	 */
	private BigDecimal tempMax;
	/**
	 * visibility: units unknown, currently unused "visibility" (meters?)
	 */
	private BigDecimal visibility;
	/**
	 * skipping the clouds subObject, and a several other attributes
	 */
	
	/**
	 * country "country":
	 */
	private String country;
	
	/**
	 * wind speed
	 */
	private Integer windSpeed;
	/**
	 * wind direction
	 */
	private Integer windDirection;
	


	/**
	 * Getters and setters
	 * 
	 */
	/**
	 * @author Justin Brubaker
	 * @return the time that this was created.
	 */
	public Instant getTimeCreated() {
		return timeCreated;
	}
	/**
	 * @author Justin Brubaker
	 * Sets the creation time to the current system time.
	 */
	public void setCreationTime() {
		timeCreated = Instant.now();
	}
	
	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the searchSuccess
	 */
	public Boolean getSearchSuccess() {
		return webServiceCallStatus;
	}

	/**
	 * @param searchSuccess the searchSuccess to set
	 */
	public void setSearchSuccess(Boolean searchSuccess) {
		this.webServiceCallStatus = searchSuccess;
	}

	/**
	 * @return the weatherId
	 */
	public String getWeatherId() {
		return weatherId;
	}

	/**
	 * @param weatherId the weatherId to set
	 */
	public void setWeatherId(String weatherId) {
		this.weatherId = weatherId;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the temp
	 */
	public BigDecimal getTemp() {
		return temp;
	}

	/**
	 * @param temp the temp to set
	 */
	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	/**
	 * @return the pressure
	 */
	public BigDecimal getPressure() {
		return pressure;
	}

	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	/**
	 * @return the humidity
	 */
	public BigDecimal getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the tempMin
	 */
	public BigDecimal getTempMin() {
		return tempMin;
	}

	/**
	 * @param tempMin the tempMin to set
	 */
	public void setTempMin(BigDecimal tempMin) {
		this.tempMin = tempMin;
	}

	/**
	 * @return the tempMax
	 */
	public BigDecimal getTempMax() {
		return tempMax;
	}

	/**
	 * @param tempMax the tempMax to set
	 */
	public void setTempMax(BigDecimal tempMax) {
		this.tempMax = tempMax;
	}

	/**
	 * @return the visibility
	 */
	public BigDecimal getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(BigDecimal visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the webServiceCallStatus
	 */
	public boolean isWebServiceCallSuccess(){
		return this.webServiceCallStatus;
	}

	/**
	 * @return the webServiceCallMessage
	 */
	public String getWebServiceCallMessage() {
		return webServiceCallMessage;
	}

	/**
	 * @param webServiceCallMessage the webServiceCallMessage to set
	 */
	public void setWebServiceCallMessage(String webServiceCallMessage) {
		this.webServiceCallMessage = webServiceCallMessage;
	}

	/**
	 * @return the windSpeed
	 */
	public Integer getWindSpeed() {
		return windSpeed;
	}

	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(Integer windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * @return the winddirection
	 */
	public Integer getWinddirection() {
		return windDirection;
	}

	/**
	 * @param winddirection the winddirection to set
	 */
	public void setWinddirection(Integer winddirection) {
		this.windDirection = winddirection;
	}
	
	/**
	 * end of subObject main
	 */

	/**
	 * The following are methods for converting units to something we can display
	 */
	/**
	 * 
	 * @param tempInKelvin
	 * @return temp in degrees Cent
	 */
	public Integer getTempInDegreesCent(BigDecimal tempInKelvin){
		Integer degreesInCent = 999;
		final double zeroInKelvin = -273.15;
		if (tempInKelvin == null){
			return degreesInCent;
		}else{	
			degreesInCent =  (int) (tempInKelvin.doubleValue() + zeroInKelvin);
			return degreesInCent; 
		}
	}


	/**
	 * 
	 * @param tempInKelvin
	 * @return temp in degrees Farenheit
	 */
	public Integer getTempInDegreesFarenheit(BigDecimal tempInKelvin){
		Integer tempInDegFar = 999;
		final double convFactor = 9/5;
		int tempInCent = this.getTempInDegreesCent(tempInKelvin);
		if (tempInCent < 900){
			tempInDegFar = (int) ((tempInCent * convFactor) + 32);
		}
		
		return tempInDegFar;
	}

	public String getWindDirText(Integer windDir){
		// handle null condition
		if (windDir == null) windDir = 0;
		
		if (windDir > 327) return "North";
		if (windDir > 292) return "North West";
		if (windDir > 249) return "West";
		if (windDir > 207) return "South West";
		if (windDir > 157) return "South";
		if (windDir > 112) return "South East";
		if (windDir > 67) return "East";
		if (windDir > 22) return "North East";
		return "North";
	}

}
