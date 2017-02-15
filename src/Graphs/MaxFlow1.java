package Graphs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//Try to test the implementation with some input of your choice
public class MaxFlow1 {

	static final int INF = (int)1e9;
	static int V, s, t;						//input
	static ArrayList<Integer>[] adjList;	//input
	static int[][] res;						//input
	static int[] p;
	
	static int edmondsKarp()
	{
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[V];
			Arrays.fill(p, -1);
			p[s] = s;
			q.add(s);
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == t)
					break;
				for(int v: adjList[u])
					if(res[u][v] > 0 && p[v] == -1) // visited and parent in the same data structure -j
					{
						p[v] = u;
						q.add(v);
					}
			}
			
			if(p[t] == -1)
				break;
			mf += augment(t, INF);
			
		}
		
	
		
		return mf;
	}
	
	static int augment(int v, int flow)
	{
		if(v == s)
			return flow;
		flow =  augment(p[v], Math.min(res[p[v]][v],flow));
		res[p[v]][v] -= flow; // decrease capacity of forward edge -j
		res[v][p[v]] += flow; // increase capacity of backward edge -j
		/*
		  By increasing the capacity of a backward edge (j → i), Ford Fulkerson’s method allows
future iteration (flow) to cancel (part of) the capacity used by a forward edge (i → j) that
was incorrectly used by some earlier flow(s) -j :- CP 
		 */
		return flow;
	}
}






