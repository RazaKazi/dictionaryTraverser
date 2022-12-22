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

		// // create graph here
		int indexFirst = wordList.indexOf(word1);
		int indexSecond = wordList.indexOf(word2);
		reader.close();
		in.close();
		//create graph over here
		DGraph G = new DGraph(verticesNo,wordList);
		for (DVertex DVertex1:G.getDvertices()){
			for (DVertex DVertex2:G.getDvertices()){
				int edge=0;
				int weight1=0; 
				int weight2=0;
				int wordDifference = 0;
				for(int i = 0; i<5; i++){
					if(DVertex1.getWord().charAt(i) != DVertex2.getWord().charAt(i)){
						wordDifference++;
						weight1=DVertex1.getWord().charAt(i);//get the "weight" of the letter at the mismatched index 
						weight2=DVertex2.getWord().charAt(i);//get the "weight" of the letter at the mismatched index 
						if (weight1>=weight2){//get the highest weigth so we can get the absolute value 
							edge=weight1-weight2;//calculate the difference to get the two words
						}
						else{
							edge=weight2-weight1;
						}
					}
				}
				if (wordDifference == 1){
					DVertex1.addToAdjList(DVertex2.getIndex(),edge);
				}
			}
		}
		G= G.DSP(G, G.getDVertex(indexFirst));
		ArrayList<DVertex> path = G.pathFinder(G.getDVertex(indexFirst),G.getDVertex(indexSecond));
		if (path == null){
			System.out.println("Word ladder not found");
		} else {
			System.out.println("total weights traversed: " + G.getDVertex(indexSecond).getShortestPath());
			System.out.println("Shortest word ladder is of length: " + (path.size()-1));
			for (DVertex v:path){
				System.out.println(v.getWord());
			}
		}
		



		
		//for (DVertex v: vertices){}
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}


}
