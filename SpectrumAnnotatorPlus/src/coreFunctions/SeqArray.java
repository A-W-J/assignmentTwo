package coreFunctions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SeqArray {
	
	public static String[] readSequences(String filename) throws IOException {
		Path paths = Paths.get(filename);
		String[] sequences = Files.lines(paths).parallel().toArray(String[]::new);
		return sequences;
	}

}

