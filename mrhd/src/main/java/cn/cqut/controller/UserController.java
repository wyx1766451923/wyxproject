package cn.cqut.controller;

import cn.cqut.domain.User;
import cn.cqut.query.UserQuery;
import cn.cqut.service.IUserService;
import cn.cqut.utils.JsonResult;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/userListPage")
    public JsonResult userListPage(@RequestBody UserQuery query){
        Page<User> page = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .like(StringUtils.hasText(query.getUsername()),"username",query.getUsername())
                .like(StringUtils.hasText(query.getNickname()),"nickname",query.getNickname())
                .eq(StringUtils.hasText(query.getPhone()),"phone",query.getPhone());
        page = userService.page(page, wrapper);
        //Map  实体类
        return JsonResult.success(new PageList<User>(page.getTotal(), page.getRecords()));
    }
    @PostMapping("/userChangeInfo")
    public JsonResult userChangeInfo(@RequestBody User user){
        try {
            String password = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(password);
            userService.updateById(user);

            return JsonResult.success(user);
        }catch(Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }
}
