package gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GenPDF {

	ArrayList<Threat> listOfThreats;
	String ID;

	public GenPDF(ArrayList<Threat> listOfThreats) {
		this.listOfThreats = listOfThreats;
	}

	public GenPDF() {

	}

	public GenPDF(String ID) {
		this.ID = ID;
	}

	public void genReport() {
		try {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss-SSS");
			LocalDateTime now = LocalDateTime.now();

			FileDialog fileDialog = new FileDialog(new Frame(), "Save File", FileDialog.SAVE);
			fileDialog.setVisible(true);
			String save = "";

			if (fileDialog.getDirectory() != null && fileDialog.getFile() != null) {
				save = fileDialog.getDirectory() + fileDialog.getFile();
			}

			String file_name = save + ".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file_name));

			document.open();
			Iterator<Threat> it = listOfThreats.iterator();

			while (it.hasNext()) {
				Threat t = it.next();
				
				Paragraph para = new Paragraph("ID: " + t.getID());
				Paragraph p1 = new Paragraph("Description: " + t.getDescription());
				Paragraph p2 = new Paragraph("Created by: " + t.getCreated_by_ref());
				Paragraph p3 = new Paragraph("Date Created: " + t.getDateCreated());
				Paragraph p6 = new Paragraph("Date Modified: " + t.getDateModified());
				Paragraph p4 = new Paragraph("Platforms: " + t.getPlatforms());
				Paragraph p5 = new Paragraph("Kill chain: " + t.getKillChains());

				document.add(para);
				document.add(p1);
				document.add(p2);
				document.add(p3);
				document.add(p4);
				document.add(p5);
				document.add(p6);
			}
	
			document.close();

		} catch (Exception e) {
			System.out.println("Error in genpdf");
		}
	}
}
