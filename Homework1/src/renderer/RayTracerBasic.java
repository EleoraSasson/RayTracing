package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import elements.AmbientLight;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTraceBase {
    public RayTracerBasic(Scene scene) {
		super(scene);
	}

    //bc we need to inherit that method for now
	@Override
	public Color traceRay(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public Color calcColor(Point point, Ray ray) {
	        //return  _scene.ambientLight.getIntensity().add(point.geometry.getEmmission());
	        return _scene.ambientLight.getIntensity();

	    }
}
