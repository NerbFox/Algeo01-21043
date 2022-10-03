/* PROGRAM JAVA UNTUK MELAKUKAN ELIMINASI GAUSS-JORDAN */
package VoidMatrix;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("resource")

public class GaussJordan {

    public double GaussJordanForDet(double[][] matrix) {
        /* KETERANGAN : mengembalikan nilai determinan matrix berukuran N*N */
        /* Perhitungan menggunakan reduksi baris (OBE) */

        /* KAMUS LOKAL */
        int baris, kolom;
        int b, k, k2, index_nilai_maks, i, p;
        double elmt_datang, elmt_banding, faktor;

        /* Mengambil indeks maksimal baris dan kolom matrix */
        baris = (matrix.length) - 1;
        kolom = baris;

        /* Inisialisasi */
        p = 0;

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

            if (index_nilai_maks != k) {
                p = p + 1;
            }

            /* Melakukan operasi baris elementer */
            for (b = k + 1; b <= baris; b++) {
                faktor = matrix[b][k] / matrix[k][k];
                for (k2 = k; k2 <= kolom; k2++) {
                    matrix[b][k2] = matrix[b][k2] - faktor * matrix[k][k2];
                }
            }
        }

        double det = 1;
        for (i = 0; i <= baris; i++) {
            det = det * matrix[i][i];
        }
        det = Math.pow(-1, p) * det;
        return det;
    }

    public boolean NotUniqueSolution(double[][] matrix) {
        /*
         * KETERANGAN : mengembalikan nilai true apabila ada baris pada matrix yang
         * seluruhnya 0
         */

        /* KAMUS LOKAL */
        boolean nol_semua, found;
        int b, k, baris, kolom;

        /* ALGORITMA */
        baris = (matrix.length) - 1;
        kolom = (matrix[0].length) - 2;

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

    public int NotUniqueSolutionRow(double[][] matrix) {
        /* KETERANGAN : mengembalikan indeks baris yang seluruhnya 0 pada matrix */
        /* PREKONDISI : NotUniqueSolution(matrix) */

        /* KAMUS LOKAL */
        boolean nol_semua, found;
        int b, k, baris, kolom, baris_temu;

        /* ALGORITMA */
        baris = (matrix.length) - 1;
        kolom = (matrix[0].length) - 2;

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

    public boolean baris_nol_semua(double[] baris) {
        /* KETERANGAN : mengembalikan true jika elemen pada baris bernilai nol semua */

        /* KAMUS LOKAL */
        int i;
        boolean nol;

        /* ALGORITMA */
        nol = true;
        for (i = 0; i <= baris.length - 1; i++) {
            if (baris[i] != 0) {
                nol = false;
            }
        }
        return (nol);
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

    public void GaussJordanElimination(double[][] matrix, double[] HASIL) {
        /*
         * KETERANGAN : Melakukan elminasi Gauss Jordan untuk matrix augmented berukuran
         * baris*(kolom+1)
         */

        /* KAMUS LOKAL */
        char simpan;
        int baris, kolom;
        int b, k, k2, index_nilai_maks, i;
        double elmt_datang, elmt_banding, faktor, pembagi, determinan, NotUniqueElmt;
        boolean NotUnique;
        Scanner sc = new Scanner(System.in);

        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length) - 1;
        kolom = (matrix[0].length) - 2;

        /* Memisahkan kolom solusi dari matrix */
        double[][] tanpa_solusi = new double[matrix.length][(matrix[0].length) - 1];
        double[] kolom_solusi = new double[matrix.length];

        for (b = 0; b <= baris; b++) {
            kolom_solusi[b] = matrix[b][kolom + 1];
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
            NotUniqueElmt = matrix[NotUniqueSolutionRow(tanpa_solusi)][kolom + 1];
        }

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
                if (Double.isNaN(matrix[b][k]) == true || Double.isInfinite(matrix[b][k]) == true) {
                    matrix[b][k] = 0;
                }
            }
        }

        /* MENCETAK SOLUSI SPL BERDASARKAN JENISNYA */
        if (determinan == 0 || NotUnique == true) {
            if (NotUniqueElmt != 0) {
                System.out.printf("Sistem Persamaan Linier Anda tidak punya solusi.\n");
            } else {
                SubstitusiParametrik(matrix);
            }
        } else {

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
                    if (y == kolom + 1) {
                        System.out.printf("%.2f\n", matrix[x][y]);
                    } else {
                        System.out.printf("%.2f ", matrix[x][y]);
                    }
                }
            }
            System.out.printf("\n");
            System.out.printf("\n");

            System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA: \n");
            for (x = 0; x < matrix.length; x++) {
                System.out.printf("x%d: %.3f ", x, HASIL[x]);
                System.out.printf("\n");
            }
            // double[] hasil = new double [baris+1];

            System.out.print("Apakah ingin disimpan (y/n) : ");
            simpan = sc.next().charAt(0);
            System.out.println();
            if (simpan == 'y' || simpan == 'Y') {
                // m.fileKeluaranDet(d);
                fileKeluaranSPL(HASIL, false);

            } else {
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

    public void SubstitusiParametrik(double[][] matrix) {
        /*
         * KETERANGAN : Membuat array berisi solusi parametrik terurut dari matrix
         * augmented
         */

        /* KAMUS LOKAL */
        int baris, kolom, b, k;

        /* ALGORITMA */
        /* Mengambil indeks maksimum baris dan kolom matrix tanpa kolom solusi */
        baris = (matrix.length) - 1;
        kolom = (matrix[0].length) - 2;

        System.out.printf("Solusi Sistem Persamaan Linier Anda ada banyak, namun harus memenuhi:\n");
        /* Mencacah baris dan kolom dari paling ujung bawah */
        for (b = baris; b >= 0; b--) {
            if (baris_nol_semua(matrix[b]) == false) {
                boolean ada_solusi_baris = false;
                boolean kolom_solusi_sudah = false;
                int var_ke = 0;

                for (k = kolom; k >= 0; k--) {
                    if (matrix[b][k] == 0) {
                        System.out.printf("x%d = 0\n", k + 1);
                    }
                }

                for (k = kolom; k >= 0; k--) {

                    if (matrix[b][k] != 0 && Double.isNaN(matrix[b][k]) == false
                            && Double.isInfinite(matrix[b][k]) == false) {
                        if (matrix[b][k] < 0) {
                            if (var_ke == kolom) {
                                System.out.printf("%f x%d = %f\n", matrix[b][k], k + 1, matrix[b][kolom + 1]);
                                ada_solusi_baris = true;
                                kolom_solusi_sudah = true;
                            } else {
                                System.out.printf("%f x%d ", matrix[b][k], k + 1);
                                ada_solusi_baris = true;
                                var_ke = var_ke + 1;
                            }

                        } else {
                            if (var_ke == kolom) {
                                System.out.printf("+ %f x%d = %f\n", matrix[b][k], k + 1, matrix[b][kolom + 1]);
                                ada_solusi_baris = true;
                                kolom_solusi_sudah = true;
                            } else if (var_ke == 0) {
                                System.out.printf("%f x%d ", matrix[b][k], k + 1);
                                ada_solusi_baris = true;
                                var_ke = var_ke + 1;
                            } else {
                                System.out.printf("+ %f x%d ", matrix[b][k], k + 1);
                                ada_solusi_baris = true;
                                var_ke = var_ke + 1;
                            }
                        }
                    }
                }
                if (kolom_solusi_sudah == false && ada_solusi_baris == true) {
                    System.out.printf("= %f\n", matrix[b][kolom + 1]);
                }
                if (ada_solusi_baris == true && b != 0) {
                    System.out.printf("\nATAU\n\n");
                }
            }
        }

        char simpan;
        Scanner sc = new Scanner(System.in);
        System.out.print("Apakah ingin disimpan (y/n) : ");
        simpan = sc.next().charAt(0);
        System.out.println();
        if (simpan == 'y' || simpan == 'Y') {
            // m.fileKeluaranDet(d);
            fileKeluaranSPLParam(matrix);

        } else {
            System.out.println("File tidak disimpan");
        }
    }

    public static void fileKeluaranSPL(double[] HASIL, boolean param) {
        int x;
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
        String namaFile = sc.nextLine();

        try {
            PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
            if (param == false) {
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

    public void fileKeluaranSPLParam(double[][] matrix) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
        String namaFile = sc.nextLine();

        try {
            PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
            int b, k;
            int baris = (matrix.length) - 1;
            int kolom = (matrix[0].length) - 2;
            for (b = baris; b >= 0; b--) {
                if (baris_nol_semua(matrix[b]) == false) {
                    boolean ada_solusi_baris = false;
                    boolean kolom_solusi_sudah = false;
                    int var_ke = 0;

                    for (k = kolom; k >= 0; k--) {
                        if (matrix[b][k] == 0) {
                            output.printf("x%d = 0\n", k + 1);
                        }
                    }

                    for (k = kolom; k >= 0; k--) {

                        if (matrix[b][k] != 0 && Double.isNaN(matrix[b][k]) == false
                                && Double.isInfinite(matrix[b][k]) == false) {
                            if (matrix[b][k] < 0) {
                                if (var_ke == kolom) {
                                    output.printf("%f x%d = %f\n", matrix[b][k], k + 1, matrix[b][kolom + 1]);
                                    ada_solusi_baris = true;
                                    kolom_solusi_sudah = true;
                                } else {
                                    output.printf("%f x%d ", matrix[b][k], k + 1);
                                    ada_solusi_baris = true;
                                    var_ke = var_ke + 1;
                                }

                            } else {
                                if (var_ke == kolom) {
                                    output.printf("+ %f x%d = %f\n", matrix[b][k], k + 1, matrix[b][kolom + 1]);
                                    ada_solusi_baris = true;
                                    kolom_solusi_sudah = true;
                                } else if (var_ke == 0) {
                                    output.printf("%f x%d ", matrix[b][k], k + 1);
                                    ada_solusi_baris = true;
                                    var_ke = var_ke + 1;
                                } else {
                                    output.printf("+ %f x%d ", matrix[b][k], k + 1);
                                    ada_solusi_baris = true;
                                    var_ke = var_ke + 1;
                                }
                            }
                        }
                    }
                    if (kolom_solusi_sudah == false && ada_solusi_baris == true) {
                        output.printf("= %f\n", matrix[b][kolom + 1]);
                    }
                    if (ada_solusi_baris == true && b != 0) {
                        output.printf("\nATAU\n\n");
                    }
                }
            }
            output.close();
        } catch (IOException ex) {
            System.out.printf("error: %s\n\n", ex);
        }
    }
}