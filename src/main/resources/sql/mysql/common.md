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

分专业师资结构统计
===
select x.专业代码,
count(distinct case when y.专业技术职称 in ('教授','其他正高级') then y.工号 else null end) 正高级职称,
count(distinct case when y.专业技术职称 in ('副教授','其他副高级') then y.工号 else null end) 副高级职称,
count(distinct case when y.专业技术职称 in ('教授','其他正高级','副教授','其他副高级') then y.工号 else null end) 高级职称,
count(distinct case when y.最高学位='博士' then y.工号 else null end) 博士学位 
from (
	select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	SELECT a.任教专业代码,a.工号,b.专业技术职称,b.最高学位 
	FROM 在编教职工 a left JOIN 教职工基本信息 b ON a.工号=b.工号
) y on x.专业代码=y.任教专业代码 group by x.专业代码

分专业高层次人才统计
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

分专业教学团队统计
===
select x.专业代码,
count(distinct case when y.类型='国家级教学团队' then y.工号 else null end) 国家级教学团队,
count(distinct case when y.类型='省部级教学团队' then y.工号 else null end) 省部级教学团队,
count(distinct case when y.类型='教育部创新团队' then y.工号 else null end) 教育部创新团队,
count(distinct case when y.类型='国家自然科学基金委创新研究群体' then y.工号 else null end) 国家自然科学基金委创新研究群体,
count(distinct case when y.类型='省级高层次研究团队' then y.工号 else null end) 省级高层次研究团队 
from (
	select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select distinct a.任教专业代码,a.工号,c.类型 
	from 在编教职工 a 
	join 教职工基本信息 b on a.工号=b.工号 
	join 高层次教学研究团队 c on a.工号=c.负责人工号
) y on x.专业代码=y.任教专业代码 group by x.专业代码

分专业留学生统计
===
select x.专业代码,
count(case when y.生源类别='留学生' then y.学号 else null end) 留学生数 
from (
	select 专业代码,校内代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select 校内专业代码,学号,生源类别 from 本科生基本情况 
) y on x.校内代码=y.校内专业代码 group by x.专业代码

分专业学生交流统计
===
select x.专业代码,
ifnull(sum(y.本专业外出交流学生人数境外),0) 本专业到境外学生数,
ifnull(sum(y.本专业外出交流学生人数境内),0) 本专业到境内学生数,
ifnull(sum(y.到本专业交流学生人数境外),0) 境外到本专业学生数,
ifnull(sum(y.到本专业交流学生人数境内),0) 境内到本专业学生数 
from (
	select 专业代码,校内代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select 校内专业代码,本专业外出交流学生人数境外,本专业外出交流学生人数境内,
	到本专业交流学生人数境外,到本专业交流学生人数境内 from 本科生交流情况 
) y on x.校内代码=y.校内专业代码 group by x.专业代码

分专业毕业生统计
===
select x.专业代码,ifnull(sum(y.应届毕业生数),0) 应届毕业生数,
ifnull(sum(y.应届生未按时毕业数),0) 应届未按时毕业生数 
from (
	select 专业代码,校内代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select 校内专业代码,应届毕业生数,应届生未按时毕业数 
	from 应届本科毕业生分专业毕业就业情况
) y on x.校内代码=y.校内专业代码 group by x.专业代码

分专业学生获奖统计
===
select x.专业代码,
count(case when y.获奖类别='国家级' then y.学号 else null end) 国家级获奖,
count(case when y.获奖类别='国家级' and y.获奖等级='一等奖' then y.学号 else null end) 国家级一等奖,
count(case when y.获奖类别='国家级' and y.获奖等级='二等奖' then y.学号 else null end) 国家级二等奖,
count(case when y.获奖类别='国家级' and y.获奖等级='三等奖' then y.学号 else null end) 国家级三等奖,
count(case when y.获奖类别='省部级' then y.学号 else null end) 省部级获奖,
count(case when y.获奖类别='省部级' and y.获奖等级='一等奖' then y.学号 else null end) 省部级一等奖,
count(case when y.获奖类别='省部级' and y.获奖等级='二等奖' then y.学号 else null end) 省部级二等奖,
count(case when y.获奖类别='省部级' and y.获奖等级='三等奖' then y.学号 else null end) 省部级三等奖 
from (
	select 专业代码,校内代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select b.校内专业代码,a.学号,a.获奖类别,a.获奖等级 
	from 学生获省级及以上各类竞赛奖励情况 a 
	join 本科生基本情况 b on a.学号=b.学号
) y on x.校内代码=y.校内专业代码 group by x.专业代码

分专业教师获得科研奖励统计
===
select x.专业代码,
count(distinct case when y.获奖等级='特等' then y.工号 else null end) 特等奖,
count(distinct case when y.获奖等级='一等' then y.工号 else null end) 一等奖,
count(distinct case when y.获奖等级='二等' then y.工号 else null end) 二等奖,
count(distinct case when y.获奖等级='三等' then y.工号 else null end) 三等奖,
count(distinct case when y.获奖类别='国家自然科学奖' then y.工号 else null end) 国家自然科学奖,
count(distinct case when y.获奖类别='国家技术发明奖' then y.工号 else null end) 国家技术发明奖,
count(distinct case when y.获奖类别='国家科技进步奖' then y.工号 else null end) 国家科技进步奖,
count(distinct case when y.获奖类别='国家级人文社科奖' then y.工号 else null end) 国家级人文社科奖,
count(distinct case when y.获奖类别='国家最高科学技术奖' then y.工号 else null end) 国家最高科学技术奖,
count(distinct case when y.获奖类别='教育部高校科研成果奖（科学技术、人文社科）' then y.工号 else null end) 教育部高校科研成果奖（科学技术、人文社科）,
count(distinct case when y.获奖类别='省（市、自治区）政府自然科学奖' then y.工号 else null end) 省（市、自治区）政府自然科学奖,
count(distinct case when y.获奖类别='省（市、自治区）政府技术发明奖' then y.工号 else null end) 省（市、自治区）政府技术发明奖,
count(distinct case when y.获奖类别='省（市、自治区）政府科技进步奖' then y.工号 else null end) 省（市、自治区）政府科技进步奖,
count(distinct case when y.获奖类别='省（市、自治区）政府哲学社科奖' then y.工号 else null end) 省（市、自治区）政府哲学社科奖,
count(distinct case when y.获奖类别='省（市、自治区）政府国际和国外奖励' then y.工号 else null end) 省（市、自治区）政府国际和国外奖励,
count(distinct case when y.获奖类别='国际科学技术合作奖' then y.工号 else null end) 国际科学技术合作奖,
count(distinct case when y.获奖类别='国家何梁何利科技奖' then y.工号 else null end) 国家何梁何利科技奖,
count(distinct case when y.获奖类别='国际和国外奖励' then y.工号 else null end) 国际和国外奖励 
from (
	select 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select distinct a.任教专业代码,a.工号,c.成果名称,c.获奖类别,c.获奖等级 
	from 在编教职工 a 
	join 教职工基本信息 b on a.工号=b.工号 
	join 教师获得科研奖励情况 c on a.工号=c.工号
) y on x.专业代码=y.任教专业代码 group by x.专业代码

分专业教师专利授权统计
===
select a.专业代码,
count(case when a.类型='发明专利' then a.工号 else null end) 发明专利,
count(case when a.类型='实用新型专利' then a.工号 else null end) 实用新型专利,
count(case when a.类型='外观专利' then a.工号 else null end) 外观专利,
count(case when a.类型='著作权' then a.工号 else null end) 著作权 
from (
	select distinct x.专业代码,y.工号,y.类型,y.授权号 
	from 专业基本情况 x 
	join (
		select distinct a.任教专业代码,a.工号,c.类型,c.授权号  
		from 在编教职工 a 
		join 教职工基本信息 b on a.工号=b.工号 
		join 教师专利授权情况 c on a.工号=c.工号
	) y on x.专业代码=y.任教专业代码 
	where 代码版本='2012'
) a group by a.专业代码