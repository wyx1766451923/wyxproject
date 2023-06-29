package cn.cqut.service;

import cn.cqut.Dto.LoginDto;
import cn.cqut.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface IUserService extends IService<User> {
    Map<String, Object> login(LoginDto loginDto);

    Map<String, Object> wechatLogin(LoginDto loginDto);

}
