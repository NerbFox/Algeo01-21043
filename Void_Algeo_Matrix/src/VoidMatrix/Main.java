package VoidMatrix;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.lang.Math;
import java.net.SocketTimeoutException;
import java.security.InvalidAlgorithmParameterException;

import javax.sound.midi.Soundbank;
import javax.swing.GroupLayout;
import javax.swing.SpinnerModel;

public class Main {

    public static void main(String[] args) {
        // ReadFile re = new ReadFile();
        // double f = (-1)*0;
        // double f = (Math.pow(0, 1))*(Math.pow(-1, 1));
        // if (f==0){
        // System.out.printf("%f",f);
        // }

        // int menu = 0;
        char simpan;
        char menu = '9';
        boolean invalid = false;
        Main obj = new Main();
        DisplayPengguna disp = new DisplayPengguna();
        Scanner sc = new Scanner(System.in);
        while (menu != '7') {
            // menu = 0;
            System.out.println("");
            disp.display();
            System.out.print("Input menu: ");
            menu = sc.next().charAt(0);
            // invalid = false;
            // int ins = 999;
            // while (!invalid) {
            // try {
            // menu = sc.nextInt();
            // invalid = true;
            // } catch (Exception ex) {
            // System.out.println("Invalid input, please input a number");
            // }
            // menu = sc.nextInt();
            // }
            // while (invalid) {
            // disp.display();
            // System.out.print("\nInput menu: ");
            // menu = sc.nextInt();
            // }
            while (!(menu == '1' || menu == '2' || menu == '3' || menu == '4' || menu == '5' || menu == '6'
                    || menu == '7')) {
                System.out.println("\nInvalid Input, please input the correct number");
                disp.display();
                System.out.print("Input menu: ");
                menu = sc.next().charAt(0);
            }
            System.out.println();
            char SubMenu;
            int Masukan;
            boolean read;
            switch (menu) {
                case '1' -> {
                    System.out.println("Menu 1");
                    System.out.println("Sistem Persamaaan Linier");
                    disp.displayMenu1();
                    System.out.print("Input: ");
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != '1' && SubMenu != 'b' && SubMenu != 'c' && SubMenu != 'd' && SubMenu != 'e') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    while (SubMenu != 'e') {
                        System.out.println();
                        switch (SubMenu) {
                            case 'a' -> {
                                System.out.println("Menu 1a");
                                System.out.println("Metode eliminasi Gauss");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(false, true, read);
                                double[] hasil = new double[m.getnRows()];
                                EliminasiGauss GE = new EliminasiGauss();
                                GE.GaussElimination(m.getMat());
                                // m.DisplayMatriks();
                                // GE.SubstitusiMundur(m.getMat(), hasil);
                                System.out.println();

                            }
                            case 'b' -> {
                                System.out.println("Menu 1b");
                                System.out.println("Metode Eliminasi Gauss-Jordan");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(false, true, read);
                                System.out.println();
                                double[] hasil = new double[m.getnRows()];
                                GaussJordan GE = new GaussJordan();
                                GE.GaussJordanElimination(m.getMat(), hasil);
                                System.out.println();
                                // m.DisplayMatriks();

                            }
                            case 'c' -> {
                                System.out.println("Menu 1c");
                                System.out.println("Metode Matriks Balikan");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(false, true, read);
                                m.inversSPL();

                            }
                            case 'd' -> {
                                System.out.println("Menu 1d");
                                System.out.println("Kaidah Cramer");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Cramer cm = new Cramer();
                                Matrix mat = new Matrix(false, true, read);
                                double[] hasil = new double[mat.getnRows()];
                                double[][] hasilH = new double[mat.getnRows()][1];

                                int x;
                                cm.CramerRule(mat.getMat(), hasil);
                                System.out.printf("SOLUSI SISTEM PERSAMAAN LINIER ANDA:\n");
                                for (x = 0; x < mat.getnRows(); x++) {
                                    hasilH[x][0] = hasil[x];
                                    System.out.printf("%f ", hasil[x]);
                                }
                                System.out.printf("\n");

                                System.out.print("Apakah ingin disimpan (y/n) : ");
                                simpan = sc.next().charAt(0);
                                System.out.println();
                                if (simpan == 'y' || simpan == 'Y') {
                                    // m.fileKeluaranDet(d);
                                    mat.fileKeluaranSPL(hasilH);

                                } else {
                                    System.out.println("File tidak disimpan");
                                }

                            }
                        }
                        disp.displayMenu1();
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                        while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c' && SubMenu != 'd' && SubMenu != 'e') {
                            System.out.println("Invalid Input, please input the correct option");
                            System.out.print("Input: ");
                            SubMenu = sc.next().charAt(0);
                        }
                    }
                }
                case '2' -> {
                    System.out.println("Menu 2");
                    System.out.println("Determinan");

                    disp.displayMenu2();
                    System.out.print("Input: ");
                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    while (SubMenu != 'c') {
                        System.out.println();

                        switch (SubMenu) {
                            case 'a' -> {
                                System.out.println("Menu 2a");
                                System.out.println("Metode ekspansi kofaktor");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(true, false, read); // determinan
                                double d = m.det(m.getMat());
                                // m.DisplayMatriks();
                                System.out.print("\nDeterminan matriks: ");
                                System.out.println(d);
                                System.out.println();

                                System.out.print("Apakah ingin disimpan (y/n) : ");
                                simpan = sc.next().charAt(0);
                                System.out.println();
                                if (simpan == 'y' || simpan == 'Y') {
                                    m.fileKeluaranDet(d);
                                } else {
                                    System.out.println("File tidak disimpan");
                                }
                            }
                            case 'b' -> {
                                System.out.println("Menu 2b");
                                System.out.println("Metode reduksi baris");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(true, false, read);
                                // m.DisplayMatriks();
                                // System.out.println();

                                GaussJordan gj = new GaussJordan();
                                double d;
                                d = gj.GaussJordanForDet(m.getMat());
                                // m.DisplayMatriks();
                                // Void_algeo_matrix/test/determinan.txt
                                System.out.print("\nDeterminan matriks: ");
                                System.out.println(d);
                                System.out.println();

                                System.out.print("Apakah ingin disimpan (y/n) : ");
                                simpan = sc.next().charAt(0);
                                System.out.println();
                                if (simpan == 'y' || simpan == 'Y') {
                                    m.fileKeluaranDet(d);
                                } else {
                                    System.out.println("File tidak disimpan");
                                }
                            }
                        }
                        disp.displayMenu2();
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                        while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                            System.out.println("Invalid Input, please input the correct option");
                            System.out.print("Input: ");
                            SubMenu = sc.next().charAt(0);
                        }

                    }
                }
                case '3' -> {
                    System.out.println("Menu 3");
                    System.out.println("Matriks balikan");
                    // ada pilihannya
                    disp.displayMenu3();
                    System.out.print("Input: ");

                    SubMenu = sc.next().charAt(0);
                    while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                        System.out.println("Invalid Input, please input the correct option");
                        System.out.print("Input: ");
                        SubMenu = sc.next().charAt(0);
                    }
                    while (SubMenu != 'c') {
                        System.out.println();
                        switch (SubMenu) {
                            case 'a' -> {
                                System.out.println("Menu 3a");
                                System.out.println("Metode Matriks Adjoin ");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(true, false, read);
                                m.invers(m.getMat());
                                // m.DisplayMatriks();

                                System.out.print("Apakah ingin disimpan (y/n) : ");
                                simpan = sc.next().charAt(0);
                                System.out.println();
                                if (simpan == 'y' || simpan == 'Y') {
                                    m.fileKeluaranMat(m.getMat());

                                } else {
                                    System.out.println("File tidak disimpan");
                                }

                            }
                            case 'b' -> {
                                System.out.println("Menu 3b");
                                System.out.println("Metode OBE");

                                disp.displayPilihan();
                                Masukan = sc.nextInt();
                                while (Masukan != 1 && Masukan != 2) {
                                    System.out.println("Masukan salah");
                                    disp.displayPilihan();
                                    Masukan = sc.nextInt();
                                }
                                read = false;
                                if (Masukan == 2) {
                                    read = true;
                                }

                                Matrix m = new Matrix(true, false, read);
                                double d = m.det(m.getMat());
                                InversOBE IO = new InversOBE(m.getMat());
                                if (d != 0) {
                                    m.DisplayMatriks();
                                    System.out.print("Apakah ingin disimpan (y/n) : ");
                                    simpan = sc.next().charAt(0);
                                    System.out.println();
                                    if (simpan == 'y' || simpan == 'Y') {
                                        m.fileKeluaranMat(m.getMat());

                                    } else {
                                        System.out.println("File tidak disimpan");
                                    }
                                } else {
                                    System.out.println("\nMatriks tidak memilki balikan\n");
                                }
                            }
                        }
                        disp.displayMenu3();
                        System.out.print("Input: ");

                        SubMenu = sc.next().charAt(0);
                        while (SubMenu != 'a' && SubMenu != 'b' && SubMenu != 'c') {
                            System.out.println("Invalid Input, please input the correct option");
                            System.out.print("Input: ");
                            SubMenu = sc.next().charAt(0);
                        }
                    }
                }
                case '4' -> {
                    System.out.println("Menu 4");
                    System.out.println("Interpolasi Polinom");

                    disp.displayPilihan();
                    Masukan = sc.nextInt();
                    while (Masukan != 1 && Masukan != 2) {
                        System.out.println("Masukan salah");
                        disp.displayPilihan();
                        Masukan = sc.nextInt();
                    }
                    read = false;
                    if (Masukan == 2) {
                        read = true;
                    }

                    PolynomialInterpolation p = new PolynomialInterpolation(read);

                }
                case '5' -> { // Interpolasi Bicubic
                    System.out.println("Menu 5");
                    System.out.println("Interpolasi Bicubic");
                    // Matrix m = new Matrix(true);

                    disp.displayPilihan();
                    Masukan = sc.nextInt();
                    while (Masukan != 1 && Masukan != 2) {
                        System.out.println("Masukan salah");
                        disp.displayPilihan();
                        Masukan = sc.nextInt();
                    }
                    read = false;
                    if (Masukan == 2) {
                        read = true;
                    }

                    BicubicInterpolation b = new BicubicInterpolation(read);

                }
                case '6' -> {
                    System.out.println("Menu 6");
                    System.out.println("Regresi linier berganda");

                    ArrayList<Object> tuple = new ArrayList<>();
                    tuple = MultipleLinearRegression.opsiInputReg();
                    MultipleLinearRegression mat = (MultipleLinearRegression) tuple.get(0);
                    double[] toEst = (double[]) tuple.get(1);
                    tuple = mat.Regresi(toEst);
                    MultipleLinearRegression.HasilReg(tuple, "Polinom");
                }
                case '7' -> {
                    System.out.println("Terima Kasih");
                    System.out.println();

                }
            }
        }
    }
}
