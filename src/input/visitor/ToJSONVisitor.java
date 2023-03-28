package input.visitor;

import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;

public class ToJSONVisitor implements ComponentNodeVisitor {
	
	public Object visitFigureNode(FigureNode figureNode, Object o) {
		JSONObject figureWrapperObject = (JSONObject)o;
		JSONObject figureObject = new JSONObject();
		figureObject.put("Description", figureNode.getDescription());
		figureNode.getPointsDatabase().accept(this, figureObject);
		figureNode.getSegments().accept(this, figureObject);
		
		figureWrapperObject.put("Figure", figureObject);
		return figureWrapperObject;
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o)
	{

		JSONObject pointNodeDatabaseObject = (JSONObject)o;
		JSONArray pointArrayObject = new JSONArray();

		Set<PointNode> allPoints = node.getAllPoints();

		for(PointNode individualPoint : allPoints) {
			pointArrayObject.put(individualPoint.accept(this, new JSONObject()));
		}	
		pointNodeDatabaseObject.put("Points", pointArrayObject);
		return pointNodeDatabaseObject;
	}

	public Object visitPointNode(PointNode pointNode, Object o) {
		JSONObject pointObject = (JSONObject)o;
		pointObject.put("name", pointNode.getName());
		pointObject.put("x", pointNode.getX());
		pointObject.put("y", pointNode.getY());
		return pointObject;
	}
	
	@Override
	public Object visitSegmentNodeDatabase(SegmentNodeDatabase node, Object o)
	{

		JSONObject wrapperObject = (JSONObject)o;
		JSONArray segmentNodeDatabaseObject = new JSONArray();
		
		Map<PointNode, Set<PointNode>> segments = node.getAdjLists();


		Set<PointNode> allSegmentKeys = segments.keySet();

		for(PointNode eachKey : allSegmentKeys) {
			JSONArray segmentListObject = new JSONArray();
			JSONObject adjacencyListObject = new JSONObject();
			for(PointNode eachValue : segments.get(eachKey)) {
				segmentListObject.put(eachValue.getName());
			}
			adjacencyListObject.put(eachKey.getName(), segmentListObject);
			segmentNodeDatabaseObject.put(adjacencyListObject);
		}

		return wrapperObject.put("Segments", segmentNodeDatabaseObject);
	}

	public Object visitSegmentNode(SegmentNode segmentNode, Object o) {
		JSONObject segmentObject = (JSONObject)o;
		PointNode start = segmentNode.getPoint1();
		PointNode end = segmentNode.getPoint2();
		segmentObject.put(start.getName(), new String[] { end.getName() });
		return segmentObject;
	}
}