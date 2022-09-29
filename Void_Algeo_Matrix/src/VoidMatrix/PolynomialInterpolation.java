package VoidMatrix;

import java.util.Scanner;

public class PolynomialInterpolation{
       public static void main(String[] args){
           Scanner myScanner = new Scanner(System.in);
           
           //Deklarasi Variabel
           int n; //Total banyaknya titik
           int count, count2; //Variabel loop, untuk menghitung count
           
           float [] arrayx = new float[100]; //Array limit 99
           float [] arrayy = new float[100]; //Array limit 99
           float x = 0; 
           float y = 0;
           float numerator;
           float denominator;
   
        // Input User
        System.out.print("Masukkan total banyaknya titik: "); 
        n = myScanner.nextInt(); //Menyimpan input di n
    
           //Input nilai x dari user
            System.out.println("Nilai x");
            for(count = 0; count<n; count++) //Memulai loop untuk nilai x
            {
                //Input nilai xi
                System.out.print("Masukkan nilai x" + count + ": ");
                //Menyimpan nilai di Array, arrayx
                arrayx[count] = myScanner.nextFloat(); 
            }

            //Input nilai y dari user
            System.out.println("Nilai y");
            for(count = 0; count<n; count++) // loop for Y
            {
                //Input nilai yi
                System.out.print("Masukkan nilai y" + count + ": ");
                //Menyimpan nilai di Array, arrayy
                arrayy[count] = myScanner.nextFloat();
            }
            //Meminta user untuk memasukkan nilai x apa pun untuk mendapatkan nilai y yang sesuai
            System.out.print("Masukkan titik x yang ingin dicari nilai y nya: ");
            x = myScanner.nextFloat();  //Menyimpan nilai di x
            
            //Loop pertama untuk menghitung polinomial
            for(count = 0; count<n; count++)
            {
                //Inisialisasi variabel
                numerator = 1; denominator = 1;
                
                //Loop kedua untuk menghitung polinomial
                for(count2 = 0; count2<n; count2++)
                {
                    if (count2 != count)
                    {
                      numerator = numerator * (x - arrayx[count2]);
                      denominator = denominator * (arrayx[count] - arrayx[count2]);
                    }  
                }
                y = y + (numerator/denominator) * arrayy[count];
            }
            System.out.println("Saat x = " + x + "," + " y = " +  y);
    }
}