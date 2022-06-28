package simulator;

import java.util.ArrayList;

public class Sortie implements Observable{
    protected String nom;
    protected String type;
    protected double value = 0;
    protected ArrayList<Observateur> observers  = new ArrayList<>();

    public Sortie(String nom, String type){
        this.nom = nom;
        this.type = type;

    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public double getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public void removeObservateur(Observateur observateur) {
        this.observers.remove(observateur);
    }

    @Override
    public void notifier() {
        this.observers.forEach(obs -> obs.update(this,this.value));
    }
    
    
	public void setValue(double value, double t) {
		this.value = value;
        if(!Double.isNaN(this.value)){
            this.notifierTous(t);
        }
	}

	@Override
	public void notifierTous(double t) {
        this.observers.forEach(obs -> obs.update(this,this.value));
		
	}



}

