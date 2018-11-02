分专业专职教师统计
===
select x.专业代码,count(y.工号) 专职教师总数,
count(distinct case when y.任职状态='当年离职' then y.工号 else null end) 离职专职教师,
count(distinct case when y.任职状态='在职' then y.工号 else null end) 在职专职教师 
from (
	select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
) x left join (
	select distinct a.工号,a.任职状态,c.任教专业代码 from 教职工基本信息 a 
	join 在编教职工 c on a.工号=c.工号
	where a.单位号 not in(select 单位号 from 学校单位信息 where 单位类别='党政单位') 
	and a.工号 not in (select c.工号 from 相关管理人员基本信息 c where c.职务 like '%辅导员%') 
	and not exists(select 1 from 校领导基本信息 b where a.工号=b.工号) 
	and not exists(select 1 from 在编教职工 b where a.工号=b.工号 and (b.是否实验技术人员='是' or b.任教类型='无任教')) 
	and a.聘期=-1
) y on x.专业代码=y.任教专业代码 group by x.专业代码

分专业生源统计
===
select x.专业代码,y.省份,y.招生计划数,
y.实际录取数,y.第一志愿专业录取数,y.实际报到人数 
from (
	select distinct 校内代码,专业代码 from 专业基本情况 where 代码版本='2012'
) x join (
	select 校内代码,省份,招生计划数,实际录取数,第一志愿专业录取数,实际报到人数 
	from 近一届各专业招生报道情况
) y on x.校内代码=y.校内代码 group by x.校内代码,y.省份