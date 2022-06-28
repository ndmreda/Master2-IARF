package simulator;

public class Etat {

	protected double ta;
	protected String name;
	
    public Etat(String name, double ta){
        this.name = name;
        this.ta = ta;
    }


    public double getTa() {
        return ta;
    }

    public void setTa(double ta) {
        this.ta = ta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
