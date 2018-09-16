package com.yang.yun.controller;

import com.yang.yun.bean.UserAddress;
import com.yang.yun.service.OrderService;
import com.yang.yun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/8/30 11:15
 * @Version 1.0
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;


    @RequestMapping("/initOrder")
    @ResponseBody
    public List<UserAddress> orderTest(@RequestParam("uid") String userId) {
        System.out.println("----RequestParam-----userId--"+userId);
        List<UserAddress> init = orderService.init(userId);

        init.stream().forEach(e-> System.out.println(e.getUserAddress()));
        return init;
    }


}
