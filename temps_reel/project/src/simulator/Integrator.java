package simulator;

public class Integrator extends Block {
	protected double hstep;
	protected double value;
	protected double delta;
	protected double dX;
	
	protected Etat init;
	protected Etat integ;
	
	protected Sortie sortie;
	protected Entree entree;
	
	

	public Integrator(String name, double t) {
		super(name, t);
		this.hstep = 0.001;
		this.dX = 0;
		this.value = 0;
		
		this.entree = new  Entree("entree");
		this.sortie = new Sortie("sortie");
		
		this.init = new Etat("init", 0);
		this.integ  = new Etat("integ", this.hstep);
		
		etats.put("init", init);
		etats.put("integ", integ);
		
		entrees.add(entree);
		sorties.add(sortie);
		
		initial = init;
	}
	
	public Integrator(String name, double hstep, double t) {
		super(name, t);
		this.hstep = hstep;
		this.dX = 0;
		this.value = 0;
		
		this.entree = new Entree("entree");
		this.sortie = new Sortie("sortie");
		
		this.init = new Etat("init", 0);
		this.integ  = new Etat("integ", this.hstep);
		
		etats.put("init", init);
		etats.put("integ", integ);
		
		entrees.add(entree);
		sorties.add(sortie);
		
		initial = init;
	}

	@Override
	public void externe() {
		this.dX = entree.getValue();
		if(courant==init) {
			courant = integ;
		}
	}

	@Override
	public void interne() {
		if(courant == init) {
			courant = integ;
		}
		else if(courant == integ){
			delta = this.avancementTemps();
			this.value +=  this.delta*this.dX;
			courant = init;
		}
	}

	@Override
	public void lambda() {
		if(courant == integ)
			sortie.setValue(this.value);
	}

	public double getHstep() {
		return hstep;
	}

	public void setHstep(double hstep) {
		this.hstep = hstep;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getdX() {
		return dX;
	}

	public void setdX(double dX) {
		this.dX = dX;
	}
	
	
}