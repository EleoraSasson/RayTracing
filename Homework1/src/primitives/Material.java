//package primitives;
//
///**
// * this class contains coefficients Kd, Ks and nShininess
// * Kt is transparency coefficent
// */
//
//public class Material {
//	public Double3 Kd;
//	public Double3 Ks;
//	public Double3 Kt;
//    public Double3 Kr;
//	public int nShininess;
//
//	public Material() {
//		this.setKd(0).setKs(0).setKt(0).setKr(0).setShininess(0);
//	}
//	
//	/**
//	 * setter
//	 * 
//	 * @param Kd
//	 * @return Material object
//	 */
//	public Material setKd(double Kd) {
//		Kd = new Double3(Kd);
//		return this;
//	}
//
//	/**
//	 * setter
//	 * 
//	 * @param Ks
//	 * @return Material object
//	 */
//
//	public Material setKs(double Ks) {
//		Ks = new Double3(Ks);
//		return this;
//	}
//	
//	/**
//     * we set the Kr coefficient and return the object material
//     * @param Kr
//     * @return Material that we are working on
//     */
//        public Material setKr(double Kr) {
//        Kr = new Double3(Kr);
//        return this;
//    }
//
//        public Material setKt(double Kt) {
//            this.Kt = new Double3(Kt);
//            return this;
//        }
//        
//	/**
//	 * setter
//	 * 
//	 * @param nShininess
//	 * @return Material object
//	 */
//	public Material setShininess(int nShininess) {
//		this.nShininess = nShininess;
//		return this;
//	}
//}

package primitives;

/**
 * Class Material is the basic class representing a geometry's material
 * 
 * @author Jonah Lawrence
 * @author Elad Harizy
 */
public class Material {

  /**
   * diffuse
   */
  public Double3 Kd;

  /**
   * specular
   */
  public Double3 Ks;

  /**
   * transparency
   */
  public Double3 Kt;

  /**
   * reflection
   */
  public Double3 Kr;

  /**
   * shininess
   */
  public int nShininess;

  /**
   * constructor
   * 
   * @param Kd
   * @param Ks
   * @param Kr
   * @param Kt
   * @param shininess
   */
  public Material(double Kd, double Ks, double Kr, double Kt, int shininess) {
    this.Kd = new Double3(Kd);
    this.Ks = new Double3(Ks);
    this.Kr = new Double3(Kr);
    this.Kt = new Double3(Kt);
    this.nShininess = shininess;
  }

  /**
   * default constructor
   */
  public Material() {
    this(0, 0, 0, 0, 0);
  }

  /**
   * set Kd
   * 
   * @param Kd
   */
  public Material setKd(double Kd) {
	  this.Kd = new Double3(Kd);
	  return this;
  }

  /**
   * set Ks
   * 
   * @param Ks
   */
  public Material setKs(double Ks) {
    this.Ks = new Double3(Ks);
    return this;
  }

  /**
   * set Kr
   * 
   * @param Kr
   */
  public Material setKr(double Kr) {
    this.Kr = new Double3(Kr);
    return this;
  }

  /**
   * set Kt
   * 
   * @param Kt
   */
  public Material setKt(double Kt) {
    this.Kt = new Double3(Kt);
    return this;
  }

  /**
   * set shininess
   * 
   * @param shininess
   */
  public Material setShininess(int shininess) {
    this.nShininess = shininess;
    return this;
  }
}