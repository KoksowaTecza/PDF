package pl.asseco.jira.issue.pdfexport;


import java.util.Map;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * Klasa reprezntujaca jeden JiraPdfIssue, zawiera wszytskie metody potrzeben do zbudowania itemu
 * 
 * @author dawid
 *
 */
public class PdfIssueItem {
	private PdfPTable table;
	private Font headFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
	private Font boldFont = new Font(Font.FontFamily.HELVETICA  , 12, Font.BOLD);
	private Font defaultFont = new Font(Font.FontFamily.HELVETICA  , 12, Font.NORMAL);
	
	public PdfIssueItem() {
			table = new PdfPTable(1);
	}

	/**
	 * Pasek stanu, kolor zalezny od statusu, DO ZMIANY
	 * 
	 * @param state
	 */
	public void addStateBelt(IssueState state) {
		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(10);
		switch (state) {
		case CZERWONY:
			cell.setBackgroundColor(BaseColor.RED);
			break;
		case NIEBIESKI:
			cell.setBackgroundColor(BaseColor.BLUE);
			break;
		case ZIELONY:
			cell.setBackgroundColor(BaseColor.GREEN);
			break;

		default:
			cell.setBackgroundColor(BaseColor.GRAY);
			break;
		}
		table.addCell(cell);
	}

	
	/**
	 * Naglowek IssueItema. Wewnatrz miejsce na obrazek oraz na nazwe issue oraz jego status
	 * 
	 * @param issueName
	 * @param state
	 */
	public void addHeaderContent(String issueName, IssueState state) {
		try {
			PdfPTable headercontent = new PdfPTable(2);

			headercontent.setWidths(new float[] { 1f, 2f });

			// image
			PdfPCell image = new PdfPCell();
			image.setBorder(Rectangle.RIGHT);
			image.setFixedHeight(50);

			// czolowe info
			Font font2 = new Font();
			switch (state) {
				case CZERWONY:
					font2.setColor(BaseColor.RED);
					break;
				case NIEBIESKI:
					font2.setColor(BaseColor.BLUE);
					break;
				case ZIELONY:
					font2.setColor(BaseColor.GREEN);
					break;
	
				default:
					font2.setColor(BaseColor.GRAY);
					break;
			}

			PdfPCell headInfo = new PdfPCell();
			headInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headInfo.setBorder(Rectangle.NO_BORDER);
			headInfo.setPadding(10);
			Paragraph issueNameLabel = new Paragraph(issueName, headFont);
			Paragraph statusLabel = new Paragraph(state.name(), font2);
			headInfo.addElement(issueNameLabel);
			headInfo.addElement(statusLabel);

			headercontent.addCell(image);
			headercontent.addCell(headInfo);
			table.addCell(headercontent);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Sekcja KeyValue. W tej sekcji umieszczane sa tresci majaca budowwe klucz-wartosc
	 * 
	 * @param map
	 */
	public void addKeyValueContent(Map<String, String> map) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(10);
		Paragraph container = new Paragraph();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Paragraph singleInfo = new Paragraph();
			singleInfo.setSpacingAfter(5);
			Phrase key = new Phrase(entry.getKey(), boldFont);
			Phrase separator = new Phrase(" : ");
			Phrase value = new Phrase(entry.getValue(), defaultFont);
			singleInfo.add(key);
			singleInfo.add(separator);
			singleInfo.add(value);
			container.add(singleInfo);
		}
		cell.addElement(container);
		table.addCell(cell);
	}

	/**
	 * Sekcja Description. W tej scekcji umiweszczen sa tresci majace budowe nazwa-dluzszy opis
	 * 
	 * @param map
	 */
	public void addDescContent(Map<String, String> map) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		Paragraph container = new Paragraph();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Paragraph name = new Paragraph(entry.getKey() + ":", boldFont);
			Paragraph desc = new Paragraph(entry.getValue(), defaultFont);
			desc.setSpacingAfter(5);
			container.add(name);
			container.add(desc);
		}
		cell.addElement(container);
		table.addCell(cell);
	}

	/**
	 * Po wszytskim mozemy pobrac itema w postacie tabeli Itexta
	 * 
	 * @return
	 */
	public PdfPTable getTable() {
		return table;
	}
}
