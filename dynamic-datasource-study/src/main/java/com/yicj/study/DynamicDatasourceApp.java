package com.yicj.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * ClassName: MybatisApp
 * Description: TODO(描述)
 * Date: 2020/8/30 17:17
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
///https://www.liangzl.com/get-article-detail-15079.html
@MapperScan("com.yicj.study.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DynamicDatasourceApp {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApp.class, args) ;
    }
}