import java.util.LinkedList;


/**
 class to represent a  vertex in a graph
*/
public class DVertex {
   
    private LinkedList<DAdjListNode> adjList ; // the adjacency list 
    private int index; // the index of this vertex in the graph
    
    //possibly other fields, for example representing data
    // stored at the node, whether the vertex has been visited
    // in a traversal, its predecessor in such a traversal, etc.
    
    String word; // word contained within the vertex
	boolean visited; // whether vertex has been visited in a traversal
    int predecessor; // index of predecessor vertex in a traversal
    int shortestPath; // the shortest from the node to search
     
    /**
	 creates a new instance of DVertex
	 */
    public DVertex(int n, String input) {
        word=input;
    	adjList = new LinkedList<DAdjListNode>();
    	index = n;
        shortestPath=Integer.MAX_VALUE;//set the shortest path to infinity or in this case a large value
    }
    
    /**
	 copy constructor
	*/
    public DVertex(DVertex v){
        word=v.getWord();
    	adjList = v.getAdjList();
    	index = v.getIndex();
        shortestPath=v.getShortestPath();
    }
     
    
    public LinkedList<DAdjListNode> getAdjList(){
        return adjList;
    }
    
    public int getIndex(){
    	return index;
    }
    
    public void setIndex(int n){
    	index = n;
    }
    
    public int getPredecessor(){
    	return predecessor;
    }
    
    public void setPredecessor(int n){
    	predecessor = n;
    }
    
    public void addToAdjList(int n, int edge){
        adjList.addLast(new DAdjListNode(n,edge));
    }
    
    public int vertexDegree(){
        return adjList.size();
    }

    public String getWord(){
        return word;
    }
    public void setWord(String input){
        word=input;
    }
    public int getShortestPath(){
        return shortestPath;
    }
    public void setShortestPath(int n){
        shortestPath=n;
    }
}
