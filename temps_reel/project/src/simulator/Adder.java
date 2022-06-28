package simulator;

import java.util.*;
public class Adder extends Block {
	
	protected Entree in1, in2, in3, in4;
	protected Sortie sortie;
	protected Etat wait, addition;
	
	
	public double sum ;
	
	public Adder(String name, double t) {
		super(name, t);	
		this.sum = 0;
		
		sortie = new Sortie("sortie");
		
		in1 = new Entree("in1");
		in2 = new Entree("in2");
		in3 = new Entree("in3");
		in4 = new Entree("in4");
		
		wait = new Etat("wait", Double.POSITIVE_INFINITY);
		addition = new Etat("addition", 0);
		
		entrees.add(in1);
		entrees.add(in2);
		entrees.add(in3);
		entrees.add(in4);
		
		etats.put("wait", wait);
        etats.put("addition", addition);

        initial = wait;
        
	}
		
	public void add(ArrayList<Entree> ins) {
		
		for (Entree in : ins) {
			if(in.getFlag())
				sum += in.getValue();
		}
		
	}	
	
	@Override
	public void externe() {
		if(courant == wait) {
			courant = addition;
		add(entrees);			
		}
	}

	@Override
	public void interne() {
		if (courant == addition)
			courant = wait;
	}
	
	@Override
	public void lambda() {
		
		if(courant == addition) {
			sortie.setValue(this.sum);
		}	
	}

	
	public double getSum() {
		return sum;
	}
	

	public void setSum(double sum) {
		this.sum = sum;
	}

}