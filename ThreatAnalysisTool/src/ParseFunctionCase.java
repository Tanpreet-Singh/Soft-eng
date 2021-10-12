import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParseFunctionCase
{
	public static void main(String[] args) throws IOException
	{
		// Place file into parser
		JsonParser parser = new JsonFactory().createParser(new File("testJSON.txt"));
		Threat testThreat = new Threat();
		parse(parser, testThreat);
		// System.out.println(testThreat);
	}

	public static void parse(JsonParser parser, Threat threat) throws JsonParseException, IOException
	{
		/*
		while (parser.nextToken() != JsonToken.END_OBJECT)
		{
			String entry = parser.getText();
		}
		*/
		String entry = parser.nextToken().getText();
		System.out.println(entry);
	}
}
