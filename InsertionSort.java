import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class InsertionSort {
    public long inv = 0;
    // pretty standard insertion sort, but counts inversions on the fly
    public long sort(int[] arr) {
    
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // shift elements right and count inversions
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                inv++;
            }
            arr[j + 1] = key;
        }
        return inv;
    }
}
