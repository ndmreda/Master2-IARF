package simulator;

import java.util.ArrayList;

public class Sortie extends Port implements Observable{
    protected ArrayList<Observateur> observers;

    public Sortie(String nom){
        super(nom);
        this.observers  = new ArrayList<>();
    }
    
    public void setValue(double value) {
        this.value = value;
        if(!Double.isNaN(this.value)){
            this.notifier();
        }
    }

    @Override
    public void addObservateur(Observateur observateur) {
        this.observers.add(observateur);
    }

    @Override
    public void notifier(){
       for (Observateur obs : observers)
    	   obs.update(this,this.value);
    }
    

	@Override
	public double getValue() {
		return this.value;
	}
}

