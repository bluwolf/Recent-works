
public class Triangle extends TwoD {
	private double base;
	private double height;

	public Triangle( String n, double b, double h ) {
		super(n);
		base = b;
		height = h;
		}
	
	public double getArea() {
		return 0.5 * base * height;
	}
}
