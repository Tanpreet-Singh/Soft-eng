package drivers;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneratePDF {

	public static void main(String[] args) {
		try {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			String file_name = "C:\\Users\\stnpr\\OneDrive\\Documents\\GitHub\\Soft-eng\\PDF reports\\" + "test" + ".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file_name));
			document.open();

			Paragraph para = new Paragraph("It is a test");

			document.add(para);

			document.close();

			System.out.println("Pdf has been generated");
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}
}