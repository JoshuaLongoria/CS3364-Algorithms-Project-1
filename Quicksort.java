import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class Quicksort{

    public static void main(String[] args)throws IOException{

//  Accessing the numbers from the files
        List<String> lines = Files.readAllLines(Path.of("source1.txt"));

//  Grabbing those variables and making slots for them
        int[] numbers = new int[lines.size()];


//  Then converts the text into numbers
        for (int i = 0; i < lines.size(); i++) {
            numbers[i] = Integer.parseInt(lines.get(i).trim());
        }

//  Calling the method quicksort to organize the numbers
        long inversions = quicksort(numbers, 0, numbers.length - 1);

        printArray(numbers);
        System.out.print("Number of inversions: "+ inversions);

    }
    private static long quicksort(int[] array, int lowIndex, int highIndex){

        if (lowIndex >= highIndex) {
            return 0;
        }

        int pivot = array[highIndex];

        int[] small = new int[highIndex - lowIndex];
        int[] large = new int[highIndex - lowIndex];
        int smallCount = 0, largeCount = 0;
        long cross = 0;
        int largerSeen = 0;

        for (int i = lowIndex; i < highIndex; i++) {
            if (array[i] > pivot) {
                large[largeCount++] = array[i];
                largerSeen++;
                cross += 1;
            } else {
                small[smallCount++] = array[i];
                cross += largerSeen;
            }
        }

        int pos = lowIndex;
        for (int i = 0; i < smallCount; i++)
            array[pos++] = small[i];

        array[pos++] = pivot;
        for (int i = 0; i < largeCount; i++)
            array[pos++] = large[i];

        int pivotIndex = lowIndex + smallCount;

        long left  = quicksort(array, lowIndex, pivotIndex - 1);
        long right = quicksort(array, pivotIndex + 1, highIndex);

        return cross + left + right;
    }



//    To print the numbers from the file( You can comment it out or not)
    private static void printArray(int [] numbers){
        for (int number : numbers) {
            System.out.println(number);
        }
     }
}