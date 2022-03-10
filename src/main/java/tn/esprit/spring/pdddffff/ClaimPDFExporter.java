package tn.esprit.spring.pdddffff;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.esprit.spring.entities.Claim;
 
 
public class ClaimPDFExporter {
    private List<Claim> listClaim;
     
    public ClaimPDFExporter(List<Claim> listClaim) {
        this.listClaim = listClaim;
    }
 

	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase(" ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Etat", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("description", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("subject", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("date", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Claim claim : listClaim) {
        	 table.addCell(String.valueOf(claim.getIdClaim()));
      
            
            table.addCell(claim.getEtat().toString());

            
            table.addCell(claim.getDescription().toString());
            table.addCell(claim.getSubject());
            table.addCell(String.valueOf(claim.getDate()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Claims", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}