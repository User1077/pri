import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The class tests the impact of recursion limit on quick sort performance.
 *
 * @author Suleiman Reed
 */
public class QuickSortRecursionTest {

    private static final int MAX_RECURSION_LIMIT = 300;
    private static final int STEP_SIZE = 2;
    private static final int MIN_ARRAY_SIZE = 20000;
    private static final int ARRAY_SIZE_STEP = 500000;
    private static final int MAX_ARRAY_SIZE = 7520000;
    private static final int TEST_RUNS = 3;

    /**
     * Generates an array of a specified size filled with random integers.
     *
     * @param size Size of array to generate.
     * @return Generated array filled with integers.
     */
    private static Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt();
        }
        return array;
    }

    /**
     * Main loop that tests array-sorting.
     *
     * @param args
     */
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("Array Size,Recursion Limit,Run,Duration\n");
            for (int size = MIN_ARRAY_SIZE; size <= MAX_ARRAY_SIZE; size += ARRAY_SIZE_STEP) {
                Integer[] array = generateRandomArray(size);
                for (int limit = 2; limit <= MAX_RECURSION_LIMIT; limit += STEP_SIZE) {
                    FHsort.setRecursionLimit(limit);
                    for (int i = 0; i < TEST_RUNS; i++) {
                        Integer[] testArray = array.clone();
                        long startTestTime = System.nanoTime();
                        FHsort.quickSort(testArray);
                        long endTestTime = System.nanoTime();
                        long duration = (endTestTime - startTestTime);
                        writer.write(size + "," + limit + "," + (i + 1) + "," + duration + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
