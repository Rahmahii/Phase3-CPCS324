package phase3;
/**
 * Every Edge must has informations 
 * @author Rahmah ,shatha, and raoom
 */
 public  class Edge {
    int src, dest, rev, cap, f;
/**
 * 
 * @param s the source node
 * @param t the distance
 * @param rev distance size
 * @param cap the capacity
 */
    public Edge(int s, int t, int rev, int cap) {
      this.src = s;
      this.dest = t;
      this.rev = rev;
      this.cap = cap;
    }
  }