package simulator;

public class Entree implements Observateur{
    protected String name;
    protected String type;
    protected double value = 0;
    protected Boolean flag = Boolean.FALSE;

    public Entree(String name, String type){
        this.name = name;
        this.type = type;

    }

    @Override
    public void update(Observable observable, double value) {
		//System.out.println("Update input ...");
        this.value = value;
        this.setFlag(Boolean.TRUE);
        return;
        }
    
    public void setName(String name) {
        this.name = name;
    } 

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public Boolean getFlag(){
        return flag;
    }

    
    public double getValue() {
		return value;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	


}
