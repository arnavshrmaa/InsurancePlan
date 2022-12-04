package com.example.demo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGenerator {

	public void export(HttpServletResponse rs, List<Plan> plans) throws IOException {
		
		//Page Format (A4 size)
		Document document = new Document(PageSize.A4);
		  PdfWriter.getInstance(document, rs.getOutputStream());
		    document.open();
		
		//Font Format 
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
		  font.setSize(16);
		    font.setColor(Color.DARK_GRAY);

		//Paragraph Format
		Paragraph p = new Paragraph("List of Plans", font);
		  p.setAlignment(Paragraph.ALIGN_CENTER);
		     document.add(p);

		//Table Format
		PdfPTable table = new PdfPTable(6);
		   table.setWidthPercentage(100f);
		     table.setWidths(new float[] { 1f, 3f, 3.5f, 3.5f, 3f, 1f });
		        table.setSpacingBefore(10);

		writeTableHeader(table);
		  writeTableData(table, plans);
		
		document.add(table);
		document.close();
	}

	private void writeTableData(PdfPTable table, List<Plan> plans) {
		for(Plan p: plans)
		{
			table.addCell(String.valueOf(p.getId()));
		    	table.addCell(p.getPlanName());
			      table.addCell(p.getPlanCategory());
			        table.addCell(p.getPlanStartDate());
			           table.addCell(p.getPlanValidity());
 		}
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setColor(Color.WHITE);
		font.setSize(14);

		cell.setPhrase(new Phrase("Id", font));
		      table.addCell(cell);
		
		cell.setPhrase(new Phrase("PlanName", font));
		      table.addCell(cell);
	
		cell.setPhrase(new Phrase("PlanCategory", font));
		     table.addCell(cell);
		     
		cell.setPhrase(new Phrase("PlanStartDate", font));
		     table.addCell(cell);
		     
		cell.setPhrase(new Phrase("PlanValidity", font));
		     table.addCell(cell);

	}
}
