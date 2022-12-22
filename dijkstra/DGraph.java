import java.util.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 class to represent an undirected DGraph using adjacency lists
 */
public class DGraph {

	private DVertex[] Dvertices; // the (array of) Dvertices
	private int numDvertices = 0; // number of Dvertices

	// possibly other fields representing properties of the DGraph

	/**
	 creates a new instance of DGraph with n Dvertices
	*/
	public DGraph(int n, ArrayList<String>wordList) {
		numDvertices = n;
		Dvertices = new DVertex[n];
		for (int i = 0; i < n; i++)
			Dvertices[i] = new DVertex(i, wordList.get(i));
	}

	public int size() {
		return numDvertices;
	}

	public DVertex getDVertex(int i) {
		return Dvertices[i];
	}

	public void setDVertex(int i, String input) {
		Dvertices[i] = new DVertex(i,input);
	}

	public DVertex[] getDvertices(){
		return Dvertices;
	}
    //gets the lowest DVertex from the set of unvisited vertices (v not in S)
    private static DVertex getLowestDistanceDVertex(Set < DVertex > unsettledDVertices) {
        DVertex lowestDistanceDVertex = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (DVertex DVertex: unsettledDVertices) {
            int DVertexDistance = DVertex.getShortestPath();
            if (DVertexDistance < lowestDistance) {
                lowestDistance = DVertexDistance;
                lowestDistanceDVertex = DVertex;
            }
        }
        return lowestDistanceDVertex;
    }
    // check if there is a new shortest path available and if there is then change the previous predecessor to the node that caused the change
    private static void calculateShortestPath(DVertex evaluationDVertex,int edgeWeight, DVertex sourceDVertex) {
        int sourceDistance = sourceDVertex.getShortestPath();
        if (sourceDistance + edgeWeight < evaluationDVertex.getShortestPath()) {
            evaluationDVertex.setShortestPath((sourceDistance + edgeWeight));
            evaluationDVertex.setPredecessor(sourceDVertex.getIndex());
        }
    }

    /**
	 carry out dijksta's shortest path search on the graph
	 */
    public DGraph DSP(DGraph Dgraph, DVertex start) {

        start.setShortestPath(0);//since all vertices are initialised to inifinity set the start vertex to 0
    
        Set<DVertex> settledDvertices = new HashSet<>();//set of visited vertices (S)
        Set<DVertex> unsettledDvertices = new HashSet<>();// set of unvisited vertices
    
        unsettledDvertices.add(start);
    
        while (unsettledDvertices.size() != 0) {// while there are still unvisited DVertices
            DVertex currentDvertex = getLowestDistanceDVertex(unsettledDvertices);//travel to the DVertex that has the shortest path to the start vertex
            unsettledDvertices.remove(currentDvertex);
            for (DAdjListNode node: currentDvertex.getAdjList()){//iterate through all the adjacent nodes for the current node
                DVertex adjNode = Dgraph.getDVertex(node.getDVertexIndex());
                int edgeWeight = node.getEdge();
                
                if (!settledDvertices.contains(adjNode)) {//check if the neighbour has been settled if it has then perform calculations
                    calculateShortestPath(adjNode, edgeWeight, currentDvertex);
                    unsettledDvertices.add(adjNode);
                }
            }
            settledDvertices.add(currentDvertex);
        }
        return Dgraph;
    }

	

	//create function that returns the path from the final 
    public ArrayList<DVertex> pathFinder(DVertex start,DVertex end){
		DVertex currentDVertex=end;//work backwards form the end DVertex
		ArrayList<DVertex> returnPath= new ArrayList<DVertex>();// make an array list where the path will be appended
		returnPath.add(currentDVertex);//add the first word to the list
		//check if the current DVertex is equal to its predecessor in which case the starting word has been reached
		//or check if NULL has been reached which happens if the starting word is not found in the chain of predecessors
		while((currentDVertex.getPredecessor()!=0) && (currentDVertex!=null)){
			currentDVertex=this.getDVertex(currentDVertex.getPredecessor());
			returnPath.add(currentDVertex);
		}
		Collections.reverse(returnPath);
		if (returnPath.get(0).getWord() == start.getWord()){
			return returnPath;
		}
		else{
			return null;
		}

	}

}
