#### 问题背景
1. 编写Filter再controller获取数据之前读取数据
    ```java
    @Order(1)
    @Component
    public class HelloFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            byte [] bytes = new byte[1024] ;
            request.getInputStream().read(bytes);
            String content = new String(bytes, "UTF-8");
            log.info("过滤器拿到请求数据 :{}", content);
            filterChain.doFilter(request, response);
        }
    }
    ```
2. 编写Controller业务方法从body中获取数据
    ```java
    @RestController
    public class HomeController {
        @PostMapping("/hello")
        @ResponseBody
        public User hello(@RequestBody  User user){
            return user;
        }
        @Data
        static class User{
            private String id ;
            private String name ;
        }
    }
    ```
3. 运行发现hello方法中获取到的user属性值id和name都为null
#### 解决方式 
1. 在所有所有调用request.getInputStream()方法之前编写Filter,替换原HttpServletRequest
    ```java
    @Order(0)
    @Component
    public class RequestBufferedFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            request = new BufferedHttpRequest(request) ;
            filterChain.doFilter(request, response);
        }
    }
    ```
2. 编写HttpServletRequestWrapper的实现类, 重点是重写了getInputStream()方法
    ```java
    public class BufferedHttpRequest extends HttpServletRequestWrapper {
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
        //重点: 重写getInputStream方法
        @Override
        public ServletInputStream getInputStream() throws IOException {
            log.info("将byte数组封装进BufferedServletInputStream");
            // 这里可以做我们数据的校验逻辑,比如处理XSS攻击
            log.info("getInputStream方法中处理xss攻击");
            return new BufferedServletInputStream(buffer);
        }
        
        class BufferedServletInputStream extends ServletInputStream {
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
            public void setReadListener(ReadListener listener) {}
        }
    }
    ```