package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
	 private  Point position;
	 private double Kc=1d;
	 private double Kl=0d;
	 private double Kq=0d;
	 
	 public PointLight(Color intensity,Point pos) {
	        super(intensity);
	        position = pos;
	    }

	    public PointLight setKc(double kc) {
	        Kc = kc;
	        return this;
	    }

	    public PointLight setKl(double kl) {
	        Kl = kl;
	        return this;
	    }

	   	public PointLight setKq(double kq) {
	        Kq = kq;
	        return this;
	    }
	    
	   
	    @Override
	    public Color getIntensity(Point p) {
	        double d=p.distance(position);
	        double attenuation=1d/(Kc+Kl*d+Kq*d*d);
	        return intensity.scale(attenuation);

	    }

	    @Override
	    public Vector getL(Point p) {
	        return p.subtract(position).normalized();
	    }
	    
	    @Override
	    public double getDistance(Point point)
	    {
	        return point.distance(position);
	    }
	    
	   
}
