/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;

/**
 * @authors nessya&eleora
 *
 */
class PointTest {
	
	 Point p1 = new Point(1, 2, 3);
	 Point p2 = new Point(-2, -4, -6);


	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		assertEquals(0, p1.distanceSquared(p2));
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		 Point p3= new Point(0,0,2.4);
	        double result=p3.distance(p2);
	        assertEquals(1.5,result,0.1);
	}

}
