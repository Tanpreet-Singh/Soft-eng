import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Parse the JSON file into it's individual components. This class will be
 * used to return a collection of Threats.
 */
class ParseFunction
{
	private File dataset;
	private List allThreats;
	private Scanner scan;
	
	ParseFunction(String filePath)
	{
		dataset = new File(filePath);
		allThreats = new ArrayList();
	}
}
