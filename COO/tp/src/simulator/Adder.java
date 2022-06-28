package simulator;

import java.util.*;
import chart.*;
public class Adder extends Block {
	
	protected Sortie out;
	protected Entree in1, in2, in3, in4;
	protected ArrayList<Entree> ins;
	protected Etat wait, addition;
	
	
	public double sum ;
	
	public Adder(String name, double t) {
		super(name, t);	
		this.sum = 0;
		out = new Sortie("out", "boolean");
		
		in1 = new Entree("in1", "boolean");
		in2 = new Entree("in2", "boolean");
		in3 = new Entree("in3", "boolean");
		in4 = new Entree("in4", "boolean");
		entrees.add(in1);
		entrees.add(in2);
		entrees.add(in3);
		entrees.add(in4);
		
		wait = new Etat("wait", Double.POSITIVE_INFINITY);
		addition = new Etat("addition", 0);
		etats.put("wait", wait);
        etats.put("addition", addition);

        initial = wait;
        
	}
	
	@Override
	public void externe() {
		System.out.println("extern...........: "+in1.getValue());
		System.out.println("extern...........: "+in2.getValue());
		System.out.println("extern...........: "+in3.getValue());
		System.out.println("extern...........: "+in4.getValue());
		if(courant == wait) {
			add(entrees);
			courant = addition;
		}
	}

	@Override
	public void interne() {
		if (courant == addition)
			courant = wait;
	}
	
	public void add(ArrayList<Entree> ins) {
		
		for (Entree in : ins) {
			if(in.getValue() != Double.NaN)
				sum += in.getValue();
		}
		
	}
	
	
	@Override
	public void lambda() {
		
		if(courant == addition) {
			out.setValue(this.sum);
			System.out.println("Sortie du adder : "+sum);
		}	
	
	     //out.setValue(this.sum, this.tl);
	}

	
	public double getSum() {
		return sum;
	}
	

	public void setSum(double sum) {
		this.sum = sum;
	}

}
