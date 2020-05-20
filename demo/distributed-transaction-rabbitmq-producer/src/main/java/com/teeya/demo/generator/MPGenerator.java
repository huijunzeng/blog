package com.teeya.demo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.teeya.common.web.entity.pojo.BaseEntity;

/**
 * mybatis-plus代码生成器
 * https://mp.baomidou.com/config/generator-config.html#mapper-2 文档链接
 * @Author: ZJH
 * @Date: 2020/3/10 10:05
 */
public class MPGenerator {

    public static void main(String[] args) {
        // 创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 指定模板引擎  默认velocity
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F:\\IdeaProjects\\blog\\demo\\distributed-transaction-rabbitmq-producer\\src\\main\\java");// 生成文件的输出目录
        gc.setFileOverride(true); //是否覆盖已有文件
        gc.setOpen(false);// 是否打开输出目录
        gc.setAuthor("ZJH");// 开发人员
        gc.setSwagger2(false);// 开启 swagger2 模式
        gc.setBaseResultMap(true); // XML是否需要BaseResultMap
        gc.setBaseColumnList(true); // XML是否显示字段
        gc.setDateType(DateType.ONLY_DATE);// 时间类型对应策略
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setEntityName("%sEntity");// 实体命名方式 例如：%sEntity 生成 UserEntity
        gc.setXmlName("%sMapper");
        gc.setIdType(IdType.ASSIGN_ID);// 指定生成的主键的ID类型
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);// 数据库类型
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");// 驱动名称
        dsc.setUrl("jdbc:mysql://129.211.34.120:3306/distributed-transaction-rabbitmq-producer?characterEncoding=utf8&useSSL=false");// 驱动连接的URL
        dsc.setUsername("root");// 数据库连接用户名
        dsc.setPassword("zeng@19940125...");// 数据库连接密码
        mpg.setDataSource(dsc);

        // 数据库表配置
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel); // 表名生成策略
        sc.setSuperEntityClass(BaseEntity.class);// 自定义继承的Entity类全称，带包名
        sc.setEntityBuilderModel(true);// 是否为构建者模型
        sc.setEntityLombokModel(true);// 是否为lombok模型
        sc.setRestControllerStyle(true);// 生成 @RestController 控制器
        sc.setEntityTableFieldAnnotationEnable(true);// 生成实体时，生成字段注解
        mpg.setStrategy(sc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.teeya.demo");// 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("entity.pojo");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        mpg.execute();
    }
}
