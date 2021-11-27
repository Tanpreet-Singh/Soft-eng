package gui;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GenPDF {

	public GenPDF() {
		super();
	}

	public String create() {
		String res = "";
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss-SSS");
			LocalDateTime now = LocalDateTime.now();

			String file_name = "C:\\Users\\stnpr\\OneDrive\\Documents\\GitHub\\Soft-eng\\PDF reports\\"	+ dtf.format(now) + ".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file_name));
			document.open();

			Paragraph para = new Paragraph("It is a test");

			document.add(para);

			document.close();

			res = "Pdf has been generated";
		} catch (Exception e) {
			res = "Error";
		}
		return res;
	}
}
