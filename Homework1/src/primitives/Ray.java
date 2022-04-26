package primitives;

import java.util.List;
import java.util.Objects;

public class Ray {
	private Point p0;
	private Vector dir;
	
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

    public Point findClosestPoint(List<Point> pointsList){
    	Point result=null;
        double closestDistance=Double.MAX_VALUE;

        if(pointsList==null){
            return null;
        }
        for(Point p:pointsList){
            double temp=p.distance(p0);// temp is the distance between a point in the list and the _p0 of the ray
            if(temp<closestDistance){
                closestDistance=temp;// closestDistance is the minimum temp
                result=p;
            }
        }
        return result; //result is the closest point to the ray

    }
}
