package com.yicj.study.condition;

import com.yicj.study.custom.MyConditionAnno;
import org.springframework.stereotype.Component;

/**
 * ClassName: B
 * Description: TODO(描述)
 * Date: 2020/8/30 13:55
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
@MyConditionAnno({"com.yicj.condition2","com.yicj.condition3"})
public class B {
}