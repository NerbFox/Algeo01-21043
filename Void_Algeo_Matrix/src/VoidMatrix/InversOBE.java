package VoidMatrix;
import java.util.*;

public class InversOBE {
    /* PROGRAM JAVA UNTUK MENCARI INVERS MATRIKS DENGAN METODE REDUKSI BARIS */

    public void InversOBE (double[][] matrix) {
        /* KETERANGAN : mengubah matrix menjadi inversnya dengan metode reduksi baris (OBE) */
        /* PREKONDISI : matrix berukuran NxN */
        
        /* KAMUS LOKAL */
        int idxMax;
        int b, k, k2, index_nilai_maks, i;
        double elmt_datang, elmt_banding, faktor, pembagi;
        double[][] matriks_identitas;
        
        /* Mengambil indeks maksimum baris dan kolom matrix dan matriks_identitas */
        idxMax = (matrix.length)-1;
        
        /* Membuat matriks identitas berukuran NxN */
        matriks_identitas = new double[idxMax+1][idxMax+1];
        for (b = 0; b <= idxMax; b++) {
            for (k = 0; k <= idxMax; k++) {
                if (b == k) {
                    matriks_identitas[b][k] = 1; 
                } else {
                    matriks_identitas[b][k] = 0;
                }
            }
        }
        
        /* Mencacah kolom untuk melakukan operasi */
        for (k = 0; k <= idxMax; k++) {
            
            /* Mencari baris acuan pada matrix dari indeks [i+1..idxMax] untuk pertukaran */
            index_nilai_maks = k;
            for (b = k+1; b <= idxMax; b++) {
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
            
            
            /* Menukar baris pada matrix dan matriks_identitas */
            double[] temp = matrix[k];
            double[] temp2 = matriks_identitas[k];
            
            matrix[k] = matrix[index_nilai_maks];
            matriks_identitas[k] = matriks_identitas[index_nilai_maks];
            
            matrix[index_nilai_maks] = temp;
            matriks_identitas[index_nilai_maks] = temp2;
            
            
            /* Melakukan operasi baris elementer */
            for (b = k+1; b <= idxMax; b++) {
                faktor = matrix[b][k] / matrix[k][k];
                for (k2 = 0; k2 <= idxMax; k2++) {
                    matrix[b][k2] = matrix[b][k2] - faktor * matrix[k][k2];
                    matriks_identitas[b][k2] = matriks_identitas[b][k2] - faktor * matriks_identitas[k][k2];
                }
            }
        }
        
        /* Membuat LEADING ONE untuk setiap baris matrix */
        /* matriks_identitas ikut dioperasikan */
        i = -1;
        
        for (b = 0; b <= idxMax; b++) {
            i = i + 1;
            pembagi = matrix[b][i];
            for (k = 0; k <= idxMax; k++) {
                matrix[b][k] = matrix[b][k] / pembagi;
                matriks_identitas[b][k] = matriks_identitas[b][k] / pembagi;
            }
        }
        
        
        /* Membuat nilai di atas dan bawah LEADING ONE pada matriks menjadi 0 */
        /* matriks_identitas ikut dioperasikan */
        int jumlah_operasi;
        double nilai_acuan;
        
        for (b = 0; b < idxMax; b++) {
            jumlah_operasi = 1;
            while (jumlah_operasi < (idxMax+1)-b) {
                i = b + jumlah_operasi;
                nilai_acuan = matrix[b][i];
                for (k = 0; k <= idxMax; k++) {
                    matrix[b][k] = matrix[b][k] - nilai_acuan*matrix[i][k];
                    matriks_identitas[b][k] = matriks_identitas[b][k] - nilai_acuan*matriks_identitas[i][k];
                }
                jumlah_operasi = jumlah_operasi + 1;
            }
        }
        
        /* KETERANGAN : isi matrix_identitas sudah berupa invers dari matrix awal */
        /* Memindahkan matriks_identitas ke matrix */
        for (b = 0; b <= idxMax; b++) {
            for (k = 0; k <= idxMax; k++) {
                matrix[b][k] = matriks_identitas[b][k];
            }
        }
    }
    
    
    
    /* PROGRAM UTAMA */
    // public static void main (String[] args) {
    //     Main ge = new Main();
    //     double[][] arr = new double[4][4];

    //     arr[0][0] = 4;
    //     arr[1][0] = 0;
    //     arr[2][0] = 4;
    //     arr[3][0] = 4;
        
    //     arr[0][1] = -1;
    //     arr[1][1] = 0;
    //     arr[2][1] = 1;
    //     arr[3][1] = 0;
        
    //     arr[0][2] = 1;
    //     arr[1][2] = -3;
    //     arr[2][2] = 0;
    //     arr[3][2] = 3;
        
    //     arr[0][3] = 6;
    //     arr[1][3] = 0;
    //     arr[2][3] = 0;
    //     arr[3][3] = 2;
        
    //     ge.InversOBE(arr);
        
    //     int x, y;
    //     for (x = 0; x <= 3; x++) {
    //         for (y = 0; y <= 3; y++) {
    //             if (y == 3) {
    //                 System.out.printf("%f\n", arr[x][y]);
    //             } else {
    //                 System.out.printf("%f ", arr[x][y]);       
    //             }
    //         }
    //     }
    //     System.out.printf("\n");
    // }
}
