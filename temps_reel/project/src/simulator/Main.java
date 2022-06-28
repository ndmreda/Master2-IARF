package simulator;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		//genBufProc();
		//Add_Steps();
		//discretTimeIntegration();
		discretEventIntegration();
		//constantSecondOrdreIntegration();
		//constantSecondOrdreIntegration2();
	}
	
	public static void genBufProc() {
		double tfin = 20;
		ArrayList<Block> composants = new ArrayList<Block>();

        Processor processor = new Processor("P", 3);
        Generator generator = new Generator("G", 2);
		Buffer buffer = new Buffer("q", 0);        
                
        composants.add(buffer);
        composants.add(generator);
        composants.add(processor);
        
        Simulator simulateur = new Simulator(tfin, composants);
        
        generator.job.addObservateur(buffer.job);
        processor.done.addObservateur(buffer.done);
        buffer.req.addObservateur(processor.req);
        
		simulateur.ordonnancer(1);
	}
	
	public static void Add_Steps() {
		double tfin = 2;
		
		ArrayList<Block> composants = new ArrayList<Block>();
				
        Step step1 = new Step("step1", 1, -3, 0.65);
        Step step2 = new Step("step2", 0, 1, 0.35);
        Step step3 = new Step("step3", 0, 1, 1);
        Step step4 = new Step("step4", 0, 4, 1.5);

        Adder adder = new Adder("Adder", 10);
        
        composants.add(step1);
        composants.add(step2);
        composants.add(step3);
        composants.add(step4);
        composants.add(adder);
        
        step1.sortie.addObservateur(adder.in1);
        step2.sortie.addObservateur(adder.in2);
        step3.sortie.addObservateur(adder.in3);
        step4.sortie.addObservateur(adder.in4); 
        
        
        Simulator simulateur = new Simulator(tfin, composants);
  
        simulateur.ordonnancer(2);
      
	}
	

	public static void discretTimeIntegration() {
		double tfin = 3;
		
		ArrayList<Block> composants = new ArrayList<Block>();
				
        Step step1 = new Step("step1", 1, -3, 0.65);
        Step step2 = new Step("step2", 0, 1, 0.35);
        Step step3 = new Step("step3", 0, 1, 1);
        Step step4 = new Step("step4", 0, 4, 1.5);

        Adder adder = new Adder("Adder", 10);
        double hstep = 0.001;
        Integrator integrator = new Integrator("Integrator", hstep, 10);
        
        composants.add(step1);
        composants.add(step2);
        composants.add(step3);
        composants.add(step4);
        composants.add(adder);
        composants.add(integrator);
        
        step1.sortie.addObservateur(adder.in1);
        step2.sortie.addObservateur(adder.in2);
        step3.sortie.addObservateur(adder.in3);
        step4.sortie.addObservateur(adder.in4); 
        adder.sortie.addObservateur(integrator.entree);
        
        Simulator simulateur = new Simulator(tfin, composants);
  
        simulateur.ordonnancer(3);
      
	}
	
	public static void discretEventIntegration() {
		double tfin = 3;
        double tInteg = 0.01;
		ArrayList<Block> composants = new ArrayList<Block>();
				
        Step step1 = new Step("step1", 1, -3, 0.65);
        Step step2 = new Step("step2", 0, 1, 0.35);
        Step step3 = new Step("step3", 0, 1, 1);
        Step step4 = new Step("step4", 0, 4, 1.5);
        Adder adder = new Adder("Adder", 10);
        IntegratorDiscretEvent integrator = new IntegratorDiscretEvent("IntegratorED",tInteg, 10);
        
        composants.add(step1);
        composants.add(step2);
        composants.add(step3);
        composants.add(step4);
        composants.add(adder);
        composants.add(integrator);
        
        step1.sortie.addObservateur(adder.in1);
        step2.sortie.addObservateur(adder.in2);
        step3.sortie.addObservateur(adder.in3);
        step4.sortie.addObservateur(adder.in4); 
        adder.sortie.addObservateur(integrator.entree);
        
        Simulator simulateur = new Simulator(tfin, composants);
  
        simulateur.ordonnancer(4);
      
	}
	
	public static void constantSecondOrdreIntegration() {
		double tfin = 3;
		
		ArrayList<Block> composants = new ArrayList<Block>();
		
		double h0 = 10;
		double quantum = 0.001;
        double tInteg = 0.01;
                
        IntegratorDiscretEvent integratorDE1 = new IntegratorDiscretEvent("IntegratorDE1", tInteg, tfin);
        IntegratorDiscretEvent integratorDE2 = new IntegratorDiscretEvent("IntegratorDE2", h0, quantum, tInteg, tfin);
        Constante constante = new Constante("Constante", tfin);
        
        composants.add(constante);
        composants.add(integratorDE1);
        composants.add(integratorDE2);
         
        constante.g.addObservateur(integratorDE1.entree);
        integratorDE1.sortie.addObservateur(integratorDE2.entree);

        Simulator simulateur = new Simulator(tfin, composants);
        simulateur.ordonnancer(5);
      
	}
	
	public static void constantSecondOrdreIntegration2() {
		double tfin = 10;
		
		ArrayList<Block> composants = new ArrayList<Block>();
		
		double h0 = 10;
		double quantum = 0.001;
        double tInteg = 0.01;
                
        IntegratorDiscretEvent integratorDE1 = new IntegratorDiscretEvent("IntegratorDE1", tInteg, tfin);
        IntegratorDiscretEvent integratorDE2 = new IntegratorDiscretEvent("IntegratorDE2", h0, quantum, tInteg, tfin);
        Constante constante = new Constante("Constante", tfin);
        
        composants.add(constante);
        composants.add(integratorDE1);
        composants.add(integratorDE2);
         
        constante.g.addObservateur(integratorDE1.entree);
        integratorDE1.sortie.addObservateur(integratorDE2.entree);

        Simulator simulateur = new Simulator(tfin, composants);
        simulateur.ordonnancer2(5);
      
	}
}
