package com.sqt.javerspoc.report;

import com.sqt.javerspoc.dto.RuleChangeDetail;
import com.sqt.javerspoc.enums.ChangeType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RuleChangeReport {

    private String ruleId;
    private Integer rowNumber;
    private ChangeType changeType;
    private List<RuleChangeDetail> changes = new ArrayList<>();

    public RuleChangeReport(String ruleId, ChangeType changeType) {
        this.ruleId = ruleId;
        this.changeType = changeType;
    }

    public void addChange(RuleChangeDetail change) {
        this.changes.add(change);
    }
}