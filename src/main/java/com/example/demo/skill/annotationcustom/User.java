package com.example.demo.skill.annotationcustom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    private String userName;

    @AnnotationCustom
    private String phoneNumber;

    // controller的各个方法入参User处添加 @Valid注解 import javax.validation.Valid ， public RespBean doLogin(@Valid @RequestBody LoginVo loginVo) {}

}
