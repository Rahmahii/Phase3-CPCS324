package phase3;

import java.util.*;
import java.lang.*;
import java.io.*;
 /**
  * <h1> maximum bipartite matching </h1>
  *The <b>MaximumBipartite</b> program implements an application that
  * find an assignment of hospitals to applicants in such that,
  * as many applicants as possible get positions in the hospital   
  * 
  * @author Rahmah ,shatha, and raoom 
  */
public class MaximumBipartite
{ 
    String applicants[]={"Ahme","Mahmoud","Ema","Fatimah","Kamel","Nojoo"};
    String hospitals[]={"King Abdelaziz University","King Fahd","East Jeddah","King Fahad Armed Forces","King Faisal Specialist","Ministry of National Guard"};
    // M is number of applicants
    // and N is number of jobs
    static final int M = 6;
    static final int N = 6;
    int countMatching;
    /**
     * check if the hospital has available position for applicant
     * @param bpGraph each element is: an applicant(dimension 1) assigned to a hospital(dimension 2)
     * @param app The number of applicants
     * @param seen assign true for visited hospital and false for unvisited hospital
     * @param matchR match a hospital to an applicant
     * @return true if a matching for vertex u is possible 
     */
   public boolean bpm(boolean bpGraph[][], int app,boolean seen[], int matchR[])
    {
        for (int v = 0; v < N; v++)    // Try every job one by one
        {
           // If applicant "app" is interested 
            // in job v and v is not visited
            if (bpGraph[app][v] && !seen[v]) // Mark v as visited
            {  seen[v] = true; 
            
                // If job 'v' is not assigned to , an applicant OR previously
                // assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the 
                // above line, matchR[v] in the following
                // recursive call will not get job 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],seen, matchR))
                {
                    System.out.println(applicants[app]+" take "+hospitals[v]);
                    matchR[v] = app;
                    this.countMatching++;
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * calculate The maximum applicants each can assigned to one hospital
     * @param bpGraph each element is: an applicant assigned to a hospital 
     * @return maximum number of applicants
     */
    public int maxBPM(boolean bpGraph[][])
    {
       
    // Returns maximum number 
    // of matching from M to N
        int matchR[] = new int[N];  // An array to keep track of the 
          // applicants assigned to jobs. 
        for(int i = 0; i < N; ++i)
            matchR[i] = -1;  // The value of matchR[i] is the 
         // applicant number assigned to job i, 
               // the value -1 indicates nobody is assigned.
                // Initially all jobs are available
                     int result = 0; 
        for (int app = 0; app < M; app++)
        {
            
            // Mark all jobs as not seen 
            // for next applicant.
            boolean seen[] =new boolean[N] ; 
            for(int i = 0; i < N; ++i)
                seen[i] = false;
              // Find if the applicant can get a job

            if (bpm(bpGraph, app, seen, matchR))
            {
                result++;
                 System.out.println("\nUpdate maximum number of applicants: "+result);
            }
            
        }
        return result;
    }
  /**
   * for execute the program
   * @param args unused 
   * @throws java.lang.Exception  Exception
   */
    public static void main (String[] args) throws java.lang.Exception
    {
          // Create a bpGraph shown
        MaximumBipartite m = new MaximumBipartite();
        m.countMatching = 0;
        boolean bpGraph[][] = new boolean[][]{
                              {true, true, false, false, false, false},
                              {false, false, false, false, false, true},
                              {true, false, false, true, false, false},
                              {false, false, true, false, false, false},
                              {false, false, false, true, true, false},
                              {false, false, false, false, false, true}
                            };
      
        System.out.println("Maximum number of application: 0");
        System.out.println("\nMaximum number of applicants that can be assigned to hospitals : "+m.maxBPM(bpGraph));  
        System.out.println( "Maximum bipartite matching : "+m.countMatching);
    }
}
