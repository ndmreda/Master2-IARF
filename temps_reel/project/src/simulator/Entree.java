package simulator;

public class Entree extends Port implements Observateur{
    protected Boolean flag;

    public Entree(String name){
    	super(name);
        this.flag = Boolean.FALSE;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public double getValue() {
		return value;
	}
    
    public Boolean getFlag(){
        return flag;
    }

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	

    @Override
    public void update(Observable observable, double value) {
        this.value = value;
        this.setFlag(Boolean.TRUE);
        }
}