package VoidMatrix;

import java.util.Scanner;

import java.lang.Math;
import javax.sound.midi.Soundbank;
import javax.swing.GroupLayout;

// import javax.sound.midi.Soundbank;
// import javax.sound.sampled.SourceDataLine;

public class Main {


    // NANTI BUAT SATU KELAS BUAT isi, fungsi matriks is square dll.
    // SAMA baca txt atau apa tuh di spek
    public static void main(String[] args) {
        // ReadFile re = new ReadFile();
        // double f = (-1)*0;
        // double f = (Math.pow(0, 1))*(Math.pow(-1, 1));
        // if (f==0){
        //     System.out.printf("%f",f);
        // }
        
        int menu = 1;
        // char SubMenu ;
        Main obj = new Main();
        DisplayPengguna disp = new DisplayPengguna();
        Scanner sc = new Scanner(System.in);
        while (menu != 7) {
            System.out.println("");
            disp.display();
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
                    System.out.println("Sistem Persamaaan Linier");
                    disp.displayMenu1();
                    System.out.print("Input: ");
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c' && SubMenu != 'd' && SubMenu != 'e') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    while (SubMenu != 'e'){
                        System.out.println();
                        switch (SubMenu) {
                            case 'a' -> {
                                System.out.println("Menu 1a");
                                System.out.println("Metode eliminasi Gauss");
                                
                                    Matrix m = new Matrix(false, true, false);
                                    double[] hasil = new double[m.getnRows()];
                                    EliminasiGauss GE = new EliminasiGauss();
                                    GE.GaussElimination(m.getMat());
                                    m.DisplayMatriks();
                                    GE.SubstitusiMundur(m.getMat(), hasil);

                            }
                            case 'b' -> {
                                System.out.println("Menu 1b");
                                System.out.println("Metode Eliminasi Gauss-Jordan");
                                Matrix m = new Matrix(false, true, false);
                                double[] hasil = new double[m.getnRows()];
                                GaussJordan GE = new GaussJordan();
                                GE.GaussJordanElimination(m.getMat(), hasil);
                                // m.DisplayMatriks();

                            }
                            case 'c' -> {
                                System.out.println("Menu 1c");
                                System.out.println("Metode Matriks Balikan");
                                Matrix m = new Matrix(false, true, false);
                                m.inversSPL();
                            }
                            case 'd' -> {
                                System.out.println("Menu 1d");
                                System.out.println("Kaidah Cramer");
                                Cramer cm = new Cramer();
                                Matrix mat = new Matrix(false, true, false);
                                double[] hasil = new double[mat.getnRows()];
                                int x;
                                cm.CramerRule(mat.getMat(), hasil);
                                System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA:\n");
                                for (x = 0; x < mat.getnRows(); x++) {
                                    System.out.printf("%f ", hasil[x]);
                                }
                                System.out.printf("\n");
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Menu 2");
                    System.out.println("Determinan");
                    // ada pilihannya
                    disp.displayMenu2();
                    System.out.println("Input: ");
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    while (SubMenu != 'c'){
                        System.out.println();
                        
                        switch (SubMenu) {
                            case 'a' -> {
                                System.out.println("Menu 2a");
                                System.out.println("Metode ekspansi kofaktor");
                                
                                Matrix m = new Matrix(true, false, false); // determinan
                                double d = m.det(m.getMat());
                                // m.DisplayMatriks();
                                System.out.print("Determinan matriks: ");
                                System.out.println(d);
                            }
                            case 'b' -> {
                                System.out.println("Menu 2b");
                                System.out.println("Metode reduksi baris");
                                Matrix m = new Matrix(true, false, false);
                                // m.DisplayMatriks();
                                // System.out.println();
                                
                                GaussJordan gj = new GaussJordan();
                                double d;
                                d = gj.GaussJordanForDet(m.getMat());
                                // m.DisplayMatriks();
                                System.out.print("Determinan matriks: ");
                                System.out.println(d);
                            }
                        }
                        disp.displayMenu2();
                        System.out.println("Input: ");
                        SubMenu = sc.next().charAt(0);
                        while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                            System.out.println("Invalid Input, please input the correct option");
                            System.out.print("Input: ");
                            SubMenu = sc.next().charAt(0);
                        }
                        
                    }
                }
                case 3 -> {
                    System.out.println("Menu 3");
                    System.out.println("Matriks balikan");
                    // ada pilihannya
                    disp.displayMenu3();
                    System.out.println("Input: ");

                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    while (SubMenu != 'c'){
                        System.out.println();
                        switch (SubMenu) {
                            case 'a' -> {
                                System.out.println("Menu 3a");
                                System.out.println("Metode Matriks Adjoin ");
                                Matrix m = new Matrix(true, false, false);
                                m.invers(m.getMat());
                                m.DisplayMatriks();

                            }
                            case 'b' -> {
                                System.out.println("Menu 3b");
                                System.out.println("Metode OBE");
                                Matrix m = new Matrix(true, false, false);
                                InversOBE IO = new InversOBE(m.getMat());
                                m.DisplayMatriks();
                            }
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Menu 4");
                    System.out.println("Interpolasi Polinom");
                    PolynomialInterpolation p = new PolynomialInterpolation();
                }

                case 5 -> { // Interpolasi Bicubic
                    System.out.println("Menu 5");
                    System.out.println("Interpolasi Bicubic");
                    // Matrix m = new Matrix(true);

                    BicubicInterpolation b = new BicubicInterpolation();
                
                }
                case 6 -> {
                    System.out.println("Menu 6");
                    System.out.println("Regresi linier berganda");
                }
                case 7 -> {
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
