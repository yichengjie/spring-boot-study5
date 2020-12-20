package com.yicj.study.mvc.hello.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HelloPdfView extends AbstractPdfView {

    private String text ;

    public HelloPdfView(String text){
        this.text = text ;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        document.add(new Paragraph(text)) ;
    }
}
