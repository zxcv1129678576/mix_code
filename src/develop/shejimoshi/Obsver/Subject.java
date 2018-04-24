package develop.shejimoshi.Obsver;

public interface Subject {
	
	public void registorObservers(Observer obsver);
	public void removeObservers(Observer obsver);
	public void notifyObservers();

}
