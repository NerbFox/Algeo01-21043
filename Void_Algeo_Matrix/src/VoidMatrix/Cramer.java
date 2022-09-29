/* PROGRAM JAVA UNTUK MENEMUKAN SOLUSI SPL DENGAN KAIDAH CRAMER */
package VoidMatrix;

import java.util.*;

public class Cramer {
    public double det(double[][] mat) {
        /* Fungsi yang mengembalikan determinan matriks */
        // Prekondisi: matriks berbentuk square
        double d = 0.0;
        int i, j, a, b, k, u;
        int num = mat.length;
        if (num != 1) {
            for (k = 0; k < num; k++) {
                a = 0;
                b = 0;
                u = num - 1;
                double[][] temp = new double[u][u];
                for (i = 0; i < num; i++) {
                    if (i != 0) {
                        b = 0;
                        for (j = 0; j < num; j++) {
                            if (j != k) {
                                temp[a][b] = mat[i][j];
                                b++;
                            }
                        }
                        a++;
                    }
                }
                d += (Math.pow(-1, k) * mat[0][k] * det(temp));
            }
        } else {
            d = mat[0][0];
        }
        return d;
    }
    
    
    public void CramerRule (double[][] matrix, double[] HASIL) {
        /* KETERANGAN : matrix berukuran baris*(kolom+1) akan di-parse dahulu */
        /* PREKONDISI : jumlah persamaan sesuai dengan jumlah variabel */
        
        /* KAMUS LOKAL */
        int b, k, i, baris, kolom;
        double[][] tanpa_solusi, temp;
        double[] kolom_solusi;
        
        /* ALGORITMA */
        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length)-1;
        kolom = (matrix[0].length)-2;
        
        /* Memisahkan kolom solusi dari matrix augmented */
        tanpa_solusi  = new double[baris+1][kolom+1];
        kolom_solusi = new double[baris+1];
        
        for (b = 0; b <= baris; b++) {
            for (k = 0; k <= kolom; k++) {
                tanpa_solusi[b][k] = matrix[b][k];
            }
        }
        
        for (b = 0; b <= baris; b++) {
            kolom_solusi[b] = matrix[b][kolom+1];
        }
        
        /* Mengisi array HASIL */
        temp = new double[baris+1][kolom+1];
        
        for (i = 0; i <= kolom; i++) {
            
            /* Salin tanpa_solusi ke temp */
            for (b = 0; b <= baris; b++) {
                for (k = 0; k <= kolom; k++) {
                    temp[b][k] = tanpa_solusi[b][k];
                }
            }
            
            /* Mengganti kolom ke-i dengan kolom_solusi */
            for (b = 0; b <= baris; b++) {
                temp[b][i] = kolom_solusi[b]; 
            }
            
            HASIL[i] = det(temp) / det(tanpa_solusi);
        }
    }
    
    
    /* PROGRAM UTAMA */
    // public static void main (String[] args) {
    //     Cramer ge = new Cramer();
    //     double[][] arr = new double[4][5];
    //     double[] hasil = new double[4];

    //     arr[0][0] = 2;
    //     arr[1][0] = 1;
    //     arr[2][0] = 3;
    //     arr[3][0] = 2;
        
    //     arr[0][1] = -1;
    //     arr[1][1] = 0;
    //     arr[2][1] = -3;
    //     arr[3][1] = 1;
        
    //     arr[0][2] = 3;
    //     arr[1][2] = -2;
    //     arr[2][2] = 1;
    //     arr[3][2] = 4;
        
    //     arr[0][3] = 4;
    //     arr[1][3] = 7;
    //     arr[2][3] = 5;
    //     arr[3][3] = 4;
        
    //     arr[0][4] = 9;
    //     arr[1][4] = 11;
    //     arr[2][4] = 8;
    //     arr[3][4] = 10;
        
    //     ge.CramerRule (arr, hasil);
        
    //     System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA:\n");
    //     for (int x = 0; x <= 3; x++) {
    //         System.out.printf("%f ", hasil[x]);
    //     }
    //     System.out.printf("\n");
    // }
}

