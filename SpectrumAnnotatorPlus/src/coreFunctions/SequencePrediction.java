package coreFunctions;

import java.util.ArrayList;
import java.util.Hashtable;

public class SequencePrediction {
	
	private static ArrayList<Double> getHighIntensityMasses(double[] ds, Peaks peaks) {
		System.out.println("bar");
		ArrayList<Double> targetMasses = new ArrayList<Double>();
		for(double mass:ds) {
			double intensity = peaks.getPeaks().get(mass);
			if(intensity >= 10000) {
				targetMasses.add(mass);
			}
		}
		return targetMasses;
	}
	
	private static ArrayList<Double> getMassDifferences(ArrayList<Double> targetMasses){
		//this is a bit of a complex problem to tackle
		//the first thing we will do is construct an array for global mass differences
		ArrayList<Double> massDifferences = new ArrayList<Double>();
		//essentially, we want to check four values, right and left, for each mass in our mass list
		//we will start by defining boundaries
		int lowerBound = 0;
		int upperBound = targetMasses.size() - 1;
		//then we iterate over each list index;
		for(int i = 0; i < upperBound; i++) {
			//"i" tells us where we are in the list
			//we define capital J to tell us how many elements to the right we have
			int J = Math.abs(i - upperBound);
			//if we have than or equal to four elements to go, we check four peaks to the right
			if(J > 0 & J >= 4) {
				//we check 4 peaks to the right
				for(int j = 1;j<=4;j++) {
					double massDiff = Math.abs(targetMasses.get(i) - targetMasses.get(i + j));
						massDifferences.add(massDiff);
				}
			}
			//if we have less than four elements to go, we check J peaks to the right
			//this breaks if J = 3, as that gives us an out of bound error
			else if(J > 0 & J < 4) {
				for(int j = 1;j <= J;j++) {
					//we will add a check here to exit this loop if we reach values outside of our boundaries
					if((i + j) >= upperBound) {
						break;
					}
					double massDiff = Math.abs(targetMasses.get(i) - targetMasses.get(i + j));
					massDifferences.add(massDiff);
				}
			}
			//if there are no elements to go, we do nothing
			else {
				continue;
			}
			//likewise, we define capital K to tell us how many elements to the left we have
			int K = Math.abs(i - lowerBound);
			//the checks are the same
			if(K > 0 & K >= 4) {
				//here, we have to change the direction we are going
				for(int k = 1;k <= 4;k++) {
					double massDiff = Math.abs(targetMasses.get(i) - targetMasses.get(i - k));
					massDifferences.add(massDiff);
				}
			}
			else if(K > 0 & K < 4) {
				for(int k = 1;k <= K;k++) {
					double massDiff = Math.abs(targetMasses.get(i) - targetMasses.get(i - k));
					massDifferences.add(massDiff);
				}
			}
			else {
				continue;
			}
			
		}
		return massDifferences;
	}
	
	private static ArrayList<Character> convertMassDifferences(ArrayList<Double> massDifferences){
		Hashtable<Character, Double> aminoTable = AminoTable.makeAminoTable();
		ArrayList<Character> output = new ArrayList<Character>();
		Character[] aminos = new Character[]{'A', 'C', 'R', 'N', 'D', 'E', 'Q', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		int matched = 0;
		int notMatched = 0;
		for(double mass:massDifferences) {
			for(Character amino:aminos) {
				double aminoMass = aminoTable.get(amino);
				double massDifference = Math.abs(mass - aminoMass);
				if(massDifference < 0.015) {
					output.add(amino);
					matched++;
				}
				else {
					output.add('X');
					notMatched++;
				}
			}
		}
		System.out.println("number of identified aminos: " + matched);
		System.out.println("number of unidentified aminos: " + notMatched);
		return output;
	}
	
	public static ArrayList<Character> predictSequence(Spectrum spec, Peaks peaks){
		ArrayList<Double> targetMasses = SequencePrediction.getHighIntensityMasses(spec.getMasses(), peaks);
		ArrayList<Double> massDifferences = SequencePrediction.getMassDifferences(targetMasses);
		ArrayList<Character> output = SequencePrediction.convertMassDifferences(massDifferences);
		return output;
	}
	
	

}
