package pl.asseco.jira.issue.pdfexport;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * JiraIssueKontenerPdf na wszysfasfstskie issue. Jeden Issue reprezentowany jest przez PdfIssueItem
 * 
 * @author dawid
 *
 */
public class PdfIssueGraphicContainer {
	private Document document;
	private PdfPTable table;
	private int itemsSize;
	
	public PdfIssueGraphicContainer() {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Table3.pdf"));
			document.open();
			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * metoda zwracjaca obiekt reprezntujacy jeden issue
	 * 
	 * @return PdfIssueItem
	 */
	public PdfIssueItem createIssueItem(){
		return new PdfIssueItem();
	}
	
	/**
	 * metoda pozwalajca na dodanie issue do kontenaraPdf. Po dodaniu issue bedzie widoczne w pdfie
	 * Issue repzentwane przez tabele w IText. Wygneruj issue przy pomocy klasy PdfIssueItem
	 * @param container
	 */
	public void addIssueToContainer(PdfPTable container){
		PdfPCell cell = new PdfPCell(container);
		cell.setPadding(10);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		itemsSize++;
	}

	/**
	 * Fianlizuje budowe pdfa
	 * 
	 */
	public void generatePdf() {
		if (document != null && table != null) {
			try {
				if(itemsSize%2!=0){
					PdfPCell cell = new PdfPCell();
					cell.setPadding(5);
					cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
				}
				document.add(table);
				document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
