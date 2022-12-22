import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 creates a new instance of Graph with n vertices
	*/
	public Graph(int n, ArrayList<String>wordList) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i, wordList.get(i));
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i, String input) {
		vertices[i] = new Vertex(i,input);
	}

	public Vertex[] getVertices(){
		return vertices;
	}


	/**
	 carry out a breadth first search/traversal of the graph
	 */
	public void bfs(Vertex start) {
		
		for (Vertex v : vertices) v.setVisited(false); // initialise (all vertices unvisted)
  		LinkedList<Vertex> queue = new LinkedList<Vertex>(); // queue to process

		Vertex initial = start;//set teh inital vertex of the BFS to teh start vertex
		initial.setVisited(true);//set visitied to true
		initial.setPredecessor(initial.getIndex()); // v was initial/starting vertex so set its predecessor to itself
		//queue.add(initial); // add to queue for processing	
		queue.add(initial); // add to queue for processing
		while (!queue.isEmpty()) {
			Vertex u = queue.remove(); // get next vertex to process
			LinkedList<AdjListNode> list = u.getAdjList(); // get adjacency list of the vertex
			for (AdjListNode node : list) {
				Vertex w = vertices[node.getVertexIndex()];
				if (!w.getVisited()) { // if vertex has not been visited
					w.setVisited(true);
					w.setPredecessor(u.getIndex()); // w was found using u so this is the predecessor
					queue.add(w); // add to queue for processing
				}
			}
		}
	}
	
	//create function that returns the path from the final 
	public ArrayList<Vertex> pathFinder(Vertex start,Vertex end){
		Vertex currentVertex=end;//work backwards form the end vertex
		ArrayList<Vertex> returnPath= new ArrayList<Vertex>();// make an array list where the path will be appended
		returnPath.add(currentVertex);//add the first word to the list
		//check if the current vertex is equal to its predecessor in which case the starting word has been reached
		//or check if NULL has been reached which happens if the starting word is not found in the chain of predecessors
		while((currentVertex.getWord() != this.getVertex(currentVertex.getPredecessor()).getWord()) && (currentVertex!=null) && (currentVertex.getPredecessor()!=0)){
			currentVertex=this.getVertex(currentVertex.getPredecessor());
			returnPath.add(currentVertex);
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
