package com.yicj.study.advice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class BufferedHttpRequest extends HttpServletRequestWrapper {
    @Getter
    private ConcurrentLinkedQueue<BufferedServletInputStream> concurrentLinkedQueue = new ConcurrentLinkedQueue<>() ;
    private byte[] buffer;
    private ByteArrayInputStream byteArrayInputStream;

    public BufferedHttpRequest(HttpServletRequest request) {
        super(request);
        // 将buffer填充
        try {
            log.info("从request拿出inputStream...");
            // 使用工具类将inputStream的内容拿到并转换为byte数据
            buffer = IOUtils.toByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("error : ", e);
        }
    }

    //重写getInputStream方法
    @Override
    public ServletInputStream getInputStream() throws IOException {
        log.info("将byte数组封装进BufferedServletInputStream");
        // 这里可以做我们数据的校验逻辑,比如处理XSS攻击
        log.info("getInputStream方法中处理xss攻击");
        BufferedServletInputStream bufferedServletInputStream = new BufferedServletInputStream(buffer);
        concurrentLinkedQueue.add(bufferedServletInputStream) ;
        return bufferedServletInputStream ;
    }

    public class BufferedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream byteArrayInputStream;

        public BufferedServletInputStream(byte[] buffer) {
            this.byteArrayInputStream = new ByteArrayInputStream(buffer);
        }

        public BufferedServletInputStream(ByteArrayInputStream inputStream) {
            this.byteArrayInputStream = inputStream;
        }

       //最主要重写此方法,用ByteArrayInputStream去接收
        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return byteArrayInputStream.read(b, off, len);
        }

        @Override
        public synchronized void reset() throws IOException {
            byteArrayInputStream.reset();
        }

        @Override
        public boolean markSupported() {
            return true;
        }


        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }
    }
}