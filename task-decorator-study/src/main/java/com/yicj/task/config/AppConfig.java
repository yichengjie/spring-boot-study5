package com.yicj.task.config;

import com.yicj.task.component.ContextCopyingDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        int corePoolSize = 10 ;
        int maxPoolSize = 20 ;
        int queueCapacity = 100 ;

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MyExecutor-");

        // for passing in request scope context
        executor.setTaskDecorator(new ContextCopyingDecorator());

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}
