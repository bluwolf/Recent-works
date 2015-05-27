
public class ShapeTest {

	public static void printShape( Shape s ) {
		System.out.print( "Shape name is:" + s );
		System.out.println( "Shape area is: " + s.getArea() );
	}
	
	public static void main( String[] args ) {
		//Shape sh = null;
		Shape sh[] = new Shape[10];
		
		sh[0] = new Triangle( "triangle", 5, 4 );
		//printShape( sh[0] );
		
		sh[1] = new Rectangle( "rectangle", 3, 2 );
		//printShape( sh[1] );
		
		sh[2] = new Square( "square", 5 );
		//printShape( sh[2] );
		
		for (int i=0;i<3;i++){
			printShape(sh[i]);
		}
	}
}
