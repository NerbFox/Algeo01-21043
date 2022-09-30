package VoidMatrix;

import java.util.Scanner;
import java.lang.Math;

public class Matrix {
    // private int n;
    private double[][] mat;
    private int nRows;
    private int nCols;

    // constructor
    public Matrix(boolean Sq) {
        int i, j, n, nR, nC;
        /**
         * Proses Deklarasi matriks dan assignment setiap
         * eklemen-elemen matriks
         */
        Scanner sc = new Scanner(System.in);
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
        mat = new double[nR][nC];

        for (i = 0; i < nRows; i++) {
            for (j = 0; j < nCols; j++) {
                mat[i][j] = sc.nextDouble();
            }
        }
    }
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
        } else {
            System.out.println("Matriks tidak memilki balikan");
            // matriks tidak punya balikan
        }

        // return mAdj;
    }

}
