package com.yicj.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: User
 * Description: TODO(描述)
 * Date: 2020/8/16 19:33
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id ;
    private String username ;
    private String password ;
    private String roles ;
}