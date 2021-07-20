package com.wlos.mybatis_plus_generator.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwin Feng
 * @since 2019-04-17 12:04
 */
public class CommonUtils {

    /**
     * 数据连接信息
     * @param dbType 数据库类型
     * @param dbUrl 连接地址
     * @param username 用户名
     * @param password 密码
     * @param driver 驱动
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig(DbType dbType, String dbUrl, String username, String password, String driver) {
        return new DataSourceConfig()
                .setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName(driver)
                .setTypeConvert(new MySqlTypeConvert(){
                    @Override
                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        //tinyint转换成Boolean
                        if ( fieldType.toLowerCase().contains( "int" ) ) {
                            return DbColumnType.INTEGER;
                        }
                        //将数据库中datetime转换成date
                        if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                            return DbColumnType.DATE;
                        }
                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                    }
                });
    }

    // 配置
    public static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setAuthor(Config.AUTHOR)
                .setOutputDir(Config.OUTPUT_DIR)
                .setFileOverride(true) // 是否覆盖已有文件
                .setOpen(false) // 是否打开输出目录
                .setDateType(DateType.TIME_PACK) // 时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
//                .setActiveRecord(false)// 不需要ActiveRecord特性的请改为false
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                .setSwagger2(Config.SWAGGER_SUPPORT) // model swagger2
                ;
//                if (!serviceNameStartWithI)
//                    config.setServiceName("%sService");
    }


    public static StrategyConfig strategyConfig(String [] tablePrefixes, String [] tableNames, String [] fieldPrefixes, String [] excludeTableNames) {
        return new StrategyConfig()
                .setCapitalMode(true) // 全局大写命名 ORACLE 注意
                .setSkipView(false) // 是否跳过视图
                //.setDbColumnUnderline(true)
                .setTablePrefix(tablePrefixes)// 此处可以修改为您的表前缀(数组)
                .setFieldPrefix(fieldPrefixes) // 字段前缀
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setInclude(tableNames)//修改替换成你需要的表名，多个表名传数组
                .setExclude(excludeTableNames) // 排除生成的表
                .setEntityLombokModel(true) // lombok实体
                .setChainModel(true) // 【实体】是否为构建者模型（默认 false）
                .setEntityColumnConstant(false) // 【实体】是否生成字段常量（默认 false）// 可通过常量名获取数据库字段名 // 3.x支持lambda表达式
                .setLogicDeleteFieldName(Config.FIELD_LOGIC_DELETE_NAME) // 逻辑删除属性名称
                .setVersionFieldName(Config.FIELD_VERSION_NAME) // 乐观锁字段名
                .setEntityTableFieldAnnotationEnable(true) // 开启实体字段注解
                ;
    }

    // 包信息配置
    public static PackageConfig packageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setController(Config.PACKAGE_NAME_CONTROLLER)
                .setEntity(Config.PACKAGE_NAME_MODEL)
                .setMapper(Config.PACKAGE_NAME_DAO)
                .setXml(null)
                .setService(Config.PACKAGE_NAME_SERVICE)
                .setServiceImpl(Config.PACKAGE_NAME_SERVICE_IMPL)
                .setModuleName(Config.MODULE_NAME)
                ;
    }

    /**
     *
     * @param packageConfig
     * @return
     */
    private static InjectionConfig injectionConfig(final PackageConfig packageConfig) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
        fileOutConfigList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return Config.PROJECT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 获取模板引擎
     * @return 模板引擎 {@link AbstractTemplateEngine}
     */
    private static AbstractTemplateEngine getTemplateEngine() {
        String templateEngine = Config.TEMPLATE_ENGINE;
        switch (templateEngine) {
            case "velocity":
                return new VelocityTemplateEngine();
            case "freemarker":
                return new FreemarkerTemplateEngine();
            case "beetl":
                return new BeetlTemplateEngine();
        }
        return new VelocityTemplateEngine();
    }

    /**
     * 执行器
     * @param dbType
     * @param dbUrl
     * @param username
     * @param password
     * @param driver
     * @param tablePrefixes
     * @param tableNames
     * @param packageName
     */
    public static void execute(DbType dbType, String dbUrl, String username, String password, String driver,
                               String [] tablePrefixes, String [] tableNames, String packageName, String [] fieldPrefixes, String [] excludeTableNames) {
        GlobalConfig globalConfig = globalConfig();
        DataSourceConfig dataSourceConfig = dataSourceConfig(dbType, dbUrl, username, password, driver);
        StrategyConfig strategyConfig = strategyConfig(tablePrefixes, tableNames, fieldPrefixes, excludeTableNames);
        PackageConfig packageConfig = packageConfig(packageName);
        InjectionConfig injectionConfig = injectionConfig(packageConfig);
        AbstractTemplateEngine templateEngine = getTemplateEngine();
        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(templateEngine)
                .setCfg(injectionConfig)
                .execute();
    }

}
