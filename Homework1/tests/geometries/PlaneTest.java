package geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.*;
import java.util.List;

class PlaneTest {

    @Test
    void constructor()
    {
        // ============ Equivalence Partitions Tests ==============
        try {
            new Plane(new Point(0,0,-1),new Point(2,0,3),new Point(0,-1,2));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

        // =============== Boundary Values Tests ==================
        // test 2 points equal
        try {
            new Plane(new Point(2,0,3),new Point(2,0,3),new Point(0,-1,2));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }
    }

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p=new Plane(new Point(4,2,-1),new Point(1,3,1),new Point(-3,0,3));
        Vector N=p.getNormal(new Point(8,-2,13));
        assertEquals(N,new Vector(0.5196558419693047,-0.12991396049232617,0.8444407432001202));
    }
    
    @Test
    void findIntersections() {
        //test 1 point of intersection
        Plane p=new Plane(new Point(0,2,0),new Point(0,0,2),new Point(0,-2,0));
        List<Point> result1 =p.findIntersections(new Ray(new Point(-1.5, 0, 0),
                new Vector(0, 1, 0.5)));
        assertEquals(1, result1.size(),"Wrong number of points");

        //test 0 point of intersection
        assertNull(p.findIntersections(new Ray(new Point(-2,1,0),
                        new Vector(-3,0,0))),
                "Ray's line out of triangle");


        //test 1 point of intersection and p0 of ray in on the limit of the triangle
        List<Point> result2 =p.findIntersections(new Ray(new Point(0, 0, 0),
                new Vector(-3,0,0)));
        assertEquals(1, result1.size(),"Wrong number of points");
    }

}