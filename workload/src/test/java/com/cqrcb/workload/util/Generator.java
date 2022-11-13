package com.cqrcb.workload.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 */
@RunWith(SpringRunner.class)
@Slf4j
public class Generator {
    /**
     * RUN THIS
     */

    @Test
    public void generateMapper() throws IOException {
        String author = "yuanziyi";
        List<String> tables = Arrays.asList("user");
        generate(author, tables);
    }

//    public void generate(String author, List<String> tables) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = buildProjectPath();
//        gc.setOutputDir(projectPath + "/src/main/java/db/entity");      //生成文件的输出目录
//        gc.setAuthor(author);                                  //作者
//        gc.setFileOverride(true);                              //是否覆蓋已有文件 默认值：false
//        gc.setOpen(false);                                    //是否打开输出目录 默认值:true
//
//        gc.setBaseColumnList(true);                              //开启 baseColumnList 默认false
//        gc.setBaseResultMap(true);                               //开启 BaseResultMap 默认false
////      gc.setEntityName("%sEntity");			//实体命名方式  默认值：null 例如：%sEntity 生成 UserEntity
//        gc.setMapperName("%sMapper");                            //mapper 命名方式 默认值：null 例如：%sDao 生成 UserDao
//        gc.setXmlName("%sMapper");                                //Mapper xml 命名方式   默认值：null 例如：%sDao 生成 UserDao.xml
//        gc.setServiceName("%sService");                            //service 命名方式   默认值：null 例如：%sBusiness 生成 UserBusiness
//        gc.setServiceImplName("%sServiceImpl");                    //service impl 命名方式  默认值：null 例如：%sBusinessImpl 生成 UserBusinessImpl
//        gc.setControllerName("%sController");    //controller 命名方式    默认值：null 例如：%sAction 生成 UserAction
//
//
//        mpg.setGlobalConfig(gc);
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://106.13.220.205:3306/staff-hour?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("wuby");
//        dsc.setPassword("123456");
//        mpg.setDataSource(dsc);
//        // 包配置
//        PackageConfig pc = new PackageConfig();
////      pc.setModuleName(scanner("模块名"));
////      pc.setParent("com.stu");
//        //自定义包配置
//        pc.setParent("com.cqrcb");
//        pc.setModuleName(null);
//        pc.setMapper("mapper");
//        pc.setEntity("domain");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setController("controller");
//        mpg.setPackageInfo(pc);
//        // 自定义配置
////        InjectionConfig cfg = new InjectionConfig() {
////            @Override
////            public void initMap() {
////                // to do nothing
////            }
////        };
////        List<FileOutConfig> focList = new ArrayList<>();
////        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
////            @Override
////            public String outputFile(TableInfo tableInfo) {
////                // 自定义输入文件名称
////                return projectPath + "/src/main/resources/mapper/" + /*pc.getModuleName() + "/" +*/
////                        tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
////            }
////        });
////        cfg.setFileOutConfigList(focList);
////        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//        // 策略配置	数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);    //表名生成策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
////	    strategy.setCapitalMode(true);			    // 全局大写命名 ORACLE 注意
////	    strategy.setTablePrefix("prefix");		    //表前缀
////	    strategy.setSuperEntityClass("com.stu.domain");	//自定义继承的Entity类全称，带包名
////	    strategy.setSuperEntityColumns(new String[] { "test_id", "age" }); 	//自定义实体，公共字段
//        strategy.setEntityLombokModel(true);        //【实体】是否为lombok模型（默认 false
//        strategy.setRestControllerStyle(true);        //生成 @RestController 控制器
////	    strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");	//自定义继承的Controller类全称，带包名
////      strategy.setInclude(scanner("表名"));		//需要包含的表名，允许正则表达式（与exclude二选一配置）
//        String[] num = tables.toArray(new String[0]);
//        strategy.setInclude(num);                       // 需要生成的表可以多张表
////	    strategy.setExclude(new String[]{"test"});      // 排除生成的表
//        strategy.setControllerMappingHyphenStyle(true);        //驼峰转连字符
//        strategy.setTablePrefix(pc.getModuleName() + "_");    //是否生成实体时，生成字段注解
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }

    public void generate(String author, List<String> tables) {
        String url = "jdbc:mysql://106.13.220.205:3306/staff-hour?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String username = "wuby";
        String password = "123456";
        String path = getOutPath();
        System.out.println("输出路径：" + path);
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(author).fileOverride().outputDir(path + "java"))
                // 包配置
                .packageConfig(builder -> builder
                        .parent("com.cqrcb.workload")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, path + "resources/mapper")))
                .injectionConfig(builder -> builder.beforeOutputFile((TableInfo, map) -> {

                }))
                // 策略配置
                .strategyConfig(builder -> builder.addInclude(getTables(tables)).controllerBuilder().enableRestStyle()//开启生成@RestController 控制器
                        .enableHyphenStyle()//开启驼峰转连字符
                        .entityBuilder().enableLombok()//启用lombok注解
                        //.addTableFills(new Column("create_time", FieldFill.INSERT))
                        .enableActiveRecord().mapperBuilder()//结果集配置
                        .enableBaseColumnList()//生成Base_Column_List
                        .enableBaseResultMap()//生成BaseResultMap
                        .build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   */.templateEngine(new FreemarkerTemplateEngine())

                .execute();
    }

    /**
     * tables = null 生成所有
     *
     * @param tables
     * @return
     */
    protected List<String> getTables(List<String> tables) {
        return tables == null ? Collections.emptyList() : tables;
    }

    public String getOutPath() {
        try {
            String targetClass = "/target/test-classes/";
            return this.getClass().getResource("/").toURI().getPath().replace(targetClass, "") + "/src/main/";
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
