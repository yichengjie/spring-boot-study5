package com.yicj.study.controller;

import com.yicj.study.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: IndexController
 * Description: TODO(描述)
 * Date: 2020/8/29 11:40
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService ;

    @GetMapping("/{key}")
    public String getKeyValue(@PathVariable("key") String key){
        return indexService.getKeyValue(key) ;
    }

}