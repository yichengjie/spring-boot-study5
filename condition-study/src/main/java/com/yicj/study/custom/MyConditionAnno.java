package com.yicj.study.custom;

import org.springframework.context.annotation.Conditional;
import java.lang.annotation.*;

/**
 * ClassName: MyConditionAnno
 * Description: TODO(描述)
 * Date: 2020/8/30 13:45
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(MyCondition.class)
public @interface MyConditionAnno {

    String [] value() default {} ;
}