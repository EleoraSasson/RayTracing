package geometries;
import primitives.*;

public class Sphere implements Geometry{
	private Point center;
	private double radius;

	//constructor that receives the center point and the radius
    public Sphere(Point _center, double _radius) {
        center = _center;
        if(_radius<=0)
            throw new IllegalArgumentException("radius can't be smaller than 0");
        radius = _radius;
    }
    
	 @Override
	 public Vector getNormal(Point p0){
	        Vector N=p0.subtract(center);
	        N.normalize();
	        return N;
	    }
	

}
