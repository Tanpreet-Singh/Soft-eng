package gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
//import com.itextpdf.layout.element.Text;

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

				Font regular = new Font(FontFamily.HELVETICA, 12);
				Font bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
				Paragraph para1 = new Paragraph();
				para1.add(new Chunk("Threat Name: ", bold));
				para1.add(new Chunk(t.getName(), regular));
				
				para1.setMultipliedLeading(3); // adds spacing at top of each threat
				
				Paragraph para = new Paragraph();
				para.add(new Chunk("Threat ID: ", bold));
				para.add(new Chunk(t.getID(), regular));
				Paragraph p1 = new Paragraph();
				p1.add(new Chunk("Description: ", bold));
				p1.add(new Chunk(t.getDescription(), regular));
				Paragraph p2 = new Paragraph();
				p2.add(new Chunk("Created by: ", bold));
				p2.add(new Chunk(t.getCreated_by_ref(), regular));
				Paragraph p3 = new Paragraph();
				p3.add(new Chunk("Date Created: ", bold));
				p3.add(new Chunk(t.getDateCreated(), regular));
				Paragraph p4 = new Paragraph();
				p4.add(new Chunk("Date Modified: ", bold));
				p4.add(new Chunk(t.getDateModified()));
				Paragraph p5 = new Paragraph();
				p5.add(new Chunk("Platforms: ", bold));
				p5.add(new Chunk(t.getPlatforms()));
				Paragraph p6 = new Paragraph();
				p6.add(new Chunk("Spec version: ", bold));
				p6.add(new Chunk(t.getSpecVersion(), regular));
		

				document.add(para1);
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
