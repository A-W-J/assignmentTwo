package coreFunctions;

import java.io.IOException;
import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) throws IOException {
		String specFile = "C:\\Users\\alexw\\Documents\\Ohio State\\Searle Lab\\Projects\\assignmentTwo\\spec.txt";
		Spectrum spec = Spectrum.readSpectrum(specFile);
		String sequence = "HELTEISNVDVETQSGK";
		FragmentMasses masses = FragmentMasses.dataAssembly(sequence);
		//we'll create several different match objects for each type of peak
		Matches bMatches = Matches.buildMatchObject(masses.getbIons(), spec.getMasses());
		Matches yMatches = Matches.buildMatchObject(masses.getyIons(), spec.getMasses());
		Matches aMatches = Matches.buildMatchObject(masses.getaIons(), spec.getMasses());
		Matches bMatchesPlus = Matches.buildMatchObject(masses.getbIonsPlus(), spec.getMasses());
		Matches yMatchesPlus = Matches.buildMatchObject(masses.getyIonsPlus(), spec.getMasses());
		//reporting the results
		for(double match:bMatches.getMatchIdentities()) {
			System.out.println("b ion" + "     " + match);
		}
		for(double match:yMatches.getMatchIdentities()) {
			System.out.println("y ion" + "     " + match);
		}
		for(double match:aMatches.getMatchIdentities()) {
			System.out.println("a ion" + "     " + match);
		}
		for(double match:bMatchesPlus.getMatchIdentities()) {
			System.out.println("b ion 2H+" + "     " + match);
		}
		for(double match:yMatchesPlus.getMatchIdentities()) {
			System.out.println("y ion 2H+" + "     " + match);
		}
		Peaks peaks = Peaks.getPeaks(spec);
		ArrayList<Character> output = SequencePrediction.predictSequence(spec, peaks);
		System.out.println(output.size());
		for(Character str:output) {
			System.out.println(str);
		}
	}

}
