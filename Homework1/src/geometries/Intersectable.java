package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
	 //public abstract List<Point> findIntersections(Ray ray);
	 
	 public final List<Point> findIntersections(Ray ray) {
		 List<GeoPoint> geoList = findGeoIntersections(ray);
		 return geoList == null ? null
		 : geoList.stream().map(gp -> gp.point). toList();
		 }
	 
	//public List<GeoPoint> findGeoIntersections(Ray ray ,double maxdistance);
	
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
	}

	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
	}

}
