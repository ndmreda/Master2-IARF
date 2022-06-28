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
		
		sortie = new Sortie("sortieStep", "boolean");
		sorties.add(sortie);
		
		init = new Etat("init", 0);
        wait = new Etat("wait", ts);
        end = new Etat("end", Double.POSITIVE_INFINITY);
        
        etats.put("init", init);
        etats.put("wait", wait);
        etats.put("end", end);
        
        initial =  init;
		
	}

    
	@Override
	public void externe() {
		return;	
	}

	@Override
	public void interne() {
		if (courant == init) {
            courant = wait;
        }
		else if (courant == wait) {
            courant = end;
        }
        
	}

	@Override
	public void lambda() {
		if (courant == wait) {
            sortie.setValue(xi);

        }
        if (courant == end ) {
            sortie.setValue(xf);
        }
        System.out.println(this.name + " : " + sortie.getValue());       	
		
		/*if (courant.getTa() == ts) {
		 
            sortie.setValue(this.xf);
        }*/
        
        
	}

}
