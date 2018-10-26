package com.ali.config;

import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.MySqlStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private DataSource getDataSource(Environment environment, String url, String username, String password) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(environment.getProperty(url));
        hikariDataSource.setUsername(environment.getProperty(username));
        hikariDataSource.setPassword(environment.getProperty(password));
        return hikariDataSource;
    }

    @Bean(name = "datasource")
    public DataSource dataSource(Environment environment){
        return getDataSource(environment,
                "spring.datasource.url",
                "spring.datasource.username",
                "spring.datasource.password");
    }

    @Bean(name = "master2016datasource")
    public DataSource master2016DataSource(Environment environment){
        return getDataSource(environment,
                "spring.datasource.master2016.url",
                "spring.datasource.master2016.username",
                "spring.datasource.master2016.password");
    }

    private SQLManager getSqlManager(Environment environment, String s, String s2, String s3, String s4) {
        String driver = environment.getProperty(s);
        String url = environment.getProperty(s2);
        String username = environment.getProperty(s3);
        String password = environment.getProperty(s4);
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, username, password);
        MySqlStyle style = new MySqlStyle();
        SQLManager sqlManager = new SQLManager(style, source);
        return sqlManager;
    }

    @Bean("beetltestSQLManager")
    public SQLManager getBeetltestSQLManager(Environment environment){
        return getSqlManager(environment,
            "spring.datasource.driver-class-name",
            "spring.datasource.url",
            "spring.datasource.username",
            "spring.datasource.password"
        );
    }

    @Bean("master2016SQLManager")
    public SQLManager getMaster2016SQLManager(Environment environment){
        return getSqlManager(environment,
            "spring.datasource.master2016.driver-class-name",
            "spring.datasource.master2016.url",
            "spring.datasource.master2016.username",
            "spring.datasource.master2016.password"
        );
    }

}
