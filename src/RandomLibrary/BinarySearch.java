package RandomLibrary;

import java.util.Arrays;

public class BinarySearch {
	int[] data;
	    int size;
	
	    public boolean binarySearch(int key) 
	    {
	         int low = 0;
	         int high = size - 1;
	          
	         while(high >= low) {
	             int middle = (low + high) / 2;
	             if(data[middle] == key) {
	                 return true;
	             }
	             if(data[middle] < key) {
	                 low = middle + 1;
	             }
	             if(data[middle] > key) {
	                 high = middle - 1;
	             }
	        }
	        return false;
	   }
	    public static int indexOf(int[] a, int key) {
	        int lo = 0;
	        int hi = a.length - 1;
	        while (lo <= hi) {
	            // Key is in a[lo..hi] or not present.
	            int mid = lo + (hi - lo) / 2;
	            if      (key < a[mid]) hi = mid - 1;
	            else if (key > a[mid]) lo = mid + 1;
	            else return mid;
	        }
	        return -1;
	    }
	    
	   // to find biggest element less than x 
	    
//	    private static int binarySearch(int[] arr, int left, int right, int temp) {
//			if(left + 1 >= right){
//				if(arr[left] <= temp){
//					return right;
//				}
//				else{
//					return left;
//				}
//			}
//			int mid = (left + right)/2;
//			if(arr[mid] <= temp){
//				return binarySearch(arr, mid, right, temp);
//			}
//			else{
//				return binarySearch(arr, left, mid, temp);
//			}
//		}
	    
		public static int minElementGreaterThanOrEqualToKey(int A[], int key,
				int imin, int imax) {

			// Return -1 if the maximum value is less than the minimum or if the key
			// is great than the maximum
			if (imax < imin || key > A[imax])
				return -1;

			// Return the first element of the array if that element is greater than
			// or equal to the key.
			if (key < A[imin])
				return imin;

			// When the minimum and maximum values become equal, we have located the
			// element.
			if (imax == imin)
				return imax;

			else {
				// calculate midpoint to cut set in half, avoiding integer overflow
				int imid = imin + ((imax - imin) / 2);

				// if key is in upper subset, then recursively search in that subset
				if (A[imid] < key)
					return minElementGreaterThanOrEqualToKey(A, key, imid + 1, imax);

				// if key is in lower subset, then recursively search in that subset
				else
					return minElementGreaterThanOrEqualToKey(A, key, imin, imid);
			}
		}
}
