package com.example.demo.skill.apimethod.catchException;

import com.example.demo.skill.copybeanutil.B;
import com.example.demo.skill.copybeanutil.mapstruct.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.skill.copybeanutil.A;

@Slf4j
public class CatchException {

    @Autowired
    Converter converter;

    //1. 远程调用服务，捕获异常处理，继续抛出异常
    public void test(A a){

        try {
            int i = 1/0;
            B b = converter.aToB(a);
            // throw new RuntimeException("抛出异常");
        } catch (Exception e) {
            log.error("调用服务出错，{}",e.getMessage());
            e.printStackTrace();
            // throw new RuntimeException("捕获后继续抛出异常"+e.getMessage());
        }
    }

    //2. 远程调用服务，捕获异常处理，直接return
    public B test1(A a){
        B b = null;
        try {
            int i = 1/0;
            b = converter.aToB(a);
            // 如有错误码可在此判断错误码，return
        } catch (Exception e) {
            log.error("调用服务出错，{}",e.getMessage());
            return b;
        }
        return b;
    }

    public static void main(String[] args) {
        CatchException catchException = new CatchException();
//        catchException.test(new A());
        catchException.test1(new A());
    }


}
