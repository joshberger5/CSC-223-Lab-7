package input;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import geometry_objects.Segment;
import input.builder.GeometryBuilder;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.segment.SegmentNode;
import input.parser.JSONParser;

/**
 * Helpers for loading geometry figures from input JSON files,
 * and converting them from their input formats to geometry
 * representations.
 *
 * @author Sam Luck-Leonard
 * @date 4/3/2023
 */
public class InputFacade {
    /**
     * A utility method to acquire a figure from the given JSON file:
     * Constructs a parser
     * Acquires an input file string.
     * Parses the file.
     *
     * @param filepath -- the path/name defining the input file
     * @return a FigureNode object corresponding to the input file.
     */
    public static FigureNode extractFigure(String filepath) {
        String input = utilities.io.FileUtilities.readFileFilterComments(filepath);
        JSONParser parser = new JSONParser(new GeometryBuilder());
        return (FigureNode) parser.parse(input);
    }

    /**
     * Convert the PointNode and SegmentNode objects to a Point and Segment objects
     * (those classes have more meaningful, geometric functionality).
     * Return the points and segments as a pair.
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
        
        //Separate into methods for helper
        for (SegmentNode segmentNode : fig.getSegments().asUniqueSegmentList()) {
            Point start = pointDB.getPoint(segmentNode.getPoint1().getName());
            Point end = pointDB.getPoint(segmentNode.getPoint2().getName());
            Segment segment = new Segment(start, end);
            segments.add(segment);
        }
        //could be one line
        return new AbstractMap.SimpleEntry<>(pointDB, segments);
    }
}
