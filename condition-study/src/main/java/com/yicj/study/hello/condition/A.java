package com.yicj.study.hello.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * ClassName: A
 * Description: TODO(描述)
 * Date: 2020/8/30 13:36
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
// 当化境变量中配置了某个属性这个这个bean才会被加入到spring容器中
@ConditionalOnProperty("com.yicj.condition")
public class A {

}