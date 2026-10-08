import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Sorting {

    public static void main(String[] args) throws IOException {
        boolean running = true;
        Scanner in = new Scanner(System.in);
        while (running) {
            System.out.print("Choose a sorting Algorithm 1)QuickSort 2)MergeSort 3)InsertionSort 4)Exit: ");
            int choice = in.nextInt();

            if (choice == 4) { running = false; continue; }

            for (int s = 1; s <= 5; s++) {
                int[] values = readSource("Testfiles/source" + s + ".txt");
                long inv;
                switch (choice) {
                    case 1:
                        inv = Quicksort.sort(values);
                        break;
                    case 2:
                        inv = MergeSort.sort(values);
                        break;
                    case 3:
                        inv = InsertionSort.sort(values);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                System.out.println("source" + s + ": " + inv);
            }

        }
        in.close();

    }
    private static int[] readSource(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filename));
        int[] numbers = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            numbers[i] = Integer.parseInt(lines.get(i).trim());
        }
        return numbers;
    }
}

