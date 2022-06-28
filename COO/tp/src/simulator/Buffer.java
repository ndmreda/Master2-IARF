package simulator;

import chart.Chart;
import chart.ChartFrame;

public class Buffer extends Block {
    protected Etat a, b, c;
    protected Sortie req;
    protected Entree job, done;
    protected double q = 0;


    public Buffer(String name, double tb) {
    	super(name, tb);
        a = new Etat("a", Double.POSITIVE_INFINITY);
        b = new Etat("b", 0);
        c = new Etat("c", Double.POSITIVE_INFINITY);
        
        job = new Entree("job", "bool");
        done = new Entree("done", "bool");
        req = new Sortie("req", "bool");
        
        etats.put("a", a);
        etats.put("b", b);
        etats.put("c", c);
        
        entrees.add(job);
        entrees.add(done);
        sorties.add(req);
        initial = a;

    }

    @Override
    public void externe() {
        if (courant == a && job.getFlag()) {
            q++;
            courant = b;
        }
        if (courant == c) {
             if (done.getFlag()) {
                if (q == 0) {
                    courant = a;
                } else {
                    courant = b;
                }
            }else if (job.getFlag()) {
                q++;

            }

        }
        

        System.out.println("q = "+q +"\n");
    }

    @Override
	public void interne() {
        if (courant == b) {
            q--;
            courant = c;

        }
        System.out.println("q = "+q +"\n");

    }

	@Override
    public void lambda() {
        if (courant == b) {
            req.setValue(1);
        }
    }

	public Etat getA() {
		return a;
	}

	public void setA(Etat a) {
		this.a = a;
	}

	public Etat getB() {
		return b;
	}

	public void setB(Etat b) {
		this.b = b;
	}

	public Etat getC() {
		return c;
	}

	public void setC(Etat c) {
		this.c = c;
	}

	public Sortie getReq() {
		return req;
	}

	public void setReq(Sortie req) {
		this.req = req;
	}

	public Entree getJob() {
		return job;
	}

	public void setJob(Entree job) {
		this.job = job;
	}

	public Entree getDone() {
		return done;
	}

	public void setDone(Entree done) {
		this.done = done;
	}

	public double getQ() {
		return q;
	}

	public void setQ(double q) {
		this.q = q;
	}

    
}
