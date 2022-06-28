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
	
	public void affichage(ArrayList<Block> composants,ArrayList<Chart> lines,double t,int part) {
		
		if(t == Double.POSITIVE_INFINITY)
			t=this.tfin;
		if(part == 1) {
			int i = 0;
			for(Chart s : lines) {
				if(i==0) {
            		Buffer buf = (Buffer) composants.get(i);
            		s.addDataToSeries(t, buf.q);
				}
				i++;
			}
		}
		
		else if(part==2) {
			int i = 0;
			for(Chart s: lines) {
				if(i<=3) {
					Step step = (Step) composants.get(i);
	        		s.addDataToSeries(t,step.sortie.value);
				}
				else if(i==4) {
					Adder add = (Adder) composants.get(i);
	        		s.addDataToSeries(t,add.sum);
				}
				i++;
			}
		}
		else if(part==3) {
			int i = 0;
			for(Chart s: lines) {
				if(i<=3) {
					Step step = (Step) composants.get(i);
	        		s.addDataToSeries(t,step.sortie.value);
				}
				else if(i==4) {
					Adder add = (Adder) composants.get(i);
	        		s.addDataToSeries(t,add.sum);
				}
				else if(i==5) {
					Integrator integ = (Integrator) composants.get(i);
	        		s.addDataToSeries(t,integ.value);
				}
				i++;
			}
		}
		else if (part == 4) {
			int i = 0;
			for(Chart s: lines) {
				if(i<=3) {
					Step step = (Step) composants.get(i);
	        		s.addDataToSeries(t,step.sortie.value);
				}
				else if(i==4) {
					Adder add = (Adder) composants.get(i);
	        		s.addDataToSeries(t,add.sum);
				}
				else if(i==5) {
					IntegratorDiscretEvent integ = (IntegratorDiscretEvent) composants.get(i);
	        		s.addDataToSeries(t,integ.value);
				}
				i++;
			}
		}
		
		else if (part == 5) {
			int i = 0;
			for(Chart s: lines) {
				if(i==0) {
					Constante cst = (Constante) composants.get(i);
	        		s.addDataToSeries(t,cst.x);
				}
				else if(i==1) {
					IntegratorDiscretEvent IDE2 = (IntegratorDiscretEvent) composants.get(i);
	        		s.addDataToSeries(t,IDE2.value);
				}
				else if(i==2) {
					IntegratorDiscretEvent IDE1 = (IntegratorDiscretEvent) composants.get(i);
	        		s.addDataToSeries(t,IDE1.value);
				}
				i++;
			}
		}
	}
	
	public void ordonnancer(int part) {
       
        ArrayList<Chart> lines = new ArrayList<>();
		ChartFrame chartFrame = new ChartFrame("simulator","frame");
        double t = 0;
        
        for (Block c : composants) {
    		c.initialiser();
    		c.setTr(c.avancementTemps());
    	}
		
		for(Block c: composants) {
			Chart Ch = new Chart(c.getName());
			chartFrame.addToLineChartPane(Ch);
			lines.add(Ch);
		}
             
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
     
            t = t + trmin;

            
            for (Block c : composants) {
                c.setE(c.getE() + trmin);
                c.setTr(c.getTr() - trmin);
            }

            for (Block c : imms)
                c.lambda();

           //Pour la liste des entrées impactées on a utilisé le design pattern Observateur 

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
            affichage(composants,lines, t,part);         
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
	
	public void ordonnancer2(int part) {

        double t = 0;
        
        for (Block c : composants) {
    		c.initialiser();
    		c.setTr(c.avancementTemps());
    	}
        
        ArrayList<Chart> lines = new ArrayList<>();
		ChartFrame chartFrame = new ChartFrame("simulator", "frame");
		
		for(Block c: composants) {
			Chart Ch = new Chart(c.getName());
			chartFrame.addToLineChartPane(Ch);
			lines.add(Ch);
		}
             
        while (t < tfin) {
        	
            ArrayList<Block> imms = new ArrayList<>();
            ArrayList<Double> trs = new ArrayList<>();
            
            double trmin = Double.POSITIVE_INFINITY;
            
            double coef = 0.7;

            double h = ((IntegratorDiscretEvent) composants.get(2)).getValue();
            double v = ((IntegratorDiscretEvent) composants.get(1)).getValue();

            if (h <= 0 && v<0) {
                v = coef * v * (-1);
                ((IntegratorDiscretEvent) composants.get(1)).setValue(v);
            }
            
            for(Block c : composants) {
    			trs.add(c.getTr());
    		}
            
            trmin = Collections.min(trs);
            
            for (Block c : composants) {
    			if(trmin == c.getTr())
    				imms.add(c);
    		}
     
            t = t + trmin;

            
            for (Block c : composants) {
                c.setE(c.getE() + trmin);
                c.setTr(c.getTr() - trmin);
            }

            for (Block c : imms)
                c.lambda();

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
                    System.out.println("ok");
                    c.setTl(t);
                    c.setE(0);
                    c.setTr(c.avancementTemps());
                    c.setTn(t + c.getTr());
                }
            }
            affichage(composants,lines, t,part);         
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