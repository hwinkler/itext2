package com.lowagie.text.pdf

import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.Paragraph
import spock.lang.Specification

class PdfWriterSpec extends Specification {
    private static String OUTPUT_FOLDER = 'build/test-output'
    private static String FILE = "${OUTPUT_FOLDER}/PositionPdf.pdf";
    
    def setup() {
        (new File(OUTPUT_FOLDER)).mkdirs()
    }
    
    def "Create a PDF"() throws Exception {
        when:
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        // Left
        Paragraph paragraph = new Paragraph("This is right aligned text");
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        // Centered
        paragraph = new Paragraph("This is centered text");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        // Left
        paragraph = new Paragraph("This is left aligned text");
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        // Left with indentation
        paragraph = new Paragraph(
                "This is left aligned text with indentation");
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setIndentationLeft(50);
        document.add(paragraph);

        document.close();
        File createdPDF = new File(FILE)
        
        then:
        createdPDF.exists()
        createdPDF.size() > 0
    }
}
