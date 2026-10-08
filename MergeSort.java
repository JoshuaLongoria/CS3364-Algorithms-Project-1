import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MergeSort{

    // public variable to count inversions in each file
    public long inv = 0;
    public static long sort(int[] array){
        MergeSort m = new MergeSort();// reset
        int[] sorted = m.mergesort(array);
        System.arraycopy(sorted, 0, array, 0, sorted.length);
        return m.inv;
    }
    private int[] mergesort(int[] array){
        // base case
        if(array.length <= 1)
        {
            return array;

        } else 
        {
            int q = array.length / 2;

            // rounds up the midpoint if the size of the array is odd
            if(array.length %2 != 0)
            {
                q++;
            }
            // initializes L and R arrays
            int[] L = new int[q];
            int[] R = new int[array.length - q];
            
            // copies the left half to L and the right half to R
            for(int i = 0; i < q; i++)
            {
                L[i] = array[i];
            }
            for(int i = 0; i < array.length - q; i++)
            {
                R[i] = array[i+q];
            }

            
            int[] LS = mergesort(L);
            int[] RS = mergesort(R);

        
            return merge(LS, RS);
        }
    }

    public int[] merge(int[] L, int[] R){

        // initializes a new array to put L and R when merged
        int[] B = new int[L.length + R.length];

        int i = 0; // index for left side
        int j = 0; // index for right side

        
        for(int k = 0; k < B.length; k++)
        {
            // adds right side if left is done
            if (i >= L.length) 
            {
                B[k] = R[j++];
                // adds left side if right is done or if left position is smaller than right position
            } else if (j >= R.length || L[i] <= R[j]) 
            {
                B[k] = L[i++];
                // otherwise adds right side and adds an inversion to the counter
            } else 
            {
                B[k] = R[j++];
                inv += (L.length-i);
            }
        }
        return B;
    }

}
