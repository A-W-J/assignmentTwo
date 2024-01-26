package coreFunctions;

import java.util.ArrayList;

public class Matches {
	final Double[] matchIdentities;
	public Matches(Double[] matchIdentities) {
		super();
		this.matchIdentities = matchIdentities;
	}
	public Double[] getMatchIdentities() {
		return matchIdentities;
	}
	
	public static Matches buildMatchObject(double[] predictions, double[] spectrumMasses) {
		ArrayList<Double> tempMatchIdentities = new ArrayList<Double>();
		//we're having a hard time implementing binary searches on decimal values; because time is
		//rather limited, we'll implement a linear search
		for(double predicted:predictions) {
			//int dummy = Arrays.binarySearch(spectrumMasses, predicted);
			for(double spectrumMass:spectrumMasses) {
				double diff = Math.abs(predicted - spectrumMass);
				if(diff < 1) {
					tempMatchIdentities.add(predicted);
				}
			}
		}
		Double[] matchIdentities = tempMatchIdentities.toArray(new Double[0]);
		//Double[] matchIdentities = tempMatchIdentities.toArray(new Double[0]);
		return new Matches(matchIdentities);
	}

}