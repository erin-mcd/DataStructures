import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {
    public static int comparisons = 0;
    public static int exchanges = 0;
    public static long time = 0;


    public static void mergeSort(int[] inputArray) {
        int size = inputArray.length;
        if (size < 2)
            return;
        int mid = size / 2;
        int leftSize = mid;
        int rightSize = size - mid;
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];
        for (int i = 0; i < mid; i++) {
            left[i] = inputArray[i];
        exchanges++;
        }
        for (int i = mid; i < size; i++) {
            right[i - mid] = inputArray[i];
            exchanges++;
        }
        mergeSort(left);
        mergeSort(right);
        merge(left, right, inputArray);
    }

    public static void merge(int[] left, int[] right, int[] arr) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            comparisons++;
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
                k++;
            } else {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
        while (i < leftSize) {
            //System.out.println("left");
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < rightSize) {
            //System.out.println("right");
            arr[k] = right[j];
            k++;
            j++;
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
            size = Math.pow(2, j);
            int sizeInt = (int) size;
            int list[] = new int[sizeInt];
            String sizee = "" + size;
            for (int i = 0; i < size; i++) {
                list[i] = (rand.nextInt(1000));
            }
            File file = new File("/Users/Erin/MergeData.csv");
            long startTime = System.nanoTime();
            mergeSort(list);
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
        int array[] = {4,2,7,41,33,52,12,34,75, 23};
        mergeSort(array);
        String answer = "";
        for (int i = 0; i < array.length; i++) {
            answer += " " + array[i];
        }
        System.out.println(answer);
        System.out.println("DONE");
    }
}