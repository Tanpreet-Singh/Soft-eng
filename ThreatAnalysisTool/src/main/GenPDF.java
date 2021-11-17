package main;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import gui.Threat;
import java.util.Iterator;
import java.util.ArrayList;
import gui.JSONBundle;

public class GenPDF {
	
	ArrayList<Threat> listOfThreats;
	File templateFile;
	
	public GenPDF(JSONBundle bundledThreats) {
		this.listOfThreats = bundledThreats.getObjects();
	}	

	public void genReport() {
		//try {
			templateFile = new File("template.html");
			//File copyFile = new File("report.html");
			//Files.copy(source.toPath(), destination.toPath());
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		String paste = "";
		Iterator<Threat> iterateThreats = listOfThreats.iterator();
		while (iterateThreats.hasNext()) {
			Threat currentThreat = iterateThreats.next();
			paste = paste + "<b>Name:</b> " + currentThreat.getName() + "<br><br>";
			paste = paste + "<b>Description:</b> " + currentThreat.getDescription() + "<br><br>";
			paste = paste + "<b>Date Created:</b> " + currentThreat.getDateCreated() + "<br><br>";
			paste = paste + "<b>Date Modified:</b> " + currentThreat.getDateModified() + "<br><br>";
			paste = paste + "<br><br>";

			// Insert into html
			try {
				String htmlString = Files.readString(Paths.get("template.html"));
				htmlString = htmlString.replace("$body", paste);
				try {
					Files.writeString(Paths.get("report.html"), htmlString);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//File reportFile = new File("report.html");
			
		}
	}
}
