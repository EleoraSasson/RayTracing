package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeometriesTest {

    @Test
    void findIntersections() {
        Geometries geos = new Geometries();
        geos.add(new Sphere(new Point(1, 1, 1), 5));
        geos.add(new Triangle(new Point(1, 1, 1), new Point(5, 0, 0), new Point(2.38, -1.77, 3.9)));
        assertNull(geos.findIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1))));

        List<Point> result1 = geos.findIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1)));
        assertEquals(2, result1.size(), "Wrong number of points");

        //test empty list
        Geometries geos2 = new Geometries();
        assertNull(geos2.findIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1))));

        //test 0 points of intersection
        assertNull(geos.findIntersections(new Ray(new Point(-4, 0, 0), new Vector(-5, 0, 0))));

        //test point of intersection with 1 figure
        List<Point> result2 = geos.findIntersections(new Ray(new Point(-1, -3, 0), new Vector(-1, 5, 0)));
        assertEquals(2, result1.size(), "Wrong number of points");

        //test intersection with some figures
        geos.add(new Triangle(new Point(-2.5, 3, 1), new Point(0, 2, 0), new Point(-3, -1, -1)));
        List<Point> result3 = geos.findIntersections(new Ray(new Point(-1, -3, 0), new Vector(-1, 5, 0)));
        assertEquals(3, result1.size(), "Wrong number of points");

        //test intersection with all figures
        geos2.add(new Sphere(new Point(1, 1, 1), 5));
        geos2.add(new Triangle(new Point(-2.5, 3, 1), new Point(0, 2, 0), new Point(-3, -1, -1)));
        List<Point> result4 = geos.findIntersections(new Ray(new Point(-1, -3, 0), new Vector(-1, 5, 0)));
        assertEquals(3, result1.size(), "Wrong number of points");
    }
}