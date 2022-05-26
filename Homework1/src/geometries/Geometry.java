package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

public abstract class Geometry extends Intersectable{
	protected Color emission = Color.BLACK;
	protected Material _material = new Material();

	/**
     * getter
     * @return Material object
     */
    public Material getMaterial() {
        return _material;
    }
    
    /**
     * this function receives Material and return geometry with this Material
     * @param material
     * @return
     */
    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }
    
	public Color getEmission() {
		return emission;
	}
	
	
	
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}



	public abstract Vector getNormal(Point p);
}
