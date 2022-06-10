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

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

}
