package VoidMatrix;
import java.util.Scanner;
import java.lang.Math;

public class Matrix {
    // private int n;
    private double[][] mat;
    private int nRows; 
    private int nCols;
    
    // constructor
    public Matrix(boolean Sq){
        int i, j, n, nR, nC;
        /**
         * Proses Deklarasi matriks dan assignment setiap
         * eklemen-elemen matriks
         */
        Scanner sc = new Scanner(System.in);
        nR = sc.nextInt();
        if (Sq){
            nC=nR;
            n=nR;
            nRows=n;
            nCols=n;
        }
        else{
            nC=sc.nextInt();
            nRows=nR;
            nCols=nC;
        }
        mat = new double[nR][nC];
        
        for (i = 0; i < nRows; i++) {
            for (j = 0; j < nCols; j++) {
                mat[i][j] = sc.nextDouble();
            }
        }
    }
    // public Matrix(int nR, int nC){
        
        //     // perlu inisialisasi 0 setiap elemen?
        //     // this.mat=mat;
        //     // this.nR=nR;
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
            //     return mat.;
    // }

    // Method
    public boolean isSquare(){
        return nCols==nRows;  // belum diuji
    }

    public void DisplayMatriksDet() {
        int i, j, n;
        n=nRows;
        /* M<enampilkan Matriks */
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (j == n - 1) {
                    System.out.println(mat[i][j] + " ");
                    // System.out.println("");
                } else {
                    System.out.print(mat[i][j] + " ");
                }
            }
        }
        System.out.println("Selesai");
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

}
