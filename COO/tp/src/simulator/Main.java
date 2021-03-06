package simulator;

import java.util.*;
import chart.*;

public class Main {

	public static void main(String[] args) {
		
		double tfin = 6;
		ArrayList<Block> composants = new ArrayList<Block>();

        Processor processor = new Processor("P", 3);
        Generator generator = new Generator("G", 2);
		Buffer buffer = new Buffer("B", 0);        
                
        composants.add(buffer);
        composants.add(generator);
        composants.add(processor);
        
        Simulator simulateur = new Simulator(tfin, composants);
        
        generator.job.addObservateur(buffer.job);
        processor.done.addObservateur(buffer.done);
        buffer.req.addObservateur(processor.req);
        
		//simulateur.ordonnancer();
		
        
        ArrayList<Block> composants2 = new ArrayList<Block>();
		
		
        Step step1 = new Step("step1", 1, -3, 0.65);
        Step step2 = new Step("step2", 0, 1, 0.35);
        Step step3 = new Step("step3", 0, 1, 1);
        Step step4 = new Step("step4", 0, 4, 1.5);

        Adder adder = new Adder("Adder", 10);
        
        composants2.add(step1);
        composants2.add(step2);
        composants2.add(step3);
        composants2.add(step4);
        composants2.add(adder);
        
        step1.sortie.addObservateur(adder.in1);
        step2.sortie.addObservateur(adder.in2);
        step3.sortie.addObservateur(adder.in3);
        step4.sortie.addObservateur(adder.in4); 
        
        
        Simulator simulateur2 = new Simulator(tfin, composants2);
  
        simulateur2.ordonnancer();
      
	}
	

}
