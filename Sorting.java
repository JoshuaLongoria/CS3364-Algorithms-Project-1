import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;



public class Sorting{
    
    private static final int SOURCE_COUNT = 5;
    private static final int PAGE_COUNT = 10000;
    
    public static void main(String[] args){
        
        // Get filename and initialize arrays
        
        String filename = "/Testfiles/";
        int[] values = new int[10000];
        
        System.out.print("Enter the filename of the data to be sorted including the extension: ");
        Scanner line = new Scanner(System.in);
        filename = filename.concat(line.nextLine()); 
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
              int a = 0;
              while (input.hasNextLine()) {
                values[a] = input.nextInt();
                a++;
              }
            } catch (FileNotFoundException e) {
              System.out.println("File Not Found");
              e.printStackTrace();
              return 1;
            }
            
        int i = 0;
        while (i==0){
            int[] copy = values.clone();
            System.out.print("Choose the filesort system: 1. QuickSort 2. MergeSort 3. InsertionSort");
            int choice = line.nextInt()
            switch (choice) {
                case 1:
                    System.out.println("Sorting with QuickSort...");
                    Quicksort qs = new Quicksort();
                    long inv = qs.sort(copy);
                    System.out.println("Inversions counted: " + inv);
                    i = 1;
                    break;
                case 2:
                    System.out.println("Sorting with MergeSort...");
                    MergeSort ms = new MergeSort();
                    ms.sort(copy);
                    System.out.println("Inversions counted: " + ms.inv);
                    i = 1;
                    break;
                case 3:
                    System.out.println("Sorted with InsertionSort...");
                    InsertionSort is = new InsertionSort();
                    long inv = is.sort(copy);
                    System.out.println("Inversions counted: " + inv);
                    i = 1;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }        
        
        line.close();
    }
}
