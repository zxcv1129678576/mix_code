package develop.shejimoshi.Obsver;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{
	private float p;
	private float h;
	private float t;
	private List<Float> forecastTemperatures;
	@Override
	public void registorObservers(Observer obsver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeObservers(Observer obsver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}
	
	public WeatherData(){
		forecastTemperatures=new ArrayList<>();
	}
	
}
