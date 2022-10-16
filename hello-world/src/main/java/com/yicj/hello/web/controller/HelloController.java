package com.yicj.hello.web.controller;

import ch.qos.logback.classic.ClassicConstants;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.util.zip.GZIPOutputStream;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        //MDC.put(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY, request.getRemoteHost());
        String host = MDC.get(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY);
        return host;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers= new  HttpHeaders();
        headers.set("Content-Encoding", "gzip");
        FileInputStream inputStream = new FileInputStream("/Users/yicj/temp/hello.png") ;
        //FileInputStream inputStream = new FileInputStream("/Users/yicj/temp/hello.txt") ;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(outputStream)) {
            StreamUtils.copy(inputStream, gzip) ;
        }
        inputStream.close();
        byte[] resultBytes = outputStream.toByteArray();
        return new ResponseEntity<>(resultBytes, headers, HttpStatus.CREATED); //返回压缩文件流
    }

}
