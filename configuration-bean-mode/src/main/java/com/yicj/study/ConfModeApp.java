package com.yicj.study;

import com.yicj.study.register.MyBeanImport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * ClassName: ConfModeApp
 * Description: TODO(描述)
 * Date: 2020/8/29 12:27
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Import(MyBeanImport.class)
@SpringBootApplication
public class ConfModeApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfModeApp.class, args) ;
    }
}