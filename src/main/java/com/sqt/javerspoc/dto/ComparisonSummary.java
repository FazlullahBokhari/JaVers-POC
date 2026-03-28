package com.sqt.javerspoc.dto;

import com.sqt.javerspoc.report.RuleChangeReport;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComparisonSummary {
    private int noChange;
    private int added;
    private int deleted;
    private int modified;
    private List<RuleChangeReport> reports;
}