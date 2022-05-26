package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;
import geometries.Intersectable.GeoPoint;

public class Ray {
	private Point p0;
	private Vector dir;
	 public static final double  DELTA=0.1;
	
	/**
	 *Constructor that returns a "null ray"
	 * 
	 * @param empty
	 * @return Ray
	 */
	public Ray() {
		super();
		this.p0 = null;
		this.dir = null;
	}

	/**
	 *Constructor 
	 * 
	 * @param Point, Vector
	 * @return Ray
	 */
	public Ray(Point p0, Vector dir) {
		super();
		this.p0 = p0;
		this.dir = dir.normalize();
	}

	public Ray (Point point, Vector n, Vector direction)
	{
	    Vector delta=n.scale(n.dotProduct(direction)>0? DELTA: -DELTA);
	    p0=point.add(delta);
	    dir=direction;
	}
	
	/**
	 *Getter for P0 
	 * 
	 * @param empty
	 * @return p0
	 */
	public Point getP0() {
		return p0;
	}
	
	/**
	 *Getter for dir 
	 * 
	 * @param empty
	 * @return dir
	 */
		public Vector getDir() {
		return dir;
	}
	
	public Point getPoint(double t) {
		Point p= p0.add(dir.scale(t));
        return p;
	}

	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dir, p0);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}
	
	/**
     * find the closest point to ray origin
     * @param pointsList intersections point List
     * @return closest point
     */


	public Point findClosestPoint(List<Point> points) {
	    return points == null || points.isEmpty() ? null
	           : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}
    
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList)
    {
        GeoPoint result=null;
        double closestDistance=Double.MAX_VALUE;

        if(geoPointList==null){
            return null;
        }
        for(GeoPoint geo:geoPointList){
            double temp=geo.point.distance(p0);
            if(temp<closestDistance){
                closestDistance=temp;
                result=geo;
            }
        }
        return result; 
    }
}
