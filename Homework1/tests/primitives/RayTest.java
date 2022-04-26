package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RayTest {
    /**
     * testFindClosestPoint receives a list of intersections points and  return
     * the closest intersection point with the ray
     */
    @Test
    void testFindClosestPoint(){
        Ray ray=new Ray(new Point(0,0,5),new Vector(10,10,15));
        //first case : list of intersections points is empty
        List<Point> point3DList=null;
        assertNull(ray.findClosestPoint(point3DList),"error");

        //second case : closest point intersection is the first point
        point3DList=new LinkedList<>();
        point3DList.add(new Point(2,2,8));
        point3DList.add(new Point(5,5,12.5));
        point3DList.add(new Point(20,20,30));
        assertEquals(new Point(2,2,8),ray.findClosestPoint(point3DList),"error");

        //third case : closest point intersection is the last point
        List<Point> pointList1=new LinkedList<>();
        point3DList.add(new Point(5,5,12.5));
        point3DList.add(new Point(20,20,30));
        point3DList.add(new Point(2,2,8));
        assertEquals(new Point(2,2,8),ray.findClosestPoint(point3DList),"error");
    }

}