package VoidMatrix;
import java.lang.Math;
import java.util.Scanner;

public class BicubicInterpolation {
    public BicubicInterpolation(){
        // Matrix m = new Matrix(true);
        int i, j, n=4, v, w, m=n*n;
        Scanner sc = new Scanner(System.in);
        double[][] f = new double[n][n];
        double[][] a = new double[n][n];
        double[][] X = new double[m][m];
        
        // for (i=0;i<4;i++){
        //     for (j=0; j<4; j++){ // baris y dan kolom x
        //         System.out.printf("f(%d,%d) = ", j-1, i-1);
        //         f[i][j] = sc.nextDouble();
        //         // System.out.println();
        //     }
        // }
        
        // DisplayMatriks(f, 4, 4);
        
        // Pembuatan Matriks X
        i=0;
        j=0;
        int x=-1,y=-1;
        for (v=0; v<m; v++){
            if((v)%4==0){
                x=-1;
                if (v==0){ y=-1; }
                else { y++; }
            }
            else{x++;}
            
            for (w=0; w<m; w++){
                if((w)%4==0){
                    i=0;
                    if (w==0){ j=0; }
                    else{ j++; }
                }
                else{ i++; }
                X[v][w]=(Math.pow(y, j))*(Math.pow(x, i));
                // if(X[v][w]==0){
                    //     X[v][w]*=0;
                    // }
                    // System.out.printf("%.1f ",X[v][w]);
                    // System.out.printf("x: %d y: %d  i: %d j: %d",x,y,i,j);
                    // System.out.println();
                }
            // System.out.println();
        } 

        
        // DisplayMatriks(X, 16, 16);
        // f(x,y) = X a
        // a = X^-1 f(x,y)
        
        // Invers matriks X
        invers(X);
        // Mengalikan dengan matriks f dan memasukkan ke dalam matriks a
        for (i=0;i<4;i++){
            for (j=0; j<4; j++){ // baris y dan kolom x
                
                a[i][j] = 1;
                // System.out.println();
            }
        }
        
        
        
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

    public void DisplayMatriks(double[][] mat, int nRows, int  nCols) {
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
