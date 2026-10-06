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
        ArrayList<Integer> values = new ArrayList<Integer>();
        
        System.out.print("Enter the filename of the data to be sorted including the extension: ");
        Scanner line = new Scanner(System.in);
        filename = filename.concat(line.nextLine()); 
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
              while (input.hasNextLine()) {
                values.add(input.nextInt());
              }
            } catch (FileNotFoundException e) {
              System.out.println("File Not Found");
              e.printStackTrace();
              return 1;
            }
        
        int i = 0;
        while (i==0){
            System.out.print("Choose the filesort system: 1. quicksort 2. mergesort 3. __");
            switch (choice) {
                case 1:
                    quicksort(values, 0, values.length - 1);
                    System.out.println("Sorted with QuickSort:");
                    i = 1;
                    break;
                case 2:
                    mergesort(values, 0, values.length - 1);
                    System.out.println("Sorted with MergeSort:");
                    i = 1;
                    break;
                case 3:
                    Sort3(values);
                    System.out.println("Sorted with Sort3:");
                    i = 1;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }        
        
        input.close();
    }
}
