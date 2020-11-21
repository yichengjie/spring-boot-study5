package com.yicj.study.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
public class HomeController {
    @PostMapping("/hello")
    @ResponseBody
    public User hello(@RequestBody  User user){
        return user;
    }

    @Data
    static class User{
        private String id ;
        private String name ;
    }

    @Data
    @AllArgsConstructor
    static class OrderInfo{
        private Long id ;
        private String name ;
    }

    @Data
    @AllArgsConstructor
    static class UserInfo{
        private Long id ;
        private String name ;
    }


    @Data
    @AllArgsConstructor
    static class OrderAction{
        private Long id ;
        private String name ;
    }


    @GetMapping("/detail")
    //这里使用Map来共享数据
    public String detail(Long id, Map map){
        OrderInfo orderInfo = new OrderInfo(id, "order") ;
        UserInfo userInfo = new UserInfo(id, "user") ;
        List<OrderAction> orderActionList = Arrays.asList(new OrderAction(id, "order action")) ;
        map.put("userInfo",userInfo);
        map.put("orderInfo",orderInfo);
        map.put("orderActionList",orderActionList);
        return "order/order_detail";
    }
}
