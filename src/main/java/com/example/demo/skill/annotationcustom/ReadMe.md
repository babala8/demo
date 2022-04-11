# spring-boot-annotation

> 此 demo 演示了 Spring Boot 自定义注解，使用了 Spring Boot 官方提供的脚手架 `spring-boot-starter-validation `。

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.ldy</groupId>
    <artifactId>sellhot</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>sellhot</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--重点jar包-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

## SellhotApplication.java

```java
/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-08 10:52
 */
@SpringBootApplication
@MapperScan("com.ldy.pojo")
public class SellhotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellhotApplication.class, args);
    }

}
```

## application.yml

```yaml

```

## AnnotationCustom.java

```java
/**
 *
 * @author
 * @apiNote 自定义手机号正则表达式校验
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {com.ldy.Utils.PatternListValidator.class}
)//引进校验器
public @interface AnnotationCustom {

    boolean required() default true;//默认不能为空

    String message() default "手机号码格式错误";//校验不通过输出信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

## User.java

```java
@Data
public class User {

    @NotNull
    private String userName;
    @NotNull
    @AnnotationCustom
    private String phoneNumber;

    // controller的各个方法入参User处添加 @Valid注解 import javax.validation.Valid ， public RespBean doLogin(@Valid @RequestBody LoginVo loginVo) {}

}
```

## PatternListValidator.java

```java

public class PatternListValidator implements ConstraintValidator<AnnotationCustom, String> {

    private boolean required = false;

    //初始化
    @Override
    public void initialize(AnnotationCustom isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
```

## ValidatorUtil.java

```java

/**
 * Created by jiangyunxiong on 2018/5/22.
 *
 * 登录校验工具类
 */
public class ValidatorUtil {

    //默认以1开头后面加10个数字为手机号
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}
```


## TestController.java

```java

@Controller
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    @ResponseBody
    public String testLogin(@Valid @RequestBody User user){
        System.out.println(user.toString());
        return "test";
    }

}
```

## 参考

- https://www.10qianwan.com/articledetail/816666.html

- http://www.cppcns.com/ruanjian/java/426975.html
