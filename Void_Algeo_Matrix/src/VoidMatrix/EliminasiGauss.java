// /* PROGRAM JAVA UNTUK MELAKUKAN ELIMINASI GAUSS */
package VoidMatrix;
/* PROGRAM JAVA UNTUK MELAKUKAN ELIMINASI GAUSS */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class EliminasiGauss {
    public boolean NotUniqueSolution (double[][] matrix) {
        /* KETERANGAN : mengembalikan nilai true apabila ada baris pada matrix yang seluruhnya 0 */
        
        /* KAMUS LOKAL */
        boolean nol_semua, found;
        int b, k, baris, kolom;
        
        /* ALGORITMA */
        baris = (matrix.length)-1;
        kolom = (matrix[0].length)-2;
        
        found = false;
        
        for (b = 0; b <= baris; b++) {
            k = 0;
            while (found == false && k <= kolom) {
                nol_semua = true;
                for (k = 0; k <= kolom; k++) {
                    if (matrix[b][k] != 0) {
                        nol_semua = false;
                    }
                }
                
                if (nol_semua == true) {
                        found = true;
                }
            }
        }
        
        return (found);
    }
    
    public int NotUniqueSolutionRow (double[][] matrix) {
        /* KETERANGAN : mengembalikan indeks baris yang seluruhnya 0 pada matrix */
        /* PREKONDISI : NotUniqueSolution(matrix) */
        
        /* KAMUS LOKAL */
        boolean nol_semua, found;
        int b, k, baris, kolom, baris_temu;
        
        /* ALGORITMA */
        baris = (matrix.length)-1;
        kolom = (matrix[0].length)-2;
        
        found = false;
        baris_temu = -1;
        
        for (b = 0; b <= baris; b++) {
            k = 0;
            while (found == false && k <= kolom) {
                nol_semua = true;
                for (k = 0; k <= kolom; k++) {
                    if (matrix[b][k] != 0) {
                        nol_semua = false;
                    }
                }
                
                if (nol_semua == true) {
                        found = true;
                        baris_temu = b;
                }
            }
        }
        return (baris_temu);
    }
    
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

    public void GaussElimination (double[][] matrix) {
        /* KETERANGAN : Melakukan elminasi Gauss untuk matrix augmented berukuran baris*(kolom+1) */
        
        /* KAMUS LOKAL */
        int baris, kolom;
        int b, k, k2, index_nilai_maks, i;
        double elmt_datang, elmt_banding, faktor, pembagi, determinan, NotUniqueElmt;
        boolean NotUnique;
        
        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length)-1;
        kolom = (matrix[0].length)-2;
        
        /* Memisahkan kolom solusi dari matrix */
        double[][] tanpa_solusi = new double [matrix.length][(matrix[0].length)-1];
        double[] kolom_solusi = new double [matrix.length];
        
        for (b = 0; b <= baris; b++) {
            kolom_solusi[b] = matrix[b][kolom+1];
        }
        
        for (b = 0; b <= baris; b++) {
            for (k = 0; k <= kolom; k++) {
                tanpa_solusi[b][k] = matrix[b][k];
            }
        }
        
        /* Parameter untuk menentukan jenis solusi SPL */
        determinan = det(tanpa_solusi);
        NotUnique = NotUniqueSolution(matrix);
        NotUniqueElmt = 0;
        if (NotUnique == true) {
            NotUniqueElmt = matrix[NotUniqueSolutionRow(tanpa_solusi)][kolom+1];
        }
        
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
        
        
        /* MENCETAK SOLUSI SPL BERDASARKAN JENISNYA */
        if (determinan == 0 || NotUnique == true) {
            if (NotUniqueElmt != 0) {
                System.out.printf("Sistem Persamaan Linier Anda tidak punya solusi.\n");
            } else {
                SubstitusiParametrik (matrix);
            }
        } else {
            double[] hasil = new double [baris+1];
            SubstitusiMundur (matrix, hasil);
            System.out.printf("Solusi Sistem Persamaan Linier Anda:\n");
            DisplayMatriks(matrix);
            for (b = 0; b <= baris; b++) {
                System.out.printf("x%d = %f\n", b+1, hasil[b]);
            }
            
            char simpan;
            Scanner sc = new Scanner(System.in);
            System.out.print("Apakah ingin disimpan (y/n) : ");
            simpan = sc.next().charAt(0);
            System.out.println();
            if(simpan=='y'||simpan=='Y'){
                // m.fileKeluaranDet(d);
                fileKeluaranSPL(hasil, false);

            }
            else{
                System.out.println("File tidak disimpan");
            }
        }
    }
    
    public void DisplayMatriks(double[][] mat) {
        int i, j;
        // n = nRows;
        /* M<enampilkan Matriks */
        System.out.println();
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat[0].length; j++) {
                if (j == mat[0].length - 1) {
                    System.out.printf("%.2f ", mat[i][j]);
                    System.out.println();
                } else {
                    System.out.printf("%.2f ", mat[i][j]);
                }
            }
        }
        System.out.println();
    }
    
    public void SubstitusiParametrik (double[][] matrix) {
        /* KETERANGAN : Membuat array berisi solusi parametrik terurut dari matrix augmented */
        
        /* KAMUS LOKAL */
        int baris, kolom, b, k, i;
        
        /* ALGORITMA */
        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length)-1;
        kolom = (matrix[0].length)-2;
        
        System.out.printf("Solusi Sistem Persamaan Linier Anda ada banyak, namun harus memenuhi:\n");
        /* Mencacah baris dan kolom dari paling ujung bawah */
        for (b = baris; b >= 0; b--) {
            for (k = kolom; k >= 0; k--) {
                if (matrix[b][k] == 0) {
                    System.out.printf("x%d = 0\n", k+1);
                }
            }
        }
        
        for (b = baris; b >= 0; b--) {
            for (k = kolom; k >= 0; k--) {
                
                if (matrix[b][k] != 0 && Double.isNaN(matrix[b][k]) == false && Double.isInfinite(matrix[b][k]) == false) {
                    if (matrix[b][k] < 0) {
                        if (k == 0) {
                            System.out.printf("%f x%d = %f\n", matrix[b][k], k+1, matrix[b][kolom+1]);
                        } else {
                            System.out.printf("%f x%d ", matrix[b][k], k+1);
                        }
                        
                    } else {
                        if (k == 0) {
                            System.out.printf("+ %f x%d = %f\n", matrix[b][k], k+1, matrix[b][kolom+1]);
                        } else if (k == kolom) {
                            System.out.printf("%f x%d ", matrix[b][k], k+1);
                        } else {
                            System.out.printf("+ %f x%d ", matrix[b][k], k+1);
                        }
                    }
                }
            }
        }
        char simpan;
        Scanner sc = new Scanner(System.in);
        System.out.print("Apakah ingin disimpan (y/n) : ");
            simpan = sc.next().charAt(0);
            System.out.println();
            if(simpan=='y'||simpan=='Y'){
                // m.fileKeluaranDet(d);
                fileKeluaranSPLParam(matrix);

            }
            else{
                System.out.println("File tidak disimpan");
            }
    }
    
    public void SubstitusiMundur (double[][] matrix, double[] HASIL) {
        /* KETERANGAN : Membuat array berisi solusi terurut dari SPL berdasarkan matrix augmented */
        
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
    
    public static void fileKeluaranSPL(double[] HASIL, boolean param){
        int x;
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
        String namaFile = sc.nextLine();

        try {
            PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
            if (param == false){
                for (x = 0; x < HASIL.length; x++) {
                    output.printf("x%d: %.3f ", x, HASIL[x]);
                    output.println();
                }
            }
            output.close();
        } catch (IOException ex) {
            System.out.printf("error: %s\n\n", ex);
        }
    }
    
    public static void fileKeluaranSPLParam(double[][] matrix){
        int x;
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
        String namaFile = sc.nextLine();

        try {
            PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
            int b, k;
            int baris = (matrix.length)-1;
            int kolom = (matrix[0].length)-2;
            for (b = baris; b >= 0; b--) {
                for (k = kolom; k >= 0; k--) {
                    
                    if (matrix[b][k] != 0 && Double.isNaN(matrix[b][k]) == false && Double.isInfinite(matrix[b][k]) == false) {
                        if (matrix[b][k] < 0) {
                            if (k == 0) {
                                output.printf("%f x%d = %f\n", matrix[b][k], k+1, matrix[b][kolom+1]);
                            } else {
                                output.printf("%f x%d ", matrix[b][k], k+1);
                            }
                            
                        } else {
                            if (k == 0) {
                                output.printf("+ %f x%d = %f\n", matrix[b][k], k+1, matrix[b][kolom+1]);
                            } else if (k == kolom) {
                                output.printf("%f x%d ", matrix[b][k], k+1);
                            } else {
                                output.printf("+ %f x%d ", matrix[b][k], k+1);
                            }
                        }
                    }
                }
            }
            output.close();
        } catch (IOException ex) {
            System.out.printf("error: %s\n\n", ex);
        }
    }
    
    /* PROGRAM UTAMA */
    // public static void main (String[] args) {
    //     Main ge = new Main();
    //     double[][] arr = new double[4][5];
    //     double[] hasil = new double [4];

    //     arr[0][0] = 2;
    //     arr[1][0] = -1;
    //     arr[2][0] = 3;
    //     arr[3][0] = 2;
        
    //     arr[0][1] = -1;
    //     arr[1][1] = 1;
    //     arr[2][1] = 1;
    //     arr[3][1] = 2;
        
    //     arr[0][2] = -1;
    //     arr[1][2] = 2;
    //     arr[2][2] = -1;
    //     arr[3][2] = -2;
        
    //     arr[0][3] = 1;
    //     arr[1][3] = 2;
    //     arr[2][3] = -3;
    //     arr[3][3] = -1;
        
    //     arr[0][4] = -2;
    //     arr[1][4] = -5;
    //     arr[2][4] = 8;
    //     arr[3][4] = 6;
        
    //     ge.GaussElimination(arr);
    // }
}



// import java.util.*;

// public class EliminasiGauss {
//     public void GaussElimination(double[][] matrix) {
//         /*
//          * KETERANGAN : Melakukan elminasi Gauss untuk matrix augmented berukuran
//          * baris*(kolom+1)
//          */

//         /* KAMUS LOKAL */
//         int baris, kolom;
//         int b, k, k2, index_nilai_maks, i;
//         double elmt_datang, elmt_banding, faktor, pembagi;
//         //4 4 4 2 4 1 1 0 -1 3 0 -3 1 6 14 3 6
//         /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
//         baris = (matrix.length) - 1;
//         kolom = (matrix[0].length) - 2;

//         /* Mencacah kolom untuk melakukan operasi */
//         for (k = 0; k <= kolom; k++) {

//             /* Mencari baris acuan dari indeks [i+1..baris] untuk pertukaran */
//             index_nilai_maks = k;
//             for (b = k + 1; b <= baris; b++) {
//                 elmt_datang = matrix[b][k];
//                 if (elmt_datang < 0) {
//                     elmt_datang = elmt_datang * -1;
//                 }

//                 elmt_banding = matrix[index_nilai_maks][k];
//                 if (elmt_banding < 0) {
//                     elmt_banding = elmt_banding * -1;
//                 }

//                 if (elmt_datang > elmt_banding) {
//                     index_nilai_maks = b;
//                 }
//             }

//             /* Menukar baris pada matrix */
//             double[] temp = matrix[k];
//             matrix[k] = matrix[index_nilai_maks];
//             matrix[index_nilai_maks] = temp;

//             /* Melakukan operasi baris elementer */
//             for (b = k + 1; b <= baris; b++) {
//                 faktor = matrix[b][k] / matrix[k][k];
//                 for (k2 = k; k2 <= kolom + 1; k2++) {
//                     matrix[b][k2] = matrix[b][k2] - faktor * matrix[k][k2];
//                 }
//             }
//         }

//         /* Membuat LEADING ONE untuk setiap baris */
//         i = -1;

//         for (b = 0; b <= baris; b++) {
//             i = i + 1;
//             pembagi = matrix[b][i];
//             for (k = 0; k <= kolom + 1; k++) {
//                 matrix[b][k] = matrix[b][k] / pembagi;
//             }
//         }
//     }

//     public void SubstitusiMundur(double[][] matrix, double[] HASIL) {
//         /*
//          * KETERANGAN : Membuat array berisi solusi terurut dari SPL berdasarkan matrix
//          * augmented
//          */
//         /* PREKONDISI : GaussElimination(matrix) */

//         /* KAMUS LOKAL */
//         double[][] tanpa_solusi = new double[matrix.length][(matrix[0].length) - 1];
//         double[] kolom_solusi = new double[matrix.length];
//         int b, k;
//         double jumlah_known;

//         /* ALGORITMA */
//         /* Memisahkan solusi dari matrix augmented */
//         for (b = 0; b <= (matrix.length) - 1; b++) {
//             kolom_solusi[b] = matrix[b][(matrix[0].length) - 1];
//         }

//         for (b = 0; b <= (matrix.length) - 1; b++) {
//             for (k = 0; k <= (matrix[0].length) - 2; k++) {
//                 tanpa_solusi[b][k] = matrix[b][k];
//             }
//         }

//         /* Substitusi mundur untuk mendapatkan matrix hasil */
//         for (b = (kolom_solusi.length) - 1; b >= 0; b--) {
//             jumlah_known = 0.0;
//             for (k = b + 1; k < (kolom_solusi.length); k++) {
//                 jumlah_known = jumlah_known + tanpa_solusi[b][k] * HASIL[k];
//             }
//             HASIL[b] = (kolom_solusi[b] - jumlah_known);
//         }
//         int x;
//         System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA:\n");
//         for (x = 0; x < matrix.length; x++) {
//             System.out.printf("a%d: %.3f ", x, HASIL[x]);
//             System.out.printf("\n");
//         }

//     }

//     /* PROGRAM UTAMA */
//     // public static void main (String[] args) {
//     // ge = new Main();
//     // double[][] arr = new double[4][5];

//     // arr[0][0] = 2;
//     // arr[1][0] = 1;
//     // arr[2][0] = 3;
//     // arr[3][0] = 2;

//     // arr[0][1] = -1;
//     // arr[1][1] = 0;
//     // arr[2][1] = -3;
//     // arr[3][1] = 1;

//     // arr[0][2] = 3;
//     // arr[1][2] = -2;
//     // arr[2][2] = 1;
//     // arr[3][2] = 4;

//     // arr[0][3] = 4;
//     // arr[1][3] = 7;
//     // arr[2][3] = 5;
//     // arr[3][3] = 4;

//     // arr[0][4] = 9;
//     // arr[1][4] = 11;
//     // arr[2][4] = 8;
//     // arr[3][4] = 10;

//     // ge.GaussElimination(arr);

//     // int x, y;
//     // for (x = 0; x <= 3; x++) {
//     // for (y = 0; y <= 4; y++) {
//     // if (y == 4) {
//     // System.out.printf("%f\n", arr[x][y]);
//     // } else {
//     // System.out.printf("%f ", arr[x][y]);
//     // }
//     // }
//     // }
//     // System.out.printf("\n");

//     // double[] hasil = new double [4];
//     // ge.SubstitusiMundur(arr, hasil);

// }