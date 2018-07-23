

public class GeometryDriver {
    public static void main(String [] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        
		do {
			System.out.println("1. Calculate the area of a circle:");
			System.out.println("2. Calculate the area of a rectangle:");
			System.out.println("3. Calculate the area of a triangle:");
			System.out.println("4. Quit");
			
			int choice = -1;
			
			do {
				try {
					choice = in.nextInt();
					break;
				} catch (java.lang.Exception e) { // bad practice but....
					System.out.println("Try again please...\n:: ");
					in.next();
					continue;
				}
			} while(true);
			
			switch (choice) {
				case 1: {
					System.out.println("Enter a radius:: ");
					double radius;
					do {
						try {
							radius = in.nextDouble();
							break;
						} catch (java.lang.Exception e) { // bad practice but....
							System.out.println("Try again please...\n:: ");
							in.next();
							continue;
						}
					} while(true);
					System.out.println("The area is: " + Geometry.getCircleAreaFromRadius(radius));
					break;
				}
				
				case 2: {
					double length, width;
					System.out.println("Enter a length:: ");
					do {
						try {
							length = in.nextDouble();
							break;
						} catch (java.lang.Exception e) { // bad practice but....
							System.out.println("Try again please...\n:: ");
							in.next();
							continue;
						}
					} while(true);
					
					System.out.println("Enter a width:: ");
					do {
						try {
							width = in.nextDouble();
							break;
						} catch (java.lang.Exception e) { // bad practice but....
							System.out.println("Try again please...\n:: ");
							in.next();
							continue;
						}
					} while(true);
					System.out.println("The area is: " + Geometry.getRectangleAreaFromLengthWidth(length, width));
					break;
				}
				case 3: {
					double base, height;
					System.out.println("Enter a base:: ");
					do {
						try {
							base = in.nextDouble();
							break;
						} catch (java.lang.Exception e) { // bad practice but....
							System.out.println("Try again please...\n:: ");
							in.next();
							continue;
						}
					} while(true);
					
					System.out.println("Enter a height:: ");
					do {
						try {
							height = in.nextDouble();
							break;
						} catch (java.lang.Exception e) { // bad practice but....
							System.out.println("Try again please...\n:: ");
							in.next();
							continue;
						}
					} while(true);
						System.out.println("The area is: " + Geometry.getTriangleAreaFromBaseHeight(base, height));
					break;
				}
				
				case 4:
					return;
					
				default:
				return;
			}
        } while (true);
    }
}