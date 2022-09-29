package VoidMatrix;

import java.util.Scanner;

public class PolynomialInterpolation {
    public PolynomialInterpolation() {
        // public static void main(String[] args){
        Scanner myScanner = new Scanner(System.in);

        // Deklarasi Variabel
        int n, n2; // Total banyaknya titik
        int count, count2, count3; // Variabel loop, untuk menghitung count

        double[] arrayx = new double[100]; // Array limit 99
        double[] arrayy = new double[100]; // Array limit 99
        double x = 0;
        double y = 0;
        double numerator;
        double denominator;

        // Input banyaknya titik dari user
        System.out.print("Masukkan total banyaknya titik: ");
        n = myScanner.nextInt(); // Menyimpan input di n

        // Input nilai x dari user
        System.out.println("Nilai x");
        for (count = 0; count < n; count++) // Memulai loop untuk nilai x
        {
            // Input nilai xi
            System.out.print("x" + count + ": ");
            // Menyimpan nilai di Array, arrayx
            arrayx[count] = myScanner.nextDouble();
        }

        // Input nilai y dari user
        System.out.println("Nilai y");
        for (count = 0; count < n; count++) // loop for Y
        {
            // Input nilai yi
            System.out.print("y" + count + ": ");
            // Menyimpan nilai di Array, arrayy
            arrayy[count] = myScanner.nextDouble();
        }

        // Input User
        System.out.print("Masukkan total banyaknya titik x yang ingin dicari: ");
        n2 = myScanner.nextInt(); // Menyimpan input di n2

        for (count3 = 0; count3 < n2; count3++){
            // Meminta user untuk memasukkan nilai x apa pun untuk mendapatkan nilai y yang sesuai
            System.out.print("Titik x yang ingin dicari nilai y nya: ");
            x = myScanner.nextDouble(); // Menyimpan nilai di x

            // Loop pertama untuk menghitung polinomial
            for (count = 0; count < n; count++) {
                // Inisialisasi variabel
                numerator = 1;
                denominator = 1;

                // Loop kedua untuk menghitung polinomial
                for (count2 = 0; count2 < n; count2++) {
                    if (count2 != count) {
                        numerator = numerator * (x - arrayx[count2]);
                        denominator = denominator * (arrayx[count] - arrayx[count2]);
                    }
                }
                y = y + (numerator / denominator) * arrayy[count];
            }
            System.out.println("Saat x = " + x + "," + " y = " + y);
            
        }
    }
}