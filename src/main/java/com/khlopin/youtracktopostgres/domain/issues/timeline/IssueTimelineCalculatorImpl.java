package com.khlopin.youtracktopostgres.domain.issues.timeline;

import com.khlopin.youtracktopostgres.domain.issues.IssueHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(value = 99)
public class IssueTimelineCalculatorImpl implements IssueTimelineCalculator, IssueHandler {
    @Override
    public void handle(String idReadable) {
        System.out.println("Calculating timeline of issue " + idReadable);
    }
}
