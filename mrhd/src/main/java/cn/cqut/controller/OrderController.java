package cn.cqut.controller;


import cn.cqut.domain.Order;
import cn.cqut.service.IOrderService;
import cn.cqut.utils.JsonResult;
import cn.cqut.utils.jwt.LoginContext;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @PostMapping("/createOrder")
    public JsonResult createOrder(@RequestBody Order order, HttpServletRequest request){
        try {
            JSONObject currentUser = LoginContext.getCurrentUser(request.getHeader(LoginContext.JWT_TOKEN_HEADER_NAME));
            order.setUserid(currentUser.getLong("id"));
            order.setOpenid(currentUser.getString("openid"));
            orderService.save(order);
            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("预约失败："+e.getMessage());
        }
    }
}
