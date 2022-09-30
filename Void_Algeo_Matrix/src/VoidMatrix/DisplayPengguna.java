package VoidMatrix;


public class DisplayPengguna extends Main{
    public static void display1(){
        
    }
    void display() {
        System.out.println("1. Sistem Persamaaan Linier"); //
        System.out.println("2. Determinan"); // done
        System.out.println("3. Matriks balikan"); // done
        System.out.println("4. Interpolasi Polinom"); // done
        System.out.println("5. Interpolasi Bicubic"); // nigel
        System.out.println("6. Regresi linier berganda"); // ghazi
        System.out.println("7. Keluar"); //done
    }
    
    void displayMenu1() {
        System.out.println("a. Metode eliminasi Gauss");  //done
        System.out.println("b. Metode eliminasi Gauss-Jordan"); //done
        System.out.println("c. Metode matriks balikan");  // belum
        System.out.println("d. Kaidah Cramer"); //done

    }

    void displayMenu2(){
        System.out.println("a. Metode ekspansi kofaktor"); // done
        System.out.println("b. Metode reduksi baris"); // done
    }
    void displayMenu3() {
        System.out.println("a. Metode Matriks Adjoin "); //done
        System.out.println("b. Metode OBE"); // done 
    }
}
