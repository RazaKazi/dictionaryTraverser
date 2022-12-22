import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest path (i.e. minimum number edges
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
	

  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data here
		int verticesNo = 0;
		ArrayList<String> wordList = new ArrayList<String>();
		while (in.hasNextLine()) {
			wordList.add(in.nextLine());
			verticesNo++;
		}		
		int indexFirst = wordList.indexOf(word1);
		int indexSecond = wordList.indexOf(word2);
		reader.close();
		in.close();
		//create graph over here
		Graph G = new Graph(verticesNo,wordList);
		for (Vertex vertex1:G.getVertices()){
			for (Vertex vertex2:G.getVertices()){
				int wordDifference = 0;
				for(int i = 0; i<5; i++){
					if(vertex1.getWord().charAt(i) != vertex2.getWord().charAt(i)){
						wordDifference++;
					}
				}
				if (wordDifference == 1){
					vertex1.addToAdjList(vertex2.getIndex());
				}
			}
		}
		// do the work here
		G.bfs(G.getVertex(indexFirst));
		ArrayList<Vertex> path = G.pathFinder(G.getVertex(indexFirst),G.getVertex(indexSecond));
		if (path == null){
			System.out.println("Word ladder not found");
		} else {
			System.out.println("Shortest word ladder is of length: " + (path.size()-1));
			for (Vertex v:path){
				System.out.println(v.getWord());
			}
		}
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}


}
