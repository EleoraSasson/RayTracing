package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
	 
	 public  List<Point> findIntersections(Ray ray) {
		 List<GeoPoint> geoList = findGeoIntersections(ray);
		 return geoList == null ? null
		 : geoList.stream().map(gp -> gp.point). toList();
		 }
	 
	//public abstract List<GeoPoint> findGeoIntersections(Ray ray, double distance);
	
	 // JV - ADDED INTO THEIR CODE
	 public final List<GeoPoint> findGeoIntersections(Ray ray, double distance) {
			return findGeoIntersectionsHelper(ray, distance);
		}
	 
	 public List<GeoPoint> findGeoIntersections(Ray ray)
	{
	        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}
	 
	public static class GeoPoint {
		public Geometry geometry;
		public Point point;

		public GeoPoint(Geometry geometry, Point point) {
			super();
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			return Objects.equals(geometry, other.geometry) && Objects.equals(point, other.point);
		}

		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}

		public Vector getNormal() {
			return geometry.getNormal(point);
		}
	}
// JV????
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);



}
