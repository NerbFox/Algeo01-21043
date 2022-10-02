package VoidMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.security.Principal;
import java.security.cert.TrustAnchor;

public class Matrix {
    // private int n;
    private double[][] mat;
    private int nRows;
    private int nCols;

    public static final Character[] invalidU = { '\000' };
    public static final Character[] invalidw = { '"', '*', ':', '<', '>', '?', '\\', '|', 0x7F };

    // constructor
    public Matrix(boolean Sq, boolean spl, boolean read) {
        int i, j, n, nR, nC;
        /**
         * Proses Deklarasi matriks dan assignment setiap
         * eklemen-elemen matriks
         */
        Scanner sc = new Scanner(System.in);

        if (!read) {
            if (spl == false) {
                nR = sc.nextInt();
                if (Sq) {
                    nC = nR;
                    n = nR;
                    nRows = n;
                    nCols = n;
                } else {
                    nC = sc.nextInt();
                    nRows = nR;
                    nCols = nC;
                }
            } else {
                System.out.printf("Masukkan banyak variabel(kolom): ");
                nCols = sc.nextInt() + 1;
                System.out.printf("Masukkan banyak persamaan(baris): ");
                nRows = sc.nextInt();

            }

            mat = new double[nRows][nCols];

            for (i = 0; i < nRows; i++) {
                for (j = 0; j < nCols; j++) {
                    mat[i][j] = sc.nextDouble();
                }
            }
        }

        else {
            File file = new File(sc.nextLine());
            Scanner Sca = null;
            try {
                Sca = new Scanner(file);
            } catch (Exception ex) {
                System.out.println("File tidak ditemukan");
            }
            while (Sca == null) {
                file = new File(sc.nextLine());
                try {
                    Sca = new Scanner(file);
                } catch (Exception ex) {
                    System.out.println("File tidak ditemukan");
                }

            }
            int rows = 0;
            int cols = 0;
            ArrayList<ArrayList<Double>> Inside = new ArrayList<ArrayList<Double>>();
            String baris;
            while (Sca.hasNextLine()) {
                baris = Sca.nextLine();
                Scanner SBar = new Scanner(baris);
                cols = 0;
                Inside.add(new ArrayList<Double>());
                while (SBar.hasNextDouble()) {
                    Inside.get(rows).add(cols, SBar.nextDouble());
                    cols++;
                }
                rows++;
            }
            nRows = Inside.size();
            nCols = Inside.get(0).size();
            mat = new double[nRows][nCols];
            for (i = 0; i < nRows; i++) {
                for (j = 0; j < nCols; j++) {
                    mat[i][j] = Inside.get(i).get(j);
                }
            }
            // Matrix M = new MatrixR(Inside);
            // return M;
        }
    }
    // // ArrayList<ArrayList<Double>> M =;
    // // this.Row = li.size();
    // this.Col = li.get(0).size();
    // this.Inside = new double[Row][Col];

    // public Matrix(int nR, int nC){

    // // perlu inisialisasi 0 setiap elemen?
    // // this.mat=mat;
    // // this.nR=nR;
    // }

    // getter
    public double[][] getMat() {
        return mat;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnCols() {
        return nCols;
    }

    // setter
    public void setMat(double[][] mat) {
        this.mat = mat;
    }

    public void setnRows(int nRows) {
        this.nRows = nRows;
    }

    public void setnCols(int nCols) {
        this.nCols = nCols;
    }
    // private int getLengthSquare(){
    // return mat.;
    // }

    // Method
    public boolean isSquare() {
        return nCols == nRows; // belum diuji
    }

    public void DisplayMatriks() {
        int i, j;
        // n = nRows;
        /* M<enampilkan Matriks */
        System.out.println();
        for (i = 0; i < nRows; i++) {
            for (j = 0; j < nCols; j++) {
                if (j == nCols - 1) {
                    System.out.printf("%.2f ", mat[i][j]);
                    System.out.println();
                } else {
                    System.out.printf("%.2f ", mat[i][j]);
                }
            }
        }
        System.out.println();
        // Determinan
    }

    public double det(double[][] mat) {
        // Pre kondisi matriks berbentuk square
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

    public void invers(double[][] mat) {
        int i, j, k, u, ba;
        double d = det(mat);
        int n = mat.length;
        double[][] mAdj = new double[n][n];
        double[][] inv;
        // copy matrix mat ke mAdj

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                mAdj[i][j] = mat[i][j];
            }
        }

        // membuat adjoin
        for (ba = 0; ba < n; ba++) {
            for (k = 0; k < n; k++) {
                int a = 0, b = 0; // deklarasi harus di dalam
                // copyMatrix(m, &temp);
                u = n - 1;
                double[][] temp = new double[u][u];
                for (i = 0; i < n; i++) {
                    if (i != ba) //
                    {
                        b = 0; //
                        for (j = 0; j < n; j++) {
                            if (j != k) //
                            {
                                temp[a][b] = mat[i][j];
                                // printf("\nelmt %d %d\n", a, b);
                                b++;
                            }
                        }
                        a++;
                    }
                }

                // displayMatrix(temp);
                // printf("\n");
                mAdj[ba][k] = (Math.pow(-1, ba + k)) * det(temp);
            }
        }

        // Transpose matrix sehingga menjadi matriks Adjoin

        // int i, j, nCol = ROW_EFF(Mtemp), nRow = COL_EFF(Mtemp);
        // ROW_EFF(*m) = nRow;
        // COL_EFF(*m) = nCol;
        // di transpose dan dikali 1/det
        if (d != 0) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    mat[i][j] = (1 / d) * mAdj[j][i]; // memasukan ke mat
                }
            }
            DisplayMatriks();
        } else {
            System.out.println("\nMatriks tidak memilki balikan\n");
            // matriks tidak punya balikan
        }

        // return mAdj;
    }

    public void inversSPL() {
        int nRVar = nRows;
        int nCVar = nCols - 1;
        int i, j;
        double[][] var = new double[nRVar][nCVar];
        double[][] hasil = new double[nRows][1];
        double[][] ai = new double[nRows][1];
        // DisplayMatriks();
        for (i = 0; i < nRVar; i++) {
            for (j = 0; j < nCVar; j++) {
                var[i][j] = mat[i][j];
                // System.out.print(var[i][j]);
            }
            // System.out.println();
        }

        // System.out.println();

        // hasil[i][0]=mat[nRows-1][0];
        /// kolom jadi 3, row jadi 2
        // j=nRows-1;
        // hasil[nRows-1][0]=1;
        // System.out.println(hasil[nRows-1][0]);
        for (i = 0; i < nRows; i++) {
            hasil[i][0] = mat[i][nCols - 1];
            // System.out.println(hasil[i][0]);
        }

        invers(var);

        // nRVar . nCVar x nRows . 1 = nRVar . 1
        // Mengalikan var invers dengan hasil untuk mendapatkan hasil
        // int a = nCols-1; // banyak variabel
        System.out.println();
        // int k;
        // double sum = 0;
        // for (i = 0; i < nRows; i++) {
        // sum = 0;
        // for (k = 0; k < nCVar; k++) // asumsi nCVar = nRows
        // {
        // sum += var[i][k] * hasil[k][0];
        // }
        // ai[i][0] = sum;
        // System.out.printf("a%d: %.2f", i, ai[i][0]);
        // System.out.println();
        // }
        ai = multiplyMatrix(var, hasil, nRVar, 1, nCVar);
        for (i = 0; i < nRVar; i++) {
            // for(j=0;j<1)
            System.out.printf("a%d: %.2f", i, ai[i][0]);
            System.out.println();
        }

    }

    public double[][] multiplyMatrix(double[][] m1, double[][] m2, int Row, int Col, int In) {
        /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
        /* Mengirim hasil perkalian matriks: salinan m1 * m2 */
        double[][] m = new double[Row][Col];
        int i, j, k, nRow1 = Row, nCol1 = In, nRow2 = In, nCol2 = Col;
        int n = nCol1; // ncol1 = nrow2
        for (i = 0; i < nRow1; i++) {
            for (j = 0; j < nCol2; j++) {
                for (k = 0; k < n; k++) {
                    if (k == 0) {
                        m[i][j] = (m1[i][k]) * (m2[k][j]);
                    } else {
                        m[i][j] += (m1[i][k]) * (m2[k][j]);
                    }
                }
            }
        }
        return m;
    }

    public static void fileKeluaranDet(double d) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
        String namaFile = sc.nextLine();

        // String fy = System.getProperty("fy.name").toLowerCase();
        // Character[] char_invalid = null;
        // if (fy.contains("win")) {
        //     char_invalid = invalidw;
        // } else if (fy.contains("nix") || fy.contains("nux") || fy.contains("mac")) {
        //     char_invalid = invalidU;
        // }
        // boolean fCharValid = true;
        // fCharValid = Arrays.stream(char_invalid).noneMatch(ch -> namaFile.contains(ch.toString()));
        // if (namaFile.isEmpty() || fCharValid == false || namaFile == null || namaFile.length() > 50) {
        //     System.out.println("Nama salah, file tidak saat dibuat");
        // } else {
            try {
                PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
                output.printf("%f\n", d);
                output.close();
            } catch (IOException ex) {
                System.out.printf("error: %s\n\n", ex);
            }
        // }
    }
    public static void fileKeluaranMat(double[][] mat){

    }
    
}
