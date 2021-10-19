package cn.zh.order.service;

import cn.zh.order.client.userClient;
import cn.zh.order.mapper.OrderMapper;
import cn.zh.order.pojo.Order;
import cn.zh.order.pojo.User;
import org.checkerframework.checker.units.qual.A;
import org.omg.PortableInterceptor.ObjectReferenceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private  userClient userClient;

    public Order queryOrderById(long orderId){
        //1.查询订单
        Order order = orderMapper.findById(orderId);
        User user= userClient.findById(order.getUserId());
        // 3.封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
    // public Order queryOrderById(Long orderId) {
    //     // 1.查询订单
    //     Order order = orderMapper.findById(orderId);
    //     // 2.利用RestTemplate发起http请求，查询用户
    //     // 2.1.url路径
    //     // String url = "http://localhost:8080/user/" + order.getUserId();
    //     String url = "http://userservice/user/" + order.getUserId();
    //       // 2.2.发送http请求，实现远程调用
    //     User user = restTemplate.getForObject(url, User.class);
    //     // 3.封装user到Order
    //     order.setUser(user);
    //     // 4.返回
    //     return order;
    // }
}
