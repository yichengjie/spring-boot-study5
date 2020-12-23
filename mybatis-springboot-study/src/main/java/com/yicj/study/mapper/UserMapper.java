package com.yicj.study.mapper;

import com.yicj.study.common.MyKeyList;
import com.yicj.study.entity.User;
import com.yicj.study.vo.LoginParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: UserMapper
 * Description: TODO(描述)
 * Date: 2020/8/16 19:38
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface UserMapper {

    User selectById(Integer id) ;

    List<User> select4Login(LoginParam loginParam) ;

    int insert(User user) ;

    MyKeyList<User> selectByUserItemResultMapper() ;
}