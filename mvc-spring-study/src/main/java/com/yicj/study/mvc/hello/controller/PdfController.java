package com.yicj.study.mvc.hello.controller;

import com.yicj.study.mvc.hello.view.HelloPdfView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PdfController {

    @GetMapping("/pdf")
    public ModelAndView pdf(){
        ModelAndView mv = new ModelAndView() ;
        mv.setView(new HelloPdfView("this should be in the PDF"));
        return mv ;
    }
}
