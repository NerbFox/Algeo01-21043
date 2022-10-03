package VoidMatrix;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;
import java.io.PrintWriter;

public class MultipleLinearRegression extends Main {
	public ArrayList<Object> Regresi(boolean read) {
		Scanner sc = new Scanner(System.in);
		double[] toEst;
		ArrayList<Object> ret = new ArrayList<>();
		boolean legal = true;
		int npeubah, msampel;
		double[][] res;

		if (!read) {
			// int opsi = sc.nextInt();
			System.out.print("Masukkan banyak peubah: ");
			npeubah = sc.nextInt();
			System.out.print("Masukkan banyak sampel: ");
			msampel = sc.nextInt();
			System.out.println("Masukkan nilai-nilai sampel: ");
			res = new double[msampel][npeubah + 1];
			for (int i = 0; i < msampel; i++) {
				for (int j = 0; j < npeubah + 1; j++) {
					res[i][j] = sc.nextDouble();
				}
			}
			toEst = new double[npeubah + 1];
			System.out.printf("  f %d", toEst.length);
			System.out.println("Masukkan data taksiran: ");
			for (int i = 0; i < npeubah; i++) {
				toEst[i] = sc.nextDouble();

			}
		} else {
			System.out.println("\nKetik nama alamat file yang diinginkan (contoh test/A.txt) ");
			File file = new File(sc.nextLine());
			Scanner Inside = null;
			try {
				Inside = new Scanner(file);
			} catch (Exception ex) {
				System.out.println("File tidak ditemukan");
			}
			while (Inside == null) {
				System.out.println("\nKetik nama alamat file yang diinginkan (contoh test/A.txt) ");
				file = new File(sc.nextLine());
				try {
					Inside = new Scanner(file);
				} catch (Exception ex) {
					System.out.println("File tidak ditemukan");
				}
			}
			int rows = 0;
			int cols = 0;
			ArrayList<ArrayList<Double>> Isi = new ArrayList<ArrayList<Double>>();
			String baris;
			while (Inside.hasNextLine()) {
				baris = Inside.nextLine();
				Scanner bar = new Scanner(baris);
				cols = 0;
				Isi.add(new ArrayList<Double>());
				while (bar.hasNextDouble()) {
					Isi.get(rows).add(cols, bar.nextDouble());
					cols++;
				}
				rows++;
			}
			int nRows = Isi.size() - 1;
			int nCols = Isi.get(0).size();
			toEst = new double[nCols];
			for (int i = 0; i < nCols - 1; i++) {
				toEst[i] = Isi.get(nRows).get(i);
				System.out.printf("%f ", toEst[i]);
				System.out.println();
			}
			// Isi.remove(rows - 1);
			// nRows-=1;
			// nCols;
			res = new double[nRows][nCols];
			for (int i = 0; i < nRows; i++) {
				for (int j = 0; j < nCols; j++) {
					res[i][j] = Isi.get(i).get(j);
					System.out.printf("%f ", res[i][j]);
				}
				System.out.println();
			}
			System.out.println("jjjjj");
			System.out.printf("%d %d     res %d  %d  toest %d  %d", nRows, nCols, res.length, res[0].length,
					toEst.length, 1);
			System.out.println();

		}

		int i, j;
		double val;

		for (i = 0; i < res.length; i++) {
			for (j = 0; j < res[0].length; j++) {
				// Pembagian 4 Kondisi ini bisa dilihat di rumus
				if (i == 0 && j == 0) {
					res[i][j] = res.length;
				} else if (i == 0) {
					val = sigma1val(j, res);
					res[i][j] = val;
				} else if (j == 0) {
					val = sigma1val(i, res);
					res[i][j] = val;
				} else {
					val = sigma2val(i, j, res);
					res[i][j] = val;
				}
			}
		}

		double[] beta = new double[res.length];
		Cramer cm = new Cramer();
		cm.CramerRule(res, beta);

		String formula = RegresiSolution(beta);

		double taksiran = 0.0;
		for (i = 0; i < beta.length; i++) {
			if (i == 0) {
				taksiran += beta[i];
			} else {
				taksiran += beta[i] * toEst[i - 1];
			}
		}
		ret.add(formula);
		ret.add(taksiran);
		return ret;
	}

	private double sigma1val(int k, double[][] input) {
		int i;
		double sum = 0.0;
		for (i = 0; i < input.length; i++) {
			sum += input[i][k - 1];
		}
		return sum;
	}

	private double sigma2val(int j, int k, double[][] input) {
		int i;
		double sum = 0.0;
		for (i = 0; i < input.length; i++) {
			sum += input[i][j - 1] * input[i][k - 1];
		}
		return sum;
	}

	public String RegresiSolution(double[] ans) {
		int i;
		String solution = "Y = ";
		for (i = 0; i < ans.length; i++) {
			if (i == 0) {
				solution += ans[i] + " ";
			} else if (0 < i && i < ans.length - 1) {
				solution += "+ " + ans[i] + " X" + i + " ";
			} else {
				solution += "+ " + ans[i] + " X" + i;
			}
		}
		return solution;
	}

	public static void HasilReg(ArrayList<Object> hsl, String typ) {
		System.out.printf("Persamaan %s: %s\n", typ, hsl.get(0));
		System.out.printf("Taksiran f(x): %f\n", hsl.get(1));
		Scanner sc = new Scanner(System.in);
		System.out.print("\nApakah ingin disimpan (y/n) : ");
		char simpan;
		simpan = sc.next().charAt(0);
		if (simpan == 'y' || simpan == 'Y') {
			fileKeluaranReg(hsl);
		} else {
			System.out.println("File tidak disimpan");
		}
	}

	public static void fileKeluaranReg(ArrayList<Object> hasil) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Masukan nama file yang akan disimpan (contoh Det.txt): ");
		String namaFile = sc.nextLine();
		try {
			PrintWriter output = new PrintWriter("FileKeluaran/" + namaFile);
			output.println(hasil.get(0));
			output.println(hasil.get(1));
		} catch (IOException ex) {
			System.out.printf("error: %s\n\n", ex);
		}
	}
}