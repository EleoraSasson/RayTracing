package geometries;
import java.util.List;

import primitives.*;

public class Triangle extends Polygon{
	public Triangle(Point A, Point B, Point C) {
		super(A,B,C);
	}
	
	@Override
	 public List<Point> findIntersections(Ray ray){
		Point p0=ray.getP0();
		return findIntersections(ray);
  	}
}
