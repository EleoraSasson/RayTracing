package geometries;
import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;

public class Geometries extends Intersectable{
	private List<Intersectable> intersectables;
	
	public Geometries() {
		this.intersectables = new LinkedList<>();
	}
	
	public Geometries(Intersectable...geometries) {
		this();
		add(geometries);
	}
	
	public void add(Intersectable...geometries) {
		Collections.addAll(intersectables, geometries);

	}
	
/*
 * 
 * JV - I TOOK IT OUT HERE AND PUT IT INTO INTERSECTABLE 
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double distance) {
		List<GeoPoint> result= null;

        //we pass on all geometries intersectables we check if there is points of intersection. If there is any, we add intersection point in a list
        for (Intersectable item : intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersectionsHelper(ray,distance);///
            if(itemPoints!=null){
                if(result==null){
                    result=new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }

        return result;
	}
*/
	
	/*
	 * JV CHANGING FROM NULL TO REAL method
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/
	 @Override
	    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double distance) {
	        List<GeoPoint> intersections = null;
	        for (var geometry : intersectables) {
	            var tempIntersections = geometry.findGeoIntersections(ray, distance);
	            if (tempIntersections != null) {
	                if (intersections == null)
	                    intersections = new LinkedList<>();
	                intersections.addAll(tempIntersections);
	            }
	        }
	        return intersections;
	    }
// JV ADDED TO CODE	
	 @Override
	    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
	        List<GeoPoint> intersections = null;
	        for (var geometry : intersectables) {
	            var tempIntersections = geometry.findGeoIntersections(ray);
	            if (tempIntersections != null) {
	                if (intersections == null)
	                    intersections = new LinkedList<>();
	                intersections.addAll(tempIntersections);
	            }
	        }
	        return intersections;
	    }

}
