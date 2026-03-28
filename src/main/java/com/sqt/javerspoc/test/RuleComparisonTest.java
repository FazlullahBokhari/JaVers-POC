package com.sqt.javerspoc.test;

import com.sqt.javerspoc.dto.ComparisonSummary;
import com.sqt.javerspoc.dto.RuleChangeDetail;
import com.sqt.javerspoc.model.Rule;
import com.sqt.javerspoc.report.RuleChangeReport;
import com.sqt.javerspoc.service.ComparisonReportService;

import java.util.List;

public class RuleComparisonTest {

    public static void main(String[] args) {

        ComparisonSummary summary = getSummary();

        System.out.println("\n========= SUMMARY =========");
        System.out.println("No Change: " + summary.getNoChange());
        System.out.println("Added   : " + summary.getAdded());
        System.out.println("Deleted : " + summary.getDeleted());
        System.out.println("Modified: " + summary.getModified());

        System.out.println("\n========= DETAILS =========");

        for (RuleChangeReport report : summary.getReports()) {

            System.out.println("Row " + report.getRowNumber()
                    + " (RuleId=" + report.getRuleId() + ") "
                    + "[" + report.getChangeType() + "]");

            if (report.getChanges().isEmpty()) {
                System.out.println("  No field changes");
                System.out.println();
                continue;
            }

            for (RuleChangeDetail change : report.getChanges()) {

                switch (report.getChangeType()) {

                    case MODIFIED:
                        System.out.println("  - " + change.getField()
                                + " : " + change.getOldValue()
                                + " → " + change.getNewValue());
                        break;

                    case ADDED:
                        System.out.println("  - " + change.getField()
                                + " : " + change.getNewValue());
                        break;

                    case DELETED:
                        System.out.println("  - " + change.getField()
                                + " : " + change.getOldValue());
                        break;
                }
            }

            System.out.println();
        }

    }

    private static ComparisonSummary getSummary() {

        ComparisonReportService service = new ComparisonReportService();

        // 🔵 OLD RULES (20 records)
        List<Rule> oldRules = List.of(
                new Rule("1", "Rule A", "spel1", "opt1", "cat1", 100.0, 1, "ACTIVE", "Admin", "Admin"),
                new Rule("2", "Rule B", "spel2", "opt2", "cat2", 200.0, 2, "ACTIVE", "Admin", "Admin"),
                new Rule("3", "Rule C", "spel3", "opt3", "cat3", 300.0, 3, "ACTIVE", "Admin", "Admin"),
                new Rule("4", "Rule D", "spel4", "opt4", "cat4", 400.0, 4, "ACTIVE", "Admin", "Admin"),
                new Rule("5", "Rule E", "spel5", "opt5", "cat5", 500.0, 5, "ACTIVE", "Admin", "Admin"),
                new Rule("6", "Rule F", "spel6", "opt6", "cat6", 600.0, 6, "ACTIVE", "Admin", "Admin"),
                new Rule("7", "Rule G", "spel7", "opt7", "cat7", 700.0, 7, "ACTIVE", "Admin", "Admin"),
                new Rule("8", "Rule H", "spel8", "opt8", "cat8", 800.0, 8, "ACTIVE", "Admin", "Admin"),
                new Rule("9", "Rule I", "spel9", "opt9", "cat9", 900.0, 9, "ACTIVE", "Admin", "Admin"),
                new Rule("10", "Rule J", "spel10", "opt10", "cat10", 1000.0, 10, "ACTIVE", "Admin", "Admin"),
                new Rule("11", "Rule K", "spel11", "opt11", "cat11", 1100.0, 11, "ACTIVE", "Admin", "Admin"),
                new Rule("12", "Rule L", "spel12", "opt12", "cat12", 1200.0, 12, "ACTIVE", "Admin", "Admin"),
                new Rule("13", "Rule M", "spel13", "opt13", "cat13", 1300.0, 13, "ACTIVE", "Admin", "Admin"),
                new Rule("14", "Rule N", "spel14", "opt14", "cat14", 1400.0, 14, "ACTIVE", "Admin", "Admin"),
                new Rule("15", "Rule O", "spel15", "opt15", "cat15", 1500.0, 15, "ACTIVE", "Admin", "Admin"),
                new Rule("16", "Rule P", "spel16", "opt16", "cat16", 1600.0, 16, "ACTIVE", "Admin", "Admin"),
                new Rule("17", "Rule Q", "spel17", "opt17", "cat17", 1700.0, 17, "ACTIVE", "Admin", "Admin"),
                new Rule("18", "Rule R", "spel18", "opt18", "cat18", 1800.0, 18, "ACTIVE", "Admin", "Admin"),
                new Rule("19", "Rule S", "spel19", "opt19", "cat19", 1900.0, 19, "ACTIVE", "Admin", "Admin"),
                new Rule("20", "Rule T", "spel20", "opt20", "cat20", 2000.0, 20, "ACTIVE", "Admin", "Admin")
        );

        // 🟢 NEW RULES (mix of changes)
        List<Rule> newRules = List.of(

                // 🔥 MODIFIED (1–5)
                new Rule("1", "Rule A", "spel1", "opt1", "cat1", 150.0, 1, "INACTIVE", "Admin", "User1"),
                new Rule("2", "Rule B", "spel2", "opt2", "cat2", 250.0, 2, "ACTIVE", "Admin", "User2"),
                new Rule("3", "Rule C", "spel3", "opt3", "cat3", 300.0, 3, "INACTIVE", "Admin", "User3"),
                new Rule("4", "Rule D Updated", "spel4", "opt4", "cat4", 450.0, 4, "ACTIVE", "Admin", "User4"),
                new Rule("5", "Rule E", "spel5", "opt5", "cat5", 550.0, 5, "INACTIVE", "Admin", "User5"),

                // ✅ NO CHANGE (6–10)
                new Rule("6", "Rule F", "spel6", "opt6", "cat6", 600.0, 6, "ACTIVE", "Admin", "Admin"),
                new Rule("7", "Rule G", "spel7", "opt7", "cat7", 700.0, 7, "ACTIVE", "Admin", "Admin"),
                new Rule("8", "Rule H", "spel8", "opt8", "cat8", 800.0, 8, "ACTIVE", "Admin", "Admin"),
                new Rule("9", "Rule I", "spel9", "opt9", "cat9", 900.0, 9, "ACTIVE", "Admin", "Admin"),
                new Rule("10", "Rule J", "spel10", "opt10", "cat10", 1000.0, 10, "ACTIVE", "Admin", "Admin"),

                // ❌ DELETED (11–13 missing)

                // 🔥 ADDED (21–25)
                new Rule("21", "Rule U", "spel21", "opt21", "cat21", 2100.0, 21, "ACTIVE", "Admin", "Admin"),
                new Rule("22", "Rule V", "spel22", "opt22", "cat22", 2200.0, 22, "ACTIVE", "Admin", "Admin"),
                new Rule("23", "Rule W", "spel23", "opt23", "cat23", 2300.0, 23, "ACTIVE", "Admin", "Admin"),
                new Rule("24", "Rule X", "spel24", "opt24", "cat24", 2400.0, 24, "ACTIVE", "Admin", "Admin"),
                new Rule("25", "Rule Y", "spel25", "opt25", "cat25", 2500.0, 25, "ACTIVE", "Admin", "Admin")
        );

        return service.compare(oldRules, newRules);
    }
}