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
		
		Point r = f.put(new Point(null, -7.02834, -9.01289));
		
		assertEquals("*_A", r.getName());
		assertEquals(-7.02834, r.getX());
		assertEquals(-9.01289, r.getY());
		assertEquals(1, f.size());
	}

	@Test
	void testPutDoubleDouble() {
		// checks that put(double x, double y) returns a Point
		// with a constructed name
		// and the factory size increases by 1
		PointNamingFactory f = new PointNamingFactory();
		
		Point r = f.put(0.91223, 2.3208);
		
		assertEquals("*_A", r.getName());
		assertEquals(0.91223, r.getX());
		assertEquals(2.3208, r.getY());
		assertEquals(1, f.size());
	}

	@Test
	void testPutStringDoubleDoubleNullName() {
		// checks that put(String name, double x, double y)
		// returns a Point with a constructed name
		// when null is passed for the name
		// and the factory size increases by 1
		PointNamingFactory f = new PointNamingFactory();
		
		Point r = f.put(null, 5.281903, 1.18723);
		
		assertEquals("*_A", r.getName());
		assertEquals(5.281903, r.getX());
		assertEquals(1.18723, r.getY());
		assertEquals(1, f.size());
	}
	
	@Test
	void testPutStringDoubleDoubleNonNullName() {
		// checks that put(String name, double x, double y)
		// returns a Point with the passed in name
		// and the factory size increases by 1
		PointNamingFactory f = new PointNamingFactory();
		
		Point r = f.put("ASDF", 6.230498, 3.180239);
		
		assertEquals("ASDF", r.getName());
		assertEquals(6.230498, r.getX());
		assertEquals(3.180239, r.getY());
		assertEquals(1, f.size());
	}

	@Test
	void testGetDoubleDoubleInDB() {
		// checks that you are returned
		// the correct point
		// when you call get with the coordinates
		// of a Point in the Factory
		PointNamingFactory f = new PointNamingFactory();
		
		Point r = f.put("QWERTY", 0.7234, 8.90123); 
		Point g = f.get(0.7234, 8.90123);
		
		assertEquals("QWERTY", g.getName());
		assertEquals(0.7234, g.getX());
		assertEquals(8.90123, g.getY());
	}
	
	@Test
	void testGetDoubleDoubleNotInDB() {
		// checks that you are returned null
		// when you call get with the coordinates
		// of a Point not in the Factory
		PointNamingFactory f = new PointNamingFactory();
		
		assertNull(f.get(0.7, 8.9));
	}

	@Test
	void testGetPointNull() {
		// checks that you are returned null
		// when you call get with null
		PointNamingFactory f = new PointNamingFactory();
		
		assertNull(f.get(null));
	}
	
	@Test
	void testGetPointInDB() {
		// checks that you are returned the correct Point
		// when you call get with a Point in the factory
		PointNamingFactory f = new PointNamingFactory();
		
		Point p = f.put("fruit salad", 1.7234, 6.987123); 
		Point g = f.get(p);
		
		assertEquals("fruit salad", g.getName());
		assertEquals(1.7234, g.getX());
		assertEquals(6.987123, g.getY());
	}
	
	@Test
	void testGetPointInNotInDB() {
		// checks that you are returned null
		// when you call get with a Point not in the factory
		PointNamingFactory f = new PointNamingFactory();
		
		Point p = new Point("fruit salad", 1.7234, 6.987123); 
		
		assertNull(f.get(p));
	}

	@Test
	void testContainsDoubleDoubleTrue() {
		// checks that you are returned true
		// when you call contains with the coordinates
		// of a Point in the Factory
		PointNamingFactory f = new PointNamingFactory();
		
		
		//NEED TO FIX EPSILON IMPLEMENTATION
		Point r = f.put("FUOC", 0.726997987, 8.799764); 
		
		assertTrue(f.contains(0.726997987, 8.799764));
	}
	
	@Test
	void testContainsDoubleDoubleFalse() {
		// checks that you are returned false
		// when you call contains with the coordinates
		// not of a Point in the Factory
		PointNamingFactory f = new PointNamingFactory();
		
		
		assertFalse(f.contains(0.7, 8.8));
	}

	@Test
	void testContainsPointNull() {
		// checks that you are returned false
		// when you call contains with null
		PointNamingFactory f = new PointNamingFactory();
				
		assertFalse(f.contains(null));
	}
	
	@Test
	void testContainsPointInDB() {
		// checks that you are returned true
		// when you call contains with a Point in the factory
		PointNamingFactory f = new PointNamingFactory();
		
		Point p = f.put("fruit salad", 1.7234, 6.987123); 

		assertTrue(f.contains(p));
	}
	
	@Test
	void testContainsPointInNotInDB() {
		// checks that you are returned false
		// when you call contains with a Point not in the factory
		PointNamingFactory f = new PointNamingFactory();
		
		Point p = new Point("fruit salad", 1.7234, 6.987123); 
		
		assertFalse(f.contains(p));
	}

	@Test
	void testGetAllPoints() {
		// checks that getAllPoints() returns a set
		// that is the same size as the factory
		PointNamingFactory f = new PointNamingFactory();
		
		for (int i = 0; i < 10; i++) {
			f.put(i, i);
		}
		
		assertEquals(10, f.getAllPoints().size());
	}

	@Test
	void testClear() {
		// checks that the factory is empty
		// after calling clear()
		PointNamingFactory f = new PointNamingFactory();
		
		for (int i = 0; i < 10; i++) {
			f.put(i, i);
		}
		
		f.clear();
		
		assertEquals(0, f.size());
	}
}
