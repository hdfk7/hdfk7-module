package com.hdfk7.module;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

    public static void main(String[] args) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setServiceImplName("%sRepository");
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.TIME_PACK);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.10.10.10:3306/test?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("password");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        pc.setParent("com.hdfk7.module");
        pc.setController("controller");
        pc.setEntity("domain.entity");
        pc.setService("infrastructure");
        pc.setServiceImpl("infrastructure");
        pc.setMapper("infrastructure.mapper");
        pc.setXml("infrastructure.mapper.xml");
        mpg.setPackageInfo(pc);

        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        strategy.setRestControllerStyle(true);
        strategy.setEntitySerialVersionUID(false);
        strategy.setControllerMappingHyphenStyle(true);

        // 设置要生成的表名
        strategy.setInclude("test");

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }

}
