package VoidMatrix;

import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

// import javax.sound.midi.Soundbank;
// import javax.sound.sampled.SourceDataLine;

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

    void displayMenu1() {
        System.out.println("a. Metode eliminasi Gauss");
        System.out.println("b. Metode eliminasi Gauss-Jordan");
        System.out.println("c. Metode matriks balikan");
        System.out.println("d. Kaidah Cramer");

    }

    void displayMenu2(){
        System.out.println("a. Matriks Kofaktor");
        System.out.println("b. ");
    }

    void displayMenu3(){
        System.out.println("a. Menggunakan Matriks Adjoin ");
        System.out.println("b. Menggunakan ");
    }

    // NANTI BUAT SATU KELAS BUAT isi, fungsi matriks is square dll.
    // SAMA baca txt atau apa tuh di spek
    public static void main(String[] args) {
        int menu = 1;
        // char SubMenu ;
        Main obj = new Main();
        Scanner sc = new Scanner(System.in);
        while (menu != 7) {
            System.out.println("");
            obj.display();
            System.out.print("Input menu: ");
            menu = sc.nextInt();
            while (menu > 7 || menu < 1) {
                System.out.println("Invalid Input, please input the correct number");
                menu = sc.nextInt();
            }
            System.out.println();
            char SubMenu;
            switch (menu) {
                case 1 -> {
                    System.out.println("Menu 1");
                    obj.displayMenu1();
                    System.out.print("Input: ");
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c' && SubMenu != 'd') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    System.out.println();
                    switch (SubMenu) {
                        case 'a' -> {
                            System.out.println("Menu 1a");
                        }
                        case 'b' -> {
                            System.out.println("Menu 1b");
                        }
                        case 'c' -> {
                            System.out.println("Menu 1c");
                        }
                        case 'd' -> {
                            System.out.println("Menu 1d");
                        }
                    }
                    
                }
                case 2 -> {
                    System.out.println("Menu 2");
                    // ada pilihannya 
                    obj.displayMenu2();
                    System.out.println("Input: ");
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' ) {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    System.out.println();
                    switch (SubMenu) {
                        case 'a' -> {
                            System.out.println("Menu 2a");

                            Matrix m = new Matrix(true); // determinan
                            double d = m.det(m.getMat());
                            m.DisplayMatriksDet();
                            System.out.print("Determinan matriks: ");
                            System.out.println(d);
                        }
                        case 'b' -> {
                            System.out.println("Menu 2b");
                        }

                    }
                }
                case 3 -> {
                    System.out.println("Menu 3");
                    // ada pilihannya 
                    obj.displayMenu3();
                    System.out.println("Input: ");
                    
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' ) {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    System.out.println();
                    switch (SubMenu) {
                        case 'a' -> {
                            System.out.println("Menu 3a");
                        }
                        case 'b' -> {
                            System.out.println("Menu 3b");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Menu 4");
                }
                case 5 -> {
                    System.out.println("Menu 5");
                }
                case 6 -> {
                    System.out.println("Menu 6");
                }
                case 7 -> 
                {
                    System.out.println("Terima Kasih");
                    System.out.println();
            
                }
            }
            // menu=sc.nextInt();
        }

        // -Main
        // -MAtriks , tampil matriks
        // -determinan extend matriks

    }

}
