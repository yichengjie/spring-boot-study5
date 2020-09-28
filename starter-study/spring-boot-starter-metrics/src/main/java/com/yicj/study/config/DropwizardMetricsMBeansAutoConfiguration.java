package com.yicj.study.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.management.MBeanServer;

@Configuration
@ComponentScan({"com.yicj.study.aop","com.yicj.study.lifecycle"})
@AutoConfigureAfter(AopAutoConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DropwizardMetricsMBeansAutoConfiguration {
    @Value("${metrics.mbeans.domain.name:com.yicj.study.metrics}")
    String metricsMBeansDomainName ;
    private final MBeanServer mBeanServer ;
    //private final MetricRegistry metricRegistry ;

    @Bean
    public MetricRegistry metrics() {
        return new MetricRegistry();
    }

    @Bean
    public JmxReporter jmxReporter(){
        JmxReporter reporter = JmxReporter.forRegistry(metrics())
                .inDomain(metricsMBeansDomainName)
                .registerWith(mBeanServer)
                .build() ;
        return reporter ;
    }

}
