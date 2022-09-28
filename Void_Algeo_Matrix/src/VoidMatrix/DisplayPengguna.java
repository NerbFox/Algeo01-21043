package VoidMatrix;


public class DisplayPengguna extends Main{
    public static void display1(){
        
    }
    void display() {
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
    }
    
    void displayMenu1() {
        System.out.println("a. Metode eliminasi Gauss");
        System.out.println("b. Metode eliminasi Gauss-Jordan");
        System.out.println("c. Metode matriks balikan");
        System.out.println("d. Kaidah Cramer");

    }

    void displayMenu2(){
        System.out.println("a. Metode ekspansi kofaktor");
        System.out.println("b. Metode reduksi baris"); // nunggu gauss 
    }
}
