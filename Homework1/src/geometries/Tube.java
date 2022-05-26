package geometries;
import primitives.*;
import static primitives.Util.*;

public class Tube {
    protected Ray axisRay;
    protected double radius;

    public Ray getaxisray() {
        return axisRay;
    }

    public double getrad() {
        return radius;
    }

    //constructor that receives Ray and radius
    public Tube(Ray axisray, double rad) {
        axisRay = axisray;
        if (isZero(rad))
            throw new IllegalArgumentException("rad cannot be ZERO");
        else
            radius = rad;

    }

    public Vector getNormal(Point p0) {

        Vector P_P0 = p0.subtract(axisRay.getP0());
        double t = axisRay.getDir().dotProduct(P_P0);
        Point O = axisRay.getP0().add(P_P0.scale(t));
        Vector N = p0.subtract(O);
        N.normalize();
        return N;
    }
}
