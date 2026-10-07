import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class Quicksort {
    public long inv = 0;
     
     public long sort(int[] array) {
            inv = 0;
            inv = quicksort(array, 0, array.length - 1);
            return inv;
        }
        
    private long Quicksort(int[] array, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex) {
            return 0;
        }
//Establishing my pivot as the high-Index
        int pivot = array[highIndex];

//Establishing the small and large arrays
        int[] small = new int[highIndex - lowIndex];
        int[] large = new int[highIndex - lowIndex];

//Setting counters, and inversions
        int smallCount = 0, largeCount = 0;
        long cross = 0;
        int largerSeen = 0;

//==========================================================================================================
//Loop to go through each element and categorize them
//If array[i] (the element in the array) is larger than the pivot
//element gets stored in large[] and largeCount, largerSeen, and cross increments to 1
// else the element gets stored in small[] and smallCount increments by 1 and cross adds largerSeen to itself
//==========================================================================================================
//Every pair of element falls into one of three buckets: both on the small side (counted by the left
//recursive call, both on the large side (counted by the right side), or split across sides involving the pivot
//counted here as the cross. No pair is counted twice and none is missed.
        for (int i = lowIndex; i < highIndex; i++) {
            if (array[i] > pivot) {
                large[largeCount++] = array[i];
                largerSeen++;
                cross += 1;
//Every larger element already passed forms an inversion with this one, so we are adding them all at once.
            } else {
                small[smallCount++] = array[i];
                cross += largerSeen;
            }
        }
//Established a variable of pos to be the lowIndex of wherever the subrange starts
        int pos = lowIndex;

//Loop for the small[] to order in array[] and increase pos or lowIndex by 1
//Until i is greater than smallCount and breaks out of the loop and pos advances past the
//small values, then the pivot is written at that position.
        for (int i = 0; i < smallCount; i++)
            array[pos++] = small[i];
        array[pos++] = pivot;


//Loop for the large[] to order in array[] and increase Index or pos by 1
//Until i is greater than largeCount

        for (int i = 0; i < largeCount; i++)
            array[pos++] = large[i];

        int pivotIndex = lowIndex + smallCount;
//Recursive call to quicksort function to move left pivot and right pivot throught the array
//Passes the array along with the pivotIndex either -1 or +1, along with the highIndex or length/position of the array.
        long left = quicksort(array, lowIndex, pivotIndex - 1);
        long right = quicksort(array, pivotIndex + 1, highIndex);

//Once return 0 happens back at the first if statement each call returns independently once its own recursion
//finishes
        return cross + left + right;
    }
}