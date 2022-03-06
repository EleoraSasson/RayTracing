package primitives;

public class Point {
	
	protected Double3 xyz;
		
	public Point(double x, double y, double z) {
		xyz = new Double3(x,y,z);
	}
	
	public Point(Double3 doub) {
		xyz = doub;
	}
	
	@Override
	public String toString() {
		return this.xyz.toString();
	}

	public Point add(Vector b)
	{
		return new Point(this.xyz.add(b.xyz));
	}
	
	public Vector subtract(Point b)
	{
		return new Vector(this.xyz.subtract(b.xyz));
	}
	
	public double distanceSquared(Point b)
	{
		double u1 = (this.xyz.d1-b.xyz.d1)*(this.xyz.d1-b.xyz.d1);
		double u2 = (this.xyz.d2-b.xyz.d2)*(this.xyz.d2-b.xyz.d2);
		double u3 = (this.xyz.d3-b.xyz.d3)*(this.xyz.d3-b.xyz.d3);
		return u1 + u2 + u3;
	}
	
	public double distance(Point b)
	{
		return Math.sqrt(distanceSquared(b));
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
