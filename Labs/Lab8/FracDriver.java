
public class FracDriver {
	public static void main() {
		Fraction a = new Fraction(8, 16);
		Fraction b = new Fraction(1, 2);
		Fraction c = new Fraction(1, 4);
		
		
		System.out.println("a=" + a);
		System.out.println("b+c=" + b.add(c));
		System.out.println("b-c=" + b.subtract(c));
		System.out.println("b*c=" + b.multiply(c));
		System.out.println("a*b=" + a.multiply(b));
		System.out.println("b/c=" + b.divide(c));
		System.out.println("a<=>b=" + a.compareTo(b));
		System.out.println("a<=>c=" + a.compareTo(c));
		System.out.println("a==b=" + a.equals(b));
		System.out.println("a=" + a.getIrrational());
		System.out.println("b=" + b.getIrrational());
		System.out.println("c=" + c.getIrrational());
		
	}
}