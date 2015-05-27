
public class Rectangle extends TwoD {
	protected double width;
	protected double length;
	
	public Rectangle( String n, double w, double l ) {
		super(n);
		width = w;
		length = l;
	}
	
	public double getArea() {
		return width * length;
	}
}
