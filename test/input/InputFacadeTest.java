package input;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.parser.JSONParser;
import utilities.io.FileUtilities;

public class InputFacadeTest {

    /**
     * Gets a stream of test JSON files
     *
     * @return a Stream containing a list of file names in the testFiles folder
     */
    public static Stream<String> provideStringsForInputFacade() {
        return Stream.of("collinear_line_segments.json", "crisscross_square.json", "fully_connected_irregular_polygon.json");
    }

    private static String getFigureString(String filename) {
        return FileUtilities.readFileFilterComments(filename);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForInputFacade")
    public void inputFacadeExtractFigureTest(String filename) {
        FigureNode n = InputFacade.extractFigure(filename);

        // make sure the FigureNode has data
        assertFalse(n.getDescription().isEmpty());
        assertFalse(n.getPointsDatabase().getAllPoints().isEmpty());
        assertFalse(n.getSegments().asUniqueSegmentList().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideStringsForInputFacade")
    public void inputFacadeToGeometryRepresentationTest(String filename) {
        String figureStr = getFigureString(filename);
        JSONParser parser = new JSONParser(new GeometryBuilder());
        ComponentNode rootNode = parser.parse(figureStr);

        FigureNode n = InputFacade.extractFigure(filename);

        Map.Entry<PointDatabase, Set<Segment>> geomEntry = InputFacade.toGeometryRepresentation(n);
        PointDatabase db = geomEntry.getKey();
        Set<Segment> segments = geomEntry.getValue();

        // make sure the geometry representation is populated
        assertFalse(db.getPoints().isEmpty());
        assertFalse(segments.isEmpty());
    }
}
