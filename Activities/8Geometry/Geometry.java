

public class Geometry {
	public static final double MATH_PI = 3.14159;
	
	public static double getCircleAreaFromRadius(double radius) {
		if (!checkParam(radius)) return 0;
		return Geometry.MATH_PI * radius * radius;
	}
	
	public static double getRectangleAreaFromLengthWidth(double length, double width) {
		if (!checkParam(length) && !checkParam(width)) return 0;
		return length * width;
	}
	
	public static double getTriangleAreaFromBaseHeight(double base, double height) {
		if (!checkParam(base) && !checkParam(height)) return 0;
		return 0.5 * base * height;
	}
	
	private static boolean checkParam(double param) {
		if (param < 0) {
			System.err.print("ERROR: param not positive");
			return false;
		}
		return true;
	}
	
}