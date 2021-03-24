import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> finalAdjList = new ArrayList<>();
    HashMap<Integer, Integer> steps = new HashMap<>();
    int currentSteps = 0;
    boolean[] mainVisited = new boolean[5];

    public Solution() {
        kosaraju(5);
    }

    public void init(int V) {
        for (int i = 0; i < V; i++) {
            finalAdjList.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(1).add(0);
        adjList.get(0).add(2);
        adjList.get(0).add(3);
        adjList.get(2).add(1);
        adjList.get(3).add(4);
    }

    public static void main(String[] args) {
        new Solution();
    }

    public int kosaraju(int V) {
        init(V);
        getFinishingTimes();
        changeOrder();
        initVisitedArray(V);
        int SCC = getScc();
        System.out.println(SCC);
        return SCC;
    }

    private void initVisitedArray(int V) {
        for (int i = 0; i < V; i++) {
            mainVisited[i] = false;
        }
    }

    //retrieve the number of Strongly connected components of the given graph
    public int getScc() {
        int numberOfSCCs = 0;
        for (int i = 0; i < finalAdjList.size(); i++)
            if (!mainVisited[adjList.size() - (i + 1)]) {
                dfsForSCC(finalAdjList, adjList.size() - (i + 1));
                numberOfSCCs++;
            }
        return numberOfSCCs;
    }


    //changing order according to finishing times
    public void changeOrder() {
        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++)
                adjList.get(i).set(j, steps.get(adjList.get(i).get(j)));

            finalAdjList.set(steps.get(i), adjList.get(i));

        }
    }

    //calculating finishing times to determine the starting vertex
    public void getFinishingTimes() {
        for (int i = 0; i < adjList.size(); i++)
            if (!mainVisited[adjList.size() - (i + 1)])
                dfsWithFinishingTimes(reverseGraph(adjList), adjList.size() - (i + 1));
    }

    //reversing the graph to edges to calculate the correct finishing times
    public ArrayList<ArrayList<Integer>> reverseGraph(ArrayList<ArrayList<Integer>> adj) {

        ArrayList<ArrayList<Integer>> reversedGraph = new ArrayList<>();

        for (int i = 0; i < adj.size(); i++) {
            reversedGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {

                if (adj.get(i).contains(adj.get(i).get(j)))
                    reversedGraph.get(adj.get(i).get(j)).add(i);
            }
        }
        return reversedGraph;

    }


    //dfs to the reversed graph to calculate finishing times
    public void dfsWithFinishingTimes(ArrayList<ArrayList<Integer>> adj, int start) {

        mainVisited[start] = true;

        if (adj.get(start).size() == 0) {


            steps.put(start, currentSteps);
            currentSteps++;
        } else {
            for (int i = 0; i < adj.get(start).size(); i++) {

                if (!mainVisited[adj.get(start).get(i)]) {

                    dfsWithFinishingTimes(adj, adj.get(start).get(i));
                    if (i == adj.get(start).size() - 1) {

                        steps.put(start, currentSteps);
                        currentSteps++;
                    }
                } else if (i == adj.get(start).size() - 1) {


                    steps.put(start, currentSteps);
                    currentSteps++;
                }

            }
        }
    }

    //dfs on the original graph, but with the right starting vertex
    public void dfsForSCC(ArrayList<ArrayList<Integer>> adj, int start) {

        mainVisited[start] = true;


        for (int i = 0; i < adj.get(start).size(); i++) {

            if (!mainVisited[adj.get(start).get(i)]) {

                dfsForSCC(adj,  adj.get(start).get(i));

            }

        }
    }


}
