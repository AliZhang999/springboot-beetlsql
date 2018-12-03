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
        where a.单位号 not in(select 单位号 from 学校单位信息 where 单位职能 is not null) 
        and not exists(select 1 from 校领导基本信息 b where a.工号=b.工号) 
        and not exists(select 1 from 相关管理人员基本信息 c where a.工号=c.工号) 
        and not exists(select 1 from 在编教职工 b where a.工号=b.工号 and b.是否实验技术人员='是') 
        and a.单位号 not like 'FSYY%' and a.聘期=-1
    ) y on x.专业代码=y.任教专业代码 group by x.专业代码

分专业生源统计
===
    select x.专业代码,y.省份,y.招生计划数,
    y.实际录取数,y.第一志愿专业录取数,y.实际报到人数 
    from (
        select distinct 校内代码,专业代码 from 专业基本情况 where 代码版本='2012'
    ) x join (
        select 专业代码,省份,招生计划数,实际录取数,第一志愿专业录取数,实际报到人数 
        from 近一届各专业招生报道情况
    ) y on x.校内代码=y.专业代码 group by x.专业代码,y.省份

首页教学成果
===
    select *,0 牵头建设国家级实践育人创新创业基地 from (
      select sum(a.教学成果奖国家级) 国家级教学成果奖 from 教学管理人员成果 a
    ) a,(
      select count(case when a.级别='国家级' then a.立项编号或批准文号 else null end) 国家级教育教学研究与改革项目
      from 教育教学研究与改革项目 a
    ) b,(
      select count(case when a.项目级别='国家级' then 1 else null end) 国家级本科教学工程,
      count(case when a.项目级别='省部级' then 1 else null end) 省部级本科教学工程 from 省级及以上本科教学工程项目情况 a
    ) c,(
      select count(case when a.类型='人才培养模式创新实验区' then 1 else null end) 人才培养模式创新实验区,
      count(case when a.类型='国家级教学基地' then 1 else null end) 国家级教学基地 from 人才培养模式创新实验项目 a
    ) d

首页师资情况
===
    select *,d.折合学生数/(g.专任教师数+h.其他教师总数) 生师比,g.博硕专任教师数/g.专任教师数*100 博硕覆盖率,e.上课教授数/f.教授总数*100 教授上台讲课比例 from (
      select count(1) 教职工人数 from 教职工基本信息 b
    ) a,(
      select count(case when b.是否实验技术人员='是' then 1 else null end) 实验师资 from 在编教职工 b
    ) b,(
      select count(distinct b.工号) 高层次人才总数 from 高层次人才 b
    ) c,(
      select sum(b.普通本科学生数+b.普通高职学生数+b.硕士研究生数全日制*1.5+b.博士研究生数全日制*2+b.留学生数*3+b.普通预科生数+b.进修生数+b.成人脱产学生数+b.夜大学生数*0.3+b.函授学生数*0.1) 折合学生数
      from 学生数量基本情况 b
    ) d,(
      select count(distinct b.工号) 上课教授数 from 开课情况 a
      left join 教职工基本信息 b on a.授课教师工号=b.工号
      where b.专业技术职称='教授'
    ) e,(
        select count(distinct b.工号) 教授总数 from 教职工基本信息 b where b.专业技术职称='教授'
    ) f,(
      select sum(专任教师数) 专任教师数,sum(博硕专任教师数) 博硕专任教师数
      from ( 
        select count(distinct b.工号) 专任教师数,
        count(distinct case when c.最高学位 in('博士','硕士') then b.工号 else null end) 博硕专任教师数
        from 在编教职工 b
        left join 教职工基本信息 c on b.工号=c.工号
        where c.单位号 not like 'fsyy%'
        and c.任职状态='在职' and b.是否实验技术人员='否'
        and not exists(select 1 from 校领导基本信息 e where b.工号=e.工号)
        and not exists(select 1 from 相关管理人员基本信息 f where b.工号=f.工号)
        and not exists(select 1 from 学校单位信息 d where d.单位号=c.单位号 and d.单位职能 is not null)
      ) a
    ) g,(
      select sum(直属医院具有医师职称的医生数*0.15+y.一般外聘教师数*0.5+外聘的非直属医院的教师数*0.5*0.15) 其他教师总数
      from (
        select count(distinct case when b.聘期=-1 and not exists(select 1 from 学校单位信息 d where d.单位号=b.单位号 and d.单位职能 is not null) and b.单位号 like 'fsyy%' then b.工号 else null end) 直属医院具有医师职称的医生数,
        count(distinct case when b.聘期>=6 and b.单位号 not like 'fsyy%' then b.工号 else null end) 一般外聘教师数,
        count(distinct case when b.聘期>=6 and b.单位号 like 'fsyy%' then b.工号 else null end) 外聘的非直属医院的教师数
        from 教职工基本信息 b
        where b.任职状态<>'当年离职'
      ) y
    ) h

首页师资情况专任教师对标
===
    select a.学校名称,count(distinct b.工号) 专任教师数
    from 在编教职工 b
    left join 教职工基本信息 c on b.工号=c.工号 and b.学校代码=c.学校代码
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where c.单位号 not like 'fsyy%' and c.任职状态='在职' and b.是否实验技术人员='否'
    and not exists(select 1 from 校领导基本信息 e where b.工号=e.工号)
    and not exists(select 1 from 相关管理人员基本信息 f where b.工号=f.工号)
    and not exists(select 1 from 学校单位信息 d where d.单位号=c.单位号 and d.单位职能 is not null)
    and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 专任教师数 desc

首页师资情况博硕覆盖率对标
===
    select a.学校名称,
    count(distinct case when c.最高学位 in('博士','硕士') then b.工号 else null end)/count(distinct b.工号)*100 博硕覆盖率
    from 在编教职工 b
    left join 教职工基本信息 c on b.工号=c.工号 and b.学校代码=c.学校代码
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where c.单位号 not like 'fsyy%' and c.任职状态='在职' and b.是否实验技术人员='否'
    and not exists(select 1 from 校领导基本信息 e where b.工号=e.工号)
    and not exists(select 1 from 相关管理人员基本信息 f where b.工号=f.工号)
    and not exists(select 1 from 学校单位信息 d where d.单位号=c.单位号 and d.单位职能 is not null)
    and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 博硕覆盖率 desc

首页师资情况生师比对标
===
    select a.学校名称,a.折合学生数/(b.专任教师数+c.其他教师总数) 生师比 from (
      select a.学校名称,
      sum(b.普通本科学生数+b.普通高职学生数+b.硕士研究生数全日制*1.5+b.博士研究生数全日制*2+b.留学生数*3+b.普通预科生数+b.进修生数+b.成人脱产学生数+b.夜大学生数*0.3+b.函授学生数*0.1) 折合学生数
      from 学生数量基本情况 b
      left join 学校基本信息 a on a.学校代码=b.学校代码
      where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
      group by a.学校代码
    ) a left join (
      select a.学校名称,count(distinct b.工号) 专任教师数
      from 在编教职工 b
      left join 教职工基本信息 c on b.工号=c.工号 and b.学校代码=c.学校代码
      left join 学校基本信息 a on a.学校代码=b.学校代码
      where c.单位号 not like 'fsyy%' and c.任职状态='在职' and b.是否实验技术人员='否'
      and not exists(select 1 from 校领导基本信息 e where b.工号=e.工号)
      and not exists(select 1 from 相关管理人员基本信息 f where b.工号=f.工号)
      and not exists(select 1 from 学校单位信息 d where d.单位号=c.单位号 and d.单位职能 is not null)
      and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
      group by a.学校代码
    ) b on a.学校名称=b.学校名称 left join (
      select y.学校名称,sum(y.直属医院具有医师职称的医生数*0.15+y.一般外聘教师数*0.5+y.外聘的非直属医院的教师数*0.5*0.15) 其他教师总数
      from (
        select a.学校名称,a.学校代码,count(distinct case when b.聘期=-1 and not exists(select 1 from 学校单位信息 d where d.单位号=b.单位号 and d.单位职能 is not null) and b.单位号 like 'fsyy%' then b.工号 else null end) 直属医院具有医师职称的医生数,
        count(distinct case when b.聘期>=6 and b.单位号 not like 'fsyy%' then b.工号 else null end) 一般外聘教师数,
        count(distinct case when b.聘期>=6 and b.单位号 like 'fsyy%' then b.工号 else null end) 外聘的非直属医院的教师数
        from 教职工基本信息 b
        left join 学校基本信息 a on a.学校代码=b.学校代码
        where b.任职状态<>'当年离职' and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
        group by a.学校代码
      ) y group by y.学校代码
    ) c on a.学校名称=c.学校名称 order by 生师比 desc