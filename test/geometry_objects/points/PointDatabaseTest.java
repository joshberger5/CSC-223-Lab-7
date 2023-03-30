package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import geometry_objects.points.PointDatabase;

class PointDatabaseTest {
	
	private PointDatabase initDB() {
		PointDatabase d = new PointDatabase();
		
		for (int i = 0; i < 10; i++) {
			// puts 10 Points in the PointDatabase
			// each one's name is a letter
			// starting at A and going to J
			d.put("" + ((char) (65+i)), 2*i-8, 3*i-5);
		}
		
		return d;
	}

	@Test
	void testGetPoints() {
		fail("Not yet implemented");
	}

	@Test
	void testPointDatabase() {
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
		
		assertEquals(10, d.size());
	}

	@Test
	void testSizeofFullDB() {
		PointDatabase d = initDB();
		
		assertEquals(10, d.size());
	}

	@Test
	void testPutWithNullName() {
		PointDatabase d = new PointDatabase();
		
		d.put(null, 0, 0);
		
		assertEquals(1, d.size());
	}

	@Test
	void testGetNameDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNamePoint() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointDoubleDouble() {
		fail("Not yet implemented");
	}

}
