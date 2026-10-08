import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sorting {

    private static final String[] SOURCE_FILES = {
            "source1.txt",
            "source2.txt",
            "source3.txt",
            "source4.txt",
            "source5.txt"
    };

    private static final int SOURCE_COUNT = SOURCE_FILES.length;
    private static final int PAGE_COUNT = 10000;

    public static void main(String[] args) {
        Path directory;

        switch (args.length) {
            case 0:
                directory = Path.of("Testfiles");
                break;

            default:
                directory = Path.of(args[0]);
                break;
        }

        try (Scanner keyboard = new Scanner(System.in)) {
            int choice = readChoice(keyboard);

            switch (choice) {
                case 0:
                    return;

                default:
                    run(directory, choice);
                    break;
            }

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static int readChoice(Scanner keyboard) {
        while (true) {
            System.out.println("Choose the sorting algorithm:");
            System.out.println("1. Quick sort");
            System.out.println("2. Merge sort");
            System.out.println("3. Insertion sort");
            System.out.print("Enter 1, 2, or 3: ");

            switch (keyboard.hasNextLine() ? 1 : 0) {
                case 0:
                    System.out.println("No selection entered.");
                    return 0;

                default:
                    break;
            }

            String selection = keyboard.nextLine().trim();

            switch (selection) {
                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
                    break;
            }
        }
    }

    private static void run(Path directory, int choice)
            throws IOException {

        int[][] sources = new int[SOURCE_COUNT][];
        long[] combined = new long[PAGE_COUNT];

        for (int s = 0; s < SOURCE_COUNT; s++) {
            Path file = directory.resolve(SOURCE_FILES[s]);
            sources[s] = readSource(file);

            for (int p = 0; p < PAGE_COUNT; p++) {
                combined[p] += sources[s][p];
            }
        }

        Integer[] pageOrder = new Integer[PAGE_COUNT];

        for (int p = 0; p < PAGE_COUNT; p++) {
            pageOrder[p] = p;
        }

        Arrays.sort(
                pageOrder,
                Comparator
                        .comparingLong((Integer p) -> combined[p])
                        .thenComparingInt(p -> p)
        );

        long[] counts = new long[SOURCE_COUNT];

        System.out.println();
        System.out.println(
                "Combined-rank ties are ordered by original page number."
        );

        System.out.printf(
                "%-12s %15s%n",
                "Source",
                "Inversions"
        );

        for (int s = 0; s < SOURCE_COUNT; s++) {
            int[] ordered = new int[PAGE_COUNT];

            for (int p = 0; p < PAGE_COUNT; p++) {
                ordered[p] = sources[s][pageOrder[p]];
            }

            switch (choice) {
                case 1: {
                    Quicksort qs = new Quicksort();
                    counts[s] = qs.sort(ordered);
                    break;
                }

                case 2: {
                    MergeSort ms = new MergeSort();
                    ordered = ms.sort(ordered);
                    counts[s] = ms.inv;
                    break;
                }

                case 3: {
                    InsertionSort is = new InsertionSort();
                    counts[s] = is.sort(ordered);
                    break;
                }

                default:
                    throw new IllegalArgumentException(
                            "Invalid choice. Enter 1, 2, or 3."
                    );
            }

            System.out.printf(
                    "%-12s %,15d%n",
                    SOURCE_FILES[s],
                    counts[s]
            );
        }

        Integer[] reliabilityOrder = new Integer[SOURCE_COUNT];

        for (int s = 0; s < SOURCE_COUNT; s++) {
            reliabilityOrder[s] = s;
        }

        Arrays.sort(
                reliabilityOrder,
                Comparator
                        .comparingLong((Integer s) -> counts[s])
                        .thenComparingInt(s -> s)
        );

        System.out.println();
        System.out.println(
                "Reliability ranking (fewer inversions is better):"
        );

        int rank = 1;
        long previousCount = -1;

        for (int i = 0; i < SOURCE_COUNT; i++) {
            int s = reliabilityOrder[i];

            switch (Long.compare(counts[s], previousCount)) {
                case 0:
                    break;

                default:
                    rank = i + 1;
                    break;
            }

            System.out.printf(
                    "Rank %d: %s (%,d inversions)%n",
                    rank,
                    SOURCE_FILES[s],
                    counts[s]
            );

            previousCount = counts[s];
        }
    }

    private static int[] readSource(Path file) throws IOException {
        int[] ranks = new int[PAGE_COUNT];
        int count = 0;

        try (Scanner input = new Scanner(Files.newBufferedReader(file))) {
            while (input.hasNext()) {

                switch (input.hasNextInt() ? 1 : 0) {
                    case 0:
                        throw new IllegalArgumentException(
                                file + ": invalid integer '" + input.next() + "'");

                    default:
                        break;
                }

                switch (Integer.compare(count, PAGE_COUNT)) {
                    case -1:
                        break;

                    default:
                        throw new IllegalArgumentException(
                                file + ": more than "
                                        + PAGE_COUNT + " ranks"
                        );
                }

                int value = input.nextInt();

                switch (
                        (value >= 1 && value <= PAGE_COUNT) ? 1 : 0
                ) {
                    case 1:
                        ranks[count] = value;
                        count++;
                        break;

                    default:
                        throw new IllegalArgumentException(
                                file + ": rank outside 1.." + PAGE_COUNT
                        );
                }
            }

            IOException readError = input.ioException();

            switch (readError == null ? 0 : 1) {
                case 0:
                    break;

                default:
                    throw readError;
            }
        }

        switch (Integer.compare(count, PAGE_COUNT)) {
            case 0:
                return ranks;

            default:
                throw new IllegalArgumentException(
                        file + ": expected " + PAGE_COUNT
                                + " ranks, found " + count
                );
        }
    }
}
