package parser;
//this tests UnparseVisitor



import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import input.builder.GeometryBuilder;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;
import input.parser.JSONParser;
import input.visitor.UnparseVisitor;

class JSONParserTest
{
	
	UnparseVisitor visitor = new UnparseVisitor();
	
	public static ComponentNode runFigureParseTest(String filename)
	{
		JSONParser parser = new JSONParser(new GeometryBuilder());

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	public static ComponentNode runValidParseTest(String filename)
	{
		JSONParser parser = new JSONParser();

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return parser.parse(figureStr);
	}
	
	@Test
	void empty_json_string_test()
	{
		JSONParser parser = new JSONParser(new GeometryBuilder());

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}
	
	@Test
	void empty_json_string_valid_test()
	{
		JSONParser parser = new JSONParser();

		assertThrows(ParseException.class, () -> { parser.parse("{}"); });
	}

	@Test
	void single_triangle_test()
	{
		//
		// The input String ("single_triangle.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/single_triangle.json"
		//
		ComponentNode node = JSONParserTest.runFigureParseTest("single_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Right Triangle in the first quadrant.\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(0.0, 0.0)\n"
				   + "        Point(B)(1.0, 1.0)\n"
				   + "        Point(C)(1.0, 0.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B C \n"
				   + "        B : A C \n"
				   + "        C : A B \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void single_triangle_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("single_triangle.json"));
	}
	
	@Test
	void collinear_line_segments_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("collinear_line_segments.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : A seqeunce of collinear line segments mimicking one line with 6 points.\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(0.0, 0.0)\n"
				   + "        Point(B)(4.0, 0.0)\n"
				   + "        Point(C)(9.0, 0.0)\n"
				   + "        Point(D)(11.0, 0.0)\n"
				   + "        Point(E)(16.0, 0.0)\n"
				   + "        Point(F)(26.0, 0.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B \n"
				   + "        B : A C \n"
				   + "        E : D F \n"
				   + "        C : B D \n"
				   + "        D : E C \n"
				   + "        F : E \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void collinear_line_segments_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("collinear_line_segments.json"));
	}
	
	@Test
	void crossing_symmetric_triangle_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("crossing_symmetric_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Crossing symmetric triangle construction.\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(D)(0.0, 0.0)\n"
				   + "        Point(E)(6.0, 0.0)\n"
				   + "        Point(B)(2.0, 4.0)\n"
				   + "        Point(C)(4.0, 4.0)\n"
				   + "        Point(A)(3.0, 6.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B C \n"
				   + "        B : A C D E \n"
				   + "        C : A B D E \n"
				   + "        D : B C E \n"
				   + "        E : B C D \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void crossing_symmetric_triangle_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("crossing_symmetric_triangle.json"));
	}
	
	@Test
	void fully_connected_irregular_polygon_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("fully_connected_irregular_polygon.json");

		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Irregular pentagon in which each vertex is connected to each other.\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(0.0, 0.0)\n"
				   + "        Point(B)(4.0, 0.0)\n"
				   + "        Point(C)(6.0, 3.0)\n"
				   + "        Point(D)(3.0, 7.0)\n"
				   + "        Point(E)(-2.0, 4.0)\n"
				   + "        Point(F)(26.0, 0.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B C E D \n"
				   + "        B : A C E D \n"
				   + "        C : A B E D \n"
				   + "        E : A B C D \n"
				   + "        D : A B C E \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void fully_connected_irregular_polygon_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("fully_connected_irregular_polygon.json"));
	}
	
	// tests a simple (4 point, 4 segment) shape in the first quadrant
	@Test
	void single_square_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("single_square.json");
		
		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Square where each corner connects to the corners on its right and left.\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(0.0, 0.0)\n"
				   + "        Point(B)(4.0, 0.0)\n"
				   + "        Point(C)(0.0, 4.0)\n"
				   + "        Point(D)(4.0, 4.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B C \n"
				   + "        B : A D \n"
				   + "        C : A D \n"
				   + "        D : B C \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void single_square_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("single_square.json"));
	}
	
	// tests a more complex (12 point, 26 segment) shape in all four quadrants
	@Test
	void crisscross_square_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("crisscross_square.json");
		
		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Square with each point connected to all the points next to it and across from it\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(-2.0, -2.0)\n"
				   + "        Point(B)(-2.0, -1.0)\n"
				   + "        Point(C)(-2.0, 0.0)\n"
				   + "        Point(D)(-2.0, 1.0)\n"
				   + "        Point(E)(-1.0, 1.0)\n"
				   + "        Point(F)(0.0, 1.0)\n"
				   + "        Point(G)(1.0, 1.0)\n"
				   + "        Point(H)(1.0, 0.0)\n"
				   + "        Point(I)(1.0, -1.0)\n"
				   + "        Point(J)(1.0, -2.0)\n"
				   + "        Point(K)(0.0, -2.0)\n"
				   + "        Point(L)(-1.0, -2.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B G L \n"
				   + "        G : A F H \n"
				   + "        C : B D E H K \n"
				   + "        I : B E H J \n"
				   + "        E : C D F I L \n"
				   + "        K : C F J L \n"
				   + "        B : A C F I L \n"
				   + "        L : A B E K \n"
				   + "        F : B E G H K \n"
				   + "        D : C E J \n"
				   + "        H : C F G I \n"
				   + "        J : D I K \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void crisscross_square_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("crisscross_square.json"));
	}
	
	// tests a complex shape (10 points, 45 segments) shape in the last two quadrants
	@Test
	void decagon_wheel_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("decagon_wheel.json");
		
		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Decagon where each point connects to every other point\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(0.0, 0.0)\n"
				   + "        Point(B)(2.0, 0.0)\n"
				   + "        Point(C)(4.0, -2.0)\n"
				   + "        Point(D)(5.0, -4.0)\n"
				   + "        Point(E)(4.0, -6.0)\n"
				   + "        Point(F)(2.0, -8.0)\n"
				   + "        Point(G)(0.0, -8.0)\n"
				   + "        Point(H)(-2.0, -6.0)\n"
				   + "        Point(I)(-3.0, -4.0)\n"
				   + "        Point(J)(-2.0, -2.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : B C F G J D E H I \n"
				   + "        B : A C F G J D E H I \n"
				   + "        C : A B F G J D E H I \n"
				   + "        F : A B C G J D E H I \n"
				   + "        G : A B C F J D E H I \n"
				   + "        J : A B C F G D E H I \n"
				   + "        D : A B C F G J E H I \n"
				   + "        E : A B C F G J D H I \n"
				   + "        H : A B C F G J D E I \n"
				   + "        I : A B C F G J D E H \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void decagon_wheel_valid_test()
	{
		assertNull(JSONParserTest.runValidParseTest("decagon_wheel.json"));
	}
	
	// tests that a segment fails when trying to connect to a nonexistent point
	@Test
	void fake_point_test()
	{
		assertThrows(ParseException.class, () -> {JSONParserTest.runFigureParseTest("fake_point.json");});
	}
	
	@Test
	void fake_point_valid_test()
	{
		// invalid shape, but valid JSON
		assertNull(JSONParserTest.runValidParseTest("fake_point.json"));
	}
	
	// tests having doubles for the points' coordinates
	// shape with 9 points, 18 segments in all four quadrants 
	@Test
	void snake_test()
	{
		ComponentNode node = JSONParserTest.runFigureParseTest("snake.json");
		
		assertTrue(node instanceof FigureNode);
		
		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		node.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : Snake\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(-103.55, 111.47)\n"
				   + "        Point(B)(-78.54, 92.63)\n"
				   + "        Point(C)(-28.98, 43.81)\n"
				   + "        Point(D)(16.69, -52.15)\n"
				   + "        Point(E)(42.73, 23.48)\n"
				   + "        Point(F)(26.25, -2.67)\n"
				   + "        Point(G)(-24.94, -48.32)\n"
				   + "        Point(H)(-82.01, -16.42)\n"
				   + "        Point(I)(-129.0, -4.09)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        C : D G F B H \n"
				   + "        D : C G F E \n"
				   + "        G : C D F B H \n"
				   + "        I : B H A \n"
				   + "        F : C D G E \n"
				   + "        B : C G I H A \n"
				   + "        E : D F \n"
				   + "        H : C G I B A \n"
				   + "        A : I B H \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void snake_valid_test()
	{
		// invalid shape, but valid JSON
		assertNull(JSONParserTest.runValidParseTest("snake.json"));
	}
	
	@Test
	void no_description_test()
	{
		assertThrows(ParseException.class, () -> { JSONParserTest.runFigureParseTest("no_description.json"); });
	}
	
	@Test
	void no_description_valid_test()
	{
		assertThrows(ParseException.class, () -> {JSONParserTest.runValidParseTest("no_description.json");});
	}
	
	@Test
	void no_points_test()
	{
		assertThrows(ParseException.class, () -> { JSONParserTest.runFigureParseTest("no_points.json"); });
	}
	
	@Test
	void no_points_valid_test()
	{
		assertThrows(ParseException.class, () -> {JSONParserTest.runValidParseTest("no_points.json");});
	}
	
	@Test
	void no_segments_test()
	{
		assertThrows(ParseException.class, () -> { JSONParserTest.runFigureParseTest("no_segments.json"); });
	}
	
	@Test
	void no_segments_valid_test()
	{
		assertThrows(ParseException.class, () -> {JSONParserTest.runValidParseTest("no_segments.json");});
	}
	
	@Test
	void no_figure_test()
	{
		assertThrows(ParseException.class, () -> { JSONParserTest.runFigureParseTest("no_figure.json"); });
	}
	
	@Test
	void no_figure_valid_test()
	{
		assertThrows(ParseException.class, () -> {JSONParserTest.runValidParseTest("no_figure.json");});
	}
	
	@Test
	void star_test()
	{
		//
		// The input String ("intersecting_star.json") assumes the file is
		// located at the top-level of the project. If you move your input
		// files into a folder, update this String with the path:
		//                                       e.g., "my_folder/intersecting_star.json"
		//
		ComponentNode jsonNode = JSONParserTest.runFigureParseTest("intersecting_star.json");

		//ComponentNode javaNode = buildSingleTriangle();

		assertTrue(jsonNode instanceof FigureNode);

		StringBuilder sb = new StringBuilder();
		AbstractMap.SimpleEntry<StringBuilder, Integer> pair = new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0);
		jsonNode.accept(visitor, pair);
		assertEquals("Figure\n"
				   + "{\n"
				   + "    Description : A star in which line segments intersect\n"
				   + "    Points: \n"
				   + "    {\n"
				   + "        Point(A)(0.0, 0.0)\n"
				   + "        Point(B)(4.0, 0.0)\n"
				   + "        Point(C)(6.0, 5.0)\n"
				   + "        Point(D)(3.0, 8.0)\n"
				   + "        Point(E)(-2.0, 5.0)\n"
				   + "    }\n"
				   + "    Segments: \n"
				   + "    {\n"
				   + "        A : D C \n"
				   + "        B : E D \n"
				   + "        E : B D C \n"
				   + "        D : A B E \n"
				   + "        C : A E \n"
				   + "    }\n"
				   + "}", sb.toString());
	}
	
	@Test
	void star_valid_test()
	{
		// invalid shape, but valid JSON
		assertNull(JSONParserTest.runValidParseTest("intersecting_star.json"));
	}
	
}
