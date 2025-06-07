package com.example.demo.service.job;

import com.example.demo.repository.BorrowSlipRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Quartz implements Job {

    @Autowired
    private BorrowSlipRepository borrowSlipRepository;

    @Autowired
    Scheduler schedulerFactoryBean;

    @Override
    public void execute(JobExecutionContext context) {
        String borrowSlipId = context.getMergedJobDataMap().getString("borrowSlipId");
        borrowSlipRepository.findById(borrowSlipId).ifPresent(borrowSlip -> {
            if ("processing".equals(borrowSlip.getStatus())) {
                borrowSlip.setStatus("overdue");
                borrowSlip.setLastModifiedDate(Instant.now());
                borrowSlipRepository.save(borrowSlip);
            } else {
                deleteJob(context);
            }
        });
    }

    private void deleteJob(JobExecutionContext context) {
        Scheduler scheduler = schedulerFactoryBean;
        try {
            scheduler.deleteJob(context.getJobDetail().getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}