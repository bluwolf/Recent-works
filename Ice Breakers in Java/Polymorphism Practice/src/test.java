
public class test {
 public static void main( String[] args){
	 Pet p1 = new Cat();
//	 Nameable n1 = new Cat();
//	 System.out.println("");
	 if(p1 instanceof Nameable){
		 System.out.println("I'm a Nameable.");
	 }
	 if(p1 instanceof Pet){
		 System.out.println("I'm a cat pet.");
	 }
	 //System.out.println(p1.speak());
	 Pet p2 = new Dog();
	 if(p2 instanceof Nameable){
		 System.out.println("I'm a Nameable.");
	 }
	 if(p2 instanceof Pet){
		 System.out.println("I'm a dog pet.");
	 }
	 //System.out.println(p2.speak());
	 
 }
}
