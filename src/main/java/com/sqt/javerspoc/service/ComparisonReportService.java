package com.sqt.javerspoc.service;

import com.sqt.javerspoc.dto.ComparisonSummary;
import com.sqt.javerspoc.dto.RuleChangeDetail;
import com.sqt.javerspoc.enums.ChangeType;
import com.sqt.javerspoc.model.Rule;
import com.sqt.javerspoc.report.RuleChangeReport;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;

import java.util.*;
import java.util.stream.Collectors;

public class ComparisonReportService {

    private final Javers javers = JaversBuilder.javers().build();

    public ComparisonSummary compare(List<Rule> oldRules, List<Rule> newRules) {

        Diff diff = javers.compareCollections(oldRules, newRules, Rule.class);

        Map<String, RuleChangeReport> reportMap = new HashMap<>();

        // Row mapping
        Map<String, Integer> oldRowMap = new HashMap<>();
        Map<String, Integer> newRowMap = new HashMap<>();

        int i = 1;
        for (Rule r : oldRules) {
            oldRowMap.put(r.getRuleId(), i++);
        }

        i = 1;
        for (Rule r : newRules) {
            newRowMap.put(r.getRuleId(), i++);
        }

        Set<String> addedIds = new HashSet<>();
        Set<String> deletedIds = new HashSet<>();
        Set<String> modifiedIds = new HashSet<>();
        Set<String> noChangeIds = new HashSet<>();

        for (var change : diff.getChanges()) {

            String ruleId = extractRuleId(change.getAffectedGlobalId().value());

            // ADDED
            if (change instanceof NewObject) {

                addedIds.add(ruleId);

                RuleChangeReport report = reportMap
                        .computeIfAbsent(ruleId,
                                id -> new RuleChangeReport(id, ChangeType.ADDED));

                report.setRowNumber(newRowMap.get(ruleId));
            }

            // DELETED
            else if (change instanceof ObjectRemoved) {

                deletedIds.add(ruleId);

                RuleChangeReport report = reportMap
                        .computeIfAbsent(ruleId,
                                id -> new RuleChangeReport(id, ChangeType.DELETED));

                report.setRowNumber(oldRowMap.get(ruleId));
            }

            // VALUE CHANGE
            else if (change instanceof ValueChange vc) {

                ChangeType type;

                if (addedIds.contains(ruleId)) {
                    type = ChangeType.ADDED;
                } else if (deletedIds.contains(ruleId)) {
                    type = ChangeType.DELETED;
                } else {
                    type = ChangeType.MODIFIED;
                    modifiedIds.add(ruleId);
                }

                RuleChangeReport report = reportMap
                        .computeIfAbsent(ruleId,
                                id -> new RuleChangeReport(id, type));

                // assign row
                if (type == ChangeType.DELETED) {
                    report.setRowNumber(oldRowMap.get(ruleId));
                } else {
                    report.setRowNumber(newRowMap.get(ruleId));
                }

                report.addChange(new RuleChangeDetail(
                        vc.getPropertyName(),
                        vc.getLeft(),
                        vc.getRight()
                ));
            }
        }

        // NO CHANGE
        Set<String> oldIds = oldRules.stream().map(Rule::getRuleId).collect(Collectors.toSet());
        Set<String> newIds = newRules.stream().map(Rule::getRuleId).collect(Collectors.toSet());

        Set<String> common = new HashSet<>(oldIds);
        common.retainAll(newIds);

        for (String ruleId : common) {

            if (!modifiedIds.contains(ruleId)
                    && !addedIds.contains(ruleId)
                    && !deletedIds.contains(ruleId)) {

                noChangeIds.add(ruleId);

                RuleChangeReport report =
                        new RuleChangeReport(ruleId, ChangeType.NO_CHANGE);

                report.setRowNumber(newRowMap.get(ruleId));

                reportMap.put(ruleId, report);
            }
        }

        int noChange = noChangeIds.size();
        int added = addedIds.size();
        int deleted = deletedIds.size();
        int modified = modifiedIds.size();

        List<RuleChangeReport> reports = new ArrayList<>(reportMap.values());
        reports.sort(Comparator.comparing(RuleChangeReport::getRowNumber));

        return new ComparisonSummary(
                noChange,
                added,
                deleted,
                modified,
                reports
        );
    }

    private String extractRuleId(String globalId) {
        return globalId.substring(globalId.lastIndexOf("/") + 1);
    }
}