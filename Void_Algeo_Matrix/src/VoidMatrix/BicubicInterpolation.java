package VoidMatrix;

import java.lang.Math;
import java.util.Scanner;

public class BicubicInterpolation {
    public BicubicInterpolation() {
        // Matrix m = new Matrix(true);
        int i, j, n = 4, v, w, m = n * n;
        Scanner sc = new Scanner(System.in);
        double[][] f = new double[n][n]; // f(x,y)
        double[][] fL = new double[m][1]; // f(x,y) 16 x 1
        double[][] a = new double[n][n]; // a
        double[][] aL = new double[m][1];
        double[][] X = new double[m][m];
        double[][] xk = new double[1][n];
        double[][] yk = new double[n][1];

        for (i=0;i<4;i++){
        for (j=0; j<4; j++){ // baris y dan kolom x
            System.out.printf("f(%d,%d) = ", j-1, i-1);
            f[i][j] = sc.nextDouble();
            // System.out.println();
        }
        }

        // DisplayMatriks(f, 4, 4);

        // Pembuatan Matriks X
        i = 0;
        j = 0;
        int x = -1, y = -1;
        for (v = 0; v < m; v++) {
            if ((v) % 4 == 0) {
                x = -1;
                if (v == 0) {
                    y = -1;
                } else {
                    y++;
                }
            } else {
                x++;
            }

            for (w = 0; w < m; w++) {
                if ((w) % 4 == 0) {
                    i = 0;
                    if (w == 0) {
                        j = 0;
                    } else {
                        j++;
                    }
                } else {
                    i++;
                }
                X[v][w] = (Math.pow(y, j)) * (Math.pow(x, i));
                // if(X[v][w]==0){
                // X[v][w]*=0;
                // }
                // System.out.printf("%.1f ",X[v][w]);
                // System.out.printf("x: %d y: %d i: %d j: %d",x,y,i,j);
                // System.out.println();
            }
            // System.out.println();
        }

        // DisplayMatriks(X, 16, 16);
        // f(x,y) = X a
        // a = X^-1 f(x,y)
        double g;
        DisplayMatriks(X, m, m);
        // g = det(X);
        // System.out.printf("%f", g);
        // Invers matriks X
        invers(X);
        DisplayMatriks(X, m, m);
        // menjadikan
        int o = 0;
        for (i=0; i<n; i++){
            for (j=0; j<n; j++){
            fL[o][0]=f[i][j];
            o++;
            }
        }

        // Mengalikan invers X dengan matriks fL dan memasukkan ke dalam matriks a
        // m . m x m . 1

        aL = multiplyMatrix(X, fL, m, 1, m);

        //
        o = 0;
        for (i=0; i<n; i++){
            for (j=0; j<n; j++){
                    a[i][j]=aL[o][0];
                    o++;
            }
        }

        double inputX, inputY;
        System.out.print("Masukkan nilai X: ");
        inputX = sc.nextDouble();
        System.out.println();
        System.out.print("Masukkan nilai Y: ");
        inputY = sc.nextDouble();

        // Inisiasi xk dan yk
        for (j=0; j<n; j++){
            xk[0][j]=Math.pow(inputX,j);
            yk[j][0]=Math.pow(inputY,j);
        }
        // DisplayMatriks(yk, n, 1);
        // menghasilkan f(x,y)
        double temp[][] = new double[1][n];
        temp = multiplyMatrix(xk, a, 1, n, n);
        double fxy[][] = new double[1][1];
        fxy = multiplyMatrix(temp, yk, 1, 1, 4);
        System.out.println();
        System.out.printf("f(%.2f,%.2f) : %f", inputX, inputY, fxy[0][0]);
        System.out.println();

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
        }
    }

    // public double det(double[][] mat) {
    //     // Pre kondisi matriks berbentuk square
    //     double d = 0.0;
    //     int i, j, a, b, k, u;
    //     int num = mat.length;
    //     if (num != 1) {
    //         for (k = 0; k < num; k++) {
    //             a = 0;
    //             b = 0;
    //             u = num - 1;
    //             double[][] temp = new double[u][u];
    //             for (i = 0; i < num; i++) {
    //                 if (i != 0) {
    //                     b = 0;
    //                     for (j = 0; j < num; j++) {
    //                         if (j != k) {
    //                             temp[a][b] = mat[i][j];
    //                             b++;
    //                         }
    //                     }
    //                     a++;
    //                 }
    //             }
    //             d += (Math.pow(-1, k) * mat[0][k] * det(temp));
    //         }
    //     } else {
    //         d = mat[0][0];
    //     }
    //     return d;
    // }

    public double det(double[][] matrix) {
        /* KETERANGAN : mengembalikan nilai determinan matrix berukuran N*N */
        /* Perhitungan menggunakan reduksi baris (OBE) */

        /* KAMUS LOKAL */
        int baris, kolom;
        int b, k, k2, index_nilai_maks, i, p;
        double elmt_datang, elmt_banding, faktor, pembagi;

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

    public void DisplayMatriks(double[][] mat, int nRows, int nCols) {
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
}
