package com.example.demo;

import org.apache.commons.lang3.StringUtils;

public class SubStrTest {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemp = new StringBuilder();
        sbTemp.append("被保人姓名、");
        sbTemp.append("被保人证件类型、");
        sbTemp.append("被保人证件号码、");
        String strTemp = sbTemp.toString().trim();
        System.out.println("strTemp:"+strTemp);
        if(StringUtils.isNotBlank(strTemp)){
            System.out.println(sb.append("hhhhhhh:" + strTemp.substring(0, strTemp.lastIndexOf('、'))));
        }

//        if(strTemp != null){
//            sb.append("hhhhhh"+strTemp.substring(0,strTemp.lastIndexOf(',')));
//        }
    }
}
