
/**
 * Write a description of class WindChillTemperature here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WindChillTemperature
{
    private double temperatureInFaharenheit;
    private double temperatureInCelsius;
    private double windSpeedinMPH;
    private double windchillTemperatureF;
    private double windchillTemperatureC;

    public WindChillTemperature() {
        temperatureInFaharenheit = 0.0;
        windSpeedinMPH = 0.0;
        windchillTemperatureF = 0.0; // the dependant vars are 0 intantiated
        // lets just 0 intiatate this too. 
    }
    
	/// @warning This takes temperatures in faharenheit
    public WindChillTemperature(double tempF) {
        temperatureInFaharenheit = tempF;
		temperatureInCelsius = F2C(tempF);
        windSpeedinMPH = 0.0;
        windchillTemperatureF = 0.0;
    }
    
	/// @warning This takes temperatures in faharenheit
    public WindChillTemperature(double tempF, double speed) {
        temperatureInFaharenheit = tempF;
		temperatureInCelsius = F2C(tempF);
        windSpeedinMPH = speed;
        updateWindChill(); // we have the other 2 vars, lets go ahead
        // and caclulate it
    }

    public void setTempF(double tempF) {
        temperatureInFaharenheit = tempF;
		temperatureInCelsius = F2C(tempF);
		updateWindChill();
    }
	
	public void setTempC(double tempC) {
        temperatureInCelsius = tempC;
		temperatureInFaharenheit = C2F(tempC);
		updateWindChill();
    }

    public void setWindSpeed(double speed) {
        windSpeedinMPH = speed;
		updateWindChill();
    }

    public double getTempF() {
        return temperatureInFaharenheit;
    }
	
    public double getTempC() {
        return temperatureInCelsius;
    }

    public double getWindSpeed() {
        return windSpeedinMPH;
    }

    public double getWindChillF() {
		return windchillTemperatureF;
    }
	
	public double getWindChillC() {
		return windchillTemperatureC;
    }
    
    private void updateWindChill() {
		// long formula eq to this:
		// WCF = 35.74 + 0.6215 * TF – 35.75 * V^0.16  + 0.4275 * TF * (V^0.16)
		windchillTemperatureF = 35.74
		+ 0.6215 * temperatureInFaharenheit
		- 35.75 * Math.pow(windSpeedinMPH, 0.16)
		+ 0.4275 * temperatureInFaharenheit * Math.pow(windSpeedinMPH, 0.16);
		
		windchillTemperatureC = F2C(windchillTemperatureF);
    }
	
	// declared static
	private static double F2C(double faharenheit) {
		return (faharenheit - 32) * 0.5556;
	}
	
	private static double C2F(double celsius) {
		return celsius * 1.8 + 32;
	}
    
    public String toString() {
        return 
          "The wind chill at "
        + temperatureInFaharenheit 
        + " F with wind speed of " 
        + windSpeedinMPH 
        + " MPH is "
        + windchillTemperatureF
        + " F";
    }
}
