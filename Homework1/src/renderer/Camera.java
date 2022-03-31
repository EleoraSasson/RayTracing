package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static primitives.Util.isZero;

public class Camera {
	Point p0;
	Vector vTo;
	Vector vUp;
	Vector vRight;
	
	//View Plane
	double width;
	double height;
	double distance;
	
	public Camera(Point P0, Vector VTo, Vector VUp) {
		  p0 = P0;
	      vTo = VTo.normalize();
	      vUp = VUp.normalize();
	      if(vTo.dotProduct(vUp)==0){
	            throw new IllegalArgumentException("vUp is not orthogonal to vTo ");
	      }
	      vRight=vTo.crossProduct(vUp);
	}
	
	public Camera setVPSize(double Width, double Height){
        width=Width;
        height=Height;
        return this;
    }
	
	public Camera setVPDistance(double Distance) {
		distance = Distance;
		return this;
	}
	
	public Ray constructRay(int nX, int nY,int j, int i)
	{
		Point Pc=p0.add(vTo.scale(distance));

		double Ry=height/nY;
		double Rx=width/nX;

		double Yi=-(i-(nY-1)/2)*Ry;
		double Xj=-(j-(nX-1)/2)*Rx;

		Point Pij;
		if(isZero(Yi) && isZero(Xj)){
			return new Ray(p0,Pc.subtract(p0));
		}

		Vector deltaX=vRight.scale(Xj);
		Vector deltaY=vUp.scale(Yi);

		Pij=Pc.add(deltaX.add(deltaY));
		Vector Vij=Pij.subtract(p0);

		return new Ray(p0,Vij);
	}

	public Point getP0() {
		return p0;
	}

	public Vector getVTo() {
		return vTo;
	}

	public Vector getVUp() {
		return vUp;
	}

	public Vector getVRight() {
		return vRight;
	}
	
	
	
	
	
	

}
