package renderer;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import lighting.AmbientLight;
import lighting.LightSource;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTraceBase {

	private static final double DELTA = 0.1;

	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	// bc we need to inherit that method for now
	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);

		if (intersections != null) {
			GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
			return calcColor(closestPoint, ray);
		}
		return _scene.background;
	}

	public Color calcColor(GeoPoint gpoint, Ray ray) {
		if (gpoint == null)
			return (Color.BLACK);
		return _scene.ambientLight.getIntensity().add(gpoint.geometry.getEmission()).add(calcLocalEffects(gpoint, ray));
	}

//	  private Color calcLocalEffects(GeoPoint gpoint, Ray ray) {
//	        Vector v = ray.getDir();
//	        Vector n = gpoint.geometry.getNormal(gpoint.point);
//	        double nv = alignZero(n.dotProduct(v));
//	        if (nv == 0) {
//	            return Color.BLACK;
//	        }
//	        return Color.BLACK;
//	  }
	private Color calcLocalEffects(GeoPoint gpoint, Ray ray) {
		Vector v = ray.getDir();
		Vector n = gpoint.geometry.getNormal(gpoint.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) {
			return Color.BLACK;
		}
		Color color = Color.BLACK;
		Material material = gpoint.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.Kd;
		double ks = material.Ks;
		for (LightSource lightSource : _scene.lights) {
			Vector l = lightSource.getL(gpoint.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				//if (unshaded(lightSource, l, n, gpoint)) {
					Color lightIntensity = lightSource.getIntensity(gpoint.point);
					
					color = color.add(gpoint.geometry.getEmission());

					color = color.add(gpoint.geometry.getEmission());

				}
			}
		//}
		return color;
	}
	/* *//**
			 * this function return true if the point received is unshaded and return false
			 * if the point is shaded
			 * 
			 * @param lightSource
			 * @param l
			 * @param n
			 * @param geoPoint
			 * @return a boolean response
			 *//*
				 * private boolean unshaded(LightSource lightSource, Vector l, Vector n,
				 * GeoPoint geoPoint) { Vector lightDirection = l.scale(-1);//from point to
				 * light Ray lightRay = new Ray(geoPoint.point, n, lightDirection);// we create
				 * a ray from the point on the object to the light source List<GeoPoint>
				 * intersections = _scene.geometries .findGeoIntersectionsHelper(lightRay,
				 * lightSource.getDistance(geoPoint.point));
				 * 
				 * if (intersections == null) return true;// we check if it exists an object
				 * between the point on the object and the light, if not the point is unshaded
				 * double lightDistance = lightSource.getDistance(geoPoint.point); for (GeoPoint
				 * gp : intersections) { if (alignZero(gp.point.distance(geoPoint.point) -
				 * lightDistance) <= 0 && gp.geometry.getMaterial().Kt == 0)// we check if there
				 * is an object1 between the light and the point on the object2 received and
				 * that the object1 is opaque return false; // we return false because the point
				 * on object2 is shaded } return true;
				 * 
				 * 
				 * }
				 */

}