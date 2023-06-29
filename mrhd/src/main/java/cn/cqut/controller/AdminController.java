package cn.cqut.controller;

import cn.cqut.Dto.LoginDto;
import cn.cqut.service.IUserService;
import cn.cqut.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/*
 * 后台管理系统跳转页面使用的controller
 * 只能加@Controller注解，不能加@RestController注解
 * 原因：
 * @RestController是一个组合注解，包含了@Controller和@ResponseBody两个注解，
 * 而@ResponseBody注解的作用是当前方法或者当前类中的所有方法都采用响应消息体的方式传输json格式字符串给前端，而不走视图解析器【也就是不跳转页面】
 * 如果没有@ResponseBody注解的方法，它底层默认采用请求转发的方式跳转页面。
*/
@Controller
public class AdminController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/userList")
    public String toUserList(){
        return "userList";
    }
    @RequestMapping("/projectList")
    public String toProjectList(){
        return "projectList";
    }
    @RequestMapping("/technicianList")
    public String toTechnicianList(){
        return "technicianList";
    }

    @RequestMapping("/userInfoChange")
    public String toUserInfoChange(){

        return "userInfoChange";
    }
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult Login(@RequestBody LoginDto loginDto){
       try {
           Map<String, Object> map = userService.login(loginDto);
           return JsonResult.success(map);
       }catch (Exception e){
           e.printStackTrace();
           return JsonResult.error(e.getMessage());
       }
    }

    @ResponseBody
    @RequestMapping(value = "/wechatLogin",method = RequestMethod.POST)
    public JsonResult wechatLogin(@RequestBody LoginDto loginDto){
       try {
           Map<String, Object> map = userService.wechatLogin(loginDto);
           return JsonResult.success(map);
       }catch (Exception e){
           e.printStackTrace();
           return JsonResult.error(e.getMessage());
       }
    }

}
