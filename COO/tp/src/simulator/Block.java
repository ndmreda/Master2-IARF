package simulator;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Block {
	protected String name;
	protected double e, tl, tn, tr;
	
	protected ArrayList<Entree> entrees = new ArrayList<>();
	protected ArrayList<Sortie> sorties = new ArrayList<>();
	protected HashMap<String,Etat> etats = new HashMap<>();
	protected Etat initial, courant;
   
    public Block(String name, double t){
    	this.name = name;
    	this.tn = t;
    }
    
    public abstract void externe();
	public abstract void interne();
	public abstract void lambda();
	
    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getTl() {
        return tl;
    }

    public void setTl(double tl) {
        this.tl = tl;
    }

    public double getTn() {
        return tn;
    }

    public void setTn(double tn) {
        this.tn = tn;
    }

    public double getTr() {
        return tr;
    }

    public void setTr(double tr) {
        this.tr = tr;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    public ArrayList<Entree> getEntrees() {
		return entrees;
	}

	public void setEntrees(ArrayList<Entree> entrees) {
		this.entrees = entrees;
	}

	public ArrayList<Sortie> getSorties() {
		return sorties;
	}

	public void setSorties(ArrayList<Sortie> sorties) {
		this.sorties = sorties;
	}

	public HashMap<String, Etat> getEtats() {
		return etats;
	}

	public void setEtats(HashMap<String, Etat> etats) {
		this.etats = etats;
	}

	public Etat getInitial() {
		return initial;
	}

	public void setInitial(Etat initial) {
		this.initial = initial;
	}

	public Etat getCourant() {
		return courant;
	}

	public void setCourant(Etat courant) {
		this.courant = courant;
	}

	public double avancementTemps() {
        return etats.get(courant.name).getTa();
    }

    
    public void initialiser() {
    	courant = initial;
    	for(Sortie out : sorties)
    		out.setValue(0.0);
    	for(Entree in : entrees)
    		in.setValue(0.0);
    	return;
	}

    public Boolean entreeImpactee(){
        Boolean rtr = Boolean.FALSE;
        for(Entree e : entrees){
            rtr |= e.getFlag();
        }
        return rtr;
    }

	
}
