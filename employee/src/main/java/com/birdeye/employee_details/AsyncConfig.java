package com.birdeye.employee_details;


import java.util.Map;
import java.util.concurrent.Executor;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name ="taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("userThread-");
        executor.setTaskDecorator(new MdcTaskDecorator());
        
        executor.initialize();
        
        return executor;
    }
}

class MdcTaskDecorator implements TaskDecorator {

	  @Override
	  public Runnable decorate(Runnable runnable) {
	    // Right now: Web thread context !
	    // (Grab the current thread MDC data)
	    Map<String, String> contextMap = MDC.getCopyOfContextMap();
	    return () -> {
	      try {
	        // Right now: @Async thread context !
	        // (Restore the Web thread context's MDC data)
	        MDC.setContextMap(contextMap);
	        runnable.run();
	      } finally {
	        MDC.clear();
	      }
	    };
	  }
	}