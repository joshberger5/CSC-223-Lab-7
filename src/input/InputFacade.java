package input;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import geometry_objects.Segment;
import input.builder.DefaultBuilder;
import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import input.parser.JSONParser;

public class InputFacade {
	
	/**
	 * A utility method to acquire a figure from the given JSON file:
	 *     Constructs a parser
	 *     Acquires an input file string.
	 *     Parses the file.
	 *
	 * @param filepath -- the path/name defining the input file
	 * @return a FigureNode object corresponding to the input file.
	 */
	public static FigureNode extractFigure(String filepath) {
		String input = filepath;
		JSONParser parser = new JSONParser(new DefaultBuilder());
		return (FigureNode) parser.parse(input);
	}

	/**
	 * 1) Convert the PointNode and SegmentNode objects to a Point and Segment objects
	 *    (those classes have more meaningful, geometric functionality).
	 * 2) Return the points and segments as a pair.
	 *
	 * @param fig -- a populated FigureNode object corresponding to a geometry figure
	 * @return a point database and a set of segments
	 */
	public static Map.Entry<PointDatabase, Set<Segment>> toGeometryRepresentation(FigureNode fig) {
	    PointDatabase pointDB = new PointDatabase();
	    Set<Segment> segments = new LinkedHashSet<>();

	    for (PointNode pointNode : fig.getPointsDatabase().getAllPoints()) {
	        pointDB.put(pointNode.getName(), pointNode.getX(), pointNode.getY());
	    }

	    for (SegmentNode segmentNode : fig.getSegments().asSegmentList()) {
	        Point start = pointDB.getPoint(segmentNode.getPoint1().getName());
	        Point end = pointDB.getPoint(segmentNode.getPoint2().getName());
	        Segment segment = new Segment(start, end);
	        segments.add(segment);
	    }

	    return new AbstractMap.SimpleEntry<>(pointDB, segments);
	}

}
