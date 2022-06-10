package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene scene ;

    /**
     * constructor
     * @param s
     */
    public RayTracerBase(Scene s) {
        if(s==null)
            throw new IllegalArgumentException("Error");
        this.scene = s;
    }
    public abstract Color traceRay (Ray ray);
}