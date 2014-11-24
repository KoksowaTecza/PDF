package pl.asseco.jira.issue.pdfexport;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args){

		
		PdfIssueGraphicContainer container = new PdfIssueGraphicContainer();
		
		for(int i=0; i<11; i++){
		PdfIssueItem issueItem  = container.createIssueItem();
		//to bedzie do zmiany, chcialem stwozrzyc enuma ze wszytskimi mozliwymi statusami w jira
		//zmiana edycja 
		issueItem.addStateBelt(IssueState.CZERWONY);
		issueItem.addHeaderContent("To jest testowy issue", IssueState.CZERWONY);
		
		//dodanie do sekcji key-value wartosci
		Map<String, String> map3 = new LinkedHashMap<String, String>();
		map3.put("Autor", "Dawid Kurek");
		map3.put("Otwarty", "19.11.2014");
		map3.put("Obserwowany", "Bartosz Mrowka, Damian Biernat, Mateusz Sochacki");
		issueItem.addKeyValueContent(map3);
		
		//doanie do sckcji descrption warotsci
		Map<String, String> map4 = new LinkedHashMap<String, String>();
		map4.put("Nazwa", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. "
				+ "Cras vel lorem. Etiam pellentesque aliquet tellus. ");
			
		map4.put("Nazwa2", "Phasellus ornare, ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget nisi.");
		issueItem.addDescContent(map4);
		
		//dodanie issueItem do kontenera
		container.addIssueToContainer(issueItem.getTable());
		}
	
		
		container.generatePdf();
		
	}
}
