package simulator;

public class Step extends Block {
	
	protected double xi;
	protected double xf;
	protected double ts;
	protected Etat init, wait, end;	
	
	protected Sortie sortie;

	public Step(String name, double xi, double xf, double ts) {
		super(name, ts);
		this.xi = xi;
		this.xf = xf;
		
		sortie = new Sortie("sortieStep");
		sorties.add(sortie);
		
		init = new Etat("init", 0);
        wait = new Etat("wait", ts);
        end = new Etat("end", Double.POSITIVE_INFINITY);
        
        etats.put("init", init);
        etats.put("wait", wait);
        etats.put("end", end);
        
        initial = courant =  init;
		
	}

    
	@Override
	public void externe() {
	}

	@Override
	public void interne() {
		if (courant == init) {
            courant = wait;
            sortie.setValue(xi);
        }
		else if (courant == wait) {
            courant = end;
            sortie.setValue(xf);
        }
        
	}

	@Override
	public void lambda() {
		if (courant == wait) {
            sortie.setValue(xi);
        }        
	}

}