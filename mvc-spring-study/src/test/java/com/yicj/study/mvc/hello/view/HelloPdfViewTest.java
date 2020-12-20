package com.yicj.study.mvc.hello.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class HelloPdfViewTest {
    private MockHttpServletRequest request = new MockHttpServletRequest() ;
    private MockHttpServletResponse response = new MockHttpServletResponse() ;

    @Test
    public void pdf() throws Exception {
        String text = "this should be in the PDF" ;
        AbstractPdfView view = new HelloPdfView(text) ;
        view.render(new HashMap<>(), request, response);
        byte[] pdfContent = response.getContentAsByteArray();
        Assert.assertEquals("correct response content type: ", "application/pdf", response.getContentType());
        Assert.assertEquals("correct response contet length: ", pdfContent.length, response.getContentLength());
        // 这里生成一个pdf,内容与使用pdf视图写入到Http的response中的一样
        Document document = new Document(PageSize.A4) ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);
        document.open();
        document.add(new Paragraph(text)) ;
        document.close();
        byte[] baosConent = baos.toByteArray();
        Assert.assertEquals("content size ", pdfContent.length, baosConent.length);
        // 对回读数据和新生成的pdf数据进行字节比较
        int diffCount = 0 ;
        for (int i = 0 ; i < pdfContent.length; i++){
            if (pdfContent[i] != baosConent[i]){
                diffCount ++ ;
            }
        }
    }
}
