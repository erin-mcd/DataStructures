import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {
    public static int comparisons = 0;
    public static int exchanges = 0;
    public static long time = 0;

    public static <T extends Comparable<T>> void sort(ArrayList<Integer> list) {
        // Sort the whole table.
        quickSort(list, 0, list.size() - 1);
    }


public static void quickSort(ArrayList<Integer> list){
    quickSort(list, 0, list.size() - 1);
}
private static <T extends Comparable<T>> void quickSort(ArrayList<Integer> list, int first, int last) {
    if (first < last) { // There is data to be sorted.
        // Partition the table.
        int pivIndex = partition(list, first, last);
        // Sort the left half.
        quickSort(list, first, pivIndex - 1);
        // Sort the right half.
        quickSort(list, pivIndex + 1, last);
    }
}
    // Insert partition method. See Listing 8.9
    private static <T extends Comparable<T>> int partition(ArrayList<Integer> list, int first, int last) {
 /* Put the median of table[first], table[middle], table[last]
 into table[first], and use this value as the pivot.
 */
        sort3(list, first, last);
        // Swap first element with median.
        swap(list, first, (first + last) / 2);
        exchanges++;
        int pivot = list.get(first);
        int up = first;
        int down = last;
        do {
 /* Invariant:
 All items in table[first . . . up ‐ 1] <= pivot
 All items in table[down + 1 . . . last] > pivot
 */
            while ((up < last) && (pivot >= (list.get(up)))) {
                up++;
                comparisons ++;
            }
            // assert: up equals last or table[up] > pivot.
            while (pivot < (list.get(down))) {
                down--;
                comparisons ++;
            }
            // assert: down equals first or table[down] <= pivot.
            if (up < down) { // if up is to the left of down.
                // Exchange table[up] and table[down].
                swap(list, up, down);
                exchanges++;
            }
        } while (up < down); // Repeat while up is left of down.
        // Exchange table[first] and table[down] thus putting the
        // pivot value where it belongs.
        swap(list, first, down);
        exchanges++;
        // Return the index of the pivot value.
        return down;
    }
    private static void sort3(ArrayList<Integer> list, int first, int last) {
        int middle = (first + last) / 2;
 /* Sort table[first], table[middle],
 table[last]. */
        if (list.get(middle).compareTo(list.get(first)) < 0) {
            swap(list, first, middle);
            comparisons ++;
            exchanges++;
        }
        // assert: table[first] <= table[middle]
        if (list.get(last).compareTo(list.get(middle)) < 0) {
            swap(list, middle, last);
            comparisons ++;
            exchanges++;
        }
        // assert: table[last] is the largest value of the three.
        if (list.get(middle).compareTo(list.get(first)) < 0) {
            swap(list, first, middle);
            comparisons ++;
            exchanges++;
        }
        // assert: table[first] <= table[middle] <= table[last].
    }
    public static void swap(ArrayList<Integer> list, int a, int b){

        int temp = list.get(b);
        list.set(b, list.get(a));
        list.set(a, temp);
    }

    public static void writeDataAtOnce(String filePath)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            String compare = "" + comparisons;
            String exchange = "" + exchanges;
            String nano = "" + time;
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Comparisons", "Exchanges", "Time" });
            data.add(new String[] { compare, exchange, nano });
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] { "Size", "Comparisons", "Exchanges", "Time" });
        Random rand = new Random();
        double size = 2;

        for (int j = 2; j < 15; j++) {
            comparisons = 0;
            exchanges = 0;
            ArrayList<Integer> list = new ArrayList<>();
              size = Math.pow(2, j);
            String sizee = "" + size;
            for (int i = 0; i < size; i++) {
                list.add(rand.nextInt(1000));
            }
            File file = new File("/Users/Erin/Data.csv");
            long startTime = System.nanoTime();
            quickSort(list);
            long stopTime = System.nanoTime();
            long elapsedTime = stopTime - startTime;
            System.out.println(elapsedTime);
            time = elapsedTime;

            try {
                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile);

                // create a List which contains String array
                String compare = "" + comparisons;
                String exchange = "" + exchanges;
                String nano = "" + time;


                data.add(new String[] {sizee, compare, exchange, nano });
                writer.writeAll(data);

                // closing writer connection
                writer.close();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        System.out.println("DONE");
    }
}
