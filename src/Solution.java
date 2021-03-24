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

        for (int i = 0; i < 5; i++) {
            finalAdjList.add(new ArrayList<>());
        }
        for (int i = 0; i < 5; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(1);
        adjList.get(0).add(2);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(4).add(3);


        for (int i = 0; i < adjList.size(); i++)
            if (!mainVisited[adjList.size() - (i + 1)])
                dfs(reverseGraph(adjList), mainVisited, adjList.size() - (i + 1));

        System.out.println(steps);
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(steps.get(i));
            finalAdjList.set(i, adjList.get(steps.get(i)));

        }
        System.out.println(finalAdjList);
    }

    public static void main(String[] args) {
        new Solution();
    }

    /* public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
         //code here
     }*/
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

    /*  public ArrayList<Integer> getFinishingTimes(ArrayList<ArrayList<Integer>> adj){

      }*/
    public void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int start) {
        System.out.println(start);
        System.out.println(adj);
        mainVisited[start] = true;

        if (adj.get(start).size() == 0) {


            steps.put(start, currentSteps);
            currentSteps++;
        } else {
            for (int i = 0; i < adj.get(start).size(); i++) {
                System.out.println("start" + start + "i:" + i);
                if (!mainVisited[adj.get(start).get(i)]) {

                    dfs(adj, mainVisited, adj.get(start).get(i));
                    if (i == adj.get(start).size() - 1) {
                        currentSteps++;
                        steps.put(start, currentSteps);
                    }
                } else if (i == adj.get(start).size() - 1) {


                    steps.put(start, currentSteps);
                    currentSteps++;
                }

            }
        }
    }


}
