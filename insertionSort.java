import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class insertionSort {
    public static int comparisons = 0;
    public static int exchanges = 0;
    public static long time = 0;

    public static void sort(ArrayList<Integer> list)
    {
        int n = list.size();
        for (int i=1; i<n; ++i)
        {
            int temp = list.get(i);
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && list.get(j) > temp)
            {
                comparisons++;
                list.set(j+1, list.get(j));
                exchanges++;
                j = j-1;
            }
            comparisons++;
            list.set(j+1, temp);
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
            File file = new File("/Users/Erin/InsertionData.csv");
            long startTime = System.nanoTime();
            sort(list);
          //  System.out.println(list);
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

