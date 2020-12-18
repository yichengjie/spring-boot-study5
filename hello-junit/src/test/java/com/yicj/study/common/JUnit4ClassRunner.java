package com.yicj.study.common;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;
import java.io.FileNotFoundException;
import java.net.URL;

/*** 
 * @title:
 * @author yicj
 * @email 626659321@qq.com
 * @date 2020/12/16 17:42
 * @return {@link null}
 **/
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