package simulator;

public class Processor extends Block {

	protected Etat free, busy;
	protected Entree req;
	protected Sortie done;
	
    public Processor(String name, double tp){
    	super(name, tp);
    	
        free = new Etat("free",Double.POSITIVE_INFINITY);
        busy = new Etat("busy",tp);
        req = new Entree("req");
        done = new Sortie("done");
        
        etats.put("free",free);
        etats.put("busy",busy);
        
        entrees.add(req);
        sorties.add(done); 
        initial = free;
    }

    @Override
    public void externe() {
        if (courant==free && req.getFlag()){
            courant = busy;
        }
    }

    @Override
    public void interne() {
        if(courant == busy){
            courant = free;
        }
    }


    @Override
    public void lambda() {
        if (courant==busy){
            done.setValue(0);
        }
    }
    
}
