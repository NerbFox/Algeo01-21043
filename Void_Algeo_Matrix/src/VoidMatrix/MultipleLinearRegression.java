package VoidMatrix;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;
import java.io.PrintWriter;

public class MultipleLinearRegression {
	public ArrayList<Object> Regresi(double [] toEst) {
		int i,j;
        double val;

        ArrayList<Object> ret = new ArrayList<>();

        Matrix mat = new Matrix (false, false, false);
        double[][] spl = new double [mat.getnRows()][mat.getnCols() + 1];

		for (i = 0; i < mat.getnRows(); i++) {
			for (j = 0; j < mat.getnCols(); j++) {
			// Pembagian 4 Kondisi ini bisa dilihat di rumus
				if ( i==0 && j==0 ) {
					spl[i][j] = mat.getnRows();
				}
				else if ( i==0 ) {
                    val = sigma1val(j, spl);
					spl[i][j] = val;
				}
				else if ( j==0 ) {
                    val = sigma1val(i, spl);
					spl[i][j] = val;
				}
				else {
                    val = sigma2val(i, j, spl);
					spl[i][j] = val;
				}
			}
		}

        double[] beta = new double [mat.getnRows()];
        Cramer cm = new Cramer();
        cm.CramerRule (spl, beta);

		String formula = RegresiSolution(beta);
		

		double taksiran = 0.0;
		for(i = 0; i < beta.length; i++) {
			if ( i == 0 ) {
				taksiran += beta[i];
			}
			else {
				taksiran += beta[i] * toEst[i-1];
			}
		}
		ret.add(formula);
		ret.add(taksiran);
		return ret;
	}

    private double sigma1val (int k, double[][] input) {
            int i;
            double sum = 0.0;
            for (i = 0; i < input.length; i++) {
                sum += input[i][k-1];
            }
            return sum;
        }
    
    private double sigma2val (int j, int k, double[][] input) {
        int i;
        double sum = 0.0;
        for ( i = 0; i < input.length; i++) {
            sum += input[i][j-1] * input[i][k-1];
        }		
        return sum;
    }

	public String RegresiSolution(double[] ans) {
		int i;
		String solution = "Y = ";
		for(i = 0; i < ans.length; i++) {
			if( i==0 ) {
				solution += ans[i]+" ";
			}
			else if ( 0<i && i <ans.length-1) {
				solution += "+ "+ans[i]+" X"+i+" ";
			}
			else {
				solution += "+ "+ans[i]+" X"+i;
			}
		}
		return solution;
	}

    public static ArrayList<Object> opsiInputReg() {
		Scanner sc = new Scanner(System.in);
		double [] toEst;
		ArrayList<Object> ret = new ArrayList<>();
		boolean legal = true;
		do {
			System.out.print("Cara input Titik Uji (Regresi Linear Berganda):\n"
					+ "1. Input Keyboard\n"
					+ "2. Input from File\n"
					+ "Masukkan cara Input (1/2): ");
			try {
				legal = true;
				int opsi = sc.nextInt();
				switch(opsi) {
				case 1:
					int npeubah,msampel;
					System.out.print("Masukkan banyak peubah: ");
					npeubah = sc.nextInt();
					System.out.print("Masukkan banyak sampel: ");
					msampel = sc.nextInt();
                    double[][] res = new double [msampel][npeubah+1];
					System.out.println("Masukkan nilai-nilai sampel: ");
					for (int i=0; i<msampel; i++){
                        for (int j=0 ; j<npeubah+1; j++){
                            res[i][j] = sc.nextDouble();
                        }
                    }
					toEst = new double[npeubah+1];
					System.out.println("Masukkan data taksiran: ");
					for(int i = 0; i < npeubah; i++) {
						toEst[i] = sc.nextDouble();
					}
					ret.add(res);
					ret.add(toEst);
					break;
				case 2:
					System.out.println("\"Masukkan nama file/path file (cth: Mat.txt ATAU [folder]/Mat.txt): ");
					ret = MultipleLinearRegression.readRegFile();
					break;
				default:
					legal = false;
					System.out.println("Terjadi kesalahan input");
				}
			} catch (Exception ex) {
				System.out.println(ex);
				legal = false;
				sc.nextLine();
			}
		} while (legal == false);
		return ret;
	}

	public static ArrayList<Object> readRegFile() {
		Scanner sc = new Scanner(System.in);
		File fIn = new File(sc.nextLine());
		Scanner SIn = null;
		try {
			SIn = new Scanner(fIn);
		} catch (Exception ex) {
			System.out.println("File tidak ditemui");
		}
		ArrayList<Object> ret = new ArrayList<>();
		int rows = 0;
		int cols = 0;
		ArrayList<ArrayList<Double>> Isi = new ArrayList<ArrayList<Double>>();
		String baris;
		while (SIn.hasNextLine()) {
			baris = SIn.nextLine();
			Scanner SBar = new Scanner(baris);
			cols = 0;
			Isi.add(new ArrayList<Double>());
			while(SBar.hasNextDouble()) {
				Isi.get(rows).add(cols,SBar.nextDouble());
				cols++;
			}
			rows++;
		}
		double[] toEst = new double[Isi.get(rows-1).size()];
		for(int i = 0; i < toEst.length; i++) {
			toEst[i] = Isi.get(rows-1).get(i);
		}
		Isi.remove(rows-1);
        int nRows = Isi.size();
        int nCols = Isi. get(0).size();
        double[][] res = new double [nRows][nCols];
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                res[i][j] = Isi.get(i).get(j);
            }
        }
		ret.add(res);
		ret.add(toEst);
		return ret;
	}

    public static void promptOutReg(ArrayList<Object> hsl, String typ) {
		System.out.printf("Persamaan %s: %s\n",typ, hsl.get(0));
		System.out.printf("Taksiran f(x): %f\n",hsl.get(1));
		Scanner sc = new Scanner(System.in);
		System.out.println("Apakah ingin menyimpan hasil ke File? (y/n)");
		String pil = sc.nextLine();
		if (pil.toLowerCase().equals("y")) outRegFile(hsl);
		else if (pil.toLowerCase().equals("n")) System.out.println("Tidak menyimpan ke file");
		else System.out.println("Input tidak dikenali, asumsi tidak menyimpan hasil");
		Matrix.refreshLayar();
	}

    public static void outRegFile(ArrayList<Object> sols) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Masukkan nama file (contoh: file.txt):");
		String fName = sc.nextLine();
			try {
				PrintWriter output = new PrintWriter("outFiles/"+fName);
				output.println(sols.get(0));
				output.println(sols.get(1));
				} catch (IOException ex) {
					System.out.printf("ERROR: %s", ex);
				}
	}
}