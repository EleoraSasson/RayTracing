package geometries;
import primitives.*;
import static primitives.Util.*;

/*
* The class is based on Point3D,Ray and Vector and an extends of Tube
 */
public class Cylinder extends Tube {
    final double _height;
// constructor
    public Cylinder(double height, Ray ray,double radius) {
        super(ray,radius);
        _height = height;
    }
    //return the normal of the cylinder at point p0
    public Vector getNormal(Point p0)
    {
       Point o=axisRay.getP0();
       Vector v=axisRay.getDir();

       //projection of P0 on the ray:
        double t;
        try{
            t=alignZero(p0.subtract(o).dotProduct(v));
        }catch(IllegalArgumentException e) {//P=O
            return v;
        }
        //if the point is a base
        if(t==0||isZero(_height-t))//if it's close to 0,we'll get ZERO vector exception
           return v;
        o=o.add(v.scale(t));
        return p0.subtract(o).normalize();
    }

    // return the height of the cylinder
    public double get_height() {
        return _height;
    }

}