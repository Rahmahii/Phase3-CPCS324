package phase3;

import java.util.ArrayList;
import java.util.List;
/**
 * <h1>Edmonds-Karp algorithm </h1>
 * the <b>MaximumFlow</b> is a program that finds the flow augmentation paths of the graph
 * 
 * @author Rahmah ,shatha, and raoom
 */
// class for create graph 
public class MaximumFlow {
/**
 * create graph with specific number of nodes 
 * @param nodes the number of nodes in graph
 * @return list of edges 
 */
    public static List<Edge>[] createGraph(int node) {
      // list for Edge 
      // node in each index in the list ... the list length = number of nodes 
      List<Edge>[] graph = new List[node];
      //  each node in the graph list has ArrayList for the children 
      for (int i = 1; i < node; i++)
        graph[i] = new ArrayList<>();
      return graph;
    }
//=====================================
/**
 * add edge to the graph by its source, distance, and capacity 
 * @param graph the main graph of the program 
 * @param src the source of the new edge 
 * @param dest the distance of the new edge
 * @param capacity the capacity of the new edge
 */

   public static void addEdge(List<Edge>[] graph, int src, int dest, int capacity) {
 // public Edge(int s, int t, int rev, int cap) in Edge class for src and dest
 // in rev we store the dest size in src and src size in dest
      graph[src].add(new Edge(src, dest, graph[dest].size(), capacity));
 //  graph[src].size() - 1---> because we're not go back and 0 because we don't have capacity for (back)
      graph[dest].add(new Edge(dest, src, graph[src].size() - 1, 0));
      
    }

//=====================================
/**
 * calculate the max flow of the graph 
 * @param graph the main graph of the program
 * @param s source vertex=1 
 * @param d sink(distance) vertex =6
 */
   public static void maxFlow(List<Edge>[] graph, int s, int d) {
      // start with flow = zero 
      int flow = 0;
      // array QmaxFlow 
      int[] AmaxFlow = new int[graph.length];
      while (true) {
        // start with f = 0
        int f = 0;
        // index 1 = s 
        AmaxFlow[f++] = s;
        
        Edge[] Thepred = new Edge[graph.length];
        // pred[d] has nodes and all = null 
        for (int i = 0; i < f && Thepred[d] == null; i++) {
         // cur = s  for example first cur = 1
          int cur = AmaxFlow[i];
         // to acsses all s children 
          for (Edge e : graph[cur]) {
            // the edges between the s and his children
            // pred[e.dest] == null we did not visitid yet 
            // if the capacty for the edge > flow
            if (Thepred[e.dest] == null && e.cap > e.f) {
              Thepred[e.dest] = e;
              AmaxFlow[f++] = e.dest;
            }
          }
        }
        System.out.println();
        // the pred for the last node 
        if (Thepred[d] == null)
          break;
       // start DF with large number 
        int DF = Integer.MAX_VALUE;
       
        // for store the other edges (not our path)
        ArrayList<String> authPath = new ArrayList<>();
       // here we go back from the des to src using the path we found 
        for (int i = d; i != s; i = Thepred[i].src) 
          DF = Math.min(DF, s);
        for (int j = d; j != s; j = Thepred[j].src) {
          Thepred[j].f += DF;
          graph[Thepred[j].dest].get(Thepred[j].rev).f -= DF;
          authPath.add(j+"");
        }
        authPath.add(s+"");
        
   // print the result     
        System.out.print("Augmenting path: ");
        for(int i=authPath.size()-1;i>=0;i--){
          System.out.print(" "+authPath.get(i)+" "); 
          if(i>0)
                System.out.print("->");
        }
        System.out.println("flow "+flow);
        flow += DF;
        System.out.println("Update  flow "+flow);
        System.out.println("---------------------------------------------");
      }
       System.out.println("The Max Flow is : " +flow);
       System.out.println("---------------------------------------------");
       



  // for min cut 
    boolean[] isVisited = new boolean[graph.length];     
    dfs(graph,s,d, isVisited);
    
        System.out.println("Minimun Cuts :");
    for (int i = 1; i < graph.length; i++) {
        Edge[] pred = new Edge[graph.length];
        for (Edge e : graph[i]) {
           if ((e.cap > 0) && (e.cap-e.f) >= 0 && isVisited[i] && !isVisited[e.dest]) {
                System.out.println(i + " - " + e.dest);
            }
         }
    }
              System.out.println("----------------Thank you---------------------"); 
  }
    /**
     * 
     * @param rGraph the main graph of the program
     * @param s source vertex=1
     * @param d sink(distance) vertex =6
     * @param visited boolean array gives the true for visited vertex
     */
    private static void dfs(List<Edge>[] rGraph, int s,int d,
                                boolean[] visited) {
        visited[s] = true;
        Edge[] pred = new Edge[rGraph.length];
        for (Edge e : rGraph[s]) {
            //System.out.println(s+" "+e.dest+" "+e.cap+" "+e.f);
                if ((e.cap-e.f) > 0 && !visited[e.dest]) {
                    dfs(rGraph, e.dest,d, visited);
                }
        }
    }
/**
 * for execute the program
 * @param args unused
 */
   public static void main(String[] args) {
     //   first create our Graph 
        List<Edge>[] graph = createGraph(7);
     // add the Edges for our graph
        addEdge(graph, 1, 2, 2);
        addEdge(graph, 1, 3, 7);
        addEdge(graph, 2, 4, 3);
        addEdge(graph, 2, 5, 4);
        addEdge(graph, 3, 4, 4);
        addEdge(graph, 3, 5, 2);
        addEdge(graph, 4, 6, 1);
        addEdge(graph, 5, 6, 5);
        System.out.println("start with flow = zero");
        System.out.println("---------------------------------------------");

     // src =1    dest=6
        maxFlow(graph, 1,6 );
       
    }
    
}