package com.sqt.javerspoc.model;

import lombok.*;
import org.javers.core.metamodel.annotation.Id;

import java.util.Objects;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    @Id
    private String ruleId;

    private String ruleName;
    private String spelRule;
    private String resolutionOptions;
    private String categories;
    private Double threshold;
    private Integer priority;
    private String status;
    private String createdBy;
    private String updatedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rule)) return false;
        Rule rule = (Rule) o;
        return Objects.equals(ruleId, rule.ruleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleId);
    }
}