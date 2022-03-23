package geometries;
import java.util.List;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.Arrays;

public class Geometries implements Intersectable{
	private List<Intersectable> intersectables;
	
	public Geometries() {
		this.intersectables = new LinkedList<>();
	}
	
	public Geometries(Intersectable...geometries) {
		intersectables = new LinkedList<>();
		add(geometries);
	}
	
	public void add(Intersectable...geometries) {
		intersectables.addAll(Arrays.asList(geometries));
	}
	
	@Override
	 public List<Point> findIntersections(Ray ray){
		 return null;
	 }
}
