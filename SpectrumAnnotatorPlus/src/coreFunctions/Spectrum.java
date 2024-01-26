package coreFunctions;

import java.io.IOException;
import java.util.*;


//use next line file reader approach
//this strategy might be too fragile
//look at string tokenizer

public class Spectrum{
	private final double[] masses;
	private final double[] intensities;

	public Spectrum(double[] masses, double[] intensities) {
		super();
		this.masses = masses;
		this.intensities = intensities;
	}
	public double[] getMasses() {
		return masses;
	}
	public double[] getIntensities() {
		return intensities;
	}
	
	public static Spectrum readSpectrum(String filename) throws IOException {
		//using a string tokenizer, we can leverage previous code
		String[] lines = SeqArray.readSequences(filename);
		//then we allocate new arrays equal to the number of lines
		double[] masses = new double[lines.length];
		double[] intensities = new double[lines.length];
		//here, we need to set up a counter
		int counter = 0;
		//instead of using a scanner, we use a tokenizer to operate on each string and
		//parse out the values
		for(String line:lines) {
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()) {
				masses[counter] = (double)Double.valueOf(st.nextToken());
				intensities[counter] = (double)Double.valueOf(st.nextToken());
				counter = counter + 1;
			}
		}
		return new Spectrum(masses, intensities);
	}
}
