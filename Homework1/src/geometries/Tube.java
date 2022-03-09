package geometries;
import primitives.*;
import static primitives.Util.*;

public class Tube {
    protected Ray _axisRay;
    protected double _radius;

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    //constructor that receives Ray and radius
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        if (isZero(radius))
            throw new IllegalArgumentException("radius cannot be ZERO");
        else
            _radius = radius;

    }

    public Vector getNormal(Point p0) {

        Vector P_P0 = p0.subtract(_axisRay.getP0());
        double t = _axisRay.getDir().dotProduct(P_P0);
        Point O = _axisRay.getP0().add(P_P0.scale(t));
        Vector N = p0.subtract(O);
        N.normalize();
        return N;
    }
}
