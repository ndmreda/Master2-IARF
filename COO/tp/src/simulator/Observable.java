package simulator;

public interface Observable {
    
	public void addObservateur(Observateur observateur);
	public void removeObservateur(Observateur observateur);
	public void notifier();
	public void notifierTous(double t);

}
