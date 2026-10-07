
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class InsertionSort {
    /*
       Insertion sort can count inversions during the sorting,
       as a part of its sorting mechanism involves "shifting" its
       elements in a way that mimics the provided definition of an inversion.

       Although it runs a bit slower in the worst case than the other algorithms,
        its big-O time of O(n^2) is perfectly reasonable for 10,000 pages.
     */

    public static void main(String[] args) throws IOException {
        // processes the 5 test files and prints inversion counts
        for (int i = 1; i <= 5; i++) {
            int[] data = readSource("Testfiles/source" + i + ".txt");
            long inversions = insertionSort(data);
            System.out.println("source" + i + ": " + inversions);
        }
    }

    // pretty standard insertion sort, but counts inversions on the fly
    public static long insertionSort(int[] arr) {
        long inversions = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // shift elements right and count inversions
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                inversions++;
            }
            arr[j + 1] = key;
        }
        return inversions;
    }

    // reads stuff
    private static int[] readSource(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filename));
        int[] nums = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            nums[i] = Integer.parseInt(lines.get(i).trim());
        }
        return nums;
    }
}
