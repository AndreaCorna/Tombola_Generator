package main_package;

import java.io.FileOutputStream;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PdfCreator {
 
 
 public void createPDF (String pdfFilename, int[][][] matrix){
 
  Document doc = new Document();
  PdfWriter docWriter = null;
 
 
  try {
    
   //special font sizes
   Font bfBold12 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0)); 

   Font bfBold22 = new Font(FontFamily.HELVETICA, 22, Font.BOLD, new BaseColor(0, 0, 0)); 

 
   //file path
   String path = pdfFilename;
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
    
   //document header attributes
   doc.addAuthor("AndreaCorna");
   doc.addCreationDate();
   doc.addProducer();
   doc.addCreator("AndreaCorna");
   doc.addTitle("List_Tables");
   doc.setPageSize(PageSize.A4);
   
   //open document
   doc.open();
   float[] columnWidths = {0.6f,0.6f,0.6f,0.6f,0.6f,0.6f,0.6f,0.6f,0.6f};
   int numberTable = 1;
   PdfPTable table = null;
   for(int page=0; page < matrix.length; page++){
	   for(int row=0; row<matrix[page].length; row++){
		   if(row % 3 == 0){
			   table = new PdfPTable(columnWidths);
			   // set table width a percentage of the page width
			   table.setWidthPercentage(60f);
			   insertCell(table, "Unità Pastorale B.V.M - Cisano Bergamasco", Element.ALIGN_CENTER, 9, bfBold12,true);
			   insertCell(table, "Tabella n° "+numberTable, Element.ALIGN_LEFT, 3, bfBold12,true);
			   insertCell(table, "6 Gennaio 2015", Element.ALIGN_CENTER, 6, bfBold12,true);
			   numberTable++;
		   }
		   for(int column = 0; column < matrix[page][row].length; column++){
			   if(matrix[page][row][column] == 0){
				   insertCell(table, "", Element.ALIGN_CENTER, 1, bfBold22,false);

			   }else{
				   insertCell(table, ""+matrix[page][row][column], Element.ALIGN_CENTER, 1, bfBold22,false);

			   }
			  
		   } 
		   if(row % 3 == 2){
			   insertCell(table, "", Element.ALIGN_CENTER, 9, bfBold22,false);
			   table.setHeaderRows(1);
			   table.setHorizontalAlignment(Element.ALIGN_LEFT);

			    
			   //add the PDF table to the paragraph 
			   //paragraph.add(table);
			   // add the paragraph to the document
			   doc.add(table);
		   }
		   
	   }
	   doc.newPage();

   }

   doc.newPage();
   
   
  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
  }
  finally
  {
   if (doc != null){
    //close the document
    doc.close();
   }
   if (docWriter != null){
    //close the writer
    docWriter.close();
   }
  }
 }
  
 private void insertCell(PdfPTable table, String text, int align, int colspan, Font font,boolean isIntestation){
   
  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  //set the cell alignment
  cell.setHorizontalAlignment(align);
  cell.setVerticalAlignment(Element.ALIGN_CENTER);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  if(!isIntestation){
	  cell.setMinimumHeight(28f);
  }else{
	  cell.setMinimumHeight(15f);

  }

  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  //add the call to the table
  table.addCell(cell);
   
 }
 
}