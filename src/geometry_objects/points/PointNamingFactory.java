package geometry_objects.points;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.math.MathUtilities;

/*
 * Given a pair of coordinates; generate a unique name for it;
 * return that point object.
 *
 * Names go from A..Z..AA..ZZ..AAA...ZZZ  (a name such as ABA does not occur)
 */
public class PointNamingFactory
{
	// Prefix associated with each generated name so those names are easily distinguishable
	private static final String _PREFIX = "*_";

    // Constants reflecting our naming characters for generated names.
	private static final char START_LETTER = 'A';
	private static final char END_LETTER = 'Z';

    //
    // the number of characters in the generated names:
	// "A" and 1 -> "A"
	// "B" and 3 -> "BBB"
	//
	private String _currentName = "A";
	private int _numLetters = 1;

	//
	// A hashed container for the database of points; this requires the Point
	// class implement equals based solely on the individual coordinates and
	// not a name. We need a get() method; HashSet doesn't offer one.
	// Each entry is a <Key, Value> pair where Key == Value
	//
	protected Map<Point, Point> _database;

	public PointNamingFactory()
	{
		_database = new HashMap<Point, Point>();
	}

	/**
	 * Initialize the database with points; must call put() to ensure all points are named
	 *
	 * @param points -- a list of points, named or not named
	 */
	public PointNamingFactory(List<Point> points)
	{
		_database = new HashMap<Point, Point>();
		if (points == null) return;
		for (Point p : points) {
			put(p);
		}
	}

	/**
	 * Overloaded add / lookup mechanism for this database.
	 *
	 * @param pt -- a Point object (may or may not be named)
	 
	 * @return THE point object in the database corresponding to its coordinate pair
                    * the object in the database if it already exists or
					* a completely new point that has been added to the database
	 */
	public Point put(Point pt)
	{
		// delegates the main put method after checking for null input
		if (pt == null) return null;
		
		return put(pt.getName(), pt.getX(), pt.getY());
	}

	/**
	 * Overloaded add / lookup mechanism for this database for an unnamed coordinate pair.
	 *
	 * @param x -- single coordinate
	 * @param y -- single coordinate

	 * @return THE point object in the database corresponding to its coordinate pair
                    * the object in the database if it already exists or
					* a completely new point that has been added to the database (with generated name)
	 */
	public Point put(double x, double y)
	{
		// delegate the main put method 
		return put(null, x, y);
	}

	/**
	 * Main overloaded add / lookup mechanism for a named coordinate pair.
	 * 
	 * @param name -- the name of the point 
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * 
	 * @return a point (if it already exists in the database) or a completely new point that
	 *         has been added to the database.
	 *         
	 *         If the point is in the database and the name differs from what
	 *         is given, nothing in the database will be changed; essentially
	 *         this means we use the first name given for a point.
	 *            e.g., a valid name cannot overwrite an existing valid name ;
	 *                  a generated name cannot be overwritten by another generated name
	 *         
	 *         The exception is that a valid name can overwrite an unnamed point.
	 */
	public Point put(String name, double x, double y)
	{
		// make a Point
		// with the given coordinates
		// and the given name
		Point p = new Point(name, x, y);
		
		// check if the point is in the database
		Point g = get(p);
						
		// if so, return it
		if (g != null) return g;
						
		// if not, make sure the point has a valid name
		// and if it doesn't give it a constructed name
		if (p._name == null || p._name.equals(Point.ANONYMOUS)) p._name = getCurrentName();
						
		// add it to the database
		_database.put(p, p);
						
		// and return it
		return p;
	}    

	/**
	 * Strict access (read-only of the database)
	 * 
	 * @param x
	 * @param y
	 * @return stored database Object corresponding to (x, y) 
	 */
	public Point get(double x, double y)
	{
		// checks if the passed in coordinates
		// match a Point in the factory
		// and if so, return it
		// if not, return null
		// use methods from point 
		for (Point p : _database.keySet()) {
			if (MathUtilities.doubleEquals(x, p.getX()) && MathUtilities.doubleEquals(y, p.getY()))
				return p;
		}
		return null;
	}	
	public Point get(Point pt)
	{
		// checks if the passed in point is null
		// and if not, delegates the main get method
		if (pt == null) return null;
		return get(pt._x, pt._y);
	}

	/**
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return simple containment; no updating
	 */
	public boolean contains(double x, double y) { 
		return get(x, y) != null;
	}
	public boolean contains(Point p) {
		if (p == null) return false;
		return get(p._x, p._y) != null;
	}

	/**
	 * Constructs the next (complete with prefix) generated name.
	 * Names should be of the form PREFIX + current name
	 *
	 * This method should also invoke updating of the current name
	 * to reflect the 'next' name in the sequence.
     *	 
	 * @return the next complete name in the sequence including prefix.
	 */
	private String getCurrentName()
	{
		String name = _PREFIX + _currentName;
		updateName();
        return name;
	}

	/**
	 * Advances the current generated name to the next letter in the alphabet:
	 * 'A' -> 'B' -> 'C' -> 'Z' --> 'AA' -> 'BB'
	 */
	private void updateName()
	{
		// if the current generated name includes the END_LETTER (Z),
		// then change it to the START_LETTER (A),
		// which should repeat as many times as there currently are letters, plus 1
		// for example, it changes Z to AA, ZZ to AAA, ZZZ to AAAA
        if (_currentName.charAt(0) == END_LETTER) {
        	_numLetters++;
        	_currentName = "";
        	for (int i = 0; i < _numLetters; i++) {
        		_currentName += START_LETTER;
        	}
        }
        // if the current generated name doesn't include Z
        // change it to the next letter in the alphabet
        // repeating as many times as there currently are letters
        // for example, it changes A to B, CC to DD, LLL to MMM
        else {
        	char updatedChar = (char) (_currentName.charAt(0) + 1);
        	_currentName = "";
        	for (int i = 0; i < _numLetters; i++) {
        		_currentName += updatedChar;
        	}
        }
	}

	/**
	 * @return The entire database of points.
	 */
	public  Set<Point> getAllPoints()
	{
		// generic, use <E>, don't ignore warning
		Set<Point> points = new HashSet();
        for (Point p : _database.keySet()) {
        	points.add(p);
        }
        return points;
	}

	public void clear() { _database.clear(); }
	public int size() { return _database.size(); }

	@Override
	public String toString()
	{
		String s = "";
        for (Point p : _database.keySet()) {
        	s += p.getName() + " ";
        }
        return s;
	}
}