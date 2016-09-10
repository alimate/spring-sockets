package me.alidg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {
    private static final int JUST_ONE_THREAD = 1;

    /**
     * Register a {@linkplain TaskScheduler} to run our push service task
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(JUST_ONE_THREAD);
        taskScheduler.setThreadNamePrefix("Pusher-");

        return taskScheduler;
    }
}