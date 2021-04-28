import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra {
    static final int[][] graph ={
        {0, 4, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0: Downer
        {4, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0}, // 1: Watson
        {5, 0, 0, 4, 0, 3, 0, 0, 0, 0, 0, 0, 0}, // 2: Lineham
        {3, 0, 4, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0}, // 3: Dickson
        {0, 3, 0, 5, 0, 0, 7, 0, 0, 0, 0, 0, 0}, // 4: Hackett
        {0, 0, 3, 5, 0, 0, 0, 3, 0, 0, 0, 0, 0}, // 5: O'Connor
        {0, 0, 0, 3, 7, 0, 0, 0, 4, 0, 0, 0, 8}, // 6: Ainslie
        {0, 0, 0, 0, 0, 3, 0, 0, 4, 6, 0, 0, 0}, // 7: Turner
        {0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 4, 3, 0}, // 8: Braddon
        {0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 5, 0, 0}, // 9: Acton
        {0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 0, 5, 0}, // 10: Civic
        {0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 5, 0, 3}, // 11: Reid
        {0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 3, 0}}; // 12: Campbell

    //Implementation of Dijkstra's algorithm on above graph (adjacency matrix)
    //in 0(n^2) to find length of minimal path to each node from the starting node
    //Based on algorithm at https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
    //Returns array of distances from start index
    static int[] findDistance(int start, int[][] inputGraph) {
        if (start > inputGraph.length || start < 0)
            return null;

        int[] distance = new int[inputGraph.length];
        boolean[] inPath = new boolean[inputGraph.length];

        //Set all distances to max value and all nodes to not in the path
        for (int i = 0; i < inputGraph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            inPath[i] = false;
        }

        //Distance to self is 0
        distance[start] = 0;

        for(int i = 0; i < inputGraph.length; i ++){
            int min = Integer.MAX_VALUE;
            int minInd = -1;
            //find the minimum distance from this node to another
            for(int j = 0; j < inputGraph.length; j++) {
                if(!inPath[j] && distance[j] <= min) {
                    min = distance[j];
                    minInd = j;
                }
            }
            inPath[minInd] = true;
            for (int location = 0; location < inputGraph.length; location ++) {
                if (!inPath[location] && inputGraph[minInd][location] != 0 &&
                        distance[minInd] != Integer.MAX_VALUE &&
                        distance[minInd] + inputGraph[minInd][location] < distance[location])
                    distance[location] = distance[minInd] + inputGraph[minInd][location];
            }
        }
        // print the constructed distance array
        return distance;
    }

    static void logicalDijkstra() throws IOException {
        System.out.println("------------------------------------------------");
        System.out.println("---------- Test distance to self is 0 ----------");
        System.out.println("Distance to self (0) is " + (findDistance(0, Dijkstra.graph))[0]);
        System.out.println("Distance to self (1) is " + (findDistance(1, Dijkstra.graph))[1]);
        System.out.println("Distance to self (2) is " + (findDistance(2, Dijkstra.graph))[2]);
        System.out.println("Distance to self (3) is " + (findDistance(3, Dijkstra.graph))[3]);
        System.out.println("Distance to self (6) is " + (findDistance(6, Dijkstra.graph))[6]);
        System.out.println("------------------------------------------------");

        //Testing cases found manually from graph
        System.out.println("----------- Test random known cases ------------");

        //Downer (0) to Watson (1) = 4
        System.out.println("Distance 0 to 1 is 4, yours: " + (findDistance(0, Dijkstra.graph))[1]);

        //Downer (0) to O'Connor (5) = 8
        System.out.println("Distance 0 to 5 is 8, yours: " + (findDistance(0, Dijkstra.graph))[5]);

        //O'Connor (5) to Reid (11) = 10
        System.out.println("Distance 5 to 11 is 10, yours: " + (findDistance(5, Dijkstra.graph))[11]);

        //Ainslie (6) to Civic (10) = 8
        System.out.println("Distance 6 to 10 is 8, yours: " + (findDistance(6, Dijkstra.graph))[10]);

        System.out.println("------------------------------------------------");

        System.out.println("------------- Test invalid cases ---------------");

        System.out.println("Node 15 is invalid, yours: " + (findDistance(15, Dijkstra.graph)));
        System.out.println("Node -1 is invalid, yours: " + (findDistance(-1, Dijkstra.graph)));

        System.out.println("------------------------------------------------");

        FlowerDeliveries.testAlgorithms();
    }


    static void empiricalDijkstra() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Which test would you like to run?");
        System.out.println("(For larger inputs, distances tend to 6, as min distance between nodes is 3 and graphs are highly connected)");
        System.out.println("[1] Size 10 ");
        System.out.println("[2] Size 1000 ");
        System.out.println("[3] Size 3000 ");
        System.out.println("[4] Size 7000 ");
        System.out.println("[5] Size 10000 ");
        System.out.println("[6] Size 100");

        int choice = in.nextInt();

        int randomCase = 4;
        int randomAnswer = 14;

        int size = 10;
        String test = "test-f2-1.txt";

        if  (choice == 2) {
            size = 1000;
            randomCase = 782;
            randomAnswer = 3;
            test = "test-f2-2.txt";
        } else if  (choice == 3) {
            size = 3000;
            randomCase = 172;
            randomAnswer = 11;
            test = "test-f2-3.txt";
        } else if  (choice == 4) {
            randomCase = 4823;
            size = 7000;
            randomAnswer = 11;
            test = "test-f2-4.txt";
        } else if (choice == 5) {
            randomCase = 8321;
            size = 10000;
            randomAnswer = 15;
            test = "test-f2-5.txt";
        } else if (choice == 6) {
            randomCase = 36;
            size = 100;
            randomAnswer = 10;
            test = "test-f2-6.txt";
        }

        BufferedReader br = new BufferedReader(new FileReader(test));

        int start = Integer.parseInt(br.readLine());
        String st;

        int[][] graph = new int[size][size];
        int[] output;
        int rowCounter = 0;
        int columnCounter = 0;

        while ((st = br.readLine()) != null) {
            //System.out.println("st: " + st);
            graph[columnCounter][rowCounter] = Integer.parseInt(st);

            //System.out.println(rowCounter);

            rowCounter++;
            if (rowCounter == size) {
                columnCounter++;
                rowCounter = 0;
                //System.out.println("cc:" + columnCounter);
            }
        }

        long startTime = System.nanoTime();

        output = findDistance(start, graph);

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println("Time Taken: " + duration);

        System.out.println("Output: " + Arrays.toString(output));

        System.out.println("Tests: ");
        System.out.println("Test path to self is 0: Path to self is " + graph[start][start]);
        boolean randCase = graph[start][randomCase] == randomAnswer;
        System.out.println("Test random case returns correct result: " + randCase);

        FlowerDeliveries.testAlgorithms();

    }

}

