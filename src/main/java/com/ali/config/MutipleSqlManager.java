package com.ali.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.MySqlStyle;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Getter
public class MutipleSqlManager {

    private SQLManager master2016SQLManager;

    private SQLManager master2017SQLManager;

    private SQLManager indicator2016SQLManager;

    private SQLManager baseSQLManager;

    private SQLManager master2016_14schoolSQLManager;

    private SQLManager master2017_14schoolSQLManager;

    public MutipleSqlManager(Environment environment) {
        master2016SQLManager = getSqlManager(master2016DataSource(environment));
        master2017SQLManager = getSqlManager(master2017DataSource(environment));
        indicator2016SQLManager = getSqlManager(indicator2016DataSource(environment));
        baseSQLManager = getSqlManager(baseDataSource(environment));
        master2016_14schoolSQLManager = getSqlManager(master2016_14schoolDataSource(environment));
        master2017_14schoolSQLManager = getSqlManager(master2017_14schoolDataSource(environment));
    }

    private DataSource getDataSource(Environment environment, String url, String username, String password) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(environment.getProperty(url));
        System.out.println(hikariDataSource.getJdbcUrl());
        hikariDataSource.setUsername(environment.getProperty(username));
        hikariDataSource.setPassword(environment.getProperty(password));
        return hikariDataSource;
    }

    private DataSource master2016DataSource(Environment environment){
        return getDataSource(environment,
                "dynamic.analysis.datasource.master2016.url",
                "dynamic.analysis.datasource.master2016.username",
                "dynamic.analysis.datasource.master2016.password");
    }

    public DataSource master2017DataSource(Environment environment){
        return getDataSource(environment,
                "dynamic.analysis.datasource.master2017.url",
                "dynamic.analysis.datasource.master2017.username",
                "dynamic.analysis.datasource.master2017.password");
    }

    public DataSource indicator2016DataSource(Environment environment){
        return getDataSource(environment,
                "dynamic.analysis.datasource.indicator2016.url",
                "dynamic.analysis.datasource.indicator2016.username",
                "dynamic.analysis.datasource.indicator2016.password");
    }

    public DataSource baseDataSource(Environment environment){
        return getDataSource(environment,
                "dynamic.analysis.datasource.url",
                "dynamic.analysis.datasource.username",
                "dynamic.analysis.datasource.password");
    }

    private DataSource master2016_14schoolDataSource(Environment environment){
        return getDataSource(environment,
                "dynamic.analysis.datasource.master2016_14school.url",
                "dynamic.analysis.datasource.master2016_14school.username",
                "dynamic.analysis.datasource.master2016_14school.password");
    }

    public DataSource master2017_14schoolDataSource(Environment environment){
        return getDataSource(environment,
                "dynamic.analysis.datasource.master2017_14school.url",
                "dynamic.analysis.datasource.master2017_14school.username",
                "dynamic.analysis.datasource.master2017_14school.password");
    }

    private SQLManager getSqlManager(DataSource dataSource) {
        ConnectionSource source = ConnectionSourceHelper.getSingle(dataSource);
        MySqlStyle style = new MySqlStyle();
        SQLManager sqlManager = new SQLManager(style, source);
        return sqlManager;
    }

}
