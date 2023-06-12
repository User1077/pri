import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class QuickSortRecursionLimitTest {

    private static final int MAX_RECURSION_LIMIT = 300;
    private static final int STEP_SIZE = 2;
    private static final int MIN_ARRAY_SIZE = 20000;
    private static final int ARRAY_SIZE_STEP = 500000;
    private static final int MAX_ARRAY_SIZE = 10000000;
    private static final int TEST_RUNS = 3;


    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("Array Size,Recursion Limit,Run,Duration\n");  // Write the header
            for (int size = MIN_ARRAY_SIZE; size <= MAX_ARRAY_SIZE; size += ARRAY_SIZE_STEP) {
                int[] array = generateRandomArray(size);
                for (int limit = 2; limit <= MAX_RECURSION_LIMIT; limit += STEP_SIZE) {
                    FHsort.setRecursionLimit(limit);
                    for (int i = 0; i < TEST_RUNS; i++) {
                        int[] testArray = array.clone();
                        long startTime = System.nanoTime();
                        FHsort.quickSort(testArray);
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime);
                        writer.write(size + "," + limit + "," + (i+1) + "," + duration + "\n");  // Write the data
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt();
        }
        return array;
    }
}
