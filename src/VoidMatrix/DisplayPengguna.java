package VoidMatrix;

<<<<<<< Updated upstream:src/VoidMatrix/DisplayPengguna.java
public class DisplayPengguna extends Main{
=======

public class DisplayPengguna {
>>>>>>> Stashed changes:Void_Algeo_Matrix/src/VoidMatrix/DisplayPengguna.java
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
        System.out.println("e. Main Menu"); //back to main menu
    }
    
    void displayMenu2(){
        System.out.println("a. Metode ekspansi kofaktor"); // done
        System.out.println("b. Metode reduksi baris"); // done
        System.out.println("c. Main Menu"); //back to main menu
    }

    void displayMenu3() {
        System.out.println("a. Metode Matriks Adjoin "); //done
        System.out.println("b. Metode OBE"); // done 
        System.out.println("c. Main Menu"); //back to main menu
    }

    void displayPilihan(){
        System.out.println("1. Masukan Keyboard");
        System.out.println("2. Masukan File ");
        System.out.print("Input : ");
    }
}