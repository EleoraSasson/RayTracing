package geometries;
import primitives.*;

public class Plane implements Geometry{
	private Point q0;
	private Vector normal;

	public Plane(Point PointA, Point PointB, Point PointC) 
    {
        this.q0 = PointA;
        normal = null;

    }
	
	public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

	public Point getQ0() {
		return q0;
	}

	@Override
	public String toString() {
		return "Plane [q0=" + q0 + ", normal=" + normal + "]";
	}
	
	 @Override
	 public Vector getNormal(Point p) {
	        return null;
	 }
	 
	 public Vector getNormal() {
		 return null;
	 }
}
