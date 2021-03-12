package s21_Meerkat_Project;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputOutputMethods {
	
	public BufferedReader openRead() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog foBox = new FileDialog(f, "Pick location for reading your file", FileDialog.LOAD);
		System.out.println(
				"The dialog box will appear behind Eclipse.  " + "\n   Choose where you would like to read from.");
		foBox.setVisible(true);
		// get the absolute path to the file
		String foName = foBox.getFile();
		String dirPath = foBox.getDirectory();

		// create a file instance for the absolute path
		File inFile = new File(dirPath + foName);
		if (!inFile.exists()) {
			System.out.println("That file does not exist");
			System.exit(0);
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(inFile));
		} catch (IOException e) {
			System.out.println("You threw an exception. ");
		}
		return in;

	}

	public PrintWriter openWrite() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog foBox = new FileDialog(f, "Pick location for writing your file", FileDialog.SAVE);
		System.out.println("The dialog box will appear behind Eclipse.  "
				+ "\n   Choose where you would like to write your data.");
		foBox.setVisible(true);
		// get the absolute path to the file
		String foName = foBox.getFile();
		String dirPath = foBox.getDirectory();

		// create a file instance for the absolute path
		File outFile = new File(dirPath + foName);
		PrintWriter out = null;

		try {
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			System.out.println("You threw an exception");
		}
		return out;
	}

	public ArrayList<Puppies> loadData() {
		ArrayList<Puppies> pupList = new ArrayList<Puppies>();
		Scanner scan = new Scanner(System.in);

		BufferedReader bf = null;
		try {
			// Open the file.
			bf = openRead();

			// read in the first line
			String line = "";
			try {
				line = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String delim = ",";

			// while there is more data in the file, process it
			while (line != null) { // more lines
				StringTokenizer st = new StringTokenizer(line, delim);
				while (st.hasMoreTokens()) { // more items on each line

					// this is what you change based on the application
					String name = st.nextToken().toString();
					String breed = st.nextToken().toString();
					String sex = st.nextToken().toString();
					boolean pedigree = Boolean.parseBoolean(st.nextToken().trim());
					double price = Double.parseDouble(st.nextToken().trim());
					boolean hypo = Boolean.parseBoolean(st.nextToken().trim());
					boolean sold = Boolean.parseBoolean(st.nextToken().trim());
					Puppies pup = new Puppies(name, breed, sex, pedigree, price, hypo, sold);
					pupList.add(pup);
				}
				// read in the next line
				try {
					line = bf.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // end of reading in the data.

		}
		// catch any other type of exception
		catch (Exception e) {
			System.out.println("Other weird things happened");
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (Exception e) {
			}
		}
		return pupList;

	}

	public void outputData(ArrayList<Puppies> puppy) {
		PrintWriter out = null;
		try {
			out = openWrite();
			for (int i = 0; i < puppy.size(); i++) {
				out.println(puppy.get(i).toStringF());
			}
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
}
