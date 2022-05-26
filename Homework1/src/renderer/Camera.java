package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static primitives.Util.isZero;

import java.util.MissingResourceException;

import primitives.Color;

public class Camera {
	Point p0;
	Vector vTo;
	Vector vUp;
	Vector vRight;
	
	//View Plane
	double width;
	double height;
	double distance;
	ImageWriter _imageWriter;
    RayTraceBase _rayTracerBase;

	  public Camera setImageWriter(ImageWriter imageWriter) {
	        _imageWriter = imageWriter;
	        return this;
	    }
	  /*public Camera setCamera(Camera camera) {
	        _camera = camera;
	        return this;
	    }*/

	    public Camera setRayTracer(RayTraceBase rayTracer) {
	        _rayTracerBase = rayTracer;

	        return this;
	    }
	    
	    //constructor
	public Camera(Point P0, Vector VTo, Vector VUp) {
		  p0 = P0;
	      vTo = VTo.normalize();
	      vUp = VUp.normalize();
	      if (!isZero(vTo.dotProduct(vUp))) {
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
	
	 /**
     * constructor that creates a ray for every pixel and return the color of every pixel
      */
	 public Camera renderImage() {
	        //we check that all fields are not null
	        try {
	        	
	            if (_imageWriter == null) {
	                throw new MissingResourceException("missing ressource", ImageWriter.class.getName(), "");
	            }
	            if (_rayTracerBase == null) {
	                throw new MissingResourceException("missing ressource", RayTraceBase.class.getName(), "");
	            }

	            //rendering the image
	            int nX= _imageWriter.getNx();
	            int nY= _imageWriter.getNy();
	            for(int i=0;i<nY;i++) {
	                for (int j = 0; j < nX; j++) {
	                    Ray ray = constructRayThroughPixel(nX, nY, j, i);
	                    Color pixelColor=_rayTracerBase.traceRay(ray);
	                    _imageWriter.writePixel(j,i,pixelColor);
	                }
	            }

	        }catch(MissingResourceException e){
	            throw new UnsupportedOperationException("Not implemented yet "+e.getClassName());
	        }
	        return this;
	    }
    
        
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
//      double rX=vpWidth/nX;
//      double rY=vpHeight/nY;
//      double xJ=(j-(nX-1)/2d)*rX;
//      double yIminus=(i-(nY-1)/2d)*rY;
//
//      Point3D pIJ=_p0.add(_vTo.scale(vpDistance))
      Point Pc = p0.add(vTo.scale(distance));

      double Ry = height / nY;
      double Rx = width / nX;

      double Yi = -(i - (nY - 1) / 2d) * Ry;
      double Xj = (j - (nX - 1) / 2d) * Rx;

      Point Pij = Pc;
//    if(Xj!=0){
//        Pij=Pij.add(_vRight.scale(Xj));
//    }
//    if(Yi!=0){
//        Pij=Pij.add(_vUp.scale(Yi));
//    }

      if (isZero(Yi) && isZero(Xj)) {
          return new Ray(p0, Pij.subtract(p0));
      }

      if (isZero(Xj)) {
          Pij = Pc.add(vUp.scale(Yi));
          return new Ray(p0, Pij.subtract((p0)));
      }

      if (isZero(Yi)) {
          Pij = Pc.add(vRight.scale(Xj));
          return new Ray(p0, Pij.subtract((p0)));
      }

      Pij = Pc.add(vRight.scale(Xj).add(vUp.scale(Yi)));
      return new Ray(p0, Pij.subtract(p0));


  }
    
	public void printGrid(int interval, Color color) {

        if(_imageWriter==null){
            throw new MissingResourceException("empty field","ImageWriter","_imageWriter");
        }
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                        _imageWriter.writePixel(j, i, color);
                }
            }
        }
	}
        /**
         * constructor checks if the imagewriter is null else call the constructor
         */
        public void writeToImage(){
            if(_imageWriter==null){
                throw new MissingResourceException("empty field","ImageWriter","_imageWriter");
            }
            _imageWriter.writeToImage();

        }
    }