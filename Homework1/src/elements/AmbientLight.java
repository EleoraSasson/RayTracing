package elements;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

	private Color _intensity;

    /**
     * Constructor
     * @param Ia intensity color
     * @param Ka constant for intensity
     */

	 public AmbientLight() {
	       _intensity=Color.BLACK;
	 }

    public AmbientLight(Color Ia, Double3 Ka) {

        _intensity=Ia.scale(Ka);
    }

    /**
     * get intensity color
     * @return intensity
     */
   public Color getIntensity() {
       return _intensity;
    }

}