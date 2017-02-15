package DataStructures;

import java.util.Scanner;

//Range Sum Query (with lazy propagation)

public class SegmentTree { // 1-based DS, OOP // 1-based so leaves are 2*p and
							// 2*p+1

	int N; // the number of elements in the array as a power of 2 (i.e. after
			// padding)
	int[] array, sTree, lazy;

	SegmentTree(int[] in) {
		array = in;
		N = in.length - 1;
		sTree = new int[N << 1]; // no. of nodes = 2*N - 1, we add one to cross
									// out index zero
		lazy = new int[N << 1];
		build(1, 1, N);
	}

	void build(int node, int b, int e) // O(n)
	{
		if (b == e)
			sTree[node] = array[b];
		else {
			build(node << 1, b, (b + e) / 2);	//2*p,low,middle
			build((node << 1) + 1, (b + e) / 2 + 1, e);	// 2*p+1 , middle+1,high
			sTree[node] = sTree[node << 1] + sTree[(node << 1) + 1]; // if min sum stree = min(bothNodes);
		}
	}

	void change_point(int index, int val) // O(log n)
	{
		index += N - 1;
		sTree[index] = val;
		while (index > 1) {
			index >>= 1;// parent formula = (i-1)/2;-> i/2 here because 1 based 
			sTree[index] = sTree[index << 1] + sTree[(index << 1) + 1];// if min sum stree = min(bothNodes);
		}
	}

	void update_point(int index, int val) // O(log n)
	{
		index += N - 1;
		sTree[index] += val;
		while (index > 1) {
			index >>= 1;// parent formula = (i-1)/2;-> i/2 here because 1 based 
			sTree[index] = sTree[index << 1] + sTree[(index << 1) + 1];// if min sum stree = min(bothNodes);
		}
	}

	void update_range(int i, int j, int val) // O(log n)
	{
		update_range(1, 1, N, i, j, val);
	}

	void update_range(int node, int b, int e, int i, int j, int val) {// b minimum , e maximum , i min query , j max query
		if (i > e || j < b)
			return;
		if (b >= i && e <= j) {
			sTree[node] += (e - b + 1) * val; // adding the new sum value here for the node .
			lazy[node] += val;
		} else {
			propagate(node, b, e);
			update_range(node << 1, b, (b + e) / 2, i, j, val);
			update_range((node << 1) + 1, (b + e) / 2 + 1, e, i, j, val);
			sTree[node] = sTree[node << 1] + sTree[(node << 1) + 1];
		}

	}

	void propagate(int node, int b, int e) {
		int mid = (b + e) / 2;
		lazy[node << 1] += lazy[node]; //left = lazy of node
		lazy[(node << 1) + 1] += lazy[node]; // right = lazy of node ;
		sTree[node << 1] += (mid - b + 1) * lazy[node];// adding the new sum value here for the node .
		sTree[(node << 1) + 1] += (e - mid) * lazy[node];// adding the new sum value here for the node .
		lazy[node] = 0;
	}

	int query(int i, int j) {
		return query(1, 1, N, i, j);
	}

	int query(int node, int b, int e, int i, int j) // O(log n)
	{
		if (i > e || j < b)
			return 0;
		if (b >= i && e <= j)
			return sTree[node];
		propagate(node, b, e);
		int q1 = query(node << 1, b, (b + e) / 2, i, j);
		int q2 = query((node << 1) + 1, (b + e) / 2 + 1, e, i, j);
		return q1 + q2;// will return minimum in case of Min range

	}

	void printTree() {
		for (int i = 0; i < sTree.length; i++) {
			System.out.printf("(%s,%s)", i, sTree[i]);
		}
		System.out.println();
	}

	void printLazy() {
		for (int i = 0; i < lazy.length; i++) {
			System.out.printf("(%s,%s)", i, lazy[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int N = 1;
		while (N < n)
			N <<= 1; // padding

		int[] in = new int[N + 1];
		for (int i = 1; i <= n; i++)
			in[i] = sc.nextInt();
SegmentTree t = new SegmentTree (in);
t.printTree();
		sc.close();
	}

}