import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sorting {
    
    private static final int SOURCE_COUNT = 5;
    private static final int PAGE_COUNT = 10000;
    
    public static void main(String[] args) {
        
        // Get filename and initialize arrays
        
        String prefix = "Testfiles/";
        ArrayList<Integer> valuesList = new ArrayList<Integer>();
        
        System.out.print("Enter the filename of the data to be sorted including the extension: ");
        Scanner line = new Scanner(System.in);
        String inputName = line.nextLine().trim();
        String filename;
        if (inputName.startsWith("Testfiles/") || inputName.startsWith("Testfiles\\")) {
            filename = inputName;
        } else {
            filename = prefix.concat(inputName);
        }

        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextInt()) {
                valuesList.add(input.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
            return;
        }

        int[] values = new int[valuesList.size()];
        for (int idx = 0; idx < valuesList.size(); idx++) {
            values[idx] = valuesList.get(idx);
        }
        
        int i = 0;
        while (i == 0) {
            System.out.print("Choose the filesort system: 1. quicksort 2. mergesort 3. insertion sort: ");
            if (!line.hasNextInt()) {
                break;
            }
            int choice = line.nextInt();
            switch (choice) {
                case 1:
                    long qInv = quicksort(values, 0, values.length - 1);
                    System.out.println("Sorted with QuickSort. Inversions: " + qInv);
                    i = 1;
                    break;
                case 2:
                    long mInv = mergesort(values, 0, values.length - 1);
                    System.out.println("Sorted with MergeSort. Inversions: " + mInv);
                    i = 1;
                    break;
                case 3:
                    long iInv = Sort3(values);
                    System.out.println("Sorted with Sort3 (InsertionSort). Inversions: " + iInv);
                    i = 1;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }        
        
        line.close();
    }

    public static long quicksort(int[] values, int low, int high) {
        return Quicksort.quicksort(values, low, high);
    }

    public static long mergesort(int[] values, int low, int high) {
        MergeSort ms = new MergeSort();
        if (low != 0 || high != values.length - 1) {
            int[] sub = Arrays.copyOfRange(values, low, high + 1);
            ms.mergesort(sub);
            System.arraycopy(sub, 0, values, low, sub.length);
        } else {
            ms.mergesort(values);
        }
        return ms.inv;
    }

    public static long Sort3(int[] values) {
        return InsertionSort.insertionSort(values);
    }

    public static long quicksort(ArrayList<Integer> values, int low, int high) {
        int[] arr = values.stream().mapToInt(Integer::intValue).toArray();
        long res = quicksort(arr, low, high);
        for (int idx = 0; idx < arr.length; idx++) {
            values.set(idx, arr[idx]);
        }
        return res;
    }

    public static long mergesort(ArrayList<Integer> values, int low, int high) {
        int[] arr = values.stream().mapToInt(Integer::intValue).toArray();
        long res = mergesort(arr, low, high);
        for (int idx = 0; idx < arr.length; idx++) {
            values.set(idx, arr[idx]);
        }
        return res;
    }

    public static long Sort3(ArrayList<Integer> values) {
        int[] arr = values.stream().mapToInt(Integer::intValue).toArray();
        long res = Sort3(arr);
        for (int idx = 0; idx < arr.length; idx++) {
            values.set(idx, arr[idx]);
        }
        return res;
    }
}
