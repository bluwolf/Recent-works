
public abstract class Shape { // class also must be abstract if functions in it are abstract
	protected String name;
	
	public Shape( String n ) {
		name = n;
	}
	
	public String toString() {
		return name;
	}
	
	public abstract double getArea(); // abstract method are similar to virtual functions
}
