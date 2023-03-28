/**
 * This class tests the Point class and its methods
 * 
 * @author Maherah
 * @date 03-28-2023
 */
package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTest {

	/**
	 * Methods creating points that are called in tests
	 * @return Points created
	 */
	public Point createPoint() {
		Point point1 = new Point("test point 1", 2.0, 3.0);
		return point1;
	}
	public Point createPoint2() {
		Point point2 = new Point("test point 2", 6.5, 3.5);
		return point2;
	}
	
	public Point createPoint3() {
		Point point3 = new Point("test point 3", 1.2, 5.8);
		return point3;
	}
	
	public Point createPoint4() {
		Point point4 = new Point("test point 4", 2.2, 1.2);
		return point4;
	}
	
	public Point createPoint5() {
		Point point5 = new Point("test point 5", 8.1, 9.5);
		return point5;
	}
	
	/**
	@Test
	void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	void testGetX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetY() {
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	void testIsGenerated() {
		fail("Not yet implemented");
	}

	@Test
	void testPointDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testPointStringDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testIsUnnamed() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	 * Tests the LexicographicOrdering method
	 */
	@Test
	void testLexicographicOrdering() {
		//testing equal points
		Point testPoint1 = createPoint();
		Point testPoint2 = createPoint();
		assertEquals(0, Point.LexicographicOrdering(testPoint1, testPoint2));
		
		//testing points where p1's x value is greater than p2's x value
		Point testPoint1XGreater = createPoint();
		Point testPoint2XLess = createPoint3();
		
		assertEquals(1, Point.LexicographicOrdering(testPoint1XGreater, testPoint2XLess));
		
		//testing points where p1's x value is less than p2's x value
		Point testPoint1XLess = createPoint4();
		Point testPoint2XGreater = createPoint2();
		
		assertEquals(-1, Point.LexicographicOrdering(testPoint1XLess, testPoint2XGreater));
		
		//testing points where p1's y value is greater than p2's y value
		Point testPoint1YGreater = createPoint2();
		Point testPoint2YLess = createPoint4();
		
		assertEquals(1, Point.LexicographicOrdering(testPoint1YGreater, testPoint2YLess));
		
		//testing points where p1's y value is less than p2's y value
		Point testPoint1YLess = createPoint3();
		Point testPoint2YGreater = createPoint5();
		
		assertEquals(-1, Point.LexicographicOrdering(testPoint1YLess, testPoint2YGreater));
		
	}

	/**
	 * Tests the compareTo method
	 */
	@Test
	void testCompareTo() {
		//testing equal points
		Point testPoint1 = createPoint();
		Point testPoint2 = createPoint();
		assertEquals(0, testPoint1.compareTo(testPoint2));
		
		//testing points where p1's x value is greater than p2's x value
		Point testPoint1XGreater = createPoint();
		Point testPoint2XLess = createPoint3();
		
		assertEquals(1, testPoint1XGreater.compareTo(testPoint2XLess));
		
		//testing points where p1's x value is less than p2's x value
		Point testPoint1XLess = createPoint4();
		Point testPoint2XGreater = createPoint2();
		
		assertEquals(-1, testPoint1XLess.compareTo(testPoint2XGreater));
		
		//testing points where p1's y value is greater than p2's y value
		Point testPoint1YGreater = createPoint2();
		Point testPoint2YLess = createPoint4();
		
		assertEquals(1, testPoint1YGreater.compareTo(testPoint2YLess));
		
		//testing points where p1's y value is less than p2's y value
		Point testPoint1YLess = createPoint3();
		Point testPoint2YGreater = createPoint5();
		
		assertEquals(-1, testPoint1YLess.compareTo(testPoint2YGreater));
		
		//testing with a null
		Point testPointCompareWithNull = createPoint();
		
		assertEquals(1, testPointCompareWithNull.compareTo(null));
	}

	/**
	 * Tests the equals method
	 */
	@Test
	void testEqualsObject() {
		//Testing two equal points
		Point testPoint1 = createPoint();
		Point testPoint2 = createPoint();
		
		assertTrue(testPoint1.equals(testPoint2));
		
		//testing points where p1's y value is less than p2's y value
		Point testPoint1YLess = createPoint3();
		Point testPoint2YGreater = createPoint5();
		
		assertFalse(testPoint1YLess.equals(testPoint2YGreater));
		
		//testing points where p1's y value is greater than p2's y value
		Point testPoint1YGreater = createPoint2();
		Point testPoint2YLess = createPoint4();
		
		assertFalse(testPoint1YGreater.equals(testPoint2YLess));
		
		//testing points where p1's x value is less than p2's x value
		Point testPoint1XLess = createPoint4();
		Point testPoint2XGreater = createPoint2();
		
		assertFalse(testPoint1XLess.equals(testPoint2XGreater));
		
		//testing points where p1's x value is greater than p2's x value
		Point testPoint1XGreater = createPoint();
		Point testPoint2XLess = createPoint3();
		
		assertFalse(testPoint1XGreater.equals(testPoint2XLess));
	}

}
