
/**
 * Contains a class for managing Coordinates. One of the big issues was the 
 * fact that deciding when to use XY or RC coordinate and converting between
 * them was confusing and annoying, so we created this class to alleviate 
 * thinking
 *
 * @author Aryan Gupta
 * @version 1.0
 */
public class Coordinate {
	/** 
	 * Used to argument passing and deciding what type of
	 * data was passed in
	 */
	public enum CoordType {
		RC, XY
	}
	
	private int x; /// x coordinate
	private int y; /// y coordinate
	
	/** 
	 * Default c'tor. Creates an empty coordinate at 0, 0
	 */
	public Coordinate() {
		set(CoordType.XY, 0, 0);
	}
	
	/** 
	 * Main c'tor. Creates an coordinate based on parameters passed in
	 *
	 * @param CoordType type - The type of coordinate passed in
	 * @param int a - X or R (depending on \p type)
	 * @param int b - Y or C (depending on \p type)
	 */	
	public Coordinate(CoordType type, int a, int b) {
		set(type, a, b);
	}

	/** 
	 * Sets the coordinate based on parameters passed in
	 *
	 * @param CoordType type - The type of coordinate passed in
	 * @param int a - X or R (depending on \p type)
	 * @param int b - Y or C (depending on \p type)
	 */	
	public void set(CoordType type, int a, int b) {
		switch(type) {
			case RC: {
				x = b;
				y = a;
				return;
			}
			
			case XY: {
				x = a;
				y = b;
				return;
			}
		}
	}
	
	/** 
	 * Adds da and db to the coordinate based on parameters passed in
	 *
	 * @param CoordType type - The type of data passed in
	 * @param int a - X or R (depending on \p type) to add to this
	 * @param int b - Y or C (depending on \p type) to add to this
	 */	
	public Coordinate add(CoordType type, int da, int db) {
		switch(type) {
			case RC: return new Coordinate(CoordType.XY, x + db, y + da);
			case XY: return new Coordinate(CoordType.XY, x + da, y + db);
		}
		return null;
	}
	
	/** 
	 * Gets the X coordinate
	 *
	 * @return int - The X coordinate
	 */	
	public int getX() {
		return x;
	}

	/** 
	 * Gets the Y coordinate
	 *
	 * @return int - The Y coordinate
	 */	
	public int getY() {
		return y;
	}


	/** 
	 * Gets the R coordinate
	 *
	 * @return int - The R coordinate
	 */	
	public int getR() {
		return y;
	}
	

	/** 
	 * Gets the C coordinate
	 *
	 * @return int - The C coordinate
	 */
	public int getC() {
		return x;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}