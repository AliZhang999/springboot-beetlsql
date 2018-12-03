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

subjectList
===
    select subjectCode `key`,subjectName `name` from subject_type

获取专业技术职称指标
===
    select 专业技术职称 `key`,专业技术职称 `name` from 教职工基本信息 group by 专业技术职称

获取高层次人才指标
===
    select 类型 `key`,类型 `name` from 高层次人才 group by 类型

获取教师情况指标
===
    select 性别 `key`,性别 `name` from 教职工基本信息 group by 性别

获取学历情况指标
===
    select 学历 `key`,学历 `name` from 教职工基本信息 group by 学历

获取最高学位指标
===
    select 最高学位 `key`,最高学位 `name` from 教职工基本信息 group by 最高学位

获取高层次研究团队指标
===
    select 类型 `key`,类型 `name` from 高层次教学研究团队 group by 类型

专业技术职称指标趋势统计
===
    select count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.专业技术职称 from 在编教职工 a 
        join 教职工基本信息 b on a.工号=b.工号 where b.专业技术职称 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode#

专业技术职称指标对比统计
===
    select y.专业技术职称,count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.专业技术职称 from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号 where b.专业技术职称 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode# group by y.专业技术职称

教师情况指标趋势统计
===
    select count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.性别 from 在编教职工 a 
        join 教职工基本信息 b on a.工号=b.工号 where b.性别 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode#

教师情况指标对比统计
===
    select y.性别,count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.性别 from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号 where b.性别 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode# group by y.性别

学历情况指标趋势统计
===
    select count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.学历 from 在编教职工 a 
        join 教职工基本信息 b on a.工号=b.工号 where b.学历 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode#

学历情况指标对比统计
===
    select y.学历,count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.学历 from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号 where b.学历 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode# group by y.学历

最高学位指标趋势统计
===
    select count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.最高学位 from 在编教职工 a 
        join 教职工基本信息 b on a.工号=b.工号 where b.最高学位 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode#

最高学位指标对比统计
===
    select y.最高学位,count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,b.最高学位 from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号 where b.最高学位 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode# group by y.最高学位

高层次人才指标趋势统计
===
    select count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,c.类型
        from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号
        join 高层次人才 c on a.工号=c.工号
        where c.类型 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode#

高层次人才指标对比统计
===
    select y.类型,count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,c.类型
        from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号
        join 高层次人才 c on a.工号=c.工号
        where c.类型 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode# group by y.类型

高层次研究团队指标趋势统计
===
    select count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,c.类型
        from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号
        join 高层次教学研究团队 c on a.工号=c.负责人工号
        where c.类型 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode#

高层次研究团队指标对比统计
===
    select y.类型,count(y.工号) 总数
    from (
        select distinct 专业代码 from 专业基本情况 where 代码版本='2012'
    ) x left join (
        select distinct a.任教专业代码,b.工号,c.类型
        from 在编教职工 a
        join 教职工基本信息 b on a.工号=b.工号
        join 高层次教学研究团队 c on a.工号=c.负责人工号
        where c.类型 in (#join(indications)#)
    ) y on x.专业代码=y.任教专业代码 where left(x.专业代码,4)=#subjectcode# group by y.类型

首页高校情况
===
    select * from (
      select count(distinct case when a.单位类别='党政单位' then a.单位号 else null end ) 党政单位,
      count(distinct case when a.单位类别='教学单位' then a.单位号 else null end ) 教学单位
      from 学校单位信息 a
    ) a,(
      select count(distinct a.重点学科名称) 重点学科数 from 重点学科 a
    ) b,(
      select a.`普通本科学生数`+ a.`普通高职学生数`+ a.`硕士研究生数全日制`+ a.`博士研究生数全日制`+
      a.`留学生数`+ a.`普通预科生数`+ a.`成人脱产学生数`+ a.`进修生数` 全日制在校生数 from 学生数量基本情况 a
    ) c,(
      select count(distinct a.工号) 教职工总数 from 教职工基本信息 a
    ) d,(
      select a.占地总面积,a.总建筑面积 from 占地与建筑面积 a
    ) e
    
首页高校情况党政单位钻取
===
    select distinct a.单位号,a.单位名称,a.单位负责人 from 学校单位信息 a where a.单位类别='党政单位'
    
首页高校情况教学单位钻取
===
    select distinct a.单位号,a.单位名称,a.单位负责人 from 学校单位信息 a where a.单位类别='教学单位'

首页高校情况重点学科钻取
===
    select a.学科代码,a.重点学科名称,a.级别 from 重点学科 a
    
首页高校情况在校生数钻取
===
    select a.`普通本科学生数`,a.`硕士研究生数全日制`,a.`博士研究生数全日制`,a.`留学生数` from 学生数量基本情况 a
    
首页高校情况教职工总数专业技术职称钻取
===
    select count(distinct a.工号) 人数,a.专业技术职称 from 教职工基本信息 a group by a.专业技术职称
    
首页高校情况教职工总数最高学位钻取
===
    select count(distinct a.工号) 人数,a.最高学位 from 教职工基本信息 a group by a.最高学位
    
首页高校情况总占地面积钻取
===
    select a.占地总面积,a.占地面积学校产权,a.学校产权绿化用地,a.学校产权运动场地,a.占地非学校产权,
    a.非学校产权独立使用,a.非学校产权共同使用,a.非学校产权绿化用地 from 占地与建筑面积 a
    
首页教学成果国家级教学成果奖对标
===
    * select a.主持人,a.项目名称 from 教育教学研究与改革项目 a where a.级别='国家级'
    select b.学校名称,sum(a.教学成果奖国家级) 国家级教学成果奖
    from 教学管理人员成果 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 国家级教学成果奖 desc
    
首页教学成果国家级教育教学研究与改革项目对标
===
    * select a.项目名称,a.项目类别,a.主持人姓名 from 省级及以上本科教学工程项目情况 a where a.项目级别='省部级'
    select b.学校名称,count(case when a.级别='国家级' then a.立项编号或批准文号 else null end) 国家级教育教学研究与改革项目
    from 教育教学研究与改革项目 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 国家级教育教学研究与改革项目 desc

首页教学成果国家级本科教学工程对标
===
    * select a.项目名称,a.项目类别,a.主持人姓名 from 省级及以上本科教学工程项目情况 a where a.项目级别='国家级'
    select b.学校名称,count(case when a.项目级别='国家级' then 1 else null end) 国家级本科教学工程
    from 省级及以上本科教学工程项目情况 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 国家级本科教学工程 desc

首页教学成果省部级本科教学工程对标
===
    * select a.基地名称,a.基地类型,a.参与角色 from 高校实践育人创新创业基地 a where a.基地级别='国家级'
    select b.学校名称,count(case when a.项目级别='省部级' then 1 else null end) 省部级本科教学工程
    from 省级及以上本科教学工程项目情况 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 省部级本科教学工程 desc
    
首页教学成果人才培养模式创新实验区对标
===
    * select a.名称,a.参与学生数 from 人才培养模式创新实验项目 a where a.类型='人才培养模式创新实验区'
    select b.学校名称,count(case when a.类型='人才培养模式创新实验区' then 1 else null end) 人才培养模式创新实验区
    from 人才培养模式创新实验项目 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 人才培养模式创新实验区 desc
    
首页教学成果国家级教学基地对标
===
    * select a.名称,a.参与学生数 from 人才培养模式创新实验项目 a where a.类型='国家级教学基地'
    select b.学校名称,count(case when a.类型='国家级教学基地' then 1 else null end) 国家级教学基地
    from 人才培养模式创新实验项目 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 国家级教学基地 desc
    
首页教学成果牵头建设国家级实践育人创新创业基地对标
===
    select b.学校名称,count(case when a.基地级别='国家级' then 1 else null end) 牵头建设国家级实践育人创新创业基地
    from 高校实践育人创新创业基地 a left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 牵头建设国家级实践育人创新创业基地 desc

首页科研水平
===
    select * from (
      select count(distinct a.授权号) 专利著作权数 from 教师专利授权情况 a where a.获批时间=(#year#-1)
    ) a,(
      select count(distinct a.ISBN) 出版专著数 from 教师出版专著情况 a where a.出版时间=(#year#-1)
    ) b,(
      select count(distinct a.论文名称) 发表论文数 from 教师发表的论文情况 a where a.发表时间=(#year#-1)
    ) c,(
      select count(case when a.项目性质='纵向项目' then 1 else null end) 纵向项目数,
      count(case when a.项目性质='横向项目' then 1 else null end) 横向项目数,
      sum(case when a.项目性质='纵向项目' then a.项目经费国内+a.项目经费国际 else 0 end) 纵向项目经费,
      sum(case when a.项目性质='横向项目' then a.项目经费国内+a.项目经费国际 else 0 end)横向项目经费
      from 教师主持科研项目情况 a where 立项时间=(#year#-1)
    ) d

首页科研水平专利著作权数对标
===
    * select distinct a.名称,a.授权号,a.类型,a.教师姓名 from 教师专利授权情况 a where a.获批时间=(#year#-1)
    select b.学校名称,count(distinct a.授权号) 专利著作权数
    from 教师专利授权情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.获批时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 专利著作权数 desc

首页科研水平出版专著数对标
===
    * select distinct a.专著名称,a.专著类别,a.ISBN,a.教师姓名 from 教师出版专著情况 a where a.出版时间=(#year#-1)
    select b.学校名称,count(distinct a.ISBN) 出版专著数
    from 教师出版专著情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.出版时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 出版专著数 desc

首页科研水平发表论文数对标
===
    * select distinct a.论文名称,a.收录情况,a.发表期刊,a.教师姓名 from 教师发表的论文情况 a where a.发表时间=(#year#-1)
    select b.学校名称,count(distinct a.论文名称) 发表论文数
    from 教师发表的论文情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.发表时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 发表论文数 desc
    
首页科研水平纵向项目数对标
===
    * select a.项目名称,a.纵向项目类别,a.教师姓名,a.工号 from 教师主持科研项目情况 a where a.项目性质='纵向项目' and 立项时间=(#year#-1)
    select b.学校名称,count(case when a.项目性质='纵向项目' then 1 else null end) 纵向项目数
    from 教师主持科研项目情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.立项时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 纵向项目数 desc
    
首页科研水平横向项目数对标
===
    * select a.项目名称,a.纵向项目类别,a.教师姓名,a.工号 from 教师主持科研项目情况 a where a.项目性质='横向项目' and 立项时间=(#year#-1)
    select b.学校名称,count(case when a.项目性质='横向项目' then 1 else null end) 横向项目数
    from 教师主持科研项目情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.立项时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 横向项目数 desc
    
首页科研水平横向项目经费对标
===
    select b.学校名称,sum(case when a.项目性质='横向项目' then a.项目经费国内+a.项目经费国际 else 0 end) 横向项目经费
    from 教师主持科研项目情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.立项时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 横向项目经费 desc
    
首页科研水平纵向项目经费对标
===
    select b.学校名称,sum(case when a.项目性质='纵向项目' then a.项目经费国内+a.项目经费国际 else 0 end) 纵向项目经费
    from 教师主持科研项目情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.立项时间=(#year#-1) and a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 纵向项目经费 desc
    
首页办学条件
===
    select * from (
      select a.学校教育经费总额 教育经费总额,a.教学经费总额 教学经费总额 from 教育经费概况 a
    ) a,(
      select a.教育经费支出总计 教学经费支出,a.实践教学支出 实践教学经费 from 教育经费收支情况 a
    ) b,(
      select a.固定资产总值 固定资产总值,a.教学科研仪器设备资产总值 教学仪器设备值 from 固定资产 a
    ) c
    
首页办学条件教育经费总额对标
===
    select b.学校名称,a.学校教育经费总额 教育经费总额
    from 教育经费概况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 教育经费总额 desc
    
首页办学条件教学仪器设备值对标
===
    select b.学校名称,a.教学科研仪器设备资产总值 教学仪器设备值
    from 固定资产 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 教学仪器设备值 desc
    
首页办学条件教学经费总额对标
===
    select b.学校名称,a.教学经费总额 教学经费总额
    from 教育经费概况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 教学经费总额 desc
    
首页办学条件教学经费支出对标
===
    select b.学校名称,a.教育经费支出总计 教学经费支出
    from 教育经费收支情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 教学经费支出 desc

首页办学条件实践教学经费对标
===
    select b.学校名称,a.实践教学支出 实践教学经费
    from 教育经费收支情况 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 实践教学经费 desc

首页办学条件固定资产总值对标
===
    select b.学校名称,a.固定资产总值 固定资产总值
    from 固定资产 a
    left join 学校基本信息 b on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 固定资产总值 desc
    
首页学生情况
===
    select * from (
      select b.`普通本科学生数` 本科生,b.`普通高职学生数` 专科生,
      b.`硕士研究生数` 硕士研究生,b.`博士研究生数` 博士研究生,b.`留学生数` 留学生
      from 学生数量基本情况 b
    ) a,(
      select b.`实际录取数` 招生人数 from `近一届本科生招生类别情况` b
    ) b,(
      select sum(b.`应届毕业生数`) 毕业生人数,sum(b.`应届就业人数`) 就业人数 from 应届本科毕业生分专业毕业就业情况 b
    ) c,(
      select b.`学校所在区域就业总数` 本省就业生数 from 应届本科毕业生就业情况 b
    ) d
    
首页学生情况本科生对标
===
    select a.学校名称,b.普通本科学生数 本科生
    from 学生数量基本情况 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 本科生 desc

首页学生情况专科生对标
===
    select a.学校名称,b.普通高职学生数 专科生
    from 学生数量基本情况 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 专科生 desc

首页学生情况硕士研究生对标
===
    select a.学校名称,b.硕士研究生数 硕士研究生
    from 学生数量基本情况 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 硕士研究生 desc

首页学生情况博士研究生对标
===
    select a.学校名称,b.博士研究生数 博士研究生
    from 学生数量基本情况 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 博士研究生 desc

首页学生情况留学生对标
===
    select a.学校名称,b.留学生数 留学生
    from 学生数量基本情况 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 留学生 desc
   
首页学生情况实际录取数对标
===
    select a.学校名称,b.实际录取数
    from 近一届本科生招生类别情况 b 
    left join 学校基本信息 a on b.学校代码=a.学校代码 
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 实际录取数 desc
   
首页学生情况就业生数对标
=== 
    select a.学校名称,sum(b.`应届就业人数`) 就业生数
    from 应届本科毕业生分专业毕业就业情况 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 就业生数 desc
    
首页学生情况毕业生数对标
===
    select a.学校名称,sum(b.`应届毕业生数`) 毕业生数
    from 应届本科毕业生分专业毕业就业情况 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by a.学校代码 order by 毕业生数 desc

首页师资情况教职工数对标
===
    * select distinct a.姓名,a.研究方向 from 高层次人才 a where a.工号 in (select distinct 工号 from 高层次人才)
    select a.学校名称,count(b.工号) 教职工人数
    from 教职工基本信息 b left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 教职工人数 desc
    
首页师资情况实验师资对标
===
    select a.学校名称,count(case when b.是否实验技术人员='是' then 1 else null end) 实验师资
    from 在编教职工 b left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 实验师资 desc
    
首页师资情况高层次人才对标
===
    select a.学校名称,count(distinct b.工号) 高层次人才总数
    from 高层次人才 b left join 学校基本信息 a on a.学校代码=b.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 高层次人才总数 desc
    
首页师资情况教授上台讲课比例对标
===
    select a.学校名称,a.上课教授数/b.教授总数*100 教授上台讲课比例 from (
      select c.学校名称,count(distinct b.工号) 上课教授数
      from 开课情况 a
      left join 教职工基本信息 b on a.授课教师工号=b.工号
      left join 学校基本信息 c on c.学校代码=b.学校代码
      where b.专业技术职称='教授' and c.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
      group by a.学校代码
    ) a left join (
      select a.学校名称,count(distinct b.工号) 教授总数
      from 教职工基本信息 b
      left join 学校基本信息 a on a.学校代码=b.学校代码
      where b.专业技术职称='教授' and b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
      group by a.学校代码
    ) b on a.学校名称=b.学校名称 order by 教授上台讲课比例 desc

首页学科专业
===
    select * from (
      select 本科专业总数 from 学科建设
    ) a,(
        select count(distinct 学科代码) 重点学科数 from 重点学科
    ) b,(
      select 
      count(case when b.类型='博士学位授权一级学科点' then b.名称 else null end) 博士学位授权一级学科点,
      count(case when b.类型='博士学位授权二级学科点' then b.名称 else null end) 博士学位授权二级学科点,
      count(case when b.类型='硕士学位授权一级学科点' then b.名称 else null end) 硕士学位授权一级学科点,
      count(case when b.类型='硕士学位授权二级学科点' then b.名称 else null end) 硕士学位授权二级学科点
      from 博士点硕士点 b
    ) c,(
      select count(distinct b.博士后流动站名称) 博士后流动站 from 博士后流动站 b
    ) d
    
首页学科专业开设专业钻取
===
    select distinct b.专业代码,b.专业名称 from 专业基本情况 b where b.代码版本='2012'
    
首页学科专业覆盖学科门类钻取
===
    select b.授予学位门类 from 专业基本情况 b group by b.授予学位门类
    
首页学科专业重点学科对标
===
    select a.学校名称,count(distinct b.学科代码) 重点学科数
    from 重点学科 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 重点学科数 desc
    
首页学科专业本科专业总数对标
===
    select a.学校名称,b.本科专业总数
    from 学科建设 b
    left join 学校基本信息 a on a.学校代码=b.学校代码
    where a.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759)
    group by a.学校代码 order by 本科专业总数 desc
    
首页学科专业博士学位授权一级学科点对标
===
    * select b.单位名称,b.名称 from 博士点硕士点 b where b.类型='博士学位授权一级学科点'
    select a.学校名称,
    count(case when b.类型='博士学位授权一级学科点' then b.名称 else null end) 博士学位授权一级学科点
    from 博士点硕士点 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 博士学位授权一级学科点 desc
    
首页学科专业博士学位授权二级学科点对标
===
    * select b.单位名称,b.名称 from 博士点硕士点 b where b.类型='博士学位授权二级学科点'
    select a.学校名称,
    count(case when b.类型='博士学位授权二级学科点' then b.名称 else null end) 博士学位授权二级学科点
    from 博士点硕士点 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 博士学位授权二级学科点 desc
    
首页学科专业硕士学位授权一级学科点对标
===
    * select b.单位名称,b.名称 from 博士点硕士点 b where b.类型='硕士学位授权一级学科点'
    select a.学校名称,
    count(case when b.类型='硕士学位授权一级学科点' then b.名称 else null end) 硕士学位授权一级学科点
    from 博士点硕士点 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 硕士学位授权一级学科点 desc
    
首页学科专业硕士学位授权二级学科点对标
===
    * select b.单位名称,b.名称 from 博士点硕士点 b where b.类型='硕士学位授权二级学科点'
    select a.学校名称,
    count(case when b.类型='硕士学位授权二级学科点' then b.名称 else null end) 硕士学位授权二级学科点
    from 博士点硕士点 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 硕士学位授权二级学科点 desc
    
首页学科专业博士后流动站对标
===
    * select distinct b.博士后流动站名称,b.单位名称 from 博士后流动站 b
    select a.学校名称,count(distinct b.博士后流动站名称) 博士后流动站
    from 博士后流动站 b
    left join 学校基本信息 a on b.学校代码=a.学校代码
    where b.学校代码 in (10075,10108,10126,10403,10459,10593,10589,10657,10673,10694,10743,10749,10755,10759) 
    group by b.学校代码 order by 博士后流动站 desc
