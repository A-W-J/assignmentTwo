package coreFunctions;

import java.util.*;

public class Peaks {
	final HashMap<Double, Double> peaks;
	
	public Peaks(HashMap<Double, Double> peaks) {
		super();
		this.peaks = peaks;
	}
	
	public HashMap<Double, Double> getPeaks(){
		return peaks;
	}
	
	public static Peaks getPeaks(Spectrum spec){
		HashMap<Double, Double> peaks = new HashMap<Double, Double>();
		for(int i = 0; i < spec.getMasses().length;i++) {
			double currentMass = spec.getMasses()[i];
			double currentIntensity = spec.getIntensities()[i];
			peaks.put(currentMass, currentIntensity);
		}
		return new Peaks(peaks);
		
	}
	
	

}
