package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {
	private Vector direction;

   public SpotLight(Color intens, Point pos, Vector dir) {
       super(intens, pos);
       direction = dir.normalized();
   }

   @Override
   public Color getIntensity(Point p) {
       Color baseIntensity= super.getIntensity(p); 
       Vector l=getL(p);
       double factor=Math.max(0,direction.dotProduct(l));
       return baseIntensity.scale(factor); 
   }
 
	public PointLight setNarrowBeam(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
