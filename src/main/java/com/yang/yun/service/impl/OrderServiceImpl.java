package com.yang.yun.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yang.yun.bean.UserAddress;
import com.yang.yun.service.OrderService;
import com.yang.yun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/8/30 9:15
 * @Version 1.0
 * 1.将服务的提供者注册到注册中心zookeeper(curator)
 *  1)导入dubbo的第依赖（2.6.2） 操作zookeepr的客户端
 *  2)配置服务
 */
@Service
public class OrderServiceImpl implements OrderService {

    /*当zookeeper宕机的时候可以消费成功消费者么？:可以使用的，只是部分数据丢失
    * 注册中心全部宕机 注册着和提供者任然可以通本地的缓存进行通信
    * */

    @Reference  //@Reference(url = "127.0.0.1:20880") 这种是采用直连的方式
    UserService userService;

    @HystrixCommand(fallbackMethod = "hello")
    @Override
    public List<UserAddress> init(String userId) {
        System.out.println("------userId-----:"+userId);
        List<UserAddress> userAddressList = userService.getUserAddressList("5");
        System.out.println(userAddressList);
//        userAddressList.stream().forEach(e-> System.out.println(e.getUserAddress()));
        return userAddressList;
    }

    public List<UserAddress> hello(String userId) {
        List<UserAddress> list = new ArrayList<>();
        System.out.println("----------provider--------"+userId);
        UserAddress userAddress1 = new UserAddress(3,"出错啦","7","俺不知道错那","000000","九年级二班");
        UserAddress userAddress2 = new UserAddress(4,"出错啦","7","俺也不知道错那","222222","九年级三班");
        list.add(userAddress1);
        list.add(userAddress2);
        return list;
    }
}
