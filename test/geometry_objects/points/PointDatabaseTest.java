package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import geometry_objects.points.PointDatabase;

class PointDatabaseTest {
	
	/**
	 * @return a PointDatabase with 10 Points
	 */
	private PointDatabase initDB() {
		PointDatabase d = new PointDatabase();
		
		for (int i = 0; i < 10; i++) {
			// puts 10 Points in the PointDatabase
			// each one's name is a letter
			// starting at A and going to J
			// and x coordinates from -8 to 11
			// and y coordinates from -5 to 22
			d.put("" + ((char) (65+i)), 2*i-8, 3*i-5);
		}
		
		return d;
	}

	@Test
	void testGetPoints() {
		// fail("Not yet implemented");
	}

	@Test
	void testPointDatabase() {
		// checks that the default constructor makes an empty PointDatabase
		PointDatabase d = new PointDatabase();
		assertEquals(0, d.size());
	}

	@Test
	void testPointDatabaseListOfPoint() {
		// initialize a list of 10 Points
		List<Point> points = new ArrayList<Point>();
		for (int i = 0; i < 10; i++) {
			Point p = new Point(2*i-8, 3*i-5);
			points.add(p);
		}
		
		// construct a PointDatabase passing in the list
		PointDatabase d = new PointDatabase(points);
		
		// checks that the PointDatabase was constructed with 10 Points
		assertEquals(10, d.size());
	}

	@Test
	void testSize() {
		// checks that the size increases from 0
		PointDatabase d = new PointDatabase();
		assertEquals(0, d.size());
		
		// to 10 after putting 10 points in the PointDatabase
		d = initDB();
		assertEquals(10, d.size());
	}

	@Test
	void testPutWithNullName() {
		PointDatabase d = new PointDatabase();
		
		// add a Point to the PointDatabase without a valid name
		d.put(null, 0, 0);
		
		// check that the PointDatabase size increases by 1
		assertEquals(1, d.size());
	}
	
	@Test
	void testPutWithNonNullName() {
		PointDatabase d = new PointDatabase();
		
		// add a Point to the PointDatabase with a valid name
		d.put("Hello", 1, 2);
		
		// check that the PointDatabase size increases by 1
		assertEquals(1, d.size());
	}
	
	@Test
	void testPutDuplicate() {
		PointDatabase d = new PointDatabase();
		
		// add a Point to the PointDatabase
		d.put("Hello", 1, 2);
		
		// add that same Point again
		// with same name and coordinates
		d.put("Hello", 1, 2);
		
		// check that the PointDatabase size only increases by 1
		assertEquals(1, d.size());
	}
	
	@Test
	void testPutRepeatWithDiffName() {
		PointDatabase d = new PointDatabase();
		
		// add a Point to the PointDatabase
		d.put("Hello", 1, 2);
		
		// add that same Point again
		// with same name and coordinates
		d.put("Goodbye", 1, 2);
		
		// check that the PointDatabase size only increases by 1
		assertEquals(1, d.size());
	}

	@Test
	void testGetNameDoubleDouble() {
		PointDatabase d = new PointDatabase();
		
		// add a Point to the PointDatabase
		d.put("A", 5.5, -5.5);
		
		Point p = d.getPoint(5.5, -5.5);
		
		// makes sure that get returns a Point
		// with the same
		// x-coordinate,
		// y-coordinate,
		// and name
		assertEquals(5.5, p._x);
		assertEquals(-5.5, p._y);
		assertEquals("A", p._name);
	}
	
	@Test
	void testGetNameDoubleDoubleNotInDB() {
		// check that getPoint(double x, double y)
		// returns null when you pass in coordinates
		// that don't correspond to a Point in the PointDatabase
		PointDatabase d = new PointDatabase();
		
		assertNull(d.getPoint(5.49, -5.51));
	}

	@Test
	void testGetNamePointNull() {
		// checks getting the name of a null Point
		// from a full PointDatabase
		// returns null
		PointDatabase d = initDB();
		
		assertNull(d.getName(null));
	}
	
	@Test
	void testGetNamePointNotInDB() {
		// checks that getName(Point pt)
		// when passing in a Point not in the PointDatabase
		// returns null
		PointDatabase d = new PointDatabase();
		
		Point p = new Point("lasdjf", 1.1320481, -8.9082301);
		
		assertNull(d.getName(p));
	}
	
	@Test
	void testGetNamePointSameName() {
		// checks that getName(Point pt)
		// returns the same name
		// as the name of the Point
		// you put into the Database
		// when you call getName(Point pt) passing in
		// a Point with the same name and coordinates
		PointDatabase d = initDB();
		
		Point p = new Point("Right", 100, -100);
		d.put("Right", 100, -100);
		
		assertEquals("Right", d.getName(p));
	}
	
	@Test
	void testGetNamePointDiffName() {
		// checks that getName(Point pt)
		// returns the name of the Point
		// you put into the Database
		// when you call getName(Point pt) passing in
		// a Point with a different name
		// but the same coordinates
		PointDatabase d = initDB();
		
		Point p = new Point("Wrong", 100, -100);
		d.put("Right", 100, -100);
		
		assertEquals("Right", d.getName(p));
	}

	@Test
	void testGetPointStringNull() {
		// checks that getPoint(String name)
		// handles null being passed in
		PointDatabase d = initDB();
		
		assertNull(d.getPoint((String) null));
	}
	
	@Test
	void testGetPointStringValid() {
		// checks that getPoint(String name)
		// return the Point when the passed in String
		// corresponds to a Point in the PointDatabase
		PointDatabase d = new PointDatabase();
		
		d.put("wiggle wiggle", 8.675309, -8.675309);
		Point g = d.getPoint("wiggle wiggle");
		
		assertEquals("wiggle wiggle", g._name);
		assertEquals(8.675309, g._x);
		assertEquals(-8.675309, g._y);
	}

	@Test
	void testGetPointPointNull() {
		PointDatabase d = new PointDatabase();
		
		assertNull(d.getPoint((Point) null));
	}
	
	@Test
	void testGetPointPointValid() {
		// add a Point to the PointDatabase
		// check that you can get the same Point from the Database
		PointDatabase d = new PointDatabase();
		
		d.put("ABC", 1, 2);
		Point p = new Point("ABC", 1, 2);
		Point g = d.getPoint(p);
		
		assertEquals("ABC", g._name);
		assertEquals(1, g._x);
		assertEquals(2, g._y);
	}
	
	@Test
	void testGetPointPointNotInDB() {
		// add a Point to the PointDatabase
		// check that you can get the same Point from the Database
		PointDatabase d = new PointDatabase();
		
		Point p = new Point("ABC", 1, 2);
		
		assertNull(d.getPoint(p));
	}

	@Test
	void testGetPointDoubleDoubleValid() {
		PointDatabase d = new PointDatabase();
		
		d.put("DEF", -27.13, -81.69);
		Point p = new Point("DEF", -27.13, -81.69);
		Point g = d.getPoint(-27.13, -81.69);
		
		assertEquals("DEF", g.getName());
		assertEquals(-27.13, g.getX());
		assertEquals(-81.69, g.getY());
	}
	
	@Test
	void testGetPointDoubleDoubleNotInDB() {
		PointDatabase d = new PointDatabase();
		
		Point p = new Point("DEF", -27.13, -81.69);
		
		assertNull(d.getPoint(-27.1, -81.7));
	}

}
