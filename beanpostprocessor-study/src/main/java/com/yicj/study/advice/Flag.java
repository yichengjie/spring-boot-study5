package com.yicj.study.advice;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ClassName: Flag
 * Description: TODO(描述)
 * Date: 2020/8/29 20:30
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@Component
public class Flag {
    private boolean canOperate = true ;
}