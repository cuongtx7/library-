package com.example.demo.service.job;

import com.example.demo.repository.BorrowSlipRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Quartz implements Job {

    @Autowired
    private BorrowSlipRepository borrowSlipRepository;

    @Override
    public void execute(JobExecutionContext context) {
        String borrowSlipId = context.getMergedJobDataMap().getString("borrowSlipId");
        borrowSlipRepository.findById(borrowSlipId).ifPresent(borrowSlip -> {
            if ("processing".equals(borrowSlip.getStatus())) {
                borrowSlip.setStatus("overdue");
                borrowSlip.setLastModifiedDate(Instant.now());
                borrowSlipRepository.save(borrowSlip);
            }
        });
    }
}