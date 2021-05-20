/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class generatePdf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException {
        
        try {
            String file="C:\\Users\\Asus\\Desktop\\pdfEXPORTS\\facture.pdf";
        Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph para = new Paragraph("dddssss");
            document.add(para);
            document.close();
            System.out.println("ok");
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
