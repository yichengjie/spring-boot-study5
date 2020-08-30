package com.yicj.study.custom;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * ClassName: MyCondition
 * Description: TODO(描述)
 * Date: 2020/8/30 13:45
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String annotationName ="com.yicj.study.custom.MyConditionAnno" ;
        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(annotationName);
        String [] props = (String[])attributes.get("value");
        for (String prop: props){
            String val = context.getEnvironment().getProperty(prop);
            if (StringUtils.isEmpty(val)){
                return false;
            }
        }
        return true;
    }
}