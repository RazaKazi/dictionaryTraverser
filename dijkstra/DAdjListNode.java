/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
*/
public class DAdjListNode {

	private int vertexIndex;//index of the adjacent node
    private int edge;// weight of the edge between node in adjacnecy list and current node
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, . . .
	
    /* creates a new instance */
	public DAdjListNode(int n,int weight){
        edge= weight;
		vertexIndex = n;
	}
	
	public int getDVertexIndex(){
		return vertexIndex;
	}
	
	public void setVertexIndex(int n){
		vertexIndex = n;
	}
    public int getEdge(){
        return edge;
    }
    public void setEdge(int weight){
        edge=weight;
    }
	
}