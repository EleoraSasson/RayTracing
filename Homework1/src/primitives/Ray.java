package primitives;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in
 * Cartesian 3-Dimensional coordinate system
 * 
 * @author Jonah Lawrence
 * @author Elad Harizy
 */
public class Ray {

  private static final double DELTA = 0.1;

  /**
   * ray p0
   */
  public final Point p0;

  /**
   * ray direction vector
   */
  public final Vector direction;

  /**
   * Constructor that takes the p0 and direction
   * 
   * @param p0    the p0 point
   * @param direction the direction vector
   */
  public Ray(Point p0, Vector direction) {
    this.p0 = p0;
    this.direction = direction.normalized();
  }

  /**
   * constructor that moves point by a constant delta in a certain direction
   * 
   * @param p0    the p0 point
   * @param direction the direction vector
   * @param normal    normal vector for displacement direction
   */
  public Ray(Point point, Vector direction, Vector normal) {
    this.direction = direction.normalized();
    double dotProduct = normal.dotProduct(this.direction);
    Vector delta = normal.scale(dotProduct >= 0 ? DELTA : -DELTA);
    this.p0 = point.add(delta);
  }

  /**
   * Get p0
   * 
   * @return p0
   */
  public Point getP0() {
    return this.p0;
  }

  /**
   * Get direction
   * 
   * @return direction
   */
  public Vector getDir() {
    return this.direction;
  }

  /**
   * Get the point after scaling the direction vector from the p0
   * 
   * @param t The scalar to multiply by
   * @return The point scaled by the direction vector
   */
  public Point getPoint(double t) {
    return p0.add(direction.scale(t));
  }

  /**
   * find the closest Point to Ray p0
   * 
   * @param pointsList intersections point List
   * @return closest point
   */
  public GeoPoint findClosestGeoPoint(List<GeoPoint> pointsList) {
    GeoPoint result = null;
    double closestDistance = Double.MAX_VALUE;
    if (pointsList == null) {
      return null;
    }
    for (GeoPoint p : pointsList) {
      double temp = p.point.distanceSquared(p0);
      if (temp < closestDistance) {
        closestDistance = temp;
        result = p;
      }
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Ray)) {
      return false;
    }
    Ray ray = (Ray) o;
    return Objects.equals(p0, ray.p0) && Objects.equals(direction, ray.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p0, direction);
  }

  @Override
  public String toString() {
    return String.format("{ p0: %s, Direction: %s }", this.p0.toString(), this.direction.toString());
  }

  public Point findClosestPoint(List<Point> points) {
	    return points == null || points.isEmpty() ? null
	           : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}
  


}