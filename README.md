# CS3364-Algorithms-Project-1

Measures the reliability of five ranking sources by counting inversions between each
source's ranking and the combined (consensus) ranking. A source with fewer inversions
agrees more closely with the consensus and is treated as more reliable.

Course: CS 3364 – Analysis of Algorithms, Texas Tech University

## Team and contributions

| Member | Contribution |
|---|---|
| Joshua Longoria | Quicksort-based inversion counting (`Quicksort.java`) |
| Kylon Ford | Mergesort-based inversion counting |
| Mason Womack | Third algorithm ??? |
| Mia -- | Report |
| Marvin -- | -- |

## Requirements

- JDK 21 (developed against Eclipse Temurin 21)
- No external libraries

## Build and run

From the project root:

```
javac -d out <source files>
java -cp out Quicksort
```

Or open the project in IntelliJ IDEA and run the class directly.

**Working directory matters.** Input paths are relative (`Testfiles/source1.txt`), so the
program must be run from the project root. Running from elsewhere produces
`NoSuchFileException`.

## Input format

Five plain-text files in `Testfiles/`:

```
Testfiles/source1.txt
Testfiles/source2.txt
Testfiles/source3.txt
Testfiles/source4.txt
Testfiles/source5.txt
```

Each file holds one integer rank per line, 10,000 lines total.

**Page identity is positional.** Line *i* of every file is the rank that source assigned to
page *i*. Page IDs are not stored in the files — the same 10,000 pages appear in all five,
which is what makes a per-page combined rank meaningful.

## Method

1. Compute each page's combined rank as the sum of its five source ranks.
2. Order the pages by combined rank.
3. For each source, list its rank values in that page order.
4. Count inversions in each of those arrays. Fewest inversions = most reliable.

An inversion is a pair `i < j` where `A[i] > A[j]`. Equal values are not inversions.

### Counting during the sort

Inversions are counted as a side effect of sorting, in O(n log n), rather than by comparing
all pairs in O(n²). After partitioning around a pivot, every pair of elements falls into
exactly one of three buckets:

- both on the small side → counted by the left recursive call
- both on the large side → counted by the right recursive call
- split across sides, or involving the pivot → counted during the partition scan

No pair is counted twice and none is missed, so the three terms sum to the true total.

### Design note: stable partition

This implementation partitions into two auxiliary arrays instead of swapping in place.
Swapping moves elements past one another, which destroys the original relative order within
each side — and inversions are defined by original relative order, so recursive calls would
then count inversions the partition itself created rather than ones present in the input.

Appending in scan order preserves that order. The cost is O(n) extra space per call instead
of textbook quicksort's O(1). This was a deliberate trade of space for correctness.

## Output

_TODO: describe what the program prints and how to read it._

## Results

| Source | Inversions | Reliability rank |
|---|---|---|
| source1 | _TODO_ | _TODO_ |
| source2 | _TODO_ | _TODO_ |
| source3 | _TODO_ | _TODO_ |
| source4 | _TODO_ | _TODO_ |
| source5 | _TODO_ | _TODO_ |

## Project structure

```
CS3364-Algorithms-Project-1/
├── Testfiles/
│   ├── source1.txt ... source5.txt
│   └── Quicksort.java          (TODO: move sources out of the data folder)
├── Docs/                       (TODO: confirm — project spec, report)
└── README.md
```

## Status

- [x] File reading (`readSource`)
- [x] Quicksort with inversion counting, validated against a brute-force O(n²) counter
- [ ] Combined-rank pipeline (sum per page, order pages, reorder each source)
- [ ] Mergesort
- [ ] Third algorithm
- [ ] Report: problem interpretation, methodology, experimental results, conclusions

## Verification

Correctness was checked against a brute-force O(n²) counter on the same inputs, and on small
hand-checkable cases (`{3,8,1,9,2}` → 5 inversions).
