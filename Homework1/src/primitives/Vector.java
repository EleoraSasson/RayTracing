package primitives;

public class Vector extends Point{	
	
	public Vector(double d1, double d2, double d3) throws IllegalArgumentException {
		
		super(d1,d2,d3);
		
	        if (this.equals(xyz.ZERO)) {
	            throw new IllegalArgumentException("Zero vector is illegal");
	        }
	}
	    	
	
	public Vector(Double3 doub) throws IllegalArgumentException {
		
		super(doub.d1, doub.d2, doub.d3);	

	        if (this.equals(xyz.ZERO)) {
	            throw new IllegalArgumentException("Zero vector is illegal");
	        }
	}
	
	public Vector(Point p) throws IllegalArgumentException {
		
		super(p.xyz.d1, p.xyz.d2, p.xyz.d3);	

	        if (this.equals(xyz.ZERO)) {
	            throw new IllegalArgumentException("Zero vector is illegal");
	        }
	}
	
	public Double3 getXYZ()
	{
		return xyz;
	}
	
	public Vector add(Vector u) {
		return new Vector(this.xyz.add(u.xyz));
	}
	
	public Vector scale(double a) {
		return new Vector(this.xyz.scale(a));
	}
	
	public double dotProduct(Vector u)
	{
		return this.xyz.d1*u.xyz.d1 + this.xyz.d2*u.xyz.d2 + this.xyz.d3*u.xyz.d3;
	}
	
	
	public Vector crossProduct(Vector u)
	{
		double newX = this.xyz.d2*u.xyz.d3 - this.xyz.d3*u.xyz.d2;
		double newY = this.xyz.d3*u.xyz.d1 - this.xyz.d1*u.xyz.d3;
		double newZ = this.xyz.d1*u.xyz.d2 - this.xyz.d2*u.xyz.d1;
		
		return new Vector(newX, newY, newZ);
	}
	
	public double lengthSquared()
	{
		double X = this.xyz.d1, Y = this.xyz.d2, Z = this.xyz.d3;
		
		return X*X + Y*Y + Z*Z;
	}
	
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	
	public Vector normalize()
	{
		return new Vector(this.xyz.reduce(this.length()));
	}
}
