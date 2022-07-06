package geometries;
import java.util.List;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;
import primitives.*;

public class Plane extends Geometry{
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
	

	 public Vector getNormal() {
		 return normal;
	 }
	 
	 @Override
	 public Vector getNormal(Point p) {
	        return normal;
	 }
	 
	 
	 
	 @Override
	 public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
	        Point p0 = ray.getP0();
	        Vector v = ray.getDir();
	        if (isZero(normal.dotProduct(v))) {
	            return null;
	        }
	        if ((p0.equals(q0)) && !(isZero(normal.dotProduct(v)))) {
	            return null;
	        }
	        double t = (normal.dotProduct(q0.subtract(p0))) / (normal.dotProduct(v));


	        if (t > 0&&(alignZero(t-maxDistance)<=0))
	        {
	            Point p = ray.getPoint(t);

	            return List.of(new GeoPoint(this, p));
	        }
	        return null;
	    }

	 @Override
	  public List<GeoPoint> findGeoIntersections(Ray ray) {
	    try {
	      // direction from ray's origin to a point on the plane
	      Vector u = q0.subtract(ray.getP0());
	      // distance from ray's origin to the point
	      double t = normal.dotProduct(u) / normal.dotProduct(ray.getDir());
	      // return the point if it is reached by the ray
	      if (t > 0 && !Double.isInfinite(t)) {
	        return List.of(new GeoPoint(this, ray.getPoint(t)));
	      }
	    } catch (IllegalArgumentException e) {
	      // ray has origin on the plane
	    }
	    return null;
	  }

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
