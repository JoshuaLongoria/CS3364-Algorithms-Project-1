public class InsertionSort {

    // this is a basic program for the first implementation of the third algorithm for the project

    
    /*
        Insertion sort can count inversions during the sorting,
        as a part of its sorting mechanism involves "shifting" its
        elements in a way that mimics the provided definition of an inversion.

        Although it runs a bit slower in the worst case than the other algorithms,
        its big-O time of O(n^2) is perfectly reasonable for 10,000 pages.
     */

    public static int insertionSort(int[] arr) {
        int n = arr.length;
        int inversions = 0;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                inversions++;
            }
            arr[j + 1] = key;
        }
        return inversions;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 8, 9, 6};
        int inversions = insertionSort(arr);
        System.out.println("inversions: " + inversions);
    }
}
