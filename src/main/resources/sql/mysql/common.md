获取教师列表
===
select distinct a.工号,a.姓名 from 教职工基本信息 a left join 在编教职工 b on a.工号=b.工号

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
select x.专业代码,count(distinct y.工号) 研究人员数,
count(distinct case when y.专业技术职称 in ('教授','其他正高级') then y.工号 else null end) 正高级研究人员,
count(distinct case when y.专业技术职称 in ('副教授','其他副高级') then y.工号 else null end) 副高级研究人员,
count(distinct case when y.专业技术职称='讲师' then y.工号 else null end) 讲师研究人员,
count(distinct case when y.专业技术职称='助教' then y.工号 else null end) 助教研究人员,
count(distinct case when y.专业技术职称='其他中级' then y.工号 else null end) 中级研究人员,
count(distinct case when y.专业技术职称='其他初级' then y.工号 else null end) 初级研究人员,
count(distinct case when y.类型='国家级教学团队' then y.工号 else null end) 国家级教学团队,
count(distinct case when y.类型='省部级教学团队' then y.工号 else null end) 省部级教学团队,
count(distinct case when y.类型='教育部创新团队' then y.工号 else null end) 教育部创新团队,
count(distinct case when y.类型='国家自然科学基金委创新研究群体' then y.工号 else null end) 国家自然科学基金委创新研究群体,
count(distinct case when y.类型='省级高层次研究团队' then y.工号 else null end) 省级高层次研究团队 
from (
	select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select distinct a.任教专业代码,a.工号,c.类型,b.专业技术职称 
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
	left join (
		select distinct a.任教专业代码,a.工号,c.类型,c.授权号  
		from 在编教职工 a 
		join 教职工基本信息 b on a.工号=b.工号 
		join 教师专利授权情况 c on a.工号=c.工号
	) y on x.专业代码=y.任教专业代码 
	where 代码版本='2012'
) a group by a.专业代码

分专业教师主编专业教材统计
===
select x.专业代码,
count(distinct case when y.教材入选情况='国家级规划教材' then y.工号 else null end) 国家级规划教材,
count(distinct case when y.教材入选情况='省部级规划教材' then y.工号 else null end) 省部级规划教材,
count(distinct case when y.教材入选情况='国家级精品教材' then y.工号 else null end) 国家级精品教材,
count(distinct case when y.教材入选情况='省部级精品教材' then y.工号 else null end) 省部级精品教材 
from (
	select 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select distinct a.任教专业代码,a.工号,c.ISBN,c.教材入选情况 
	from 在编教职工 a 
	join 教职工基本信息 b on a.工号=b.工号 
	join 教师主编本专业教材情况 c on a.工号=c.工号
) y on x.专业代码=y.任教专业代码 group by x.专业代码

分专业教师主持科研项目统计
===
select a.专业代码,
count(case when a.项目性质='纵向项目' then a.工号 else null end) 纵向项目,
count(case when a.项目性质='横向项目' then a.工号 else null end) 横向项目,
count(case when a.项目性质='纵向项目' and a.纵向项目类别='科技部项目' then a.工号 else null end) 科技部项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='国家重大科技专项' then a.工号 else null end) 国家重大科技专项,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='国家自然基金项目' then a.工号 else null end) 国家自然基金项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='国家社科基金项目' then a.工号 else null end) 国家社科基金项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='国家艺术基金' then a.工号 else null end) 国家艺术基金,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='国防/军队重要科研项目' then a.工号 else null end) 国防军队重要科研项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='境外合作科研项目' then a.工号 else null end) 境外合作科研项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='国家级全国教育科学规划课题' then a.工号 else null end) 国家级全国教育科学规划课题,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='教育部级全国教育科学规划课题' then a.工号 else null end) 教育部级全国教育科学规划课题,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='教育部人文社会科学研究项目' then a.工号 else null end) 教育部人文社会科学研究项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='部委级项目' then a.工号 else null end) 部委级项目,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='省教育厅科研立项' then a.工号 else null end) 省教育厅科研立项,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='省科技厅立项' then a.工号 else null end) 省科技厅立项,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='省自然科学基金' then a.工号 else null end) 省自然科学基金,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='省哲学/社科基金' then a.工号 else null end) 省哲学社科基金,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='省级其它' then a.工号 else null end) 省级其它,
count(case when a.项目性质='纵向项目' and  a.纵向项目类别='无' then a.工号 else null end) 无 
from (
	select distinct x.专业代码,y.任教专业代码,y.工号,y.项目名称,y.项目性质,y.纵向项目类别,y.立项编号  
	from (
		select 专业代码 from 专业基本情况 where 代码版本='2012'
	) x left join (
		select distinct a.任教专业代码,a.工号,c.项目名称,c.项目性质,c.纵向项目类别,c.立项编号 
		from 在编教职工 a 
		join 教职工基本信息 b on a.工号=b.工号 
		join 教师主持科研项目情况 c on a.工号=c.工号
	) y on x.专业代码=y.任教专业代码
) a group by a.专业代码

分专业教师出版专著统计
===
select a.专业代码,
count(case when a.专著类别='专著' then a.工号 else null end) 专著,
count(case when a.专著类别='译著' then a.工号 else null end) 译著,
count(case when a.专著类别='辞书' then a.工号 else null end) 辞书 
from (
	select distinct x.专业代码,y.任教专业代码,y.工号,y.专著名称,y.ISBN,y.专著类别 
	from (
		select 专业代码 from 专业基本情况 where 代码版本='2012'
	) x left join (
		select distinct a.任教专业代码,a.工号,c.专著名称,c.ISBN,c.专著类别 
		from 在编教职工 a 
		join 教职工基本信息 b on a.工号=b.工号 
		join 教师出版专著情况 c on a.工号=c.工号
	) y on x.专业代码=y.任教专业代码
) a group by a.专业代码

分专业教师发表论文统计
===
select a.专业代码,count(a.工号) 论文总数,sum(a.他引次数) 他引次数,
count(case when a.作者类型='第一作者' then a.工号 else null end) 第一作者,
count(case when a.作者类型='通讯作者' then a.工号 else null end) 通讯作者,
count(case when a.论文类别='科研' then a.工号 else null end) 科研论文,
count(case when a.论文类别='教研' then a.工号 else null end) 教研论文,
count(case when a.收录情况='SCI' then a.工号 else null end) SCI,
count(case when a.收录情况='SSCI' then a.工号 else null end) SSCI,
count(case when a.收录情况='EI' then a.工号 else null end) EI,
count(case when a.收录情况='CPCI' then a.工号 else null end) CPCI,
count(case when a.收录情况='A&HCI' then a.工号 else null end) AHCI,
count(case when a.收录情况='CSCD' then a.工号 else null end) CSCD,
count(case when a.收录情况='CSSCI' then a.工号 else null end) CSSCI,
count(case when a.收录情况='ISTP' then a.工号 else null end) ISTP,
count(case when a.收录情况='北大中文核心期刊' then a.工号 else null end) 北大中文核心期刊,
count(case when a.是否与行业联合发表='是' then a.工号 else null end) 是行业联合发表,
count(case when a.是否与行业联合发表='否' then a.工号 else null end) 未与行业联合发表,
count(case when a.是否与地方联合发表='是' then a.工号 else null end) 是与行业联合发表,
count(case when a.是否与地方联合发表='否' then a.工号 else null end) 未与行业联合发表,
count(case when a.是否与国际联合发表='是' then a.工号 else null end) 是与国际联合发表,
count(case when a.是否与国际联合发表='否' then a.工号 else null end) 未与国际联合发表,
count(case when a.是否跨学科论文='是' then a.工号 else null end) 是跨学科论文,
count(case when a.是否跨学科论文='否' then a.工号 else null end) 非跨学科论文 
from (
	select distinct x.专业代码,y.任教专业代码,y.工号,y.作者类型,y.论文名称,y.论文类别,y.发表期刊,
	y.收录情况,y.他引次数,y.是否与行业联合发表,y.是否与地方联合发表,y.是否与国际联合发表,y.是否跨学科论文  
	from (
		select 专业代码 from 专业基本情况 where 代码版本='2012'
	) x join (
		select distinct a.任教专业代码,a.工号,c.作者类型,c.论文名称,c.论文类别,c.发表期刊,c.收录情况,
		c.他引次数,c.是否与行业联合发表,c.是否与地方联合发表,c.是否与国际联合发表,c.是否跨学科论文 
		from 在编教职工 a 
		join 教职工基本信息 b on a.工号=b.工号 
		join 教师发表的论文情况 c on a.工号=c.工号
	) y on x.专业代码=y.任教专业代码
) a group by a.专业代码

校内专业列表
===
select s.校内代码,k.校内名称 from 专业基本情况 s
left join 校内专业大类 k on s.校内代码=k.校内代码

专业基本信息
===
select s.专业代码,s.校内代码,k.校内名称,
case when d.专业带头人姓名 is null then '' else d.专业带头人姓名 end as 专业带头人姓名,
case when j.性别 is null then '' else j.性别 end as 带头人性别,
s.所属单位名称,s.允许修业年限,s.授予学位门类,s.专业设置年份,s.优势专业类型,s.学制,s.招生状态
from 专业基本情况 s
left join 校内专业大类 k on s.校内代码=k.校内代码
left join 专业培养计划表 d on s.校内代码=d.校内专业代码
left join 教职工基本信息 j on d.专业带头人工号=j.工号
where s.校内代码=#majorCode#

专业内教职工列表
===
select distinct d.*
from 开课情况 a,专业课教学实施情况 b,专业基本情况 c,教职工基本信息 d
where a.开课号=b.开课号 and c.校内代码=b.校内专业代码
and d.工号=a.授课教师工号 and c.校内代码=#majorCode#

专业内教学成果列表
===
select distinct * from 教学成果奖 s where
exists(select 1 from 开课情况 a,专业课教学实施情况 b,教职工基本信息 d
where a.开课号 = b.开课号 and d.工号 = a.授课教师工号
and b.校内专业代码=#majorCode# and d.工号=s.教师工号)

专业内开设课程列表
===
select distinct * from 开课情况 a where
exists (select 1 from 专业课教学实施情况 b
where a.开课号=b.开课号 and b.校内专业代码=#majorCode#)

专业内实习基地列表
===
select s.基地名称,s.建立时间,s.每次可接纳学生数,s.地址,s.当年接纳学生总数
from 校外实习实训基地 s where s.校内专业代码=#majorCode#

专业内本科生列表
===
select a.学号,a.学生姓名,a.性别,a.年级,a.入学年份
from 本科生基本情况 a where  a.校内专业代码=#majorCode#

专业内各类竞赛列表
===
select s.学号,s.学生姓名,s.竞赛名称,s.竞赛名称,s.获奖类别,s.获奖等级
from 学生获省级及以上各类竞赛奖励情况 s where
exists (select 1 from 本科生基本情况 a where s.学号=a.学号 and a.校内专业代码=#majorCode#)

专业内专业比赛列表
===
select s.学号,s.学生姓名,s.比赛名称,s.赛事类别,s.获奖时间,s.获奖等级,s.主办单位,s.学生排名
from 学生获专业比赛奖励情况 s where
exists (select 1 from 本科生基本情况 a where s.学号=a.学号 and a.校内专业代码=#majorCode#)

专业内学术论文列表
===
select distinct s.工号,s.教师姓名,s.论文名称,s.发表期刊,s.发表时间,s.收录情况
from 教师发表的论文情况 s where
exists (select 1 from 开课情况 a,专业课教学实施情况 b,教职工基本信息 d
where a.开课号=b.开课号 and d.工号=a.授课教师工号
and b.校内专业代码=#majorCode# and d.工号=s.工号)

获取学科平台列表
===
select a.学科代码,
count(case when a.级别='国家级' then a.中心名称 else null end ) 国家级,
count(case when a.级别='省部级' then a.中心名称 else null end ) 省部级
from 实验教学示范中心 a group by a.学科代码

分专业学生情况
===
select a.专业代码,count(distinct a.学号) as 本科生数,
count(distinct case when a.生源类别='留学生' then a.学号 else null end) as 留学生,
count(distinct case when (a.生源类别='港澳台学生' or a.生源类别='港澳台侨学生') then a.学号 else null end) as 港澳台学生,
count(distinct case when (#year#-a.年级)=0 then a.学号 else null end) as 一年级,
count(distinct case when (#year#-a.年级)=1 then a.学号 else null end) as 二年级,
count(distinct case when (#year#-a.年级)=2 then a.学号 else null end) as 三年级,
count(distinct case when (#year#-a.年级)=3 then a.学号 else null end) as 四年级,
count(distinct case when (#year#-a.年级)>3 then a.学号 else null end) as 其他年级
from (
    select a.校内专业代码,a.校内专业名称,a.学号,a.学生姓名,a.生源类别,a.年级,b.专业代码
    from 本科生基本情况 a
    join 专业基本情况 b on a.校内专业代码=b.校内代码
    where b.代码版本='2012'
) a group by a.专业代码

分专业在校成果
===
select a.专业代码,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创新') then a.学号 else null end ) as 国家级创新,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创新' and (#year#-年级)=0) then a.学号 else null end ) as 一年级国家级创新,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创新' and (#year#-年级)=1) then a.学号 else null end ) as 二年级国家级创新,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创新' and (#year#-年级)=2) then a.学号 else null end ) as 三年级国家级创新,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创新' and (#year#-年级)=3) then a.学号 else null end ) as 四年级国家级创新,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创业') then a.学号 else null end ) as 国家级创业,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创业' and (#year#-年级)=0) then a.学号 else null end ) as 一年级国家级创业,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创业' and (#year#-年级)=1) then a.学号 else null end ) as 二年级国家级创业,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创业' and (#year#-年级)=2) then a.学号 else null end ) as 三年级国家级创业,
count(distinct case when (a.项目级别='国家级' and a.项目类别='创业' and (#year#-年级)=3) then a.学号 else null end ) as 四年级国家级创业,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创新') then a.学号 else null end ) as 省部级创新,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创新' and (#year#-年级)=0) then a.学号 else null end ) as 一年级省部级创新,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创新' and (#year#-年级)=1) then a.学号 else null end ) as 二年级省部级创新,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创新' and (#year#-年级)=2) then a.学号 else null end ) as 三年级省部级创新,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创新' and (#year#-年级)=3) then a.学号 else null end ) as 四年级省部级创新,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创业') then a.学号 else null end ) as 省部级创业,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创业' and (#year#-年级)=0) then a.学号 else null end ) as 一年级省部级创业,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创业' and (#year#-年级)=1) then a.学号 else null end ) as 二年级省部级创业,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创业' and (#year#-年级)=2) then a.学号 else null end ) as 三年级省部级创业,
count(distinct case when (a.项目级别='省部级' and a.项目类别='创业' and (#year#-年级)=3) then a.学号 else null end ) as 四年级省部级创业
from (
  select a.项目级别,a.项目类别,a.项目名称,b.学号,b.学生姓名,b.年级,c.专业代码
  from 学生参加大学生创新创业训练计划情况 a
  join 本科生基本情况 b on a.学号=b.学号
  join 专业基本情况 c on b.校内专业代码=c.校内代码
  where c.代码版本='2012'
) a group by a.专业代码
