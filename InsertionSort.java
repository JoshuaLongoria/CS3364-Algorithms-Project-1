import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class InsertionSort {

    // pretty standard insertion sort, but counts inversions on the fly
    public static long sort(int[] arr) {
        long inv = 0;
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
