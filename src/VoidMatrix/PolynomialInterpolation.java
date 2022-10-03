package VoidMatrix;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("resource")

public class PolynomialInterpolation {
    public PolynomialInterpolation(boolean read) {
        if (!read) {
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
            n = 0;
            while (n <= 0) {
                System.out.print("Masukkan total banyaknya titik: ");
                n = myScanner.nextInt(); // Menyimpan input di n
                if (n <= 0) {
                    System.out.println("Invalid Input, please input the correct option");
                }
            }

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

            for (count3 = 0; count3 < n2; count3++) {
                // Meminta user untuk memasukkan nilai x apa pun untuk mendapatkan nilai y yang
                // sesuai
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
                System.out.println("x = " + x + "," + " y = " + y);

                char simpan;
                Scanner sc = new Scanner(System.in);
                System.out.print("\nApakah ingin disimpan (y/n) : ");
                simpan = sc.next().charAt(0);
                System.out.println();
                if (simpan == 'y' || simpan == 'Y') {
                    fileKeluaranPolinomIn(x, y);
                } else {
                    System.out.println("File tidak disimpan");
                }
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nKetik nama alamat file yang diinginkan (contoh test/A.txt) ");
            File file = new File(sc.nextLine());
            Scanner Inside = null;
            try {
                Inside = new Scanner(file);
            } catch (Exception ex) {
                System.out.println("File tidak ditemukan");
            }
            while (Inside == null) {
                System.out.println("\nKetik nama alamat file yang diinginkan (contoh test/A.txt) ");
                file = new File(sc.nextLine());
                try {
                    Inside = new Scanner(file);
                } catch (Exception ex) {
                    System.out.println("File tidak ditemukan");
                }
            }
            int rows = 0;
            int cols = 0;
            ArrayList<ArrayList<Double>> Isi = new ArrayList<ArrayList<Double>>();
            String baris;
            while (Inside.hasNextLine()) {
                baris = Inside.nextLine();
                Scanner SBar = new Scanner(baris);
                cols = 0;
                Isi.add(new ArrayList<Double>());
                while (SBar.hasNextDouble()) {
                    Isi.get(rows).add(cols, SBar.nextDouble());
                    cols++;
                }
                rows++;
            }
            double titik = Isi.get(rows - 1).get(cols - 1);
            Isi.remove(rows - 1);
            int nRows, nCols, i, j;
            nRows = Isi.size();
            nCols = Isi.get(0).size();
            double[][] res = new double[nRows][nCols];
            for (i = 0; i < nRows; i++) {
                for (j = 0; j < nCols; j++) {
                    res[i][j] = Isi.get(i).get(j);
                }
            }

            int count, count2;
            double x = 0;
            double y = 0;
            double numerator;
            double denominator;

            x = titik;

            // Loop pertama untuk menghitung polinomial
            for (count = 0; count < nRows; count++) {
                // Inisialisasi variabel
                numerator = 1;
                denominator = 1;

                // Loop kedua untuk menghitung polinomial
                for (count2 = 0; count2 < nRows; count2++) {
                    if (count2 != count) {
                        numerator = numerator * (x - res[count2][0]);
                        denominator = denominator * (res[count][0] - res[count2][0]);
                    }
                }
                y = y + (numerator / denominator) * res[count][1];
            }
            System.out.println("x = " + x + "," + " y = " + y);
            char simpan;
            System.out.print("\nApakah ingin disimpan (y/n) : ");
            simpan = sc.next().charAt(0);
            System.out.println();
            if (simpan == 'y' || simpan == 'Y') {
                fileKeluaranPolinomIn(x, y);
            } else {
                System.out.println("File tidak disimpan");
            }

        }
    }

    public void fileKeluaranPolinomIn(double x, double y) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
        String namaFile = sc.nextLine();

        try {
            PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
            output.printf("x = " + x + "," + " y = " + y);
            output.close();
        } catch (IOException ex) {
            System.out.printf("error: %s\n\n", ex);
        }
        // }
    }
}