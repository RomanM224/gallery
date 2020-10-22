package com.maistruk.galery.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.maistruk.galery.controller.FileController;
import com.maistruk.galery.controller.GaleryController;
import com.maistruk.galery.controller.PainterController;
import com.maistruk.galery.controller.PaintingController;
import com.maistruk.galery.dao.PainterDao;
import com.maistruk.galery.dao.PaintingDao;
import com.maistruk.galery.dao.impl.PainterDaoImpl;
import com.maistruk.galery.dao.impl.PaintingDaoImpl;
import com.maistruk.galery.dao.impl.rowMapper.PainterRowMapper;
import com.maistruk.galery.dao.impl.rowMapper.PaintingRowMapper;
import com.maistruk.galery.service.PainterService;
import com.maistruk.galery.service.PaintingService;

@Configuration
@ComponentScan({ "com.maistruk.galery.config" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    
    @Bean
    public GaleryController galeryController() {
        return new GaleryController();
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driver}")
    private String driver;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setDriverClassName(driver);
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public PainterDao painterDao() {
        return new PainterDaoImpl();
    }
    
    @Bean 
    public PaintingDao paintingDao() {
        return new PaintingDaoImpl();
    }
    
    @Bean
    public PainterRowMapper painterRowMapper() {
        return new PainterRowMapper();
    }
    
    @Bean
    public PaintingRowMapper paintingRowMapper() {
        return new PaintingRowMapper();
    }
    
    @Bean
    public PainterService painterService() {
        return new PainterService();
    }
    
    @Bean
    public PaintingService paintingService() {
        return new PaintingService();
    }
    
    @Bean
    public PainterController painterController() {
        return new PainterController();
    }
    
    @Bean
    public PaintingController paintingController() {
        return new PaintingController();
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
    
    @Bean
    public FileController fileController() {
        return new FileController();
    }
}
