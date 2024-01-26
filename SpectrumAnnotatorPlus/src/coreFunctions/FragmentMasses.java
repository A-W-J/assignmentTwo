package coreFunctions;

import java.util.Hashtable;
import java.io.IOException;
import java.lang.Math;

public class FragmentMasses {
	//let's add some more information here
	final double[] bIons;
	final double[] yIons;
	//for this assignment, we need to consider a ions and 2H+ ions
	final double[] aIons;
	final double[] bIonsPlus;
	final double[] yIonsPlus;
	
	public FragmentMasses(double[] bIons, double[] yIons, double[] aIons, double[] bIonsPlus, double[] yIonsPlus) {
		super();
		this.bIons = bIons;
		this.yIons = yIons;
		this.aIons = aIons;
		this.bIonsPlus = bIonsPlus;
		this.yIonsPlus = yIonsPlus;
	}
	
	public double[] getbIons() {
		return bIons;
	}

	public double[] getyIons() {
		return yIons;
	}
	
	public double[] getaIons() {
		return aIons;
	}
	
	public double[] getbIonsPlus() {
		return bIonsPlus;
	}
	
	public double[] getyIonsPlus() {
		return yIonsPlus;
	}

	public static String[] buildFragments(String sequence){
		String[] fragmentList = new String[sequence.length()];
		int counter = 0;
		for (int i = 1; i <= sequence.length(); i++) {
			String fragment = sequence.substring(0, i);
			fragmentList[counter] = fragment;
			counter = counter + 1;
		}
		return fragmentList;
	}
	
	public static Long getFragMass(String frag, boolean flag){
		//loading in the table of amino acids
		//AminoTable aminoTable = AminoTable.makeAminoLookup();
		//HashMaps are normally faster than Hashtables
		Hashtable<Character, Double> aminoTable = AminoTable.makeAminoTable();
		//we can loop over the string
		//instead of summing a list, we can just add each residue mass
		double fragMass = 0;
		for(char residue:frag.toCharArray()) {
			double residueMass = aminoTable.get(residue);
			fragMass = fragMass + residueMass;
		}
		Long roundedMass = Math.round(fragMass);
		if(flag == true) {
			roundedMass += 19;
			return roundedMass;
		}
		roundedMass += 1;
		return roundedMass;
	}
	
	public static double[] buildMassList(String sequence, boolean flag) {
		//this will be our output array
		double[] fragmentMassList = new double[sequence.length()]; 
		String[] fragmentList = FragmentMasses.buildFragments(sequence);
		int counter = 0;
		for(String frag:fragmentList) {
			double mass = FragmentMasses.getFragMass(frag, flag);
			fragmentMassList[counter] = mass;
			counter = counter + 1;
		}
		return fragmentMassList;
	}
	
	//we'll add more methods to get the additional masses that we need
	public static double[] addHs(double[] massList) {
		double[] massListPlus = new double[massList.length];
		int counter = 0;
		for(double mass:massList) {
			double massPlus = (mass + 2)/2;
			massListPlus[counter] = massPlus;
			counter++;
		}
		return massListPlus;
	}
	
	public static double[] getAIons(double[] bIons) {
		double[] aIons = new double[bIons.length];
		int counter = 0;
		for(double bIon:bIons) {
			double aIon = bIon - 28;
			aIons[counter] = aIon;
			counter++;
		}
		return aIons;
	}
	
	//this is highly inefficient; look for a better method of this process
	public static String seqReverse(String forwardSeq) {
		String nstr = "";
		char ch;
		for (int i = 0; i < forwardSeq.length(); i++) {
			ch = forwardSeq.charAt(i);
			nstr = ch + nstr;
		}
		return nstr;
	}
	
	public static FragmentMasses dataAssembly(String sequence) throws IOException{
		//here, the boolean indicates whether or not we have a b-ion or a y-ion,
		//this dictates whether or not we add +18 or +1 to the mass
		//b-ion series
		boolean bFlag = false;
		double[] bIons = FragmentMasses.buildMassList(sequence, bFlag);
		double[] bIonsPlus = FragmentMasses.addHs(bIons);
		//y-ion series
		String revSequence = FragmentMasses.seqReverse(sequence);
		boolean yFlag = true;
		double[] yIons = FragmentMasses.buildMassList(revSequence, yFlag);
		double[] yIonsPlus = FragmentMasses.addHs(yIons);
		//a-ion series
		double[] aIons = FragmentMasses.getAIons(bIons);
		return new FragmentMasses(bIons, yIons, aIons, bIonsPlus, yIonsPlus);
	}
}
