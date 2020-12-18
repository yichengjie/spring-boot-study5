1. 编写JUnit4ClassRunner类指定log4j文件
    ```java
    public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {
        static {
            try {
                initLogging("classpath:log4j-dev.properties");
            } catch (FileNotFoundException ex) {
                System.err.println("Cannot Initialize log4j");
            }
        }
        public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
            super(clazz);
        }
        // 来自Log4jConfigurer类（5.x版本已移除）
        private static void initLogging(String location) throws FileNotFoundException {
            String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
            URL url = ResourceUtils.getURL(resolvedLocation);
            if (ResourceUtils.URL_PROTOCOL_FILE.equals(url.getProtocol()) && !ResourceUtils.getFile(url).exists()) {
                throw new FileNotFoundException("Log4j config file [" + resolvedLocation + "] not found");
            }
            if (resolvedLocation.toLowerCase().endsWith(".xml")) {
                DOMConfigurator.configure(url);
            } else {
                PropertyConfigurator.configure(url);
            }
        }
    }
    ```
2. 在@RunWith中指定使用JUnit4ClassRunner.class
    ```java
    @Slf4j
    @RunWith(JUnit4ClassRunner.class)
    @ContextConfiguration(classes = MyConfig.class)
    public class HelloTest{
        @Test
        public void test1(){
            log.info("============");
        }
    }
    ```

