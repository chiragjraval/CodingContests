package com.chirag.test.bankbazaar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ShortestPath
{
	static enum LineColor
	{
		BLACK,
		BLUE,
		RED,
		GREEN
	}
	
	static class GraphEdge
	{
		private GraphNode origNode;
		private GraphNode destNode;
		private LineColor color;
		private Integer time;
		
		public GraphEdge(GraphNode origNode, GraphNode destNode, LineColor lineColor, Integer time)
		{
			this.origNode = origNode;
			this.destNode = destNode;
			this.color = lineColor;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return "Edge[origNode=" + origNode.nodeId + ", destNode=" + destNode.nodeId + ", color=" + color + ", time=" + time + "]";
		}
	}
	
	static class Path implements Comparable<Path>
	{
		private ArrayList<GraphEdge> pathEdges;
		private Integer totalTime;
		private Integer noOfHalts;
		private Integer noOfTransfers;
		
		public Path()
		{
			this.pathEdges = null;
			this.totalTime = Integer.MAX_VALUE;
			this.noOfHalts = Integer.MAX_VALUE;
			this.noOfTransfers = Integer.MAX_VALUE;
		}
		
		public Path(ArrayList<GraphEdge> pathEdges, Integer totalTime,  Integer noOfHalts, Integer noOfTransfers)
		{
			this.totalTime = totalTime;
			this.pathEdges = pathEdges;
			this.noOfHalts = noOfHalts;
			this.noOfTransfers = noOfTransfers;
		}
		

		@Override
		public int compareTo(Path other) {
			int retVal = Integer.compare(this.totalTime, other.totalTime);
			retVal = retVal==0 ? Integer.compare(this.noOfTransfers, other.noOfTransfers) : retVal;
			retVal = retVal==0 ? Integer.compare(this.noOfHalts, other.noOfHalts) : retVal;
			return retVal;
		}
		
		@Override
		public String toString() {
			return "Path[totalTime=" + totalTime + ", noOfHalts=" + noOfHalts + ", noOfTransfers=" + noOfTransfers + "]";
		}
		
		public ArrayList<GraphEdge> getPathEdges() {
			return pathEdges;
		}

		public void setPathEdges(ArrayList<GraphEdge> pathEdges) {
			this.pathEdges = pathEdges;
		}

		public Integer getTotalTime() {
			return totalTime;
		}

		public void setTotalTime(Integer totalTime) {
			this.totalTime = totalTime;
		}

		public Integer getNoOfHalts() {
			return noOfHalts;
		}

		public void setNoOfHalts(Integer noOfHalts) {
			this.noOfHalts = noOfHalts;
		}

		public Integer getNoOfTransfers() {
			return noOfTransfers;
		}

		public void setNoOfTransfers(Integer noOfTransfers) {
			this.noOfTransfers = noOfTransfers;
		}

	}
	
	static class GraphNode
	{
		private String nodeId;
		private ArrayList<GraphEdge> adjEdges;
		
		// Below 5 fields are used while finding shortest path;
		private boolean isVisited;
		private Path shortestPath;

		public GraphNode(String nodeId)
		{
			this.nodeId = nodeId;
			this.isVisited = false;
			this.shortestPath = new Path();
		}
		
		@Override
		public String toString() {
			return "Node[ID=" + nodeId + ", AdjEdges=" + adjEdges + ", IsVisited=" + isVisited + ", ShortestPath=" + shortestPath + "]";
		}
		
		
		public void addEdge(GraphNode destNode, LineColor lineColor, Integer time)
		{
			GraphEdge graphEdge = new GraphEdge(this, destNode, lineColor, time);
			if(this.adjEdges==null)
				this.adjEdges = new ArrayList<GraphEdge>();
			this.adjEdges.add(graphEdge);
		}
		
		public ArrayList<GraphEdge> getAdjEdges() {
			return this.adjEdges;
		}

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}

		public Path getShortestPath() {
			return shortestPath;
		}

		public void setShortestPath(Path shortestPath) {
			this.shortestPath = shortestPath;
		}	
	}
	
	private static void buildOneLineString(String lineString, LineColor lineColor, HashMap<String, GraphNode> graphNodesMap)
	{
		String[] lineSegments = lineString.split("#");
		
		for (String lineSegment : lineSegments) {
			String[] lineParams = lineSegment.split("-");
			String station1 = lineParams[0];
			String station2 = lineParams[1];
			Integer time = Integer.valueOf(lineParams[2]);
			
			GraphNode station1Node = graphNodesMap.get(station1);
			GraphNode station2Node = graphNodesMap.get(station2);
			
			if(station1Node==null)
				station1Node = new GraphNode(station1);
			if(station2Node==null)
				station2Node = new GraphNode(station2);
			
			station1Node.addEdge(station2Node, lineColor, time);
			station2Node.addEdge(station1Node, lineColor, time);
			
			graphNodesMap.put(station1, station1Node);
			graphNodesMap.put(station2, station2Node);
		}
	}
	
	private static HashMap<String, GraphNode> buildGraph(String[] lineStrings)
	{
		HashMap<String, GraphNode> graphNodesMap = new HashMap<String, GraphNode>();
		
		if(lineStrings.length!=4)
			System.err.println("Lines count doesn't equal 4");
		else {
			buildOneLineString(lineStrings[0], LineColor.BLACK, graphNodesMap);
			buildOneLineString(lineStrings[1], LineColor.BLUE, graphNodesMap);
			buildOneLineString(lineStrings[2], LineColor.RED, graphNodesMap);
			buildOneLineString(lineStrings[3], LineColor.GREEN, graphNodesMap);
		}
		
		return graphNodesMap;
	}
	
	private static GraphNode getOriginStationNode(String input2, HashMap<String, GraphNode> graphNodesMap)
	{
		String[] stations = input2.split("#");
		return graphNodesMap.get(stations[0]);
	}
	
	private static GraphNode getDestStationNode(String input2, HashMap<String, GraphNode> graphNodesMap)
	{
		String[] stations = input2.split("#");
		return graphNodesMap.get(stations[1]);
	}
	
	private static GraphNode findUnvisitedNodeWithMinTime(HashMap<String, GraphNode> graphNodesMap)
	{
		Collection<GraphNode> graphNodes = graphNodesMap.values();
		GraphNode nodeWithMinTime = null;
		
		for (GraphNode graphNode : graphNodes)
		{
			if(!graphNode.isVisited())
			{
				if(nodeWithMinTime==null)
					nodeWithMinTime=graphNode;
				else if(graphNode.getShortestPath().compareTo(nodeWithMinTime.getShortestPath()) < 0)
					nodeWithMinTime = graphNode;
			}
		}
		
		return nodeWithMinTime;
	}
	
	private static Path addEdgeToPath(GraphEdge graphEdge, Path path)
	{
		Path retPath = null;
		ArrayList<GraphEdge> curPathEdges = path.getPathEdges();
		
		if(curPathEdges==null || curPathEdges.size()==0)
		{
			ArrayList<GraphEdge> retPathEdges = new ArrayList<GraphEdge>();
			retPathEdges.add(graphEdge);
			retPath = new Path(retPathEdges, (graphEdge.time + 15), 1, 0); 
		}
		else
		{
			retPath = new Path(new ArrayList<GraphEdge>(curPathEdges), path.getTotalTime(), path.getNoOfHalts(), path.getNoOfTransfers());
			GraphEdge lastEdge = curPathEdges.get(curPathEdges.size()-1);
			retPath.setTotalTime(retPath.getTotalTime() + graphEdge.time + 15);
			retPath.setNoOfHalts(retPath.getNoOfHalts()+1);
			if(!lastEdge.color.equals(graphEdge.color)) {
				retPath.setTotalTime(retPath.getTotalTime() + 30);
				retPath.setNoOfTransfers(retPath.getNoOfTransfers()+1);
			}
			retPath.getPathEdges().add(graphEdge);
		}
		
		return retPath;
	}
	
	private static void findShortestPathForNodes(HashMap<String, GraphNode> graphNodesMap, GraphNode originNode, GraphNode destNode)
	{
		originNode.setShortestPath(new Path(null, 0, 0, 0));
		
		while(true)
		{
			GraphNode unvisitedNodeWithMinTime = findUnvisitedNodeWithMinTime(graphNodesMap);
			if(unvisitedNodeWithMinTime==null || unvisitedNodeWithMinTime.equals(destNode)) break;
			unvisitedNodeWithMinTime.setVisited(true);
			
			List<GraphEdge> adjNodeEdges = unvisitedNodeWithMinTime.getAdjEdges();
			for (GraphEdge graphEdge : adjNodeEdges)
			{
				if(!graphEdge.destNode.isVisited())
				{
					Path destNodePath = addEdgeToPath(graphEdge, graphEdge.origNode.getShortestPath());
					if(destNodePath.compareTo(graphEdge.destNode.getShortestPath()) < 0)
						graphEdge.destNode.setShortestPath(destNodePath);
				}
			}
		}
	}
	
	private static String[] getRouteStringArray(GraphNode destNode)
	{
		ArrayList<String> pathStrings = new ArrayList<>();
		pathStrings.add("NC");
		
		ArrayList<GraphEdge> pathEdges = destNode.getShortestPath().getPathEdges();
		StringBuilder curString = new StringBuilder();
		
		for(int i=0; i<pathEdges.size(); i++)
		{
			if(i==0)
				curString.append(pathEdges.get(i).origNode.nodeId).append("-").append(pathEdges.get(i).destNode.nodeId).append("-").append(pathEdges.get(i).time);
			else
			{
				if(!pathEdges.get(i).color.equals(pathEdges.get(i-1).color)) {
					pathStrings.add(curString.toString());
					curString = new StringBuilder();
				}
				
				if(curString.length()>0)
					curString.append("#");
				
				curString.append(pathEdges.get(i).origNode.nodeId).append("-").append(pathEdges.get(i).destNode.nodeId).append("-").append(pathEdges.get(i).time);
			}
		}
		
		pathStrings.add(curString.toString());
		pathStrings.add("NC");
		return pathStrings.toArray(new String[]{});
	}
	
	public static String[] quickestroute(String[] input1,String input2)
    {
		HashMap<String, GraphNode> graphNodesMap = buildGraph(input1);
		GraphNode originNode = getOriginStationNode(input2, graphNodesMap);
		GraphNode destNode = getDestStationNode(input2, graphNodesMap);
		
		findShortestPathForNodes(graphNodesMap, originNode, destNode);
		String[] pathStrings = getRouteStringArray(destNode);
		
        return pathStrings;
    }
	
	public static void main(String[] args) {
		String[] input1 = new String[4];
		input1[0] = "1-2-30#2-3-25#3-4-30#4-5-45#5-6-30#6-7-15#7-8-60#8-9-40";
		input1[1] = "10-11-45#11-4-60#4-12-60#12-13-45#13-14-30#14-15-35";
		input1[2] = "1-3-40#3-4-25#4-16-30#16-17-15#17-18-20#18-19-30#19-20-25";
		input1[3] = "21-12-30#12-17-180#17-22-45";
		String input2 = "12#18";
		
		quickestroute(input1, input2);
	}
}
