package simulator;

public abstract class Port {
    protected String nom;
    protected double value;
    
    public Port(String nom){
        this.nom = nom;
        this.value = 0;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public abstract double getValue();
    public abstract void setValue(double value);
}

