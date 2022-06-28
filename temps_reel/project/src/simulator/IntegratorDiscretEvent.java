package simulator;

import java.lang.Math;


public class IntegratorDiscretEvent extends Block {
	
	protected double quantum;
	protected double value;
	protected double dX;
	protected double delta;
	protected double tInteg;

	protected Etat init;
	protected Etat integ;
	
	protected Sortie sortie;
	protected Entree entree;
	
	
	public IntegratorDiscretEvent(String name,double tInteg, double t) {
		super(name, t);
		
		this.tInteg = tInteg;
		this.quantum = 0.01;
		
		this.entree = new Entree("entree");
		this.sortie = new Sortie("sortie");
		
		this.init = new Etat("init", 0);
		this.integ  = new Etat("integ", this.tInteg);
		
		etats.put("init", init);
		etats.put("integ", integ);
		
		entrees.add(entree);
		sorties.add(sortie);
		initial = init;			
		}
	
	public IntegratorDiscretEvent(String name, double value, double tInteg, double t) {
		super(name, t);
		
		this.value = value;
		this.tInteg = tInteg;
		this.quantum = 0.01;
		
		this.entree = new Entree("entree");
		this.sortie = new Sortie("sortie");
		
		this.init = new Etat("init", 0);
		this.integ  = new Etat("integ", this.tInteg);
		
		etats.put("init", init);
		etats.put("integ", integ);

		entrees.add(entree);
		sorties.add(sortie);
		initial = init;	
		}
	
	public IntegratorDiscretEvent(String name, double value, double quantum, double tInteg, double t) {
		super(name, t);
		
		this.value = value;
		this.tInteg = tInteg;
		this.quantum = quantum;
		
		this.entree = new Entree("entree");
		this.sortie = new Sortie("sortie");
		this.init = new Etat("init", 0);
		this.integ  = new Etat("integ", this.tInteg);
		
		etats.put("init", init);
		etats.put("integ", integ);
		entrees.add(entree);
		sorties.add(sortie);
		
		initial = init;	
		}

	@Override
	public void externe() {
			delta = this.avancementTemps(); // variation de temps dt = temps d'avancement
			this.dX = entree.getValue(); //derivée en entrée
			
			if(this.dX == 0.0){
				this.delta = Double.POSITIVE_INFINITY;
			}
			
			else{
				this.quantum = Math.abs(this.quantum) * Math.signum(this.dX);
				this.delta = Math.abs(this.quantum/this.dX);
			}
			
			courant = init;	
			integ.setTa(delta);
	}

	@Override
	public void interne() {	
		if(courant == init){
			courant = integ;
		}	
		else if(courant == integ) {
			this.value +=  this.quantum;
			courant = init;
		}
	}

	@Override
	public void lambda() {
		if(courant == integ) {
			sortie.setValue(this.value);
		}
	}

	public double getQuantum() {
		return quantum;
	}

	public void setQuantum(double quantum) {
		this.quantum = quantum;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getdX() {
		return dX;
	}

	public void setdX(double dX) {
		this.dX = dX;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double gettInteg() {
		return tInteg;
	}

	public void settInteg(double tInteg) {
		this.tInteg = tInteg;
	}

	public Etat getInit() {
		return init;
	}

	public void setInit(Etat init) {
		this.init = init;
	}

	public Etat getInteg() {
		return integ;
	}

	public void setInteg(Etat integ) {
		this.integ = integ;
	}

	public Sortie getSortie() {
		return sortie;
	}

	public void setSortie(Sortie sortie) {
		this.sortie = sortie;
	}

	public Entree getEntree() {
		return entree;
	}

	public void setEntree(Entree entree) {
		this.entree = entree;
	}

}