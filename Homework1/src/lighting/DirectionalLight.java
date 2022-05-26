package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
	private Vector direction;
	
	 public DirectionalLight(Color intensity, Vector dir) {
	        super(intensity);
	        direction = dir.normalized();
	    }
	 
	   @Override
	    public Color getIntensity(Point p) {
	        return intensity;
	    }

	    @Override
	    public Vector getL(Point p) {
	        return direction.normalized();
	    }

		@Override
		public double getDistance(Point p) {
			// TODO Auto-generated method stub
			return 0;
		}
}
