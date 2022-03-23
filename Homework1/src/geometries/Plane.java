package geometries;
import java.util.List;
import static primitives.Util.isZero;
import primitives.*;

public class Plane implements Geometry{
	private Point q0;
	private Vector normal;

	//constructor that receives 3 points and do a plane from these 3 points
    public Plane(Point p0, Point p1, Point p2) {
        if((p0.equals(p1)||(p1.equals(p2))||(p0.equals(p2))))
            throw new IllegalArgumentException();
        q0 = p0;
        Vector U=p1.subtract(p0);
        Vector V=p2.subtract(p0);
        Vector N= U.crossProduct(V);
        N.normalize();
        normal = N;
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
	        return normal;
	 }
	 
	 public Vector getNormal() {
		 return normal;
	 }
	 
	 @Override
	 public List<Point> findIntersections(Ray ray){
		 Point p0=ray.getP0();
	        Vector v=ray.getDir();
	        double t=(normal.dotProduct(q0.subtract(p0)))/(normal.dotProduct(v));
	        if((p0.equals(q0)) && !(isZero(normal.dotProduct(v)))){
	            Point p= q0;

	            return List.of(p);
	        }

	        if(t>0){
	            Point p= ray.getPoint(t);

	            return List.of(p);
	        }
	        return null;
	 }

}
