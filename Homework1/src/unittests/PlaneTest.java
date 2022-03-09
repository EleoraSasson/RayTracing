package unittests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;

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

}