package coreFunctions;

import java.util.*;

//this doesn't need to be an object
public class AminoTable {
	public static Hashtable<Character, Double> makeAminoTable() {
		Hashtable<Character, Double> aminoTable = new Hashtable <Character, Double>();
		aminoTable.put('A', 71.04);
		aminoTable.put('C', 160.06);
		aminoTable.put('R', 156.10);
		aminoTable.put('N', 114.04);
		aminoTable.put('D', 115.03);
		aminoTable.put('E', 129.04);
		aminoTable.put('Q', 128.06);
		aminoTable.put('G', 57.02);
		aminoTable.put('H', 137.06);
		aminoTable.put('I', 113.08);
		aminoTable.put('L', 113.08);
		aminoTable.put('K', 128.09);
		aminoTable.put('M', 131.0);
		aminoTable.put('F', 147.07);
		aminoTable.put('P', 97.05);
		aminoTable.put('S', 87.03);
		aminoTable.put('T', 101.05);
		aminoTable.put('W', 186.08);
		aminoTable.put('Y', 163.06);
		aminoTable.put('V', 99.07);
		return aminoTable;
	}
}
