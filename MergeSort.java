import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MergeSort{

    public MergeSort(){
    }

    public MergeSort(int[] array){
        mergesort(array);
    }

    // constructor to streamline creating each file and array, as well as sorting them
    public MergeSort(File f, int[] array){
        fileReading(f, array);
        mergesort(array);
    }

    // saves the files contents to the array
    public void fileReading(File f, int[] array){
        try (Scanner scanner = new Scanner(f)) {
            int i = 0;
            while(scanner.hasNextInt()){
                if (i < array.length) {
                    array[i++] = scanner.nextInt();
                } else {
                    scanner.nextInt();
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    // public variable to count inversions in each file
    public long inv = 0;
    
    // increases the inverion 
    public void addInversion(){
        inv++;
    }

    public void addInversion(long count){
        inv += count;
    }

    public int[] mergesort(int[] array){
        // base case
        if(array == null || array.length <= 1)
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

        
            int[] B = merge(LS, RS);
            System.arraycopy(B, 0, array, 0, array.length);
            return B;
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
                addInversion(L.length - i);
            }
        }
        return B;
    }

    // displays the inverions
    public void displayInversions(int n){
        System.out.println("The number of inversions in file " + n + " is " + inv + "\n");
    }

    public static void main(String [] args) {

        File file1 = new File("Testfiles/source1.txt");
        File file2 = new File("Testfiles/source2.txt");
        File file3 = new File("Testfiles/source3.txt");
        File file4 = new File("Testfiles/source4.txt");
        File file5 = new File("Testfiles/source5.txt");

        int[] array1 = new int[10000];
        int[] array2 = new int[10000];
        int[] array3 = new int[10000];
        int[] array4 = new int[10000];
        int[] array5 = new int[10000];
        
        MergeSort one = new MergeSort(file1, array1);
        one.displayInversions(1);
        
        MergeSort two = new MergeSort(file2, array2);
        two.displayInversions(2);

        MergeSort three = new MergeSort(file3, array3);
        three.displayInversions(3);

        MergeSort four = new MergeSort(file4, array4);
        four.displayInversions(4);

        MergeSort five = new MergeSort(file5, array5);
        five.displayInversions(5);
        
    }
}
