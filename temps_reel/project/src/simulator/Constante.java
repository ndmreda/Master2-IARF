package simulator;

public class Constante extends Block {
	
	protected final double x = -9.81;
	protected Sortie g;
	protected Etat init;
	protected Etat end;
	
	
	public Constante(String name, double t) {
		super(name, t);
		
		this.g = new Sortie("g");
		this.init = new Etat("init", 0);
		this.end = new Etat("end", Double.POSITIVE_INFINITY);
		
		sorties.add(g);
		etats.put("init", init);
		etats.put("end", end);
		
		initial = init;
	}

	@Override
	public void externe() {}

	@Override
	public void interne() {
		if(courant == init)
			courant = end;
	}

	@Override
	public void lambda() {
		if(courant == init)
			g.setValue(x);
	}

}
