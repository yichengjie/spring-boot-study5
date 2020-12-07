package com.yicj.study.aware;

import com.yicj.study.advice.Flag;
import org.springframework.beans.factory.Aware;

/**
 * ClassName: MyAware
 * Description: TODO(描述)
 * Date: 2020/8/29 20:29
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface MyAware extends Aware {

    void setFlag(Flag flag) ;
}