## POC FLOW
 Problem Statement

We need to compare two datasets (Old Rules vs New Rules)
and identify:

 - ✅ Added rules
 - ❌ Deleted rules
 - 🔄 Modified rules
 - ➖ No Change

👉 Similar to how Git diff works.

#### 🧠 2. High-Level Flow
      Old Rules (DB)    New Rules (Excel/API)
           ↓                  ↓
         → Javers Comparison Engine
                    ↓
            Raw Changes (Diff)
                    ↓
            Custom Classification Logic
                    ↓
            Business-Friendly Report (Row-wise)
#### ⚙️ 3. Step-by-Step Internal Flow
 - 🔹 Step 1: Input Data
  - List<Rule> oldRules
  - List<Rule> newRules

👉 Source:
 - Old → DB
 - New → Excel (future)
#### 🔹 Step 2: Javers Comparison
 - Diff diff = javers.compareCollections(oldRules, newRules, Rule.class);

👉 Javers gives:

 - NewObject → ADDED
 - ObjectRemoved → DELETED
 - ValueChange → FIELD CHANGE


### ========= SUMMARY =========
 - No Change: 5
 - Added   : 5
 - Deleted : 10
 - Modified: 5

#### ========= DETAILS =========
Row 1 (RuleId=1) [MODIFIED]
- threshold : 100.0 → 150.0
- status : ACTIVE → INACTIVE
- updatedBy : Admin → User1

Row 2 (RuleId=2) [MODIFIED]
- threshold : 200.0 → 250.0
- updatedBy : Admin → User2

Row 3 (RuleId=3) [MODIFIED]
- status : ACTIVE → INACTIVE
- updatedBy : Admin → User3

Row 4 (RuleId=4) [MODIFIED]
- ruleName : Rule D → Rule D Updated
- threshold : 400.0 → 450.0
- updatedBy : Admin → User4

Row 5 (RuleId=5) [MODIFIED]
- threshold : 500.0 → 550.0
- status : ACTIVE → INACTIVE
- updatedBy : Admin → User5

Row 6 (RuleId=6) [NO_CHANGE]
No field changes

Row 7 (RuleId=7) [NO_CHANGE]
No field changes

Row 8 (RuleId=8) [NO_CHANGE]
No field changes

Row 9 (RuleId=9) [NO_CHANGE]
No field changes

Row 10 (RuleId=10) [NO_CHANGE]
No field changes

Row 11 (RuleId=11) [DELETED]
- ruleId : 11
- ruleName : Rule K
- spelRule : spel11
- resolutionOptions : opt11
- categories : cat11
- threshold : 1100.0
- priority : 11
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 11 (RuleId=21) [ADDED]
- ruleId : 21
- ruleName : Rule U
- spelRule : spel21
- resolutionOptions : opt21
- categories : cat21
- threshold : 2100.0
- priority : 21
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 12 (RuleId=22) [ADDED]
- ruleId : 22
- ruleName : Rule V
- spelRule : spel22
- resolutionOptions : opt22
- categories : cat22
- threshold : 2200.0
- priority : 22
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 12 (RuleId=12) [DELETED]
- ruleId : 12
- ruleName : Rule L
- spelRule : spel12
- resolutionOptions : opt12
- categories : cat12
- threshold : 1200.0
- priority : 12
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 13 (RuleId=23) [ADDED]
- ruleId : 23
- ruleName : Rule W
- spelRule : spel23
- resolutionOptions : opt23
- categories : cat23
- threshold : 2300.0
- priority : 23
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 13 (RuleId=13) [DELETED]
- ruleId : 13
- ruleName : Rule M
- spelRule : spel13
- resolutionOptions : opt13
- categories : cat13
- threshold : 1300.0
- priority : 13
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 14 (RuleId=24) [ADDED]
- ruleId : 24
- ruleName : Rule X
- spelRule : spel24
- resolutionOptions : opt24
- categories : cat24
- threshold : 2400.0
- priority : 24
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 14 (RuleId=14) [DELETED]
- ruleId : 14
- ruleName : Rule N
- spelRule : spel14
- resolutionOptions : opt14
- categories : cat14
- threshold : 1400.0
- priority : 14
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 15 (RuleId=25) [ADDED]
- ruleId : 25
- ruleName : Rule Y
- spelRule : spel25
- resolutionOptions : opt25
- categories : cat25
- threshold : 2500.0
- priority : 25
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 15 (RuleId=15) [DELETED]
- ruleId : 15
- ruleName : Rule O
- spelRule : spel15
- resolutionOptions : opt15
- categories : cat15
- threshold : 1500.0
- priority : 15
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 16 (RuleId=16) [DELETED]
- ruleId : 16
- ruleName : Rule P
- spelRule : spel16
- resolutionOptions : opt16
- categories : cat16
- threshold : 1600.0
- priority : 16
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 17 (RuleId=17) [DELETED]
- ruleId : 17
- ruleName : Rule Q
- spelRule : spel17
- resolutionOptions : opt17
- categories : cat17
- threshold : 1700.0
- priority : 17
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 18 (RuleId=18) [DELETED]
- ruleId : 18
- ruleName : Rule R
- spelRule : spel18
- resolutionOptions : opt18
- categories : cat18
- threshold : 1800.0
- priority : 18
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 19 (RuleId=19) [DELETED]
- ruleId : 19
- ruleName : Rule S
- spelRule : spel19
- resolutionOptions : opt19
- categories : cat19
- threshold : 1900.0
- priority : 19
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

Row 20 (RuleId=20) [DELETED]
- ruleId : 20
- ruleName : Rule T
- spelRule : spel20
- resolutionOptions : opt20
- categories : cat20
- threshold : 2000.0
- priority : 20
- status : ACTIVE
- createdBy : Admin
- updatedBy : Admin

