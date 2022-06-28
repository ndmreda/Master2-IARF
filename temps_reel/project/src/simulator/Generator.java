package simulator;

public class Generator extends Block {

	protected Etat s;
    protected Sortie job;
    
    public Generator(String name, double tg){
    	super(name, tg);
    	
        s = new Etat("s",tg);
        job = new Sortie("job");

        initial = courant = s;
        etats.put("s",s);
        sorties.add(job);
    }

    @Override
    public void externe() {}

    @Override
    public void interne() {
    	if(courant == s)
    		courant = s;
    }

    @Override
    public void lambda() {
        job.setValue(0);
    }
    
}
