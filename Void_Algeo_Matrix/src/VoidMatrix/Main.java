package VoidMatrix;

import java.util.Scanner;

public class Main {
    void display() {
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
    }

    // NANTI BUAT SATU KELAS BUAT isi, fungsi matriks is square dll.
    // SAMA baca txt atau apa tuh di spek
    public static void main(String[] args) {
        int menu = 1;
        Main obj = new Main();
        Scanner sc = new Scanner(System.in);
        while (menu != 7) {
            System.out.println("");
            obj.display();
            menu = sc.nextInt();
            while (menu > 7 || menu < 1) {
                System.out.println("Invalid Input, try the correct number");
                menu = sc.nextInt();
            }
            switch (menu) {
                case 1 -> 
                { 
                    System.out.println("Menu 1");
                }
                case 2 ->
                {
                    System.out.println("Menu 2");
                    Matrix m = new Matrix(true); // determinan
                    double d = m.det(m.getMat());
                    m.DisplayMatriksDet();
                    System.out.println(d);
                }
                case 3 -> 
                {
                    System.out.println("Menu 3");
                }
                case 4 -> 
                {
                    System.out.println("Menu 4");
                }
                case 5 -> 
                {
                    System.out.println("Menu 5");
                }
                case 6 -> {
                    System.out.println("Menu 6");
                }
                case 7 -> System.out.println("Terima Kasih");
            }

            

            // menu=sc.nextInt();
        }

        // -Main
        // -MAtriks , tampil matriks
        // -determinan extend matriks

    }

}
