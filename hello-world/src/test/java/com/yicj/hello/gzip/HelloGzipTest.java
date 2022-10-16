package com.yicj.hello.gzip;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class HelloGzipTest {

    @Test
    public void hello() throws IOException{
        //// 1057580 -> gzip : 1057877  图片文件压缩效果不明显
        FileInputStream inputStream = new FileInputStream("/Users/yicj/temp/hello.png") ;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(outputStream)) {
            StreamUtils.copy(inputStream, gzip) ;
        }
        inputStream.close();
        int size = outputStream.size();
        log.info("size : {}", size);
    }

    @Test
    public void hello2() throws IOException{
        //// 247 -> gzip : 35   文件压缩效果明显
        FileInputStream inputStream = new FileInputStream("/Users/yicj/temp/hello.txt") ;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(outputStream)) {
            StreamUtils.copy(inputStream, gzip) ;
        }
        inputStream.close();
        int size = outputStream.size();
        log.info("size : {}", size);
    }
}
