package geometries;
import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import primitives.*;

public class Sphere extends Geometry{
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
	 
	 
	  @Override
	    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray , double maxDistance ) {///
	        Point p0=ray.getP0();
	        Vector v=ray.getDir();

	        if(p0.equals(center)){
	            throw new IllegalArgumentException("Ray p0 cannot be equals to the center of the sphere");
	        }

	        Vector u=center.subtract(p0);

	        double tm=alignZero(v.dotProduct(u));
	        double d=alignZero(Math.sqrt(u.lengthSquared()-(tm*tm)));

	        if(d>=radius){
	            return null;
	        }

	        double th=alignZero(Math.sqrt(radius*radius-d*d));
	        double t1=alignZero(tm-th);
	        double t2=alignZero(tm+th);

	        boolean validT1=alignZero(t1-maxDistance)<=0;
	        boolean validT2=alignZero(t2-maxDistance)<=0;

	        if(t1>0 && t2>0&& validT1&&validT2){
	            Point p1=ray.getPoint(t1);
	            Point p2= ray.getPoint(t2);

	            return List.of(new GeoPoint(this,p1), new GeoPoint(this,p2));
	        }
	        if(t1>0 &&validT1){
	            Point p1= ray.getPoint(t1);

	            return List.of(new GeoPoint(this,p1));
	        }

	        if(t2>0&& validT2){
	            Point p2= ray.getPoint(t2);

	            return List.of(new GeoPoint(this,p2));
	        }
	        return null;
	    }

	  @Override
		protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
			// TODO Auto-generated method stub
			return null;
		}


	

}
