import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
import java.io.File;

public class Sudoku {
    static int sum = 0;

    public static boolean valid(int[][] board, int row, int col, int num) {
        if (!isRowValid(board, row, col, num) || !isColValid(board, row, col, num) || !isBoxValid(board, row, col, num)) {
            return false;
        }
        return true;
    }

    public static boolean isRowValid(int[][] board, int row, int col, int num) {
        for (int i = 1; col - i >= 0; i++) {
            if (board[row][col - i] == num) {
                return false;
            }
        }

        for (int i = 1; col + i < board.length; i++) {
            if (board[row][col + i] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean isColValid(int[][] board, int row, int col, int num) {
        for (int i = 1; row - i >= 0; i++) {
            if (board[row - i][col] == num) {
                return false;
            }
        }
        for (int i = 1; row + i < board.length; i++) {
            if (board[row + i][col] == num) {
                return false;
            }
        }
        return true;
    }


    public static boolean isBoxValid(int[][] board, int row, int col, int num) {
        int topLRow = row - row % 3;
        int topLCol = col - col % 3;

        for (int i = topLRow; i < topLRow + 3; i++) {
            for (int j = topLCol; j < topLCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean solve(int[][] board, int row, int col) {

        if (col == board.length) {
            row = row + 1;
            col = 0;

        }
        if (row == board.length) {
            return true;
        }

        if (board[row][col] != 0) {
            return solve(board, row, col + 1);
        }


        for (int i = 1; i < 10; i++) {
            if (valid(board, row, col, i)) {
                board[row][col] = i;
                if (solve(board, row, col + 1) == true) {
                    return true;
                }
            }
        }
        board[row][col] = 0;
        return false;
    }

    public static void read() {
        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("p096_sudoku.txt")));

            int rows = 9;
            int columns = 9;
            int[][] myArray = new int[rows][columns];

            while (sc.hasNextLine()) {
                String lineOfText = sc.nextLine();

                if (lineOfText.startsWith("G")) {
                    continue;
                }


                String[] line = lineOfText.trim().split("");
              //  System.out.println(Arrays.toString(line));
                for (int j = 0; j < line.length; j++) {
                    myArray[0][j] = Integer.parseInt(line[j]);
                }

                for (int i = 1; i < myArray.length; i++) {

                        line = sc.nextLine().trim().split("");
                       //  System.out.println(Arrays.toString(line));
                        for (int j = 0; j < line.length; j++) {
                            myArray[i][j] = Integer.parseInt(line[j]);
                        }
                    }


                 System.out.println(Arrays.deepToString(myArray));
               // System.out.println(lineOfText);
                int one = myArray[0][1];
                int two = myArray[0][2];
                int three = myArray[0][3];
            //   solve(myArray, 0, 0);
               // System.out.println(Arrays.deepToString(myArray));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //return myArray;
    }



    public static void main(String args[]) {
        Scanner sc = null;
        String one = "";
        String two = "";
        String three = "";
        String num = "";
        int realNum = 0;
        int realSum = 0;
        int sum = 0;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("p096_sudoku.txt")));

            int rows = 9;
            int columns = 9;
            int[][] myArray = new int[rows][columns];

            while (sc.hasNextLine()) {
                String lineOfText = sc.nextLine();

                if (lineOfText.startsWith("G")) {
                    continue;
                }


                String[] line = lineOfText.trim().split("");
                //  System.out.println(Arrays.toString(line));
                for (int j = 0; j < line.length; j++) {
                    myArray[0][j] = Integer.parseInt(line[j]);
                }

                for (int i = 1; i < myArray.length; i++) {

                    line = sc.nextLine().trim().split("");
                    //  System.out.println(Arrays.toString(line));
                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }


           //     System.out.println(Arrays.deepToString(myArray));
                // System.out.println(lineOfText);

                solve(myArray,0,0);
                one = "" + myArray[0][0];
                two = "" + myArray[0][1];
                three = "" + myArray[0][2];
                num = one + two + three;
                realNum = Integer.parseInt(num);
                realSum = realSum + realNum;



                for (int[] row : myArray) {
                    System.out.println(Arrays.toString(row));
                }
                //   solve(myArray, 0, 0);
                // System.out.println(Arrays.deepToString(myArray));
            }
            sum = realSum;

            System.out.println(sum);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //return myArray;
    }
}





    /*
                int num = 0;
                int board[][] =
                        {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                                {0, 0, 0, 0, 8, 0, 0, 7, 9}};
                solve(board, 0, 0);
                System.out.println(solve(board, 0, 0));
                for (int[] row : board) {
                    System.out.println(Arrays.toString(row));
                }
            }*/
