package unittests;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



class SphereTest {
    @Test
    void constructor()
    {
        // ============ Equivalence Partitions Tests ==============
        try {
            new Sphere(new Point(0, 1, 0), 2);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct sphere");
        }

        // =============== Boundary Values Tests ==================

        // Test radius ZERO
        try {
            new Sphere(new Point(0, 1, 0), 0);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct sphere");
        }

        // Test negative radius
        try {
            new Sphere(new Point(0, 1, 0), -4);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct sphere");
        }
    }
        @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============;
        Sphere sp=new Sphere(new Point(0,0,0),4);
        Vector N=sp.getNormal(new Point(2,2,2));
        Vector V=new Vector(0.5773502691896258,0.5773502691896258,0.5773502691896258);
        assertEquals(N,V);
    }
}