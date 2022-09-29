/* PROGRAM JAVA UNTUK MELAKUKAN ELIMINASI GAUSS */
package VoidMatrix;
import java.util.*;

public class EliminasiGauss {
    public void GaussElimination (double[][] matrix) {
        /* KETERANGAN : Melakukan elminasi Gauss untuk matrix augmented berukuran baris*(kolom+1) */
        
        /* KAMUS LOKAL */
        int baris, kolom;
        int b, k, k2, index_nilai_maks, i;
        double elmt_datang, elmt_banding, faktor, pembagi;
        
        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length)-1;
        kolom = (matrix[0].length)-2;
        
        /* Mencacah kolom untuk melakukan operasi */
        for (k = 0; k <= kolom; k++) {
            
            /* Mencari baris acuan dari indeks [i+1..baris] untuk pertukaran */
            index_nilai_maks = k;
            for (b = k+1; b <= baris; b++) {
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
            for (b = k+1; b <= baris; b++) {
                faktor = matrix[b][k] / matrix[k][k];
                for (k2 = k; k2 <= kolom+1; k2++) {
                    matrix[b][k2] = matrix[b][k2] - faktor * matrix[k][k2];
                }
            }
        }
        
        /* Membuat LEADING ONE untuk setiap baris */
        i = -1;
        
        for (b = 0; b <= baris; b++) {
            i = i + 1;
            pembagi = matrix[b][i];
            for (k = 0; k <= kolom+1; k++) {
                matrix[b][k] = matrix[b][k] / pembagi;
            }
        }
    }
    
    
    public void SubstitusiMundur (double[][] matrix, double[] HASIL) {
        /* KETERANGAN : Membuat array berisi solusi terurut dari SPL berdasarkan matrix augmented */
        /* PREKONDISI : GaussElimination(matrix) */
        
        /* KAMUS LOKAL */
        double[][] tanpa_solusi = new double [matrix.length][(matrix[0].length)-1];
        double[] kolom_solusi = new double [matrix.length];
        int b, k;
        double jumlah_known;
        
        /* ALGORITMA */
        /* Memisahkan solusi dari matrix augmented */
        for (b = 0; b <= (matrix.length)-1; b++) {
            kolom_solusi[b] = matrix[b][(matrix[0].length)-1];
        }
        
        for (b = 0; b <= (matrix.length)-1; b++) {
            for (k = 0; k <= (matrix[0].length)-2; k++) {
                tanpa_solusi[b][k] = matrix[b][k];
            }
        }
        
        /* Substitusi mundur untuk mendapatkan matrix hasil */
        for (b = (kolom_solusi.length)-1; b >= 0; b--) {
            jumlah_known = 0.0;
            for (k = b+1; k < (kolom_solusi.length); k++) {
                jumlah_known = jumlah_known + tanpa_solusi[b][k] * HASIL[k];
            }
            HASIL[b] = (kolom_solusi[b] - jumlah_known);
        }
    }
    
    
    /* PROGRAM UTAMA */
    // public static void main (String[] args) {
    //      ge = new Main();
    //     double[][] arr = new double[4][5];

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
        
    //     ge.GaussElimination(arr);
        
    //     int x, y;
    //     for (x = 0; x <= 3; x++) {
    //         for (y = 0; y <= 4; y++) {
    //             if (y == 4) {
    //                 System.out.printf("%f\n", arr[x][y]);
    //             } else {
    //                 System.out.printf("%f ", arr[x][y]);       
    //             }
    //         }
    //     }
    //     System.out.printf("\n");
        
    //     double[] hasil = new double [4];
    //     ge.SubstitusiMundur(arr, hasil);
        
    //     System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA:\n");
    //     for (x = 0; x <= 3; x++) {
    //         System.out.printf("%f ", hasil[x]);
    //     }
    //     System.out.printf("\n");
    // }
}