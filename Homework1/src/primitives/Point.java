package primitives;

public class Point {
	
	public static final Point ZERO = new Point(0, 0, 0);
	public Double3 xyz;
		
	/**
	 *Constructor 
	 * 
	 * @param 3 doubles
	 * @return new Point
	 */
	public Point(double x, double y, double z) {
		xyz = new Double3(x,y,z);
	}
	
	/**
	 *Constructor 
	 * 
	 * @param Double3
	 * @return new Point
	 */
	public Point(Double3 doub) {
		xyz = doub;
	}
	
	@Override
	public String toString() {
		return this.xyz.toString();
	}

	/**
	 *Adds a vector to the Point 
	 * 
	 * @param Vector
	 * @return new Point
	 */
	public Point add(Vector b)
	{
		return new Point(this.xyz.add(b._head));
	}
	
	/**
	 *Subtracts a Point from a Vector 
	 * 
	 * @param Point
	 * @return new Vector
	 */
	public Vector subtract(Point b)
	{
		return new Vector(this.xyz.subtract(b.xyz));
	}
	
	/**
	 *Returns the squared distance between 2 points
	 * 
	 * @param Point
	 * @return double
	 */
	public double distanceSquared(Point b)
	{
		double u1 = (this.xyz.d1-b.xyz.d1)*(this.xyz.d1-b.xyz.d1);
		double u2 = (this.xyz.d2-b.xyz.d2)*(this.xyz.d2-b.xyz.d2);
		double u3 = (this.xyz.d3-b.xyz.d3)*(this.xyz.d3-b.xyz.d3);
		return u1 + u2 + u3;
	}
	
	/**
	 *Returns the distance between 2 points
	 * 
	 * @param Point
	 * @return double
	 */
	public double distance(Point b)
	{
		return Math.sqrt(distanceSquared(b));
	}
	
	public double getX() {
		return xyz.getX();
	}

	public double getY() {
		return xyz.getY();
	}

	public double getZ() {
		return xyz.getZ();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	
}
