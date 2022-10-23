package com.yicj.env.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.*;

/**
 * 环境变量扩展点
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class MyJsonEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";
    private static final String DEFAULT_NAMES = "myapp";
    private static final String DEFAULT_FILE_EXTENSION = ".properties";

    private static final String PREFIX = "com.spring.environmentpostprocessor.";
    private static final String CALCULATION_MODE = "calculation_mode";
    private static final String GROSS_CALCULATION_TAX_RATE = "gross_calculation_tax_rate";
    private static final String CALCULATION_MODE_DEFAULT_VALUE = "NET";
    private static final double GROSS_CALCULATION_TAX_RATE_DEFAULT_VALUE = 0;

    List<String> names = Arrays.asList(CALCULATION_MODE, GROSS_CALCULATION_TAX_RATE);
    private static Map<String, Object> defaults = new LinkedHashMap<>();

    static {
        defaults.put(CALCULATION_MODE, CALCULATION_MODE_DEFAULT_VALUE);
        defaults.put(GROSS_CALCULATION_TAX_RATE, GROSS_CALCULATION_TAX_RATE_DEFAULT_VALUE);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        /*
         * PropertySource<?> system = environment.getPropertySources()
         * .get(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);
         *
         * Map<String, Object> prefixed = new LinkedHashMap<>();
         *
         * if (!hasOurPriceProperties(system)) { // Baeldung-internal code so
         * this doesn't break other examples logger.error(
         * "System environment variables [calculation_mode,gross_calculation_tax_rate] not detected, fallback to default value [calcuation_mode={},gross_calcuation_tax_rate={}]"
         * , CALCUATION_MODE_DEFAULT_VALUE,
         * GROSS_CALCULATION_TAX_RATE_DEFAULT_VALUE); prefixed = names.stream()
         * .collect(Collectors.toMap(this::rename, this::getDefaultValue));
         *
         * environment.getPropertySources()
         * .addAfter(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, new
         * MapPropertySource("prefixer", prefixed));
         *
         * return; }
         *
         * prefixed = names.stream() .collect(Collectors.toMap(this::rename,
         * system::getProperty)); environment.getPropertySources()
         * .addAfter(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, new
         * MapPropertySource("prefixer", prefixed));
         */
        List<String> list = Arrays.asList(StringUtils.trimArrayElements(StringUtils
                        .commaDelimitedListToStringArray(DEFAULT_SEARCH_LOCATIONS)));
        Collections.reverse(list);
        Set<String> reversedLocationSet = new LinkedHashSet(list);
        System.err.println(reversedLocationSet);

        ResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        // YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new
        // YamlPropertiesFactoryBean();
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();

        List<Properties> loadedProperties = new ArrayList<>(16);
        // List<Resource> propertiesPathList = new ArrayList<>(16);

        reversedLocationSet.forEach(location -> {
            Resource resource = defaultResourceLoader.getResource(location
                    + DEFAULT_NAMES + DEFAULT_FILE_EXTENSION);
            System.err.println(location + DEFAULT_NAMES
                    + DEFAULT_FILE_EXTENSION);
            if (!resource.exists()) {
                return;
            }
            System.err.println("################33");
            Properties p = new Properties();
            try {
                InputStream inputStream = resource.getInputStream();
                p.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            loadedProperties.add(p);
        });

        System.err.println(loadedProperties);

        Properties filteredProperties = new Properties();
        Set<Object> addedKeys = new LinkedHashSet<>();
        for (Properties propertySource : loadedProperties) {
            for (Object key : propertySource.keySet()) {
                String stringKey = (String) key;
                if (addedKeys.add(key)) {
                    filteredProperties.setProperty(stringKey,
                            propertySource.getProperty(stringKey));
                }
            }
        }

        System.err.println("*********** end ************");

        System.err.println(filteredProperties);

        PropertiesPropertySource propertySources = new PropertiesPropertySource(
                DEFAULT_NAMES, filteredProperties);
        environment.getPropertySources().addLast(propertySources);
    }

    private Object getDefaultValue(String key) {
        return defaults.get(key);
    }

    private String rename(String key) {
        return PREFIX + key.replaceAll("\\_", ".");
    }

    private boolean hasOurPriceProperties(PropertySource<?> system) {
        if (system.containsProperty(CALCULATION_MODE)
                && system.containsProperty(GROSS_CALCULATION_TAX_RATE)) {
            return true;
        } else
            return false;
    }

}
