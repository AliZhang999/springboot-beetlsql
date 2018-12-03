subjectAssessment
===
    select a.assessment_result from subject_assessment a where subject_code = #code#

subjectType
===
    select a.subjectCode,a.subjectName from subject_type a

subjectList
===
    select distinct subject_code,subject_name from subject_major_info;

newSubjectAssessment
===
    select times 评估批次,ranking 排名,ranking_rate 排名百分位,total_school 参评高校数,assessment_level 评估等级
    from subject_assessment where subject_code = #subjectCode# group by times;

newSubjectInfo
===
    select subject_code code,subject_name name,authorization_level level from subject_info where subject_code=#subjectCode#

new高层次人才统计
===
    select a.subject_code,a.subject_name,count(c.teacher_code) 人才数
    from subject_info a
    left join subject_major_info b on a.subject_code=b.subject_code
    left join high_level_talents c on b.major_code=c.major_code and c.year=#year# and c.schoolcode=#schoolcode#
    group by a.subject_code order by 人才数 desc

new各学科第四轮学科排名情况
===
    select b.subject_code 学科代码,b.subject_name 学科名称,
    a.assessment_level 评估等级,a.total_school 参评高校数,
    convert(a.ranking_rate*100,decimal(10,2)) 排名百分位,
    a.ranking 排名 from subject_assessment a
    left join subject_info b on a.subject_code=b.subject_code
    where a.times='第四轮'

new外校列表
===
    select schoolcode,schoolname from fschool

new各学科高层次人才类型
===
    select * from (
      select a.subject_code,a.subject_name,c.type
      from subject_info a
      left join subject_major_info b on a.subject_code=b.subject_code
      left join high_level_talents c on b.major_code=c.major_code and c.schoolcode=10593
      where a.subject_code=#subjectCode#
      group by a.subject_code,c.type
    ) a where a.type is not null
    
new各学科各类型高层次人才三年数据
===
    select a.subject_code,a.subject_name,c.type,
    count(case when c.year=2018 then c.teacher_code else null end ) 人才数2018,
    count(case when c.year=2017 then c.teacher_code else null end ) 人才数2017,
    count(case when c.year=2016 then c.teacher_code else null end ) 人才数2016
    from subject_info a
    left join subject_major_info b on a.subject_code=b.subject_code
    left join high_level_talents c on b.major_code=c.major_code and c.schoolcode=10593 and c.type=#type#
    where a.subject_code=#subjectCode#
    group by a.subject_code

new单个学科分类型高层次三年数据统计
===
    select * from (
      select a.subject_code,a.subject_name,c.type,count(c.teacher_code) 人才数,
      count(case when c.year=2018 then c.teacher_code else null end ) 人才数2018,
      count(case when c.year=2017 then c.teacher_code else null end ) 人才数2017,
      count(case when c.year=2016 then c.teacher_code else null end ) 人才数2016
      from subject_info a
      left join subject_major_info b on a.subject_code=b.subject_code
      left join high_level_talents c on b.major_code=c.major_code and c.schoolcode=10593
      where a.subject_code=#subjectCode#
      group by c.type
    ) a where a.type is not null

new单个学科高层次人才分类统计
===
    select * from (
      select a.subject_code,a.subject_name,c.type,count(c.teacher_code) 人才数
      from subject_info a
      left join subject_major_info b on a.subject_code=b.subject_code
      left join high_level_talents c on b.major_code=c.major_code and c.schoolcode=#schoolcode#
      where a.subject_code=#subjectCode# and c.year=#year#
      group by c.type
    ) a where a.type is not null

new本校学科外校学科高层次人才分类统计
===
    select a.type,a.year,a.人才数 as 本校数据,ifnull(b.人才数,0) as 标杆数据 from (
      select * from (
        select a.subject_code,a.subject_name,c.type,c.year,count(c.teacher_code) 人才数
        from subject_info a
        left join subject_major_info b on a.subject_code=b.subject_code
        left join high_level_talents c on b.major_code=c.major_code and c.schoolcode=10593
        where a.subject_code=#leftSubjectCode#
        group by c.type,c.year
      ) a where a.type is not null
    ) a
    left join (
      select * from (
        select a.subject_code,a.subject_name,c.type,c.year,count(c.teacher_code) 人才数
        from subject_info a
        left join subject_major_info b on a.subject_code=b.subject_code
        left join high_level_talents c on b.major_code=c.major_code and c.schoolcode=#rightSchoolCode#
        where a.subject_code=#rightSubjectCode#
        group by c.type,c.year
      ) a where a.type is not null
    ) b on a.type=b.type and a.year=b.year order by a.type,a.year desc,a.人才数 desc