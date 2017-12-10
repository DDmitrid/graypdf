package in.amarjeet;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

public class PDFEngine {
	public static final String SOURCE = "C:\\Users\\anand\\Desktop\\1.pdf";
	public static final String TARGET = "C:\\Users\\anand\\Desktop\\2.pdf";
	
//	public static final String SOURCE = "/home/anand/Desktop/1.pdf";
//	public static final String TARGET = "/home/anand/Desktop/2.pdf";

	public static void main(String[] args) throws Exception {
		PdfDocument srcPDFDoc = new PdfDocument(new PdfReader(SOURCE));
		PdfDocument trgtPDFDoc = new PdfDocument(new PdfWriter(TARGET));

		int pageCount = srcPDFDoc.getNumberOfPages();
		for (int i = 1; i <= pageCount; i++) {
			PdfPage origPage = srcPDFDoc.getPage(i);
			Rectangle orgRect = origPage.getPageSize();
			PdfFormXObject pagexCopy = origPage.copyAsFormXObject(trgtPDFDoc);
			PdfPage pageCopy = trgtPDFDoc.addPage(origPage.copyTo(trgtPDFDoc));

			PdfCanvas canvas = new PdfCanvas(pageCopy);
			canvas.rectangle(orgRect).setFillColor(ColorConstants.LIGHT_GRAY).fill().saveState();
			canvas.addXObject(pagexCopy, 0, 0);
		}

		trgtPDFDoc.close();
		srcPDFDoc.close();

	}
}
