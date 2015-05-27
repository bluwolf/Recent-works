
public abstract class Pet implements Nameable{

	private String name;
	
    public void setName(String n){         
         this.name = n;
     }    
    public String getName() {
       return this.name;
    }
   public abstract String speak();
   
}
