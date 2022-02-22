package com.example.demo.skill.copybeanutil.mapstruct;


import com.example.demo.skill.copybeanutil.A;
import com.example.demo.skill.copybeanutil.B;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 怎么自动生成ConverterImpl？ 参考
 * https://blog.csdn.net/chenwen0326/article/details/122233399?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-1-122233399.pc_agg_new_rank&utm_term=mapstruct+%E4%BD%95%E6%97%B6%E7%94%9F%E6%88%90%E7%B1%BB&spm=1000.2123.3001.4430
 * 1.添加pom依赖
 *         <!-- https://mvnrepository.com/artifact/org.mapstruct/mapstruct -->
 *         <dependency>
 *             <groupId>org.mapstruct</groupId>
 *             <artifactId>mapstruct</artifactId>
 *             <version>1.4.2.Final</version>
 *         </dependency>
 *         <!-- https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor -->
 *         <dependency>
 *             <groupId>org.mapstruct</groupId>
 *             <artifactId>mapstruct-processor</artifactId>
 *             <version>1.4.2.Final</version>
 *         </dependency>
 * 2.使用mapstruct, 项目根目录的pom文件中加入
 * <build></build>和<properties></properties>
 */

/**
 * @Mapper 定义这是一个MapStruct对象属性转换接口，在这个类里面规定转换规则
 *          在项目构建时，会自动生成改接口的实现类，这个实现类将实现对象属性值复制
 */
@Mapper
public interface Converter {

    /**
     * 获取该类自动生成的实现类的实例
     * 接口中的属性都是 public static final 的 方法都是public abstract的
     */
    Converter INSTANCE = Mappers.getMapper(Converter.class);

    /**
     * 这个方法就是用于实现对象属性复制的方法
     *
     * @param car 这个参数就是源对象，也就是需要被复制的对象
     * @return 返回的是目标对象，就是最终的结果对象
     * @Mapping 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性
     */
    @Mappings({
//            @Mapping(source = "car.id", target = "userId"),
//            @Mapping(source = "car.username", target = "name"),
//            @Mapping(source = "role.roleName", target = "roleName")
    })
    B aToB(A car);
}