package VoidMatrix;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class ReadFile {
    
    static void readFile() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("A.txt"));
    
        String firstDimension = reader.readLine();
        String[] split = firstDimension.split(" ");
        int firstX = Integer.parseInt(split[0]);
        int firstY = Integer.parseInt(split[0]);
    
        int[][] first = new int[firstX][firstY];
    
        for (int i = 0; i < firstX; i++) {
            String[] line = reader.readLine().split(" ");
    
            for (int j = 0; j < firstY; j++) {
                first[i][j] = Integer.parseInt(line[j]);
            }
    
        }
    
        // Read "@"
        reader.readLine();
    
        System.out.println(Arrays.deepToString(first));
    
    
    }
    // public static void main(String[] args) throws IOException {
    //     BufferedReader reader;
    //     String Filename;
    //     Scanner sc = new Scanner(System.in);
    //     // Filename=sc.nextLine();
    //     Filename="A.txt";
    //     reader = new BufferedReader(new FileReader(Filename));

    //     String firstDimension = reader.readLine();
    //     String[] split = firstDimension.split(" ");
    //     int firstX = Integer.parseInt(split[0]);
    //     int firstY = Integer.parseInt(split[0]);

    //     int[][] first = new int[firstX][firstY];

    //     for (int i = 0; i < firstX; i++) {
    //         String[] line;
    //         line = reader.readLine().split(" ");

    //         for (int j = 0; j < firstY; j++) {
    //             first[i][j] = Integer.parseInt(line[j]);
    //         }

    //     }

    //     // Read "@"
    //     reader.readLine();

    //     String secondDimension = reader.readLine();
    //     String[] split2 = secondDimension.split("");
    //     int secX = Integer.parseInt(split2[0]);
    //     int secY = Integer.parseInt(split2[0]);

    //     int[][] second = new int[secX][secY];

    //     for (int i = 0; i < secX; i++) {
    //         String[] line;
    //         line = reader.readLine().split(" ");

    //         for (int j = 0; j < secY; j++) {
    //             second[i][j] = Integer.parseInt(line[j]);
    //         }

    //     }

    //     // System.out.println(Arrays.deepToString(second));

    //     multiply(first, second);

    //     reader.close();
    // }

    // public static void multiply(int[][] first, int[][] second) {
    //     for (int i = 0; i < first.length; i++) {
    //         int total = 0;
    //         for (int j = 0; j < second[0].length; j++) {
    //             int fnum = first[i][j];
    //             int snum = second[j][i];
    //             int product = fnum * snum;
    //             total += product;
    //         }
    //         System.out.print(total + " ");
    //     }
    // }

}
