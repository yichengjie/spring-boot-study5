package com.yicj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: PropConfApp
 * Description: TODO(描述)
 * Date: 2020/8/29 20:02
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@PropertySource({"demo.properties"})
@SpringBootApplication
public class PropConfApp {

    public static void main(String[] args) {
        SpringApplication.run(PropConfApp.class, args) ;
    }
}