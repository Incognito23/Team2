package Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.itextpdf.text.DocumentException;

public class DataToArray {

	private static char[][] c;
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<char[][]> chars = new ArrayList<char[][]>();
	private static ArrayList<Integer> partitionLength = new ArrayList<Integer>();
	public static String textFile = "MoonlightSonata.txt";
	public static String Title = "NO TITLE";
	public static String SubTitle = "NO SUBTITLE";
	public static float Spacing = 8.0f;

	private static ArrayList<char[][]> newchars = new ArrayList<char[][]>();

	private static int col;

	public static ArrayList<char[][]> textToArray(String source)
			throws DocumentException, IOException {
		chars.clear();
		lines.clear();
		Title = " ";
		SubTitle = " ";
		BufferedReader input = null;
		input = new BufferedReader(new FileReader(source));
		String line = "";
		while (null != (line = input.readLine())) {
			if (line.contains("subtitle") || line.contains("SUBTITLE")) {
				SubTitle = line.substring(line.indexOf('=') + 1, line.length());
			} else if (line.contains("title") || line.contains("TITLE")) {
				Title = line.substring(line.indexOf('=') + 1, line.length());
			}
			if (line.contains("spacing") || line.contains("SPACING")) {
				Spacing = Float.parseFloat(line.substring(line.indexOf('=') + 1, line.length()));
			}
			if (line.trim().length() == 0) {
				continue;
			}
			if (line.charAt(0) == '|') {
				lines.add(line);
			}
		}
		for (int z = 0; z < lines.size(); z = z + 6) {
			col = lines.get(z).length();
			c = new char[6][col];
			int temp = z;
			for (int i = 0; i < 6; i++, temp++) {

				// System.out.println(temp);
				for (int j = 0; j < lines.get(temp).length(); j++) {
					c[i][j] = lines.get(temp).charAt(j);
					// System.out.println("i = " + i + " temp = " + temp +
					// " j = " + j);
					// System.out.println(j);
				}
			}
			chars.add(c);
		}
		
		input.close();
		
		for (int t = 0; t < chars.size(); t++) //Check every element in the cars and split them up as needed
		{
			boolean alreadyBottomed = false;
			char[][] d = new char[6][chars.get(t)[0].length]; //Make it as long as the old element, and we'll trim it later
			int n = 0;
			for (int v = 0; v < chars.get(t).length; v++) //Read every column
			{
				for (int w = 0; w < 6; w++) //Then read every row
				{
					char currentChar = chars.get(t)[w][v];
					d[w][n] = currentChar;
					System.out.print(currentChar);
					if (w == 5 && currentChar == '|' && !alreadyBottomed)
					{
						n = 0; //The new element should start at position 0, obviously
						v--; //The last column should be printed in twice, so back up one and do this column again
						newchars.add(d); //Add the new element to the list
						d = new char[6][chars.get(t)[0].length]; //Reset the array we are writing so it is blank
						alreadyBottomed = true;
					}
					if (w == 5 && currentChar != '|' && alreadyBottomed)
					{
						alreadyBottomed = false;
					}
				}
				System.out.println();
			}
			
		}
		// Test to see printed lines
		for (int a = 0; a < newchars.get(0).length; a++)
		{
			for (int b = 0; b < 6; b++)
			{
				System.out.print(newchars.get(0)[b][a]);
			}
			System.out.println();
		}
				
		
		return newchars; 
	}

	public static void LengthOfPartition() {
		int length;
		String temp;
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			StringTokenizer StrTkn = new StringTokenizer(line, "|");
			// System.out.println("NUMBER OF TOKENS = " + StrTkn.countTokens());
			while (StrTkn.hasMoreTokens()) {
				temp = StrTkn.nextToken();
				length = temp.length();
				partitionLength.add(length);
			}
		}
	}

	// Gets the left bars and everything else not including the right bars
	public static String DanielsPartition(String line) {
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '|') {
				if (i == 0 || i == 1) {
				} else {
					line = line.substring(0, i);
				}
			}
		}
		// System.out.println(line);
		return line;
	}

	public static ArrayList<String> DanielsPartition2(ArrayList<String> data) {
		ArrayList<String> partition = data;
		for (int i = 0; i < partition.size(); i++) {
			String line = DanielsPartition(partition.get(i));
			partition.set(i, line);
		}
		for (int j = 0; j < partition.size(); j++) {
			// System.out.println(partition.get(j));
		}
		return partition;
	}

	public static int getMaxColumnAmount() {
		return DataToArray.col;
	}

	public static int getTotalRowAmount() {
		return DataToArray.lines.size();
	}

	public static int getBarAmount() {
		return DataToArray.lines.size() / 6;
	}

	public static String getTitle() {
		return Title;
	}

	public static String getsubTitle() {
		return SubTitle;
	}

	public static float getSpacing() {
		return Spacing;
	}

	public static void main(String[] args) throws DocumentException,
			IOException {
		// textToArray();
		DataToArray.textToArray(DataToArray.textFile);
		LengthOfPartition();
		DanielsPartition2(lines);
	}
}
