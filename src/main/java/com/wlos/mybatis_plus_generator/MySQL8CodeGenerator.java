package com.wlos.mybatis_plus_generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.wlos.mybatis_plus_generator.config.CommonUtils;
import com.wlos.mybatis_plus_generator.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * MySQL 数据库代码生成类
 * @author Erwin Feng
 * @since 2019-04-17 10:33
 */
@Component
public class MySQL8CodeGenerator {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${DbType}")
    private String dbTypes;

    public void generatorCode(String[] tableNames) {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = CommonUtils.globalConfig();
        generator.setGlobalConfig(gc);

        // 数据源配置:跟随配置文件，MYSQL或者ORACLE都行；默认MYSQL
        DataSourceConfig dsc = new DataSourceConfig();
        DbType dbType = DbType.MYSQL;
        if(DbType.MYSQL.getDb().equals(dbTypes)){
            dbType = DbType.MYSQL;
        }else if(DbType.ORACLE.getDb().equals(dbTypes)){
            dbType = DbType.ORACLE;
        }
        dsc.setDbType(dbType);
        dsc.setUrl(url);
        dsc.setDriverName(driverClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }

        });
        generator.setDataSource(dsc);

        // 包配置
        PackageConfig pc = CommonUtils.packageConfig(Config.PACKAGE_NAME);
        generator.setPackageInfo(pc);

        // 自定义配置--生成mapper.xml（mybatis_plus暂不需要xml文件）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // xml模板
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return Config.PROJECT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        // 策略配置
        // 表前缀，生成的实体类，不含前缀
        String [] tablePrefixes = {};
        // 表名，为空，生成所有的表
//        String [] tableNames = {"school"};
        // 字段前缀
        String [] fieldPrefixes = {};
        // 排除的表名
        String [] excludeTableNames = {};
        StrategyConfig strategy =CommonUtils.strategyConfig(tablePrefixes,tableNames,fieldPrefixes,excludeTableNames);
        generator.setStrategy(strategy);

        //模板引擎：freemarker
        FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();
        generator.setTemplateEngine(freemarkerTemplateEngine);

        generator.execute();
    }
}
