package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;
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
    RayTracerBase _rayTracerBase;
    
    
    //****************************//
    public enum SUPERSAMPLING_TYPE {
        NONE, SUPERSAMPLING
      }

      /**
       * whether or not supersampling is enabled
       */
      private SUPERSAMPLING_TYPE supersamplingType = SUPERSAMPLING_TYPE.NONE;

      /**
       * number of rows and columns for supersampling
       */
      private int supersamplingGridSize = 9;
      
      
      
      /**
       * Cast ray from camera in order to color a pixel
       * 
       * @param nX          resolution on X axis (number of pixels in row)
       * @param nY          resolution on Y axis (number of pixels in column)
       * @param pixelWidth  camera width / number of pixels wide
       * @param pixelHeight camera height / number of pixels high
       * @param col         pixel's column number (pixel index in row)
       * @param row         pixel's row number (pixel index in column)
       */
      private void castRay(int nX, int nY, double pixelWidth, double pixelHeight, int col, int row) {
        Ray ray = constructRayThroughPixel(nX, nY, col, row);
        Color pixelColor;

        if (supersamplingType == SUPERSAMPLING_TYPE.SUPERSAMPLING) {
          pixelColor = calcSupersamplingColor(ray, supersamplingGridSize);
        }
        // no supersampling
        else {
          pixelColor = _rayTracerBase.traceRay(ray);
        }
        _imageWriter.writePixel(col, row, pixelColor);
      }
      
      /**
       * Get the average color of rays for supersampling
       * 
       * @param middleRay ray for original pixel location
       * @param gridSize  number of rows and columns for dividing pixel
       * @return supersampling average color
       */
      private Color calcSupersamplingColor(Ray middleRay, int gridSize) {
        double pixelWidth = width / _imageWriter.getNx();
        double pixelHeight = height / _imageWriter.getNy();
        // list for returning rays
        List<Ray> supersamplingRays = constructSupersamplingRays(middleRay, gridSize, pixelWidth, pixelHeight);
        // add the intersected colors together
        Color pixelColor = Color.BLACK;
        for (Ray r : supersamplingRays) {
          pixelColor = pixelColor.add(_rayTracerBase.traceRay(r));
        }
        // divide by the number of rays
        return pixelColor.reduce(supersamplingRays.size());
      }
      
      /**
       * set supersampling to NONE, SUPERSAMPLING, or ADAPTIVE
       * 
       * @param type supersampling type
       * @return Render object
       */
      public Camera setSupersamplingType(SUPERSAMPLING_TYPE type) {
        this.supersamplingType = type;
        return this;
      }

      /**
       * set supersampling grid size
       * 
       * @param gridSize number of rows/cols
       * @return Render object
       */
      public Camera setSupersamplingGridSize(int gridSize) {
        this.supersamplingGridSize = gridSize;
        return this;
      }
      
      /**
       * construct an equidistant grid of rays through a pixel
       * 
       * @param center      ray for original pixel location
       * @param gridSize    number of rows and columns for dividing pixel
       * @param pixelWidth  width of pixel
       * @param pixelHeight height of pixel
       * @return rays
       */
      public List<Ray> constructSupersamplingRays(Ray center, int gridSize, double pixelWidth, double pixelHeight) {
          // get spacing amount based on grid size
          double spacingVertical = pixelHeight / (gridSize + 1);
          double spacingHorizontal = pixelWidth / (gridSize + 1);
          // position of top left ray intersection with view plane
          Point topLeft = center.getPoint(distance).add(vRight.scale(-pixelWidth / 2 - spacingHorizontal))
                  .add(vUp.scale(pixelHeight / 2 - spacingVertical));
          return constructGridOfRays(topLeft, gridSize, spacingVertical, spacingHorizontal);
      }
      
      /**
       * construct a grid of rays from a given point
       * 
       * @param topLeft           position of top left ray intersection
       * @param gridSize          number of rows and columns for dividing pixel
       * @param spacingVertical   distance from one pixel to another vertically
       * @param spacingHorizontal distance from one pixel to another horizontally
       * @return rays
       */
      public List<Ray> constructGridOfRays(Point topLeft, int gridSize, double spacingVertical,
              double spacingHorizontal) {
          List<Ray> rays = new ArrayList<>();
          // create grid of rays for supersampling
          for (int row = 0; row < gridSize; row++) {
              for (int col = 0; col < gridSize; col++) {
                  Point newPoint = topLeft;
                  if (row > 0) {
                      newPoint = newPoint.add(vUp.scale(-row * spacingVertical));
                  }
                  if (col > 0) {
                      newPoint = newPoint.add(vRight.scale(col * spacingHorizontal));
                  }
                  rays.add(constructRayThroughPoint(newPoint));
              }
          }
          return rays;
      }
      
      /**
       * Gets parameters that define the view plane matrix, and the index of the pixel
       * on it, and returns a ray from the place point of the camera through the pixel
       * 
       * @param point point to construct ray through
       * @return Ray from the camera through the point
       */
      public Ray constructRayThroughPoint(Point point) {
          return new Ray(p0, point.subtract(p0));
      }






//*******************************//

	  public Camera setImageWriter(ImageWriter imageWriter) {
	        _imageWriter = imageWriter;
	        return this;
	    }
	  /*public Camera setCamera(Camera camera) {
	        _camera = camera;
	        return this;
	    }*/

	    public Camera setRayTracer(RayTracerBase rayTracer) {
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
	                throw new MissingResourceException("missing ressource", RayTracerBase.class.getName(), "");
	            }

	            //rendering the image
	            int nX= _imageWriter.getNx();
	            int nY= _imageWriter.getNy();
	            
	            /*********************/
	            double pixelWidth = width / _imageWriter.getNx();
                double pixelHeight = height / _imageWriter.getNy();
                
	            
	            if(supersamplingType ==SUPERSAMPLING_TYPE.SUPERSAMPLING) {
	            	 for (int i = 0; i < nY; ++i)
	                   for (int j = 0; j < nX; ++j)
	                     castRay(nX, nY, pixelWidth, pixelHeight, j, i);
	               }
	            
	        /*****************************/
	            else {
	            for(int i=0;i<nY;i++) {
	                for (int j = 0; j < nX; j++) {
	                    Ray ray = constructRayThroughPixel(nX, nY, j, i);
	                    Color pixelColor=_rayTracerBase.traceRay(ray);
	                    _imageWriter.writePixel(j,i,pixelColor);
	                }
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