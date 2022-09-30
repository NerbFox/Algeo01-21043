/* PROGRAM JAVA UNTUK MELAKUKAN ELIMINASI GAUSS-JORDAN */
package VoidMatrix;

import java.util.*;

public class GaussJordan {
    public void GaussJordanElimination(double[][] matrix, double[] HASIL) {
        /*
         * KETERANGAN : Melakukan elminasi Gauss untuk matrix augmented berukuran
         * baris*(kolom+1)
         */

        /* KAMUS LOKAL */
        int baris, kolom;
        int b, k, k2, index_nilai_maks, i;
        double elmt_datang, elmt_banding, faktor, pembagi;

        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length) - 1;
        kolom = (matrix[0].length) - 2;

        /* Mencacah kolom untuk melakukan operasi */
        for (k = 0; k <= kolom; k++) {

            /* Mencari baris acuan dari indeks [i+1..baris] untuk pertukaran */
            index_nilai_maks = k;
            for (b = k + 1; b <= baris; b++) {
                elmt_datang = matrix[b][k];
                if (elmt_datang < 0) {
                    elmt_datang = elmt_datang * -1;
                }

                elmt_banding = matrix[index_nilai_maks][k];
                if (elmt_banding < 0) {
                    elmt_banding = elmt_banding * -1;
                }

                if (elmt_datang > elmt_banding) {
                    index_nilai_maks = b;
                }
            }

            /* Menukar baris pada matrix */
            double[] temp = matrix[k];
            matrix[k] = matrix[index_nilai_maks];
            matrix[index_nilai_maks] = temp;

            /* Melakukan operasi baris elementer */
            for (b = k + 1; b <= baris; b++) {
                faktor = matrix[b][k] / matrix[k][k];
                for (k2 = k; k2 <= kolom + 1; k2++) {
                    matrix[b][k2] = matrix[b][k2] - faktor * matrix[k][k2];
                }
            }
        }

        /* Membuat LEADING ONE untuk setiap baris */
        i = -1;

        for (b = 0; b <= baris; b++) {
            i = i + 1;
            pembagi = matrix[b][i];
            for (k = 0; k <= kolom + 1; k++) {
                matrix[b][k] = matrix[b][k] / pembagi;
            }
        } /* SAMPAI SINI, ELIMINASI GAUSS TELAH SELESAI DILAKUKAN */

        /* Membuat nilai di atas dan bawah LEADING ONE menjadi 0 */
        int jumlah_operasi;
        double nilai_acuan;

        for (b = 0; b < baris; b++) {
            jumlah_operasi = 1;
            while (jumlah_operasi < (baris + 1) - b) {
                i = b + jumlah_operasi;
                nilai_acuan = matrix[b][i];
                for (k = 0; k <= kolom + 1; k++) {
                    matrix[b][k] = matrix[b][k] - nilai_acuan * matrix[i][k];
                }
                jumlah_operasi = jumlah_operasi + 1;
            }
        }

        /* Memindahkan kolom solusi ke array HASIL */
        for (b = 0; b <= baris; b++) {
            HASIL[b] = matrix[b][kolom + 1];
        }

        // display
        int x, y;
        for (x = 0; x < matrix.length; x++) {
            for (y = 0; y <= kolom + 1; y++) {
                if (y == kolom+1) {
                    System.out.printf("%f\n", matrix[x][y]);
                } else {
                    System.out.printf("%f ", matrix[x][y]);
                }
            }
        }
        System.out.printf("\n");
        System.out.printf("\n");

        System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA: \n");
        for (x = 0; x < matrix.length; x++) {
            System.out.printf("x%d: %f ", x, HASIL[x]);
            System.out.printf("\n");
        }
    }

    /* PROGRAM UTAMA */
    // public static void main (String[] args) {
    // Main ge = new Main();
    // double[][] arr = new double[4][5];
    // double[] hasil = new double[4];

    // arr[0][0] = 2;
    // arr[1][0] = 1;
    // arr[2][0] = 3;
    // arr[3][0] = 2;

    // arr[0][1] = -1;
    // arr[1][1] = 0;
    // arr[2][1] = -3;
    // arr[3][1] = 1;

    // arr[0][2] = 3;
    // arr[1][2] = -2;
    // arr[2][2] = 1;
    // arr[3][2] = 4;

    // arr[0][3] = 4;
    // arr[1][3] = 7;
    // arr[2][3] = 5;
    // arr[3][3] = 4;

    // arr[0][4] = 9;
    // arr[1][4] = 11;
    // arr[2][4] = 8;
    // arr[3][4] = 10;

    // ge.GaussJordanElimination(arr, hasil);

    // }
}
