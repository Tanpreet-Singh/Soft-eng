package drivers;

import gui.ParseFunction;
import main.GenPDF;
import gui.JSONBundle;
import com.fasterxml.jackson.databind.*;
import java.io.IOException; 

public class TestPDF {
	public static void main(String[] args) {
		ParseFunction parse = new ParseFunction();
		try {
			JSONBundle bundle = parse.parseJSON();
			GenPDF pdf = new GenPDF(bundle);
			pdf.genReport();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
