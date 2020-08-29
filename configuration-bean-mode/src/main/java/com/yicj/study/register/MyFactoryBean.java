package com.yicj.study.register;

import com.yicj.study.model.Monky;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyFactoryBeam
 * Description: TODO(描述)
 * Date: 2020/8/29 19:36
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
public class MyFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new Monky();
    }

    @Override
    public Class<?> getObjectType() {
        return Monky.class;
    }
}