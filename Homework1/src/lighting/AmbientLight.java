package lighting;

import primitives.Color;
import primitives.Double3;
import lighting.Light;

public class AmbientLight extends Light
{
	 /**
     * Constructor
     * @param Ia intensity color
     * @param Ka constant for intensity
     */

	 public AmbientLight() {
	       super(Color.BLACK);
	 }

	 /**
	     * Constructor
	     * @param Ia intensity color
	     * @param Ka constant for intensity
	     */


	    public AmbientLight(Color Ia, Double3 Ka) {
	        super(Ia.scale(Ka));
	    }

	    public AmbientLight(Color Ia, double Ka) {

	        //_intensity=Ia.scale(Ka);
	        super(Ia.scale(Ka));
	    }

//	    public AmbientLight(Color Ia, double Kadouble) {
//	        Double3 Ka = new Double3(Kadouble);
//	    	//super(Ia.scale(Ka));
//	        
//	    }
    
    //to delete
    /**
     * get intensity color
     * @return intensity
     */
   /*public Color getIntensity() {
       return _intensity; 
    }*/

}