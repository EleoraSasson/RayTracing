package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;
import primitives.Double3;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String _name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight(new Color(192, 192, 192), new Double3(1,1,1));
    public Geometries geometries= new Geometries();
	


    /**
     * constructor that creates a scene
     * @param name
     */
    public Scene(String name) {
        _name = name;
        geometries = new Geometries();
    }

    //setters

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
       // this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}