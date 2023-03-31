package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PointNamingFactoryTest {

	@Test
	void testPointNamingFactory() {
		// checks that the default constructor makes an empty PointNamingFactory
		PointNamingFactory f = new PointNamingFactory();
		
		assertEquals(0, f.size());
	}

	@Test
	void testPointNamingFactoryListOfPoint() {
		// makes a list with 10 points in it
		List<Point> points = new ArrayList<Point>();
		
		for (int i = 0; i < 10; i++) {
			points.add(new Point("" + (char) (65+i), i, i));
		}
		
		// makes sure the constructor makes a PointNamingFactory with 10 Points
		PointNamingFactory f = new PointNamingFactory(points);
		
		assertEquals(10, f.size());
	}

	@Test
	void testPutPointNull() {
		// checks that put(Point pt) handles null input
		PointNamingFactory f = new PointNamingFactory();
		
		assertNull(f.put(null));
	}
	
	@Test
	void testPutPointWithName() {
		// checks that put(Point pt) returns the same Point
		// and the size of the factory increases by 1
		PointNamingFactory f = new PointNamingFactory();
		
		Point r = f.put(new Point("ABC", 1.05, 1.05));
		
		assertEquals("ABC", r.getName());
		assertEquals(1.05, r.getX());
		assertEquals(1.05, r.getY());
		assertEquals(1, f.size());
	}
	
	@Test
	void testPutPointWithNullName() {
		// checks that put(Point p) returns a Point
		// with a constructed name when it is passed
		// a Point without a name
		// and the size of the factory increases by 1
		PointNamingFactory f = new PointNamingFactory();
		
		Point r = f.put(new Point(null, 1, 1));
		
		assertEquals("*_A", r.getName());
		assertEquals(1, r.getX());
		assertEquals(1, r.getY());
		assertEquals(1, f.size());
	}

	@Test
	void testPutDoubleDouble() {
		// fail("Not yet implemented");
	}

	@Test
	void testPutStringDoubleDouble() {
		// fail("Not yet implemented");
	}

	@Test
	void testGetDoubleDouble() {
		// fail("Not yet implemented");
	}

	@Test
	void testGetPoint() {
		// fail("Not yet implemented");
	}

	@Test
	void testContainsDoubleDouble() {
		// fail("Not yet implemented");
	}

	@Test
	void testContainsPoint() {
		// fail("Not yet implemented");
	}

	@Test
	void testGetAllPoints() {
		// fail("Not yet implemented");
	}

	@Test
	void testClear() {
		// fail("Not yet implemented");
	}

	@Test
	void testSize() {
		// fail("Not yet implemented");
	}

	@Test
	void testToString() {
		// fail("Not yet implemented");
	}

}
