package com.yicj.study.component;

import com.yicj.study.aware.MyAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * ClassName: UserService
 * Description: TODO(描述)
 * Date: 2020/8/29 20:37
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Component
public class UserService implements MyAware, EnvironmentAware {
    private Environment environment ;

    private Flag flag ;

    @Override
    public void setFlag(Flag flag) {

        this.flag = flag ;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment ;
    }

    public void hello(){
        log.info("======> flag canOperate : {}", flag.isCanOperate());
    }
}