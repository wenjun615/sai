package com.wen.sai.generator;

import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.wen.sai.common.entity.BaseController;
import com.wen.sai.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * MyBatis-Plus 代码生成器
 * </p>
 *
 * @author wenjun
 * @since 2021/1/27
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator()
                // 全局配置
                .setGlobalConfig(initGlobalConfig())
                // 数据源配置
                .setDataSource(initDataSourceConfig())
                // 包配置
                .setPackageInfo(initPackageConfig())
                // 策略配置
                .setStrategy(initStrategyConfig())
                // 模板引擎
                .setTemplateEngine(new VelocityTemplateEngine());
        // 执行
        autoGenerator.execute();
    }

    /**
     * 初始化全局配置
     *
     * @return 全局配置
     */
    private static GlobalConfig initGlobalConfig() {
        return new GlobalConfig()
                // 生成文件的输出目录。默认值：D 盘根目录
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                // 是否打开输出目录。默认值：true
                .setOpen(false)
                // 是否覆盖已有文件。默认值：false
                // .setFileOverride(true)
                // 开发人员。默认值：null
                .setAuthor("wenjun")
                // Entity 命名方式。默认值：null，%s 为占位符
                .setEntityName("%s")
                // Controller 命名方式。默认值：null
                .setControllerName("%sController")
                // Service 命名方式。默认值：null
                .setServiceName("%sService")
                // ServiceImpl 命名方式。默认值：null
                .setServiceImplName("%sServiceImpl")
                // Mapper 命名方式。默认值：null
                .setMapperName("%sMapper")
                // Xml 命名方式。默认值：null
                .setXmlName("%sMapper")
                // 开启 Xml 中的 BaseResultMap。默认值：false
                .setBaseResultMap(true)
                // 开启 Xml 中的 BaseColumnList。默认值：false
                .setBaseColumnList(true)
                // 使用 Swagger。默认值：false
                .setSwagger2(true)
                // 日期类型策略。默认值：TIME_PACK
                .setDateType(DateType.ONLY_DATE);
    }

    /**
     * 初始化数据源配置
     *
     * @return 数据源配置
     */
    private static DataSourceConfig initDataSourceConfig() {
        // 获取 generator.properties
        Props props = new Props("generator.properties");
        return new DataSourceConfig()
                .setDriverName(props.getStr("dataSource.driverName"))
                .setUrl(props.getStr("dataSource.url"))
                .setUsername(props.getStr("dataSource.username"))
                .setPassword(props.getStr("dataSource.password"));
    }

    /**
     * 初始化包配置
     *
     * @return 包配置
     */
    private static PackageConfig initPackageConfig() {
        Props props = new Props("generator.properties");
        return new PackageConfig()
                // 父包名。默认值：com.baomidou
                .setParent(props.getStr("package.base"))
                // 模块名。默认值：空字符串
                // .setModuleName("")
                // Entity 包名。默认值：entity
                .setEntity("model");
        // Controller 包名。默认值：controller
        // packageConfig.setController("controller");
        // Service 包名。默认值：service
        // packageConfig.setService("service");
        // ServiceImpl 包名。默认值：service.impl
        // packageConfig.setServiceImpl("service.impl");
        // Mapper 包名。默认值：mapper
        // packageConfig.setMapper("mapper");
        // Xml 包名。默认值：mapper.xml
        // packageConfig.setXml("mapper.xml");
    }

    /**
     * 初始化策略配置
     *
     * @return 策略配置
     */
    private static StrategyConfig initStrategyConfig() {
        // 自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(createTime);
        tableFillList.add(updateTime);
        return new StrategyConfig()
                // 数据表映射到实体命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 数据表字段映射到实体属性命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 是否为 Lombok 模型。默认值：false
                .setEntityLombokModel(true)
                // 生成 @RestController。默认值：false
                .setRestControllerStyle(true)
                // 自定义 Entity 基类
                .setSuperEntityClass(BaseEntity.class)
                // Entity 基类公共字段
                // .setSuperEntityColumns("id", "is_deleted", "create_time", "update_time")
                // 自定义 Controller 基类
                .setSuperControllerClass(BaseController.class)
                // .setSuperServiceClass()
                // .setSuperServiceImplClass()
                // .setSuperMapperClass()
                // 逻辑删除字段
                .setLogicDeleteFieldName("is_deleted")
                // 乐观锁字段
                // .setVersionFieldName("version")
                // 自动填充配置
                .setTableFillList(tableFillList)
                // 数据表 Boolean 类型字段是否移除 is 前缀。默认值：false
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 驼峰转连字符（是否开启 Controller 映射连字符风格）
                .setControllerMappingHyphenStyle(false)
                // 是否在实体中生成表、字段注释。默认值：false
                .setEntityTableFieldAnnotationEnable(true)
                // 表前缀
                .setTablePrefix("ums_")
                // 字段前缀
                .setFieldPrefix("is_")
                // 表名，多个用英文逗号分隔，不写则表示生成全部表
                // .setInclude()
                // 模糊匹配表名，与 notLikeTable() 二选一配置
                .setLikeTable(new LikeTable("ums_"));
    }
}
