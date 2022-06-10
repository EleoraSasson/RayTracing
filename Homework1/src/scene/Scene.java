package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

public class Scene {  

    public static String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight;
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor
     */
    public Scene(String n) {
        this.name = n;
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    /**
     * setter for list of lights
     */
    public Scene setLights(List<LightSource> ls) {
        lights = ls;
        return this;
    }

    /**
     * Set the background color
     */
    public Scene setBackground(Color color) {
        background = color;
        return this;
    }

    /**
     * Set the ambient light
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Set the geometries
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
