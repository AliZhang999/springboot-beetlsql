获取教师授课信息
===
select * from `开课情况` t where t.`授课教师工号`=#teacherCode#

获取教师论文信息
===
select * from `教师发表的论文情况` t where t.`工号`=#teacherCode#

获取教师专利信息
===
select * from `教师专利授权情况` t where t.`工号`=#teacherCode#

获取教师项目信息
===
select * from `教师主持科研项目情况` t where t.`工号`=#teacherCode#

获取教师奖励信息
===
select * from `教师获得科研奖励情况` t where t.`工号`=#teacherCode#

获取教师专著信息
===
select * from `教师出版专著情况` t where t.`工号`=#teacherCode#

获取教师教材信息
===
select * from `教师出版专著情况` t where t.`工号`=#teacherCode#

获取教师成果信息
===
select * from `教学成果奖` t where t.`教师工号`=#teacherCode#

获取教师个人信息
===
select j.`工号`,j.`姓名`,j.`性别`,j.`出生年月`,j.`任职状态`,j.`单位号`,j.`单位名称`,j.`聘期`,j.`学历`,j.`最高学位`,j.`专业技术职称`,z.`入校时间`,z.`学缘`,z.`任教类型`,z.`是否实验技术人员`,z.`是否双师型`,z.`是否工程背景`,z.`是否行业背景`,z.`学科类别`,z.`任教专业代码`,z.`任教专业名称`,z.`专业任教时间` from `教职工基本信息` j left join `在编教职工` z on j.`工号`=z.`工号` where j.`工号`=#teacherCode#

高层次人才统计
===
select x.专业代码,count(y.工号) 高层次人才总数,
count(distinct case when y.类型='中国科学院院士' then y.工号 else null end) 中国科学院院士,
count(distinct case when y.类型='中国工程院院士' then y.工号 else null end) 中国工程院院士,
count(distinct case when y.类型='引进海外高层次人才“千人计划”入选者' then y.工号 else null end) 引进海外高层次人才“千人计划”入选者,
count(distinct case when y.类型='长江学者特聘教授' then y.工号 else null end) 长江学者特聘教授,
count(distinct case when y.类型='国家杰出青年科学基金资助者' then y.工号 else null end) 国家杰出青年科学基金资助者,
count(distinct case when y.类型='国家优秀青年科学基金资助者' then y.工号 else null end) 国家优秀青年科学基金资助者,
count(distinct case when y.类型='新世纪优秀人才' then y.工号 else null end) 新世纪优秀人才,
count(distinct case when y.类型='教育部高校青年教师获奖者' then y.工号 else null end) 教育部高校青年教师获奖者,
count(distinct case when y.类型='青年“千人计划”' then y.工号 else null end) 青年“千人计划”,
count(distinct case when y.类型='百千万人才工程' then y.工号 else null end) 百千万人才工程,
count(distinct case when y.类型='万人计划' then y.工号 else null end) 万人计划,
count(distinct case when y.类型='国家级教学名师' then y.工号 else null end) 国家级教学名师,
count(distinct case when y.类型='省级高层次人才' then y.工号 else null end) 省级高层次人才,
count(distinct case when y.类型='省部级突出贡献专家' then y.工号 else null end) 省部级突出贡献专家,
count(distinct case when y.类型='省级教学名师入选者' then y.工号 else null end) 省级教学名师入选者,
count(distinct case when y.类型='外国科学院院士' then y.工号 else null end) 外国科学院院士,
count(distinct case when y.类型='中国社会科学院学部委员' then y.工号 else null end) 中国社会科学院学部委员 
from (
	select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select distinct a.任教专业代码,b.工号,c.类型 from 在编教职工 a 
	join 教职工基本信息 b on a.工号=b.工号 join 高层次人才 c on a.工号=c.工号
) y on x.专业代码=y.任教专业代码 group by x.专业代码