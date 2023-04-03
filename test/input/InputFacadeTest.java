package input;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;

public class InputFacadeTest {

    /**
     * Provides a list of test JSON file names
     *
     * @return a list of file names in the testFiles folder
     * Test more files if you have them
     */
    public static Object[][] provideInputFileNames() {
        return new Object[][] {
            { "collinear_line_segments.json" },
            { "crisscross_square.json" },
            { "fully_connected_irregular_polygon.json" }
        };
    }

    @ParameterizedTest
    @MethodSource("provideInputFileNames")
    public void testExtractFigure(String filename) {
        FigureNode n = InputFacade.extractFigure(filename);

        // make sure the FigureNode has data
        assertFalse(n.getDescription().isEmpty());
        assertFalse(n.getPointsDatabase().getAllPoints().isEmpty());
        assertFalse(n.getSegments().asUniqueSegmentList().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideInputFileNames")
    public void testToGeometryRepresentation(String filename) {
        FigureNode n = InputFacade.extractFigure(filename);

        Map.Entry<PointDatabase, Set<Segment>> geomEntry = InputFacade.toGeometryRepresentation(n);
        PointDatabase db = geomEntry.getKey();
        Set<Segment> segments = geomEntry.getValue();

        // make sure the geometry representation is filled
        assertFalse(db.getPoints().isEmpty());
        assertFalse(segments.isEmpty());
    }
}
