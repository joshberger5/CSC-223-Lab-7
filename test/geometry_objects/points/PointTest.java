package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTest {

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
	
	@Test
	void testLexicographicOrdering() {
		Point testPoint1 = createPoint();
		Point testPoint2 = createPoint();
		
		assertEquals(0, Point.LexicographicOrdering(testPoint1, testPoint2));
		
	}

	//@Test
	//void testCompareTo() {
		//fail("Not yet implemented");
	//}

	@Test
	void testEqualsObject() {
		Point testPoint1 = createPoint();
		Point testPoint2 = createPoint();
		
		assertTrue(testPoint1.equals(testPoint2));
	}

}
