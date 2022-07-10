package primitives;

import static primitives.Double3.ZERO;

public class Vector {
    public Double3 _head;

    /**
     * constructor checks if the receive point is the point (0,0,0) if so it throws exception
     * @param head
     */

    public Vector(Double3 head) {
        if (head.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0");
        }
        _head = head;
    }

    /**
     * constructor checks if the receive point is the point (0,0,0) if so it throws exception
     *
     * @param x 1st number
     * @param y 2nd number
     * @param z 3nd number
     */
    public Vector(double x, double y, double z) {
        Double3 p0=new Double3(x,y,z);
        if (p0.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0");
        }
        _head=p0;
    }

    /**
     * @param v
     * @return u1 * v1 + u2 * v2 + u3 * v3
     */
    public double dotProduct(Vector v) {
        double u1 = _head.d1;
        double u2 = _head.d2;
        double u3 = _head.d3;
        double v1 = v._head.d1;
        double v2 = v._head.d2;
        double v3 = v._head.d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);


    }

    /**
     * @param v
     * @return the orthogonal vector to vector v and the vector on which the operation is performed
     */
    public Vector crossProduct(Vector v) {
    	double u1 = _head.d1;
        double u2 = _head.d2;
        double u3 = _head.d3;
        double v1 = v._head.d1;
        double v2 = v._head.d2;
        double v3 = v._head.d3;

       Double3 newhead=new Double3(
                u2*v3-u3*v2,
                u3*v1-u1*v3,
                u1*v2-u2*v1

        );
       if(newhead.equals(ZERO)){
           throw new IllegalArgumentException("cross product resulting zero point head");
       }
       return new Vector(newhead);
    }

    /**
     * @param v
     * @return the addition of 2 vectors
     */
    public Vector add(Vector v){
        return new Vector(new Double3(
               _head.d1 +v._head.d1,
               _head.d2 +v._head.d2,
               _head.d3 +v._head.d3

        ));
    }

    /**
     * @param v
     * @return the substraction of 2 vectors
     */
    public Vector subtract(Vector v){
        if (v.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector(0,0,0)");
        }

        return new Vector(new Double3(
        		 _head.d1 -v._head.d1,
                 _head.d2 -v._head.d2,
                 _head.d3 -v._head.d3

        ));
    }

    /**
     * @param d
     * @return the multiplication of the vector on which the operation is performed and number d
     */
    public Vector scale(double d){
        return new Vector(new Double3(
                d*_head.d1,
                d*_head.d2,
                d*_head.d3

        ));
    }

    //return the vector length squared
    public double lengthSquared(){
        return (_head.d1)*(_head.d1)+(_head.d2)*(_head.d2)+(_head.d3)*(_head.d3);
    }

    //return the vector length
    public double length(){
        return Math.sqrt(this.lengthSquared());

    }

    //return the vector on which the operation is performed normalized
    public Vector normalize(){

        double x=_head.d1;
        double y=_head.d2;
        double z=_head.d3;
        double l=this.length();
        _head=new Double3(x/l,y/l,z/l);
        return this;

    }

    //return new vector normal from the vector on which the operation is performed
    public Vector normalized(){
//        return new Vector(new Double3(
//                _head.d1 /this.length(),
//                _head.d2 /this.length(),
//                _head.d3 /this.length()
//
//        ));
    	return new Vector(this._head).normalize();
    }


    public Double3 getHead() {
        return _head;
    }
    
    public double getX() {
    	return _head.d1;
    }
    
    public double getY() {
    	return _head.d2;
    }
    
    public double getZ() {
    	return _head.d3;
    }
    
    /**
     * @param o
     * @return true if the vector received and the vector on which the operation is performed are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(((Vector) o)._head);
//        Objects.equals(_head, vector._head);

    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }

}