package primitives;

/**
 * this class contains coefficients kd, ks and nShininess
 * kt is transparency coefficent
 */

public class Material {
	public Double3 Kd = new Double3(0,0,0);
	public Double3 Ks = new Double3(0,0,0);
	public Double3 Kt= new Double3(0,0,0);
    public Double3 Kr = new Double3(0,0,0);
	public int nShininess = 0;

	/**
	 * setter
	 * 
	 * @param kd
	 * @return Material object
	 */
	public Material setKd(double kd) {
		Kd = new Double3(kd);
		return this;
	}

	/**
	 * setter
	 * 
	 * @param ks
	 * @return Material object
	 */

	public Material setKs(double ks) {
		Ks = new Double3(ks);
		return this;
	}
	
	/**
     * we set the kr coefficient and return the object material
     * @param kr
     * @return Material that we are working on
     */
        public Material setKr(double kr) {
        Kr = new Double3(kr);
        return this;
    }

	/**
	 * setter
	 * 
	 * @param nShininess
	 * @return Material object
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}