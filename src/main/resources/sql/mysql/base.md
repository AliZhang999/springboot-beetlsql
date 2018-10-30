selectAll
===
select * from user where name=#name#

allSubject
===
select distinct 专业代码,专业名称 from 专业基本情况 where 代码版本='2012' order by 专业代码

subjectAssessment
===
select a.assessment_result from subject_assessment a where subject_code = #code#

indicatorData
===
select data from #text(tableName)# where year = #year# and schoolCode = #schoolCode#