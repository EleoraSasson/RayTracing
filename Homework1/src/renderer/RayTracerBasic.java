package renderer;

import java.util.List;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

import static primitives.Util.*;

public class RayTracerBasic extends RayTracerBase {

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * RayTracerBasic constructor
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * Find the color of the closest object hit by the ray.
	 * @param ray 
	 * @return the color of the closest object hit by the ray.
	 */
	@Override
	
	  public Color traceRay(Ray ray) { 
		GeoPoint gp = findClosestIntersection(ray);
		if (gp == null) 
		{ 
			return scene.background; 
		} 
		return calcColor(gp, ray); 
		}
	 
	/**
	 * Calculate the color of a geometry intersection point by calling calcColor and adding the ambient light
	 * @param gp  - intersection point
	 * @param ray - ray that intersected the geometry
	 * @return the color of the intersection point
	 */
	public Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Calculate the color of a geometry intersection point
	 * @param gp - the intersection point
	 * @param ray - the ray that intersected the geometry
	 * @param level - the current level of recursion
	 * @param k - the current k effect coefficient for reflection and refraction
	 * @return the color of the intersection point
	 */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		if (level == 1 || k.lowerThan(MIN_CALC_COLOR_K)) {
			return Color.BLACK;
		}
		Color result = gp.geometry.getEmission().add(calcLocalEffects(gp, ray, k));
		Vector normal = gp.getNormal();
		Ray reflectedRay = constructReflectedRay(normal, gp.point, ray);
		Ray refractedRay = constructRefractedRay(normal, gp.point, ray);
		result = result.add(calcGlobalEffect(gp.geometry.getMaterial().Kr, reflectedRay, level, k));
		result = result.add(calcGlobalEffect(gp.geometry.getMaterial().Kt, refractedRay, level, k));
		// return the color
		return result;
	}

	/**
	 * Calculate a single global effect of a specific factor (reflection or refraction)
	 * @param effect - material kR for reflection or kT for refraction
	 * @param angledRay - the ray of reflection or refraction
	 * @param level - the current level of recursion
	 * @param k - the current k coefficient for reflection and refraction
	 * @return a color to add to the color of the point
	 */
	private Color calcGlobalEffect(Double3 effect, Ray angledRay, int level, Double3 k) {
		GeoPoint nextIntersection = findClosestIntersection(angledRay);
		if (nextIntersection == null) {
			return scene.background;
		}
		Double3 scaledEffect = effect.product(k);
		return calcColor(nextIntersection, angledRay, level - 1, scaledEffect).scale(effect);
	}

	/**
	 * Calculate the local lighting effects of a geometry intersection point
	 * @param intersection - of type GeoPoint
	 * @param ray - of type Ray
	 * @return a color - based on effect of local factors
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
		Vector v = ray.getDir();
		Vector n = intersection.getNormal();
		double nv = alignZero(n.dotProduct(v));
		if (isZero(nv)) {
			return Color.BLACK;
		}
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		Double3 kd = material.Kd;
		Double3 ks = material.Ks;

		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) // sign(nl) == sign(nv)
			{
				Double3 ktr = transparency(intersection, lightSource, l, n);
				if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
	}

	/**
	 * Calculates the diffuse lighting effect of a geometry intersection point
	 * @param kd - the diffuse coefficient
	 * @param l - the light vector
	 * @param n - the normal vector
	 * @param lightIntensity - the light intensity
	 * @return the diffuse lighting effect
	 */
	private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
		double nl = Math.abs(alignZero(n.dotProduct(l)));
		return lightIntensity.scale(kd.scale(nl));
	}

	/**
	 * Calculates the specular lighting effect of a geometry intersection point
	 * @param ks - the specular coefficient
	 * @param l - the light vector
	 * @param n - the normal vector
	 * @param v - the view vector
	 * @param nShininess - the shininess coefficient
	 * @param lightIntensity - the light intensity
	 * @return the specular lighting effect
	 */
	private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		double nl = alignZero(n.dotProduct(l));
		Vector r = l.subtract(n.scale(2 * nl));
		double rv = alignZero(r.dotProduct(v));
		Double3 scaledKs = ks.scale(Math.pow(-rv, nShininess));
		return scaledKs.lowerThan(0) ? lightIntensity.scale(scaledKs.scale(-1)) : lightIntensity.scale(scaledKs);
	}

	/**
	 * Calculates the transparency of the shadow of a light source onto an object
	 * @param geoPoint - geoPoint of object
	 * @param ls - the light source
	 * @param l - a vector from the light to the object
	 * @param n - the normal
	 * @return a Double3 which represents the quantity of shade of the geometry
	 */
	
	private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) { 
		Vector directionToLight = l.scale(-1); 
		Ray rayPointToLight = new Ray(geoPoint.point, directionToLight, n); 
		double lightDistance = ls.getDistance(geoPoint.point); 
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(rayPointToLight, lightDistance);
		Double3 ktr = Double3.ONE; 
		if (intersections == null) 
			return Double3.ONE; 
		for (GeoPoint gp : intersections) { 
			ktr = ktr.product(gp.geometry.getMaterial().Kt);
			// make it fully opaque if it is mostly
			if (ktr.lowerThan(MIN_CALC_COLOR_K)) { 
				return Double3.ZERO; } 
		} 
		return ktr; 
	}

	/**
	 * Constructs a refracted ray of another ray to a point
	 * @param p - Point of the object
	 * @param hittingRay - ray that hits points and is refracted
	 * @return refractedRay - the array that is refracted from the original inRay
	 */
	
	  private Ray constructRefractedRay(Vector n, Point p, Ray hittingRay) 
	  { 
		  return new Ray(p, hittingRay.getDir(), n); 
	  }
	 
	/**
	 * Constructs a reflexive ray of another ray to a point
	 * @param p - Point of the object
	 * @param hittingRay - ray that hits points and is reflected
	 * @return reflectedRay - the array that is reflected from the original inRay
	 */
	
	  private Ray constructReflectedRay(Vector n, Point p, Ray hittingRay) { 
		  double d1 = hittingRay.getDir().dotProduct(n); 
		  if(isZero(d1)) { return null; } 
		  Vector v1 = n.scale(d1 * 2); 
		  Vector newV = hittingRay.getDir().subtract(v1); 
		  return new Ray(p,newV, n); 
	  }
	 
	/**
	 * Method that receives a ray and finds a list of intersections of the ray with
	 * geometries and returns the closest of those intersections
	 * @param ray - the ray to find the closest intersection for
	 * @return GeoPoint - a geoPoint that is from the geometry intersected by the ray first
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> lst = scene.geometries.findGeoIntersections(ray);
		if (lst == null)
			return null;
		return ray.findClosestGeoPoint(lst);
	}
}
