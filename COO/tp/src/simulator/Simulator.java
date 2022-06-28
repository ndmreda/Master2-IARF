package simulator;

import chart.*;
import java.util.ArrayList;
import java.util.Collections;

public class Simulator {

	public double tfin;
    public ArrayList<Block> composants;
	
	public Simulator(double tfin, ArrayList<Block> composants) {
    	this.composants = composants;
    	this.tfin = tfin;
    }
	
	public void ordonnancer() {

        double t = 0;
        
        for (Block c : composants) {
    		c.initialiser();
    		c.setTr(c.avancementTemps());  //recuperer la durée Ta de l'etat courant associé à chaque composant
    	}
        
        Chart chart = new Chart("chart");
        ChartFrame chartFrame = new ChartFrame("simulator", "frame");
        
        chartFrame.addToLineChartPane(chart);
        
        while (t < tfin) {

        	
            ArrayList<Block> imms = new ArrayList<>();
            ArrayList<Double> trs = new ArrayList<>();
            
            double trmin = Double.POSITIVE_INFINITY;
            
            for(Block c : composants) {
    			trs.add(c.getTr());
    		}
            
            trmin = Collections.min(trs);
            
            for (Block c : composants) {
    			if(trmin == c.getTr())
    				imms.add(c);
    		}
            
           
            
            /*
            double var = Double.POSITIVE_INFINITY;
            for (Double temp : trs) {
            	if(temp < var)
            		var = temp;
            	
            }
            trmin = var;
            */
            
            for (Block c : composants)
                if (trmin == c.getTr())
                    imms.add(c);
            
            t = t + trmin;
            
            System.out.println("temps : " + t + " trmin "+trmin);

            
            for (Block c : composants) {
                c.setE(c.getE() + trmin);
                c.setTr(c.getTr() - trmin);
            }

            for (Block c : imms)
                c.lambda();

            ArrayList<Entree> ins = new ArrayList<>();
            for (Block c : composants) {
           	
                for (Entree in : c.entrees) {
                    if (in.getFlag()) {
                        ins.add(in);
                    }
                }
            }
            
            
            
            
            for (Block c : composants) {
                if (imms.contains(c) && !c.entreeImpactee()) {
                    c.interne();
                    c.setTl(t);
                    c.setE(0);
                    c.setTr(c.avancementTemps());
                    c.setTn(t + c.getTr());

                }
                else if (!imms.contains(c) && c.entreeImpactee()) {
                    c.externe();
                    c.setTl(t);
                    c.setE(0);
                    c.setTr(c.avancementTemps());
                    c.setTn(t + c.getTr());
                }
                else if (imms.contains(c) && c.entreeImpactee()) {
                    c.externe();
                    c.setTl(t);
                    c.setE(0);
                    c.setTr(c.avancementTemps());
                    c.setTn(t + c.getTr());
                }
            }
            
            // affichage q en fontion de t 
            /*
            for(Block c : composants) {
            	if (c.getName()=="B") {
            		Buffer buf = (Buffer) c;
            		chart.addDataToSeries(t, buf.q);
            		System.out.println("q récupée : "+buf.q);
                }
            }
            */
            
            for(Block c : composants) {
            	if (c.getName()=="Adder") {
            		Adder add = (Adder) c;
            		chart.addDataToSeries(t, add.getSum());
            		System.out.println("sum récupée : "+add.getSum());
                }
            }
            
            for (Block c : composants) {
            	for(Entree in : c.entrees) {
            		in.setValue(Double.NaN);
            		in.setFlag(false);
            	}
            	
            	for(Sortie out : c.sorties)
            		out.setValue(Double.NaN);
            }
            
        }
	}  
}
