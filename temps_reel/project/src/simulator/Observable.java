package simulator;

public interface Observable {
    
	public void addObservateur(Observateur observateur);
	public void notifier();

}
